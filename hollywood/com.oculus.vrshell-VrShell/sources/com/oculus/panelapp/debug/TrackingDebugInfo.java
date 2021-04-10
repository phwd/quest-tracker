package com.oculus.panelapp.debug;

/* access modifiers changed from: package-private */
public class TrackingDebugInfo {
    private final long mPtr = nativeCreateInstance();

    private native String get(long j);

    private native String getType(long j);

    private native long nativeCreateInstance();

    private native boolean start(long j);

    private native boolean stop(long j);

    public boolean start() {
        return start(this.mPtr);
    }

    public boolean stop() {
        return stop(this.mPtr);
    }

    public String get() {
        return get(this.mPtr);
    }

    public String getType() {
        return getType(this.mPtr);
    }
}
