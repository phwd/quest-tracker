package com.facebook.base.lwperf.perfstats;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.os.Build;
import android.os.PowerManager;
import android.os.Process;
import android.os.SystemClock;
import android.util.Log;
import com.facebook.base.lwperf.perfstats.ProcVMStats;
import java.util.Map;
import javax.annotation.Nullable;

public class LitePerfStats implements PerfStats {
    public static final int NO_TID = -1;
    private static final String TAG = "LitePerfStats";
    @Nullable
    private static ActivityManager mActivityManager;
    @Nullable
    private static PowerManager mPowerManager;
    private long mAllocStalls;
    private boolean mCompleted;
    @Nullable
    private DiskIOStats mDiskIOStats;
    @Nullable
    private FsStats mDiskStats;
    private boolean mInitialized;
    private String mLowPowerState;
    @Nullable
    private ActivityManager.MemoryInfo mMemoryInfo;
    private long mPagesIn;
    private long mPagesOut;
    private int mPriorityStart;
    private int mPriorityStop;
    private long mProcDelayacctBlkioTicks;
    private long mProcessCpuTimeMs;
    private long mProcessMajorFaults;
    private long mProcessMinorFaults;
    private long mThreadCpuTimeMs;
    private long mThreadMajorFaults;
    private int mTid;

    @Override // com.facebook.base.lwperf.perfstats.PerfStats
    public void generateStartPerfStatsAfterSoLibLoad() {
    }

    @Override // com.facebook.base.lwperf.perfstats.PerfStats
    @Nullable
    @SuppressLint({"PublicMethodReturnMutableCollection"})
    public Map<String, Long> getBpfCounters() {
        return null;
    }

    @Override // com.facebook.base.lwperf.perfstats.PerfStats
    public int getClassLoadsAttempted() {
        return -1;
    }

    @Override // com.facebook.base.lwperf.perfstats.PerfStats
    public int getClassLoadsFailed() {
        return -1;
    }

    @Override // com.facebook.base.lwperf.perfstats.PerfStats
    public int getDexFileQueries() {
        return -1;
    }

    @Override // com.facebook.base.lwperf.perfstats.PerfStats
    public int getIncorrectDfaGuesses() {
        return -1;
    }

    @Override // com.facebook.base.lwperf.perfstats.PerfStats
    public int getLocatorAssistedClassLoads() {
        return -1;
    }

    @Override // com.facebook.base.lwperf.perfstats.PerfStats
    public long getPerfCpuClock() {
        return -1;
    }

    @Override // com.facebook.base.lwperf.perfstats.PerfStats
    public long getPerfTaskClock() {
        return -1;
    }

    @Override // com.facebook.base.lwperf.perfstats.PerfStats
    public long getUserInstructionCount() {
        return -1;
    }

    @Override // com.facebook.base.lwperf.perfstats.PerfStats
    public long getUserKernelInstructionCount() {
        return -1;
    }

    public LitePerfStats(@Nullable PowerManager powerManager, @Nullable ActivityManager activityManager) {
        mPowerManager = powerManager;
        mActivityManager = activityManager;
    }

    public LitePerfStats() {
        resetState();
    }

    public LitePerfStats(PerfStats perfStats) {
        resetState();
        if (perfStats != null) {
            this.mTid = perfStats.getTid();
            this.mPriorityStart = perfStats.getPriorityStart();
            this.mProcessCpuTimeMs = perfStats.getProcessCpuTimeMs();
            this.mThreadCpuTimeMs = perfStats.getThreadCpuTimeMs();
            this.mProcessMinorFaults = perfStats.getProcessMinorFaults();
            this.mProcessMajorFaults = perfStats.getProcessMajorFaults();
            this.mThreadMajorFaults = perfStats.getThreadMajorFaults();
            this.mAllocStalls = perfStats.getAllocStalls();
            this.mPagesIn = perfStats.getPagesIn();
            this.mPagesOut = perfStats.getPagesOut();
            if (perfStats instanceof LitePerfStats) {
                LitePerfStats litePerfStats = (LitePerfStats) perfStats;
                this.mDiskStats = litePerfStats.mDiskStats;
                this.mDiskIOStats = litePerfStats.mDiskIOStats;
            }
            this.mLowPowerState = perfStats.getLowPowerState();
            this.mMemoryInfo = perfStats.getMemoryInfo();
            this.mInitialized = true;
        }
    }

