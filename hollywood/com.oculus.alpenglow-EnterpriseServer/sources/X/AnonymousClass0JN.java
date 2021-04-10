package X;

import com.google.common.collect.Multiset;
import java.util.Iterator;

/* renamed from: X.0JN  reason: invalid class name */
public class AnonymousClass0JN extends AbstractC02330Xy<E> {
    public final /* synthetic */ AnonymousClass0YK A00;

    public AnonymousClass0JN(AnonymousClass0YK r1) {
        this.A00 = r1;
    }

    @Override // X.AbstractC02330Xy
    public final AnonymousClass0tC<E> A00() {
        return this.A00;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
    public final Iterator<Multiset.Entry<E>> iterator() {
        return this.A00.A06();
    }

    public final int size() {
        return this.A00.A04();
    }
}
