package X;

import com.google.common.collect.HashBiMap;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* renamed from: X.0e5  reason: invalid class name */
public final class AnonymousClass0e5 extends AbstractC06700pe<K, V, V> {
    public final /* synthetic */ HashBiMap A00;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass0e5(HashBiMap hashBiMap) {
        super(hashBiMap);
        this.A00 = hashBiMap;
    }

    @Override // X.AbstractC06700pe
    public final V A00(int i) {
        return this.A00.A0C[i];
    }

    public final boolean contains(@NullableDecl Object obj) {
        return this.A00.containsValue(obj);
    }

    public final boolean remove(@NullableDecl Object obj) {
        int A02 = C06710pf.A02(obj);
        HashBiMap hashBiMap = this.A00;
        int A0C = hashBiMap.A0C(obj, A02);
        if (A0C == -1) {
            return false;
        }
        hashBiMap.A0F(A0C, A02);
        return true;
    }
}
