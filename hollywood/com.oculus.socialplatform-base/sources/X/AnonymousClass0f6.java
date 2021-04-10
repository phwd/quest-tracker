package X;

import com.google.j2objc.annotations.Weak;
import java.util.Iterator;
import java.util.Map;

/* renamed from: X.0f6  reason: invalid class name */
public class AnonymousClass0f6<K, V> extends AbstractC05580wA<K> {
    @Weak
    public final Map<K, V> A00;

    public final void clear() {
        this.A00.clear();
    }

    public final boolean contains(Object obj) {
        return this.A00.containsKey(obj);
    }

    public final boolean isEmpty() {
        return this.A00.isEmpty();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
    public final Iterator<K> iterator() {
        return new AnonymousClass0fC(this.A00.entrySet().iterator());
    }

    public final int size() {
        return this.A00.size();
    }

    public AnonymousClass0f6(Map<K, V> map) {
        this.A00 = map;
    }

    public final boolean remove(Object obj) {
        if (!contains(obj)) {
            return false;
        }
        this.A00.remove(obj);
        return true;
    }
}
