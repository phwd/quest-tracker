package X;

import com.google.common.collect.Multiset;
import java.util.Iterator;

/* renamed from: X.00v  reason: invalid class name */
public class AnonymousClass00v extends AnonymousClass06q<E> {
    public final /* synthetic */ AbstractC001306r A00;

    public AnonymousClass00v(AbstractC001306r r1) {
        this.A00 = r1;
    }

    @Override // X.AnonymousClass06q
    public final Iterator<Multiset.Entry<E>> A04() {
        return this.A00.A07();
    }

    @Override // X.AnonymousClass0eD, java.util.Collection, X.AnonymousClass06q, java.lang.Iterable
    public final Iterator<E> iterator() {
        AnonymousClass0Bk A2E = this.A00.A2E();
        return new C07130rB(A2E, A2E.entrySet().iterator());
    }

    @Override // X.AnonymousClass06q
    public final AnonymousClass0Bk<E> A03() {
        return this.A00;
    }
}
