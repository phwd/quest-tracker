package X;

import com.google.common.collect.AbstractBiMap;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* renamed from: X.0Bl  reason: invalid class name */
public class AnonymousClass0Bl extends AnonymousClass0NM<Map.Entry<K, V>> {
    public final Set<Map.Entry<K, V>> A00;
    public final /* synthetic */ AbstractBiMap A01;

    public AnonymousClass0Bl(AbstractBiMap abstractBiMap) {
        this.A01 = abstractBiMap;
        this.A00 = abstractBiMap.A01.entrySet();
    }

    @Override // X.AbstractC01640ff
    public final void clear() {
        this.A01.clear();
    }

    @Override // X.AbstractC01640ff, java.util.Collection, java.util.Set, java.lang.Iterable
    public final Iterator<Map.Entry<K, V>> iterator() {
        AbstractBiMap abstractBiMap = this.A01;
        return new C05090tP(abstractBiMap, abstractBiMap.A01.entrySet().iterator());
    }

    @Override // X.AbstractC01640ff
    public final boolean remove(Object obj) {
        Set<Map.Entry<K, V>> set = this.A00;
        if (!set.contains(obj)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        this.A01.A00.A01.remove(entry.getValue());
        set.remove(entry);
        return true;
    }

    @Override // X.AbstractC01640ff, java.util.Collection, java.util.Set
    public final boolean removeAll(Collection<?> collection) {
        if (collection != null) {
            return AnonymousClass0wE.A02(this, collection);
        }
        throw null;
    }

    @Override // X.AnonymousClass0NM
    /* renamed from: A05 */
    public final Set<Map.Entry<K, V>> A02() {
        return this.A00;
    }

    @Override // X.AbstractC01640ff
    public final boolean contains(Object obj) {
        Set A05 = A02();
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        if (entry != null) {
            return A05.contains(new AnonymousClass0f9(entry));
        }
        throw null;
    }

    @Override // X.AbstractC01640ff, java.util.Collection, java.util.Set
    public final boolean containsAll(Collection<?> collection) {
        Iterator<?> it = collection.iterator();
        while (it.hasNext()) {
            if (!contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    @Override // X.AbstractC01640ff, java.util.Collection, java.util.Set
    public final boolean retainAll(Collection<?> collection) {
        return A03(collection);
    }

    @Override // X.AbstractC01640ff
    public final Object[] toArray() {
        return toArray(new Object[size()]);
    }

    @Override // X.AbstractC01640ff, java.util.Collection, java.util.Set
    public final <T> T[] toArray(T[] tArr) {
        return (T[]) A04(tArr);
    }
}
