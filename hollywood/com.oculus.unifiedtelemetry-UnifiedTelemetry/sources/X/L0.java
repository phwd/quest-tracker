package X;

import java.io.IOException;

public abstract class L0 implements AbstractC0312cb {
    public boolean A00;
    public final K4 A01;
    public final /* synthetic */ C0127Kx A02;

    public L0(C0127Kx kx) {
        this.A02 = kx;
        this.A01 = new K4(kx.A04.timeout());
    }

    public final void A00(boolean z) throws IOException {
        C0127Kx kx = this.A02;
        int i = kx.A00;
        if (i == 6) {
            return;
        }
        if (i == 5) {
            C0127Kx.A00(this.A01);
            kx.A00 = 6;
            C0350dM dMVar = kx.A03;
            if (dMVar != null) {
                dMVar.A05(!z, kx);
                return;
            }
            return;
        }
        throw new IllegalStateException(AnonymousClass06.A01("state: ", i));
    }

    @Override // X.AbstractC0312cb
    public final ca timeout() {
        return this.A01;
    }
}
