package X;

/* renamed from: X.0hM  reason: invalid class name */
public final class AnonymousClass0hM {
    public int A00;
    public final int[] A01 = new int[10];

    public final int A00() {
        if ((this.A00 & 128) != 0) {
            return this.A01[7];
        }
        return 65535;
    }

    public final void A01(int i, int i2) {
        if (i >= 0) {
            int[] iArr = this.A01;
            if (i < iArr.length) {
                this.A00 = (1 << i) | this.A00;
                iArr[i] = i2;
            }
        }
    }
}
