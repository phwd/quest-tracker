package com.facebook.quicklog;

public interface ReliabilityMarkersObserver {
    void reliabilityMarkerEnd(int i, int i2);

    void reliabilityMarkerStart(int i, int i2, boolean z);

    void reliabilityMarkersEndAll();
}
