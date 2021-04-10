package X;

import android.os.Handler;

/* renamed from: X.1ys  reason: invalid class name */
public class AnonymousClass1ys implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.cameracore.audio.encoder.LegacyAudioEncoderImpl$3";
    public final /* synthetic */ Handler A00;
    public final /* synthetic */ C11401ym A01;
    public final /* synthetic */ AbstractC11131xk A02;

    public AnonymousClass1ys(C11401ym r1, AbstractC11131xk r2, Handler handler) {
        this.A01 = r1;
        this.A02 = r2;
        this.A00 = handler;
    }

    public final void run() {
        C11401ym r4 = this.A01;
        AbstractC11131xk r5 = this.A02;
        Handler handler = this.A00;
        Integer num = r4.A06;
        Integer num2 = AnonymousClass007.A0C;
        if (num == num2) {
            C11401ym.A01(r4);
        }
        try {
            if (r4.A01 != null) {
                if (r4.A06 == num2) {
                    r4.A01.flush();
                    r4.A01.stop();
                }
                r4.A01.release();
            }
            r4.A06 = AnonymousClass007.A00;
            r4.A01 = null;
            r4.A00 = null;
            r4.A02 = null;
            AnonymousClass1z6.A00(r5, handler);
        } catch (Exception e) {
            AnonymousClass1z6.A01(r5, handler, e);
            r4.A06 = AnonymousClass007.A00;
            r4.A01 = null;
            r4.A00 = null;
            r4.A02 = null;
        } catch (Throwable th) {
            r4.A06 = AnonymousClass007.A00;
            r4.A01 = null;
            r4.A00 = null;
            r4.A02 = null;
            throw th;
        }
    }
}
