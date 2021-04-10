package org.webrtc;

public class VideoSource extends MediaSource {
    public static native void restart(long j);

    public static native void stop(long j);

    public VideoSource(long j) {
        super(j);
    }

    public void restart() {
        restart(this.nativeSource);
    }

    public void stop() {
        stop(this.nativeSource);
    }
}
