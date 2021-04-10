package X;

import java.util.AbstractSet;
import java.util.Collection;

public abstract class UV<E> extends AbstractSet<E> {
    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean retainAll(Collection collection) {
        if (collection != null) {
            return super.retainAll(collection);
        }
        throw null;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.AbstractSet, java.util.Set
    public boolean removeAll(Collection collection) {
        return UX.A02(this, collection);
    }
}
