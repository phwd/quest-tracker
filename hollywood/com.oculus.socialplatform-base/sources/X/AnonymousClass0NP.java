package X;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Multiset;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible
/* renamed from: X.0NP  reason: invalid class name */
public abstract class AnonymousClass0NP<E> extends AbstractC01640ff<E> implements AbstractC05490vp<E> {
    /* renamed from: A05 */
    public abstract AbstractC05490vp<E> A02();

    @Override // X.AbstractC01640ff
    public final boolean A03(Collection<?> collection) {
        if (collection != null) {
            if (collection instanceof AbstractC05490vp) {
                collection = ((AbstractC05490vp) collection).A2k();
            }
            return A2k().retainAll(collection);
        }
        throw null;
    }

    @Override // X.AbstractC05490vp
    public final boolean equals(@NullableDecl Object obj) {
        if (obj == this || A02().equals(obj)) {
            return true;
        }
        return false;
    }

    @Override // X.AbstractC01640ff
    public final String A01() {
        return entrySet().toString();
    }

    @Override // X.AbstractC05490vp
    @CanIgnoreReturnValue
    public final int A1A(E e, int i) {
        return A02().A1A(e, i);
    }

    @Override // X.AbstractC05490vp
    public final int A2J(Object obj) {
        return A02().A2J(obj);
    }

    @Override // X.AbstractC05490vp
    public Set<E> A2k() {
        return A02().A2k();
    }

    @Override // X.AbstractC05490vp
    @CanIgnoreReturnValue
    public final int A92(Object obj, int i) {
        return A02().A92(obj, i);
    }

    @Override // X.AbstractC05490vp
    @CanIgnoreReturnValue
    public final int A9n(E e, int i) {
        return A02().A9n(e, i);
    }

    @Override // X.AbstractC05490vp
    @CanIgnoreReturnValue
    public final boolean A9o(E e, int i, int i2) {
        return A02().A9o(e, i, i2);
    }

    @Override // X.AbstractC05490vp
    public Set<Multiset.Entry<E>> entrySet() {
        return A02().entrySet();
    }

    @Override // X.AbstractC05490vp
    public final int hashCode() {
        return A02().hashCode();
    }
}
