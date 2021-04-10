package X;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Multiset;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible
/* renamed from: X.9h  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC00189h<E> extends NA<E> implements AbstractC0120Qz<E> {
    @Override // X.AbstractC0120Qz
    @CanIgnoreReturnValue
    public final int A0k(E e, int i) {
        return ((C00050s) ((AnonymousClass6q) this)).A00.A0k(e, i);
    }

    @Override // X.AbstractC0120Qz
    public final int A1D(Object obj) {
        return ((C00050s) ((AnonymousClass6q) this)).A00.A1D(obj);
    }

    @Override // X.AbstractC0120Qz
    @CanIgnoreReturnValue
    public final int A3F(Object obj, int i) {
        return ((C00050s) ((AnonymousClass6q) this)).A00.A3F(obj, i);
    }

    @Override // X.AbstractC0120Qz
    @CanIgnoreReturnValue
    public final int A3X(E e, int i) {
        return ((C00050s) ((AnonymousClass6q) this)).A00.A3X(e, i);
    }

    @Override // X.AbstractC0120Qz
    @CanIgnoreReturnValue
    public final boolean A3Y(E e, int i, int i2) {
        return ((C00050s) ((AnonymousClass6q) this)).A00.A3Y(e, i, i2);
    }

    @Override // X.AbstractC0120Qz
    public final int hashCode() {
        return ((C00050s) ((AnonymousClass6q) this)).A00.hashCode();
    }

    @Override // X.AbstractC0120Qz
    public final Set<E> A1R() {
        if (!(this instanceof AnonymousClass6q)) {
            return ((C00050s) ((AnonymousClass6q) this)).A00.A1R();
        }
        return ((AnonymousClass6q) this).A1Q();
    }

    @Override // X.AbstractC0120Qz
    public final Set<Multiset.Entry<E>> entrySet() {
        if (!(this instanceof AnonymousClass6q)) {
            return ((C00050s) ((AnonymousClass6q) this)).A00.entrySet();
        }
        AnonymousClass6q r1 = (AnonymousClass6q) this;
        Set<Multiset.Entry<E>> set = r1.A00;
        if (set != null) {
            return set;
        }
        C00199i r0 = new C00199i(r1);
        r1.A00 = r0;
        return r0;
    }

    @Override // X.AbstractC0120Qz
    public final boolean equals(@NullableDecl Object obj) {
        if (obj == this || ((C00050s) ((AnonymousClass6q) this)).A00.equals(obj)) {
            return true;
        }
        return false;
    }
}
