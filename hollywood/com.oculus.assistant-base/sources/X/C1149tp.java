package X;

import com.google.common.collect.AbstractMapBasedMultimap;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/* renamed from: X.tp  reason: case insensitive filesystem */
public final class C1149tp extends UJ<K, Collection<V>> {
    public final transient Map A00;
    public final /* synthetic */ AbstractMapBasedMultimap A01;

    public C1149tp(AbstractMapBasedMultimap abstractMapBasedMultimap, Map map) {
        this.A01 = abstractMapBasedMultimap;
        this.A00 = map;
    }

    public final void clear() {
        Map map = this.A00;
        AbstractMapBasedMultimap abstractMapBasedMultimap = this.A01;
        if (map == abstractMapBasedMultimap.A01) {
            abstractMapBasedMultimap.clear();
        } else {
            UB.A00(new C0354Te(this));
        }
    }

    public final boolean containsKey(Object obj) {
        Map map = this.A00;
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

    public final boolean equals(Object obj) {
        if (this == obj || this.A00.equals(obj)) {
            return true;
        }
        return false;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object get(Object obj) {
        Object obj2;
        Map map = this.A00;
        if (map != null) {
            try {
                obj2 = map.get(obj);
            } catch (ClassCastException | NullPointerException unused) {
                obj2 = null;
            }
            Collection collection = (Collection) obj2;
            if (collection == null) {
                return null;
            }
            return this.A01.A04(obj, collection);
        }
        throw null;
    }

    public final int hashCode() {
        return this.A00.hashCode();
    }

    @Override // java.util.AbstractMap, X.UJ, java.util.Map
    public final Set keySet() {
        return this.A01.keySet();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object remove(Object obj) {
        Collection collection = (Collection) this.A00.remove(obj);
        if (collection == null) {
            return null;
        }
        AbstractMapBasedMultimap abstractMapBasedMultimap = this.A01;
        Collection A03 = abstractMapBasedMultimap.A03();
        A03.addAll(collection);
        abstractMapBasedMultimap.A00 -= collection.size();
        collection.clear();
        return A03;
    }

    public final int size() {
        return this.A00.size();
    }

    public final String toString() {
        return this.A00.toString();
    }
}
