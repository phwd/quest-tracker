package X;

import com.google.j2objc.annotations.Weak;
import java.util.Iterator;
import java.util.Map;

/* renamed from: X.0dP  reason: invalid class name */
public class AnonymousClass0dP<K, V> extends AnonymousClass0rW<K> {
    @Weak
    public final Map<K, V> A00;

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
    public Iterator<K> iterator() {
        return new AnonymousClass0dT(this.A00.entrySet().iterator());
    }

    public final int size() {
        return this.A00.size();
    }

    public AnonymousClass0dP(Map<K, V> map) {
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
