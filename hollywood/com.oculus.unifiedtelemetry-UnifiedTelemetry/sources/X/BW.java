package X;

import com.google.common.collect.Multiset;
import java.util.Iterator;

public class BW extends AbstractC0179Ue<E> {
    public final /* synthetic */ AbstractC00156u A00;

    public BW(AbstractC00156u r1) {
        this.A00 = r1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
    public final Iterator<Multiset.Entry<E>> iterator() {
        return this.A00.A04();
    }

    public final int size() {
        return this.A00.A03().entrySet().size();
    }

    @Override // X.AbstractC0179Ue
    public final AnonymousClass34<E> A00() {
        return this.A00;
    }
}
