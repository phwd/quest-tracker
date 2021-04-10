package defpackage;

import J.N;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.RectF;
import com.oculus.browser.R;
import org.chromium.chrome.browser.compositor.LayerTitleCache;
import org.chromium.chrome.browser.compositor.layouts.content.TabContentManager;
import org.chromium.chrome.browser.layouts.scene_layer.SceneLayer;
import org.chromium.ui.resources.ResourceManager;

/* renamed from: Q91  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Q91 extends SceneLayer {
    public long G;
    public AbstractC0124Ca1 H;
    public int[] I = new int[4];

    /* renamed from: J  reason: collision with root package name */
    public boolean f8739J;

    @Override // org.chromium.chrome.browser.layouts.scene_layer.SceneLayer
    public void b() {
        if (this.G == 0) {
            this.G = N.MwkJn6dB(this);
        }
    }

    public void d(Context context, RectF rectF, AbstractC2300e70 e70, LayerTitleCache layerTitleCache, TabContentManager tabContentManager, ResourceManager resourceManager, AbstractC2400ek ekVar, int i, float f, int i2) {
        int i3;
        AbstractC0124Ca1 ca1;
        Q91 q91 = this;
        if (q91.G != 0) {
            Resources resources = context.getResources();
            float f2 = resources.getDisplayMetrics().density;
            boolean M09VlOh_ = N.M09VlOh_("HorizontalTabSwitcherAndroid");
            int i4 = R.color.f10840_resource_name_obfuscated_RES_2131099774;
            if ((M09VlOh_ || AbstractC4772sd1.b()) && (ca1 = q91.H) != null && ((AbstractC0246Ea1) ca1).r()) {
                i4 = R.color.f10880_resource_name_obfuscated_RES_2131099778;
            }
            int color = context.getResources().getColor(i4);
            J70[] j70Arr = e70.P;
            int length = j70Arr != null ? j70Arr.length : 0;
            boolean z = q91.f8739J;
            boolean z2 = true;
            if (!z) {
                long j = q91.G;
                if (j != 0 && !z) {
                    N.MxcqGWbf(j, this, tabContentManager, layerTitleCache, resourceManager);
                    q91.f8739J = true;
                }
            }
            N.MQUD003X(q91.G, q91);
            N.Mf2p86oA(q91.G, this, color, rectF.left, rectF.top, rectF.width(), rectF.height());
            int i5 = -1;
            if (i != -1) {
                N.MdnzXRBb(q91.G, this, i, f, i2);
            }
            boolean M09VlOh_2 = N.M09VlOh_("HorizontalTabSwitcherAndroid");
            float f3 = AbstractC1270Uv.g(color) ? 1.0f : 0.8f;
            int i6 = 0;
            while (i6 < length) {
                J70 j70 = j70Arr[i6];
                float e = j70.e(J70.Q);
                boolean z3 = (!j70.E() || M09VlOh_2) ? false : z2;
                int a2 = AbstractC2934hr.a(resources, z3);
                if (z3) {
                    i3 = i5;
                } else {
                    i3 = resources.getColor(R.color.f11340_resource_name_obfuscated_RES_2131099824);
                }
                float dimensionPixelSize = (float) resources.getDimensionPixelSize(R.dimen.f25740_resource_name_obfuscated_RES_2131166193);
                int i7 = j70.E() ? R.color.f15100_resource_name_obfuscated_RES_2131100200 : R.color.f15090_resource_name_obfuscated_RES_2131100199;
                j70.q();
                int[] iArr = q91.I;
                long j2 = q91.G;
                int q = j70.q();
                boolean h = j70.h(J70.G);
                int f4 = j70.f(J70.T);
                int color2 = resources.getColor(i7);
                boolean E = j70.E();
                boolean h2 = j70.h(J70.N);
                float e2 = j70.e(J70.n) * f2;
                float e3 = j70.e(J70.o) * f2;
                float w = j70.w() * f2;
                float v = j70.v() * f2;
                float t = j70.t() * f2;
                float s = j70.s() * f2;
                float e4 = j70.e(J70.p) * f2;
                float e5 = j70.e(J70.q) * f2;
                float min = Math.min(j70.e(J70.r), j70.w()) * f2;
                float min2 = Math.min(j70.e(J70.s), j70.v()) * f2;
                float e6 = j70.e(J70.j) * f2;
                float e7 = j70.e(J70.i) * f2;
                float x = j70.x();
                float y = j70.y();
                float n = j70.n();
                RH0 rh0 = J70.v;
                float e8 = j70.e(rh0);
                RH0 rh02 = J70.t;
                N.Mp1Kxnqn(j2, this, q, iArr, false, R.id.control_container, R.drawable.f28450_resource_name_obfuscated_RES_2131230885, R.drawable.f35240_resource_name_obfuscated_RES_2131231564, R.drawable.f35220_resource_name_obfuscated_RES_2131231562, R.drawable.f33540_resource_name_obfuscated_RES_2131231394, R.drawable.f35210_resource_name_obfuscated_RES_2131231561, R.drawable.f35230_resource_name_obfuscated_RES_2131231563, h, f4, color2, E, h2, e2, e3, w, v, t, s, e4, e5, min, min2, e6, e7, x, y, n, Math.min(e8, j70.e(rh02)) * e, Math.min((1.0f - j70.e(J70.f8270J)) * j70.e(rh0), j70.e(rh02)) * e, e, f3 * e, j70.e(J70.w) * e, f2 * 36.0f, dimensionPixelSize, j70.e(J70.C), j70.e(J70.x), j70.e(J70.g), j70.e(J70.D), j70.H(), a2, j70.f(J70.U), i3, j70.h(J70.I), j70.h(J70.R), R.drawable.f33750_resource_name_obfuscated_RES_2131231415, j70.f(J70.V), j70.z(), (float) ((C1551Zj) ekVar).T, j70.e(J70.M), j70.h(J70.K));
                i6++;
                q91 = this;
                z2 = z2;
                length = length;
                j70Arr = j70Arr;
                f2 = f2;
                i5 = -1;
                resources = resources;
            }
            N.Mn9kYrkw(q91.G, q91);
        }
    }
}
