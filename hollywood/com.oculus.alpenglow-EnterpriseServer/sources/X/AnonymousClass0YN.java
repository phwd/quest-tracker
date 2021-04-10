package X;

import com.google.common.base.Preconditions;
import com.google.common.collect.AbstractMapBasedMultiset;

/* renamed from: X.0YN  reason: invalid class name */
public class AnonymousClass0YN extends AbstractMapBasedMultiset<E>.Itr {
    public final /* synthetic */ AbstractMapBasedMultiset A00;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass0YN(AbstractMapBasedMultiset abstractMapBasedMultiset) {
        super(abstractMapBasedMultiset);
        this.A00 = abstractMapBasedMultiset;
    }

    public final E A00(int i) {
        AnonymousClass0tI<E> r1 = this.A00.A01;
        Preconditions.checkElementIndex(i, r1.A02);
        return (E) r1.A07[i];
    }
}
