package X;

import java.util.AbstractCollection;
import java.util.Map;

public abstract class UL<K, V> extends AbstractCollection<Map.Entry<K, V>> {
    public final void clear() {
        ((C1155tv) this).A00.clear();
    }

    public final int size() {
        return ((C1155tv) this).A00.size();
    }

    public final boolean contains(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        return ((C1155tv) this).A00.A1U(entry.getKey(), entry.getValue());
    }

    public final boolean remove(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        return ((C1155tv) this).A00.remove(entry.getKey(), entry.getValue());
    }
}
