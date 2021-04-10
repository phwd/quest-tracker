package X;

import java.util.AbstractSet;
import java.util.Collection;

/* renamed from: X.0rW  reason: invalid class name */
public abstract class AnonymousClass0rW<E> extends AbstractSet<E> {
    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean retainAll(Collection<?> collection) {
        if (collection != null) {
            return super.retainAll(collection);
        }
        throw null;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.AbstractSet, java.util.Set
    public boolean removeAll(Collection<?> collection) {
        return C07190ra.A03(this, collection);
    }
}
