package X;

import com.google.common.base.Objects;
import com.google.common.collect.HashBiMap;
import java.util.Map;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* renamed from: X.0fY  reason: invalid class name */
public class AnonymousClass0fY<K, V> extends AbstractC05140uH<K, V, Map.Entry<V, K>> {
    @Override // X.AbstractC05140uH
    public final Object A00(int i) {
        return new C01600fb(this.A00, i);
    }

    public final boolean contains(@NullableDecl Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        Object key = entry.getKey();
        Object value = entry.getValue();
        HashBiMap<K, V> hashBiMap = this.A00;
        int A0B = hashBiMap.A0B(key, C05150uI.A02(key));
        if (A0B == -1 || !Objects.equal(hashBiMap.A0C[A0B], value)) {
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
        int A02 = C05150uI.A02(key);
        HashBiMap<K, V> hashBiMap = this.A00;
        int A0B = hashBiMap.A0B(key, A02);
        if (A0B == -1 || !Objects.equal(hashBiMap.A0C[A0B], value)) {
            return false;
        }
        HashBiMap.A07(hashBiMap, A0B, C05150uI.A02(hashBiMap.A0C[A0B]), A02);
        return true;
    }

    public AnonymousClass0fY(HashBiMap<K, V> hashBiMap) {
        super(hashBiMap);
    }
}
