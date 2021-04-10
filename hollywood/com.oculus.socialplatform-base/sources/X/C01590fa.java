package X;

import com.google.common.base.Objects;
import com.google.common.collect.HashBiMap;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Map;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* renamed from: X.0fa  reason: invalid class name and case insensitive filesystem */
public final class C01590fa extends AbstractC05140uH<K, V, Map.Entry<K, V>> {
    public final /* synthetic */ HashBiMap A00;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C01590fa(HashBiMap hashBiMap) {
        super(hashBiMap);
        this.A00 = hashBiMap;
    }

    @Override // X.AbstractC05140uH
    public final Object A00(int i) {
        return new C01610fc(this.A00, i);
    }

    public final boolean contains(@NullableDecl Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        Object key = entry.getKey();
        Object value = entry.getValue();
        HashBiMap hashBiMap = this.A00;
        int A0A = hashBiMap.A0A(key, C05150uI.A02(key));
        if (A0A == -1 || !Objects.equal(value, hashBiMap.A0D[A0A])) {
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
        int A02 = C05150uI.A02(key);
        HashBiMap hashBiMap = this.A00;
        int A0A = hashBiMap.A0A(key, A02);
        if (A0A == -1 || !Objects.equal(value, hashBiMap.A0D[A0A])) {
            return false;
        }
        hashBiMap.A0D(A0A, A02);
        return true;
    }
}
