package X;

import java.util.AbstractSet;
import java.util.Collection;

/* renamed from: X.0wA  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC05580wA<E> extends AbstractSet<E> {
    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean retainAll(Collection<?> collection) {
        if (collection != null) {
            return super.retainAll(collection);
        }
        throw null;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.AbstractSet, java.util.Set
    public boolean removeAll(Collection<?> collection) {
        return AnonymousClass0wE.A02(this, collection);
    }
}