    public static LitePerfStats createLiteStartPerfStats() {
        LitePerfStats litePerfStats = new LitePerfStats();
        litePerfStats.generateStartPerfStats();
        return litePerfStats;
    }

    @Override // com.facebook.base.lwperf.perfstats.PerfStats
    public PerfStats clonePerfStats() {
        return new LitePerfStats(this);
    }

    @Override // com.facebook.base.lwperf.perfstats.PerfStats
    public boolean isInitialized() {
        return this.mInitialized;
    }

    @Override // com.facebook.base.lwperf.perfstats.PerfStats
    public boolean isCompleted() {
        return this.mCompleted;
    }

    @Override // com.facebook.base.lwperf.perfstats.PerfStats
    public int getPriorityStart() {
        return this.mPriorityStart;
    }

    @Override // com.facebook.base.lwperf.perfstats.PerfStats
    public int getPriorityStop() {
        return this.mPriorityStop;
    }

    @Override // com.facebook.base.lwperf.perfstats.PerfStats
    public long getProcessCpuTimeMs() {
        return this.mProcessCpuTimeMs;
    }

    @Override // com.facebook.base.lwperf.perfstats.PerfStats
    public long getProcDelayacctBlkioTicks() {
        return this.mProcDelayacctBlkioTicks;
    }

    @Override // com.facebook.base.lwperf.perfstats.PerfStats
    public long getProcessMajorFaults() {
        return this.mProcessMajorFaults;
    }

    @Override // com.facebook.base.lwperf.perfstats.PerfStats
    public long getProcessMinorFaults() {
        return this.mProcessMinorFaults;
    }

    @Override // com.facebook.base.lwperf.perfstats.PerfStats
    public long getThreadCpuTimeMs() {
        return this.mThreadCpuTimeMs;
    }

    @Override // com.facebook.base.lwperf.perfstats.PerfStats
    public long getThreadMajorFaults() {
        return this.mThreadMajorFaults;
    }

    @Override // com.facebook.base.lwperf.perfstats.PerfStats
    public long getAllocStalls() {
        return this.mAllocStalls;
    }

    @Override // com.facebook.base.lwperf.perfstats.PerfStats
    public long getPagesIn() {
        return this.mPagesIn;
    }

    @Override // com.facebook.base.lwperf.perfstats.PerfStats
    public long getPagesOut() {
        return this.mPagesOut;
    }

    @Override // com.facebook.base.lwperf.perfstats.PerfStats
    public long getReadChars() {
        DiskIOStats diskIOStats = this.mDiskIOStats;
        if (diskIOStats != null) {
            return diskIOStats.readChars;
        }
        return -1;
    }

    @Override // com.facebook.base.lwperf.perfstats.PerfStats
    public long getWriteChars() {
        DiskIOStats diskIOStats = this.mDiskIOStats;
        if (diskIOStats != null) {
            return diskIOStats.writeChars;
        }
        return -1;
    }

    @Override // com.facebook.base.lwperf.perfstats.PerfStats
    public long getReadSysCalls() {
        DiskIOStats diskIOStats = this.mDiskIOStats;
        if (diskIOStats != null) {
            return diskIOStats.readSysCalls;
        }
        return -1;
    }

    @Override // com.facebook.base.lwperf.perfstats.PerfStats
    public long getWriteSysCalls() {
        DiskIOStats diskIOStats = this.mDiskIOStats;
        if (diskIOStats != null) {
            return diskIOStats.writeSysCalls;
        }
        return -1;
    }

