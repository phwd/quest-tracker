package X;

import com.google.common.collect.AbstractMapBasedMultimap;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* renamed from: X.0CV  reason: invalid class name */
public class AnonymousClass0CV extends AnonymousClass0dQ<K, Collection<V>> {
    public final /* synthetic */ AnonymousClass0eR A00;

    public AnonymousClass0CV(AnonymousClass0eR r1) {
        this.A00 = r1;
    }

    @Override // X.AnonymousClass0dQ
    public final boolean contains(Object obj) {
        Set<Map.Entry<K, Collection<V>>> entrySet = this.A00.A00.entrySet();
        if (entrySet != null) {
            try {
                return entrySet.contains(obj);
            } catch (ClassCastException | NullPointerException unused) {
                return false;
            }
        } else {
            throw null;
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
    public final Iterator<Map.Entry<K, Collection<V>>> iterator() {
        return new C06620os(this.A00);
    }

    @Override // X.AnonymousClass0dQ
    public final Map<K, Collection<V>> A00() {
        return this.A00;
    }

    @Override // X.AnonymousClass0dQ
    public final boolean remove(Object obj) {
        Collection<V> collection;
        if (!contains(obj)) {
            return false;
        }
        AbstractMapBasedMultimap abstractMapBasedMultimap = this.A00.A01;
        Object key = ((Map.Entry) obj).getKey();
        Map<K, Collection<V>> map = abstractMapBasedMultimap.A01;
        if (map != null) {
            try {
                collection = map.remove(key);
            } catch (ClassCastException | NullPointerException unused) {
                collection = null;
            }
            Collection<V> collection2 = collection;
            if (collection2 == null) {
                return true;
            }
            int size = collection2.size();
            collection2.clear();
            abstractMapBasedMultimap.A00 -= size;
            return true;
        }
        throw null;
    }
}
