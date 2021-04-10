package X;

import com.facebook.proxygen.HTTPTransportCallback;

/* renamed from: X.cY  reason: case insensitive filesystem */
public final class C0590cY {
    public int A00;
    public final int[] A01 = new int[10];

    public final int A00() {
        if ((this.A00 & HTTPTransportCallback.BODY_BYTES_RECEIVED) != 0) {
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
