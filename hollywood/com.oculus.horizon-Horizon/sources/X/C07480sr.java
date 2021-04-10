package X;

import java.util.Map;

/* renamed from: X.0sr  reason: invalid class name and case insensitive filesystem */
public class C07480sr extends AnonymousClass02n<E, E> {
    public final /* synthetic */ C000502c A00;

    public C07480sr(C000502c r1) {
        this.A00 = r1;
    }

    @Override // X.AnonymousClass02n
    public final int A01() {
        return this.A00.A00;
    }

    @Override // X.AnonymousClass02n
    public final int A02(Object obj) {
        C000502c r1 = this.A00;
        if (obj == null) {
            return C000502c.A00(r1);
        }
        return C000502c.A01(r1, obj, obj.hashCode());
    }

    @Override // X.AnonymousClass02n
    public final int A03(Object obj) {
        C000502c r1 = this.A00;
        if (obj == null) {
            return C000502c.A00(r1);
        }
        return C000502c.A01(r1, obj, obj.hashCode());
    }

    @Override // X.AnonymousClass02n
    public final Object A04(int i, int i2) {
        return this.A00.A03[i];
    }

    @Override // X.AnonymousClass02n
    public final E A05(int i, E e) {
        throw new UnsupportedOperationException("not a map");
    }

    @Override // X.AnonymousClass02n
    public final Map<E, E> A06() {
        throw new UnsupportedOperationException("not a map");
    }

    @Override // X.AnonymousClass02n
    public final void A07() {
        this.A00.clear();
    }

    @Override // X.AnonymousClass02n
    public final void A08(int i) {
        this.A00.A04(i);
    }

    @Override // X.AnonymousClass02n
    public final void A09(E e, E e2) {
        this.A00.add(e);
    }
}
