package X;

import com.google.common.collect.CompactHashMap;
import com.google.common.collect.CompactHashSet;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.ImmutableMultimap;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/* renamed from: X.Qq  reason: case insensitive filesystem */
public class C0118Qq<K, V> {
    public Map<K, Collection<V>> A00 = new CompactHashMap();

    /* JADX DEBUG: Multi-variable search result rejected for r0v13, resolved type: java.util.Map<K, java.util.Collection<V>> */
    /* JADX WARN: Multi-variable type inference failed */
    @CanIgnoreReturnValue
    public C0118Qq<K, V> A00(K k, Iterable<? extends V> iterable) {
        Collection collection;
        if (k != null) {
            Collection<V> collection2 = this.A00.get(k);
            Iterator<? extends V> it = iterable.iterator();
            if (collection2 != null) {
                while (it.hasNext()) {
                    V v = (V) it.next();
                    QN.A01(k, v);
                    collection2.add(v);
                }
            } else if (it.hasNext()) {
                if (!(this instanceof N1)) {
                    collection = new ArrayList();
                } else {
                    collection = new CompactHashSet();
                }
                while (it.hasNext()) {
                    Object next = it.next();
                    QN.A01(k, next);
                    collection.add(next);
                }
                this.A00.put(k, collection);
                return this;
            }
            return this;
        }
        Iterator<? extends V> it2 = iterable.iterator();
        StringBuilder sb = new StringBuilder("[");
        boolean z = true;
        while (it2.hasNext()) {
            if (!z) {
                sb.append(", ");
            }
            z = false;
            sb.append(it2.next());
        }
        sb.append(']');
        throw new NullPointerException(AnonymousClass06.A03("null key in entry: null=", sb.toString()));
    }

    public ImmutableMultimap<K, V> A02() {
        return ImmutableListMultimap.A03(this.A00.entrySet());
    }

    @CanIgnoreReturnValue
    public C0118Qq<K, V> A01(K k, V v) {
        QN.A01(k, v);
        Collection<V> collection = this.A00.get(k);
        if (collection == null) {
            Map<K, Collection<V>> map = this.A00;
            if (!(this instanceof N1)) {
                collection = new ArrayList<>();
            } else {
                collection = new CompactHashSet<>();
            }
            map.put(k, collection);
        }
        collection.add(v);
        return this;
    }
}
