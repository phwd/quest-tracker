package X;

import android.media.MediaCodec;
import android.os.Handler;

/* renamed from: X.204  reason: invalid class name */
public class AnonymousClass204 implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.cameracore.videoencoding.AsyncSurfaceVideoEncoderImpl$3";
    public final /* synthetic */ C11471yw A00;
    public final /* synthetic */ AnonymousClass1zu A01;

    public AnonymousClass204(AnonymousClass1zu r1, C11471yw r2) {
        this.A01 = r1;
        this.A00 = r2;
    }

    public final void run() {
        AnonymousClass1zu r4 = this.A01;
        C11471yw r3 = this.A00;
        Handler A002 = r3.A00();
        r4.A05.append("asyncStop, ");
        r4.A04 = r3;
        r4.A02 = A002;
        MediaCodec mediaCodec = r4.A00;
        if (mediaCodec != null) {
            mediaCodec.signalEndOfInputStream();
        }
    }
}
