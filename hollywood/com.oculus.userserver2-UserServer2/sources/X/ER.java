package X;

import java.io.IOException;

public abstract class ER implements WF {
    public boolean A00;
    public final Dl A01;
    public final /* synthetic */ EN A02;

    public ER(EN en) {
        this.A02 = en;
        this.A01 = new Dl(en.A04.timeout());
    }

    public final void A00(boolean z) throws IOException {
        EN en = this.A02;
        int i = en.A00;
        if (i == 6) {
            return;
        }
        if (i == 5) {
            EN.A00(this.A01);
            en.A00 = 6;
            X0 x0 = en.A03;
            if (x0 != null) {
                x0.A05(!z, en);
                return;
            }
            return;
        }
        throw new IllegalStateException(AnonymousClass06.A01("state: ", i));
    }

    @Override // X.WF
    public final WE timeout() {
        return this.A01;
    }
}
