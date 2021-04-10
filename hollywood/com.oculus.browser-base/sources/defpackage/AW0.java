package defpackage;

import android.animation.AnimatorSet;
import android.content.Context;
import android.graphics.RectF;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import org.chromium.chrome.browser.compositor.LayerTitleCache;
import org.chromium.chrome.browser.compositor.layouts.content.TabContentManager;
import org.chromium.chrome.browser.layouts.scene_layer.SceneLayer;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tabmodel.TabModel;
import org.chromium.ui.resources.ResourceManager;

/* renamed from: AW0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AW0 extends AbstractC2300e70 {
    public C5677xw V;
    public AnimatorSet W;
    public AnimatorSet X;
    public J70 Y;
    public J70 Z;
    public final Q91 a0 = new Q91();
    public final C0813Nh b0;

    public AW0(Context context, K70 k70, F70 f70) {
        super(context, k70, f70);
        this.b0 = new C0813Nh(context);
    }

    @Override // defpackage.AbstractC2300e70
    public void B(long j, int i, int i2, boolean z) {
        if (this.Y != null) {
            TabModel m = ((AbstractC0246Ea1) this.L).m(i2);
            if (m != null) {
                J70 d = d(i2, m.a(), false, false);
                d.k(J70.Q, 0.0f);
                this.P = new J70[]{d, this.Y};
                LinkedList linkedList = new LinkedList(Arrays.asList(Integer.valueOf(i2), Integer.valueOf(this.Y.q())));
                TabContentManager tabContentManager = this.M;
                if (tabContentManager != null) {
                    tabContentManager.k(linkedList, -1);
                }
            } else {
                this.P = new J70[]{this.Y};
            }
            j();
            this.Z = this.Y;
            C5677xw c = C5677xw.c(m(), 0.0f, 0.7f * Math.min(this.F, this.G), 250, new C5774yW0(this));
            this.V = c;
            c.K = animation.InterpolatorC5286vf.f;
            c.start();
            this.Y = null;
            if (m != null) {
                this.L.e(m.a());
            }
        }
        this.N.e(i2, false);
        this.Q = true;
        this.S = i2;
    }

    @Override // defpackage.AbstractC2300e70
    public void C(long j, int i) {
        Y();
        j();
        TabModel m = ((AbstractC0246Ea1) this.L).m(i);
        if (m != null) {
            J70 d = d(i, m.a(), false, false);
            this.Y = d;
            d.k(J70.v, 0.0f);
            this.P = new J70[]{this.Y};
            LinkedList linkedList = new LinkedList(Arrays.asList(Integer.valueOf(i)));
            TabContentManager tabContentManager = this.M;
            if (tabContentManager != null) {
                tabContentManager.k(linkedList, -1);
                return;
            }
            return;
        }
        this.P = null;
        this.Y = null;
    }

    @Override // defpackage.AbstractC2300e70
    public void E(long j, int i, int i2, int i3, boolean z, boolean z2, float f, float f2) {
        char c;
        J70[] j70Arr;
        float f3;
        float f4;
        X(i3);
        if (!z2 || (j70Arr = this.P) == null || j70Arr.length <= 0) {
            J70 d = d(i, z, false, false);
            J70[] j70Arr2 = this.P;
            if (j70Arr2 == null || j70Arr2.length == 0) {
                c = 1;
                this.P = new J70[]{d};
            } else {
                c = 1;
                this.P = new J70[]{j70Arr2[0], d};
            }
            Integer[] numArr = new Integer[2];
            numArr[0] = Integer.valueOf(i);
            numArr[c] = Integer.valueOf(i3);
            LinkedList linkedList = new LinkedList(Arrays.asList(numArr));
            TabContentManager tabContentManager = this.M;
            if (tabContentManager != null) {
                tabContentManager.k(linkedList, -1);
            }
            d.k(J70.v, 0.0f);
            d.k(J70.C, 1.0f);
            j();
            C4316pw m = m();
            C5677xw f5 = C5677xw.f(m, d, J70.g, 0.0f, 1.0f, 300);
            C5677xw f6 = C5677xw.f(m, d, J70.t, 0.0f, 1.0f, 300);
            C5677xw f7 = C5677xw.f(m, d, J70.l, f, 0.0f, 300);
            C5677xw f8 = C5677xw.f(m, d, J70.m, f2, 0.0f, 300);
            AnimatorSet animatorSet = new AnimatorSet();
            this.W = animatorSet;
            animatorSet.setInterpolator(animation.InterpolatorC5286vf.e);
            this.W.playTogether(f5, f6, f7, f8);
            this.W.start();
            this.L.e(z);
            this.N.e(i, false);
            this.Q = true;
            this.S = i;
            return;
        }
        J70 d2 = d(i, z, false, true);
        J70 j70 = this.P[0];
        this.P = new J70[]{j70, d2};
        LinkedList linkedList2 = new LinkedList(Arrays.asList(Integer.valueOf(i), Integer.valueOf(i3)));
        TabContentManager tabContentManager2 = this.M;
        if (tabContentManager2 != null) {
            tabContentManager2.k(linkedList2, -1);
        }
        j();
        RH0 rh0 = J70.v;
        d2.k(rh0, 0.0f);
        float min = (Math.min(this.F, this.G) * 0.100000024f) / 2.0f;
        C4316pw m2 = m();
        ArrayList arrayList = new ArrayList(5);
        RH0 rh02 = J70.g;
        arrayList.add(C5677xw.f(m2, j70, rh02, 1.0f, 0.9f, 300));
        RH0 rh03 = J70.l;
        arrayList.add(C5677xw.f(m2, j70, rh03, 0.0f, min, 300));
        RH0 rh04 = J70.m;
        arrayList.add(C5677xw.f(m2, j70, rh04, 0.0f, min, 300));
        RH0 rh05 = J70.x;
        arrayList.add(C5677xw.f(m2, j70, rh05, 1.1111112f, 1.0f, 300));
        arrayList.add(C5677xw.f(m2, j70, rh0, 0.0f, 1.0f, 300));
        AnimatorSet animatorSet2 = new AnimatorSet();
        animation.InterpolatorC5286vf vfVar = animation.InterpolatorC5286vf.e;
        animatorSet2.setInterpolator(vfVar);
        animatorSet2.playTogether(arrayList);
        if (this.K == 1) {
            f4 = min;
            f3 = this.G * 0.5f;
        } else {
            f3 = min;
            f4 = this.F * 0.5f;
        }
        ArrayList arrayList2 = new ArrayList(4);
        RH0 rh06 = J70.t;
        arrayList2.add(C5677xw.f(m2, d2, rh06, 0.0f, 1.0f, 150));
        arrayList2.add(C5677xw.f(m2, d2, rh02, 0.0f, 0.9f, 300));
        arrayList2.add(C5677xw.f(m2, d2, rh03, f, f4, 300));
        arrayList2.add(C5677xw.f(m2, d2, rh04, f2, f3, 300));
        AnimatorSet animatorSet3 = new AnimatorSet();
        animatorSet3.setInterpolator(animation.InterpolatorC5286vf.g);
        animatorSet3.playTogether(arrayList2);
        AnimatorSet animatorSet4 = new AnimatorSet();
        animatorSet4.playTogether(animatorSet3, animatorSet2);
        ArrayList arrayList3 = new ArrayList(7);
        arrayList3.add(C5677xw.g(m2, j70, rh02, 0.9f, 1.0f, 300, vfVar));
        arrayList3.add(C5677xw.g(m2, j70, rh03, min, 0.0f, 300, vfVar));
        arrayList3.add(C5677xw.g(m2, j70, rh04, min, 0.0f, 300, vfVar));
        arrayList3.add(C5677xw.g(m2, j70, rh05, 1.0f, 1.1111112f, 300, vfVar));
        arrayList3.add(C5677xw.g(m2, j70, rh0, 1.0f, 0.0f, 300, vfVar));
        arrayList3.add(C5677xw.f(m2, d2, rh06, 1.0f, 0.0f, 300));
        if (this.K == 1) {
            arrayList3.add(C5677xw.g(m2, d2, rh04, f3, this.G, 300, animation.InterpolatorC5286vf.f));
        } else {
            arrayList3.add(C5677xw.g(m2, d2, rh03, f4, this.F, 300, animation.InterpolatorC5286vf.f));
        }
        AnimatorSet animatorSet5 = new AnimatorSet();
        animatorSet5.setStartDelay(150);
        animatorSet5.addListener(new C5944zW0(this, i3));
        animatorSet5.playTogether(arrayList3);
        AnimatorSet animatorSet6 = new AnimatorSet();
        this.X = animatorSet6;
        animatorSet6.playSequentially(animatorSet4, animatorSet5);
        this.X.start();
        this.L.e(z);
    }

    @Override // defpackage.AbstractC2300e70
    public void F(int i) {
        Y();
        j();
        X(i);
    }

    @Override // defpackage.AbstractC2300e70
    public void P(long j, boolean z) {
        Tab j2;
        this.Q = false;
        this.R = true;
        this.S = -1;
        AbstractC0124Ca1 ca1 = this.L;
        if (!(ca1 == null || this.M == null || (j2 = ((AbstractC0246Ea1) ca1).j()) == null || !j2.isNativePage())) {
            this.M.b(j2);
        }
        Y();
    }

    @Override // defpackage.AbstractC2300e70
    public void T(long j, long j2) {
        J70[] j70Arr = this.P;
        if (j70Arr != null) {
            boolean z = false;
            for (int length = j70Arr.length - 1; length >= 0; length--) {
                z = W(j2, this.P[length]) || z;
            }
            if (z) {
                M();
            }
        }
    }

    @Override // defpackage.AbstractC2300e70
    public void U(RectF rectF, RectF rectF2, LayerTitleCache layerTitleCache, TabContentManager tabContentManager, ResourceManager resourceManager, AbstractC2400ek ekVar) {
        this.a0.d(this.f9833J, rectF2, this, layerTitleCache, tabContentManager, resourceManager, ekVar, -1, 0.0f, 0);
    }

    public final void X(int i) {
        TabModel m;
        J70[] j70Arr = this.P;
        if ((j70Arr == null || j70Arr.length != 1 || j70Arr[0].q() != i) && (m = ((AbstractC0246Ea1) this.L).m(i)) != null) {
            J70 d = d(i, m.a(), false, false);
            d.k(J70.v, 0.0f);
            this.P = new J70[]{d};
            LinkedList linkedList = new LinkedList(Arrays.asList(Integer.valueOf(i)));
            TabContentManager tabContentManager = this.M;
            if (tabContentManager != null) {
                tabContentManager.k(linkedList, -1);
            }
        }
    }

    public final void Y() {
        this.P = null;
        this.Z = null;
        this.Y = null;
    }

    @Override // defpackage.AbstractC2300e70
    public void j() {
        C5677xw xwVar = this.V;
        if (xwVar != null) {
            xwVar.end();
        }
        AnimatorSet animatorSet = this.W;
        if (animatorSet != null) {
            animatorSet.end();
        }
        AnimatorSet animatorSet2 = this.X;
        if (animatorSet2 != null) {
            animatorSet2.end();
        }
    }

    @Override // defpackage.AbstractC2300e70
    public VL n() {
        return this.b0;
    }

    @Override // defpackage.AbstractC2300e70
    public int p() {
        return 3;
    }

    @Override // defpackage.AbstractC2300e70
    public SceneLayer q() {
        return this.a0;
    }

    @Override // defpackage.AbstractC2300e70
    public int r() {
        return 3;
    }

    @Override // defpackage.AbstractC2300e70
    public boolean t() {
        return true;
    }

    @Override // defpackage.AbstractC2300e70
    public boolean u() {
        return true;
    }
}
