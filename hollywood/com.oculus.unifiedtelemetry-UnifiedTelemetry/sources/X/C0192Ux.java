package X;

import com.google.common.base.Preconditions;
import com.google.common.collect.AbstractMapBasedMultiset;

/* renamed from: X.Ux  reason: case insensitive filesystem */
public class C0192Ux extends AbstractMapBasedMultiset<E>.Itr {
    public final /* synthetic */ AbstractMapBasedMultiset A00;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0192Ux(AbstractMapBasedMultiset abstractMapBasedMultiset) {
        super(abstractMapBasedMultiset);
        this.A00 = abstractMapBasedMultiset;
    }

    public final E A00(int i) {
        AnonymousClass3s<E> r1 = this.A00.A01;
        Preconditions.checkElementIndex(i, r1.A02);
        return (E) r1.A07[i];
    }
}
