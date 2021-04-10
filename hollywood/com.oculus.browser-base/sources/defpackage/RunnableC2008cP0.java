package defpackage;

import android.graphics.Bitmap;

/* renamed from: cP0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC2008cP0 implements Runnable {
    public final C2691gP0 F;

    public RunnableC2008cP0(C2691gP0 gp0) {
        this.F = gp0;
    }

    public void run() {
        C2691gP0 gp0 = this.F;
        Bitmap bitmap = gp0.g.c;
        gp0.h = bitmap;
        if (bitmap != null) {
            HZ hz = gp0.f;
            if (hz == null) {
                gp0.d();
            } else if (hz.a()) {
                gp0.c();
            } else {
                int i = C2691gP0.f9996a;
                if (i < 5) {
                    C2691gP0.f9996a = i + 1;
                    gp0.b(true, null);
                    return;
                }
                gp0.d();
            }
        }
    }
}
