package X;

import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/* renamed from: X.6w  reason: invalid class name */
public abstract class AnonymousClass6w<E> extends AbstractSet<E> {
    @Override // java.util.AbstractCollection, java.util.Collection, java.util.AbstractSet, java.util.Set
    public final boolean removeAll(Collection<?> collection) {
        boolean z;
        if (collection != null) {
            if (collection instanceof AnonymousClass34) {
                collection = ((AnonymousClass34) collection).A1t();
            }
            if (!(collection instanceof Set) || collection.size() <= size()) {
                Iterator<?> it = collection.iterator();
                z = false;
                while (it.hasNext()) {
                    z |= remove(it.next());
                }
            } else {
                Iterator<E> it2 = iterator();
                z = false;
                while (it2.hasNext()) {
                    if (collection.contains(it2.next())) {
                        it2.remove();
                        z = true;
                    }
                }
            }
            return z;
        }
        throw null;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean retainAll(Collection<?> collection) {
        if (collection != null) {
            return super.retainAll(collection);
        }
        throw null;
    }
}
