package org.chromium.chrome.browser.toolbar;

import J.N;
import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import com.oculus.browser.R;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.Objects;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TrustedCdn;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class LocationBarModel extends AbstractC4422qa0 implements Sj1 {

    /* renamed from: a  reason: collision with root package name */
    public final Context f10784a;
    public final AbstractC1377Wn0 b;
    public final Xj1 c;
    public final C3085ik1 d;
    public final AbstractC0062Ba0 e;
    public Tab f;
    public int g;
    public AbstractC2642g70 h;
    public boolean i;
    public boolean j;
    public boolean k;
    public long l;
    public C1322Vq0 m = new C1322Vq0();

    public LocationBarModel(Context context, AbstractC1377Wn0 wn0, Xj1 xj1, C3085ik1 ik1, AbstractC0062Ba0 ba0) {
        this.f10784a = context;
        this.b = wn0;
        this.c = xj1;
        this.d = ik1;
        this.e = ba0;
        this.g = AbstractC2934hr.a(context.getResources(), false);
    }

    public void A() {
        Iterator it = this.m.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((AbstractC4592ra0) uq0.next()).n();
            } else {
                return;
            }
        }
    }

    public void B() {
        Iterator it = this.m.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((AbstractC4592ra0) uq0.next()).o();
            } else {
                return;
            }
        }
    }

    public final void C() {
        this.j = !this.i && this.g != AbstractC2934hr.a(this.f10784a.getResources(), this.i) && r() && !this.f.isNativePage();
    }

    @Override // defpackage.AbstractC4422qa0, defpackage.Sj1
    public boolean a() {
        return this.i;
    }

    @Override // defpackage.AbstractC4422qa0, defpackage.Sj1
    public Profile b() {
        Profile b2 = Profile.b();
        if (!this.i) {
            return b2;
        }
        Tab tab = this.f;
        WindowAndroid i2 = tab != null ? tab.i() : null;
        Objects.requireNonNull(this.d);
        Z00.b(i2);
        return b2.c();
    }

    @Override // defpackage.AbstractC4422qa0, defpackage.Sj1
    public int c() {
        if (g()) {
            return AbstractC2934hr.a(this.f10784a.getResources(), this.i);
        }
        return this.g;
    }

    @Override // defpackage.AbstractC4422qa0, defpackage.Sj1
    public Tab d() {
        if (r()) {
            return this.f;
        }
        return null;
    }

    @Override // defpackage.AbstractC4422qa0, defpackage.Sj1
    public AbstractC1377Wn0 e() {
        return this.b;
    }

    @Override // defpackage.Sj1
    public boolean f() {
        return this.k;
    }

    @Override // defpackage.AbstractC4422qa0, defpackage.Sj1
    public boolean g() {
        AbstractC2642g70 g70;
        if (!this.k || (g70 = this.h) == null) {
            return false;
        }
        AbstractC2300e70 e70 = ((D70) g70).S;
        if (e70 != null && e70.p() == 1) {
            return true;
        }
        return false;
    }

    public final WebContents getActiveWebContents() {
        if (!r()) {
            return null;
        }
        return this.f.l();
    }

    @Override // defpackage.AbstractC4422qa0, defpackage.Sj1
    public Pq1 h() {
        String str;
        if (!r()) {
            return Pq1.c;
        }
        String i2 = i();
        if (AbstractC5818ym0.a(i2, this.i) || AbstractC5154ur1.g(i2)) {
            return Pq1.c;
        }
        long j2 = this.l;
        String str2 = "";
        if (j2 == 0) {
            str = str2;
        } else {
            str = N.MvJvjGzq(j2, this);
        }
        if (this.f.G()) {
            return x(i2, str, str);
        }
        if (HG.c(i2)) {
            String a2 = HG.a(i2);
            if (a2 == null) {
                return x(i2, str, str);
            }
            Objects.requireNonNull(this.c);
            String M5yzUycr = N.M5yzUycr(a2);
            return x(M5yzUycr, M5yzUycr, M5yzUycr);
        } else if (v()) {
            String k2 = AbstractC5154ur1.k(i2);
            return x(i2, k2, k2);
        } else if (t()) {
            String n = this.f.n();
            Objects.requireNonNull(this.c);
            String k3 = AbstractC5154ur1.k(N.M5yzUycr(n));
            AbstractC0062Ba0 ba0 = this.e;
            WebContents l2 = this.f.l();
            Objects.requireNonNull((Ik1) ba0);
            if (!AbstractC2254ds0.h(l2)) {
                return x(i2, k3, str2);
            }
            return x(i2, k3, k3);
        } else {
            long j3 = this.l;
            if (j3 != 0) {
                str2 = N.Ml$ZWVQn(j3, this);
            }
            if (!str2.equals(str)) {
                return x(i2, str2, str);
            }
            return x(i2, str, str);
        }
    }

    @Override // defpackage.AbstractC4422qa0, defpackage.Sj1
    public String i() {
        if (g()) {
            return "chrome-native://newtab/";
        }
        return (!r() || !d().isInitialized()) ? "" : d().s().trim();
    }

    @Override // defpackage.Sj1
    public boolean j() {
        return g() || this.j;
    }

    @Override // defpackage.AbstractC4422qa0
    public void k(AbstractC4592ra0 ra0) {
        this.m.b(ra0);
    }

    @Override // defpackage.AbstractC4422qa0
    public int l(boolean z) {
        if (this.l == 0) {
            return 0;
        }
        if (g()) {
            return 1;
        }
        return N.MY48gn2Q(this.l, this, z);
    }

    @Override // defpackage.AbstractC4422qa0
    public int m() {
        p();
        boolean g2 = AbstractC1270Uv.g(c());
        if (this.i || g2) {
            return AbstractC1300Vg1.c(true);
        }
        if (v()) {
            return R.color.f12950_resource_name_obfuscated_RES_2131099985;
        }
        return AbstractC1300Vg1.c(false);
    }

    @Override // defpackage.AbstractC4422qa0
    public int n() {
        return MR0.a(p());
    }

    @Override // defpackage.AbstractC4422qa0
    public int o(boolean z) {
        int p = p();
        boolean z2 = true;
        boolean z3 = !z;
        boolean t = t();
        boolean v = v();
        if (u()) {
            return R.drawable.f34350_resource_name_obfuscated_RES_2131231475;
        }
        if (v) {
            return R.drawable.f34660_resource_name_obfuscated_RES_2131231506;
        }
        if (t) {
            return R.drawable.f32310_resource_name_obfuscated_RES_2131231271;
        }
        if ((p == 0 || p == 6) && this.l == 0) {
            return R.drawable.f34350_resource_name_obfuscated_RES_2131231475;
        }
        if (AbstractC5762yQ0.g(this.i) && !this.b.g()) {
            z2 = false;
        }
        return MR0.b(p, z3, z2);
    }

    @Override // defpackage.AbstractC4422qa0
    public int p() {
        Tab d2 = d();
        String j2 = d2 != null ? TrustedCdn.j(d2) : null;
        boolean t = t();
        if (d2 == null || t) {
            return 0;
        }
        if (j2 == null) {
            return LR0.a(d2.l());
        }
        try {
            return new Vo1(j2).g().equals("https") ? 3 : 6;
        } catch (URISyntaxException e2) {
            throw new IllegalArgumentException(e2);
        }
    }

    @Override // defpackage.AbstractC4422qa0
    public String q() {
        if (!r()) {
            return "";
        }
        String title = d().getTitle();
        return TextUtils.isEmpty(title) ? title : title.trim();
    }

    @Override // defpackage.AbstractC4422qa0
    public boolean r() {
        Tab tab = this.f;
        return tab != null && tab.isInitialized() && !this.f.g();
    }

    @Override // defpackage.AbstractC4422qa0
    public boolean t() {
        if (r()) {
            AbstractC0062Ba0 ba0 = this.e;
            Tab tab = this.f;
            Objects.requireNonNull((Ik1) ba0);
            if (AbstractC2254ds0.f(tab)) {
                return true;
            }
        }
        return false;
    }

    @Override // defpackage.AbstractC4422qa0
    public boolean u() {
        return r() && C1872be1.e(this.f).h();
    }

    @Override // defpackage.AbstractC4422qa0
    public boolean v() {
        return r() && AbstractC5566xF0.a(this.f);
    }

    @Override // defpackage.AbstractC4422qa0
    public void w(AbstractC4592ra0 ra0) {
        this.m.c(ra0);
    }

    public final Pq1 x(String str, String str2, String str3) {
        boolean z;
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str2);
        if (this.l != 0 && spannableStringBuilder.length() > 0) {
            Tab tab = this.f;
            if (tab == null || TrustedCdn.j(tab) == null) {
                try {
                    z = AbstractC5154ur1.b.contains(new Vo1(str).g());
                } catch (URISyntaxException unused) {
                    z = false;
                }
                C3956nq nqVar = new C3956nq(b());
                AbstractC0229Ds0.a(spannableStringBuilder, this.f10784a.getResources(), nqVar, p(), z, !AbstractC1270Uv.g(c()), !(g() || this.j) && !this.i);
                nqVar.a();
            }
        }
        return Pq1.d(str, spannableStringBuilder, str3);
    }

    public void y() {
        Iterator it = this.m.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((AbstractC4592ra0) uq0.next()).l();
            } else {
                return;
            }
        }
    }

    public void z() {
        Iterator it = this.m.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((AbstractC4592ra0) uq0.next()).m();
            } else {
                return;
            }
        }
    }
}
