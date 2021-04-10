package org.webrtc;

public class RtpSender {
    public MediaStreamTrack cachedTrack;
    public final long nativeRtpSender;
    public boolean ownsTrack = true;

    public static native void free(long j);

    public static native long nativeGetTrack(long j);

    public static native String nativeId(long j);

    public static native boolean nativeSetTrack(long j, long j2);

    public void dispose() {
        MediaStreamTrack mediaStreamTrack = this.cachedTrack;
        if (mediaStreamTrack != null && this.ownsTrack) {
            mediaStreamTrack.dispose();
        }
        free(this.nativeRtpSender);
    }

    public String id() {
        return nativeId(this.nativeRtpSender);
    }

    public boolean setTrack(MediaStreamTrack mediaStreamTrack, boolean z) {
        long j;
        long j2 = this.nativeRtpSender;
        if (mediaStreamTrack == null) {
            j = 0;
        } else {
            j = mediaStreamTrack.nativeTrack;
        }
        if (!nativeSetTrack(j2, j)) {
            return false;
        }
        MediaStreamTrack mediaStreamTrack2 = this.cachedTrack;
        if (mediaStreamTrack2 != null && this.ownsTrack) {
            mediaStreamTrack2.dispose();
        }
        this.cachedTrack = mediaStreamTrack;
        this.ownsTrack = z;
        return true;
    }

    public RtpSender(long j) {
        MediaStreamTrack mediaStreamTrack;
        this.nativeRtpSender = j;
        long nativeGetTrack = nativeGetTrack(j);
        if (nativeGetTrack == 0) {
            mediaStreamTrack = null;
        } else {
            mediaStreamTrack = new MediaStreamTrack(nativeGetTrack);
        }
        this.cachedTrack = mediaStreamTrack;
    }

    public MediaStreamTrack track() {
        return this.cachedTrack;
    }
}
