package X;

import com.google.common.base.Objects;
import com.google.common.collect.HashBiMap;

/* renamed from: X.0eA  reason: invalid class name */
public final class AnonymousClass0eA<K, V> extends AnonymousClass0oy<V, K> {
    public int A00;
    public final HashBiMap<K, V> A01;
    public final V A02;

    private void A00() {
        int i = this.A00;
        if (i != -1) {
            HashBiMap<K, V> hashBiMap = this.A01;
            if (i <= hashBiMap.A00 && Objects.equal(this.A02, hashBiMap.A0C[i])) {
                return;
            }
        }
        HashBiMap<K, V> hashBiMap2 = this.A01;
        V v = this.A02;
        this.A00 = hashBiMap2.A0C(v, C06710pf.A02(v));
    }

    public AnonymousClass0eA(HashBiMap<K, V> hashBiMap, int i) {
        this.A01 = hashBiMap;
        this.A02 = hashBiMap.A0C[i];
        this.A00 = i;
    }

    @Override // java.util.Map.Entry, X.AnonymousClass0oy
    public final V getKey() {
        return this.A02;
    }

    @Override // java.util.Map.Entry, X.AnonymousClass0oy
    public final K getValue() {
        A00();
        int i = this.A00;
        if (i == -1) {
            return null;
        }
        return this.A01.A0B[i];
    }

    @Override // java.util.Map.Entry, X.AnonymousClass0oy
    public final K setValue(K k) {
        A00();
        int i = this.A00;
        if (i == -1) {
            return this.A01.A0D(this.A02, k, false);
        }
        HashBiMap<K, V> hashBiMap = this.A01;
        K k2 = hashBiMap.A0B[i];
        if (Objects.equal(k2, k)) {
            return k;
        }
        HashBiMap.A09(hashBiMap, this.A00, k, false);
        return k2;
    }
}
