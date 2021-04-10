package X;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;

public class V1 extends AbstractSet<Map.Entry<K, V>> {
    public final /* synthetic */ VD A00;

    public V1(VD vd) {
        this.A00 = vd;
    }

    public final void clear() {
        this.A00.clear();
    }

    public final boolean contains(Object obj) {
        if (!(obj instanceof Map.Entry) || this.A00.A05((Map.Entry) obj) == null) {
            return false;
        }
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
    public final Iterator<Map.Entry<K, V>> iterator() {
        return new Ti(this);
    }

    public final boolean remove(Object obj) {
        VD vd;
        VC A05;
        if (!(obj instanceof Map.Entry) || (A05 = (vd = this.A00).A05((Map.Entry) obj)) == null) {
            return false;
        }
        vd.A06(A05, true);
        return true;
    }

    public final int size() {
        return this.A00.size;
    }
}
