package X;

import com.google.common.base.Preconditions;
import com.google.common.collect.BoundType;
import com.google.common.collect.GeneralRange;
import com.google.common.collect.TreeMultiset;
import java.util.Iterator;
import java.util.NoSuchElementException;

public final class Ua implements Iterator {
    public AbstractC1179ua A00;
    public C0368Ue A01;
    public final /* synthetic */ TreeMultiset A02;

    public Ua(TreeMultiset treeMultiset) {
        C0368Ue ue;
        this.A02 = treeMultiset;
        Object obj = treeMultiset.A02.A00;
        C0368Ue ue2 = null;
        if (obj != null) {
            GeneralRange generalRange = treeMultiset.A00;
            if (generalRange.hasLowerBound) {
                Object obj2 = generalRange.lowerEndpoint;
                ue = C0368Ue.A05((C0368Ue) obj, treeMultiset.comparator(), obj2);
                if (ue != null) {
                    if (generalRange.lowerBoundType == BoundType.OPEN && treeMultiset.comparator().compare(obj2, ue.A08) == 0) {
                        ue = ue.A07;
                    }
                }
            } else {
                ue = treeMultiset.A01.A07;
            }
            ue2 = (ue == treeMultiset.A01 || !generalRange.A03(ue.A08)) ? null : ue;
        }
        this.A01 = ue2;
    }

    public final boolean hasNext() {
        C0368Ue ue = this.A01;
        if (ue != null) {
            if (!this.A02.A00.A01(ue.A08)) {
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
        this.A02.A4z(this.A00.A01(), 0);
        this.A00 = null;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (hasNext()) {
            TreeMultiset treeMultiset = this.A02;
            C0368Ue ue = this.A01;
            Bg bg = new Bg(treeMultiset, ue);
            this.A00 = bg;
            C0368Ue ue2 = ue.A07;
            if (ue2 == treeMultiset.A01) {
                ue2 = null;
            }
            this.A01 = ue2;
            return bg;
        }
        throw new NoSuchElementException();
    }
}
