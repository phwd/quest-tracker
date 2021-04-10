package defpackage;

import J.N;
import android.app.Activity;
import android.view.View;
import java.util.ArrayList;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.WebContents;

/* renamed from: m10  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3649m10 extends Pr1 implements AbstractC3322k60 {
    public static final W F;
    public final AbstractC1404Xa1 G;
    public final View.OnAttachStateChangeListener H = new View$OnAttachStateChangeListenerC2795h10(this);
    public final ArrayList I = new ArrayList();

    /* renamed from: J  reason: collision with root package name */
    public final C1322Vq0 f10392J = new C1322Vq0();
    public final C1322Vq0 K = new C1322Vq0();
    public final C2966i10 L = new C2966i10(this);
    public final Tab M;
    public long N;
    public boolean O;
    public boolean P;
    public View Q;
    public B10 R;
    public C1856bZ S;
    public AbstractC0576Jj T;
    public AbstractC4448qj U;

    static {
        C2453f10 f10 = new C2453f10();
        F = f10;
        C0283Ep h = C0283Ep.h();
        h.c().b(f10);
        f10.l(h.d());
    }

    public C3649m10(Tab tab) {
        C2624g10 g10 = new C2624g10(this);
        this.G = g10;
        tab.A(g10);
        this.Q = tab.b();
        this.M = tab;
        k(j(tab));
        this.N = N.MQNiH$D1(this);
    }

    public static void c(C3649m10 m10) {
        if (m10.R != null) {
            WebContents l = m10.M.l();
            if (l != null) {
                B10 b10 = m10.R;
                if (l != b10.O) {
                    b10.d(l);
                    long j = m10.N;
                    if (j != 0) {
                        N.Mb3PR8J$(j, m10, l);
                    }
                }
            }
            View view = m10.Q;
            if (view != null) {
                view.removeOnAttachStateChangeListener(m10.H);
            }
            View b = m10.M.b();
            m10.Q = b;
            if (b != null) {
                b.addOnAttachStateChangeListener(m10.H);
            }
        }
    }

    public static C3649m10 h(Tab tab) {
        return (C3649m10) tab.M().c(C3649m10.class);
    }

    public static ChromeActivity j(Tab tab) {
        Activity activity = (Activity) tab.i().s0().get();
        if (activity instanceof ChromeActivity) {
            return (ChromeActivity) activity;
        }
        return null;
    }

    @Override // defpackage.AbstractC3322k60
    public void b(boolean z) {
        boolean z2 = this.R.getVisibility() == 0;
        if (z) {
            if (z2) {
                this.R.setVisibility(4);
            }
        } else if (!z2 && !this.P) {
            this.R.setVisibility(0);
        }
    }

    @Override // defpackage.Qr1, defpackage.Pr1
    public void destroy() {
        e();
        this.M.I(this.G);
        long j = this.N;
        if (j != 0) {
            N.MUX1cETi(j, this);
            this.N = 0;
        }
        this.O = true;
    }

    public final void e() {
        AbstractC0576Jj jj;
        C1856bZ bZVar = this.S;
        if (bZVar != null) {
            this.K.c(bZVar);
            this.f10392J.c(this.S);
            this.S = null;
        }
        B10 b10 = this.R;
        if (b10 != null) {
            b10.d(null);
            long j = this.N;
            if (j != 0) {
                N.Mb3PR8J$(j, this, null);
            }
            this.R.b();
            this.R = null;
        }
        if (!(j(this.M) == null || (jj = this.T) == null)) {
            ((C5638xj) this.U).r(jj);
        }
        this.M.i().u0().h(this);
        View view = this.Q;
        if (view != null) {
            view.removeOnAttachStateChangeListener(this.H);
            this.Q = null;
        }
    }

    public final void k(ChromeActivity chromeActivity) {
        int i = 0;
        B10 b10 = new B10(ContextUtils.getApplicationContext(), this.L, null, false);
        this.R = b10;
        b10.addOnAttachStateChangeListener(new View$OnAttachStateChangeListenerC3307k10(this));
        B10 b102 = this.R;
        if (this.P) {
            i = 8;
        }
        b102.setVisibility(i);
        C1856bZ bZVar = new C1856bZ(new WY(chromeActivity, this.M));
        this.S = bZVar;
        this.K.b(bZVar);
        this.f10392J.b(this.S);
        this.M.i().u0().a(this);
    }

    public void l(boolean z) {
        this.P = z;
        B10 b10 = this.R;
        if (b10 != null) {
            b10.setVisibility(z ? 8 : 0);
            if (z) {
                B10 b102 = this.R;
                if (b102 != null) {
                    b102.setVisibility(8);
                    return;
                }
                return;
            }
            B10 b103 = this.R;
            if (b103 != null) {
                b103.setVisibility(0);
            }
        }
    }
}
