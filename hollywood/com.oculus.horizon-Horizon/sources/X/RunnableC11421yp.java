package X;

import android.os.Handler;

/* renamed from: X.1yp  reason: invalid class name and case insensitive filesystem */
public class RunnableC11421yp implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.cameracore.audio.encoder.AudioEncoderImpl$3";
    public final /* synthetic */ Handler A00;
    public final /* synthetic */ C11391yl A01;
    public final /* synthetic */ AbstractC11131xk A02;

    public RunnableC11421yp(C11391yl r1, AbstractC11131xk r2, Handler handler) {
        this.A01 = r1;
        this.A02 = r2;
        this.A00 = handler;
    }

    public final void run() {
        boolean z;
        C11391yl r3 = this.A01;
        AbstractC11131xk r5 = this.A02;
        Handler handler = this.A00;
        try {
            if (r3.A01 != null) {
                if (r3.A06 == AnonymousClass007.A0C) {
                    int dequeueInputBuffer = r3.A01.dequeueInputBuffer((long) r3.A03.A01);
                    if (dequeueInputBuffer >= 0) {
                        r3.A01.queueInputBuffer(dequeueInputBuffer, 0, 0, 0, 4);
                        z = true;
                    } else {
                        z = false;
                    }
                    C11391yl.A01(r3, z);
                    r3.A01.stop();
                }
                r3.A01.release();
            }
            r3.A06 = AnonymousClass007.A00;
            r3.A01 = null;
            r3.A00 = null;
            r3.A02 = null;
            AnonymousClass1z6.A00(r5, handler);
        } catch (Exception e) {
            AnonymousClass1z6.A01(r5, handler, e);
            r3.A06 = AnonymousClass007.A00;
            r3.A01 = null;
            r3.A00 = null;
            r3.A02 = null;
        } catch (Throwable th) {
            r3.A06 = AnonymousClass007.A00;
            r3.A01 = null;
            r3.A00 = null;
            r3.A02 = null;
            throw th;
        }
    }
}
