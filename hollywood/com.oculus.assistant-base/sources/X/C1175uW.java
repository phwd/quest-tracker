package X;

import java.util.Iterator;
import java.util.Map;

/* renamed from: X.uW  reason: case insensitive filesystem */
public class C1175uW<K, V> extends UV<K> {
    public final Map A00;

    public void clear() {
        this.A00.clear();
    }

    public final boolean contains(Object obj) {
        return this.A00.containsKey(obj);
    }

    public final boolean isEmpty() {
        return this.A00.isEmpty();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
    public Iterator iterator() {
        return new C1170uR(this.A00.entrySet().iterator());
    }

    public final int size() {
        return this.A00.size();
    }

    public C1175uW(Map map) {
        if (map != null) {
            this.A00 = map;
            return;
        }
        throw null;
    }

    public boolean remove(Object obj) {
        if (!contains(obj)) {
            return false;
        }
        this.A00.remove(obj);
        return true;
    }
}
