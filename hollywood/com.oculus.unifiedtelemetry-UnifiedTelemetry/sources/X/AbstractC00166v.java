package X;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.BoundType;
import com.google.common.collect.GwtTransient;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets$ImmutableEntry;
import com.google.common.collect.NaturalOrdering;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.MonotonicNonNullDecl;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible(emulated = true)
/* renamed from: X.6v  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC00166v<E> extends Uv<E> implements BA<E> {
    @MonotonicNonNullDecl
    public transient BA<E> A00;
    @GwtTransient
    public final Comparator<? super E> comparator;

    public abstract Iterator<Multiset.Entry<E>> A08();

    @Override // X.Uv
    public final Set A04() {
        return new AnonymousClass6l(this);
    }

    @Override // X.BA
    public final BA<E> A1o() {
        BA<E> ba = this.A00;
        if (ba != null) {
            return ba;
        }
        AnonymousClass0s r0 = new AnonymousClass0s(this);
        this.A00 = r0;
        return r0;
    }

    @Override // X.BA
    public final BA<E> A5Q(@NullableDecl E e, BoundType boundType, @NullableDecl E e2, BoundType boundType2) {
        if (boundType == null) {
            throw null;
        } else if (boundType2 != null) {
            return A5T(e, boundType).A30(e2, boundType2);
        } else {
            throw null;
        }
    }

    @Override // X.BA
    /* renamed from: A1s */
    public final NavigableSet<E> A1t() {
        return (NavigableSet) super.A1t();
    }

    @Override // X.BA
    public final Multiset.Entry<E> A2C() {
        Iterator<Multiset.Entry<E>> A07 = A07();
        if (A07.hasNext()) {
            return A07.next();
        }
        return null;
    }

    @Override // X.BA
    public final Multiset.Entry<E> A3M() {
        Iterator<Multiset.Entry<E>> A08 = A08();
        if (A08.hasNext()) {
            return A08.next();
        }
        return null;
    }

    @Override // X.BA
    public final Multiset.Entry<E> A4E() {
        Iterator<Multiset.Entry<E>> A07 = A07();
        if (!A07.hasNext()) {
            return null;
        }
        AbstractC0181Ug next = A07.next();
        Multisets$ImmutableEntry multisets$ImmutableEntry = new Multisets$ImmutableEntry(next.A01(), next.A00());
        A07.remove();
        return multisets$ImmutableEntry;
    }

    @Override // X.BA
    public final Multiset.Entry<E> A4F() {
        Iterator<Multiset.Entry<E>> A08 = A08();
        if (!A08.hasNext()) {
            return null;
        }
        AbstractC0181Ug next = A08.next();
        Multisets$ImmutableEntry multisets$ImmutableEntry = new Multisets$ImmutableEntry(next.A01(), next.A00());
        A08.remove();
        return multisets$ImmutableEntry;
    }

    @Override // X.AnonymousClass6z, X.BA
    public final Comparator<? super E> comparator() {
        return this.comparator;
    }

    public AbstractC00166v() {
        this(NaturalOrdering.A00);
    }

    public AbstractC00166v(Comparator<? super E> comparator2) {
        if (comparator2 != null) {
            this.comparator = comparator2;
            return;
        }
        throw null;
    }
}