    @Override // com.facebook.base.lwperf.perfstats.PerfStats
    public long getReadBytes() {
        DiskIOStats diskIOStats = this.mDiskIOStats;
        if (diskIOStats != null) {
            return diskIOStats.readBytes;
        }
        return -1;
    }

    @Override // com.facebook.base.lwperf.perfstats.PerfStats
    public long getWriteBytes() {
        DiskIOStats diskIOStats = this.mDiskIOStats;
        if (diskIOStats != null) {
            return diskIOStats.writeBytes;
        }
        return -1;
    }

    @Override // com.facebook.base.lwperf.perfstats.PerfStats
    public long getCancelledWriteBytes() {
        DiskIOStats diskIOStats = this.mDiskIOStats;
        if (diskIOStats != null) {
            return diskIOStats.cancelledWriteBytes;
        }
        return -1;
    }

    @Override // com.facebook.base.lwperf.perfstats.PerfStats
    public long getAvailableDiskSpaceKb() {
        FsStats fsStats = this.mDiskStats;
        if (fsStats == null) {
            return -1;
        }
        return fsStats.getAvailableDiskSpaceKb();
    }

    @Override // com.facebook.base.lwperf.perfstats.PerfStats
    public String getLowPowerState() {
        return this.mLowPowerState;
    }

    @Override // com.facebook.base.lwperf.perfstats.PerfStats
    @Nullable
    public ActivityManager.MemoryInfo getMemoryInfo() {
        return this.mMemoryInfo;
    }

    @Override // com.facebook.base.lwperf.perfstats.PerfStats
    public boolean threadStatsAreValid() {
        return this.mCompleted && this.mTid != -1;
    }

    private void invalidateThreadStats() {
        this.mTid = -1;
        this.mThreadCpuTimeMs = -1;
        this.mThreadMajorFaults = -1;
    }

    @Override // com.facebook.base.lwperf.perfstats.PerfStats
    public int getTid() {
        return this.mTid;
    }

    @Override // com.facebook.base.lwperf.perfstats.PerfStats
    public void generateStartPerfStats() {
        String str;
        this.mTid = Process.myTid();
        this.mPriorityStart = Process.getThreadPriority(this.mTid);
        if (mPowerManager != null) {
            if (Build.VERSION.SDK_INT >= 21) {
                try {
                    str = mPowerManager.isPowerSaveMode() ? "true" : "false";
                } catch (SecurityException unused) {
                }
                this.mLowPowerState = str;
            }
            str = "unknown";
            this.mLowPowerState = str;
        }
        this.mProcessCpuTimeMs = Process.getElapsedCpuTime();
        this.mThreadCpuTimeMs = SystemClock.currentThreadTimeMillis();
        long[] readIoStatsFromProc = ProcIoStats.readIoStatsFromProc();
        this.mProcessMinorFaults = readIoStatsFromProc[0];
        this.mProcessMajorFaults = readIoStatsFromProc[2];
        this.mProcDelayacctBlkioTicks = readIoStatsFromProc[4];
        this.mThreadMajorFaults = ProcIoStats.readThMajorFaultsFromProc();
        ProcVMStats.VMStatsValues readFromProc = ProcVMStats.readFromProc();
        this.mAllocStalls = readFromProc.mAllocStalls;
        this.mPagesIn = readFromProc.mPagesIn;
        this.mPagesOut = readFromProc.mPagesOut;
        this.mDiskIOStats = DiskIOStats.getCurrentStats();
        this.mInitialized = true;
        this.mCompleted = false;
        this.mPriorityStop = -1;
    }

