package X;

import com.google.common.annotations.GwtCompatible;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@GwtCompatible(emulated = true)
/* renamed from: X.0wE  reason: invalid class name */
public final class AnonymousClass0wE {
    public static <E> HashSet<E> A01(int i) {
        int i2;
        if (i < 3) {
            AnonymousClass0th.A00(i, "expectedSize");
            i2 = i + 1;
        } else if (i < 1073741824) {
            i2 = (int) ((((float) i) / 0.75f) + 1.0f);
        } else {
            i2 = Integer.MAX_VALUE;
        }
        return new HashSet<>(i2);
    }

    public static boolean A02(Set<?> set, Collection<?> collection) {
        boolean z;
        if (collection != null) {
            if (collection instanceof AbstractC05490vp) {
                collection = ((AbstractC05490vp) collection).A2k();
            }
            if (!(collection instanceof Set) || collection.size() <= set.size()) {
                Iterator<?> it = collection.iterator();
                z = false;
                while (it.hasNext()) {
                    z |= set.remove(it.next());
                }
            } else {
                Iterator<?> it2 = set.iterator();
                z = false;
                while (it2.hasNext()) {
                    if (collection.contains(it2.next())) {
                        it2.remove();
                        z = true;
                    }
                }
            }
            return z;
        }
        throw null;
    }

    public static int A00(Set<?> set) {
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
