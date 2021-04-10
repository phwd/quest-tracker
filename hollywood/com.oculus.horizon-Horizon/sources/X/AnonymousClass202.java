package X;

import android.os.Handler;
import android.view.Surface;

/* renamed from: X.202  reason: invalid class name */
public class AnonymousClass202 implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.cameracore.videoencoding.LegacySurfaceVideoEncoderImpl$3";
    public final /* synthetic */ C11471yw A00;
    public final /* synthetic */ AnonymousClass1zs A01;

    public AnonymousClass202(AnonymousClass1zs r1, C11471yw r2) {
        this.A01 = r1;
        this.A00 = r2;
    }

    public final void run() {
        AnonymousClass1zs r5 = this.A01;
        C11471yw r4 = this.A00;
        Handler A002 = r4.A00();
        r5.A03.append("asyncStop, ");
        if (r5.A09) {
            AnonymousClass1zs.A03(r5, true);
        }
        try {
            Surface surface = r5.A02;
            if (surface != null) {
                surface.release();
            }
            if (r5.A00 != null) {
                if (r5.A09) {
                    r5.A00.flush();
                    r5.A00.stop();
                }
                r5.A00.release();
            }
            r5.A08 = AnonymousClass007.A0D;
            r5.A00 = null;
            r5.A02 = null;
            r5.A01 = null;
            r5.A03.append("asyncStop end, ");
            AnonymousClass1z7.A00(r4, A002);
        } catch (Exception e) {
            AnonymousClass205 r1 = new AnonymousClass205(e);
            AnonymousClass1zs.A01(r5, r1, e);
            r5.A08 = AnonymousClass007.A0D;
            r5.A00 = null;
            r5.A02 = null;
            r5.A01 = null;
            AnonymousClass1z7.A01(r4, A002, r1);
        }
    }
}
