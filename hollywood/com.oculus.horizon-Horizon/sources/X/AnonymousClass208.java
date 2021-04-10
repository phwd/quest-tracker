package X;

import android.os.Handler;

/* renamed from: X.208  reason: invalid class name */
public class AnonymousClass208 implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.cameracore.videoencoding.LegacySurfaceVideoEncoderImpl$1";
    public final /* synthetic */ Handler A00;
    public final /* synthetic */ AnonymousClass1zF A01;
    public final /* synthetic */ AnonymousClass1zs A02;

    public AnonymousClass208(AnonymousClass1zs r1, AnonymousClass1zF r2, Handler handler) {
        this.A02 = r1;
        this.A01 = r2;
        this.A00 = handler;
    }

    public final void run() {
        AnonymousClass1zs.A02(this.A02, this.A01, this.A00, true);
    }
}
