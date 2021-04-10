package defpackage;

import J.N;
import java.util.Iterator;
import org.chromium.content.browser.webcontents.WebContentsImpl;
import org.chromium.content_public.browser.WebContents;

/* renamed from: iu1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C3115iu1 extends Pr1 implements AbstractC2603fu1, Jy1 {
    public final WebContentsImpl F;
    public Boolean G;
    public boolean H;
    public Boolean I;

    /* renamed from: J  reason: collision with root package name */
    public boolean f10171J;

    public C3115iu1(WebContents webContents) {
        this.F = (WebContentsImpl) webContents;
    }

    public static C3115iu1 c(WebContents webContents) {
        return (C3115iu1) ((WebContentsImpl) webContents).v0(C3115iu1.class, AbstractC2945hu1.f10108a);
    }

    public void e() {
        Zy1 t0 = Zy1.t0(this.F);
        t0.I = true;
        t0.s0();
        Iterator it = t0.F.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((Wy1) uq0.next()).onAttachedToWindow();
            } else {
                return;
            }
        }
    }

    @Override // defpackage.Jy1
    public void f() {
        if (!this.H) {
            this.H = true;
            j();
        }
    }

    @Override // defpackage.Jy1
    public void g() {
        if (this.H) {
            this.H = false;
            j();
        }
    }

    public void h() {
        Zy1 t0 = Zy1.t0(this.F);
        t0.u0();
        t0.I = false;
        Iterator it = t0.F.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((Wy1) uq0.next()).onDetachedFromWindow();
            } else {
                return;
            }
        }
    }

    public final void j() {
        Boolean bool = this.G;
        if (bool != null) {
            boolean z = bool.booleanValue() && !this.H;
            Boolean bool2 = this.I;
            if (bool2 == null || bool2.booleanValue() != z) {
                this.I = Boolean.valueOf(z);
                WebContentsImpl webContentsImpl = this.F;
                if (webContentsImpl != null) {
                    Zy1 t0 = Zy1.t0(webContentsImpl);
                    boolean booleanValue = this.I.booleanValue();
                    boolean z2 = this.f10171J;
                    Iterator it = t0.F.iterator();
                    while (true) {
                        C1261Uq0 uq0 = (C1261Uq0) it;
                        if (!uq0.hasNext()) {
                            break;
                        }
                        ((Wy1) uq0.next()).S(booleanValue, z2);
                    }
                    WebContentsImpl webContentsImpl2 = this.F;
                    boolean booleanValue2 = this.I.booleanValue();
                    long j = webContentsImpl2.H;
                    if (j != 0) {
                        N.M9QxNoTJ(j, webContentsImpl2, booleanValue2);
                    }
                }
            }
        }
    }

    public void k(boolean z) {
        Iterator it = Zy1.t0(this.F).F.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((Wy1) uq0.next()).onWindowFocusChanged(z);
            } else {
                return;
            }
        }
    }
}
