package X;

import com.google.common.collect.AbstractMapBasedMultiset;
import com.google.common.collect.Multiset;
import com.google.common.collect.TreeMultiset;
import java.util.Iterator;

/* renamed from: X.9k  reason: invalid class name and case insensitive filesystem */
public class C00219k extends AbstractC0104Mf<E> {
    public final /* synthetic */ NO A00;

    public C00219k(NO no) {
        this.A00 = no;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
    public final Iterator<Multiset.Entry<E>> iterator() {
        return this.A00.A03();
    }

    public final int size() {
        NO no = this.A00;
        if (!(no instanceof TreeMultiset)) {
            return ((AbstractMapBasedMultiset) no).A01.A02;
        }
        return C0145Tq.A00(TreeMultiset.A02((TreeMultiset) no, TreeMultiset.Aggregate.DISTINCT));
    }
}
