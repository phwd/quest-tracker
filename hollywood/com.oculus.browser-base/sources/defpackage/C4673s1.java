package defpackage;

import android.accounts.Account;
import android.content.Context;
import android.text.TextUtils;
import com.oculus.browser.R;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.chromium.components.signin.AccountManagerFacade;
import org.chromium.components.signin.AccountManagerFacadeProvider;

/* renamed from: s1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4673s1 implements AbstractC5523x1 {
    public final AbstractC5863z1 F;
    public final WG0 G;
    public final UH0 H;
    public final VG0 I;

    /* renamed from: J  reason: collision with root package name */
    public final AccountManagerFacade f11244J;
    public final W1 K;
    public String L;
    public String M;
    public String N = null;

    public C4673s1(Context context, AbstractC5863z1 z1Var, Runnable runnable) {
        C3135j1 j1Var = new C3135j1(this);
        this.I = j1Var;
        C3306k1 k1Var = new C3306k1(this);
        this.K = k1Var;
        this.F = z1Var;
        WG0 wg0 = new WG0(context, context.getResources().getDimensionPixelSize(R.dimen.f26670_resource_name_obfuscated_RES_2131166286));
        this.G = wg0;
        View$OnClickListenerC3477l1 l1Var = new View$OnClickListenerC3477l1(runnable);
        RunnableC3648m1 m1Var = new RunnableC3648m1(this);
        RunnableC3819n1 n1Var = new RunnableC3819n1(this);
        Map c = UH0.c(AbstractC5183v1.f);
        OH0 oh0 = AbstractC5183v1.f11454a;
        View$OnClickListenerC4843t1 t1Var = new View$OnClickListenerC4843t1(m1Var);
        LH0 lh0 = new LH0(null);
        lh0.f8415a = t1Var;
        HashMap hashMap = (HashMap) c;
        hashMap.put(oh0, lh0);
        TH0 th0 = AbstractC5183v1.b;
        LH0 lh02 = new LH0(null);
        lh02.f8415a = null;
        hashMap.put(th0, lh02);
        OH0 oh02 = AbstractC5183v1.c;
        View$OnClickListenerC5013u1 u1Var = new View$OnClickListenerC5013u1(n1Var);
        LH0 lh03 = new LH0(null);
        lh03.f8415a = u1Var;
        hashMap.put(oh02, lh03);
        OH0 oh03 = AbstractC5183v1.d;
        LH0 lh04 = new LH0(null);
        lh04.f8415a = l1Var;
        hashMap.put(oh03, lh04);
        SH0 sh0 = AbstractC5183v1.e;
        JH0 jh0 = new JH0(null);
        jh0.f8282a = 0;
        hashMap.put(sh0, jh0);
        this.H = new UH0(c, null);
        wg0.U(j1Var);
        AccountManagerFacade instance = AccountManagerFacadeProvider.getInstance();
        this.f11244J = instance;
        instance.a(k1Var);
        a();
    }

    public final void a() {
        List n = this.f11244J.n();
        if (n.isEmpty()) {
            this.H.l(AbstractC5183v1.e, 0);
            this.L = null;
            this.M = null;
            this.H.m(AbstractC5183v1.b, null);
            return;
        }
        this.M = ((Account) n.get(0)).name;
        UH0 uh0 = this.H;
        SH0 sh0 = AbstractC5183v1.e;
        int f = uh0.f(sh0);
        if (f == 0) {
            b(this.M);
            this.H.l(sh0, 1);
        } else if (f == 1 && V1.c(n, this.L) == null) {
            b(this.M);
        }
    }

    public final void b(String str) {
        this.L = str;
        this.G.Z(Collections.singletonList(str));
        c(this.L);
    }

    public final void c(String str) {
        if (TextUtils.equals(this.L, str)) {
            this.H.m(AbstractC5183v1.b, this.G.W(str));
        }
    }

    @Override // defpackage.AbstractC5523x1
    public void h() {
        AbstractC3901nW0.a(2);
        AbstractC5863z1 z1Var = this.F;
        C3990o1 o1Var = new C3990o1(this);
        D1 d1 = (D1) z1Var;
        Objects.requireNonNull(d1);
        AccountManagerFacadeProvider.getInstance().q(new A1(d1, o1Var));
    }

    @Override // defpackage.AbstractC5523x1
    public void i() {
        this.H.l(AbstractC5183v1.e, 4);
    }

    @Override // defpackage.AbstractC5523x1
    public void r(String str, boolean z) {
        this.H.l(AbstractC5183v1.e, 1);
        b(str);
    }
}
