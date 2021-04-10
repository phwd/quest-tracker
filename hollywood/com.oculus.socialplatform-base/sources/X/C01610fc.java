package X;

import com.google.common.base.Objects;
import com.google.common.collect.HashBiMap;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* renamed from: X.0fc  reason: invalid class name and case insensitive filesystem */
public final class C01610fc extends AnonymousClass0tY<K, V> {
    public int A00;
    @NullableDecl
    public final K A01;
    public final /* synthetic */ HashBiMap A02;

    public C01610fc(HashBiMap hashBiMap, int i) {
        this.A02 = hashBiMap;
        this.A01 = hashBiMap.A0C[i];
        this.A00 = i;
    }

    private final void A00() {
        int i = this.A00;
        if (i != -1) {
            HashBiMap hashBiMap = this.A02;
            if (i <= hashBiMap.A03 && Objects.equal(hashBiMap.A0C[i], this.A01)) {
                return;
            }
        }
        HashBiMap hashBiMap2 = this.A02;
        K k = this.A01;
        this.A00 = hashBiMap2.A0A(k, C05150uI.A02(k));
    }

    @Override // X.AnonymousClass0tY, java.util.Map.Entry
    public final K getKey() {
        return this.A01;
    }

    @Override // X.AnonymousClass0tY, java.util.Map.Entry
    @NullableDecl
    public final V getValue() {
        A00();
        int i = this.A00;
        if (i == -1) {
            return null;
        }
        return this.A02.A0D[i];
    }

    @Override // X.AnonymousClass0tY, java.util.Map.Entry
    public final V setValue(V v) {
        A00();
        int i = this.A00;
        if (i == -1) {
            return (V) this.A02.put(this.A01, v);
        }
        HashBiMap hashBiMap = this.A02;
        V v2 = hashBiMap.A0D[i];
        if (Objects.equal(v2, v)) {
            return v;
        }
        HashBiMap.A09(hashBiMap, this.A00, v);
        return v2;
    }
}
