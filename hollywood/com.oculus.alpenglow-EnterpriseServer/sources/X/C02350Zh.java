package X;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

/* renamed from: X.0Zh  reason: invalid class name and case insensitive filesystem */
public final class C02350Zh extends AnonymousClass0o1 implements Serializable {
    public static final long serialVersionUID = 1;
    public final LinkedHashSet<AnonymousClass0o0> _registeredSubtypes;

    private final void A00(C02460Zs r4, AnonymousClass0o0 r5, AnonymousClass0a7<?> r6, AbstractC02590aM r7, HashMap<AnonymousClass0o0, AnonymousClass0o0> hashMap) {
        String A0h;
        if (r5._name == null && (A0h = r7.A0h(r4)) != null) {
            r5 = new AnonymousClass0o0(r5._class, A0h);
        }
        if (!hashMap.containsKey(r5)) {
            hashMap.put(r5, r5);
            List<AnonymousClass0o0> A0n = r7.A0n(r4);
            if (!(A0n == null || A0n.isEmpty())) {
                for (AnonymousClass0o0 r52 : A0n) {
                    C02460Zs A01 = C02460Zs.A01(r52._class, r7, r6);
                    if (r52._name == null) {
                        r52 = new AnonymousClass0o0(r52._class, r7.A0h(A01));
                    }
                    A00(A01, r52, r6, r7, hashMap);
                }
            }
        } else if (r5._name != null && hashMap.get(r5)._name == null) {
            hashMap.put(r5, r5);
        }
    }

    @Override // X.AnonymousClass0o1
    public final Collection<AnonymousClass0o0> A01(C02460Zs r16, AnonymousClass0a7<?> r17, AbstractC02590aM r18) {
        HashMap<AnonymousClass0o0, AnonymousClass0o0> hashMap = new HashMap<>();
        LinkedHashSet<AnonymousClass0o0> linkedHashSet = this._registeredSubtypes;
        if (linkedHashSet != null) {
            Class<?> A0K = r16.A0K();
            Iterator<AnonymousClass0o0> it = linkedHashSet.iterator();
            while (it.hasNext()) {
                AnonymousClass0o0 next = it.next();
                if (A0K.isAssignableFrom(next._class)) {
                    A00(C02460Zs.A01(next._class, r18, r17), next, r17, r18, hashMap);
                }
            }
        }
        A00(r16, new AnonymousClass0o0(r16.A0K(), null), r17, r18, hashMap);
        return new ArrayList(hashMap.values());
    }

    @Override // X.AnonymousClass0o1
    public final Collection<AnonymousClass0o0> A02(AbstractC02450Zr r10, AnonymousClass0a7<?> r11, AbstractC02590aM r12, AnonymousClass0aI r13) {
        Class<?> cls;
        if (r13 == null) {
            cls = r10.A0K();
        } else {
            cls = r13._class;
        }
        HashMap<AnonymousClass0o0, AnonymousClass0o0> hashMap = new HashMap<>();
        LinkedHashSet<AnonymousClass0o0> linkedHashSet = this._registeredSubtypes;
        if (linkedHashSet != null) {
            Iterator<AnonymousClass0o0> it = linkedHashSet.iterator();
            while (it.hasNext()) {
                AnonymousClass0o0 next = it.next();
                if (cls.isAssignableFrom(next._class)) {
                    A00(C02460Zs.A01(next._class, r12, r11), next, r11, r12, hashMap);
                }
            }
        }
        List<AnonymousClass0o0> A0n = r12.A0n(r10);
        if (A0n != null) {
            for (AnonymousClass0o0 r5 : A0n) {
                A00(C02460Zs.A01(r5._class, r12, r11), r5, r11, r12, hashMap);
            }
        }
        A00(C02460Zs.A01(cls, r12, r11), new AnonymousClass0o0(cls, null), r11, r12, hashMap);
        return new ArrayList(hashMap.values());
    }
}
