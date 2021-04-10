package X;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.Iterator;

@GwtCompatible(emulated = true)
/* renamed from: X.0uz  reason: invalid class name and case insensitive filesystem */
public final class C05250uz {
    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: java.util.Collection<T> */
    /* JADX WARN: Multi-variable type inference failed */
    @CanIgnoreReturnValue
    public static <T> boolean A00(Collection<T> collection, Iterator<? extends T> it) {
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
