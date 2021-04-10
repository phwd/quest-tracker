package X;

/* renamed from: X.1rs  reason: invalid class name and case insensitive filesystem */
public class C10291rs implements AnonymousClass1ou<V> {
    public final /* synthetic */ C10311ru A00;
    public final /* synthetic */ C10271rq A01;

    public C10291rs(C10271rq r1, C10311ru r2) {
        this.A01 = r1;
        this.A00 = r2;
    }

    @Override // X.AnonymousClass1ou
    public final void A86(V v) {
        AnonymousClass1qa A012;
        C10271rq r4 = this.A01;
        C10311ru r3 = this.A00;
        if (r3 != null) {
            synchronized (r4) {
                int i = r3.A00;
                boolean z = false;
                if (i > 0) {
                    z = true;
                }
                AnonymousClass0KU.A03(z);
                int i2 = i - 1;
                r3.A00 = i2;
                if (!r3.A01 && i2 == 0) {
                    r4.A04.A03(r3.A03, r3);
                }
                A012 = C10271rq.A01(r4, r3);
            }
            if (A012 != null) {
                A012.close();
            }
            C10271rq.A04(r4);
            C10271rq.A03(r4);
            return;
        }
        throw null;
    }
}
