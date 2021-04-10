package X;

import java.util.Map;

/* renamed from: X.0st  reason: invalid class name and case insensitive filesystem */
public class C07500st extends AnonymousClass02n<K, V> {
    public final /* synthetic */ C07490ss A00;

    public C07500st(C07490ss r1) {
        this.A00 = r1;
    }

    @Override // X.AnonymousClass02n
    public final int A01() {
        return ((C000602o) this.A00).A00;
    }

    @Override // X.AnonymousClass02n
    public final int A02(Object obj) {
        return this.A00.A05(obj);
    }

    @Override // X.AnonymousClass02n
    public final int A03(Object obj) {
        return this.A00.A04(obj);
    }

    @Override // X.AnonymousClass02n
    public final Object A04(int i, int i2) {
        return this.A00.A02[(i << 1) + i2];
    }

    @Override // X.AnonymousClass02n
    public final V A05(int i, V v) {
        int i2 = (i << 1) + 1;
        Object[] objArr = this.A00.A02;
        V v2 = (V) objArr[i2];
        objArr[i2] = v;
        return v2;
    }

    @Override // X.AnonymousClass02n
    public final void A07() {
        this.A00.clear();
    }

    @Override // X.AnonymousClass02n
    public final void A08(int i) {
        this.A00.A06(i);
    }

    @Override // X.AnonymousClass02n
    public final void A09(K k, V v) {
        this.A00.put(k, v);
    }

    @Override // X.AnonymousClass02n
    public final Map<K, V> A06() {
        return this.A00;
    }
}
