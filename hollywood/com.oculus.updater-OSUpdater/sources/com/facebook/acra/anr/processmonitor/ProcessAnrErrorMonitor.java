package com.facebook.acra.anr.processmonitor;

import android.app.ActivityManager;
import android.content.Context;
import android.os.DeadObjectException;
import android.os.Process;
import androidx.annotation.VisibleForTesting;
import com.facebook.debug.log.BLog;
import com.facebook.errorreporting.appstate.GlobalAppState;
import com.facebook.infer.annotation.Assertions;
import com.facebook.infer.annotation.Nullsafe;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class ProcessAnrErrorMonitor {
    public static final int DEFAULT_POLLING_TIME_MS = 500;
    private static final String LOG_TAG = "ProcessAnrErrorMonitor";
    public static final int UNLIMITED_NUMBER_OF_CHECKS = 0;
    private final Context mContext;
    private final boolean mContinuousMonitoring;
    @GuardedBy("this")
    private long mCurrentMonitorThreadId;
    @GuardedBy("this")
    @Nullable
    private MonitorThread mErrorCheckThread;
    private final int mMaxNumberOfChecksAfterError;
    private final int mMaxNumberOfChecksBeforeError;
    private final int mMyUid;
    private final int mPollingTime;
    private final String mProcessName;
    @GuardedBy("this")
    private State mState;

    public enum State {
        NOT_MONITORING,
        MONITORING_NO_ERROR_DETECTED,
        MONITORING_ERROR_DETECTED
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public enum StateChangeReason {
        MONITOR_STARTED,
        ERROR_CLEARED,
        ERROR_DETECTED,
        MAX_NUMBER_BEFORE_ERROR,
        MAX_NUMBER_AFTER_ERROR,
        STOP_REQUESTED,
        ERROR_QUERYING_ACTIVITY_MANAGER
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public static class AnrErrorState {
        @Nullable
        String mErrorMsg;
        @Nullable
        String mProcessName;
        @Nullable
        String mTag;

        AnrErrorState() {
        }
    }

    public ProcessAnrErrorMonitor(Context context, String str, int i, int i2) {
        this(context, str, false, DEFAULT_POLLING_TIME_MS, false, i, i2);
    }

    public ProcessAnrErrorMonitor(Context context, String str, boolean z, int i, int i2) {
        this(context, str, z, DEFAULT_POLLING_TIME_MS, false, i, i2);
    }

    public ProcessAnrErrorMonitor(Context context, String str, boolean z, int i, boolean z2, int i2, int i3) {
        this.mContext = context;
        this.mProcessName = str;
        this.mState = State.NOT_MONITORING;
        this.mPollingTime = i;
        this.mContinuousMonitoring = z2;
        this.mMaxNumberOfChecksBeforeError = i2;
        this.mMaxNumberOfChecksAfterError = i3;
        this.mMyUid = Process.myUid();
        if (z) {
            startMonitoring(null);
        }
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public static class AnrCheckState {
        boolean mAnrConfirmed;
        int mCount;

        AnrCheckState() {
        }
    }

    @VisibleForTesting
    public class MonitorThread extends Thread {
        private final ActivityManager mAm;
        private final long mDelay;
        private boolean mFirstCheck;
        private final long mId;
        @Nullable
        private volatile ProcessErrorStateListener mListener;
        private final Object mMonitorLock;
        @GuardedBy("mMonitorLock")
        private boolean mPauseRequested;
        private final Set<String> mProcessesInAnr;
        @GuardedBy("mMonitorLock")
        private boolean mStopRequested;

        private MonitorThread(ActivityManager activityManager, @Nullable ProcessErrorStateListener processErrorStateListener, long j, long j2) {
            super("ProcessAnrErrorMonitorThread:" + GlobalAppState.getSessionId());
            this.mMonitorLock = new Object();
            this.mProcessesInAnr = new HashSet();
            this.mAm = activityManager;
            this.mListener = processErrorStateListener;
            this.mId = j;
            this.mDelay = j2;
            this.mFirstCheck = true;
        }

        public void run() {
            monitorLoop();
        }

        public long getMonitorId() {
            return this.mId;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void stopRequested() {
            synchronized (this.mMonitorLock) {
                this.mStopRequested = true;
                this.mMonitorLock.notifyAll();
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void pauseRequested() {
            synchronized (this.mMonitorLock) {
                this.mPauseRequested = true;
                this.mMonitorLock.notifyAll();
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void resumeRequested() {
            synchronized (this.mMonitorLock) {
                this.mPauseRequested = false;
                this.mMonitorLock.notifyAll();
            }
        }

        /* access modifiers changed from: package-private */
        public boolean hasListener() {
            return this.mListener != null;
        }

        /* access modifiers changed from: package-private */
        public void setListener(ProcessErrorStateListener processErrorStateListener) {
            synchronized (this.mMonitorLock) {
                this.mListener = processErrorStateListener;
                this.mMonitorLock.notifyAll();
            }
        }

        /* access modifiers changed from: package-private */
        @VisibleForTesting
        public boolean checkIteration(AnrCheckState anrCheckState) {
            boolean z = true;
            try {
                LinkedList<AnrErrorState> checkProcessError = ProcessAnrErrorMonitor.this.checkProcessError(this.mAm);
                if (this.mFirstCheck) {
                    BLog.w(ProcessAnrErrorMonitor.LOG_TAG, "Starting process monitor checks for process '%s'", ProcessAnrErrorMonitor.this.mProcessName);
                    this.mFirstCheck = false;
                    ProcessAnrErrorMonitor.this.updateStateAndMaybeCallListener(StateChangeReason.MONITOR_STARTED, this.mListener);
                }
                AnrErrorState anrErrorState = null;
                if (!checkProcessError.isEmpty()) {
                    AnrErrorState anrErrorState2 = (AnrErrorState) Assertions.assumeNotNull(checkProcessError.getFirst());
                    if (ProcessAnrErrorMonitor.this.mProcessName.equals(anrErrorState2.mProcessName)) {
                        anrErrorState = anrErrorState2;
                    }
                }
                if (anrErrorState != null && !anrCheckState.mAnrConfirmed) {
                    anrCheckState.mAnrConfirmed = true;
                    anrCheckState.mCount = 0;
                    BLog.w(ProcessAnrErrorMonitor.LOG_TAG, "ANR detected Short msg: %s Tag: %s", anrErrorState.mErrorMsg, anrErrorState.mTag);
                    ProcessAnrErrorMonitor.this.updateStateAndMaybeCallListener(StateChangeReason.ERROR_DETECTED, this.mListener, anrErrorState.mErrorMsg, anrErrorState.mTag);
                } else if (anrErrorState != null || !anrCheckState.mAnrConfirmed) {
                    if (anrErrorState != null || anrCheckState.mAnrConfirmed) {
                        anrCheckState.mCount++;
                        if (ProcessAnrErrorMonitor.this.mMaxNumberOfChecksAfterError > 0 && anrCheckState.mCount >= ProcessAnrErrorMonitor.this.mMaxNumberOfChecksAfterError) {
                            ProcessAnrErrorMonitor.this.updateStateAndMaybeCallListener(StateChangeReason.MAX_NUMBER_AFTER_ERROR, this.mListener);
                            BLog.w(ProcessAnrErrorMonitor.LOG_TAG, "Stopping checks for '%s' because of MAX_NUMBER_AFTER_ERROR", ProcessAnrErrorMonitor.this.mProcessName);
                        }
                    } else {
                        anrCheckState.mCount++;
                        if (ProcessAnrErrorMonitor.this.mMaxNumberOfChecksBeforeError > 0 && anrCheckState.mCount >= ProcessAnrErrorMonitor.this.mMaxNumberOfChecksBeforeError) {
                            ProcessAnrErrorMonitor.this.updateStateAndMaybeCallListener(StateChangeReason.MAX_NUMBER_BEFORE_ERROR, this.mListener);
                            BLog.w(ProcessAnrErrorMonitor.LOG_TAG, "Stopping checks for '%s' because of MAX_NUMBER_BEFORE_ERROR", ProcessAnrErrorMonitor.this.mProcessName);
                        }
                    }
                    z = false;
                } else {
                    BLog.w(ProcessAnrErrorMonitor.LOG_TAG, "On error cleared");
                    ProcessAnrErrorMonitor.this.updateStateAndMaybeCallListener(StateChangeReason.ERROR_CLEARED, this.mListener);
                    if (ProcessAnrErrorMonitor.this.mContinuousMonitoring) {
                        anrCheckState.mAnrConfirmed = false;
                        anrCheckState.mCount = 0;
                    }
                    z = ProcessAnrErrorMonitor.this.mContinuousMonitoring;
                }
                if (!checkProcessError.isEmpty()) {
                    maybeLogAnrStateFromOtherProcesses(checkProcessError, ProcessAnrErrorMonitor.this.mProcessName);
                }
                maybeCallIterationListener();
                return z;
            } catch (RuntimeException e) {
                if (e.getCause() instanceof DeadObjectException) {
                    ProcessAnrErrorMonitor.this.updateStateAndMaybeCallListener(StateChangeReason.ERROR_QUERYING_ACTIVITY_MANAGER, this.mListener);
                    BLog.e(ProcessAnrErrorMonitor.LOG_TAG, "Stopping checks for '%s' because of ERROR_QUERYING_ACTIVITY_MANAGER", ProcessAnrErrorMonitor.this.mProcessName, e);
                    return false;
                }
                throw e;
            }
        }

        private void maybeLogAnrStateFromOtherProcesses(LinkedList<AnrErrorState> linkedList, String str) {
            if (this.mListener != null) {
                Iterator<AnrErrorState> it = linkedList.iterator();
                while (it.hasNext()) {
                    AnrErrorState next = it.next();
                    if (!str.equals(next.mProcessName)) {
                        BLog.w(ProcessAnrErrorMonitor.LOG_TAG, "Error found in process '%s' different from process being searched '%s'", next.mProcessName, str);
                        if (next.mProcessName != null && !this.mProcessesInAnr.contains(next.mProcessName) && this.mListener.onErrorDetectOnOtherProcess(next.mProcessName, next.mErrorMsg, next.mTag)) {
                            this.mProcessesInAnr.add(next.mProcessName);
                        }
                    }
                }
            }
        }

        private void maybeCallIterationListener() {
            if (this.mListener != null) {
                this.mListener.onCheckPerformed();
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:13:0x001e, code lost:
            if (r14.mListener == null) goto L_0x0020;
         */
        /* JADX WARNING: Removed duplicated region for block: B:18:0x002b A[SYNTHETIC, Splitter:B:18:0x002b] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void monitorLoop() {
            /*
            // Method dump skipped, instructions count: 163
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.acra.anr.processmonitor.ProcessAnrErrorMonitor.MonitorThread.monitorLoop():void");
        }
    }

    public synchronized State getState() {
        return this.mState;
    }

    public void startMonitoring(@Nullable ProcessErrorStateListener processErrorStateListener) {
        startMonitoringAfterDelay(processErrorStateListener, 0);
    }

    public void startMonitoringAfterDelay(@Nullable ProcessErrorStateListener processErrorStateListener, long j) {
        BLog.w(LOG_TAG, "startMonitoring with delay: %d", Long.valueOf(j));
        ActivityManager activityManager = (ActivityManager) this.mContext.getSystemService("activity");
        Assertions.assumeNotNull(activityManager);
        synchronized (this) {
            if (processErrorStateListener == null && j != 0) {
                throw new IllegalArgumentException("Cannot delay and wait for listener at the same time");
            } else if (this.mErrorCheckThread == null || this.mErrorCheckThread.hasListener()) {
                if (this.mState != State.NOT_MONITORING) {
                    this.mErrorCheckThread.stopRequested();
                }
                this.mCurrentMonitorThreadId++;
                this.mErrorCheckThread = new MonitorThread(activityManager, processErrorStateListener, this.mCurrentMonitorThreadId, j);
                if (processErrorStateListener == null) {
                    this.mState = State.NOT_MONITORING;
                } else {
                    this.mState = State.MONITORING_NO_ERROR_DETECTED;
                }
                this.mErrorCheckThread.start();
            } else if (processErrorStateListener != null) {
                this.mErrorCheckThread.setListener(processErrorStateListener);
            } else {
                throw new IllegalArgumentException("Listener cannot be null");
            }
        }
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public MonitorThread startMonitoringForTest(ProcessErrorStateListener processErrorStateListener) {
        ActivityManager activityManager = (ActivityManager) this.mContext.getSystemService("activity");
        Assertions.assumeNotNull(activityManager);
        synchronized (this) {
            if (this.mState != State.NOT_MONITORING) {
                this.mErrorCheckThread.stopRequested();
            }
            this.mCurrentMonitorThreadId++;
            this.mErrorCheckThread = new MonitorThread(activityManager, processErrorStateListener, this.mCurrentMonitorThreadId, 0);
        }
        return this.mErrorCheckThread;
    }

    public void stopMonitoring() {
        synchronized (this) {
            if (this.mState != State.NOT_MONITORING) {
                this.mErrorCheckThread.stopRequested();
            }
        }
    }

    public void pause() {
        synchronized (this) {
            if (this.mState != State.NOT_MONITORING) {
                this.mErrorCheckThread.pauseRequested();
            }
        }
    }

    public void resume() {
        synchronized (this) {
            if (this.mState != State.NOT_MONITORING) {
                this.mErrorCheckThread.resumeRequested();
            }
        }
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void updateStateAndMaybeCallListener(StateChangeReason stateChangeReason, @Nullable ProcessErrorStateListener processErrorStateListener) {
        updateStateAndMaybeCallListener(stateChangeReason, processErrorStateListener, null, null);
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public synchronized void updateStateAndMaybeCallListener(StateChangeReason stateChangeReason, @Nullable ProcessErrorStateListener processErrorStateListener, @Nullable String str, @Nullable String str2) {
        if (this.mErrorCheckThread == null || this.mErrorCheckThread.getMonitorId() == this.mCurrentMonitorThreadId) {
            switch (stateChangeReason) {
                case MONITOR_STARTED:
                    if (processErrorStateListener != null) {
                        processErrorStateListener.onStart();
                        break;
                    }
                    break;
                case ERROR_DETECTED:
                    this.mState = State.MONITORING_ERROR_DETECTED;
                    if (processErrorStateListener != null) {
                        processErrorStateListener.onErrorDetected(str, str2);
                        break;
                    }
                    break;
                case ERROR_CLEARED:
                    if (this.mContinuousMonitoring) {
                        this.mState = State.MONITORING_NO_ERROR_DETECTED;
                    } else {
                        this.mState = State.NOT_MONITORING;
                    }
                    if (processErrorStateListener != null) {
                        processErrorStateListener.onErrorCleared();
                        break;
                    }
                    break;
                case MAX_NUMBER_AFTER_ERROR:
                    this.mState = State.NOT_MONITORING;
                    if (processErrorStateListener != null) {
                        processErrorStateListener.onMaxChecksReachedAfterError();
                        break;
                    }
                    break;
                case MAX_NUMBER_BEFORE_ERROR:
                    this.mState = State.NOT_MONITORING;
                    if (processErrorStateListener != null) {
                        processErrorStateListener.onMaxChecksReachedBeforeError();
                        break;
                    }
                    break;
                case STOP_REQUESTED:
                    this.mState = State.NOT_MONITORING;
                    break;
                case ERROR_QUERYING_ACTIVITY_MANAGER:
                    this.mState = State.NOT_MONITORING;
                    if (processErrorStateListener != null) {
                        processErrorStateListener.onCheckFailed();
                        break;
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Unexpected state change reason: " + stateChangeReason);
            }
        }
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public synchronized void setErrorCheckThread(MonitorThread monitorThread) {
        this.mErrorCheckThread = monitorThread;
    }

    /* access modifiers changed from: package-private */
    @Nullable
    @VisibleForTesting
    public synchronized MonitorThread getErrorCheckThread() {
        return this.mErrorCheckThread;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public LinkedList<AnrErrorState> checkProcessError(ActivityManager activityManager) {
        List<ActivityManager.ProcessErrorStateInfo> processesInErrorState = activityManager.getProcessesInErrorState();
        LinkedList<AnrErrorState> linkedList = new LinkedList<>();
        if (processesInErrorState != null) {
            for (ActivityManager.ProcessErrorStateInfo processErrorStateInfo : processesInErrorState) {
                if (processErrorStateInfo.condition == 2 && processErrorStateInfo.uid == this.mMyUid) {
                    AnrErrorState anrErrorState = new AnrErrorState();
                    anrErrorState.mErrorMsg = processErrorStateInfo.shortMsg;
                    anrErrorState.mTag = processErrorStateInfo.tag;
                    anrErrorState.mProcessName = processErrorStateInfo.processName;
                    if (this.mProcessName.equals(processErrorStateInfo.processName)) {
                        linkedList.addFirst(anrErrorState);
                    } else {
                        linkedList.addLast(anrErrorState);
                    }
                }
            }
        }
        return linkedList;
    }
}
