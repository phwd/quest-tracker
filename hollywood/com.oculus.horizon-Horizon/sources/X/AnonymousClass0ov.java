package X;

import com.google.common.collect.AbstractMapBasedMultimap;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;

/* renamed from: X.0ov  reason: invalid class name */
public class AnonymousClass0ov implements Iterator<V> {
    public final Iterator<V> A00;
    public final Collection<V> A01;
    public final /* synthetic */ AnonymousClass0ow A02;

    public AnonymousClass0ov(AnonymousClass0ow r3) {
        Iterator<V> it;
        this.A02 = r3;
        Collection<V> collection = r3.A00;
        this.A01 = collection;
        if (collection instanceof List) {
            it = ((List) collection).listIterator();
        } else {
            it = collection.iterator();
        }
        this.A00 = it;
    }

    public final boolean hasNext() {
        AnonymousClass0ow r0 = this.A02;
        r0.A01();
        if (r0.A00 == this.A01) {
            return this.A00.hasNext();
        }
        throw new ConcurrentModificationException();
    }

    @Override // java.util.Iterator
    public final V next() {
        AnonymousClass0ow r0 = this.A02;
        r0.A01();
        if (r0.A00 == this.A01) {
            return this.A00.next();
        }
        throw new ConcurrentModificationException();
    }

    public final void remove() {
        this.A00.remove();
        AnonymousClass0ow r2 = this.A02;
        AbstractMapBasedMultimap abstractMapBasedMultimap = r2.A02;
        abstractMapBasedMultimap.A00--;
        r2.A00();
    }
}
