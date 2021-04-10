package defpackage;

import java.util.HashSet;
import java.util.Iterator;

/* renamed from: u50  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC5026u50 {
    public static boolean a(C1794b90 b90, int[] iArr) {
        HashSet hashSet = new HashSet(iArr.length);
        for (int i : iArr) {
            hashSet.add(Integer.valueOf(i));
        }
        Iterator it = b90.iterator();
        while (it.hasNext()) {
            C2636g50 g50 = ((G50) it.next()).b;
            if (g50 != null && hashSet.contains(Integer.valueOf(g50.c))) {
                return true;
            }
        }
        return false;
    }
}
