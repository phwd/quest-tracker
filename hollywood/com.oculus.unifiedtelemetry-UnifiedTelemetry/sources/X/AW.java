package X;

public abstract class AW {
    public int A00;
    public boolean A01;
    public final /* synthetic */ AX A02;

    public void A00() {
    }

    public abstract boolean A02();

    public final void A01(boolean z) {
        if (z != this.A01) {
            this.A01 = z;
            AX ax = this.A02;
            int i = ax.A00;
            int i2 = 1;
            if (!z) {
                i2 = -1;
            }
            int i3 = i + i2;
            ax.A00 = i3;
            if (i3 == 0) {
                if (!z) {
                    return;
                }
            } else if (!z) {
                return;
            }
            ax.A01(this);
        }
    }
}
