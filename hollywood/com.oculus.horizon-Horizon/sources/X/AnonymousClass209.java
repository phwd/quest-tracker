package X;

import android.os.Handler;

/* renamed from: X.209  reason: invalid class name */
public class AnonymousClass209 implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.cameracore.videoencoding.SurfaceVideoEncoderImpl$1";
    public final /* synthetic */ Handler A00;
    public final /* synthetic */ AnonymousClass1zF A01;
    public final /* synthetic */ AnonymousClass1zr A02;

    public AnonymousClass209(AnonymousClass1zr r1, AnonymousClass1zF r2, Handler handler) {
        this.A02 = r1;
        this.A01 = r2;
        this.A00 = handler;
    }

    public final void run() {
        AnonymousClass1zr.A02(this.A02, this.A01, this.A00, true);
    }
}
