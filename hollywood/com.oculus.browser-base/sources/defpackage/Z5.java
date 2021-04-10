package defpackage;

import J.N;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;
import android.text.TextUtils;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import org.chromium.base.ThreadUtils;
import org.chromium.components.payments.PaymentApp;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: Z5  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Z5 extends PaymentApp implements AbstractC1950c40 {
    public final Handler l = new Handler();
    public final U5 m;
    public final Set n;
    public final boolean o;
    public final String p;
    public final String q;
    public final String r;
    public C1784b6 s;
    public AbstractC1277Uy0 t;
    public ServiceConnectionC2121d40 u;
    public String v;
    public boolean w;
    public final Z31 x;
    public boolean y;
    public Kx1 z;

    public Z5(U5 u5, String str, String str2, String str3, String str4, Drawable drawable, boolean z2, String str5, Z31 z31) {
        super(str, str4, null, drawable);
        Object obj = ThreadUtils.f10596a;
        this.m = u5;
        this.p = str;
        this.q = str2;
        this.r = str3;
        this.n = new HashSet();
        this.o = z2;
        this.v = str5;
        this.x = z31;
        this.y = false;
    }

    public static String H(String str) {
        return N.MR6Af3ZS(str, 1);
    }

    @Override // org.chromium.components.payments.PaymentApp
    public boolean C() {
        Object obj = ThreadUtils.f10596a;
        return C3813mz0.a().c();
    }

    @Override // org.chromium.components.payments.PaymentApp
    public void D() {
        Object obj = ThreadUtils.f10596a;
        C3813mz0 a2 = C3813mz0.a();
        Objects.requireNonNull(a2);
        AbstractC2710gZ gZVar = a2.b;
        if (gZVar != null) {
            try {
                ((C2368eZ) gZVar).c();
            } catch (RemoteException e) {
                AbstractC1220Ua0.a("PaymentDetailsUpdate", "Error calling paymentDetailsNotUpdated", e);
            } catch (Throwable th) {
                a2.b = null;
                throw th;
            }
            a2.b = null;
        }
    }

    @Override // org.chromium.components.payments.PaymentApp
    public void F(C4530rA0 ra0) {
        ArrayList arrayList;
        Bundle bundle;
        List<C3337kB0> asList;
        Object obj = ThreadUtils.f10596a;
        C3813mz0 a2 = C3813mz0.a();
        Gx1 a3 = Mx1.a(ra0.d);
        C3337kB0[] kb0Arr = ra0.e;
        if (kb0Arr == null || (asList = Arrays.asList(kb0Arr)) == null) {
            arrayList = null;
        } else {
            arrayList = new ArrayList();
            for (C3337kB0 kb0 : asList) {
                arrayList.add(kb0 == null ? null : new Lx1(kb0.d, kb0.e, Mx1.a(kb0.f), kb0.g));
            }
        }
        String str = ra0.g;
        String str2 = ra0.h;
        T3 t3 = ra0.i;
        if (t3 == null) {
            bundle = null;
        } else {
            bundle = new Bundle();
            Mx1.c("addressLine", t3.d, bundle);
            Mx1.c("city", t3.e, bundle);
            Mx1.c("countryCode", t3.f, bundle);
            Mx1.c("dependentLocality", t3.g, bundle);
            Mx1.c("organization", t3.h, bundle);
            Mx1.c("phone", t3.i, bundle);
            Mx1.c("postalCode", t3.j, bundle);
            Mx1.c("recipient", t3.k, bundle);
            Mx1.c("region", t3.l, bundle);
            Mx1.c("sortingCode", t3.m, bundle);
        }
        Objects.requireNonNull(a2);
        Object obj2 = ThreadUtils.f10596a;
        AbstractC2710gZ gZVar = a2.b;
        if (gZVar != null) {
            try {
                Bundle bundle2 = new Bundle();
                if (a3 != null) {
                    Objects.requireNonNull(a3);
                    Bundle bundle3 = new Bundle();
                    bundle3.putString("currency", a3.f8125a);
                    bundle3.putString("value", a3.b);
                    bundle2.putBundle("total", bundle3);
                }
                if (arrayList != null && !arrayList.isEmpty()) {
                    bundle2.putParcelableArray("shippingOptions", Lx1.a(arrayList));
                }
                if (!TextUtils.isEmpty(str)) {
                    bundle2.putString("error", str);
                }
                if (!TextUtils.isEmpty(str2)) {
                    bundle2.putString("stringifiedPaymentMethodErrors", str2);
                }
                if (bundle != null) {
                    bundle2.putBundle("addressErrors", bundle);
                }
                ((C2368eZ) gZVar).d(bundle2);
            } catch (RemoteException e) {
                AbstractC1220Ua0.a("PaymentDetailsUpdate", "Error calling updateWith", e);
            } catch (Throwable th) {
                a2.b = null;
                throw th;
            }
            a2.b = null;
        }
    }

    public final void G(String str) {
        AbstractC2531fV.q(this, str, this.l);
    }

    public final void I(boolean z2) {
        Object obj = ThreadUtils.f10596a;
        C1784b6 b6Var = this.s;
        if (b6Var != null) {
            C2296e6 e6Var = b6Var.f9512a;
            if (z2) {
                e6Var.h.j(this);
            }
            int i = e6Var.r - 1;
            e6Var.r = i;
            if (i == 0) {
                e6Var.h.a(e6Var.i);
            }
            this.s = null;
        }
    }

    @Override // org.chromium.components.payments.PaymentApp
    public void l() {
    }

    @Override // org.chromium.components.payments.PaymentApp
    public String m() {
        return this.v;
    }

    @Override // org.chromium.components.payments.PaymentApp
    public Set p() {
        return Collections.unmodifiableSet(this.n);
    }

    @Override // org.chromium.components.payments.PaymentApp
    public int q() {
        return 2;
    }

    @Override // org.chromium.components.payments.PaymentApp
    public boolean s() {
        return this.x.d;
    }

    @Override // org.chromium.components.payments.PaymentApp
    public boolean t() {
        return this.x.b;
    }

    @Override // org.chromium.components.payments.PaymentApp
    public boolean u() {
        return this.x.c;
    }

    @Override // org.chromium.components.payments.PaymentApp
    public boolean v() {
        return this.x.f9317a;
    }

    @Override // org.chromium.components.payments.PaymentApp
    public void w(String str, String str2, String str3, String str4, byte[][] bArr, Map map, C1035Qz0 qz0, List list, Map map2, C1523Yz0 yz0, List list2, AbstractC1277Uy0 uy0) {
        Context context;
        this.t = uy0;
        N5 n5 = new N5(this, str, str2, H(str3), H(str4), bArr, map, qz0, list, map2, yz0, list2);
        if (!this.o) {
            n5.run();
            return;
        }
        U5 u5 = this.m;
        O5 o5 = new O5(this);
        WindowAndroid I = ((Y5) u5).f9250a.I();
        if (I == null) {
            context = null;
        } else {
            context = (Context) I.s0().get();
        }
        if (context == null) {
            G("Unable to find Chrome activity.");
            return;
        }
        C2246dp1 dp1 = new C2246dp1(context, R.style.f72700_resource_name_obfuscated_RES_2132017843);
        dp1.g(R.string.f52160_resource_name_obfuscated_RES_2131952533);
        dp1.c(R.string.f52180_resource_name_obfuscated_RES_2131952535);
        dp1.e(R.string.f56550_resource_name_obfuscated_RES_2131952972, new V5(n5));
        dp1.d(R.string.f48470_resource_name_obfuscated_RES_2131952164, new W5(o5));
        dp1.f9828a.l = new X5(o5);
        dp1.i();
    }

    @Override // org.chromium.components.payments.PaymentApp
    public boolean x() {
        return this.y;
    }
}
