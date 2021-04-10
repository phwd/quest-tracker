package X;

import java.util.Map;

/* renamed from: X.0wf  reason: invalid class name and case insensitive filesystem */
public class C05690wf extends AnonymousClass02u<E, E> {
    public final /* synthetic */ AnonymousClass02j A00;

    public C05690wf(AnonymousClass02j r1) {
        this.A00 = r1;
    }

    @Override // X.AnonymousClass02u
    public final int A00() {
        return this.A00.A00;
    }

    @Override // X.AnonymousClass02u
    public final int A01(Object obj) {
        AnonymousClass02j r1 = this.A00;
        if (obj == null) {
            return AnonymousClass02j.A00(r1);
        }
        return AnonymousClass02j.A01(r1, obj, obj.hashCode());
    }

    @Override // X.AnonymousClass02u
    public final int A02(Object obj) {
        AnonymousClass02j r1 = this.A00;
        if (obj == null) {
            return AnonymousClass02j.A00(r1);
        }
        return AnonymousClass02j.A01(r1, obj, obj.hashCode());
    }

    @Override // X.AnonymousClass02u
    public final Object A03(int i, int i2) {
        return this.A00.A03[i];
    }

    @Override // X.AnonymousClass02u
    public final E A04(int i, E e) {
        throw new UnsupportedOperationException("not a map");
    }

    @Override // X.AnonymousClass02u
    public final Map<E, E> A05() {
        throw new UnsupportedOperationException("not a map");
    }

    @Override // X.AnonymousClass02u
    public final void A06() {
        this.A00.clear();
    }

    @Override // X.AnonymousClass02u
    public final void A07(int i) {
        this.A00.A04(i);
    }

    @Override // X.AnonymousClass02u
    public final void A08(E e, E e2) {
        this.A00.add(e);
    }
}
