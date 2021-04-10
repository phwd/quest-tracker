package com.facebook.quicklog;

import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public interface QuickPerformanceLoggerGKs {
    boolean enableCrashReporting();

    @Nullable
    MetadataGKs getMetadataGKs();

    boolean isHealthMonitoringEnabled();

    boolean isLocalAggregationEnabled();

    int localAggregationEventLimit();

    boolean shouldAllowTracer();

    boolean shouldCancelUserFlowOnBackground();

    boolean shouldCheckIsTracing();

    boolean shouldProcessPerfEventsOnIdle();

    boolean shouldReportWhenNoConfig();
}
