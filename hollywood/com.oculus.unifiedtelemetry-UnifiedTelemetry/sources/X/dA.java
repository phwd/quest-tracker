package X;

import com.squareup.okhttp.internal.framed.Hpack;
import java.io.IOException;
import java.util.Arrays;

public final class dA {
    public int A00 = 0;
    public int A01 = 0;
    public int A02 = 4096;
    public int A03 = 7;
    public int A04 = Integer.MAX_VALUE;
    public boolean A05;
    public C0343dC[] A06 = new C0343dC[8];
    public final AnonymousClass98 A07;
    public final boolean A08 = true;

    public static void A00(dA dAVar, int i) {
        int i2;
        int i3 = 0;
        if (i > 0) {
            C0343dC[] dCVarArr = dAVar.A06;
            int length = dCVarArr.length;
            while (true) {
                length--;
                i2 = dAVar.A03;
                if (length < i2 || i <= 0) {
                    int i4 = i2 + 1;
                    System.arraycopy(dCVarArr, i4, dCVarArr, i4 + i3, dAVar.A01);
                    C0343dC[] dCVarArr2 = dAVar.A06;
                    int i5 = dAVar.A03 + 1;
                    Arrays.fill(dCVarArr2, i5, i5 + i3, (Object) null);
                    dAVar.A03 += i3;
                } else {
                    int i6 = dCVarArr[length].A00;
                    i -= i6;
                    dAVar.A00 -= i6;
                    dAVar.A01--;
                    i3++;
                }
            }
            int i42 = i2 + 1;
            System.arraycopy(dCVarArr, i42, dCVarArr, i42 + i3, dAVar.A01);
            C0343dC[] dCVarArr22 = dAVar.A06;
            int i52 = dAVar.A03 + 1;
            Arrays.fill(dCVarArr22, i52, i52 + i3, (Object) null);
            dAVar.A03 += i3;
        }
    }

    public dA(AnonymousClass98 r4) {
        this.A07 = r4;
    }

    public static final void A01(dA dAVar, int i, int i2, int i3) {
        if (i < i2) {
            dAVar.A07.A09(i | i3);
            return;
        }
        AnonymousClass98 r2 = dAVar.A07;
        r2.A09(i3 | i2);
        int i4 = i - i2;
        while (i4 >= 128) {
            r2.A09(128 | (i4 & Hpack.PREFIX_7_BITS));
            i4 >>>= 7;
        }
        r2.A09(i4);
    }

    public static final void A02(dA dAVar, ci ciVar) throws IOException {
        int A072;
        if (dAVar.A08) {
            long j = 0;
            int i = 0;
            while (true) {
                A072 = ciVar.A07();
                if (i >= A072) {
                    break;
                }
                j += (long) d0.A02[ciVar.A06(i) & 255];
                i++;
            }
            if (((int) ((j + 7) >> 3)) < A072) {
                AnonymousClass98 r4 = new AnonymousClass98();
                int i2 = 0;
                long j2 = 0;
                byte b = 0;
                while (i2 < ciVar.A07()) {
                    int A062 = ciVar.A06(i2) & 255;
                    int i3 = d0.A03[A062];
                    byte b2 = d0.A02[A062];
                    j2 = (j2 << b2) | ((long) i3);
                    int i4 = b + b2;
                    while (i4 >= 8) {
                        i4 = (i4 == 1 ? 1 : 0) - 8;
                        r4.A5q((int) (j2 >> i4));
                    }
                    i2++;
                    b = i4;
                }
                if (b > 0) {
                    r4.A5q((int) (((long) (255 >>> b)) | (j2 << (8 - b))));
                }
                ci ciVar2 = new ci(r4.A4S());
                A01(dAVar, ciVar2.A07(), Hpack.PREFIX_7_BITS, 128);
                ciVar2.A0F(dAVar.A07);
                return;
            }
        }
        A01(dAVar, ciVar.A07(), Hpack.PREFIX_7_BITS, 0);
        ciVar.A0F(dAVar.A07);
    }
}
