package defpackage;

import android.content.Intent;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: N5  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class N5 implements Runnable {
    public final Z5 F;
    public final String G;
    public final String H;
    public final String I;

    /* renamed from: J  reason: collision with root package name */
    public final String f8527J;
    public final byte[][] K;
    public final Map L;
    public final C1035Qz0 M;
    public final List N;
    public final Map O;
    public final C1523Yz0 P;
    public final List Q;

    public N5(Z5 z5, String str, String str2, String str3, String str4, byte[][] bArr, Map map, C1035Qz0 qz0, List list, Map map2, C1523Yz0 yz0, List list2) {
        this.F = z5;
        this.G = str;
        this.H = str2;
        this.I = str3;
        this.f8527J = str4;
        this.K = bArr;
        this.L = map;
        this.M = qz0;
        this.N = list;
        this.O = map2;
        this.P = yz0;
        this.Q = list2;
    }

    public void run() {
        Kx1 kx1;
        byte[][] bArr;
        HashMap hashMap;
        ArrayList arrayList;
        Ix1 ix1;
        ArrayList arrayList2;
        HashMap hashMap2;
        ArrayList arrayList3;
        Iterator it;
        Lx1 lx1;
        Ix1 ix12;
        ArrayList arrayList4;
        Iterator it2;
        Hx1 hx1;
        Jx1 jx1;
        byte[][] bArr2;
        Iterator it3;
        Jx1 jx12;
        String str;
        String str2;
        Z5 z5 = this.F;
        String str3 = this.G;
        String str4 = this.H;
        String str5 = this.I;
        String str6 = this.f8527J;
        byte[][] bArr3 = this.K;
        Map map = this.L;
        C1035Qz0 qz0 = this.M;
        List<C1035Qz0> list = this.N;
        Map map2 = this.O;
        C1523Yz0 yz0 = this.P;
        List list2 = this.Q;
        if (yz0 == null) {
            kx1 = null;
        } else {
            boolean z = yz0.g;
            if (z) {
                int i = yz0.h;
                if (i == 0) {
                    str2 = "shipping";
                } else if (i == 1) {
                    str2 = "delivery";
                } else if (i == 2) {
                    str2 = "pickup";
                }
                str = str2;
                kx1 = new Kx1(yz0.d, yz0.e, yz0.f, z, str);
            }
            str = null;
            kx1 = new Kx1(yz0.d, yz0.e, yz0.f, z, str);
        }
        z5.z = kx1;
        String str7 = z5.p;
        String str8 = z5.q;
        if (map == null) {
            bArr = bArr3;
            hashMap = null;
        } else {
            HashMap hashMap3 = new HashMap();
            Iterator it4 = map.entrySet().iterator();
            while (it4.hasNext()) {
                Map.Entry entry = (Map.Entry) it4.next();
                String str9 = (String) entry.getKey();
                C1401Wz0 wz0 = (C1401Wz0) entry.getValue();
                if (wz0 == null) {
                    bArr2 = bArr3;
                    it3 = it4;
                    jx12 = null;
                } else {
                    it3 = it4;
                    bArr2 = bArr3;
                    jx12 = new Jx1(wz0.d, wz0.e);
                }
                hashMap3.put(str9, jx12);
                it4 = it3;
                bArr3 = bArr2;
            }
            bArr = bArr3;
            hashMap = hashMap3;
        }
        Ix1 b = Mx1.b(qz0);
        if (list == null) {
            arrayList = null;
        } else {
            ArrayList arrayList5 = new ArrayList();
            for (C1035Qz0 qz02 : list) {
                arrayList5.add(Mx1.b(qz02));
            }
            arrayList = arrayList5;
        }
        if (map2 == null) {
            ix1 = b;
            arrayList2 = arrayList;
            hashMap2 = null;
        } else {
            HashMap hashMap4 = new HashMap();
            Iterator it5 = map2.entrySet().iterator();
            while (it5.hasNext()) {
                Map.Entry entry2 = (Map.Entry) it5.next();
                String str10 = (String) entry2.getKey();
                C2959hz0 hz0 = (C2959hz0) entry2.getValue();
                if (hz0 == null) {
                    ix12 = b;
                    arrayList4 = arrayList;
                    it2 = it5;
                    hx1 = null;
                } else {
                    it2 = it5;
                    Ix1 b2 = Mx1.b(hz0.d);
                    C1401Wz0 wz02 = hz0.f;
                    if (wz02 == null) {
                        ix12 = b;
                        arrayList4 = arrayList;
                        jx1 = null;
                    } else {
                        arrayList4 = arrayList;
                        ix12 = b;
                        jx1 = new Jx1(wz02.d, wz02.e);
                    }
                    hx1 = new Hx1(b2, jx1);
                }
                hashMap4.put(str10, hx1);
                it5 = it2;
                arrayList = arrayList4;
                b = ix12;
            }
            ix1 = b;
            arrayList2 = arrayList;
            hashMap2 = hashMap4;
        }
        Kx1 kx12 = z5.z;
        if (list2 == null) {
            arrayList3 = null;
        } else {
            ArrayList arrayList6 = new ArrayList();
            Iterator it6 = list2.iterator();
            while (it6.hasNext()) {
                C3337kB0 kb0 = (C3337kB0) it6.next();
                if (kb0 == null) {
                    it = it6;
                    lx1 = null;
                } else {
                    it = it6;
                    lx1 = new Lx1(kb0.d, kb0.e, Mx1.a(kb0.f), kb0.g);
                }
                arrayList6.add(lx1);
                it6 = it;
            }
            arrayList3 = arrayList6;
        }
        Intent intent = new Intent();
        Fx1.d(str8, "activityName");
        Fx1.d(str7, "packageName");
        intent.setClassName(str7, str8);
        intent.setAction("org.chromium.intent.action.PAY");
        Fx1.d(str3, "id");
        Fx1.c(str4, "merchantName");
        Fx1.d(str5, "schemelessOrigin");
        Fx1.d(str6, "schemelessIframeOrigin");
        Fx1.b(hashMap, "methodDataMap");
        Fx1.c(ix1, "total");
        if (kx12 == null || !kx12.d || (arrayList3 != null && !arrayList3.isEmpty())) {
            intent.putExtras(Fx1.a(str3, str4, str5, str6, bArr, hashMap, ix1, arrayList2, hashMap2, kx12, arrayList3));
            U5 u5 = z5.m;
            P5 p5 = new P5(z5);
            Y5 y5 = (Y5) u5;
            if (y5.f9250a.g()) {
                z5.G("Unable to invoke the payment app.");
                return;
            }
            WindowAndroid I2 = y5.f9250a.I();
            if (I2 == null) {
                z5.G("Unable to invoke the payment app.");
                return;
            }
            y5.b = p5;
            try {
                if (!(I2.F0(intent, y5, Integer.valueOf(R.string.f58440_resource_name_obfuscated_RES_2131953161)) >= 0)) {
                    z5.G("Unable to invoke the payment app.");
                }
            } catch (SecurityException unused) {
                z5.G("Payment app does not have android:exported=\"true\" on the PAY activity.");
            }
        } else {
            throw new IllegalArgumentException("shippingOptions should not be null or empty when shipping is requested.");
        }
    }
}
