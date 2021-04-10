package defpackage;

import J.N;
import android.content.Context;
import android.os.SystemClock;
import android.util.SparseArray;
import android.view.ViewGroup;
import java.util.Objects;
import org.chromium.chrome.browser.compositor.CompositorViewHolder;
import org.chromium.chrome.browser.compositor.LayerTitleCache;
import org.chromium.chrome.browser.compositor.layouts.content.TabContentManager;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: p70  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4180p70 extends AbstractC3838n70 {
    public AW0 C0;

    public C4180p70(AbstractC4692s70 s70, ViewGroup viewGroup, AbstractC2451f01 f01, AbstractC0956Pq0 pq0, Q31 q31, C1570Zs0 zs0, C1570Zs0 zs02, Q31 q312) {
        super(s70, viewGroup, true, f01, pq0, q31, zs0, zs02, q312);
    }

    @Override // defpackage.AbstractC0063Ba1, defpackage.AbstractC3838n70
    public boolean a(boolean z) {
        AbstractC2300e70 e70;
        if (this.S == this.K && !z) {
            if (C5052uE.a()) {
                e70 = this.r0;
            } else {
                e70 = this.t0;
            }
            F(e70, false);
        }
        return super.a(z);
    }

    @Override // defpackage.K70, defpackage.D70
    public void d(int i) {
        ((LayerTitleCache) this.v0).b(i);
        this.V.remove(i);
    }

    @Override // defpackage.D70
    public C70 h() {
        return new C4009o70(this);
    }

    @Override // defpackage.D70
    public void j(int i) {
        J70 j70 = (J70) this.V.get(i);
        this.V.clear();
        if (j70 != null) {
            this.V.put(i, j70);
        }
        AbstractC0757Mi1 mi1 = this.v0;
        if (mi1 != null) {
            LayerTitleCache layerTitleCache = (LayerTitleCache) mi1;
            C2130d70 d70 = (C2130d70) layerTitleCache.d.get(i);
            for (int i2 = 0; i2 < layerTitleCache.d.size(); i2++) {
                SparseArray sparseArray = layerTitleCache.d;
                C2130d70 d702 = (C2130d70) sparseArray.get(sparseArray.keyAt(i2));
                if (d702 != d70) {
                    d702.a();
                }
            }
            layerTitleCache.d.clear();
            C3713mO mOVar = layerTitleCache.i;
            mOVar.f10418a = null;
            mOVar.b = null;
            mOVar.c = null;
            mOVar.d = null;
            if (d70 != null) {
                layerTitleCache.d.put(i, d70);
            }
            long j = layerTitleCache.f;
            if (j != 0) {
                N.MearVShy(j, layerTitleCache, i);
            }
        }
    }

    @Override // defpackage.AbstractC3838n70, defpackage.D70
    public void p(AbstractC0124Ca1 ca1, AbstractC3226ja1 ja1, DA da, IJ ij) {
        Context context = this.G.getContext();
        CompositorViewHolder compositorViewHolder = (CompositorViewHolder) this.G;
        Objects.requireNonNull(compositorViewHolder);
        this.C0 = new AW0(context, this, compositorViewHolder);
        super.p(ca1, ja1, da, ij);
        this.K.V = false;
        this.s0.Z = true;
        AW0 aw0 = this.C0;
        aw0.L = ca1;
        aw0.N((TabContentManager) ((C1078Rq0) this.A0).H);
    }

    @Override // defpackage.D70
    public void u(int i, int i2, boolean z, boolean z2) {
        AbstractC2300e70 e70;
        boolean z3 = true;
        boolean z4 = i2 == -1;
        if (C5052uE.a()) {
            e70 = this.r0;
        } else {
            e70 = this.t0;
        }
        if (this.S != e70 && z4) {
            t(e70);
        }
        this.S.B(SystemClock.uptimeMillis(), i, i2, z);
        Tab z5 = z(i2);
        if (!(z5 == null || z5.b() == null)) {
            z5.b().requestFocus();
        }
        if (z2 || !this.w0) {
            z3 = false;
        }
        if (this.S != e70 && z4 && !z3) {
            E(false);
        }
    }

    @Override // defpackage.AbstractC3838n70, defpackage.D70
    public void w(int i, int i2, int i3, boolean z, boolean z2, float f, float f2) {
        Tab d;
        super.w(i, i2, i3, z, z2, f, f2);
        if (z2 && (d = AbstractC1160Ta1.d(((AbstractC0246Ea1) this.M).l(z), i)) != null && d.b() != null) {
            d.b().requestFocus();
        }
    }

    @Override // defpackage.D70
    public void x(int i, String str, boolean z) {
        J70[] j70Arr;
        AbstractC2300e70 e70 = this.S;
        if (e70 != null && !e70.Q) {
            boolean z2 = true;
            if (e70 != null && (j70Arr = e70.P) != null && j70Arr.length == 1) {
                int i2 = 0;
                while (true) {
                    if (i2 >= this.m0.size()) {
                        break;
                    } else if (((LO0) this.m0.get(i2)).u() && ((LO0) this.m0.get(i2)).v()) {
                        e(e70.P[0].q(), false);
                        b();
                        break;
                    } else {
                        i2++;
                    }
                }
            }
            z2 = false;
            if (z2 && this.S.u()) {
                this.S.F(i);
                return;
            }
        }
        if (this.w0) {
            if (!C()) {
                AbstractC2300e70 e702 = this.S;
                if (e702 == null || !e702.Q) {
                    F(this.C0, false);
                } else {
                    t(this.C0);
                    this.S.h();
                }
            }
            AbstractC2300e70 e703 = this.S;
            if (e703 != null) {
                e703.F(i);
            }
        }
    }
}
