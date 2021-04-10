package X;

import com.google.common.collect.CompactHashMap;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/* renamed from: X.0pv  reason: invalid class name and case insensitive filesystem */
public class C06810pv<K, V> {
    public Map<K, Collection<V>> A00 = new CompactHashMap();

    @CanIgnoreReturnValue
    public C06810pv<K, V> A00(K k, Iterable<? extends V> iterable) {
        if (k != null) {
            Collection<V> collection = this.A00.get(k);
            Iterator<? extends V> it = iterable.iterator();
            if (collection != null) {
                while (it.hasNext()) {
                    V v = (V) it.next();
                    AnonymousClass0p6.A01(k, v);
                    collection.add(v);
                }
            } else if (it.hasNext()) {
                Collection<V> A01 = A01();
                while (it.hasNext()) {
                    V v2 = (V) it.next();
                    AnonymousClass0p6.A01(k, v2);
                    A01.add(v2);
                }
                this.A00.put(k, A01);
                return this;
            }
            return this;
        }
        throw new NullPointerException(AnonymousClass006.A05("null key in entry: null=", C06900qE.A00(iterable)));
    }

    public Collection<V> A01() {
        return new ArrayList();
    }
}
