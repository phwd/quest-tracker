package X;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.BoundType;
import com.google.common.collect.GwtTransient;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets$ImmutableEntry;
import com.google.common.collect.NaturalOrdering;
import com.google.common.collect.TreeMultiset;
import com.oculus.common.build.BuildConfig;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NavigableSet;
import org.checkerframework.checker.nullness.compatqual.MonotonicNonNullDecl;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible(emulated = BuildConfig.IS_LIBCXX_BUILD)
/* renamed from: X.6s  reason: invalid class name */
public abstract class AnonymousClass6s<E> extends NO<E> implements AnonymousClass9K<E> {
    @MonotonicNonNullDecl
    public transient AnonymousClass9K<E> A00;
    @GwtTransient
    public final Comparator<? super E> comparator;

    @Override // X.AnonymousClass9K
    public final Multiset.Entry<E> A2G() {
        SX sx = new SX((TreeMultiset) this);
        if (sx.hasNext()) {
            return (Mh) sx.next();
        }
        return null;
    }

    @Override // X.AnonymousClass9K
    public final Multiset.Entry<E> A2s() {
        SX sx = new SX((TreeMultiset) this);
        if (!sx.hasNext()) {
            return null;
        }
        Mh mh = (Mh) sx.next();
        Multisets$ImmutableEntry multisets$ImmutableEntry = new Multisets$ImmutableEntry(mh.A01(), mh.A00());
        sx.remove();
        return multisets$ImmutableEntry;
    }

    @Override // X.AnonymousClass9K
    public final AnonymousClass9K<E> A1M() {
        AnonymousClass9K<E> r0 = this.A00;
        if (r0 != null) {
            return r0;
        }
        C00050s r02 = new C00050s(this);
        this.A00 = r02;
        return r02;
    }

    @Override // X.AnonymousClass9K
    public final AnonymousClass9K<E> A3n(@NullableDecl E e, BoundType boundType, @NullableDecl E e2, BoundType boundType2) {
        if (boundType == null) {
            throw null;
        } else if (boundType2 != null) {
            return A3p(e, boundType).A26(e2, boundType2);
        } else {
            throw null;
        }
    }

    @Override // X.AnonymousClass9K
    /* renamed from: A1Q */
    public final NavigableSet<E> A1R() {
        return (NavigableSet) super.A1R();
    }

    @Override // X.AnonymousClass9K
    public final Multiset.Entry<E> A1Y() {
        Iterator<Multiset.Entry<E>> A03 = A03();
        if (A03.hasNext()) {
            return A03.next();
        }
        return null;
    }

    @Override // X.AnonymousClass9K
    public final Multiset.Entry<E> A2r() {
        Iterator<Multiset.Entry<E>> A03 = A03();
        if (!A03.hasNext()) {
            return null;
        }
        Mh next = A03.next();
        Multisets$ImmutableEntry multisets$ImmutableEntry = new Multisets$ImmutableEntry(next.A01(), next.A00());
        A03.remove();
        return multisets$ImmutableEntry;
    }

    @Override // X.AnonymousClass9K, X.S1
    public final Comparator<? super E> comparator() {
        return this.comparator;
    }

    public AnonymousClass6s() {
        this(NaturalOrdering.A00);
    }

    public AnonymousClass6s(Comparator<? super E> comparator2) {
        if (comparator2 != null) {
            this.comparator = comparator2;
            return;
        }
        throw null;
    }
}
