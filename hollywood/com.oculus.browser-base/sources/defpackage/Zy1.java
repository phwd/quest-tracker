package defpackage;

import java.util.Iterator;
import org.chromium.content.browser.webcontents.WebContentsImpl;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: Zy1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class Zy1 extends WF implements Qr1 {
    public final C1322Vq0 F;
    public WindowAndroid G;
    public C3115iu1 H;
    public boolean I;

    /* renamed from: J  reason: collision with root package name */
    public int f9391J;
    public float K;

    public Zy1(WebContents webContents) {
        C1322Vq0 vq0 = new C1322Vq0();
        this.F = vq0;
        this.H = C3115iu1.c(webContents);
        WindowAndroid I2 = webContents.I();
        if (I2 != null) {
            x(I2);
        }
        vq0.b((WebContentsImpl) webContents);
    }

    public static Zy1 t0(WebContents webContents) {
        return (Zy1) ((WebContentsImpl) webContents).v0(Zy1.class, Yy1.f9310a);
    }

    @Override // defpackage.XF, defpackage.WF
    public void U(float f) {
        if (this.K != f) {
            this.K = f;
            Iterator it = this.F.iterator();
            while (true) {
                C1261Uq0 uq0 = (C1261Uq0) it;
                if (uq0.hasNext()) {
                    ((Wy1) uq0.next()).U(f);
                } else {
                    return;
                }
            }
        }
    }

    @Override // defpackage.Qr1
    public void destroy() {
    }

    @Override // defpackage.XF, defpackage.WF
    public void h0(int i) {
        if (this.f9391J != i) {
            this.f9391J = i;
            Iterator it = this.F.iterator();
            while (true) {
                C1261Uq0 uq0 = (C1261Uq0) it;
                if (uq0.hasNext()) {
                    ((Wy1) uq0.next()).h0(i);
                } else {
                    return;
                }
            }
        }
    }

    public void r0(Wy1 wy1) {
        this.F.b(wy1);
        if (this.I) {
            wy1.onAttachedToWindow();
        }
    }

    public final void s0() {
        WindowAndroid windowAndroid;
        WindowAndroid windowAndroid2;
        if (this.I && (windowAndroid2 = this.G) != null) {
            YF yf = windowAndroid2.I;
            yf.b.put(this, null);
            h0(yf.h);
            U(yf.e);
        }
        if (this.I && (windowAndroid = this.G) != null) {
            windowAndroid.W.b(this.H);
            if (this.G.t0() == 3) {
                C3115iu1 iu1 = this.H;
                if (iu1.H) {
                    iu1.H = false;
                    iu1.j();
                }
            }
        }
    }

    public final void u0() {
        WindowAndroid windowAndroid;
        WindowAndroid windowAndroid2 = this.G;
        if (windowAndroid2 != null) {
            windowAndroid2.I.b.remove(this);
        }
        if (this.I && (windowAndroid = this.G) != null) {
            windowAndroid.W.c(this.H);
        }
    }

    public void x(WindowAndroid windowAndroid) {
        if (windowAndroid != this.G) {
            u0();
            this.G = windowAndroid;
            s0();
            Iterator it = this.F.iterator();
            while (true) {
                C1261Uq0 uq0 = (C1261Uq0) it;
                if (uq0.hasNext()) {
                    ((Wy1) uq0.next()).x(windowAndroid);
                } else {
                    return;
                }
            }
        }
    }
}
