package X;

import com.google.common.base.Preconditions;
import com.google.common.collect.AbstractMapBasedMultiset;

/* renamed from: X.0eO  reason: invalid class name */
public class AnonymousClass0eO extends AbstractMapBasedMultiset<E>.Itr {
    public final /* synthetic */ AbstractMapBasedMultiset A00;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass0eO(AbstractMapBasedMultiset abstractMapBasedMultiset) {
        super(abstractMapBasedMultiset);
        this.A00 = abstractMapBasedMultiset;
    }

    public final Object A00(int i) {
        AnonymousClass0rF<E> r1 = this.A00.A01;
        Preconditions.checkElementIndex(i, r1.A01);
        return new AnonymousClass0Bw(r1, i);
    }
}
