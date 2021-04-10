package X;

/* renamed from: X.0r8  reason: invalid class name */
public class AnonymousClass0r8 implements AnonymousClass0D4<TResult, AnonymousClass0DC<TContinuationResult>> {
    public final /* synthetic */ AnonymousClass0D4 A00;
    public final /* synthetic */ AnonymousClass0DC A01;

    public AnonymousClass0r8(AnonymousClass0DC r1, AnonymousClass0D4 r2) {
        this.A01 = r1;
        this.A00 = r2;
    }

    @Override // X.AnonymousClass0D4
    public final Object then(AnonymousClass0DC r2) throws Exception {
        if (r2.A0K()) {
            return AnonymousClass0DC.A03(r2.A0F());
        }
        if (r2.A0I()) {
            return AnonymousClass0DC.A06;
        }
        return r2.A0A(this.A00);
    }
}
