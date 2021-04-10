package X;

/* renamed from: X.0r7  reason: invalid class name */
public class AnonymousClass0r7 implements AnonymousClass0D4<TContinuationResult, Void> {
    public final /* synthetic */ AnonymousClass0D7 A00;

    public AnonymousClass0r7(AnonymousClass0D7 r1) {
        this.A00 = r1;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass0D4
    public final Void then(AnonymousClass0DC r4) throws Exception {
        AnonymousClass0D7 r1 = this.A00;
        if (r4.A0I()) {
            r1.A02.A00();
            return null;
        } else if (r4.A0K()) {
            r1.A02.A01(r4.A0F());
            return null;
        } else {
            r1.A02.A02(r4.A0G());
            return null;
        }
    }
}
