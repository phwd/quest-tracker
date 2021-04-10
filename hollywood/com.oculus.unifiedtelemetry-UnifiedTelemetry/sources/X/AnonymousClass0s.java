package X;

import com.google.common.collect.Multiset;
import java.util.Iterator;

/* renamed from: X.0s  reason: invalid class name */
public class AnonymousClass0s extends AbstractC00156u<E> {
    public final /* synthetic */ AbstractC00166v A00;

    public AnonymousClass0s(AbstractC00166v r1) {
        this.A00 = r1;
    }

    @Override // X.AbstractC00156u
    public final Iterator<Multiset.Entry<E>> A04() {
        return this.A00.A08();
    }

    @Override // java.util.Collection, X.AbstractC0189Ur, X.AbstractC00156u, java.lang.Iterable
    public final Iterator<E> iterator() {
        BA A1o = this.A00.A1o();
        return new AnonymousClass3L(A1o, A1o.entrySet().iterator());
    }

    @Override // X.AbstractC00156u
    public final BA<E> A03() {
        return this.A00;
    }
}
