package X;

import com.google.common.collect.AbstractBiMap;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/* renamed from: X.0Bj  reason: invalid class name */
public class AnonymousClass0Bj extends AnonymousClass0NM<K> {
    public final /* synthetic */ AbstractBiMap A00;

    public AnonymousClass0Bj(AbstractBiMap abstractBiMap) {
        this.A00 = abstractBiMap;
    }

    @Override // X.AnonymousClass0NM
    /* renamed from: A05 */
    public final Set<K> A02() {
        return this.A00.A01.keySet();
    }

    @Override // X.AbstractC01640ff
    public final void clear() {
        this.A00.clear();
    }

    @Override // X.AbstractC01640ff, java.util.Collection, java.util.Set, java.lang.Iterable
    public final Iterator<K> iterator() {
        return new AnonymousClass0fC(this.A00.entrySet().iterator());
    }

    @Override // X.AbstractC01640ff, java.util.Collection, java.util.Set
    public final boolean removeAll(Collection<?> collection) {
        if (collection != null) {
            return AnonymousClass0wE.A02(this, collection);
        }
        throw null;
    }

    @Override // X.AbstractC01640ff
    public final boolean remove(Object obj) {
        if (!contains(obj)) {
            return false;
        }
        AbstractBiMap abstractBiMap = this.A00;
        abstractBiMap.A00.A01.remove(abstractBiMap.A01.remove(obj));
        return true;
    }

    @Override // X.AbstractC01640ff, java.util.Collection, java.util.Set
    public final boolean retainAll(Collection<?> collection) {
        return A03(collection);
    }
}
