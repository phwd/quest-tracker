package X;

import com.google.common.collect.AbstractMapBasedMultiset;
import com.google.common.collect.TreeMultiset;
import java.util.Iterator;

public final class Dj extends AbstractC1181uc<E> {
    public final /* synthetic */ AbstractC1157tx A00;

    public Dj(AbstractC1157tx txVar) {
        this.A00 = txVar;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
    public final Iterator iterator() {
        return this.A00.A03();
    }

    public final int size() {
        AbstractC1157tx txVar = this.A00;
        if (!(txVar instanceof TreeMultiset)) {
            return ((AbstractMapBasedMultiset) txVar).A01.A02;
        }
        return C0374Uk.A00(TreeMultiset.A02((TreeMultiset) txVar, TreeMultiset.Aggregate.DISTINCT));
    }
}