    @Override // com.facebook.base.lwperf.perfstats.PerfStats
    public void generateStopPerfStats() {
        if (this.mInitialized && !this.mCompleted) {
            int myTid = Process.myTid();
            this.mPriorityStop = Process.getThreadPriority(myTid);
            this.mProcessCpuTimeMs = Process.getElapsedCpuTime() - this.mProcessCpuTimeMs;
            long[] readIoStatsFromProc = ProcIoStats.readIoStatsFromProc();
            this.mProcessMinorFaults = readIoStatsFromProc[0] - this.mProcessMinorFaults;
            this.mProcessMajorFaults = readIoStatsFromProc[2] - this.mProcessMajorFaults;
            this.mProcDelayacctBlkioTicks = readIoStatsFromProc[4] - this.mProcDelayacctBlkioTicks;
            if (myTid == this.mTid) {
                this.mThreadCpuTimeMs = SystemClock.currentThreadTimeMillis() - this.mThreadCpuTimeMs;
                this.mThreadMajorFaults = ProcIoStats.readThMajorFaultsFromProc() - this.mThreadMajorFaults;
            } else {
                invalidateThreadStats();
            }
            if (!(this.mAllocStalls == -1 && this.mPagesIn == -1 && this.mPagesOut == -1)) {
                ProcVMStats.VMStatsValues readFromProc = ProcVMStats.readFromProc();
                if (this.mAllocStalls == -1 || readFromProc.mAllocStalls == -1) {
                    this.mAllocStalls = -1;
                } else {
                    this.mAllocStalls = readFromProc.mAllocStalls - this.mAllocStalls;
                }
                if (this.mPagesIn == -1 || readFromProc.mPagesIn == -1) {
                    this.mPagesIn = -1;
                } else {
                    this.mPagesIn = readFromProc.mPagesIn - this.mPagesIn;
                }
                if (this.mPagesOut == -1 || readFromProc.mPagesOut == -1) {
                    this.mPagesOut = -1;
                } else {
                    this.mPagesOut = readFromProc.mPagesOut - this.mPagesOut;
                }
            }
            this.mDiskStats = new FsStats();
            DiskIOStats diskIOStats = this.mDiskIOStats;
            if (diskIOStats != null) {
                DiskIOStats currentStats = DiskIOStats.getCurrentStats();
                if (currentStats != null) {
                    this.mDiskIOStats = currentStats.getDiskIOStatsDelta(diskIOStats);
                } else {
                    this.mDiskIOStats = null;
                }
            }
            if (mActivityManager != null) {
                this.mMemoryInfo = new ActivityManager.MemoryInfo();
                mActivityManager.getMemoryInfo(this.mMemoryInfo);
            }
            if (!sanityCheck()) {
                resetState();
            } else {
                this.mCompleted = true;
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean sanityCheck() {
        if (this.mProcessCpuTimeMs >= 0 && this.mProcessMajorFaults >= 0 && this.mProcessMinorFaults >= 0) {
            if (!threadStatsAreValid()) {
                return true;
            }
            if (this.mThreadCpuTimeMs >= 0 && this.mThreadMajorFaults >= 0) {
                return true;
            }
        }
        if (!Log.isLoggable(TAG, 5)) {
            return false;
        }
        Log.w(TAG, "Negative values detected for PerfStats, discarding stats.");
        return false;
    }

    /* access modifiers changed from: protected */
    public void resetState() {
        this.mInitialized = false;
        this.mCompleted = false;
        this.mTid = -1;
        this.mPriorityStart = -1;
        this.mPriorityStop = -1;
        this.mLowPowerState = "not set";
        this.mProcessCpuTimeMs = -1;
        this.mThreadCpuTimeMs = -1;
        this.mProcessMinorFaults = -1;
        this.mProcessMajorFaults = -1;
        this.mThreadMajorFaults = -1;
        this.mAllocStalls = -1;
        this.mPagesIn = -1;
        this.mPagesOut = -1;
        this.mDiskStats = null;
        this.mMemoryInfo = null;
        this.mDiskIOStats = null;
    }
}
