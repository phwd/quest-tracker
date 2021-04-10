package defpackage;

import android.content.Context;
import android.graphics.RectF;
import android.util.SparseArray;
import com.oculus.browser.R;
import java.util.Iterator;
import java.util.Objects;
import org.chromium.chrome.browser.compositor.CompositorViewHolder;
import org.chromium.chrome.browser.compositor.LayerTitleCache;
import org.chromium.ui.base.LocalizationUtils;
import org.chromium.ui.resources.ResourceManager;

/* renamed from: o31  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3998o31 implements Bv1 {

    /* renamed from: a  reason: collision with root package name */
    public static final AbstractC3719mR f10529a = new C2802h31("offsetX");
    public static final AbstractC3719mR b = new C2973i31("offsetY");
    public static final AbstractC3719mR c = new C3143j31("width");
    public C5677xw A;
    public float B;
    public final RectF C = new RectF();
    public C1322Vq0 D = new C1322Vq0();
    public int d = -1;
    public final Context e;
    public final AbstractC3827n31 f;
    public final Y91 g;
    public final F70 h;
    public final K70 i;
    public final C0575Ji1 j;
    public boolean k = true;
    public boolean l;
    public boolean m = true;
    public final boolean n;
    public float o;
    public float p = 1.0f;
    public String q;
    public float r;
    public float s;
    public float t;
    public float u;
    public float v;
    public float w;
    public float x;
    public final RectF y = new RectF();
    public boolean z = true;

    public C3998o31(Context context, int i2, AbstractC3827n31 n31, W21 w21, F70 f70, K70 k70, boolean z2) {
        this.d = i2;
        this.e = context;
        this.f = n31;
        this.g = new Y91(i2, w21);
        this.h = f70;
        this.i = k70;
        this.n = z2;
        C0575Ji1 ji1 = new C0575Ji1(context, 0.0f, 0.0f, new C3314k31(this), R.drawable.f28600_resource_name_obfuscated_RES_2131230900);
        this.j = ji1;
        ji1.r = R.color.f11220_resource_name_obfuscated_RES_2131099812;
        ji1.s = R.color.f11230_resource_name_obfuscated_RES_2131099813;
        ji1.t = R.color.f11320_resource_name_obfuscated_RES_2131099822;
        ji1.u = R.color.f13330_resource_name_obfuscated_RES_2131100023;
        ji1.m = z2;
        ji1.b.set(g());
        ji1.j = 0.0f;
    }

    @Override // defpackage.Bv1
    public boolean a(float f2, float f3) {
        if (f(f2, f3)) {
            return false;
        }
        return this.y.contains(f2, f3);
    }

    @Override // defpackage.Bv1
    public String b() {
        return this.q;
    }

    @Override // defpackage.Bv1
    public void c(long j2) {
        X21 x21 = (X21) this.f;
        Objects.requireNonNull(x21);
        if (!this.l) {
            x21.d.x(AbstractC1160Ta1.e(x21.d, this.d), 3);
        }
    }

    @Override // defpackage.Bv1
    public void d(RectF rectF) {
        rectF.set(this.y);
    }

    public final void e(boolean z2) {
        boolean z3 = this.m && this.p > 0.99f;
        if (z3 != this.z) {
            float f2 = z3 ? 1.0f : 0.0f;
            if (z2) {
                C5677xw xwVar = this.A;
                if (xwVar != null) {
                    xwVar.end();
                }
                C4316pw pwVar = ((D70) this.i).h0;
                C0575Ji1 ji1 = this.j;
                C5677xw d2 = C5677xw.d(pwVar, ji1, C0053Aw.f7706a, ji1.i, f2, 150);
                this.A = d2;
                d2.H.b(new C3485l31(this));
                this.A.start();
            } else {
                this.j.i = f2;
            }
            this.z = z3;
            if (!z3) {
                this.j.k = false;
            }
        }
    }

    public boolean f(float f2, float f3) {
        if (this.z) {
            return this.j.a(f2, f3);
        }
        return false;
    }

    public final RectF g() {
        float f2;
        float f3 = 0.0f;
        if (!LocalizationUtils.isLayoutRtl()) {
            RectF rectF = this.C;
            float f4 = this.w - 36.0f;
            rectF.left = f4;
            rectF.right = f4 + 36.0f;
        } else {
            RectF rectF2 = this.C;
            rectF2.left = 0.0f;
            rectF2.right = 36.0f;
        }
        RectF rectF3 = this.C;
        rectF3.top = 0.0f;
        rectF3.bottom = this.x;
        ResourceManager resourceManager = ((CompositorViewHolder) this.h).M.N;
        if (resourceManager != null) {
            SparseArray sparseArray = (SparseArray) resourceManager.b.get(0);
            G70 g70 = sparseArray != null ? (G70) sparseArray.get(R.drawable.f28260_resource_name_obfuscated_RES_2131230866) : null;
            if (g70 != null) {
                if (LocalizationUtils.isLayoutRtl()) {
                    f2 = g70.f8066a.left;
                } else {
                    f2 = -(g70.b.width() - g70.f8066a.right);
                }
                f3 = f2;
            }
        }
        this.C.offset(this.u + f3, this.v);
        return this.C;
    }

    public float h() {
        return AbstractC4089od0.b(1.0f - (this.v / this.x), 0.0f, 1.0f);
    }

    public final void i() {
        RectF g2 = g();
        C0575Ji1 ji1 = this.j;
        float width = g2.width();
        RectF rectF = ji1.b;
        rectF.right = rectF.left + width;
        C0575Ji1 ji12 = this.j;
        float height = g2.height();
        RectF rectF2 = ji12.b;
        rectF2.bottom = rectF2.top + height;
        this.j.i(g2.left);
        this.j.j(g2.top);
    }

    public void j(float f2) {
        C0575Ji1 ji1 = this.j;
        ji1.i((f2 - this.u) + ji1.b.left);
        this.u = f2;
        RectF rectF = this.y;
        rectF.left = f2;
        rectF.right = f2 + this.w;
    }

    public void k(float f2) {
        C0575Ji1 ji1 = this.j;
        ji1.j((f2 - this.v) + ji1.b.top);
        this.v = f2;
        RectF rectF = this.y;
        rectF.top = f2;
        rectF.bottom = f2 + this.x;
    }

    public void l(boolean z2) {
        this.k = z2;
        if (!z2) {
            K70 k70 = this.i;
            ((LayerTitleCache) ((AbstractC3838n70) k70).v0).b(this.d);
        }
        Iterator it = this.D.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((AbstractC3656m31) uq0.next()).a(this.k);
            } else {
                return;
            }
        }
    }
}
