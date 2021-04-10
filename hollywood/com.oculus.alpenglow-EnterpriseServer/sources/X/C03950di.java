package X;

import java.util.Map;

/* renamed from: X.0di  reason: invalid class name and case insensitive filesystem */
public class C03950di extends AnonymousClass06C<E, E> {
    public final /* synthetic */ AnonymousClass061 A00;

    public C03950di(AnonymousClass061 r1) {
        this.A00 = r1;
    }

    @Override // X.AnonymousClass06C
    public final int A00() {
        return this.A00.A00;
    }

    @Override // X.AnonymousClass06C
    public final int A01(Object obj) {
        AnonymousClass061 r1 = this.A00;
        if (obj == null) {
            return AnonymousClass061.A00(r1);
        }
        return AnonymousClass061.A01(r1, obj, obj.hashCode());
    }

    @Override // X.AnonymousClass06C
    public final int A02(Object obj) {
        AnonymousClass061 r1 = this.A00;
        if (obj == null) {
            return AnonymousClass061.A00(r1);
        }
        return AnonymousClass061.A01(r1, obj, obj.hashCode());
    }

    @Override // X.AnonymousClass06C
    public final Object A03(int i, int i2) {
        return this.A00.A03[i];
    }

    @Override // X.AnonymousClass06C
    public final E A04(int i, E e) {
        throw new UnsupportedOperationException("not a map");
    }

    @Override // X.AnonymousClass06C
    public final Map<E, E> A05() {
        throw new UnsupportedOperationException("not a map");
    }

    @Override // X.AnonymousClass06C
    public final void A06() {
        this.A00.clear();
    }

    @Override // X.AnonymousClass06C
    public final void A07(int i) {
        this.A00.A04(i);
    }

    @Override // X.AnonymousClass06C
    public final void A08(E e, E e2) {
        this.A00.add(e);
    }
}
