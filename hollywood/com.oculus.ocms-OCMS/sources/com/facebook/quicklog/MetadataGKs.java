package com.facebook.quicklog;

import com.facebook.infer.annotation.ThreadSafe;

@ThreadSafe
public interface MetadataGKs {
    boolean isActiveTTRCMarkersEnabled();

    boolean isCpuStatsEnabled();

    boolean isDataUsageEnabled();

    boolean isDetailedMemStatsEnabled();

    boolean isDetailedNetworkInfoEnabled();

    boolean isDexInfoEnabled();

    boolean isEndpointEnabled();

    boolean isFb4aStartupStatsEnabled();

    boolean isFreeModeStatsEnabled();

    boolean isIOStatsEnabled();

    boolean isLegacyPerfStatsEnabled();

    boolean isLithoStatsEnabled();

    boolean isMccStatsEnabled();

    boolean isMemoryStatsEnabled();

    boolean isMobileBoostUsageEnabled();

    boolean isNTStatsEnabled();

    boolean isNetworkStatsEnabled();

    boolean isStallTimeEnabled();

    boolean isStartupStatsEnabled();

    boolean isThermalStatsEnabled();

    boolean isYogaStatsEnabled();
}
