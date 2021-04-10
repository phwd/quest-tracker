package defpackage;

import J.N;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.content.pm.Signature;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import org.chromium.base.ContextUtils;
import org.chromium.base.PackageManagerUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.payments.PaymentManifestWebDataService;
import org.chromium.components.payments.PaymentManifestParser;
import org.chromium.content_public.browser.WebContents;
import org.chromium.url.GURL;

/* renamed from: a6  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1604a6 implements AbstractC1521Yy0 {
    /* JADX INFO: finally extract failed */
    @Override // defpackage.AbstractC1521Yy0
    public void a(AbstractC1460Xy0 xy0) {
        int i;
        String str;
        GURL gurl;
        GURL gurl2;
        ResolveInfo resolveInfo;
        GURL gurl3;
        String str2;
        C2296e6 e6Var = new C2296e6(new PaymentManifestWebDataService(), new C1096Rz0(), new PaymentManifestParser(), new C3456ku0(), new Ao1(), xy0, this);
        if (!e6Var.h.d().e()) {
            HashSet hashSet = new HashSet();
            hashSet.add("basic-card");
            hashSet.add("interledger");
            hashSet.add("payee-credit-transfer");
            hashSet.add("payer-credit-transfer");
            hashSet.add("tokenized-card");
            for (String str3 : e6Var.h.d().h().keySet()) {
                if (!e6Var.k.containsValue(new GURL(str3))) {
                    if (hashSet.contains(str3)) {
                        e6Var.f9831a.add(str3);
                    } else {
                        GURL gurl4 = new GURL(str3);
                        if (AbstractC5324vr1.a(gurl4)) {
                            e6Var.b.add(gurl4);
                        }
                    }
                }
            }
            C3456ku0 ku0 = e6Var.f;
            Intent intent = new Intent("org.chromium.intent.action.PAY");
            Objects.requireNonNull(ku0);
            List c = PackageManagerUtils.c(intent, 128);
            if (c.isEmpty()) {
                e6Var.d();
                return;
            }
            if (!e6Var.j) {
                C3456ku0 ku02 = e6Var.f;
                Intent intent2 = new Intent("org.chromium.intent.action.IS_READY_TO_PAY");
                Objects.requireNonNull(ku02);
                StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
                try {
                    List<ResolveInfo> queryIntentServices = ContextUtils.getApplicationContext().getPackageManager().queryIntentServices(intent2, 0);
                    StrictMode.setThreadPolicy(allowThreadDiskReads);
                    int size = queryIntentServices.size();
                    for (int i2 = 0; i2 < size; i2++) {
                        ServiceInfo serviceInfo = queryIntentServices.get(i2).serviceInfo;
                        e6Var.p.put(serviceInfo.packageName, serviceInfo.name);
                    }
                } catch (Throwable th) {
                    StrictMode.setThreadPolicy(allowThreadDiskReads);
                    throw th;
                }
            }
            if (!AbstractC1584Zz0.a(e6Var.h.d().b()) && N.M1X7xdZV("AppStoreBilling")) {
                String s = e6Var.h.d().s();
                if (!TextUtils.isEmpty(s)) {
                    int i3 = 0;
                    while (true) {
                        if (i3 >= c.size()) {
                            resolveInfo = null;
                            break;
                        }
                        resolveInfo = (ResolveInfo) c.get(i3);
                        if (s.equals(resolveInfo.activityInfo.packageName)) {
                            break;
                        }
                        i3++;
                    }
                    if (resolveInfo != null) {
                        ArrayList arrayList = new ArrayList();
                        for (GURL gurl5 : e6Var.k.values()) {
                            String h = C2296e6.h(gurl5.h());
                            if (e6Var.h.d().h().containsKey(h)) {
                                Bundle bundle = resolveInfo.activityInfo.metaData;
                                if (bundle == null) {
                                    str2 = null;
                                } else {
                                    str2 = bundle.getString("org.chromium.default_payment_method_name");
                                }
                                if (((HashSet) e6Var.c(resolveInfo.activityInfo)).contains(gurl5.h()) || gurl5.equals(new GURL(str2))) {
                                    arrayList.add(h);
                                }
                            }
                        }
                        if (!N.M1X7xdZV("AppStoreBillingDebug")) {
                            Objects.requireNonNull(e6Var.g);
                            String installerPackageName = ContextUtils.getApplicationContext().getPackageManager().getInstallerPackageName(s);
                            if (!(installerPackageName == null || (gurl3 = (GURL) e6Var.k.get(installerPackageName)) == null)) {
                                String h2 = gurl3.h();
                                if (arrayList.contains(h2)) {
                                    e6Var.g(resolveInfo, h2);
                                }
                            }
                        } else {
                            Iterator it = arrayList.iterator();
                            while (it.hasNext()) {
                                e6Var.g(resolveInfo, (String) it.next());
                            }
                        }
                        Z5 z5 = (Z5) e6Var.l.get(s);
                        if (z5 != null) {
                            z5.y = true;
                        }
                    }
                }
            }
            HashSet hashSet2 = new HashSet(e6Var.b);
            HashMap hashMap = new HashMap();
            HashMap hashMap2 = new HashMap();
            HashMap hashMap3 = new HashMap();
            for (int i4 = 0; i4 < c.size(); i4++) {
                ResolveInfo resolveInfo2 = (ResolveInfo) c.get(i4);
                Bundle bundle2 = resolveInfo2.activityInfo.metaData;
                if (bundle2 == null) {
                    str = null;
                } else {
                    str = bundle2.getString("org.chromium.default_payment_method_name");
                }
                if (!TextUtils.isEmpty(str)) {
                    gurl = new GURL(str);
                    if (!e6Var.k.values().contains(gurl)) {
                        if (AbstractC5324vr1.a(gurl)) {
                            str = C2296e6.i(gurl);
                        }
                        if (!hashMap.containsKey(str)) {
                            hashMap.put(str, new HashSet());
                        }
                        ((Set) hashMap.get(str)).add(resolveInfo2);
                        if (AbstractC5324vr1.a(gurl)) {
                            hashSet2.add(gurl);
                            if (!hashMap2.containsKey(gurl)) {
                                hashMap2.put(gurl, new HashSet());
                            }
                            ((Set) hashMap2.get(gurl)).add(resolveInfo2);
                            gurl2 = new GURL();
                            N.MNBd3mFA(gurl.f11029a, gurl.b, gurl.c.c(), gurl2);
                            if (!e6Var.m.containsKey(gurl2)) {
                                e6Var.m.put(gurl2, new HashSet());
                            }
                            ((Set) e6Var.m.get(gurl2)).add(gurl);
                        } else {
                            gurl2 = null;
                        }
                    }
                } else {
                    gurl2 = null;
                    gurl = null;
                }
                Iterator it2 = ((HashSet) e6Var.c(resolveInfo2.activityInfo)).iterator();
                while (it2.hasNext()) {
                    String str4 = (String) it2.next();
                    GURL gurl6 = new GURL(str4);
                    if (!AbstractC5324vr1.a(gurl6)) {
                        gurl6 = null;
                    }
                    if ((gurl6 == null || !gurl6.equals(gurl)) && !e6Var.k.values().contains(gurl6)) {
                        if (!hashMap.containsKey(str4)) {
                            hashMap.put(str4, new HashSet());
                        }
                        ((Set) hashMap.get(str4)).add(resolveInfo2);
                        if (gurl6 != null) {
                            if (!e6Var.n.containsKey(gurl6)) {
                                e6Var.n.put(gurl6, new HashSet());
                            }
                            ((Set) e6Var.n.get(gurl6)).add(resolveInfo2);
                            if (gurl2 != null) {
                                if (!hashMap3.containsKey(gurl6)) {
                                    hashMap3.put(gurl6, new HashSet());
                                }
                                ((Set) hashMap3.get(gurl6)).add(gurl2);
                            }
                        }
                    }
                }
            }
            ArrayList arrayList2 = new ArrayList();
            Iterator it3 = hashSet2.iterator();
            while (true) {
                if (!it3.hasNext()) {
                    i = 0;
                    break;
                }
                GURL gurl7 = (GURL) it3.next();
                if (hashMap.containsKey(C2296e6.i(gurl7))) {
                    PaymentManifestParser paymentManifestParser = e6Var.e;
                    Objects.requireNonNull(paymentManifestParser);
                    Object obj = ThreadUtils.f10596a;
                    if (!(paymentManifestParser.f10874a != 0)) {
                        PaymentManifestParser paymentManifestParser2 = e6Var.e;
                        WebContents t = e6Var.h.d().t();
                        Objects.requireNonNull(paymentManifestParser2);
                        paymentManifestParser2.f10874a = N.MfK4x$Iq(t);
                    }
                    C1096Rz0 rz0 = e6Var.c;
                    Objects.requireNonNull(rz0);
                    if (!(rz0.f8866a != 0)) {
                        C1096Rz0 rz02 = e6Var.c;
                        WebContents t2 = e6Var.h.d().t();
                        Objects.requireNonNull(rz02);
                        rz02.f8866a = N.MzoXDvTe(t2);
                    }
                    arrayList2.add(new C1340Vz0(e6Var.h.d().o(), gurl7, (Set) hashMap2.get(gurl7), (Set) hashMap3.get(gurl7), e6Var.d, e6Var.c, e6Var.e, e6Var.f, e6Var));
                    if (arrayList2.size() == 10) {
                        i = 0;
                        AbstractC1220Ua0.a("PaymentAppFinder", "Reached maximum number of allowed payment app manifests.", new Object[0]);
                        break;
                    }
                    it3 = it3;
                }
            }
            for (String str5 : e6Var.f9831a) {
                if (hashMap.containsKey(str5)) {
                    for (ResolveInfo resolveInfo3 : (Set) hashMap.get(str5)) {
                        e6Var.g(resolveInfo3, str5);
                    }
                }
            }
            if (arrayList2.isEmpty()) {
                e6Var.d();
                return;
            }
            int size2 = arrayList2.size();
            e6Var.s = size2;
            e6Var.q = size2;
            Iterator it4 = arrayList2.iterator();
            while (it4.hasNext()) {
                C1340Vz0 vz0 = (C1340Vz0) it4.next();
                if (vz0.c.isEmpty() || vz0.l != null) {
                    ArrayList arrayList3 = new ArrayList();
                    for (Map.Entry entry : vz0.c.entrySet()) {
                        String str6 = (String) entry.getKey();
                        C1218Tz0 tz0 = (C1218Tz0) entry.getValue();
                        PackageInfo a2 = vz0.j.a(str6);
                        if (a2 == null) {
                            arrayList3.add(str6);
                        } else {
                            tz0.b = (long) a2.versionCode;
                            tz0.c = new HashSet();
                            Signature[] signatureArr = a2.signatures;
                            for (int i5 = i; i5 < signatureArr.length; i5++) {
                                vz0.l.update(signatureArr[i5].toByteArray());
                                tz0.c.add(C1340Vz0.a(vz0.l.digest()));
                            }
                        }
                    }
                    for (int i6 = i; i6 < arrayList3.size(); i6++) {
                        vz0.c.remove(arrayList3.get(i6));
                    }
                    PaymentManifestWebDataService paymentManifestWebDataService = vz0.g;
                    if (!N.M8p9RlSH(paymentManifestWebDataService.f10745a, paymentManifestWebDataService, vz0.b.toString(), vz0)) {
                        vz0.o = true;
                        vz0.h.a(vz0.f9120a, vz0.b, vz0);
                    }
                } else {
                    ((C2296e6) vz0.k).f();
                    ((C2296e6) vz0.k).e();
                }
            }
        }
    }
}
