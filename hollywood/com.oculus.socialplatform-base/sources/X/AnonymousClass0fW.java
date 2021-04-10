package X;

import com.google.common.collect.HashBiMap;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* renamed from: X.0fW  reason: invalid class name */
public final class AnonymousClass0fW extends AbstractC05140uH<K, V, V> {
    public final /* synthetic */ HashBiMap A00;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass0fW(HashBiMap hashBiMap) {
        super(hashBiMap);
        this.A00 = hashBiMap;
    }

    @Override // X.AbstractC05140uH
    public final V A00(int i) {
        return this.A00.A0D[i];
    }

    public final boolean contains(@NullableDecl Object obj) {
        return this.A00.containsValue(obj);
    }

    public final boolean remove(@NullableDecl Object obj) {
        int A02 = C05150uI.A02(obj);
        HashBiMap hashBiMap = this.A00;
        int A0B = hashBiMap.A0B(obj, A02);
        if (A0B == -1) {
            return false;
        }
        HashBiMap.A07(hashBiMap, A0B, C05150uI.A02(hashBiMap.A0C[A0B]), A02);
        return true;
    }
}
