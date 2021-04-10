package X;

import com.google.common.base.Objects;
import com.google.common.collect.HashBiMap;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Map;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* renamed from: X.0e9  reason: invalid class name */
public final class AnonymousClass0e9 extends AbstractC06700pe<K, V, Map.Entry<K, V>> {
    public final /* synthetic */ HashBiMap A00;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass0e9(HashBiMap hashBiMap) {
        super(hashBiMap);
        this.A00 = hashBiMap;
    }

    @Override // X.AbstractC06700pe
    public final Object A00(int i) {
        return new AnonymousClass0eB(this.A00, i);
    }

    public final boolean contains(@NullableDecl Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        Object key = entry.getKey();
        Object value = entry.getValue();
        HashBiMap hashBiMap = this.A00;
        int A0B = hashBiMap.A0B(key, C06710pf.A02(key));
        if (A0B == -1 || !Objects.equal(value, hashBiMap.A0C[A0B])) {
            return false;
        }
        return true;
    }

    @CanIgnoreReturnValue
    public final boolean remove(@NullableDecl Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        Object key = entry.getKey();
        Object value = entry.getValue();
        int A02 = C06710pf.A02(key);
        HashBiMap hashBiMap = this.A00;
        int A0B = hashBiMap.A0B(key, A02);
        if (A0B == -1 || !Objects.equal(value, hashBiMap.A0C[A0B])) {
            return false;
        }
        hashBiMap.A0E(A0B, A02);
        return true;
    }
}
