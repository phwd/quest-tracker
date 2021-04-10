package X;

import java.util.Collection;
import java.util.Iterator;

public final class UB {
    public static void A00(Iterator it) {
        if (it == null) {
            throw null;
        }
        while (it.hasNext()) {
            it.next();
            it.remove();
        }
    }

    public static boolean A01(Collection collection, Iterator it) {
        if (it != null) {
            boolean z = false;
            while (it.hasNext()) {
                z |= collection.add(it.next());
            }
            return z;
        }
        throw null;
    }
}
