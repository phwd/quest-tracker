package X;

/* renamed from: X.0mh  reason: invalid class name and case insensitive filesystem */
public class C03460mh implements AbstractC00840Jw<V> {
    public final /* synthetic */ AnonymousClass0P9 A00;
    public final /* synthetic */ AnonymousClass0I1 A01;

    public C03460mh(AnonymousClass0I1 r1, AnonymousClass0P9 r2) {
        this.A01 = r1;
        this.A00 = r2;
    }

    @Override // X.AbstractC00840Jw
    public final void A8y(V v) {
        AbstractC00820Ju A012;
        AnonymousClass0I1 r4 = this.A01;
        AnonymousClass0P9 r3 = this.A00;
        if (r3 != null) {
            synchronized (r4) {
                int i = r3.A00;
                boolean z = false;
                if (i > 0) {
                    z = true;
                }
                C00740Ii.A03(z);
                int i2 = i - 1;
                r3.A00 = i2;
                if (!r3.A01 && i2 == 0) {
                    r4.A04.A03(r3.A03, r3);
                }
                A012 = AnonymousClass0I1.A01(r4, r3);
            }
            AbstractC00820Ju.A03(A012);
            AnonymousClass0I1.A04(r4);
            AnonymousClass0I1.A03(r4);
            return;
        }
        throw null;
    }
}
