package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.RectF;
import com.oculus.browser.R;
import java.util.ArrayList;
import org.chromium.chrome.browser.compositor.LayerTitleCache;
import org.chromium.chrome.browser.compositor.layouts.content.TabContentManager;
import org.chromium.chrome.browser.layouts.scene_layer.SceneLayer;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tabmodel.TabModel;
import org.chromium.ui.resources.ResourceManager;

/* renamed from: vl1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5306vl1 extends AbstractC2300e70 {
    public J70 V;
    public J70 W;
    public J70 X;
    public J70 Y;
    public boolean Z;
    public float a0;
    public float b0;
    public float c0;
    public final float d0;
    public final float e0;
    public final C0813Nh f0;
    public final Q91 g0 = new Q91();

    public C5306vl1(Context context, K70 k70, F70 f70) {
        super(context, k70, f70);
        this.f0 = new C0813Nh(context);
        Resources resources = context.getResources();
        float f = 1.0f / resources.getDisplayMetrics().density;
        this.e0 = resources.getDimension(R.dimen.f26410_resource_name_obfuscated_RES_2131166260) * f;
        this.d0 = resources.getDimension(R.dimen.f26420_resource_name_obfuscated_RES_2131166261) * f;
    }

    @Override // defpackage.AbstractC2300e70
    public void P(long j, boolean z) {
        int k;
        this.Q = false;
        this.R = true;
        this.S = -1;
        this.P = null;
        this.X = null;
        this.V = null;
        this.W = null;
        this.Y = null;
        this.a0 = 0.0f;
        this.b0 = 0.0f;
        this.c0 = 0.0f;
        AbstractC0124Ca1 ca1 = this.L;
        if (ca1 != null) {
            Tab j2 = ((AbstractC0246Ea1) ca1).j();
            if (j2 != null && j2.isNativePage()) {
                this.M.b(j2);
            }
            TabModel i = ((AbstractC0246Ea1) this.L).i();
            if (i != null && (k = ((AbstractC0246Ea1) this.L).k()) != -1) {
                J70 d = d(k, i.a(), false, true);
                this.X = d;
                Y(d, false);
            }
        }
    }

    @Override // defpackage.AbstractC2300e70
    public void T(long j, long j2) {
        float f;
        float f2;
        J70 j70 = this.X;
        if (j70 != null) {
            if (this.V == null && this.W == null) {
                this.W = j70;
            }
            float f3 = this.b0;
            float f4 = this.c0;
            float e = AbstractC4089od0.e(AbstractC4089od0.b(f3, f4 - 30.0f, 30.0f + f4), f4, 0.8f);
            this.b0 = e;
            boolean z = true;
            boolean z2 = Math.abs(e - this.c0) >= 0.1f;
            if ((this.V != null) ^ (this.W != null)) {
                float f5 = this.b0 / this.F;
                f = Math.signum(f5) * G30.f8058a.getInterpolation(Math.abs(f5)) * (this.F / 5.0f);
                f2 = f;
            } else {
                float e2 = AbstractC4089od0.e(0.0f, this.F + this.d0, AbstractC4089od0.b((this.b0 / this.F) + (this.a0 == 0.0f ? 0.0f : 1.0f), 0.0f, 1.0f));
                float min = (e2 - this.d0) - Math.min(this.F, this.V.t());
                float f6 = this.F / 2.0f;
                f2 = Math.max(f6 - (this.W.p() / 2.0f), e2);
                f = Math.min(f6 - (this.V.p() / 2.0f), min);
            }
            J70 j702 = this.V;
            if (j702 != null) {
                j702.k(J70.l, f);
                z2 = W(j2, this.V) || z2;
            }
            J70 j703 = this.W;
            if (j703 != null) {
                j703.k(J70.l, f2);
                if (!W(j2, this.W) && !z2) {
                    z = false;
                }
                z2 = z;
            }
            if (z2) {
                M();
            }
        }
    }

    @Override // defpackage.AbstractC2300e70
    public void U(RectF rectF, RectF rectF2, LayerTitleCache layerTitleCache, TabContentManager tabContentManager, ResourceManager resourceManager, AbstractC2400ek ekVar) {
        this.g0.d(this.f9833J, rectF2, this, layerTitleCache, tabContentManager, resourceManager, ekVar, -1, 0.0f, 0);
    }

    public final void X(float f, float f2, long j) {
        if (j > 0) {
            C5677xw c = C5677xw.c(m(), f, f2, j, null);
            c.I.add(new C5136ul1(this));
            c.start();
        }
    }

    public final void Y(J70 j70, boolean z) {
        if (j70.h(J70.F)) {
            j70.k(J70.u, 0.0f);
        }
        j70.G(j70.t(), j70.s());
        j70.k(J70.g, 1.0f);
        j70.k(J70.x, 1.0f);
        j70.k(J70.Q, 0.0f);
        j70.k(J70.m, 0.0f);
        j70.j(J70.H, this.Z);
        j70.j(J70.I, z);
    }

    public final void Z(int i, int i2, int i3) {
        int i4;
        int i5;
        float f;
        J70 j70;
        boolean z = i == 2;
        int i6 = z ? i3 : i2;
        if (z) {
            i3 = i2;
        }
        TabModel i7 = ((AbstractC0246Ea1) this.L).i();
        if (i6 < 0 || i6 >= i7.getCount()) {
            i4 = -1;
        } else {
            i4 = i7.getTabAt(i6).getId();
            J70 d = d(i4, i7.a(), false, true);
            this.V = d;
            Y(d, i6 != i2);
        }
        if (i3 < 0 || i3 >= i7.getCount()) {
            i5 = -1;
        } else {
            i5 = i7.getTabAt(i3).getId();
            J70 d2 = d(i5, i7.a(), false, true);
            this.W = d2;
            Y(d2, i3 != i2);
        }
        int i8 = z ? i5 : i4;
        if (!z) {
            i4 = i5;
        }
        ArrayList arrayList = new ArrayList();
        if (i4 != -1) {
            arrayList.add(Integer.valueOf(i4));
        }
        if (i8 != -1) {
            arrayList.add(Integer.valueOf(i8));
        }
        TabContentManager tabContentManager = this.M;
        if (tabContentManager != null) {
            tabContentManager.k(arrayList, -1);
        }
        this.Y = null;
        if (z) {
            f = 0.0f;
        } else {
            f = this.F;
        }
        this.a0 = f;
        this.b0 = 0.0f;
        this.c0 = 0.0f;
        J70 j702 = this.V;
        if (j702 != null && (j70 = this.W) != null) {
            this.P = new J70[]{j702, j70};
        } else if (j702 != null) {
            this.P = new J70[]{j702};
        } else {
            J70 j703 = this.W;
            if (j703 != null) {
                this.P = new J70[]{j703};
            } else {
                this.P = null;
            }
        }
        M();
    }

    public void a0(float f) {
        this.c0 = AbstractC4089od0.b(this.a0 + f, 0.0f, this.F) - this.a0;
        M();
    }

    @Override // defpackage.AbstractC2300e70
    public boolean k() {
        return this.Z;
    }

    @Override // defpackage.AbstractC2300e70
    public VL n() {
        return this.f0;
    }

    @Override // defpackage.AbstractC2300e70
    public int p() {
        return 2;
    }

    @Override // defpackage.AbstractC2300e70
    public SceneLayer q() {
        return this.g0;
    }

    @Override // defpackage.AbstractC2300e70
    public int r() {
        return !this.Z ? 1 : 0;
    }
}
