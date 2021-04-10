package X;

import com.google.common.annotations.GwtCompatible;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible
/* renamed from: X.0NM  reason: invalid class name */
public abstract class AnonymousClass0NM<E> extends AbstractC01640ff<E> implements Set<E> {
    /* renamed from: A05 */
    public abstract Set<E> A02();

    public final boolean equals(@NullableDecl Object obj) {
        if (obj == this || A02().equals(obj)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return A02().hashCode();
    }
}
