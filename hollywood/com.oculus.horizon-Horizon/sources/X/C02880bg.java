package X;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/* renamed from: X.0bg  reason: invalid class name and case insensitive filesystem */
public final class C02880bg {
    public static C02870bf A00() {
        Set<AnonymousClass0bQ> set = C02780bN.A11;
        Set<String> set2 = C02710bE.A00;
        HashMap hashMap = new HashMap();
        for (AnonymousClass0bQ r1 : set) {
            hashMap.put(r1, Collections.unmodifiableSet(set2));
        }
        return new C02870bf(Collections.unmodifiableMap(hashMap));
    }

    public static C02870bf A01(Set<AnonymousClass0bQ> set) {
        HashMap hashMap = new HashMap();
        for (AnonymousClass0bQ r2 : set) {
            hashMap.put(r2, Collections.unmodifiableSet(new HashSet(Collections.singletonList("*|all_packages|*"))));
        }
        return new C02870bf(Collections.unmodifiableMap(hashMap));
    }

    public static C02870bf A02(Set<AnonymousClass0bQ> set, Set<String> set2) {
        HashMap hashMap = new HashMap();
        for (AnonymousClass0bQ r1 : set) {
            hashMap.put(r1, Collections.unmodifiableSet(set2));
        }
        return new C02870bf(Collections.unmodifiableMap(hashMap));
    }
}
