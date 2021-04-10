package org.webrtc;

public class RtpReceiver {
    public MediaStreamTrack cachedTrack;
    public final long nativeRtpReceiver;

    public static native void free(long j);

    public static native long nativeGetTrack(long j);

    public static native String nativeId(long j);

    public void dispose() {
        this.cachedTrack.dispose();
        free(this.nativeRtpReceiver);
    }

    public String id() {
        return nativeId(this.nativeRtpReceiver);
    }

    public RtpReceiver(long j) {
        this.nativeRtpReceiver = j;
        this.cachedTrack = new MediaStreamTrack(nativeGetTrack(j));
    }

    public MediaStreamTrack track() {
        return this.cachedTrack;
    }
}
