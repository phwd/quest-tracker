package X;

import java.util.Arrays;

/* renamed from: X.0Ln  reason: invalid class name */
public final class AnonymousClass0Ln extends C07700vD {
    public final transient int[] A00;
    public final transient byte[][] A01;

    public AnonymousClass0Ln(AnonymousClass0B3 r9, int i) {
        super(null);
        C07610v4.A00(r9.A00, 0, (long) i);
        C07660v9 r7 = r9.A01;
        int i2 = 0;
        C07660v9 r3 = r7;
        int i3 = 0;
        int i4 = 0;
        while (i3 < i) {
            if (r3.A01 != r3.A02) {
                i3 += r3.A01 - r3.A02;
                i4++;
                r3 = r3.A00;
            } else {
                throw new AssertionError("s.limit == s.pos");
            }
        }
        byte[][] bArr = new byte[i4][];
        this.A01 = bArr;
        int[] iArr = new int[(i4 << 1)];
        this.A00 = iArr;
        int i5 = 0;
        while (i2 < i) {
            bArr[i5] = r7.A06;
            i2 += r7.A01 - r7.A02;
            if (i2 > i) {
                i2 = i;
            }
            iArr[i5] = i2;
            iArr[i4 + i5] = r7.A02;
            r7.A05 = true;
            i5++;
            r7 = r7.A00;
        }
    }

    @Override // X.C07700vD
    public final boolean A0G(int i, C07700vD r9, int i2, int i3) {
        int i4;
        if (i >= 0 && i <= A07() - i3) {
            int A002 = A00(i);
            while (i3 > 0) {
                if (A002 == 0) {
                    i4 = 0;
                } else {
                    i4 = this.A00[A002 - 1];
                }
                int[] iArr = this.A00;
                int min = Math.min(i3, ((iArr[A002] - i4) + i4) - i);
                byte[][] bArr = this.A01;
                if (r9.A0H(i2, bArr[A002], (i - i4) + iArr[bArr.length + A002], min)) {
                    i += min;
                    i2 += min;
                    i3 -= min;
                    A002++;
                }
            }
            return true;
        }
        return false;
    }

    @Override // X.C07700vD
    public final boolean A0H(int i, byte[] bArr, int i2, int i3) {
        int i4;
        if (i >= 0 && i <= A07() - i3 && i2 >= 0 && i2 <= bArr.length - i3) {
            int A002 = A00(i);
            while (i3 > 0) {
                if (A002 == 0) {
                    i4 = 0;
                } else {
                    i4 = this.A00[A002 - 1];
                }
                int[] iArr = this.A00;
                int min = Math.min(i3, ((iArr[A002] - i4) + i4) - i);
                byte[][] bArr2 = this.A01;
                int i5 = (i - i4) + iArr[bArr2.length + A002];
                byte[] bArr3 = bArr2[A002];
                for (int i6 = 0; i6 < min; i6++) {
                    if (bArr3[i6 + i5] == bArr[i6 + i2]) {
                    }
                }
                i += min;
                i2 += min;
                i3 -= min;
                A002++;
            }
            return true;
        }
        return false;
    }

    @Override // X.C07700vD
    public final boolean equals(Object obj) {
        C07700vD r5;
        int A07;
        return obj == this || ((obj instanceof C07700vD) && (r5 = (C07700vD) obj).A07() == (A07 = A07()) && A0G(0, r5, 0, A07));
    }

    private int A00(int i) {
        int binarySearch = Arrays.binarySearch(this.A00, 0, this.A01.length, i + 1);
        if (binarySearch < 0) {
            return binarySearch ^ -1;
        }
        return binarySearch;
    }

    @Override // X.C07700vD
    public final byte A06(int i) {
        int i2;
        int[] iArr = this.A00;
        byte[][] bArr = this.A01;
        int length = bArr.length;
        C07610v4.A00((long) iArr[length - 1], (long) i, 1);
        int A002 = A00(i);
        if (A002 == 0) {
            i2 = 0;
        } else {
            i2 = iArr[A002 - 1];
        }
        return bArr[A002][(i - i2) + iArr[length + A002]];
    }

    @Override // X.C07700vD
    public final int A07() {
        return this.A00[this.A01.length - 1];
    }

    @Override // X.C07700vD
    public final void A0F(AnonymousClass0B3 r10) {
        byte[][] bArr = this.A01;
        int length = bArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int[] iArr = this.A00;
            int i3 = iArr[length + i];
            int i4 = iArr[i];
            C07660v9 r1 = new C07660v9(bArr[i], i3, (i3 + i4) - i2);
            C07660v9 r0 = r10.A01;
            if (r0 == null) {
                r1.A03 = r1;
                r1.A00 = r1;
                r10.A01 = r1;
            } else {
                r0.A03.A02(r1);
            }
            i++;
            i2 = i4;
        }
        r10.A00 += (long) i2;
    }

    @Override // X.C07700vD
    public final byte[] A0I() {
        int[] iArr = this.A00;
        byte[][] bArr = this.A01;
        int length = bArr.length;
        byte[] bArr2 = new byte[iArr[length - 1]];
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int i3 = iArr[length + i];
            int i4 = iArr[i];
            System.arraycopy(bArr[i], i3, bArr2, i2, i4 - i2);
            i++;
            i2 = i4;
        }
        return bArr2;
    }

    @Override // X.C07700vD
    public final int hashCode() {
        int i = super.A00;
        if (i != 0) {
            return i;
        }
        byte[][] bArr = this.A01;
        int length = bArr.length;
        int i2 = 0;
        int i3 = 1;
        int i4 = 0;
        while (i2 < length) {
            byte[] bArr2 = bArr[i2];
            int[] iArr = this.A00;
            int i5 = iArr[length + i2];
            int i6 = iArr[i2];
            int i7 = (i6 - i4) + i5;
            while (i5 < i7) {
                i3 = (i3 * 31) + bArr2[i5];
                i5++;
            }
            i2++;
            i4 = i6;
        }
        super.A00 = i3;
        return i3;
    }

    private Object writeReplace() {
        return new C07700vD(A0I());
    }

    @Override // X.C07700vD
    public final String A08() {
        return new C07700vD(A0I()).A08();
    }

    @Override // X.C07700vD
    public final String A09() {
        return new C07700vD(A0I()).A09();
    }

    @Override // X.C07700vD
    public final String A0A() {
        return new C07700vD(A0I()).A0A();
    }

    @Override // X.C07700vD
    public final C07700vD A0B() {
        return new C07700vD(A0I()).A0B();
    }

    @Override // X.C07700vD
    public final C07700vD A0C() {
        return new C07700vD(A0I()).A0C();
    }

    @Override // X.C07700vD
    public final C07700vD A0D() {
        return new C07700vD(A0I()).A0D();
    }

    @Override // X.C07700vD
    public final C07700vD A0E(int i, int i2) {
        return new C07700vD(A0I()).A0E(i, i2);
    }

    @Override // X.C07700vD
    public final String toString() {
        return new C07700vD(A0I()).toString();
    }
}
