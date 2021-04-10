package X;

import com.google.common.base.Objects;
import com.google.common.collect.HashBiMap;
import java.util.Map;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* renamed from: X.0e7  reason: invalid class name */
public class AnonymousClass0e7<K, V> extends AbstractC06700pe<K, V, Map.Entry<V, K>> {
    @Override // X.AbstractC06700pe
    public final Object A00(int i) {
        return new AnonymousClass0eA(this.A00, i);
    }

    public final boolean contains(@NullableDecl Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        Object key = entry.getKey();
        Object value = entry.getValue();
        HashBiMap<K, V> hashBiMap = this.A00;
        int A0C = hashBiMap.A0C(key, C06710pf.A02(key));
        if (A0C == -1 || !Objects.equal(hashBiMap.A0B[A0C], value)) {
            return false;
        }
        return true;
    }

    public final boolean remove(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        Object key = entry.getKey();
        Object value = entry.getValue();
        int A02 = C06710pf.A02(key);
        HashBiMap<K, V> hashBiMap = this.A00;
        int A0C = hashBiMap.A0C(key, A02);
        if (A0C == -1 || !Objects.equal(hashBiMap.A0B[A0C], value)) {
            return false;
        }
        hashBiMap.A0F(A0C, A02);
        return true;
    }

    public AnonymousClass0e7(HashBiMap<K, V> hashBiMap) {
        super(hashBiMap);
    }
}
