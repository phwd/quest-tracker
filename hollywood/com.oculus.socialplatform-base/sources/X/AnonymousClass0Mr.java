package X;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* renamed from: X.0Mr  reason: invalid class name */
public class AnonymousClass0Mr extends AnonymousClass0f7<K, Collection<V>> {
    public final /* synthetic */ AnonymousClass0f4 A00;

    public AnonymousClass0Mr(AnonymousClass0f4 r1) {
        this.A00 = r1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
    public final Iterator<Map.Entry<K, Collection<V>>> iterator() {
        Set<K> keySet = this.A00.A00.keySet();
        return new AnonymousClass0fA(keySet.iterator(), new AnonymousClass0f5(this));
    }

    @Override // X.AnonymousClass0f7
    public final Map<K, Collection<V>> A00() {
        return this.A00;
    }

    @Override // X.AnonymousClass0f7
    public final boolean remove(Object obj) {
        if (!contains(obj)) {
            return false;
        }
        AnonymousClass0f4 r0 = this.A00;
        r0.A00.keySet().remove(((Map.Entry) obj).getKey());
        return true;
    }
}
