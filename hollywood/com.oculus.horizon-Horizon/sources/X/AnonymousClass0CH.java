package X;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Multiset;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible
/* renamed from: X.0CH  reason: invalid class name */
public abstract class AnonymousClass0CH<E> extends AnonymousClass0eD<E> implements AnonymousClass0r9<E> {
    /* renamed from: A02 */
    public abstract AnonymousClass0r9<E> A01();

    @Override // X.AnonymousClass0r9
    public final boolean equals(@NullableDecl Object obj) {
        if (obj == this || A01().equals(obj)) {
            return true;
        }
        return false;
    }

    @Override // X.AnonymousClass0r9
    @CanIgnoreReturnValue
    public final int A11(E e, int i) {
        return A01().A11(e, i);
    }

    @Override // X.AnonymousClass0r9
    public final int A1v(Object obj) {
        return A01().A1v(obj);
    }

    @Override // X.AnonymousClass0r9
    public Set<E> A2N() {
        return A01().A2N();
    }

    @Override // X.AnonymousClass0r9
    @CanIgnoreReturnValue
    public final int A87(Object obj, int i) {
        return A01().A87(obj, i);
    }

    @Override // X.AnonymousClass0r9
    @CanIgnoreReturnValue
    public final int A8d(E e, int i) {
        return A01().A8d(e, i);
    }

    @Override // X.AnonymousClass0r9
    @CanIgnoreReturnValue
    public final boolean A8e(E e, int i, int i2) {
        return A01().A8e(e, i, i2);
    }

    @Override // X.AnonymousClass0r9
    public Set<Multiset.Entry<E>> entrySet() {
        return A01().entrySet();
    }

    @Override // X.AnonymousClass0r9
    public final int hashCode() {
        return A01().hashCode();
    }
}
