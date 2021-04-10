package com.android.server.am;

import com.android.internal.app.procstats.Processstats;
import com.android.server.am.MemInfoDumpProto;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface MemInfoDumpProtoOrBuilder extends MessageLiteOrBuilder {
    MemInfoDumpProto.AppData getAppProcesses(int i);

    int getAppProcessesCount();

    List<MemInfoDumpProto.AppData> getAppProcessesList();

    long getCachedKernelKb();

    long getCachedPssKb();

    long getElapsedRealtimeMs();

    long getFreeKb();

    boolean getIsHighEndGfx();

    boolean getIsLowRamDevice();

    long getKsmSharedKb();

    long getKsmSharingKb();

    long getKsmUnsharedKb();

    long getKsmVolatileKb();

    long getLostRamKb();

    MemInfoDumpProto.ProcessMemory getNativeProcesses(int i);

    int getNativeProcessesCount();

    List<MemInfoDumpProto.ProcessMemory> getNativeProcessesList();

    long getOomKb();

    long getRestoreLimitKb();

    Processstats.ProcessStatsProto.MemoryFactor getStatus();

    MemInfoDumpProto.MemItem getTotalPssByCategory(int i);

    int getTotalPssByCategoryCount();

    List<MemInfoDumpProto.MemItem> getTotalPssByCategoryList();

    MemInfoDumpProto.MemItem getTotalPssByOomAdjustment(int i);

    int getTotalPssByOomAdjustmentCount();

    List<MemInfoDumpProto.MemItem> getTotalPssByOomAdjustmentList();

    MemInfoDumpProto.MemItem getTotalPssByProcess(int i);

    int getTotalPssByProcessCount();

    List<MemInfoDumpProto.MemItem> getTotalPssByProcessList();

    long getTotalRamKb();

    long getTotalZramKb();

    long getTotalZramSwapKb();

    int getTuningLargeMb();

    int getTuningMb();

    long getUptimeDurationMs();

    long getUsedKernelKb();

    long getUsedPssKb();

    long getZramPhysicalUsedInSwapKb();

    boolean hasCachedKernelKb();

    boolean hasCachedPssKb();

    boolean hasElapsedRealtimeMs();

    boolean hasFreeKb();

    boolean hasIsHighEndGfx();

    boolean hasIsLowRamDevice();

    boolean hasKsmSharedKb();

    boolean hasKsmSharingKb();

    boolean hasKsmUnsharedKb();

    boolean hasKsmVolatileKb();

    boolean hasLostRamKb();

    boolean hasOomKb();

    boolean hasRestoreLimitKb();

    boolean hasStatus();

    boolean hasTotalRamKb();

    boolean hasTotalZramKb();

    boolean hasTotalZramSwapKb();

    boolean hasTuningLargeMb();

    boolean hasTuningMb();

    boolean hasUptimeDurationMs();

    boolean hasUsedKernelKb();

    boolean hasUsedPssKb();

    boolean hasZramPhysicalUsedInSwapKb();
}
