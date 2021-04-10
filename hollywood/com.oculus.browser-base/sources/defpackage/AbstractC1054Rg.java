package defpackage;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* renamed from: Rg  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC1054Rg {
    public static Set a(C1401Wz0 wz0) {
        HashMap hashMap = new HashMap();
        int i = 0;
        hashMap.put(0, "amex");
        boolean z = true;
        hashMap.put(1, "diners");
        hashMap.put(2, "discover");
        hashMap.put(3, "jcb");
        hashMap.put(4, "mastercard");
        hashMap.put(5, "mir");
        hashMap.put(6, "unionpay");
        hashMap.put(7, "visa");
        int[] iArr = wz0.j;
        if (iArr == null || iArr.length == 0) {
            z = false;
        }
        if (!z) {
            return new HashSet(hashMap.values());
        }
        HashSet hashSet = new HashSet();
        while (true) {
            int[] iArr2 = wz0.j;
            if (i >= iArr2.length) {
                return hashSet;
            }
            String str = (String) hashMap.get(Integer.valueOf(iArr2[i]));
            if (str != null) {
                hashSet.add(str);
            }
            i++;
        }
    }

    public static boolean b(Map map) {
        C1401Wz0 wz0 = (C1401Wz0) map.get("basic-card");
        return wz0 != null && !((HashSet) a(wz0)).isEmpty();
    }
}
