package X;

import com.google.common.base.Function;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;

/* renamed from: X.0pA  reason: invalid class name */
public class AnonymousClass0pA<F, T> extends AbstractCollection<T> {
    public final Function<? super F, ? extends T> A00;
    public final Collection<F> A01;

    public final void clear() {
        this.A01.clear();
    }

    public final boolean isEmpty() {
        return this.A01.isEmpty();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final Iterator<T> iterator() {
        Iterator<F> it = this.A01.iterator();
        Function<? super F, ? extends T> function = this.A00;
        if (function != null) {
            return new C03660dt(it, function);
        }
        throw null;
    }

    public final int size() {
        return this.A01.size();
    }

    public AnonymousClass0pA(Collection<F> collection, Function<? super F, ? extends T> function) {
        if (collection != null) {
            this.A01 = collection;
            if (function != null) {
                this.A00 = function;
                return;
            }
        }
        throw null;
    }
}
