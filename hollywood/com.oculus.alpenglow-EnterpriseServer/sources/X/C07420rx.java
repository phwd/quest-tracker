package X;

import com.google.common.collect.CompactHashMap;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.ImmutableMultimap;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/* renamed from: X.0rx  reason: invalid class name and case insensitive filesystem */
public class C07420rx<K, V> {
    public Map<K, Collection<V>> A00 = new CompactHashMap();

    @CanIgnoreReturnValue
    public C07420rx<K, V> A00(K k, Iterable<? extends V> iterable) {
        if (k != null) {
            Collection<V> collection = this.A00.get(k);
            Iterator<? extends V> it = iterable.iterator();
            if (collection != null) {
                while (it.hasNext()) {
                    V v = (V) it.next();
                    C07340r5.A01(k, v);
                    collection.add(v);
                }
            } else if (it.hasNext()) {
                Collection<V> A03 = A03();
                while (it.hasNext()) {
                    V v2 = (V) it.next();
                    C07340r5.A01(k, v2);
                    A03.add(v2);
                }
                this.A00.put(k, A03);
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
        throw new NullPointerException(AnonymousClass006.A05("null key in entry: null=", sb.toString()));
    }

    public ImmutableMultimap<K, V> A02() {
        return ImmutableListMultimap.A02(this.A00.entrySet());
    }

    public Collection<V> A03() {
        return new ArrayList();
    }

    @CanIgnoreReturnValue
    public C07420rx<K, V> A01(K k, V v) {
        C07340r5.A01(k, v);
        Collection<V> collection = this.A00.get(k);
        if (collection == null) {
            Map<K, Collection<V>> map = this.A00;
            collection = A03();
            map.put(k, collection);
        }
        collection.add(v);
        return this;
    }
}
