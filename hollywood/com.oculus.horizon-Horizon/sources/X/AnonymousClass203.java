package X;

import android.media.MediaCodec;
import android.os.Handler;

/* renamed from: X.203  reason: invalid class name */
public class AnonymousClass203 implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.cameracore.videoencoding.LegacyAsyncSurfaceVideoEncoderImpl$3";
    public final /* synthetic */ C11471yw A00;
    public final /* synthetic */ AnonymousClass1zt A01;

    public AnonymousClass203(AnonymousClass1zt r1, C11471yw r2) {
        this.A01 = r1;
        this.A00 = r2;
    }

    public final void run() {
        AnonymousClass1zt r4 = this.A01;
        C11471yw r3 = this.A00;
        Handler A002 = r3.A00();
        r4.A04.append("asyncStop, ");
        r4.A03 = r3;
        r4.A02 = A002;
        try {
            MediaCodec mediaCodec = r4.A00;
            if (mediaCodec != null) {
                mediaCodec.signalEndOfInputStream();
            }
        } catch (Exception e) {
            AnonymousClass205 r0 = new AnonymousClass205(e);
            AnonymousClass1zt.A01(r4, r0, e);
            AnonymousClass1zt.A02(r4, r3, A002);
            AnonymousClass1z7.A01(r3, A002, r0);
        }
    }
}
