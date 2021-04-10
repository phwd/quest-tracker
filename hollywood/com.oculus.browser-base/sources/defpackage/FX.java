package defpackage;

import android.graphics.Insets;
import android.graphics.Rect;
import android.os.Build;
import android.view.ViewGroup;
import java.util.Objects;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.WebContents;

/* renamed from: FX  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class FX implements V10, AbstractC0850Ny0 {
    public final Runnable F = new RunnableC4925tX(this);
    public ViewGroup G;
    public MX H;
    public W10 I;

    /* renamed from: J  reason: collision with root package name */
    public Y2 f8020J;
    public M2 K;
    public WT L;
    public Runnable M;
    public AbstractC3467ky N;
    public String O;
    public Q31 P;
    public Tab Q;
    public boolean R;
    public C0887Om0 S;
    public HX T;
    public AbstractC1497Ym0 U;
    public C1572Zt0 V;
    public Runnable W;
    public Runnable X;

    public static boolean h(Tab tab) {
        return tab.l() == null || tab.l().I() == null;
    }

    @Override // defpackage.AbstractC0850Ny0
    public void b() {
        this.G.post(this.F);
    }

    @Override // defpackage.AbstractC0850Ny0
    public void c() {
    }

    @Override // defpackage.V10
    public void f(Rect rect) {
    }

    @Override // defpackage.V10
    public void g(int i, int i2, int i3, int i4) {
        j();
    }

    public final boolean i() {
        if (Build.VERSION.SDK_INT < 29) {
            return true;
        }
        ViewGroup viewGroup = this.G;
        if (viewGroup == null || !viewGroup.isAttachedToWindow()) {
            return this.R;
        }
        Insets systemGestureInsets = this.G.getRootWindowInsets().getSystemGestureInsets();
        if (systemGestureInsets.left == 0 && systemGestureInsets.right == 0) {
            return true;
        }
        return false;
    }

    public final void j() {
        boolean z = this.R;
        boolean i = i();
        this.R = i;
        if (i != z) {
            k();
        }
    }

    public final void k() {
        HX hx;
        if (this.R) {
            Tab tab = this.Q;
            WebContents l = tab != null ? tab.l() : null;
            Tab tab2 = this.Q;
            if (tab2 == null || l != null) {
                if (l != null) {
                    WT wt = this.L;
                    Runnable runnable = this.M;
                    AbstractC3467ky kyVar = this.N;
                    String str = this.O;
                    Q31 q31 = this.P;
                    if (h(tab2)) {
                        hx = HX.f8161a;
                    } else {
                        hx = new EX(tab2, wt, runnable, kyVar, str, q31);
                    }
                } else {
                    hx = HX.f8161a;
                }
                if (this.S == null) {
                    UH0 uh0 = new UH0(UH0.c(AbstractC5261vV.h), null);
                    ZH0.a(uh0, this.H, new AX());
                    this.S = new C0887Om0(uh0, this.H, new BX(this), new CX(this));
                    this.W.run();
                }
                if (this.T != hx) {
                    C0887Om0 om0 = this.S;
                    Objects.requireNonNull(om0);
                    om0.d = hx.b();
                    this.T = hx;
                    if (l != null) {
                        Profile.a(l);
                    } else {
                        Profile.b();
                    }
                    this.U = AbstractC1497Ym0.u;
                    this.T.a();
                }
            }
        }
        Tab tab3 = this.Q;
        if (tab3 != null) {
            C41.l(tab3).O = this;
        }
    }
}
