package X;

import com.google.common.base.Preconditions;
import com.google.common.collect.AbstractMapBasedMultiset;

public class Uw extends AbstractMapBasedMultiset<E>.Itr {
    public final /* synthetic */ AbstractMapBasedMultiset A00;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public Uw(AbstractMapBasedMultiset abstractMapBasedMultiset) {
        super(abstractMapBasedMultiset);
        this.A00 = abstractMapBasedMultiset;
    }

    public final Object A00(int i) {
        AnonymousClass3s<E> r1 = this.A00.A01;
        Preconditions.checkElementIndex(i, r1.A02);
        return new BJ(r1, i);
    }
}
