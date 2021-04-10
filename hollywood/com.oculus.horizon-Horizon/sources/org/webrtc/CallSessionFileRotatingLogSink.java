package org.webrtc;

import org.webrtc.Logging;

public class CallSessionFileRotatingLogSink {
    public long nativeSink;

    public static native long nativeAddSink(String str, int i, int i2);

    public static native void nativeDeleteSink(long j);

    public static native byte[] nativeGetLogData(String str);

    static {
        System.loadLibrary("jingle_peerconnection_so");
    }

    public void dispose() {
        long j = this.nativeSink;
        if (j != 0) {
            nativeDeleteSink(j);
            this.nativeSink = 0;
        }
    }

    public CallSessionFileRotatingLogSink(String str, int i, Logging.Severity severity) {
        this.nativeSink = nativeAddSink(str, i, severity.ordinal());
    }

    public static byte[] getLogData(String str) {
        return nativeGetLogData(str);
    }
}
