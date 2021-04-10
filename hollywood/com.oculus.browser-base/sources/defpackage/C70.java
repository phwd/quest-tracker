package defpackage;

import android.os.SystemClock;
import java.util.Objects;
import org.chromium.chrome.browser.compositor.CompositorViewHolder;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.ui.base.LocalizationUtils;

/* renamed from: C70  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C70 extends AbstractC5783ya1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ D70 f7787a;

    public C70(D70 d70) {
        this.f7787a = d70;
    }

    @Override // defpackage.AbstractC5783ya1
    public void A(int i, boolean z) {
        D70.f(this.f7787a, i, z, false);
    }

    @Override // defpackage.AbstractC5783ya1
    public void C(Tab tab, int i, int i2) {
        if (i == 4) {
            AbstractC3838n70 n70 = (AbstractC3838n70) this.f7787a;
            Objects.requireNonNull(n70);
            if (tab == null || i2 == -1) {
                int id = tab.getId();
                boolean a2 = tab.a();
                AbstractC2300e70 e70 = n70.S;
                if (e70 != null) {
                    e70.I(SystemClock.uptimeMillis(), id, i2, a2);
                    return;
                }
                return;
            }
            n70.F(n70.s0, false);
            C5306vl1 vl1 = n70.s0;
            int id2 = tab.getId();
            int e = AbstractC1160Ta1.e(((AbstractC0246Ea1) vl1.L).i(), i2);
            int e2 = AbstractC1160Ta1.e(((AbstractC0246Ea1) vl1.L).i(), id2);
            vl1.Z(e < e2 ? 1 : 2, e, e2);
            vl1.Y = e < e2 ? vl1.W : vl1.V;
            float f = e < e2 ? -vl1.F : vl1.F;
            vl1.N.e(id2, false);
            vl1.Q = true;
            vl1.S = id2;
            vl1.X(0.0f, f, 350);
            Tab z = n70.z(i2);
            if (AbstractC5154ur1.h(z.getUrl()) && !z.h() && !z.k()) {
                ((AbstractC0246Ea1) n70.M).l(z.a()).e(z, tab, false, false, false);
            }
        } else if (tab.getId() != i2) {
            D70 d70 = this.f7787a;
            int id3 = tab.getId();
            boolean a3 = tab.a();
            AbstractC2300e70 e702 = d70.S;
            if (e702 != null) {
                e702.I(SystemClock.uptimeMillis(), id3, i2, a3);
            }
        }
    }

    @Override // defpackage.AbstractC5783ya1
    public void F(Tab tab) {
        this.f7787a.v(tab.getId(), tab.a());
    }

    @Override // defpackage.AbstractC5783ya1
    public void H(Tab tab) {
        D70.f(this.f7787a, tab.getId(), tab.a(), false);
    }

    @Override // defpackage.AbstractC5783ya1
    public void I(Tab tab) {
        D70.f(this.f7787a, tab.getId(), tab.a(), true);
    }

    @Override // defpackage.AbstractC5783ya1
    public void J(Tab tab, int i) {
        if (i != 3 && i != 6 && i != 1 && i != 7 && i != 11) {
            D70 d70 = this.f7787a;
            d70.x(((AbstractC0246Ea1) d70.M).k(), tab.s(), tab.a());
        }
    }

    @Override // defpackage.AbstractC5783ya1
    public void z(Tab tab, int i, int i2) {
        float f;
        float f2;
        int id = tab.getId();
        if (i == 3) {
            this.f7787a.S.H(SystemClock.uptimeMillis(), id);
            return;
        }
        boolean a2 = tab.a();
        boolean z = i != 5 || (!((AbstractC0246Ea1) this.f7787a.M).r() && a2);
        float width = LocalizationUtils.isLayoutRtl() ? ((float) this.f7787a.G.getWidth()) * this.f7787a.F : 0.0f;
        if (i != 2) {
            CompositorViewHolder compositorViewHolder = (CompositorViewHolder) this.f7787a.G;
            float height = (float) (compositorViewHolder.getHeight() - (compositorViewHolder.c() + compositorViewHolder.m()));
            D70 d70 = this.f7787a;
            float f3 = d70.F;
            float f4 = ((float) d70.I) * f3;
            f = (f3 * ((float) d70.f7867J)) - (height * f3);
            f2 = f4;
        } else {
            f2 = width;
            f = 0.0f;
        }
        D70 d702 = this.f7787a;
        d702.w(id, ((AbstractC0246Ea1) d702.M).k(), i, a2, z, f2, f);
    }
}
