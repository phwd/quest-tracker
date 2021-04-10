package com.facebook.proxygen;

public interface TraceEventHandler {
    void decorateStatistics(RequestStats requestStats, long j);
}
