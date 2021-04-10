package X;

import java.util.Arrays;

public final class Dg extends WM {
    public final transient int[] A00;
    public final transient byte[][] A01;

    public Dg(AnonymousClass8k r9, int i) {
        super(null);
        WD.A00(r9.A00, 0, (long) i);
        WI wi = r9.A01;
        int i2 = 0;
        WI wi2 = wi;
        int i3 = 0;
        int i4 = 0;
        while (i3 < i) {
            if (wi2.A00 != wi2.A01) {
                i3 += wi2.A00 - wi2.A01;
                i4++;
                wi2 = wi2.A02;
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
            bArr[i5] = wi.A06;
            i2 += wi.A00 - wi.A01;
            if (i2 > i) {
                i2 = i;
            }
            iArr[i5] = i2;
            iArr[i4 + i5] = wi.A01;
            wi.A05 = true;
            i5++;
            wi = wi.A02;
        }
    }

    @Override // X.WM
    public final boolean equals(Object obj) {
        WM wm;
        int A07;
        return obj == this || ((obj instanceof WM) && (wm = (WM) obj).A07() == (A07 = A07()) && A0G(0, wm, 0, A07));
    }

    public static int A00(Dg dg, int i) {
        int binarySearch = Arrays.binarySearch(dg.A00, 0, dg.A01.length, i + 1);
        if (binarySearch < 0) {
            return binarySearch ^ -1;
        }
        return binarySearch;
    }

    @Override // X.WM
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
        return new WM(A0I());
    }

    @Override // X.WM
    public final String toString() {
        return new WM(A0I()).toString();
    }
}
