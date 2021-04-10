package X;

/* renamed from: X.0AX  reason: invalid class name */
public abstract class AnonymousClass0AX {
    public int A00 = -1;
    public boolean A01;
    public final AbstractC00450Aa<? super T> A02;
    public final /* synthetic */ AnonymousClass0AY A03;

    public void A00() {
    }

    public abstract boolean A02();

    public boolean A03(AnonymousClass0AS r2) {
        return false;
    }

    public AnonymousClass0AX(AnonymousClass0AY r2, AbstractC00450Aa<? super T> r3) {
        this.A03 = r2;
        this.A02 = r3;
    }

    public final void A01(boolean z) {
        if (z != this.A01) {
            this.A01 = z;
            AnonymousClass0AY r2 = this.A03;
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
            r2.A03(this);
        }
    }
}
