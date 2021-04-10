package X;

import com.google.common.collect.AbstractMapBasedMultimap;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* renamed from: X.0eR  reason: invalid class name */
public class AnonymousClass0eR extends AbstractC07060qw<K, Collection<V>> {
    public final transient Map<K, Collection<V>> A00;
    public final /* synthetic */ AbstractMapBasedMultimap A01;

    public AnonymousClass0eR(AbstractMapBasedMultimap abstractMapBasedMultimap, Map<K, Collection<V>> map) {
        this.A01 = abstractMapBasedMultimap;
        this.A00 = map;
    }

    @Override // X.AbstractC07060qw
    public final Set<Map.Entry<K, Collection<V>>> A00() {
        return new AnonymousClass0CV(this);
    }

    public final void clear() {
        Map<K, Collection<V>> map = this.A00;
        AbstractMapBasedMultimap abstractMapBasedMultimap = this.A01;
        Map<K, Collection<V>> map2 = abstractMapBasedMultimap.A01;
        if (map == map2) {
            for (Collection<V> collection : map2.values()) {
                collection.clear();
            }
            abstractMapBasedMultimap.A01.clear();
            abstractMapBasedMultimap.A00 = 0;
            return;
        }
        AnonymousClass0qL.A00(new C06620os(this));
    }

    public final boolean containsKey(Object obj) {
        Map<K, Collection<V>> map = this.A00;
        if (map != null) {
            try {
                return map.containsKey(obj);
            } catch (ClassCastException | NullPointerException unused) {
                return false;
            }
        } else {
            throw null;
        }
    }

    public final boolean equals(@NullableDecl Object obj) {
        if (this == obj || this.A00.equals(obj)) {
            return true;
        }
        return false;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object get(Object obj) {
        Collection<V> collection;
        Map<K, Collection<V>> map = this.A00;
        if (map != null) {
            try {
                collection = map.get(obj);
            } catch (ClassCastException | NullPointerException unused) {
                collection = null;
            }
            Collection<V> collection2 = collection;
            if (collection2 == null) {
                return null;
            }
            return this.A01.A03(obj, collection2);
        }
        throw null;
    }

    public final int hashCode() {
        return this.A00.hashCode();
    }

    @Override // java.util.AbstractMap, java.util.Map, X.AbstractC07060qw
    public final Set<K> keySet() {
        return this.A01.keySet();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object remove(Object obj) {
        Collection<V> remove = this.A00.remove(obj);
        if (remove == null) {
            return null;
        }
        AbstractMapBasedMultimap abstractMapBasedMultimap = this.A01;
        Collection A02 = abstractMapBasedMultimap.A02();
        A02.addAll(remove);
        abstractMapBasedMultimap.A00 -= remove.size();
        remove.clear();
        return A02;
    }

    public final int size() {
        return this.A00.size();
    }

    public final String toString() {
        return this.A00.toString();
    }
}
