package X;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

/* renamed from: X.0fz  reason: invalid class name and case insensitive filesystem */
public final class C03730fz extends AbstractC05920m7 implements Serializable {
    public static final long serialVersionUID = 1;
    public final LinkedHashSet<C05910m6> _registeredSubtypes;

    private final void A00(C03810gA r4, C05910m6 r5, AbstractC03910gQ<?> r6, AbstractC04040gi r7, HashMap<C05910m6, C05910m6> hashMap) {
        String A0U;
        if (r5._name == null && (A0U = r7.A0U(r4)) != null) {
            r5 = new C05910m6(r5._class, A0U);
        }
        if (!hashMap.containsKey(r5)) {
            hashMap.put(r5, r5);
            List<C05910m6> A0a = r7.A0a(r4);
            if (!(A0a == null || A0a.isEmpty())) {
                for (C05910m6 r52 : A0a) {
                    C03810gA A01 = C03810gA.A01(r52._class, r7, r6);
                    if (r52._name == null) {
                        r52 = new C05910m6(r52._class, r7.A0U(A01));
                    }
                    A00(A01, r52, r6, r7, hashMap);
                }
            }
        } else if (r5._name != null && hashMap.get(r5)._name == null) {
            hashMap.put(r5, r5);
        }
    }

    @Override // X.AbstractC05920m7
    public final Collection<C05910m6> A01(C03810gA r16, AbstractC03910gQ<?> r17, AbstractC04040gi r18) {
        HashMap<C05910m6, C05910m6> hashMap = new HashMap<>();
        LinkedHashSet<C05910m6> linkedHashSet = this._registeredSubtypes;
        if (linkedHashSet != null) {
            Class<?> A0J = r16.A0J();
            Iterator<C05910m6> it = linkedHashSet.iterator();
            while (it.hasNext()) {
                C05910m6 next = it.next();
                if (A0J.isAssignableFrom(next._class)) {
                    A00(C03810gA.A01(next._class, r18, r17), next, r17, r18, hashMap);
                }
            }
        }
        A00(r16, new C05910m6(r16.A0J(), null), r17, r18, hashMap);
        return new ArrayList(hashMap.values());
    }

    @Override // X.AbstractC05920m7
    public final Collection<C05910m6> A02(AnonymousClass0g9 r10, AbstractC03910gQ<?> r11, AbstractC04040gi r12, AbstractC04000gb r13) {
        Class<?> cls;
        if (r13 == null) {
            cls = r10.A0J();
        } else {
            cls = r13._class;
        }
        HashMap<C05910m6, C05910m6> hashMap = new HashMap<>();
        LinkedHashSet<C05910m6> linkedHashSet = this._registeredSubtypes;
        if (linkedHashSet != null) {
            Iterator<C05910m6> it = linkedHashSet.iterator();
            while (it.hasNext()) {
                C05910m6 next = it.next();
                if (cls.isAssignableFrom(next._class)) {
                    A00(C03810gA.A01(next._class, r12, r11), next, r11, r12, hashMap);
                }
            }
        }
        List<C05910m6> A0a = r12.A0a(r10);
        if (A0a != null) {
            for (C05910m6 r5 : A0a) {
                A00(C03810gA.A01(r5._class, r12, r11), r5, r11, r12, hashMap);
            }
        }
        A00(C03810gA.A01(cls, r12, r11), new C05910m6(cls, null), r11, r12, hashMap);
        return new ArrayList(hashMap.values());
    }
}
