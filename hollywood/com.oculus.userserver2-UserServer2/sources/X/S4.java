package X;

import com.google.common.annotations.GwtCompatible;
import java.util.Iterator;

@GwtCompatible
public abstract class S4<F, T> implements Iterator<T> {
    public final Iterator<? extends F> A00;

    public final boolean hasNext() {
        return this.A00.hasNext();
    }

    @Override // java.util.Iterator
    public final T next() {
        return (T) ((Mh) this.A00.next()).A01();
    }

    public final void remove() {
        this.A00.remove();
    }

    public S4(Iterator<? extends F> it) {
        if (it != null) {
            this.A00 = it;
            return;
        }
        throw null;
    }
}
