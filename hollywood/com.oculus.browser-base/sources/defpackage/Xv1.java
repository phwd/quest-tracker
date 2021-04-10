package defpackage;

import J.N;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import com.oculus.browser.R;
import java.util.Iterator;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: Xv1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Xv1 implements AbstractC0639Kk, AbstractC3464kx {
    public final Q31 F;
    public final M2 G;
    public final C2746gl0 H;
    public final AbstractC2404el0 I;

    /* renamed from: J  reason: collision with root package name */
    public final BN0 f9243J;
    public final C0517Ik K;
    public final C1322Vq0 L = new C1322Vq0();
    public Integer M;
    public int N;

    public Xv1(Context context, Drawable drawable, Q31 q31, M2 m2, C2746gl0 gl0, BN0 bn0) {
        this.F = q31;
        this.G = m2;
        m2.a(this);
        Wv1 wv1 = new Wv1(this);
        this.I = wv1;
        this.H = gl0;
        gl0.h.b(wv1);
        this.f9243J = bn0;
        this.K = new C0517Ik(false, drawable, new Vv1(this), R.string.f46260_resource_name_obfuscated_RES_2131951943, true, null, true);
        this.N = context.getResources().getConfiguration().screenWidthDp;
    }

    @Override // defpackage.AbstractC0639Kk
    public void O(AbstractC0578Jk jk) {
        this.L.b(jk);
    }

    @Override // defpackage.AbstractC0639Kk
    public void destroy() {
        this.G.b(this);
        C2746gl0 gl0 = this.H;
        gl0.h.c(this.I);
        this.L.clear();
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

    public final boolean g(Tab tab) {
        boolean z;
        if (AbstractC4226pO.a() && N.M09VlOh_("VoiceButtonInTopToolbar") && tab != null && !tab.a()) {
            Sv1 C = this.f9243J.f7733a.V.d0.C();
            if (C == null) {
                z = false;
            } else {
                z = C.b();
            }
            if (z) {
                if (this.M == null) {
                    this.M = Integer.valueOf(N.M37SqSAy("VoiceButtonInTopToolbar", "minimum_width_dp", 360));
                }
                if (!(this.N >= this.M.intValue())) {
                    return false;
                }
                return AbstractC5154ur1.e(tab.getUrl());
            }
        }
        return false;
    }

    @Override // defpackage.AbstractC0639Kk
    public void n(AbstractC0578Jk jk) {
        this.L.c(jk);
    }

    @Override // defpackage.AbstractC3464kx
    public void onConfigurationChanged(Configuration configuration) {
        int i = this.N;
        int i2 = configuration.screenWidthDp;
        if (i != i2) {
            this.N = i2;
            this.K.f8247a = g((Tab) this.F.get());
            f(this.K.f8247a);
        }
    }

    @Override // defpackage.AbstractC0639Kk
    public C0517Ik r(Tab tab) {
        this.K.f8247a = g(tab);
        return this.K;
    }
}
