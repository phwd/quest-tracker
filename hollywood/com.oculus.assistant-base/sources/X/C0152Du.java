package X;

import com.google.common.collect.AbstractMapBasedMultimap;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/* renamed from: X.Du  reason: case insensitive filesystem */
public final class C0152Du extends C1175uW<K, Collection<V>> {
    public final /* synthetic */ AbstractMapBasedMultimap A00;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0152Du(AbstractMapBasedMultimap abstractMapBasedMultimap, Map map) {
        super(map);
        this.A00 = abstractMapBasedMultimap;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean containsAll(Collection collection) {
        return super.A00.keySet().containsAll(collection);
    }

    public final boolean equals(Object obj) {
        if (this == obj || super.A00.keySet().equals(obj)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return super.A00.keySet().hashCode();
    }

    @Override // X.C1175uW, java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
    public final Iterator iterator() {
        return new C0356Tg(this, super.A00.entrySet().iterator());
    }

    @Override // X.C1175uW
    public final boolean remove(Object obj) {
        Collection collection = (Collection) super.A00.remove(obj);
        if (collection == null) {
            return false;
        }
        int size = collection.size();
        collection.clear();
        this.A00.A00 -= size;
        if (size > 0) {
            return true;
        }
        return false;
    }

    @Override // X.C1175uW
    public final void clear() {
        UB.A00(iterator());
    }
}
