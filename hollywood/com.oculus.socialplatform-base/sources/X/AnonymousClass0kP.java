package X;

import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

/* renamed from: X.0kP  reason: invalid class name */
public final class AnonymousClass0kP {
    public static AnonymousClass0kO A00() {
        Set<C02920k8> set = AnonymousClass0k6.A11;
        Set<String> set2 = C02860jx.A00;
        HashMap hashMap = new HashMap();
        for (C02920k8 r1 : set) {
            hashMap.put(r1, Collections.unmodifiableSet(set2));
        }
        return new AnonymousClass0kO(Collections.unmodifiableMap(hashMap));
    }
}
