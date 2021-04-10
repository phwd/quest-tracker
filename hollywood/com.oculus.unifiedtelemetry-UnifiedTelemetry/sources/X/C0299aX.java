package X;

import java.util.Map;

/* renamed from: X.aX  reason: case insensitive filesystem */
public class C0299aX extends AnonymousClass2n<K, V> {
    public final /* synthetic */ C0298aW A00;

    public C0299aX(C0298aW aWVar) {
        this.A00 = aWVar;
    }

    @Override // X.AnonymousClass2n
    public final int A00() {
        return ((C00062o) this.A00).A00;
    }

    @Override // X.AnonymousClass2n
    public final int A01(Object obj) {
        return this.A00.A05(obj);
    }

    @Override // X.AnonymousClass2n
    public final int A02(Object obj) {
        return this.A00.A04(obj);
    }

    @Override // X.AnonymousClass2n
    public final Object A03(int i, int i2) {
        return this.A00.A02[(i << 1) + i2];
    }

    @Override // X.AnonymousClass2n
    public final V A04(int i, V v) {
        int i2 = (i << 1) + 1;
        Object[] objArr = this.A00.A02;
        V v2 = (V) objArr[i2];
        objArr[i2] = v;
        return v2;
    }

    @Override // X.AnonymousClass2n
    public final void A06() {
        this.A00.clear();
    }

    @Override // X.AnonymousClass2n
    public final void A07(int i) {
        this.A00.A06(i);
    }

    @Override // X.AnonymousClass2n
    public final void A08(K k, V v) {
        this.A00.put(k, v);
    }

    @Override // X.AnonymousClass2n
    public final Map<K, V> A05() {
        return this.A00;
    }
}
