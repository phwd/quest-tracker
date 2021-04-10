package defpackage;

import J.N;
import android.content.Context;
import android.content.res.Configuration;
import android.view.View;
import com.oculus.browser.R;
import java.util.Iterator;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: yT0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5768yT0 implements AbstractC0639Kk, AbstractC3464kx {
    public final Context F;
    public final DU0 G;
    public final AbstractC0956Pq0 H;
    public M2 I;

    /* renamed from: J  reason: collision with root package name */
    public C1595a3 f11684J;
    public C0517Ik K;
    public C1322Vq0 L = new C1322Vq0();
    public View.OnClickListener M;
    public C2746gl0 N;
    public AbstractC2404el0 O;
    public Integer P;
    public int Q;

    public C5768yT0(Context context, C1595a3 a3Var, AbstractC0956Pq0 pq0, DU0 du0, M2 m2, C2746gl0 gl0, Runnable runnable) {
        this.F = context;
        this.I = m2;
        m2.a(this);
        this.f11684J = a3Var;
        this.G = du0;
        this.H = pq0;
        this.M = new View$OnClickListenerC5428wT0(this, runnable);
        C5598xT0 xt0 = new C5598xT0(this);
        this.O = xt0;
        this.N = gl0;
        gl0.h.b(xt0);
        this.K = new C0517Ik(false, AbstractC5544x8.a(context, R.drawable.f32820_resource_name_obfuscated_RES_2131231322), this.M, R.string.f61520_resource_name_obfuscated_RES_2131953469, true, null, true);
        this.Q = context.getResources().getConfiguration().screenWidthDp;
    }

    @Override // defpackage.AbstractC0639Kk
    public void O(AbstractC0578Jk jk) {
        this.L.b(jk);
    }

    @Override // defpackage.AbstractC0639Kk
    public void destroy() {
        C2746gl0 gl0;
        M2 m2 = this.I;
        if (m2 != null) {
            m2.b(this);
            this.I = null;
        }
        AbstractC2404el0 el0 = this.O;
        if (el0 != null && (gl0 = this.N) != null) {
            gl0.h.c(el0);
            this.O = null;
            this.N = null;
        }
    }

    public final void f(boolean z) {
        Iterator it = this.L.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((AbstractC0578Jk) uq0.next()).a(z);
            } else {
                return;
            }
        }
    }

    public final void g(Tab tab) {
        C1595a3 a3Var;
        if (tab == null || tab.l() == null || (a3Var = this.f11684J) == null || a3Var.H == null || !N.M09VlOh_("ShareButtonInTopToolbar")) {
            this.K.f8247a = false;
            return;
        }
        if (this.P == null) {
            this.P = Integer.valueOf(N.M37SqSAy("ShareButtonInTopToolbar", "minimum_width", 360));
        }
        boolean z = this.Q > this.P.intValue();
        if (((C1078Rq0) this.H).H == null || !z) {
            this.K.f8247a = false;
        } else {
            this.K.f8247a = this.G.a(tab);
        }
    }

    @Override // defpackage.AbstractC0639Kk
    public void n(AbstractC0578Jk jk) {
        this.L.c(jk);
    }

    @Override // defpackage.AbstractC3464kx
    public void onConfigurationChanged(Configuration configuration) {
        int i = this.Q;
        int i2 = configuration.screenWidthDp;
        if (i != i2) {
            this.Q = i2;
            g(this.f11684J.H);
            f(this.K.f8247a);
        }
    }

    @Override // defpackage.AbstractC0639Kk
    public C0517Ik r(Tab tab) {
        g(tab);
        return this.K;
    }
}
