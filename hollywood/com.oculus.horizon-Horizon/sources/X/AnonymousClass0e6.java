package X;

import com.google.common.collect.HashBiMap;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* renamed from: X.0e6  reason: invalid class name */
public final class AnonymousClass0e6 extends AbstractC06700pe<K, V, K> {
    public final /* synthetic */ HashBiMap A00;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass0e6(HashBiMap hashBiMap) {
        super(hashBiMap);
        this.A00 = hashBiMap;
    }

    @Override // X.AbstractC06700pe
    public final K A00(int i) {
        return this.A00.A0B[i];
    }

    public final boolean contains(@NullableDecl Object obj) {
        return this.A00.containsKey(obj);
    }

    public final boolean remove(@NullableDecl Object obj) {
        int A02 = C06710pf.A02(obj);
        HashBiMap hashBiMap = this.A00;
        int A0B = hashBiMap.A0B(obj, A02);
        if (A0B == -1) {
            return false;
        }
        hashBiMap.A0E(A0B, A02);
        return true;
    }
}
