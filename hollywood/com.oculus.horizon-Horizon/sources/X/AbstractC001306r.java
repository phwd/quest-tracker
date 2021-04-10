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
/* renamed from: X.06r  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC001306r<E> extends AnonymousClass0eM<E> implements AnonymousClass0Bk<E> {
    @MonotonicNonNullDecl
    public transient AnonymousClass0Bk<E> A00;
    @GwtTransient
    public final Comparator<? super E> comparator;

    public abstract Iterator<Multiset.Entry<E>> A07();

    @Override // X.AnonymousClass0eM
    public final Set A03() {
        return new AnonymousClass06b(this);
    }

    @Override // X.AnonymousClass0Bk
    public final AnonymousClass0Bk<E> A2E() {
        AnonymousClass0Bk<E> r0 = this.A00;
        if (r0 != null) {
            return r0;
        }
        AnonymousClass00v r02 = new AnonymousClass00v(this);
        this.A00 = r02;
        return r02;
    }

    @Override // X.AnonymousClass0Bk
    public final AnonymousClass0Bk<E> A9W(@NullableDecl E e, BoundType boundType, @NullableDecl E e2, BoundType boundType2) {
        if (boundType == null) {
            throw null;
        } else if (boundType2 != null) {
            return A9c(e, boundType).A4j(e2, boundType2);
        } else {
            throw null;
        }
    }

    @Override // X.AnonymousClass0Bk
    /* renamed from: A2M */
    public final NavigableSet<E> A2N() {
        return (NavigableSet) super.A2N();
    }

    @Override // X.AnonymousClass0Bk
    public final Multiset.Entry<E> A2p() {
        Iterator<Multiset.Entry<E>> A06 = A06();
        if (A06.hasNext()) {
            return A06.next();
        }
        return null;
    }

    @Override // X.AnonymousClass0Bk
    public final Multiset.Entry<E> A5C() {
        Iterator<Multiset.Entry<E>> A07 = A07();
        if (A07.hasNext()) {
            return A07.next();
        }
        return null;
    }

    @Override // X.AnonymousClass0Bk
    public final Multiset.Entry<E> A7M() {
        Iterator<Multiset.Entry<E>> A06 = A06();
        if (!A06.hasNext()) {
            return null;
        }
        AnonymousClass0dN next = A06.next();
        Multisets$ImmutableEntry multisets$ImmutableEntry = new Multisets$ImmutableEntry(next.A01(), next.A00());
        A06.remove();
        return multisets$ImmutableEntry;
    }

    @Override // X.AnonymousClass0Bk
    public final Multiset.Entry<E> A7N() {
        Iterator<Multiset.Entry<E>> A07 = A07();
        if (!A07.hasNext()) {
            return null;
        }
        AnonymousClass0dN next = A07.next();
        Multisets$ImmutableEntry multisets$ImmutableEntry = new Multisets$ImmutableEntry(next.A01(), next.A00());
        A07.remove();
        return multisets$ImmutableEntry;
    }

    @Override // X.AbstractC07200rb, X.AnonymousClass0Bk
    public final Comparator<? super E> comparator() {
        return this.comparator;
    }

    public AbstractC001306r() {
        this(NaturalOrdering.A00);
    }

    public AbstractC001306r(Comparator<? super E> comparator2) {
        if (comparator2 != null) {
            this.comparator = comparator2;
            return;
        }
        throw null;
    }
}
