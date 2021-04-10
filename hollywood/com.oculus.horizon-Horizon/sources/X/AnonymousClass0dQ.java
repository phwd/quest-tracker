package X;

import com.google.common.base.Objects;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

/* renamed from: X.0dQ  reason: invalid class name */
public abstract class AnonymousClass0dQ<K, V> extends AnonymousClass0rW<Map.Entry<K, V>> {
    public abstract Map<K, V> A00();

    public boolean contains(Object obj) {
        V v;
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        Object key = entry.getKey();
        Map<K, V> A00 = A00();
        if (A00 != null) {
            try {
                v = A00.get(key);
            } catch (ClassCastException | NullPointerException unused) {
                v = null;
            }
            if (!Objects.equal(v, entry.getValue())) {
                return false;
            }
            if (v != null || A00.containsKey(key)) {
                return true;
            }
            return false;
        }
        throw null;
    }

    @Override // X.AnonymousClass0rW, java.util.AbstractCollection, java.util.Collection, java.util.AbstractSet, java.util.Set
    public final boolean removeAll(Collection<?> collection) {
        if (collection != null) {
            try {
                return super.removeAll(collection);
            } catch (UnsupportedOperationException unused) {
                Iterator<?> it = collection.iterator();
                boolean z = false;
                while (it.hasNext()) {
                    z |= remove(it.next());
                }
                return z;
            }
        } else {
            throw null;
        }
    }

    @Override // X.AnonymousClass0rW, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean retainAll(Collection<?> collection) {
        if (collection != null) {
            try {
                return super.retainAll(collection);
            } catch (UnsupportedOperationException unused) {
                HashSet A01 = C07190ra.A01(collection.size());
                for (Object obj : collection) {
                    if (contains(obj)) {
                        A01.add(((Map.Entry) obj).getKey());
                    }
                }
                return A00().keySet().retainAll(A01);
            }
        } else {
            throw null;
        }
    }

    public final void clear() {
        A00().clear();
    }

    public final boolean isEmpty() {
        return A00().isEmpty();
    }

    public boolean remove(Object obj) {
        if (contains(obj)) {
            return A00().keySet().remove(((Map.Entry) obj).getKey());
        }
        return false;
    }

    public final int size() {
        return A00().size();
    }
}
