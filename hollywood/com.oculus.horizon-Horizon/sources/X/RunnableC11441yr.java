package X;

import android.os.Handler;

/* renamed from: X.1yr  reason: invalid class name and case insensitive filesystem */
public class RunnableC11441yr implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.cameracore.audio.encoder.LegacyAudioEncoderImpl$2";
    public final /* synthetic */ Handler A00;
    public final /* synthetic */ C11401ym A01;
    public final /* synthetic */ AbstractC11131xk A02;

    public RunnableC11441yr(C11401ym r1, AbstractC11131xk r2, Handler handler) {
        this.A01 = r1;
        this.A02 = r2;
        this.A00 = handler;
    }

    public final void run() {
        C11401ym r4 = this.A01;
        AbstractC11131xk r3 = this.A02;
        Handler handler = this.A00;
        if (r4.A06 != AnonymousClass007.A01) {
            AnonymousClass1z6.A01(r3, handler, new IllegalStateException(AnonymousClass006.A05("prepare() must be called before starting audio encoding. Current state is: ", AnonymousClass1z1.A00(r4.A06))));
            return;
        }
        try {
            r4.A01.start();
            r4.A06 = AnonymousClass007.A0C;
            AnonymousClass1z6.A00(r3, handler);
        } catch (Exception e) {
            AnonymousClass1z6.A01(r3, handler, e);
        }
    }
}
