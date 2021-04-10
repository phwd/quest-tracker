package org.webrtc;

public class MediaSource {
    public final long nativeSource;

    public enum State {
        INITIALIZING,
        LIVE,
        ENDED,
        MUTED
    }

    public static native void free(long j);

    public static native State nativeState(long j);

    public void dispose() {
        free(this.nativeSource);
    }

    public State state() {
        return nativeState(this.nativeSource);
    }

    public MediaSource(long j) {
        this.nativeSource = j;
    }
}
