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
/* renamed from: X.0C1  reason: invalid class name */
public abstract class AnonymousClass0C1<E> extends AnonymousClass0YK<E> implements AnonymousClass0Ie<E> {
    @MonotonicNonNullDecl
    public transient AnonymousClass0Ie<E> A00;
    @GwtTransient
    public final Comparator<? super E> comparator;

    public abstract Iterator<Multiset.Entry<E>> A07();

    @Override // X.AnonymousClass0YK
    public final Set A03() {
        return new AnonymousClass0BY(this);
    }

    @Override // X.AnonymousClass0Ie
    public final AnonymousClass0Ie<E> A26() {
        AnonymousClass0Ie<E> r0 = this.A00;
        if (r0 != null) {
            return r0;
        }
        AnonymousClass06L r02 = new AnonymousClass06L(this);
        this.A00 = r02;
        return r02;
    }

    @Override // X.AnonymousClass0Ie
    public final AnonymousClass0Ie<E> A8V(@NullableDecl E e, BoundType boundType, @NullableDecl E e2, BoundType boundType2) {
        if (boundType == null) {
            throw null;
        } else if (boundType2 != null) {
            return A8Y(e, boundType).A52(e2, boundType2);
        } else {
            throw null;
        }
    }

    @Override // X.AbstractC07460te, X.AnonymousClass0Ie
    public final Comparator<? super E> comparator() {
        return this.comparator;
    }

    @Override // X.AnonymousClass0Ie
    /* renamed from: A2F */
    public final NavigableSet<E> A2G() {
        return (NavigableSet) super.A2G();
    }

    @Override // X.AnonymousClass0Ie
    public final Multiset.Entry<E> A2o() {
        Iterator<Multiset.Entry<E>> A06 = A06();
        if (A06.hasNext()) {
            return A06.next();
        }
        return null;
    }

    @Override // X.AnonymousClass0Ie
    public final Multiset.Entry<E> A5d() {
        Iterator<Multiset.Entry<E>> A07 = A07();
        if (A07.hasNext()) {
            return A07.next();
        }
        return null;
    }

    @Override // X.AnonymousClass0Ie
    public final Multiset.Entry<E> A6q() {
        Iterator<Multiset.Entry<E>> A06 = A06();
        if (!A06.hasNext()) {
            return null;
        }
        AnonymousClass0Y0 next = A06.next();
        Multisets$ImmutableEntry multisets$ImmutableEntry = new Multisets$ImmutableEntry(next.A01(), next.A00());
        A06.remove();
        return multisets$ImmutableEntry;
    }

    @Override // X.AnonymousClass0Ie
    public final Multiset.Entry<E> A6r() {
        Iterator<Multiset.Entry<E>> A07 = A07();
        if (!A07.hasNext()) {
            return null;
        }
        AnonymousClass0Y0 next = A07.next();
        Multisets$ImmutableEntry multisets$ImmutableEntry = new Multisets$ImmutableEntry(next.A01(), next.A00());
        A07.remove();
        return multisets$ImmutableEntry;
    }

    public AnonymousClass0C1() {
        this(NaturalOrdering.A00);
    }

    public AnonymousClass0C1(Comparator<? super E> comparator2) {
        if (comparator2 != null) {
            this.comparator = comparator2;
            return;
        }
        throw null;
    }
}
