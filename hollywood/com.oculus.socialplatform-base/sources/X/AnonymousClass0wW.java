package X;

import com.google.common.annotations.GwtCompatible;
import java.util.Iterator;

@GwtCompatible
/* renamed from: X.0wW  reason: invalid class name */
public abstract class AnonymousClass0wW<F, T> implements Iterator<T> {
    public final Iterator<? extends F> A00;

    public abstract T A00(F f);

    public final boolean hasNext() {
        return this.A00.hasNext();
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: X.0wW<F, T> */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Iterator
    public final T next() {
        return (T) A00(this.A00.next());
    }

    public final void remove() {
        this.A00.remove();
    }

    public AnonymousClass0wW(Iterator<? extends F> it) {
        if (it != null) {
            this.A00 = it;
            return;
        }
        throw null;
    }
}
