package X;

import java.util.Map;

/* renamed from: X.aV  reason: case insensitive filesystem */
public class C0297aV extends AnonymousClass2n<E, E> {
    public final /* synthetic */ C00052c A00;

    public C0297aV(C00052c r1) {
        this.A00 = r1;
    }

    @Override // X.AnonymousClass2n
    public final int A00() {
        return this.A00.A00;
    }

    @Override // X.AnonymousClass2n
    public final int A01(Object obj) {
        C00052c r1 = this.A00;
        if (obj == null) {
            return C00052c.A00(r1);
        }
        return C00052c.A01(r1, obj, obj.hashCode());
    }

    @Override // X.AnonymousClass2n
    public final int A02(Object obj) {
        C00052c r1 = this.A00;
        if (obj == null) {
            return C00052c.A00(r1);
        }
        return C00052c.A01(r1, obj, obj.hashCode());
    }

    @Override // X.AnonymousClass2n
    public final Object A03(int i, int i2) {
        return this.A00.A03[i];
    }

    @Override // X.AnonymousClass2n
    public final E A04(int i, E e) {
        throw new UnsupportedOperationException("not a map");
    }

    @Override // X.AnonymousClass2n
    public final Map<E, E> A05() {
        throw new UnsupportedOperationException("not a map");
    }

    @Override // X.AnonymousClass2n
    public final void A06() {
        this.A00.clear();
    }

    @Override // X.AnonymousClass2n
    public final void A07(int i) {
        this.A00.A04(i);
    }

    @Override // X.AnonymousClass2n
    public final void A08(E e, E e2) {
        this.A00.add(e);
    }
}
