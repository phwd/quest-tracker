package X;

import android.os.Handler;

/* renamed from: X.200  reason: invalid class name */
public class AnonymousClass200 implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.cameracore.videoencoding.AsyncSurfaceVideoEncoderImpl$2";
    public final /* synthetic */ Handler A00;
    public final /* synthetic */ AnonymousClass1zF A01;
    public final /* synthetic */ AnonymousClass1zu A02;

    public AnonymousClass200(AnonymousClass1zu r1, AnonymousClass1zF r2, Handler handler) {
        this.A02 = r1;
        this.A01 = r2;
        this.A00 = handler;
    }

    public final void run() {
        String str;
        AnonymousClass1zu r3 = this.A02;
        AnonymousClass1zF r4 = this.A01;
        Handler handler = this.A00;
        synchronized (r3) {
            r3.A05.append("asyncStart, ");
            if (r3.A0B != AnonymousClass007.A00) {
                Integer num = r3.A0B;
                if (num != null) {
                    str = AnonymousClass1z2.A00(num);
                } else {
                    str = "null";
                }
                AnonymousClass205 r5 = new AnonymousClass205(AnonymousClass006.A05("prepare() must be called before starting video encoding. Current state is: ", str));
                r5.A00("current_state", AnonymousClass1z2.A00(r3.A0B));
                r5.A00("method_invocation", r3.A05.toString());
                AnonymousClass1z7.A01(r4, handler, r5);
            } else {
                try {
                    r3.A00.start();
                    r3.A0B = AnonymousClass007.A01;
                    r3.A05.append("asyncStart end, ");
                    AnonymousClass1z7.A00(r4, handler);
                } catch (Exception e) {
                    AnonymousClass205 r0 = new AnonymousClass205(e);
                    AnonymousClass1zu.A01(r3, r0, e);
                    AnonymousClass1z7.A01(r4, handler, r0);
                }
            }
        }
    }
}
