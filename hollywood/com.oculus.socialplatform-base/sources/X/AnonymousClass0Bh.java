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
/* renamed from: X.0Bh  reason: invalid class name */
public abstract class AnonymousClass0Bh<E> extends AnonymousClass0fp<E> implements AnonymousClass0MC<E> {
    @MonotonicNonNullDecl
    public transient AnonymousClass0MC<E> A00;
    @GwtTransient
    public final Comparator<? super E> comparator;

    public abstract Iterator<Multiset.Entry<E>> A07();

    @Override // X.AnonymousClass0fp
    public final Set A03() {
        return new AnonymousClass0BS(this);
    }

    @Override // X.AnonymousClass0MC
    public final AnonymousClass0MC<E> A2a() {
        AnonymousClass0MC<E> r0 = this.A00;
        if (r0 != null) {
            return r0;
        }
        AnonymousClass031 r02 = new AnonymousClass031(this);
        this.A00 = r02;
        return r02;
    }

    @Override // X.AnonymousClass0MC
    public final AnonymousClass0MC<E> AAW(@NullableDecl E e, BoundType boundType, @NullableDecl E e2, BoundType boundType2) {
        if (boundType == null) {
            throw null;
        } else if (boundType2 != null) {
            return AAg(e, boundType).A5T(e2, boundType2);
        } else {
            throw null;
        }
    }

    @Override // X.AnonymousClass0MC
    /* renamed from: A2j */
    public final NavigableSet<E> A2k() {
        return (NavigableSet) super.A2k();
    }

    @Override // X.AnonymousClass0MC
    public final Multiset.Entry<E> A3E() {
        Iterator<Multiset.Entry<E>> A06 = A06();
        if (A06.hasNext()) {
            return A06.next();
        }
        return null;
    }

    @Override // X.AnonymousClass0MC
    public final Multiset.Entry<E> A6F() {
        Iterator<Multiset.Entry<E>> A07 = A07();
        if (A07.hasNext()) {
            return A07.next();
        }
        return null;
    }

    @Override // X.AnonymousClass0MC
    public final Multiset.Entry<E> A8P() {
        Iterator<Multiset.Entry<E>> A06 = A06();
        if (!A06.hasNext()) {
            return null;
        }
        AnonymousClass0f2 next = A06.next();
        Multisets$ImmutableEntry multisets$ImmutableEntry = new Multisets$ImmutableEntry(next.A01(), next.A00());
        A06.remove();
        return multisets$ImmutableEntry;
    }

    @Override // X.AnonymousClass0MC
    public final Multiset.Entry<E> A8Q() {
        Iterator<Multiset.Entry<E>> A07 = A07();
        if (!A07.hasNext()) {
            return null;
        }
        AnonymousClass0f2 next = A07.next();
        Multisets$ImmutableEntry multisets$ImmutableEntry = new Multisets$ImmutableEntry(next.A01(), next.A00());
        A07.remove();
        return multisets$ImmutableEntry;
    }

    @Override // X.AnonymousClass0wF, X.AnonymousClass0MC
    public final Comparator<? super E> comparator() {
        return this.comparator;
    }

    public AnonymousClass0Bh() {
        this(NaturalOrdering.A00);
    }

    public AnonymousClass0Bh(Comparator<? super E> comparator2) {
        if (comparator2 != null) {
            this.comparator = comparator2;
            return;
        }
        throw null;
    }
}
