package X;

import com.google.common.collect.AbstractMapBasedMultiset;
import com.google.common.collect.TreeMultiset;
import java.util.Iterator;

/* renamed from: X.Dk  reason: case insensitive filesystem */
public final class C0145Dk extends AbstractC1180ub<E> {
    public final /* synthetic */ AbstractC1157tx A00;

    public C0145Dk(AbstractC1157tx txVar) {
        this.A00 = txVar;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
    public final Iterator iterator() {
        AbstractC1157tx txVar = this.A00;
        if (!(txVar instanceof TreeMultiset)) {
            return new C1153tt((AbstractMapBasedMultiset) txVar);
        }
        return new C1178uZ(txVar.A03());
    }
}
