package X;

import java.io.IOException;

/* renamed from: X.0Mc  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC01190Mc implements AbstractC07630v6 {
    public boolean A00;
    public final AnonymousClass0Ls A01;
    public final /* synthetic */ AnonymousClass0MU A02;

    public AbstractC01190Mc(AnonymousClass0MU r3) {
        this.A02 = r3;
        this.A01 = new AnonymousClass0Ls(r3.A04.timeout());
    }

    public final void A00(boolean z) throws IOException {
        AnonymousClass0MU r3 = this.A02;
        int i = r3.A00;
        if (i == 6) {
            return;
        }
        if (i == 5) {
            AnonymousClass0MU.A00(this.A01);
            r3.A00 = 6;
            C08090vs r1 = r3.A03;
            if (r1 != null) {
                r1.A05(!z, r3);
                return;
            }
            return;
        }
        throw new IllegalStateException(AnonymousClass006.A01("state: ", i));
    }

    @Override // X.AbstractC07630v6
    public final C07620v5 timeout() {
        return this.A01;
    }
}
