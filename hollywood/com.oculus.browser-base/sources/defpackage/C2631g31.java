package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.RectF;
import com.oculus.browser.R;
import java.util.List;
import java.util.Objects;
import org.chromium.chrome.browser.compositor.LayerTitleCache;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.ui.base.LocalizationUtils;

/* renamed from: g31  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2631g31 implements LO0 {
    public final RectF F = new RectF();
    public AbstractC0124Ca1 G;
    public final K70 H;
    public final C3395ka I;

    /* renamed from: J  reason: collision with root package name */
    public boolean f9973J;
    public final X21 K;
    public final X21 L;
    public float M;
    public final float N;
    public int O;
    public final C0053Aw P;
    public C1285Vb1 Q;
    public C2460f31 R;
    public AbstractC0855Oa1 S;
    public AbstractC1099Sa1 T;
    public final AbstractC0612Ka1 U = new Y21(this);
    public AbstractC5783ya1 V;
    public final String W;
    public final Q31 X;
    public final Q31 Y;

    public C2631g31(Context context, K70 k70, F70 f70, Q31 q31, Q31 q312) {
        this.H = k70;
        this.X = q31;
        this.Y = q312;
        this.Q = new C1285Vb1(context);
        this.R = new C2460f31(this, null);
        this.W = context.getString(R.string.f63150_resource_name_obfuscated_RES_2131953632);
        this.I = new C3395ka(context, this.R, null, false, false);
        X21 x21 = new X21(context, k70, f70, false);
        this.K = x21;
        X21 x212 = new X21(context, k70, f70, true);
        this.L = x212;
        C0053Aw aw = new C0053Aw(context, 24.0f, 24.0f, new Z21(this));
        this.P = aw;
        aw.m = false;
        aw.l = false;
        aw.h(R.drawable.f28640_resource_name_obfuscated_RES_2131230904, R.drawable.f28640_resource_name_obfuscated_RES_2131230904, R.drawable.f33520_resource_name_obfuscated_RES_2131231392, R.drawable.f33520_resource_name_obfuscated_RES_2131231392);
        aw.j(10.0f);
        Resources resources = context.getResources();
        this.N = resources.getDimension(R.dimen.f25730_resource_name_obfuscated_RES_2131166192) / resources.getDisplayMetrics().density;
        String string = resources.getString(R.string.f46060_resource_name_obfuscated_RES_2131951923);
        String string2 = resources.getString(R.string.f46050_resource_name_obfuscated_RES_2131951922);
        aw.o = string;
        aw.p = string2;
        x21.v = new AZ0(context);
        x21.O = context;
        x212.v = new AZ0(context);
        x212.O = context;
    }

    public static void b(C2631g31 g31, Tab tab) {
        if (g31.X.get() != null) {
            String a2 = ((LayerTitleCache) ((AbstractC0757Mi1) g31.X.get())).a(tab, g31.W);
            X21 f = g31.f(tab.a());
            int id = tab.getId();
            Tab d = AbstractC1160Ta1.d(f.d, id);
            if (d != null) {
                f.m(f.f(id), a2, d.isHidden());
            }
            ((D70) g31.H).s(null);
        }
    }

    @Override // defpackage.LO0
    public boolean a() {
        return false;
    }

    @Override // defpackage.LO0
    public VL c() {
        return this.I;
    }

    public X21 d() {
        return f(this.f9973J);
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x008d  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0092  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00a4  */
    @Override // defpackage.LO0
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean e(long r18, long r20) {
        /*
        // Method dump skipped, instructions count: 257
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C2631g31.e(long, long):boolean");
    }

    public X21 f(boolean z) {
        return z ? this.L : this.K;
    }

    public final void g(boolean z) {
        if (z != this.f9973J) {
            this.f9973J = z;
            X21 x21 = this.L;
            if (z) {
                x21.a(0, false);
            } else {
                x21.u.dismiss();
            }
            X21 x212 = this.K;
            if (!this.f9973J) {
                x212.a(0, false);
            } else {
                x212.u.dismiss();
            }
            h();
            ((D70) this.H).s(null);
        }
    }

    public final void h() {
        this.P.m = this.f9973J;
        AbstractC0124Ca1 ca1 = this.G;
        if (ca1 != null) {
            boolean z = true;
            if (((AbstractC0246Ea1) ca1).l(true).getCount() == 0) {
                z = false;
            }
            this.P.l = z;
            float f = z ? 33.0f : 0.0f;
            this.K.o(f);
            this.L.o(f);
        }
    }

    @Override // defpackage.LO0
    public boolean k() {
        return false;
    }

    @Override // defpackage.LO0
    public void m(float f, float f2, float f3, int i) {
        this.M = f;
        this.O = i;
        if (!LocalizationUtils.isLayoutRtl()) {
            this.P.i((this.M - 24.0f) - 6.0f);
        } else {
            this.P.i(6.0f);
        }
        this.K.j(this.M, this.N);
        this.L.j(this.M, this.N);
        this.F.set(0.0f, 0.0f, this.M, Math.min(this.N, f3));
        C3395ka kaVar = this.I;
        RectF rectF = this.F;
        if (rectF == null) {
            kaVar.p.setEmpty();
        } else {
            kaVar.p.set(rectF);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:40:0x0144  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0147  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x014b  */
    @Override // defpackage.LO0
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public defpackage.MO0 q(android.graphics.RectF r36, android.graphics.RectF r37, org.chromium.ui.resources.ResourceManager r38, float r39) {
        /*
        // Method dump skipped, instructions count: 577
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C2631g31.q(android.graphics.RectF, android.graphics.RectF, org.chromium.ui.resources.ResourceManager, float):MO0");
    }

    @Override // defpackage.LO0
    public void s(List list) {
        C0053Aw aw = this.P;
        if (aw.l) {
            list.add(aw);
        }
        X21 d = d();
        int i = 0;
        while (true) {
            C3998o31[] o31Arr = d.i;
            if (i >= o31Arr.length) {
                break;
            }
            C3998o31 o31 = o31Arr[i];
            Objects.requireNonNull(o31);
            list.add(o31);
            if (o31.z) {
                list.add(o31.j);
            }
            i++;
        }
        C0053Aw aw2 = d.o;
        if (aw2.l) {
            list.add(aw2);
        }
    }

    @Override // defpackage.LO0
    public boolean u() {
        return true;
    }

    @Override // defpackage.LO0
    public boolean v() {
        return false;
    }
}
