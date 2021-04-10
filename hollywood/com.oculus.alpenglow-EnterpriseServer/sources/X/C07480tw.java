package X;

import com.google.common.base.Preconditions;
import com.google.common.collect.BoundType;
import com.google.common.collect.GeneralRange;
import com.google.common.collect.Multiset;
import com.google.common.collect.TreeMultiset;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* renamed from: X.0tw  reason: invalid class name and case insensitive filesystem */
public class C07480tw implements Iterator<Multiset.Entry<E>> {
    @NullableDecl
    public Multiset.Entry<E> A00;
    public AnonymousClass0u0<E> A01;
    public final /* synthetic */ TreeMultiset A02;

    public C07480tw(TreeMultiset treeMultiset) {
        AnonymousClass0u0<E> r2;
        this.A02 = treeMultiset;
        T t = treeMultiset.A02.A00;
        AnonymousClass0u0<E> r4 = null;
        if (t != null) {
            GeneralRange<E> generalRange = treeMultiset.A00;
            if (generalRange.hasLowerBound) {
                T t2 = generalRange.lowerEndpoint;
                r2 = AnonymousClass0u0.A05(t, treeMultiset.comparator(), t2);
                if (r2 != null) {
                    if (generalRange.lowerBoundType == BoundType.OPEN && treeMultiset.comparator().compare(t2, r2.A08) == 0) {
                        r2 = r2.A07;
                    }
                }
            } else {
                r2 = treeMultiset.A01.A07;
            }
            r4 = (r2 == treeMultiset.A01 || !generalRange.A03(r2.A08)) ? null : r2;
        }
        this.A01 = r4;
    }

    public final boolean hasNext() {
        AnonymousClass0u0<E> r3 = this.A01;
        if (r3 != null) {
            if (!this.A02.A00.A01(r3.A08)) {
                return true;
            }
            this.A01 = null;
        }
        return false;
    }

    public final void remove() {
        boolean z = false;
        if (this.A00 != null) {
            z = true;
        }
        Preconditions.checkState(z, "no calls to next() since the last call to remove()");
        this.A02.A7q(this.A00.A01(), 0);
        this.A00 = null;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (hasNext()) {
            TreeMultiset treeMultiset = this.A02;
            AnonymousClass0u0<E> r0 = this.A01;
            C01540Ic r2 = new C01540Ic(treeMultiset, r0);
            this.A00 = r2;
            AnonymousClass0u0<E> r1 = r0.A07;
            if (r1 == treeMultiset.A01) {
                r1 = null;
            }
            this.A01 = r1;
            return r2;
        }
        throw new NoSuchElementException();
    }
}
