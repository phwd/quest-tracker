package X;

import com.google.common.base.Objects;
import com.google.common.collect.CompactHashMap;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* renamed from: X.0eG  reason: invalid class name */
public final class AnonymousClass0eG extends AnonymousClass0oy<K, V> {
    public int A00;
    @NullableDecl
    public final K A01;
    public final /* synthetic */ CompactHashMap A02;

    public AnonymousClass0eG(CompactHashMap compactHashMap, int i) {
        this.A02 = compactHashMap;
        this.A01 = (K) compactHashMap.A06[i];
        this.A00 = i;
    }

    private void A00() {
        int i = this.A00;
        if (i != -1) {
            CompactHashMap compactHashMap = this.A02;
            if (i < compactHashMap.size() && Objects.equal(this.A01, compactHashMap.A06[i])) {
                return;
            }
        }
        this.A00 = CompactHashMap.A00(this.A02, this.A01);
    }

    @Override // java.util.Map.Entry, X.AnonymousClass0oy
    public final K getKey() {
        return this.A01;
    }

    @Override // java.util.Map.Entry, X.AnonymousClass0oy
    public final V getValue() {
        A00();
        int i = this.A00;
        if (i == -1) {
            return null;
        }
        return (V) this.A02.A07[i];
    }

    @Override // java.util.Map.Entry, X.AnonymousClass0oy
    public final V setValue(V v) {
        A00();
        int i = this.A00;
        if (i == -1) {
            this.A02.put(this.A01, v);
            return null;
        }
        Object[] objArr = this.A02.A07;
        V v2 = (V) objArr[i];
        objArr[i] = v;
        return v2;
    }
}
