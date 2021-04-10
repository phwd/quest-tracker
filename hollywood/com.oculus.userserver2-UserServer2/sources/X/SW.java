package X;

import com.google.common.base.Preconditions;
import com.google.common.collect.BoundType;
import com.google.common.collect.GeneralRange;
import com.google.common.collect.Multiset;
import com.google.common.collect.TreeMultiset;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

public class SW implements Iterator<Multiset.Entry<E>> {
    @NullableDecl
    public Multiset.Entry<E> A00;
    public TP<E> A01;
    public final /* synthetic */ TreeMultiset A02;

    public SW(TreeMultiset treeMultiset) {
        TP<E> tp;
        this.A02 = treeMultiset;
        T t = treeMultiset.A02.A00;
        TP<E> tp2 = null;
        if (t != null) {
            GeneralRange<E> generalRange = treeMultiset.A00;
            if (generalRange.hasLowerBound) {
                T t2 = generalRange.lowerEndpoint;
                tp = TP.A05(t, treeMultiset.comparator(), t2);
                if (tp != null) {
                    if (generalRange.lowerBoundType == BoundType.OPEN && treeMultiset.comparator().compare(t2, tp.A08) == 0) {
                        tp = tp.A07;
                    }
                }
            } else {
                tp = treeMultiset.A01.A07;
            }
            tp2 = (tp == treeMultiset.A01 || !generalRange.A03(tp.A08)) ? null : tp;
        }
        this.A01 = tp2;
    }

    public final boolean hasNext() {
        TP<E> tp = this.A01;
        if (tp != null) {
            if (!this.A02.A00.A01(tp.A08)) {
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
        this.A02.A3X(this.A00.A01(), 0);
        this.A00 = null;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (hasNext()) {
            TreeMultiset treeMultiset = this.A02;
            TP<E> tp = this.A01;
            AnonymousClass9I r2 = new AnonymousClass9I(treeMultiset, tp);
            this.A00 = r2;
            TP<E> tp2 = tp.A07;
            if (tp2 == treeMultiset.A01) {
                tp2 = null;
            }
            this.A01 = tp2;
            return r2;
        }
        throw new NoSuchElementException();
    }
}
