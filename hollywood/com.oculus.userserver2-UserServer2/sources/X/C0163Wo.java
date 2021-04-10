package X;

import com.squareup.okhttp.internal.framed.Hpack;
import java.io.IOException;
import java.util.Arrays;

/* renamed from: X.Wo  reason: case insensitive filesystem */
public final class C0163Wo {
    public int A00 = 0;
    public int A01 = 0;
    public int A02 = 4096;
    public int A03 = 7;
    public int A04 = Integer.MAX_VALUE;
    public boolean A05;
    public C0165Wq[] A06 = new C0165Wq[8];
    public final AnonymousClass8k A07;
    public final boolean A08 = true;

    public static void A00(C0163Wo wo, int i) {
        int i2;
        int i3 = 0;
        if (i > 0) {
            C0165Wq[] wqArr = wo.A06;
            int length = wqArr.length;
            while (true) {
                length--;
                i2 = wo.A03;
                if (length < i2 || i <= 0) {
                    int i4 = i2 + 1;
                    System.arraycopy(wqArr, i4, wqArr, i4 + i3, wo.A01);
                    C0165Wq[] wqArr2 = wo.A06;
                    int i5 = wo.A03 + 1;
                    Arrays.fill(wqArr2, i5, i5 + i3, (Object) null);
                    wo.A03 += i3;
                } else {
                    int i6 = wqArr[length].A00;
                    i -= i6;
                    wo.A00 -= i6;
                    wo.A01--;
                    i3++;
                }
            }
            int i42 = i2 + 1;
            System.arraycopy(wqArr, i42, wqArr, i42 + i3, wo.A01);
            C0165Wq[] wqArr22 = wo.A06;
            int i52 = wo.A03 + 1;
            Arrays.fill(wqArr22, i52, i52 + i3, (Object) null);
            wo.A03 += i3;
        }
    }

    public C0163Wo(AnonymousClass8k r4) {
        this.A07 = r4;
    }

    public static final void A01(C0163Wo wo, int i, int i2, int i3) {
        if (i < i2) {
            wo.A07.A09(i | i3);
            return;
        }
        AnonymousClass8k r2 = wo.A07;
        r2.A09(i3 | i2);
        int i4 = i - i2;
        while (i4 >= 128) {
            r2.A09(128 | (i4 & Hpack.PREFIX_7_BITS));
            i4 >>>= 7;
        }
        r2.A09(i4);
    }

    public static final void A02(C0163Wo wo, WM wm) throws IOException {
        int A072;
        if (wo.A08) {
            long j = 0;
            int i = 0;
            while (true) {
                A072 = wm.A07();
                if (i >= A072) {
                    break;
                }
                j += (long) C0153We.A02[wm.A06(i) & 255];
                i++;
            }
            if (((int) ((j + 7) >> 3)) < A072) {
                AnonymousClass8k r4 = new AnonymousClass8k();
                int i2 = 0;
                long j2 = 0;
                byte b = 0;
                while (i2 < wm.A07()) {
                    int A062 = wm.A06(i2) & 255;
                    int i3 = C0153We.A03[A062];
                    byte b2 = C0153We.A02[A062];
                    j2 = (j2 << b2) | ((long) i3);
                    int i4 = b + b2;
                    while (i4 >= 8) {
                        i4 = (i4 == 1 ? 1 : 0) - 8;
                        r4.A3y((int) (j2 >> i4));
                    }
                    i2++;
                    b = i4;
                }
                if (b > 0) {
                    r4.A3y((int) (((long) (255 >>> b)) | (j2 << (8 - b))));
                }
                WM wm2 = new WM(r4.A31());
                A01(wo, wm2.A07(), Hpack.PREFIX_7_BITS, 128);
                wm2.A0F(wo.A07);
                return;
            }
        }
        A01(wo, wm.A07(), Hpack.PREFIX_7_BITS, 0);
        wm.A0F(wo.A07);
    }
}
