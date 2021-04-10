package X;

import javax.annotation.Nullable;

/* renamed from: X.0h3  reason: invalid class name and case insensitive filesystem */
public final class C04570h3 {
    public C04570h3 A00;
    public int A01;
    public int A02;
    public C04570h3 A03;
    public boolean A04;
    public boolean A05;
    public final byte[] A06;

    public final C04570h3 A01() {
        this.A05 = true;
        return new C04570h3(this.A06, this.A02, this.A01);
    }

    @Nullable
    public final C04570h3 A00() {
        C04570h3 r3 = this.A00;
        if (r3 == this) {
            r3 = null;
        }
        C04570h3 r1 = this.A03;
        r1.A00 = r3;
        this.A00.A03 = r1;
        this.A00 = null;
        this.A03 = null;
        return r3;
    }

    public final void A02(C04570h3 r2) {
        r2.A03 = this;
        r2.A00 = this.A00;
        this.A00.A03 = r2;
        this.A00 = r2;
    }

    public final void A03(C04570h3 r6, int i) {
        if (r6.A04) {
            int i2 = r6.A01;
            int i3 = i2 + i;
            if (i3 > 8192) {
                if (!r6.A05) {
                    int i4 = r6.A02;
                    if (i3 - i4 <= 8192) {
                        byte[] bArr = r6.A06;
                        System.arraycopy(bArr, i4, bArr, 0, i2 - i4);
                        i2 = r6.A01 - r6.A02;
                        r6.A01 = i2;
                        r6.A02 = 0;
                    }
                }
            }
            System.arraycopy(this.A06, this.A02, r6.A06, i2, i);
            r6.A01 += i;
            this.A02 += i;
            return;
        }
        throw new IllegalArgumentException();
    }

    public C04570h3() {
        this.A06 = new byte[8192];
        this.A04 = true;
        this.A05 = false;
    }

    public C04570h3(byte[] bArr, int i, int i2) {
        this.A06 = bArr;
        this.A02 = i;
        this.A01 = i2;
        this.A05 = true;
        this.A04 = false;
    }
}
