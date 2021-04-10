package X;

import com.google.common.annotations.GwtCompatible;
import java.util.Iterator;

@GwtCompatible(emulated = true)
/* renamed from: X.0qE  reason: invalid class name and case insensitive filesystem */
public final class C06900qE {
    public static String A00(Iterable<?> iterable) {
        Iterator<?> it = iterable.iterator();
        StringBuilder sb = new StringBuilder("[");
        boolean z = true;
        while (it.hasNext()) {
            if (!z) {
                sb.append(", ");
            }
            z = false;
            sb.append(it.next());
        }
        sb.append(']');
        return sb.toString();
    }
}
