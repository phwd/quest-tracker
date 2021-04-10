package X;

import java.util.Map;

/* renamed from: X.0wi  reason: invalid class name and case insensitive filesystem */
public class C05720wi extends AnonymousClass02u<K, V> {
    public final /* synthetic */ C05700wg A00;

    public C05720wi(C05700wg r1) {
        this.A00 = r1;
    }

    @Override // X.AnonymousClass02u
    public final int A00() {
        return ((C000502v) this.A00).A00;
    }

    @Override // X.AnonymousClass02u
    public final int A01(Object obj) {
        return this.A00.A05(obj);
    }

    @Override // X.AnonymousClass02u
    public final int A02(Object obj) {
        return this.A00.A04(obj);
    }

    @Override // X.AnonymousClass02u
    public final Object A03(int i, int i2) {
        return this.A00.A02[(i << 1) + i2];
    }

    @Override // X.AnonymousClass02u
    public final V A04(int i, V v) {
        return (V) this.A00.A07(i, v);
    }

    @Override // X.AnonymousClass02u
    public final void A06() {
        this.A00.clear();
    }

    @Override // X.AnonymousClass02u
    public final void A07(int i) {
        this.A00.A06(i);
    }

    @Override // X.AnonymousClass02u
    public final void A08(K k, V v) {
        this.A00.put(k, v);
    }

    @Override // X.AnonymousClass02u
    public final Map<K, V> A05() {
        return this.A00;
    }
}
