package X;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;

public class hQ extends AbstractSet<Map.Entry<K, V>> {
    public final /* synthetic */ hM A00;

    public hQ(hM hMVar) {
        this.A00 = hMVar;
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
        return new C0085Lg(this);
    }

    public final boolean remove(Object obj) {
        hM hMVar;
        hN A05;
        if (!(obj instanceof Map.Entry) || (A05 = (hMVar = this.A00).A05((Map.Entry) obj)) == null) {
            return false;
        }
        hMVar.A06(A05, true);
        return true;
    }

    public final int size() {
        return this.A00.size;
    }
}
