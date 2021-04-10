package X;

import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

/* renamed from: X.0iy  reason: invalid class name and case insensitive filesystem */
public final class C05290iy {
    public static C05280ix A00() {
        Set<C05140ii> set = C05120ig.A11;
        Set<String> set2 = AnonymousClass0iX.A00;
        HashMap hashMap = new HashMap();
        for (C05140ii r1 : set) {
            hashMap.put(r1, Collections.unmodifiableSet(set2));
        }
        return new C05280ix(Collections.unmodifiableMap(hashMap));
    }

    public static C05280ix A01(Set<C05140ii> set, Set<String> set2) {
        HashMap hashMap = new HashMap();
        for (C05140ii r1 : set) {
            hashMap.put(r1, Collections.unmodifiableSet(set2));
        }
        return new C05280ix(Collections.unmodifiableMap(hashMap));
    }
}
