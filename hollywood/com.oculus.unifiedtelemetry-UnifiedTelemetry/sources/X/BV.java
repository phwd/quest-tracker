package X;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Multiset;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible
public abstract class BV<E> extends AbstractC0189Ur<E> implements AnonymousClass34<E> {
    /* renamed from: A02 */
    public abstract AnonymousClass34<E> A01();

    @Override // X.AnonymousClass34
    public final boolean equals(@NullableDecl Object obj) {
        if (obj == this || A01().equals(obj)) {
            return true;
        }
        return false;
    }

    @Override // X.AnonymousClass34
    @CanIgnoreReturnValue
    public final int A11(E e, int i) {
        return A01().A11(e, i);
    }

    @Override // X.AnonymousClass34
    public final int A1c(Object obj) {
        return A01().A1c(obj);
    }

    @Override // X.AnonymousClass34
    public Set<E> A1t() {
        return A01().A1t();
    }

    @Override // X.AnonymousClass34
    @CanIgnoreReturnValue
    public final int A4f(Object obj, int i) {
        return A01().A4f(obj, i);
    }

    @Override // X.AnonymousClass34
    @CanIgnoreReturnValue
    public final int A4x(E e, int i) {
        return A01().A4x(e, i);
    }

    @Override // X.AnonymousClass34
    @CanIgnoreReturnValue
    public final boolean A4y(E e, int i, int i2) {
        return A01().A4y(e, i, i2);
    }

    @Override // X.AnonymousClass34
    public Set<Multiset.Entry<E>> entrySet() {
        return A01().entrySet();
    }

    @Override // X.AnonymousClass34
    public final int hashCode() {
        return A01().hashCode();
    }
}
