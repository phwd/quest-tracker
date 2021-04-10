package X;

import com.google.common.collect.AbstractMapBasedMultimap;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public final class Dz extends AbstractC1174uV<K, Collection<V>> {
    public final /* synthetic */ C1149tp A00;

    public Dz(C1149tp tpVar) {
        this.A00 = tpVar;
    }

    @Override // X.AbstractC1174uV
    public final boolean contains(Object obj) {
        Set entrySet = this.A00.A00.entrySet();
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
    public final Iterator iterator() {
        return new C0354Te(this.A00);
    }

    @Override // X.AbstractC1174uV
    public final boolean remove(Object obj) {
        Object obj2;
        if (!contains(obj)) {
            return false;
        }
        AbstractMapBasedMultimap abstractMapBasedMultimap = this.A00.A01;
        Object key = ((Map.Entry) obj).getKey();
        Map map = abstractMapBasedMultimap.A01;
        if (map != null) {
            try {
                obj2 = map.remove(key);
            } catch (ClassCastException | NullPointerException unused) {
                obj2 = null;
            }
            Collection collection = (Collection) obj2;
            if (collection == null) {
                return true;
            }
            int size = collection.size();
            collection.clear();
            abstractMapBasedMultimap.A00 -= size;
            return true;
        }
        throw null;
    }
}
