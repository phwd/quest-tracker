package X;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Multiset;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible
/* renamed from: X.0JK  reason: invalid class name */
public abstract class AnonymousClass0JK<E> extends AnonymousClass0YC<E> implements AnonymousClass0tC<E> {
    /* renamed from: A02 */
    public abstract AnonymousClass0tC<E> A01();

    @Override // X.AnonymousClass0tC
    public final boolean equals(@NullableDecl Object obj) {
        if (obj == this || A01().equals(obj)) {
            return true;
        }
        return false;
    }

    @Override // X.AnonymousClass0tC
    @CanIgnoreReturnValue
    public final int A0x(E e, int i) {
        return A01().A0x(e, i);
    }

    @Override // X.AnonymousClass0tC
    public final int A1s(Object obj) {
        return A01().A1s(obj);
    }

    @Override // X.AnonymousClass0tC
    public Set<E> A2G() {
        return A01().A2G();
    }

    @Override // X.AnonymousClass0tC
    @CanIgnoreReturnValue
    public final int A7L(Object obj, int i) {
        return A01().A7L(obj, i);
    }

    @Override // X.AnonymousClass0tC
    @CanIgnoreReturnValue
    public final int A7q(E e, int i) {
        return A01().A7q(e, i);
    }

    @Override // X.AnonymousClass0tC
    @CanIgnoreReturnValue
    public final boolean A7r(E e, int i, int i2) {
        return A01().A7r(e, i, i2);
    }

    @Override // X.AnonymousClass0tC
    public Set<Multiset.Entry<E>> entrySet() {
        return A01().entrySet();
    }

    @Override // X.AnonymousClass0tC
    public final int hashCode() {
        return A01().hashCode();
    }
}
