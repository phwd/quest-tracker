package X;

import android.os.Handler;

/* renamed from: X.206  reason: invalid class name */
public class AnonymousClass206 implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.cameracore.videoencoding.AsyncSurfaceVideoEncoderImpl$1";
    public final /* synthetic */ Handler A00;
    public final /* synthetic */ AnonymousClass1zF A01;
    public final /* synthetic */ AnonymousClass1zu A02;

    public AnonymousClass206(AnonymousClass1zu r1, AnonymousClass1zF r2, Handler handler) {
        this.A02 = r1;
        this.A01 = r2;
        this.A00 = handler;
    }

    public final void run() {
        AnonymousClass1zu.A02(this.A02, this.A01, this.A00, true);
    }
}
