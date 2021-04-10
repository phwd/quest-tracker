package X;

/* renamed from: X.Bx  reason: case insensitive filesystem */
public abstract class AbstractC0045Bx {
    public int A00;
    public boolean A01;
    public final /* synthetic */ AbstractC0046By A02;

    public final void A00(boolean z) {
        if (z != this.A01) {
            this.A01 = z;
            AbstractC0046By by = this.A02;
            int i = by.A00;
            int i2 = 1;
            if (!z) {
                i2 = -1;
            }
            int i3 = i + i2;
            by.A00 = i3;
            if (i3 == 0) {
                if (!z) {
                    return;
                }
            } else if (!z) {
                return;
            }
            by.A01(this);
        }
    }
}
