package org.webrtc;

public class MediaStreamTrack {
    public final long nativeTrack;

    public enum State {
        INITIALIZING,
        LIVE,
        ENDED,
        FAILED
    }

    public static native void free(long j);

    public static native boolean nativeEnabled(long j);

    public static native String nativeId(long j);

    public static native String nativeKind(long j);

    public static native boolean nativeSetEnabled(long j, boolean z);

    public static native boolean nativeSetState(long j, int i);

    public static native State nativeState(long j);

    public void dispose() {
        free(this.nativeTrack);
    }

    public boolean enabled() {
        return nativeEnabled(this.nativeTrack);
    }

    public String id() {
        return nativeId(this.nativeTrack);
    }

    public String kind() {
        return nativeKind(this.nativeTrack);
    }

    public boolean setEnabled(boolean z) {
        return nativeSetEnabled(this.nativeTrack, z);
    }

    public boolean setState(State state) {
        return nativeSetState(this.nativeTrack, state.ordinal());
    }

    public State state() {
        return nativeState(this.nativeTrack);
    }

    public MediaStreamTrack(long j) {
        this.nativeTrack = j;
    }
}
