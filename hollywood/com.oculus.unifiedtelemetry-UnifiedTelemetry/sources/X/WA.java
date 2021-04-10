package X;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

public final class WA extends V6 implements Serializable {
    public static final long serialVersionUID = 1;
    public final LinkedHashSet<V7> _registeredSubtypes;

    private final void A00(WK wk, V7 v7, WZ<?> wz, Wp wp, HashMap<V7, V7> hashMap) {
        String A0V;
        if (v7._name == null && (A0V = wp.A0V(wk)) != null) {
            v7 = new V7(v7._class, A0V);
        }
        if (!hashMap.containsKey(v7)) {
            hashMap.put(v7, v7);
            List<V7> A0b = wp.A0b(wk);
            if (!(A0b == null || A0b.isEmpty())) {
                for (V7 v72 : A0b) {
                    WK A01 = WK.A01(v72._class, wp, wz);
                    if (v72._name == null) {
                        v72 = new V7(v72._class, wp.A0V(A01));
                    }
                    A00(A01, v72, wz, wp, hashMap);
                }
            }
        } else if (v7._name != null && hashMap.get(v7)._name == null) {
            hashMap.put(v7, v7);
        }
    }

    @Override // X.V6
    public final Collection<V7> A01(WK wk, WZ<?> wz, Wp wp) {
        HashMap<V7, V7> hashMap = new HashMap<>();
        LinkedHashSet<V7> linkedHashSet = this._registeredSubtypes;
        if (linkedHashSet != null) {
            Class<?> A0J = wk.A0J();
            Iterator<V7> it = linkedHashSet.iterator();
            while (it.hasNext()) {
                V7 next = it.next();
                if (A0J.isAssignableFrom(next._class)) {
                    A00(WK.A01(next._class, wp, wz), next, wz, wp, hashMap);
                }
            }
        }
        A00(wk, new V7(wk.A0J(), null), wz, wp, hashMap);
        return new ArrayList(hashMap.values());
    }

    @Override // X.V6
    public final Collection<V7> A02(WJ wj, WZ<?> wz, Wp wp, AbstractC0224Wl wl) {
        Class<?> cls;
        if (wl == null) {
            cls = wj.A0J();
        } else {
            cls = wl._class;
        }
        HashMap<V7, V7> hashMap = new HashMap<>();
        LinkedHashSet<V7> linkedHashSet = this._registeredSubtypes;
        if (linkedHashSet != null) {
            Iterator<V7> it = linkedHashSet.iterator();
            while (it.hasNext()) {
                V7 next = it.next();
                if (cls.isAssignableFrom(next._class)) {
                    A00(WK.A01(next._class, wp, wz), next, wz, wp, hashMap);
                }
            }
        }
        List<V7> A0b = wp.A0b(wj);
        if (A0b != null) {
            for (V7 v7 : A0b) {
                A00(WK.A01(v7._class, wp, wz), v7, wz, wp, hashMap);
            }
        }
        A00(WK.A01(cls, wp, wz), new V7(cls, null), wz, wp, hashMap);
        return new ArrayList(hashMap.values());
    }
}
