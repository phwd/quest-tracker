package com.facebook.acra.anr.processmonitor;

import X.AnonymousClass06;
import X.Mu;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Process;
import androidx.annotation.VisibleForTesting;
import com.facebook.acra.AppComponentStats;
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
    public static final String LOG_TAG = "ProcessAnrErrorMonitor";
    public static final int UNLIMITED_NUMBER_OF_CHECKS = 0;
    public final Context mContext;
    public final boolean mContinuousMonitoring;
    @GuardedBy("this")
    public long mCurrentMonitorThreadId;
    @GuardedBy("this")
    @Nullable
    public MonitorThread mErrorCheckThread;
    public final int mMaxNumberOfChecksAfterError;
    public final int mMaxNumberOfChecksBeforeError;
    public final int mMyUid;
    public final int mPollingTime;
    public final String mProcessName;
    @GuardedBy("this")
    public State mState;

    @VisibleForTesting
    public static class AnrCheckState {
        public boolean mAnrConfirmed;
        public int mCount;
    }

    @VisibleForTesting
    public static class AnrErrorState {
        @Nullable
        public String mErrorMsg;
        @Nullable
        public String mProcessName;
        @Nullable
        public String mTag;
    }

    @VisibleForTesting
    public class MonitorThread extends Thread {
        public final ActivityManager mAm;
        public final long mDelay;
        public boolean mFirstCheck;
        public final long mId;
        @Nullable
        public volatile ProcessErrorStateListener mListener;
        public final Object mMonitorLock;
        @GuardedBy("mMonitorLock")
        public boolean mPauseRequested;
        public final Set<String> mProcessesInAnr;
        @GuardedBy("mMonitorLock")
        public boolean mStopRequested;

        /* JADX WARNING: Removed duplicated region for block: B:14:0x006a  */
        @androidx.annotation.VisibleForTesting
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean checkIteration(com.facebook.acra.anr.processmonitor.ProcessAnrErrorMonitor.AnrCheckState r9) {
            /*
            // Method dump skipped, instructions count: 251
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.acra.anr.processmonitor.ProcessAnrErrorMonitor.MonitorThread.checkIteration(com.facebook.acra.anr.processmonitor.ProcessAnrErrorMonitor$AnrCheckState):boolean");
        }

        private void maybeCallIterationListener() {
            if (this.mListener != null) {
                this.mListener.onCheckPerformed();
            }
        }

        private void maybeLogAnrStateFromOtherProcesses(LinkedList<AnrErrorState> linkedList, String str) {
            if (this.mListener != null) {
                Iterator<AnrErrorState> it = linkedList.iterator();
                while (it.hasNext()) {
                    AnrErrorState next = it.next();
                    String str2 = next.mProcessName;
                    if (!str.equals(str2)) {
                        Mu.A06(ProcessAnrErrorMonitor.LOG_TAG, "Error found in process '%s' different from process being searched '%s'", str2, str);
                        String str3 = next.mProcessName;
                        if (str3 != null && !this.mProcessesInAnr.contains(str3) && this.mListener.onErrorDetectOnOtherProcess(next.mProcessName, next.mErrorMsg, next.mTag)) {
                            this.mProcessesInAnr.add(next.mProcessName);
                        }
                    }
                }
            }
        }

        /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
            jadx.core.utils.exceptions.JadxRuntimeException: CFG modification limit reached, blocks count: 173
            	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.processBlocksTree(BlockProcessor.java:72)
            	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.visit(BlockProcessor.java:46)
            */
        private void monitorLoop() {
            /*
            // Method dump skipped, instructions count: 158
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.acra.anr.processmonitor.ProcessAnrErrorMonitor.MonitorThread.monitorLoop():void");
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

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void stopRequested() {
            synchronized (this.mMonitorLock) {
                this.mStopRequested = true;
                this.mMonitorLock.notifyAll();
            }
        }

        public boolean hasListener() {
            if (this.mListener != null) {
                return true;
            }
            return false;
        }

        public void setListener(ProcessErrorStateListener processErrorStateListener) {
            synchronized (this.mMonitorLock) {
                this.mListener = processErrorStateListener;
                this.mMonitorLock.notifyAll();
            }
        }

        public long getMonitorId() {
            return this.mId;
        }

        public void run() {
            monitorLoop();
        }

        public MonitorThread(ActivityManager activityManager, @Nullable ProcessErrorStateListener processErrorStateListener, long j, long j2) {
            super(AnonymousClass06.A04("ProcessAnrErrorMonitorThread:", "unknown"));
            this.mMonitorLock = new Object();
            this.mProcessesInAnr = new HashSet();
            this.mAm = activityManager;
            this.mListener = processErrorStateListener;
            this.mId = j;
            this.mDelay = j2;
            this.mFirstCheck = true;
        }

        public /* synthetic */ MonitorThread(ProcessAnrErrorMonitor processAnrErrorMonitor, ActivityManager activityManager, ProcessErrorStateListener processErrorStateListener, long j, long j2, AnonymousClass1 r8) {
            this(activityManager, processErrorStateListener, j, j2);
        }
    }

    public enum State {
        NOT_MONITORING,
        MONITORING_NO_ERROR_DETECTED,
        MONITORING_ERROR_DETECTED
    }

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

    @Nullable
    @VisibleForTesting
    public synchronized MonitorThread getErrorCheckThread() {
        return this.mErrorCheckThread;
    }

    public synchronized State getState() {
        return this.mState;
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

    @VisibleForTesting
    public synchronized void setErrorCheckThread(MonitorThread monitorThread) {
        this.mErrorCheckThread = monitorThread;
    }

    @VisibleForTesting
    public MonitorThread startMonitoringForTest(ProcessErrorStateListener processErrorStateListener) {
        ActivityManager activityManager = (ActivityManager) this.mContext.getSystemService(AppComponentStats.TAG_ACTIVITY);
        synchronized (this) {
            if (this.mState != State.NOT_MONITORING) {
                this.mErrorCheckThread.stopRequested();
            }
            long j = this.mCurrentMonitorThreadId + 1;
            this.mCurrentMonitorThreadId = j;
            this.mErrorCheckThread = new MonitorThread(activityManager, processErrorStateListener, j, 0);
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

    /* renamed from: com.facebook.acra.anr.processmonitor.ProcessAnrErrorMonitor$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$facebook$acra$anr$processmonitor$ProcessAnrErrorMonitor$StateChangeReason;

        /* JADX WARNING: Can't wrap try/catch for region: R(16:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|16) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0036 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x003f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0024 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002d */
        static {
            /*
                com.facebook.acra.anr.processmonitor.ProcessAnrErrorMonitor$StateChangeReason[] r0 = com.facebook.acra.anr.processmonitor.ProcessAnrErrorMonitor.StateChangeReason.values()
                int r0 = r0.length
                int[] r2 = new int[r0]
                com.facebook.acra.anr.processmonitor.ProcessAnrErrorMonitor.AnonymousClass1.$SwitchMap$com$facebook$acra$anr$processmonitor$ProcessAnrErrorMonitor$StateChangeReason = r2
                com.facebook.acra.anr.processmonitor.ProcessAnrErrorMonitor$StateChangeReason r0 = com.facebook.acra.anr.processmonitor.ProcessAnrErrorMonitor.StateChangeReason.MONITOR_STARTED     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0 = 1
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                com.facebook.acra.anr.processmonitor.ProcessAnrErrorMonitor$StateChangeReason r0 = com.facebook.acra.anr.processmonitor.ProcessAnrErrorMonitor.StateChangeReason.ERROR_DETECTED     // Catch:{ NoSuchFieldError -> 0x001b }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x001b }
                r0 = 2
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x001b }
            L_0x001b:
                com.facebook.acra.anr.processmonitor.ProcessAnrErrorMonitor$StateChangeReason r0 = com.facebook.acra.anr.processmonitor.ProcessAnrErrorMonitor.StateChangeReason.ERROR_CLEARED     // Catch:{ NoSuchFieldError -> 0x0024 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0024 }
                r0 = 3
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0024 }
            L_0x0024:
                com.facebook.acra.anr.processmonitor.ProcessAnrErrorMonitor$StateChangeReason r0 = com.facebook.acra.anr.processmonitor.ProcessAnrErrorMonitor.StateChangeReason.MAX_NUMBER_AFTER_ERROR     // Catch:{ NoSuchFieldError -> 0x002d }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x002d }
                r0 = 4
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x002d }
            L_0x002d:
                com.facebook.acra.anr.processmonitor.ProcessAnrErrorMonitor$StateChangeReason r0 = com.facebook.acra.anr.processmonitor.ProcessAnrErrorMonitor.StateChangeReason.MAX_NUMBER_BEFORE_ERROR     // Catch:{ NoSuchFieldError -> 0x0036 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0036 }
                r0 = 5
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0036 }
            L_0x0036:
                com.facebook.acra.anr.processmonitor.ProcessAnrErrorMonitor$StateChangeReason r0 = com.facebook.acra.anr.processmonitor.ProcessAnrErrorMonitor.StateChangeReason.STOP_REQUESTED     // Catch:{ NoSuchFieldError -> 0x003f }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x003f }
                r0 = 6
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x003f }
            L_0x003f:
                com.facebook.acra.anr.processmonitor.ProcessAnrErrorMonitor$StateChangeReason r0 = com.facebook.acra.anr.processmonitor.ProcessAnrErrorMonitor.StateChangeReason.ERROR_QUERYING_ACTIVITY_MANAGER     // Catch:{ NoSuchFieldError -> 0x0048 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0048 }
                r0 = 7
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0048 }
            L_0x0048:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.acra.anr.processmonitor.ProcessAnrErrorMonitor.AnonymousClass1.<clinit>():void");
        }
    }

    public void startMonitoring(@Nullable ProcessErrorStateListener processErrorStateListener) {
        startMonitoringAfterDelay(processErrorStateListener, 0);
    }

    public void startMonitoringAfterDelay(@Nullable ProcessErrorStateListener processErrorStateListener, long j) {
        Mu.A06(LOG_TAG, "startMonitoring with delay: %d", Long.valueOf(j));
        ActivityManager activityManager = (ActivityManager) this.mContext.getSystemService(AppComponentStats.TAG_ACTIVITY);
        synchronized (this) {
            if (processErrorStateListener != null || j == 0) {
                MonitorThread monitorThread = this.mErrorCheckThread;
                if (monitorThread == null || monitorThread.hasListener()) {
                    if (this.mState != State.NOT_MONITORING) {
                        this.mErrorCheckThread.stopRequested();
                    }
                    long j2 = this.mCurrentMonitorThreadId + 1;
                    this.mCurrentMonitorThreadId = j2;
                    MonitorThread monitorThread2 = new MonitorThread(activityManager, processErrorStateListener, j2, j);
                    this.mErrorCheckThread = monitorThread2;
                    if (processErrorStateListener == null) {
                        this.mState = State.NOT_MONITORING;
                    } else {
                        this.mState = State.MONITORING_NO_ERROR_DETECTED;
                    }
                    monitorThread2.start();
                } else if (processErrorStateListener != null) {
                    this.mErrorCheckThread.setListener(processErrorStateListener);
                } else {
                    throw new IllegalArgumentException("Listener cannot be null");
                }
            } else {
                throw new IllegalArgumentException("Cannot delay and wait for listener at the same time");
            }
        }
    }

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
                    String str = processErrorStateInfo.processName;
                    anrErrorState.mProcessName = str;
                    if (this.mProcessName.equals(str)) {
                        linkedList.addFirst(anrErrorState);
                    } else {
                        linkedList.addLast(anrErrorState);
                    }
                }
            }
        }
        return linkedList;
    }

    public static /* synthetic */ String access$000() {
        return LOG_TAG;
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
            startMonitoringAfterDelay(null, 0);
        }
    }

    @VisibleForTesting
    public void updateStateAndMaybeCallListener(StateChangeReason stateChangeReason, @Nullable ProcessErrorStateListener processErrorStateListener) {
        updateStateAndMaybeCallListener(stateChangeReason, processErrorStateListener, null, null);
    }

    @VisibleForTesting
    public synchronized void updateStateAndMaybeCallListener(StateChangeReason stateChangeReason, @Nullable ProcessErrorStateListener processErrorStateListener, @Nullable String str, @Nullable String str2) {
        MonitorThread monitorThread = this.mErrorCheckThread;
        if (monitorThread == null || monitorThread.mId == this.mCurrentMonitorThreadId) {
            switch (stateChangeReason.ordinal()) {
                case 0:
                    if (processErrorStateListener != null) {
                        processErrorStateListener.onStart();
                        break;
                    }
                    break;
                case 1:
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
                case 2:
                    this.mState = State.MONITORING_ERROR_DETECTED;
                    if (processErrorStateListener != null) {
                        processErrorStateListener.onErrorDetected(str, str2);
                        break;
                    }
                    break;
                case 3:
                    this.mState = State.NOT_MONITORING;
                    if (processErrorStateListener != null) {
                        processErrorStateListener.onMaxChecksReachedBeforeError();
                        break;
                    }
                    break;
                case 4:
                    this.mState = State.NOT_MONITORING;
                    if (processErrorStateListener != null) {
                        processErrorStateListener.onMaxChecksReachedAfterError();
                        break;
                    }
                    break;
                case 5:
                    this.mState = State.NOT_MONITORING;
                    break;
                case 6:
                    this.mState = State.NOT_MONITORING;
                    if (processErrorStateListener != null) {
                        processErrorStateListener.onCheckFailed();
                        break;
                    }
                    break;
                default:
                    StringBuilder sb = new StringBuilder();
                    sb.append("Unexpected state change reason: ");
                    sb.append(stateChangeReason);
                    throw new IllegalArgumentException(sb.toString());
            }
        }
    }
}
