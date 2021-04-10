package X;

import java.io.IOException;

/* renamed from: X.0PU  reason: invalid class name */
public abstract class AnonymousClass0PU implements AbstractC04550h0 {
    public boolean A00;
    public final AnonymousClass0OW A01;
    public final /* synthetic */ AnonymousClass0PK A02;

    public AnonymousClass0PU(AnonymousClass0PK r3) {
        this.A02 = r3;
        this.A01 = new AnonymousClass0OW(r3.A04.timeout());
    }

    public final void A00(boolean z) throws IOException {
        AnonymousClass0PK r3 = this.A02;
        int i = r3.A00;
        if (i == 6) {
            return;
        }
        if (i == 5) {
            AnonymousClass0PK.A00(this.A01);
            r3.A00 = 6;
            C05360jA r1 = r3.A03;
            if (r1 != null) {
                r1.A05(!z, r3);
                return;
            }
            return;
        }
        throw new IllegalStateException(AnonymousClass006.A01("state: ", i));
    }

    @Override // X.AbstractC04550h0
    public final C04540gz timeout() {
        return this.A01;
    }
}
