package X;

import java.util.Map;

/* renamed from: X.0dk  reason: invalid class name and case insensitive filesystem */
public class C03970dk extends AnonymousClass06C<K, V> {
    public final /* synthetic */ C03960dj A00;

    public C03970dk(C03960dj r1) {
        this.A00 = r1;
    }

    @Override // X.AnonymousClass06C
    public final int A00() {
        return ((AnonymousClass06D) this.A00).A00;
    }

    @Override // X.AnonymousClass06C
    public final int A01(Object obj) {
        return this.A00.A05(obj);
    }

    @Override // X.AnonymousClass06C
    public final int A02(Object obj) {
        return this.A00.A04(obj);
    }

    @Override // X.AnonymousClass06C
    public final Object A03(int i, int i2) {
        return this.A00.A02[(i << 1) + i2];
    }

    @Override // X.AnonymousClass06C
    public final V A04(int i, V v) {
        int i2 = (i << 1) + 1;
        Object[] objArr = this.A00.A02;
        V v2 = (V) objArr[i2];
        objArr[i2] = v;
        return v2;
    }

    @Override // X.AnonymousClass06C
    public final Map<K, V> A05() {
        return this.A00;
    }

    @Override // X.AnonymousClass06C
    public final void A06() {
        this.A00.clear();
    }

    @Override // X.AnonymousClass06C
    public final void A07(int i) {
        this.A00.A06(i);
    }

    @Override // X.AnonymousClass06C
    public final void A08(K k, V v) {
        this.A00.put(k, v);
    }
}
