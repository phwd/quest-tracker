package com.facebook.acra.anr.processmonitor;

import android.app.ActivityManager;
import android.content.Context;
import android.os.DeadObjectException;
import android.os.Process;
import com.facebook.debug.log.BLog;
import com.facebook.errorreporting.appstate.GlobalAppState;
import com.facebook.infer.annotation.Nullsafe;
import com.oculus.fitnesstracker.database.FitnessTrackerMoveContract;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class ProcessAnrErrorMonitor {
    private static final String LOG_TAG = "ProcessAnrErrorMonitor";
    private final Context mContext;
    private final boolean mContinuousMonitoring;
    private long mCurrentMonitorThreadId;
    private MonitorThread mErrorCheckThread;
    private final int mMaxNumberOfChecksAfterError;
    private final int mMaxNumberOfChecksBeforeError;
    final int mMyUid;
    private final int mPollingTime;
    final String mProcessName;
    private int mState$20c36de5;

    /* access modifiers changed from: package-private */
    public enum StateChangeReason {
        MONITOR_STARTED,
        ERROR_CLEARED,
        ERROR_DETECTED,
        MAX_NUMBER_BEFORE_ERROR,
        MAX_NUMBER_AFTER_ERROR,
        STOP_REQUESTED,
        ERROR_QUERYING_ACTIVITY_MANAGER
    }

    /* JADX INFO: Failed to restore enum class, 'enum' modifier removed */
    public static final class State extends Enum<State> {
        private static final /* synthetic */ int[] $VALUES$7a5547e0 = {NOT_MONITORING$20c36de5, MONITORING_NO_ERROR_DETECTED$20c36de5, MONITORING_ERROR_DETECTED$20c36de5};
        public static final int MONITORING_ERROR_DETECTED$20c36de5 = 3;
        public static final int MONITORING_NO_ERROR_DETECTED$20c36de5 = 2;
        public static final int NOT_MONITORING$20c36de5 = 1;

        public static int[] values$3745c61() {
            return (int[]) $VALUES$7a5547e0.clone();
        }
    }

    /* access modifiers changed from: package-private */
    public static class AnrErrorState {
        String mErrorMsg;
        String mProcessName;
        String mTag;

        AnrErrorState() {
        }
    }

    public ProcessAnrErrorMonitor(Context context, String str, int i, int i2) {
        this(context, str, false, 500, false, 20, 100);
    }

    public ProcessAnrErrorMonitor(Context context, String str, boolean z, int i, boolean z2, int i2, int i3) {
        this.mContext = context;
        this.mProcessName = str;
        this.mState$20c36de5 = State.NOT_MONITORING$20c36de5;
        this.mPollingTime = i;
        this.mContinuousMonitoring = z2;
        this.mMaxNumberOfChecksBeforeError = i2;
        this.mMaxNumberOfChecksAfterError = i3;
        this.mMyUid = Process.myUid();
    }

    /* access modifiers changed from: package-private */
    public static class AnrCheckState {
        boolean mAnrConfirmed;
        int mCount;

        AnrCheckState() {
        }
    }

    public class MonitorThread extends Thread {
        private final ActivityManager mAm;
        private final long mDelay;
        private boolean mFirstCheck;
        final long mId;
        private volatile ProcessErrorStateListener mListener;
        private final Object mMonitorLock;
        private boolean mPauseRequested;
        private final Set<String> mProcessesInAnr;
        private boolean mStopRequested;

        /* synthetic */ MonitorThread(ProcessAnrErrorMonitor processAnrErrorMonitor, ActivityManager activityManager, ProcessErrorStateListener processErrorStateListener, long j, long j2, byte b) {
            this(activityManager, processErrorStateListener, j, j2);
        }

        private MonitorThread(ActivityManager activityManager, ProcessErrorStateListener processErrorStateListener, long j, long j2) {
            super("ProcessAnrErrorMonitorThread:" + GlobalAppState.getSessionId());
            this.mMonitorLock = new Object();
            this.mProcessesInAnr = new HashSet();
            this.mAm = activityManager;
            this.mListener = processErrorStateListener;
            this.mId = j;
            this.mDelay = j2;
            this.mFirstCheck = true;
        }

        /* access modifiers changed from: package-private */
        public final boolean hasListener() {
            return this.mListener != null;
        }

        /* access modifiers changed from: package-private */
        public final void setListener(ProcessErrorStateListener processErrorStateListener) {
            synchronized (this.mMonitorLock) {
                this.mListener = processErrorStateListener;
                this.mMonitorLock.notifyAll();
            }
        }

        private boolean checkIteration(AnrCheckState anrCheckState) {
            boolean z = true;
            try {
                ProcessAnrErrorMonitor processAnrErrorMonitor = ProcessAnrErrorMonitor.this;
                List<ActivityManager.ProcessErrorStateInfo> processesInErrorState = this.mAm.getProcessesInErrorState();
                LinkedList<AnrErrorState> linkedList = new LinkedList<>();
                if (processesInErrorState != null) {
                    for (ActivityManager.ProcessErrorStateInfo processErrorStateInfo : processesInErrorState) {
                        if (processErrorStateInfo.condition == 2 && processErrorStateInfo.uid == processAnrErrorMonitor.mMyUid) {
                            AnrErrorState anrErrorState = new AnrErrorState();
                            anrErrorState.mErrorMsg = processErrorStateInfo.shortMsg;
                            anrErrorState.mTag = processErrorStateInfo.tag;
                            anrErrorState.mProcessName = processErrorStateInfo.processName;
                            if (processAnrErrorMonitor.mProcessName.equals(processErrorStateInfo.processName)) {
                                linkedList.addFirst(anrErrorState);
                            } else {
                                linkedList.addLast(anrErrorState);
                            }
                        }
                    }
                }
                if (this.mFirstCheck) {
                    BLog.w(ProcessAnrErrorMonitor.LOG_TAG, "Starting process monitor checks for process '%s'", ProcessAnrErrorMonitor.this.mProcessName);
                    this.mFirstCheck = false;
                    ProcessAnrErrorMonitor.this.updateStateAndMaybeCallListener(StateChangeReason.MONITOR_STARTED, this.mListener);
                }
                AnrErrorState anrErrorState2 = null;
                if (!linkedList.isEmpty()) {
                    AnrErrorState first = linkedList.getFirst();
                    if (ProcessAnrErrorMonitor.this.mProcessName.equals(first.mProcessName)) {
                        anrErrorState2 = first;
                    }
                }
                if (anrErrorState2 != null && !anrCheckState.mAnrConfirmed) {
                    anrCheckState.mAnrConfirmed = true;
                    anrCheckState.mCount = 0;
                    BLog.w(ProcessAnrErrorMonitor.LOG_TAG, "ANR detected Short msg: %s Tag: %s", anrErrorState2.mErrorMsg, anrErrorState2.mTag);
                    ProcessAnrErrorMonitor.this.updateStateAndMaybeCallListener(StateChangeReason.ERROR_DETECTED, this.mListener, anrErrorState2.mErrorMsg, anrErrorState2.mTag);
                } else if (anrErrorState2 != null || !anrCheckState.mAnrConfirmed) {
                    if (anrErrorState2 != null || anrCheckState.mAnrConfirmed) {
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
                if (!linkedList.isEmpty()) {
                    maybeLogAnrStateFromOtherProcesses(linkedList, ProcessAnrErrorMonitor.this.mProcessName);
                }
                if (this.mListener != null) {
                    ProcessErrorStateListener processErrorStateListener = this.mListener;
                }
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

        /* JADX WARNING: Code restructure failed: missing block: B:13:0x001e, code lost:
            if (r14.mListener == null) goto L_0x0020;
         */
        /* JADX WARNING: Removed duplicated region for block: B:18:0x002b A[SYNTHETIC, Splitter:B:18:0x002b] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void run() {
            /*
            // Method dump skipped, instructions count: 162
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.acra.anr.processmonitor.ProcessAnrErrorMonitor.MonitorThread.run():void");
        }

        static /* synthetic */ void access$600(MonitorThread monitorThread) {
            synchronized (monitorThread.mMonitorLock) {
                monitorThread.mStopRequested = true;
                monitorThread.mMonitorLock.notifyAll();
            }
        }
    }

    public final synchronized int getState$5e679686() {
        return this.mState$20c36de5;
    }

    public final void startMonitoringAfterDelay(ProcessErrorStateListener processErrorStateListener, long j) {
        BLog.w(LOG_TAG, "startMonitoring with delay: %d", Long.valueOf(j));
        ActivityManager activityManager = (ActivityManager) this.mContext.getSystemService(FitnessTrackerMoveContract.Session.ACTIVITY);
        synchronized (this) {
            if (processErrorStateListener == null && j != 0) {
                throw new IllegalArgumentException("Cannot delay and wait for listener at the same time");
            } else if (this.mErrorCheckThread == null || this.mErrorCheckThread.hasListener()) {
                if (this.mState$20c36de5 != State.NOT_MONITORING$20c36de5) {
                    MonitorThread.access$600(this.mErrorCheckThread);
                }
                this.mCurrentMonitorThreadId++;
                this.mErrorCheckThread = new MonitorThread(this, activityManager, processErrorStateListener, this.mCurrentMonitorThreadId, j, (byte) 0);
                if (processErrorStateListener == null) {
                    this.mState$20c36de5 = State.NOT_MONITORING$20c36de5;
                } else {
                    this.mState$20c36de5 = State.MONITORING_NO_ERROR_DETECTED$20c36de5;
                }
                this.mErrorCheckThread.start();
            } else if (processErrorStateListener != null) {
                this.mErrorCheckThread.setListener(processErrorStateListener);
            } else {
                throw new IllegalArgumentException("Listener cannot be null");
            }
        }
    }

    public final void stopMonitoring() {
        synchronized (this) {
            if (this.mState$20c36de5 != State.NOT_MONITORING$20c36de5) {
                MonitorThread.access$600(this.mErrorCheckThread);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void updateStateAndMaybeCallListener(StateChangeReason stateChangeReason, ProcessErrorStateListener processErrorStateListener) {
        updateStateAndMaybeCallListener(stateChangeReason, processErrorStateListener, null, null);
    }

    /* access modifiers changed from: package-private */
    public final synchronized void updateStateAndMaybeCallListener(StateChangeReason stateChangeReason, ProcessErrorStateListener processErrorStateListener, String str, String str2) {
        if (this.mErrorCheckThread == null || this.mErrorCheckThread.mId == this.mCurrentMonitorThreadId) {
            switch (stateChangeReason) {
                case MONITOR_STARTED:
                    if (processErrorStateListener != null) {
                        processErrorStateListener.onStart();
                        return;
                    }
                    break;
                case ERROR_DETECTED:
                    this.mState$20c36de5 = State.MONITORING_ERROR_DETECTED$20c36de5;
                    if (processErrorStateListener != null) {
                        processErrorStateListener.onErrorDetected(str, str2);
                        return;
                    }
                    break;
                case ERROR_CLEARED:
                    if (this.mContinuousMonitoring) {
                        this.mState$20c36de5 = State.MONITORING_NO_ERROR_DETECTED$20c36de5;
                    } else {
                        this.mState$20c36de5 = State.NOT_MONITORING$20c36de5;
                    }
                    if (processErrorStateListener != null) {
                        processErrorStateListener.onErrorCleared();
                        return;
                    }
                    break;
                case MAX_NUMBER_AFTER_ERROR:
                    this.mState$20c36de5 = State.NOT_MONITORING$20c36de5;
                    if (processErrorStateListener != null) {
                        processErrorStateListener.onMaxChecksReachedAfterError();
                        return;
                    }
                    break;
                case MAX_NUMBER_BEFORE_ERROR:
                    this.mState$20c36de5 = State.NOT_MONITORING$20c36de5;
                    if (processErrorStateListener != null) {
                        processErrorStateListener.onMaxChecksReachedBeforeError();
                        return;
                    }
                    break;
                case STOP_REQUESTED:
                    this.mState$20c36de5 = State.NOT_MONITORING$20c36de5;
                    return;
                case ERROR_QUERYING_ACTIVITY_MANAGER:
                    this.mState$20c36de5 = State.NOT_MONITORING$20c36de5;
                    if (processErrorStateListener != null) {
                        processErrorStateListener.onCheckFailed();
                        return;
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Unexpected state change reason: " + stateChangeReason);
            }
        }
    }
}
