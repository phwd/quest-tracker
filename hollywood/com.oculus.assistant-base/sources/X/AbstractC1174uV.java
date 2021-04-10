package X;

import com.google.common.base.Objects;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;

/* renamed from: X.uV  reason: case insensitive filesystem */
public abstract class AbstractC1174uV<K, V> extends UV<Map.Entry<K, V>> {
    private final Map A00() {
        if (!(this instanceof C0132Cl)) {
            return ((Dz) this).A00;
        }
        return ((C0132Cl) this).A00;
    }

    public boolean contains(Object obj) {
        Object obj2;
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        Object key = entry.getKey();
        Map A00 = A00();
        if (A00 != null) {
            try {
                obj2 = A00.get(key);
            } catch (ClassCastException | NullPointerException unused) {
                obj2 = null;
            }
            if (!Objects.equal(obj2, entry.getValue())) {
                return false;
            }
            if (obj2 != null || A00.containsKey(key)) {
                return true;
            }
            return false;
        }
        throw null;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.AbstractSet, java.util.Set, X.UV
    public final boolean removeAll(Collection collection) {
        if (collection != null) {
            try {
                return super.removeAll(collection);
            } catch (UnsupportedOperationException unused) {
                boolean z = false;
                for (Object obj : collection) {
                    z |= remove(obj);
                }
                return z;
            }
        } else {
            throw null;
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, X.UV
    public final boolean retainAll(Collection collection) {
        if (collection != null) {
            try {
                return super.retainAll(collection);
            } catch (UnsupportedOperationException unused) {
                HashSet A01 = UX.A01(collection.size());
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
