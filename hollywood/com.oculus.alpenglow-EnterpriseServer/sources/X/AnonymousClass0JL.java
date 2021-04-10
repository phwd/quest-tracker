package X;

import com.google.common.collect.Multiset;
import java.util.Iterator;

/* renamed from: X.0JL  reason: invalid class name */
public class AnonymousClass0JL extends AbstractC02330Xy<E> {
    public final /* synthetic */ AnonymousClass0C0 A00;

    public AnonymousClass0JL(AnonymousClass0C0 r1) {
        this.A00 = r1;
    }

    @Override // X.AbstractC02330Xy
    public final AnonymousClass0tC<E> A00() {
        return this.A00;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
    public final Iterator<Multiset.Entry<E>> iterator() {
        return this.A00.A04();
    }

    public final int size() {
        return this.A00.A03().entrySet().size();
    }
}
