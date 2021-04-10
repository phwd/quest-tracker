package com.facebook.base.lwperf.perfstats;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import java.util.Map;
import javax.annotation.Nullable;

public interface PerfStats {
    PerfStats clonePerfStats();

    void generateStartPerfStats();

    void generateStartPerfStatsAfterSoLibLoad();

    void generateStopPerfStats();

    long getAllocStalls();

    long getAvailableDiskSpaceKb();

    @Nullable
    @SuppressLint({"PublicMethodReturnMutableCollection"})
    Map<String, Long> getBpfCounters();

    long getCancelledWriteBytes();

    int getClassLoadsAttempted();

    int getClassLoadsFailed();

    int getDexFileQueries();

    int getIncorrectDfaGuesses();

    int getLocatorAssistedClassLoads();

    String getLowPowerState();

    @Nullable
    ActivityManager.MemoryInfo getMemoryInfo();

    long getPagesIn();

    long getPagesOut();

    long getPerfCpuClock();

    long getPerfTaskClock();

    int getPriorityStart();

    int getPriorityStop();

    long getProcDelayacctBlkioTicks();

    long getProcessCpuTimeMs();

    long getProcessMajorFaults();

    long getProcessMinorFaults();

    long getReadBytes();

    long getReadChars();

    long getReadSysCalls();

    long getThreadCpuTimeMs();

    long getThreadMajorFaults();

    int getTid();

    long getUserInstructionCount();

    long getUserKernelInstructionCount();

    long getWriteBytes();

    long getWriteChars();

    long getWriteSysCalls();

    boolean isCompleted();

    boolean isInitialized();

    boolean threadStatsAreValid();
}
