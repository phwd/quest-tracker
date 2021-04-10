package X;

/* renamed from: X.0AW  reason: invalid class name */
public abstract class AnonymousClass0AW {
    public int A00;
    public boolean A01;
    public final /* synthetic */ AnonymousClass0AX A02;

    public void A00() {
    }

    public abstract boolean A02();

    public final void A01(boolean z) {
        if (z != this.A01) {
            this.A01 = z;
            AnonymousClass0AX r2 = this.A02;
            int i = r2.A00;
            int i2 = 1;
            if (!z) {
                i2 = -1;
            }
            int i3 = i + i2;
            r2.A00 = i3;
            if (i3 == 0) {
                if (!z) {
                    return;
                }
            } else if (!z) {
                return;
            }
            r2.A01(this);
        }
    }
}
