package X;

/* renamed from: X.0rA  reason: invalid class name and case insensitive filesystem */
public class C07120rA implements AnonymousClass0D4<TResult, AnonymousClass0DC<TContinuationResult>> {
    public final /* synthetic */ AnonymousClass0Cz A00;
    public final /* synthetic */ AnonymousClass0D4 A01;
    public final /* synthetic */ AnonymousClass0DC A02;

    public C07120rA(AnonymousClass0DC r1, AnonymousClass0Cz r2, AnonymousClass0D4 r3) {
        this.A02 = r1;
        this.A00 = r2;
        this.A01 = r3;
    }

    @Override // X.AnonymousClass0D4
    public final Object then(AnonymousClass0DC r2) throws Exception {
        AnonymousClass0Cz r0 = this.A00;
        if (r0 == null || !r0.A00.A03()) {
            if (r2.A0K()) {
                return AnonymousClass0DC.A03(r2.A0F());
            }
            if (!r2.A0I()) {
                return r2.A09(this.A01);
            }
        }
        return AnonymousClass0DC.A06;
    }
}
