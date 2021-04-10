package X;

/* renamed from: X.Pd  reason: case insensitive filesystem */
public final class C0281Pd {
    public Object A00;
    public final N3 A01;
    public final AbstractC1020qp A02;
    public final O4 A03;
    public final AnonymousClass2H A04;

    public C0281Pd(AnonymousClass2H r4, O4 o4) {
        this.A04 = r4;
        this.A03 = o4;
        N3 n3 = r4._serializationInclusion;
        n3 = n3 == null ? N3.ALWAYS : n3;
        C1046rL rLVar = (C1046rL) o4;
        AbstractC1020qp qpVar = rLVar.A07;
        this.A01 = qpVar != null ? qpVar.A03(rLVar.A09, n3) : n3;
        this.A02 = this.A04.A01();
    }
}
