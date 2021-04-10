package X;

import com.google.common.collect.HashBiMap;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* renamed from: X.0fX  reason: invalid class name */
public final class AnonymousClass0fX extends AbstractC05140uH<K, V, K> {
    public final /* synthetic */ HashBiMap A00;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass0fX(HashBiMap hashBiMap) {
        super(hashBiMap);
        this.A00 = hashBiMap;
    }

    @Override // X.AbstractC05140uH
    public final K A00(int i) {
        return this.A00.A0C[i];
    }

    public final boolean contains(@NullableDecl Object obj) {
        return this.A00.containsKey(obj);
    }

    public final boolean remove(@NullableDecl Object obj) {
        int A02 = C05150uI.A02(obj);
        HashBiMap hashBiMap = this.A00;
        int A0A = hashBiMap.A0A(obj, A02);
        if (A0A == -1) {
            return false;
        }
        hashBiMap.A0D(A0A, A02);
        return true;
    }
}
