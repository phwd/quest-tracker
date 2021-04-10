package X;

import java.util.Arrays;

public final class Jx extends ci {
    public final transient int[] A00;
    public final transient byte[][] A01;

    public Jx(AnonymousClass98 r9, int i) {
        super(null);
        C0311cZ.A00(r9.A00, 0, (long) i);
        C0315ce ceVar = r9.A01;
        int i2 = 0;
        C0315ce ceVar2 = ceVar;
        int i3 = 0;
        int i4 = 0;
        while (i3 < i) {
            if (ceVar2.A00 != ceVar2.A01) {
                i3 += ceVar2.A00 - ceVar2.A01;
                i4++;
                ceVar2 = ceVar2.A02;
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
            bArr[i5] = ceVar.A06;
            i2 += ceVar.A00 - ceVar.A01;
            if (i2 > i) {
                i2 = i;
            }
            iArr[i5] = i2;
            iArr[i4 + i5] = ceVar.A01;
            ceVar.A05 = true;
            i5++;
            ceVar = ceVar.A02;
        }
    }

    @Override // X.ci
    public final boolean A0G(int i, ci ciVar, int i2, int i3) {
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
                if (ciVar.A0H(i2, bArr[A002], (i - i4) + iArr[bArr.length + A002], min)) {
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

    @Override // X.ci
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

    @Override // X.ci
    public final boolean equals(Object obj) {
        ci ciVar;
        int A07;
        return obj == this || ((obj instanceof ci) && (ciVar = (ci) obj).A07() == (A07 = A07()) && A0G(0, ciVar, 0, A07));
    }

    private int A00(int i) {
        int binarySearch = Arrays.binarySearch(this.A00, 0, this.A01.length, i + 1);
        if (binarySearch < 0) {
            return binarySearch ^ -1;
        }
        return binarySearch;
    }

    @Override // X.ci
    public final byte A06(int i) {
        int i2;
        int[] iArr = this.A00;
        byte[][] bArr = this.A01;
        int length = bArr.length;
        C0311cZ.A00((long) iArr[length - 1], (long) i, 1);
        int A002 = A00(i);
        if (A002 == 0) {
            i2 = 0;
        } else {
            i2 = iArr[A002 - 1];
        }
        return bArr[A002][(i - i2) + iArr[length + A002]];
    }

    @Override // X.ci
    public final int A07() {
        return this.A00[this.A01.length - 1];
    }

    @Override // X.ci
    public final void A0F(AnonymousClass98 r10) {
        byte[][] bArr = this.A01;
        int length = bArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int[] iArr = this.A00;
            int i3 = iArr[length + i];
            int i4 = iArr[i];
            C0315ce ceVar = new C0315ce(bArr[i], i3, (i3 + i4) - i2);
            C0315ce ceVar2 = r10.A01;
            if (ceVar2 == null) {
                ceVar.A03 = ceVar;
                ceVar.A02 = ceVar;
                r10.A01 = ceVar;
            } else {
                ceVar2.A03.A02(ceVar);
            }
            i++;
            i2 = i4;
        }
        r10.A00 += (long) i2;
    }

    @Override // X.ci
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

    @Override // X.ci
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
        return new ci(A0I());
    }

    @Override // X.ci
    public final String A08() {
        return new ci(A0I()).A08();
    }

    @Override // X.ci
    public final String A09() {
        return new ci(A0I()).A09();
    }

    @Override // X.ci
    public final String A0A() {
        return new ci(A0I()).A0A();
    }

    @Override // X.ci
    public final ci A0B() {
        return new ci(A0I()).A0B();
    }

    @Override // X.ci
    public final ci A0C() {
        return new ci(A0I()).A0C();
    }

    @Override // X.ci
    public final ci A0D() {
        return new ci(A0I()).A0D();
    }

    @Override // X.ci
    public final ci A0E(int i, int i2) {
        return new ci(A0I()).A0E(i, i2);
    }

    @Override // X.ci
    public final String toString() {
        return new ci(A0I()).toString();
    }
}
