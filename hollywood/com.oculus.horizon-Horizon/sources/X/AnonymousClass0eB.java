package X;

import com.google.common.base.Objects;
import com.google.common.collect.HashBiMap;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* renamed from: X.0eB  reason: invalid class name */
public final class AnonymousClass0eB extends AnonymousClass0oy<K, V> {
    public int A00;
    @NullableDecl
    public final K A01;
    public final /* synthetic */ HashBiMap A02;

    public AnonymousClass0eB(HashBiMap hashBiMap, int i) {
        this.A02 = hashBiMap;
        this.A01 = hashBiMap.A0B[i];
        this.A00 = i;
    }

    private final void A00() {
        int i = this.A00;
        if (i != -1) {
            HashBiMap hashBiMap = this.A02;
            if (i <= hashBiMap.A00 && Objects.equal(hashBiMap.A0B[i], this.A01)) {
                return;
            }
        }
        HashBiMap hashBiMap2 = this.A02;
        K k = this.A01;
        this.A00 = hashBiMap2.A0B(k, C06710pf.A02(k));
    }

    @Override // java.util.Map.Entry, X.AnonymousClass0oy
    public final K getKey() {
        return this.A01;
    }

    @Override // java.util.Map.Entry, X.AnonymousClass0oy
    @NullableDecl
    public final V getValue() {
        A00();
        int i = this.A00;
        if (i == -1) {
            return null;
        }
        return this.A02.A0C[i];
    }

    @Override // java.util.Map.Entry, X.AnonymousClass0oy
    public final V setValue(V v) {
        A00();
        int i = this.A00;
        if (i == -1) {
            return (V) this.A02.put(this.A01, v);
        }
        HashBiMap hashBiMap = this.A02;
        V v2 = hashBiMap.A0C[i];
        if (Objects.equal(v2, v)) {
            return v;
        }
        HashBiMap.A0A(hashBiMap, this.A00, v, false);
        return v2;
    }
}
