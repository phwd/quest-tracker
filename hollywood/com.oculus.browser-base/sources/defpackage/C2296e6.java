package defpackage;

import J.N;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import org.chromium.base.ContextUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.payments.PaymentManifestWebDataService;
import org.chromium.components.payments.PaymentManifestParser;
import org.chromium.url.GURL;

/* renamed from: e6  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2296e6 implements AbstractC1279Uz0 {

    /* renamed from: a  reason: collision with root package name */
    public final Set f9831a = new HashSet();
    public final Set b = new HashSet();
    public final C1096Rz0 c;
    public final PaymentManifestWebDataService d;
    public final PaymentManifestParser e;
    public final C3456ku0 f;
    public final Ao1 g;
    public final AbstractC1460Xy0 h;
    public final AbstractC1521Yy0 i;
    public final boolean j;
    public final Map k;
    public final Map l;
    public final Map m;
    public final Map n;
    public final Map o;
    public final Map p;
    public int q;
    public int r;
    public int s;

    public C2296e6(PaymentManifestWebDataService paymentManifestWebDataService, C1096Rz0 rz0, PaymentManifestParser paymentManifestParser, C3456ku0 ku0, Ao1 ao1, AbstractC1460Xy0 xy0, AbstractC1521Yy0 yy0) {
        HashMap hashMap = new HashMap();
        this.k = hashMap;
        this.l = new HashMap();
        this.m = new HashMap();
        this.n = new HashMap();
        this.o = new HashMap();
        this.p = new HashMap();
        this.h = xy0;
        hashMap.put("com.android.vending", new GURL("https://play.google.com/billing"));
        for (GURL gurl : hashMap.values()) {
        }
        this.c = rz0;
        this.d = paymentManifestWebDataService;
        this.e = paymentManifestParser;
        this.f = ku0;
        this.g = ao1;
        this.i = yy0;
        ChromeActivity J0 = ChromeActivity.J0(this.h.d().t());
        this.j = J0 != null && J0.Q0().a();
    }

    public static String h(String str) {
        if (str == null) {
            return null;
        }
        return str.endsWith("/") ? str.substring(0, str.length() - 1) : str;
    }

    public static String i(GURL gurl) {
        if (gurl == null) {
            return null;
        }
        return h(gurl.h());
    }

    public final C2126d6 a(GURL gurl) {
        C2126d6 d6Var = (C2126d6) this.o.get(gurl);
        if (d6Var != null) {
            return d6Var;
        }
        C2126d6 d6Var2 = new C2126d6(null);
        this.o.put(gurl, d6Var2);
        return d6Var2;
    }

    public final String[] b(ActivityInfo activityInfo, String str) {
        int i2;
        Bundle bundle = activityInfo.metaData;
        if (bundle == null || (i2 = bundle.getInt(str)) == 0) {
            return null;
        }
        C3456ku0 ku0 = this.f;
        ApplicationInfo applicationInfo = activityInfo.applicationInfo;
        Objects.requireNonNull(ku0);
        try {
            Resources resourcesForApplication = ContextUtils.getApplicationContext().getPackageManager().getResourcesForApplication(applicationInfo);
            if (resourcesForApplication == null) {
                return null;
            }
            return resourcesForApplication.getStringArray(i2);
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    public final Set c(ActivityInfo activityInfo) {
        HashSet hashSet = new HashSet();
        String[] b2 = b(activityInfo, "org.chromium.payment_method_names");
        if (b2 == null) {
            return hashSet;
        }
        for (String str : b2) {
            GURL gurl = new GURL(str);
            if (AbstractC5324vr1.a(gurl)) {
                str = i(gurl);
            }
            hashSet.add(str);
        }
        return hashSet;
    }

    public final void d() {
        this.h.i(this.l.size() > 0);
        if (this.l.isEmpty() || this.h.d().e()) {
            this.h.a(this.i);
            return;
        }
        this.r = this.l.size();
        for (Map.Entry entry : this.l.entrySet()) {
            Z5 z5 = (Z5) entry.getValue();
            Map h2 = this.h.d().h();
            Set<String> p2 = z5.p();
            HashMap hashMap = new HashMap();
            for (String str : p2) {
                if (h2.containsKey(str)) {
                    hashMap.put(str, (C1401Wz0) h2.get(str));
                }
            }
            String r2 = this.h.d().r();
            String n2 = this.h.d().n();
            byte[][] l2 = this.h.d().l();
            Map g2 = this.h.d().g();
            Set<String> p3 = z5.p();
            HashMap hashMap2 = new HashMap();
            for (String str2 : p3) {
                if (g2.containsKey(str2)) {
                    hashMap2.put(str2, (C2959hz0) g2.get(str2));
                }
            }
            C1784b6 b6Var = new C1784b6(this);
            Object obj = ThreadUtils.f10596a;
            z5.s = b6Var;
            String str3 = z5.r;
            if (str3 == null) {
                z5.I(true);
            } else {
                String str4 = z5.p;
                String H = Z5.H(r2);
                String H2 = Z5.H(n2);
                HashMap hashMap3 = new HashMap();
                for (Map.Entry entry2 : hashMap.entrySet()) {
                    String str5 = (String) entry2.getKey();
                    C1401Wz0 wz0 = (C1401Wz0) entry2.getValue();
                    hashMap3.put(str5, wz0 == null ? null : new Jx1(wz0.d, wz0.e));
                }
                Intent intent = new Intent();
                Fx1.d(str3, "serviceName");
                Fx1.d(str4, "packageName");
                intent.setClassName(str4, str3);
                Fx1.d(H, "schemelessOrigin");
                Fx1.d(H2, "schemelessIframeOrigin");
                Fx1.b(hashMap3, "methodDataMap");
                intent.putExtras(Fx1.a(null, null, H, H2, l2, hashMap3, null, null, null, null, null));
                if (z5.w) {
                    z5.I(true);
                } else {
                    ServiceConnectionC2121d40 d40 = new ServiceConnectionC2121d40(ContextUtils.getApplicationContext(), intent, z5);
                    z5.u = d40;
                    try {
                        d40.c = d40.f9745a.bindService(d40.f, d40, 1);
                    } catch (SecurityException unused) {
                    }
                    if (!d40.c) {
                        d40.c();
                    } else {
                        d40.e.postDelayed(new RunnableC1599a40(d40), 1000);
                    }
                }
            }
        }
    }

    public void e() {
        boolean z = true;
        int i2 = this.s - 1;
        this.s = i2;
        if (i2 == 0) {
            PaymentManifestWebDataService paymentManifestWebDataService = this.d;
            N.MX7AwTCa(paymentManifestWebDataService.f10745a, paymentManifestWebDataService);
            paymentManifestWebDataService.f10745a = 0;
            C1096Rz0 rz0 = this.c;
            Objects.requireNonNull(rz0);
            Object obj = ThreadUtils.f10596a;
            if (rz0.f8866a != 0) {
                C1096Rz0 rz02 = this.c;
                Objects.requireNonNull(rz02);
                N.MJUrxDH$(rz02.f8866a, rz02);
                rz02.f8866a = 0;
            }
            PaymentManifestParser paymentManifestParser = this.e;
            Objects.requireNonNull(paymentManifestParser);
            if (paymentManifestParser.f10874a == 0) {
                z = false;
            }
            if (z) {
                PaymentManifestParser paymentManifestParser2 = this.e;
                Objects.requireNonNull(paymentManifestParser2);
                N.MFuu4tOD(paymentManifestParser2.f10874a);
                paymentManifestParser2.f10874a = 0;
            }
        }
    }

    public void f() {
        int i2 = this.q - 1;
        this.q = i2;
        if (i2 == 0) {
            for (Map.Entry entry : this.o.entrySet()) {
                GURL gurl = (GURL) entry.getKey();
                if (this.b.contains(gurl)) {
                    C2126d6 d6Var = (C2126d6) entry.getValue();
                    String i3 = i(gurl);
                    for (ResolveInfo resolveInfo : d6Var.f9747a) {
                        g(resolveInfo, i3);
                    }
                    for (GURL gurl2 : d6Var.b) {
                        Set<GURL> set = (Set) this.m.get(gurl2);
                        if (set != null) {
                            for (GURL gurl3 : set) {
                                C2126d6 d6Var2 = (C2126d6) this.o.get(gurl3);
                                if (d6Var2 != null) {
                                    for (ResolveInfo resolveInfo2 : d6Var2.f9747a) {
                                        g(resolveInfo2, i3);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            d();
        }
    }

    public final void g(ResolveInfo resolveInfo, String str) {
        Z31 z31;
        String str2;
        if (!this.h.d().e()) {
            ActivityInfo activityInfo = resolveInfo.activityInfo;
            String str3 = activityInfo.packageName;
            String[] b2 = b(activityInfo, "org.chromium.payment_supported_delegations");
            if (b2 == null || b2.length == 0) {
                z31 = new Z31();
            } else {
                int min = Math.min(b2.length, 4);
                boolean z = false;
                boolean z2 = false;
                boolean z3 = false;
                boolean z4 = false;
                for (int i2 = 0; i2 < min; i2++) {
                    if (b2[i2] == null) {
                        AbstractC1220Ua0.a("SupportedDelegations", "null is an invalid delegation value. Only [\"shippingAddress\", \"payerName\", \"payerPhone\", \"payerEmail\"] values are possible.", new Object[0]);
                    } else if (b2[i2].equals("shippingAddress")) {
                        z = true;
                    } else if (b2[i2].equals("payerName")) {
                        z2 = true;
                    } else if (b2[i2].equals("payerPhone")) {
                        z3 = true;
                    } else if (b2[i2].equals("payerEmail")) {
                        z4 = true;
                    } else {
                        AbstractC1220Ua0.a("SupportedDelegations", "\"%s\" is an invalid delegation value. Only [\"shippingAddress\", \"payerName\", \"payerPhone\", \"payerEmail\"] values are possible.", b2[i2]);
                    }
                }
                z31 = new Z31(z, z2, z3, z4);
            }
            if (N.M1X7xdZV("EnforceFullDelegation") || str.equals("https://play.google.com/billing")) {
                C1523Yz0 b3 = this.h.d().b();
                if (!(b3 == null || ((!b3.g || z31.f9317a) && ((!b3.d || z31.b) && ((!b3.f || z31.c) && (!b3.e || z31.d)))))) {
                    AbstractC1220Ua0.a("PaymentAppFinder", "Skipping $ for not providing all of the requested PaymentOptions.".replace("$", str3), new Object[0]);
                    return;
                }
            }
            Z5 z5 = (Z5) this.l.get(str3);
            if (z5 == null) {
                Objects.requireNonNull(this.f);
                CharSequence loadLabel = resolveInfo.loadLabel(ContextUtils.getApplicationContext().getPackageManager());
                if (TextUtils.isEmpty(loadLabel)) {
                    AbstractC1220Ua0.a("PaymentAppFinder", "Skipping \"%s\" because of empty label.", str3);
                    return;
                }
                Bundle bundle = resolveInfo.activityInfo.metaData;
                if (bundle == null) {
                    str2 = null;
                } else {
                    str2 = bundle.getString("org.chromium.default_payment_method_name");
                }
                String charSequence = loadLabel.toString();
                Objects.requireNonNull(this.f);
                Z5 z52 = new Z5(new Y5(this.h.d().t()), str3, resolveInfo.activityInfo.name, (String) this.p.get(str3), charSequence, resolveInfo.loadIcon(ContextUtils.getApplicationContext().getPackageManager()), this.j, str2, z31);
                this.l.put(str3, z52);
                z5 = z52;
            }
            z5.n.add(str);
        }
    }
}
