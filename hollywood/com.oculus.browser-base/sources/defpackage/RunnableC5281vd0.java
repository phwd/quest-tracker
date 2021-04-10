package defpackage;

import org.chromium.media.MediaCodecBridge;

/* renamed from: vd0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC5281vd0 implements Runnable {
    public int F;
    public final /* synthetic */ MediaCodecBridge G;

    public RunnableC5281vd0(MediaCodecBridge mediaCodecBridge, int i) {
        this.G = mediaCodecBridge;
        this.F = i;
    }

    public void run() {
        MediaCodecBridge mediaCodecBridge = this.G;
        int i = this.F;
        synchronized (mediaCodecBridge) {
            if (mediaCodecBridge.k == i) {
                mediaCodecBridge.i = false;
            }
        }
    }
}
