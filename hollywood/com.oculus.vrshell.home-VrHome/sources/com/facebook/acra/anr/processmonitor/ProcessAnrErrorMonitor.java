package com.facebook.acra.anr.processmonitor;

import android.app.ActivityManager;
import android.content.Context;
import android.os.DeadObjectException;
import android.os.Process;
import android.os.SystemClock;
import com.facebook.debug.log.BLog;
import com.facebook.errorreporting.appstate.GlobalAppState;
import com.facebook.infer.annotation.Assertions;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

public class ProcessAnrErrorMonitor {
    private static final String LOG_TAG = ProcessAnrErrorMonitor.class.getSimpleName();
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

    public ProcessAnrErrorMonitor(Context context, String processName, int maxNumberOfChecksBeforeError, int maxNumberOfChecksAfterError) {
        this(context, processName, false, 500, false, maxNumberOfChecksBeforeError, maxNumberOfChecksAfterError);
    }

    public ProcessAnrErrorMonitor(Context context, String processName, boolean startThread, int checkIntervalTimeMs, boolean continuousMonitoring, int maxNumberOfChecksBeforeError, int maxNumberOfChecksAfterError) {
        this.mContext = context;
        this.mProcessName = processName;
        this.mState = State.NOT_MONITORING;
        this.mPollingTime = checkIntervalTimeMs;
        this.mContinuousMonitoring = continuousMonitoring;
        this.mMaxNumberOfChecksBeforeError = maxNumberOfChecksBeforeError;
        this.mMaxNumberOfChecksAfterError = maxNumberOfChecksAfterError;
        this.mMyUid = Process.myUid();
        if (startThread) {
            startMonitoring(null);
        }
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
        private final long mId;
        @Nullable
        private volatile ProcessErrorStateListener mListener;
        private final Object mMonitorLock;
        @GuardedBy("mMonitorLock")
        private boolean mPauseRequested;
        private final Set<String> mProcessesInAnr;
        @GuardedBy("mMonitorLock")
        private boolean mStopRequested;

        private MonitorThread(ActivityManager am, ProcessErrorStateListener listener, @Nullable long id, long delay) {
            super("ProcessAnrErrorMonitorThread:" + GlobalAppState.getSessionId());
            this.mMonitorLock = new Object();
            this.mProcessesInAnr = new HashSet();
            this.mAm = am;
            this.mListener = listener;
            this.mId = id;
            this.mDelay = delay;
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

        /* access modifiers changed from: package-private */
        public boolean hasListener() {
            return this.mListener != null;
        }

        /* access modifiers changed from: package-private */
        public void setListener(ProcessErrorStateListener listener) {
            synchronized (this.mMonitorLock) {
                this.mListener = listener;
                this.mMonitorLock.notifyAll();
            }
        }

        /* access modifiers changed from: package-private */
        public boolean checkIteration(AnrCheckState anrCheckState) {
            try {
                LinkedList<AnrErrorState> errorList = ProcessAnrErrorMonitor.this.checkProcessError(this.mAm);
                if (this.mFirstCheck) {
                    BLog.w(ProcessAnrErrorMonitor.LOG_TAG, "Starting process monitor checks for process '%s'", ProcessAnrErrorMonitor.this.mProcessName);
                    this.mFirstCheck = false;
                    ProcessAnrErrorMonitor.this.updateStateAndMaybeCallListener(StateChangeReason.MONITOR_STARTED, this.mListener);
                }
                AnrErrorState currentProcessError = null;
                if (!errorList.isEmpty()) {
                    AnrErrorState firstError = (AnrErrorState) Assertions.assumeNotNull(errorList.getFirst());
                    if (ProcessAnrErrorMonitor.this.mProcessName.equals(firstError.mProcessName)) {
                        currentProcessError = firstError;
                    }
                }
                boolean shouldContinue = true;
                if (currentProcessError != null && !anrCheckState.mAnrConfirmed) {
                    anrCheckState.mAnrConfirmed = true;
                    anrCheckState.mCount = 0;
                    BLog.w(ProcessAnrErrorMonitor.LOG_TAG, "ANR detected Short msg: %s Tag: %s", currentProcessError.mErrorMsg, currentProcessError.mTag);
                    ProcessAnrErrorMonitor.this.updateStateAndMaybeCallListener(StateChangeReason.ERROR_DETECTED, this.mListener, currentProcessError.mErrorMsg, currentProcessError.mTag);
                } else if (currentProcessError == null && anrCheckState.mAnrConfirmed) {
                    BLog.w(ProcessAnrErrorMonitor.LOG_TAG, "On error cleared");
                    ProcessAnrErrorMonitor.this.updateStateAndMaybeCallListener(StateChangeReason.ERROR_CLEARED, this.mListener);
                    if (ProcessAnrErrorMonitor.this.mContinuousMonitoring) {
                        anrCheckState.mAnrConfirmed = false;
                        anrCheckState.mCount = 0;
                    }
                    shouldContinue = ProcessAnrErrorMonitor.this.mContinuousMonitoring;
                } else if (currentProcessError != null || anrCheckState.mAnrConfirmed) {
                    anrCheckState.mCount++;
                    if (ProcessAnrErrorMonitor.this.mMaxNumberOfChecksAfterError > 0 && anrCheckState.mCount >= ProcessAnrErrorMonitor.this.mMaxNumberOfChecksAfterError) {
                        ProcessAnrErrorMonitor.this.updateStateAndMaybeCallListener(StateChangeReason.MAX_NUMBER_AFTER_ERROR, this.mListener);
                        shouldContinue = false;
                        BLog.w(ProcessAnrErrorMonitor.LOG_TAG, "Stopping checks for '%s' because of MAX_NUMBER_AFTER_ERROR", ProcessAnrErrorMonitor.this.mProcessName);
                    }
                } else {
                    anrCheckState.mCount++;
                    if (ProcessAnrErrorMonitor.this.mMaxNumberOfChecksBeforeError > 0 && anrCheckState.mCount >= ProcessAnrErrorMonitor.this.mMaxNumberOfChecksBeforeError) {
                        ProcessAnrErrorMonitor.this.updateStateAndMaybeCallListener(StateChangeReason.MAX_NUMBER_BEFORE_ERROR, this.mListener);
                        shouldContinue = false;
                        BLog.w(ProcessAnrErrorMonitor.LOG_TAG, "Stopping checks for '%s' because of MAX_NUMBER_BEFORE_ERROR", ProcessAnrErrorMonitor.this.mProcessName);
                    }
                }
                if (!errorList.isEmpty()) {
                    maybeLogAnrStateFromOtherProcesses(errorList, ProcessAnrErrorMonitor.this.mProcessName);
                }
                maybeCallIterationListener();
                return shouldContinue;
            } catch (RuntimeException e) {
                if (e.getCause() instanceof DeadObjectException) {
                    ProcessAnrErrorMonitor.this.updateStateAndMaybeCallListener(StateChangeReason.ERROR_QUERYING_ACTIVITY_MANAGER, this.mListener);
                    BLog.e(ProcessAnrErrorMonitor.LOG_TAG, "Stopping checks for '%s' because of ERROR_QUERYING_ACTIVITY_MANAGER", ProcessAnrErrorMonitor.this.mProcessName, e);
                    return false;
                }
                throw e;
            }
        }

        private void maybeLogAnrStateFromOtherProcesses(LinkedList<AnrErrorState> errorList, String processNameToIgnore) {
            if (this.mListener != null) {
                Iterator<AnrErrorState> it = errorList.iterator();
                while (it.hasNext()) {
                    AnrErrorState errorState = it.next();
                    if (!processNameToIgnore.equals(errorState.mProcessName)) {
                        BLog.w(ProcessAnrErrorMonitor.LOG_TAG, "Error found in process '%s' different from process being searched '%s'", errorState.mProcessName, processNameToIgnore);
                        if (errorState.mProcessName != null && !this.mProcessesInAnr.contains(errorState.mProcessName) && this.mListener.onErrorDetectOnOtherProcess(errorState.mProcessName, errorState.mErrorMsg, errorState.mTag)) {
                            this.mProcessesInAnr.add(errorState.mProcessName);
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

        private void monitorLoop() {
            boolean stopRequested;
            boolean stopRequested2;
            if (this.mDelay > 0 || this.mListener == null) {
                long delay = this.mDelay;
                synchronized (this.mMonitorLock) {
                    boolean shouldWait = delay > 0 ? !this.mStopRequested : this.mListener == null;
                    stopRequested2 = this.mStopRequested;
                    long startWait = SystemClock.uptimeMillis();
                    while (shouldWait) {
                        try {
                            this.mMonitorLock.wait(delay);
                        } catch (InterruptedException e) {
                        }
                        shouldWait = delay > 0 ? !this.mStopRequested : this.mListener == null;
                        stopRequested2 = this.mStopRequested;
                        if (shouldWait && delay > 0) {
                            delay = this.mDelay - (SystemClock.uptimeMillis() - startWait);
                            if (delay < 1) {
                                break;
                            }
                        }
                    }
                }
                if (stopRequested2) {
                    return;
                }
            }
            AnrCheckState state = new AnrCheckState();
            state.mAnrConfirmed = false;
            state.mCount = 0;
            while (checkIteration(state)) {
                synchronized (this.mMonitorLock) {
                    stopRequested = this.mStopRequested;
                    if (!this.mStopRequested) {
                        int waitTime = ProcessAnrErrorMonitor.this.mPollingTime;
                        do {
                            if (this.mPauseRequested) {
                                waitTime = 0;
                            }
                            try {
                                this.mMonitorLock.wait((long) waitTime);
                            } catch (InterruptedException e2) {
                            }
                            if (!this.mPauseRequested) {
                                break;
                            }
                        } while (!this.mStopRequested);
                        stopRequested = this.mStopRequested;
                    }
                }
                if (stopRequested) {
                    ProcessAnrErrorMonitor.this.updateStateAndMaybeCallListener(StateChangeReason.STOP_REQUESTED, this.mListener);
                    return;
                }
            }
        }
    }

    public synchronized State getState() {
        return this.mState;
    }

    public void startMonitoring(@Nullable ProcessErrorStateListener listener) {
        startMonitoringAfterDelay(listener, 0);
    }

    public void startMonitoringAfterDelay(@Nullable ProcessErrorStateListener listener, long delay) {
        BLog.w(LOG_TAG, "startMonitoring with delay: %d", Long.valueOf(delay));
        ActivityManager am = (ActivityManager) this.mContext.getSystemService("activity");
        Assertions.assumeNotNull(am);
        synchronized (this) {
            if (listener == null && delay != 0) {
                throw new IllegalArgumentException("Cannot delay and wait for listener at the same time");
            } else if (this.mErrorCheckThread == null || this.mErrorCheckThread.hasListener()) {
                if (this.mState != State.NOT_MONITORING) {
                    this.mErrorCheckThread.stopRequested();
                }
                this.mCurrentMonitorThreadId++;
                this.mErrorCheckThread = new MonitorThread(am, listener, this.mCurrentMonitorThreadId, delay);
                if (listener == null) {
                    this.mState = State.NOT_MONITORING;
                } else {
                    this.mState = State.MONITORING_NO_ERROR_DETECTED;
                }
                this.mErrorCheckThread.start();
            } else if (listener == null) {
                throw new IllegalArgumentException("Listener cannot be null");
            } else {
                this.mErrorCheckThread.setListener(listener);
            }
        }
    }

    public void stopMonitoring() {
        synchronized (this) {
            if (this.mState != State.NOT_MONITORING) {
                this.mErrorCheckThread.stopRequested();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void updateStateAndMaybeCallListener(StateChangeReason reason, @Nullable ProcessErrorStateListener listener) {
        updateStateAndMaybeCallListener(reason, listener, null, null);
    }

    /* access modifiers changed from: package-private */
    public synchronized void updateStateAndMaybeCallListener(StateChangeReason reason, @Nullable ProcessErrorStateListener listener, @Nullable String errorMsg, @Nullable String tag) {
        if (this.mErrorCheckThread == null || this.mErrorCheckThread.getMonitorId() == this.mCurrentMonitorThreadId) {
            switch (reason) {
                case MONITOR_STARTED:
                    if (listener != null) {
                        listener.onStart();
                        break;
                    }
                    break;
                case ERROR_DETECTED:
                    this.mState = State.MONITORING_ERROR_DETECTED;
                    if (listener != null) {
                        listener.onErrorDetected(errorMsg, tag);
                        break;
                    }
                    break;
                case ERROR_CLEARED:
                    if (this.mContinuousMonitoring) {
                        this.mState = State.MONITORING_NO_ERROR_DETECTED;
                    } else {
                        this.mState = State.NOT_MONITORING;
                    }
                    if (listener != null) {
                        listener.onErrorCleared();
                        break;
                    }
                    break;
                case MAX_NUMBER_AFTER_ERROR:
                    this.mState = State.NOT_MONITORING;
                    if (listener != null) {
                        listener.onMaxChecksReachedAfterError();
                        break;
                    }
                    break;
                case MAX_NUMBER_BEFORE_ERROR:
                    this.mState = State.NOT_MONITORING;
                    if (listener != null) {
                        listener.onMaxChecksReachedBeforeError();
                        break;
                    }
                    break;
                case STOP_REQUESTED:
                    this.mState = State.NOT_MONITORING;
                    break;
                case ERROR_QUERYING_ACTIVITY_MANAGER:
                    this.mState = State.NOT_MONITORING;
                    if (listener != null) {
                        listener.onCheckFailed();
                        break;
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Unexpected state change reason: " + reason);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public LinkedList<AnrErrorState> checkProcessError(ActivityManager am) {
        List<ActivityManager.ProcessErrorStateInfo> pInfoList = am.getProcessesInErrorState();
        LinkedList<AnrErrorState> errorStatesList = new LinkedList<>();
        if (pInfoList != null) {
            for (ActivityManager.ProcessErrorStateInfo pInfo : pInfoList) {
                if (pInfo.condition == 2 && pInfo.uid == this.mMyUid) {
                    AnrErrorState errorState = new AnrErrorState();
                    errorState.mErrorMsg = pInfo.shortMsg;
                    errorState.mTag = pInfo.tag;
                    errorState.mProcessName = pInfo.processName;
                    if (this.mProcessName.equals(pInfo.processName)) {
                        errorStatesList.addFirst(errorState);
                    } else {
                        errorStatesList.addLast(errorState);
                    }
                }
            }
        }
        return errorStatesList;
    }
}
