package X;

import com.google.common.collect.AbstractMapBasedMultimap;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* renamed from: X.0CP  reason: invalid class name */
public class AnonymousClass0CP extends AnonymousClass0dP<K, Collection<V>> {
    public final /* synthetic */ AbstractMapBasedMultimap A00;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass0CP(AbstractMapBasedMultimap abstractMapBasedMultimap, Map<K, Collection<V>> map) {
        super(map);
        this.A00 = abstractMapBasedMultimap;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean containsAll(Collection<?> collection) {
        return super.A00.keySet().containsAll(collection);
    }

    public final boolean equals(@NullableDecl Object obj) {
        if (this == obj || super.A00.keySet().equals(obj)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return super.A00.keySet().hashCode();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, X.AnonymousClass0dP, java.lang.Iterable
    public final Iterator<K> iterator() {
        return new AnonymousClass0ou(this, super.A00.entrySet().iterator());
    }

    @Override // X.AnonymousClass0dP
    public final boolean remove(Object obj) {
        V remove = super.A00.remove(obj);
        if (remove == null) {
            return false;
        }
        int size = remove.size();
        remove.clear();
        this.A00.A00 -= size;
        if (size > 0) {
            return true;
        }
        return false;
    }

    @Override // X.AnonymousClass0dP
    public final void clear() {
        AnonymousClass0qL.A00(iterator());
    }
}
