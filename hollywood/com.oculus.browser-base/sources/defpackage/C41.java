package defpackage;

import android.content.Context;
import android.view.ViewGroup;
import com.oculus.browser.R;
import org.chromium.base.ThreadUtils;
import org.chromium.base.TraceEvent;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.OverscrollRefreshHandler;

/* renamed from: C41  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C41 extends AbstractC0499Id1 implements OverscrollRefreshHandler {
    public int G;
    public K41 H;
    public Tab I;

    /* renamed from: J  reason: collision with root package name */
    public WK f7782J;
    public ViewGroup K;
    public Runnable L;
    public Runnable M;
    public String N;
    public FX O;

    public C41(Tab tab) {
        super(tab);
        this.I = tab;
        B41 b41 = new B41(this);
        this.f7782J = b41;
        tab.A(b41);
    }

    public static C41 l(Tab tab) {
        C41 c41 = (C41) tab.M().c(C41.class);
        return c41 == null ? (C41) tab.M().e(C41.class, new C41(tab)) : c41;
    }

    public static C41 m(Tab tab) {
        return (C41) tab.M().c(C41.class);
    }

    @Override // defpackage.AbstractC0499Id1
    public void c(WebContents webContents) {
        k();
        this.K = null;
        this.O = null;
        n(false);
    }

    @Override // defpackage.AbstractC0499Id1
    public void e() {
        K41 k41 = this.H;
        if (k41 != null) {
            k41.G = null;
            k41.H = null;
        }
    }

    @Override // defpackage.AbstractC0499Id1
    public void h(WebContents webContents) {
        webContents.p(this);
        this.K = this.I.u();
        n(true);
    }

    public final void j() {
        if (this.L != null) {
            ThreadUtils.b().removeCallbacks(this.L);
        }
    }

    public final void k() {
        if (this.H != null) {
            if (this.M != null) {
                ThreadUtils.b().removeCallbacks(this.M);
                this.M = null;
            }
            if (this.H.getParent() != null) {
                this.K.removeView(this.H);
            }
        }
    }

    public void n(boolean z) {
        C0887Om0 om0;
        if (!z) {
            j();
            K41 k41 = this.H;
            if (k41 != null) {
                k41.e();
            }
            FX fx = this.O;
            if (fx != null && (om0 = fx.S) != null) {
                om0.c();
            }
        }
    }

    @Override // org.chromium.ui.OverscrollRefreshHandler
    public void pull(float f, float f2) {
        FX fx;
        C0887Om0 om0;
        TraceEvent.Y("SwipeRefreshHandler.pull", null);
        int i = this.G;
        if (i == 1) {
            this.H.c(f2);
        } else if (!(i != 2 || (fx = this.O) == null || (om0 = fx.S) == null)) {
            om0.a(f);
        }
        TraceEvent.f0("SwipeRefreshHandler.pull");
    }

    @Override // org.chromium.ui.OverscrollRefreshHandler
    public void release(boolean z) {
        FX fx;
        C0887Om0 om0;
        TraceEvent.Y("SwipeRefreshHandler.release", null);
        int i = this.G;
        if (i == 1) {
            this.H.d(z);
        } else if (!(i != 2 || (fx = this.O) == null || (om0 = fx.S) == null)) {
            om0.b(z);
        }
        TraceEvent.f0("SwipeRefreshHandler.release");
    }

    @Override // org.chromium.ui.OverscrollRefreshHandler
    public void reset() {
        C0887Om0 om0;
        j();
        K41 k41 = this.H;
        if (k41 != null) {
            k41.e();
        }
        FX fx = this.O;
        if (fx != null && (om0 = fx.S) != null) {
            om0.c();
        }
    }

    @Override // org.chromium.ui.OverscrollRefreshHandler
    public boolean start(int i, float f, float f2, boolean z) {
        FX fx;
        this.G = i;
        if (i == 1) {
            if (this.H == null) {
                Context context = this.I.getContext();
                K41 k41 = new K41(context);
                this.H = k41;
                k41.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
                K41 k412 = this.H;
                int color = k412.getResources().getColor(R.color.f10950_resource_name_obfuscated_RES_2131099785);
                k412.P.setBackgroundColor(color);
                k412.T.f9935J.w = color;
                this.H.f(R.color.f11100_resource_name_obfuscated_RES_2131099800);
                if (this.K != null) {
                    this.H.setEnabled(true);
                }
                K41 k413 = this.H;
                k413.G = new C5534x41(this, context);
                k413.H = new C5704y41(this);
            }
            if (this.M != null) {
                ThreadUtils.b().removeCallbacks(this.M);
                this.M = null;
            }
            if (this.H.getParent() == null) {
                this.K.addView(this.H);
            }
            return this.H.k();
        } else if (i != 2 || (fx = this.O) == null) {
            this.G = 0;
            return false;
        } else {
            C0887Om0 om0 = fx.S;
            if (om0 != null) {
                om0.i = 1;
            }
            return (z && !this.I.k()) || (om0 != null && om0.d(z, f, f2));
        }
    }
}
