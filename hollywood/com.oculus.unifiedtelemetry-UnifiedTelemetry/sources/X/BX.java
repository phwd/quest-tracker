package X;

import com.google.common.collect.Multiset;
import java.util.Iterator;

public class BX extends AbstractC0179Ue<E> {
    public final /* synthetic */ Uv A00;

    public BX(Uv uv) {
        this.A00 = uv;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
    public final Iterator<Multiset.Entry<E>> iterator() {
        return this.A00.A07();
    }

    public final int size() {
        return this.A00.A05();
    }

    @Override // X.AbstractC0179Ue
    public final AnonymousClass34<E> A00() {
        return this.A00;
    }
}
