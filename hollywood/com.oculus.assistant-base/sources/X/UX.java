package X;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public final class UX {
    public static HashSet A01(int i) {
        int i2;
        if (i < 3) {
            C0361Tm.A00(i, "expectedSize");
            i2 = i + 1;
        } else if (i < 1073741824) {
            i2 = (int) ((((float) i) / 0.75f) + 1.0f);
        } else {
            i2 = Integer.MAX_VALUE;
        }
        return new HashSet(i2);
    }

    public static boolean A02(Set set, Collection collection) {
        boolean z;
        if (collection != null) {
            if (collection instanceof UM) {
                collection = ((UM) collection).A1m();
            }
            if (!(collection instanceof Set) || collection.size() <= set.size()) {
                z = false;
                for (Object obj : collection) {
                    z |= set.remove(obj);
                }
            } else {
                Iterator it = set.iterator();
                z = false;
                while (it.hasNext()) {
                    if (collection.contains(it.next())) {
                        it.remove();
                        z = true;
                    }
                }
            }
            return z;
        }
        throw null;
    }

    public static int A00(Set set) {
        int i;
        int i2 = 0;
        for (Object obj : set) {
            if (obj != null) {
                i = obj.hashCode();
            } else {
                i = 0;
            }
            i2 = ((i2 + i) ^ -1) ^ -1;
        }
        return i2;
    }
}
