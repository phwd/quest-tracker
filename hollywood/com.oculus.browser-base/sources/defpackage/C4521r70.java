package defpackage;

import J.N;
import android.content.Context;
import android.os.SystemClock;
import android.view.ViewGroup;
import com.oculus.browser.R;
import java.util.List;
import java.util.Objects;
import org.chromium.chrome.browser.compositor.CompositorViewHolder;
import org.chromium.chrome.browser.compositor.LayerTitleCache;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tabmodel.TabModel;

/* renamed from: r70  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4521r70 extends AbstractC3838n70 {
    public C2631g31 C0;

    public C4521r70(AbstractC4692s70 s70, ViewGroup viewGroup, AbstractC0956Pq0 pq0, Q31 q31, C1570Zs0 zs0, C1570Zs0 zs02, Q31 q312) {
        super(s70, viewGroup, false, null, pq0, q31, zs0, zs02, q312);
        Context context = s70.getContext();
        CompositorViewHolder compositorViewHolder = (CompositorViewHolder) this.G;
        Objects.requireNonNull(compositorViewHolder);
        C2631g31 g31 = new C2631g31(context, this, compositorViewHolder, new C4351q70(this), q31);
        this.C0 = g31;
        g(g31);
        t(null);
    }

    @Override // defpackage.AbstractC3838n70, defpackage.D70
    public void i() {
        super.i();
        C2631g31 g31 = this.C0;
        if (g31 != null) {
            C1285Vb1 vb1 = g31.Q;
            N.MPFnESYL(vb1.F, vb1);
            vb1.G = 0;
            g31.Q = null;
            g31.L.l.removeCallbacksAndMessages(null);
            g31.K.l.removeCallbacksAndMessages(null);
            AbstractC0124Ca1 ca1 = g31.G;
            if (ca1 != null) {
                ((AbstractC0246Ea1) ca1).c.h(g31.V);
                AbstractC0124Ca1 ca12 = g31.G;
                ((AbstractC0246Ea1) ca12).f.c(g31.U);
                g31.S.destroy();
                g31.T.destroy();
            }
            this.C0 = null;
        }
    }

    @Override // defpackage.AbstractC3838n70, defpackage.D70
    public void p(AbstractC0124Ca1 ca1, AbstractC3226ja1 ja1, DA da, IJ ij) {
        AbstractC0757Mi1 mi1;
        C2631g31 g31 = this.C0;
        if (!(g31 == null || g31.G == ca1)) {
            C1597a31 a31 = new C1597a31(g31);
            g31.V = a31;
            AbstractC0246Ea1 ea1 = (AbstractC0246Ea1) ca1;
            ea1.c.a(a31);
            g31.G = ca1;
            if (ea1.h) {
                g31.h();
            } else {
                ea1.c(new C1948c31(g31));
            }
            X21 x21 = g31.K;
            TabModel l = ((AbstractC0246Ea1) g31.G).l(false);
            A61 S = ja1.S(false);
            if (x21.d != l) {
                x21.d = l;
                x21.e = S;
                x21.c(false);
            }
            X21 x212 = g31.L;
            TabModel l2 = ((AbstractC0246Ea1) g31.G).l(true);
            A61 S2 = ja1.S(true);
            if (x212.d != l2) {
                x212.d = l2;
                x212.e = S2;
                x212.c(false);
            }
            g31.g(((AbstractC0246Ea1) g31.G).r());
            g31.S = new C2119d31(g31, ca1);
            g31.T = new C2289e31(g31, ca1);
            ((AbstractC0246Ea1) g31.G).c(g31.U);
        }
        super.p(ca1, ja1, da, ij);
        List list = ((AbstractC0246Ea1) ca1).f7969a;
        for (int i = 0; i < list.size(); i++) {
            TabModel tabModel = (TabModel) list.get(i);
            for (int i2 = 0; i2 < tabModel.getCount(); i2++) {
                Tab tabAt = tabModel.getTabAt(i2);
                if (!(tabAt == null || (mi1 = this.v0) == null)) {
                    ((LayerTitleCache) mi1).a(tabAt, this.G.getContext().getString(R.string.f63150_resource_name_obfuscated_RES_2131953632));
                }
            }
        }
    }

    @Override // defpackage.D70
    public void v(int i, boolean z) {
        AbstractC2300e70 e70 = this.S;
        if (e70 != null) {
            e70.D(SystemClock.uptimeMillis(), i, z);
        }
        AbstractC0757Mi1 mi1 = this.v0;
        if (mi1 != null) {
            ((LayerTitleCache) mi1).b(i);
        }
    }

    @Override // defpackage.AbstractC3838n70, defpackage.D70
    public void w(int i, int i2, int i3, boolean z, boolean z2, float f, float f2) {
        if (k() != null) {
            k().G.s();
        }
        super.w(i, i2, i3, z, z2, f, f2);
    }

    @Override // defpackage.D70
    public void y(boolean z) {
        AbstractC2300e70 e70 = this.S;
        if (e70 != null) {
            e70.G(z);
        }
        this.M.d();
    }
}
