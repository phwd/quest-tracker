package X;

import android.os.Handler;

/* renamed from: X.207  reason: invalid class name */
public class AnonymousClass207 implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.cameracore.videoencoding.LegacyAsyncSurfaceVideoEncoderImpl$1";
    public final /* synthetic */ Handler A00;
    public final /* synthetic */ AnonymousClass1zF A01;
    public final /* synthetic */ AnonymousClass1zt A02;

    public AnonymousClass207(AnonymousClass1zt r1, AnonymousClass1zF r2, Handler handler) {
        this.A02 = r1;
        this.A01 = r2;
        this.A00 = handler;
    }

    public final void run() {
        AnonymousClass1zt.A03(this.A02, this.A01, this.A00, true);
    }
}
