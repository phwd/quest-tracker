package X;

import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/* renamed from: X.0tZ  reason: invalid class name */
public abstract class AnonymousClass0tZ<E> extends AbstractSet<E> {
    @Override // java.util.AbstractCollection, java.util.Collection, java.util.AbstractSet, java.util.Set
    public final boolean removeAll(Collection<?> collection) {
        boolean z;
        if (collection != null) {
            if (collection instanceof AnonymousClass0tC) {
                collection = ((AnonymousClass0tC) collection).A2G();
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
