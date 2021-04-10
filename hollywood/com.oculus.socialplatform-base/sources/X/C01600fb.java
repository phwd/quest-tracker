package X;

import com.google.common.base.Objects;
import com.google.common.collect.HashBiMap;

/* renamed from: X.0fb  reason: invalid class name and case insensitive filesystem */
public final class C01600fb<K, V> extends AnonymousClass0tY<V, K> {
    public int A00;
    public final HashBiMap<K, V> A01;
    public final V A02;

    private void A00() {
        int i = this.A00;
        if (i != -1) {
            HashBiMap<K, V> hashBiMap = this.A01;
            if (i <= hashBiMap.A03 && Objects.equal(this.A02, hashBiMap.A0D[i])) {
                return;
            }
        }
        HashBiMap<K, V> hashBiMap2 = this.A01;
        V v = this.A02;
        this.A00 = hashBiMap2.A0B(v, C05150uI.A02(v));
    }

    public C01600fb(HashBiMap<K, V> hashBiMap, int i) {
        this.A01 = hashBiMap;
        this.A02 = hashBiMap.A0D[i];
        this.A00 = i;
    }

    @Override // X.AnonymousClass0tY, java.util.Map.Entry
    public final V getKey() {
        return this.A02;
    }

    @Override // X.AnonymousClass0tY, java.util.Map.Entry
    public final K getValue() {
        A00();
        int i = this.A00;
        if (i == -1) {
            return null;
        }
        return this.A01.A0C[i];
    }

    @Override // X.AnonymousClass0tY, java.util.Map.Entry
    public final K setValue(K k) {
        A00();
        int i = this.A00;
        if (i == -1) {
            return (K) this.A01.A0C(this.A02, k);
        }
        HashBiMap<K, V> hashBiMap = this.A01;
        K k2 = hashBiMap.A0C[i];
        if (Objects.equal(k2, k)) {
            return k;
        }
        HashBiMap.A08(hashBiMap, this.A00, k);
        return k2;
    }
}
