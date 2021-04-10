package X;

import java.util.Arrays;

/* renamed from: X.sv  reason: case insensitive filesystem */
public final class C1108sv extends C0603cm {
    public final transient int[] A00;
    public final transient byte[][] A01;

    public C1108sv(AnonymousClass33 r9, int i) {
        super(null);
        C0611cu.A00(r9.A00, 0, (long) i);
        C0606cp cpVar = r9.A01;
        int i2 = 0;
        C0606cp cpVar2 = cpVar;
        int i3 = 0;
        int i4 = 0;
        while (i3 < i) {
            if (cpVar2.A00 != cpVar2.A01) {
                i3 += cpVar2.A00 - cpVar2.A01;
                i4++;
                cpVar2 = cpVar2.A02;
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
            bArr[i5] = cpVar.A06;
            i2 += cpVar.A00 - cpVar.A01;
            if (i2 > i) {
                i2 = i;
            }
            iArr[i5] = i2;
            iArr[i4 + i5] = cpVar.A01;
            cpVar.A05 = true;
            i5++;
            cpVar = cpVar.A02;
        }
    }

    @Override // X.C0603cm
    public final boolean equals(Object obj) {
        C0603cm cmVar;
        int A05;
        return obj == this || ((obj instanceof C0603cm) && (cmVar = (C0603cm) obj).A05() == (A05 = A05()) && A0E(0, cmVar, 0, A05));
    }

    public static int A00(C1108sv svVar, int i) {
        int binarySearch = Arrays.binarySearch(svVar.A00, 0, svVar.A01.length, i + 1);
        if (binarySearch < 0) {
            return binarySearch ^ -1;
        }
        return binarySearch;
    }

    @Override // X.C0603cm
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
        return new C0603cm(A0G());
    }

    @Override // X.C0603cm
    public final String toString() {
        return new C0603cm(A0G()).toString();
    }
}
