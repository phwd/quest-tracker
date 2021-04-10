package X;

import android.os.Handler;
import android.view.Surface;

/* renamed from: X.1zx  reason: invalid class name and case insensitive filesystem */
public class RunnableC11551zx implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.cameracore.videoencoding.SurfaceVideoEncoderImpl$3";
    public final /* synthetic */ C11471yw A00;
    public final /* synthetic */ AnonymousClass1zr A01;

    public RunnableC11551zx(AnonymousClass1zr r1, C11471yw r2) {
        this.A01 = r1;
        this.A00 = r2;
    }

    public final void run() {
        AnonymousClass1zr r6 = this.A01;
        C11471yw r5 = this.A00;
        Handler A002 = r5.A00();
        r6.A03.append("asyncStop, ");
        try {
            if (r6.A00 != null) {
                if (r6.A0A) {
                    r6.A00.signalEndOfInputStream();
                    AnonymousClass1zr.A03(r6, true);
                } else {
                    r6.A04 = true;
                }
            }
            Surface surface = r6.A02;
            if (surface != null) {
                surface.release();
            }
            if (r6.A00 != null) {
                if (r6.A0A) {
                    r6.A00.stop();
                }
                r6.A00.release();
            }
            r6.A09 = AnonymousClass007.A0D;
            r6.A00 = null;
            r6.A02 = null;
            r6.A01 = null;
            r6.A03.append("asyncStop end, ");
            if (r6.A04) {
                AnonymousClass1z7.A00(r5, A002);
                return;
            }
            AnonymousClass205 r2 = new AnonymousClass205("Codec not in End-Of-Stream stage when stopping");
            r2.A00("current_state", AnonymousClass1z2.A00(r6.A09));
            r2.A00("method_invocation", r6.A03.toString());
            AnonymousClass1z7.A01(r5, A002, r2);
        } catch (Exception e) {
            AnonymousClass205 r1 = new AnonymousClass205(e);
            AnonymousClass1zr.A01(r6, r1, e);
            r6.A09 = AnonymousClass007.A0D;
            r6.A00 = null;
            r6.A02 = null;
            r6.A01 = null;
            AnonymousClass1z7.A01(r5, A002, r1);
        }
    }
}
