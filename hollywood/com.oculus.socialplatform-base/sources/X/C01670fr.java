package X;

import com.google.common.base.Preconditions;
import com.google.common.collect.AbstractMapBasedMultiset;

/* renamed from: X.0fr  reason: invalid class name and case insensitive filesystem */
public class C01670fr extends AbstractMapBasedMultiset<E>.Itr {
    public final /* synthetic */ AbstractMapBasedMultiset A00;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C01670fr(AbstractMapBasedMultiset abstractMapBasedMultiset) {
        super(abstractMapBasedMultiset);
        this.A00 = abstractMapBasedMultiset;
    }

    public final Object A00(int i) {
        AnonymousClass0vu<E> r1 = this.A00.A01;
        Preconditions.checkElementIndex(i, r1.A01);
        return new AnonymousClass0Mp(r1, i);
    }
}
