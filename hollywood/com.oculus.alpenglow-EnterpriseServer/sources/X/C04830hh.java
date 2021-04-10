package X;

import com.squareup.okhttp.internal.framed.Hpack;
import java.io.IOException;
import java.util.Arrays;

/* renamed from: X.0hh  reason: invalid class name and case insensitive filesystem */
public final class C04830hh {
    public int A00 = 0;
    public int A01 = 0;
    public int A02 = 4096;
    public int A03 = 7;
    public int A04 = Integer.MAX_VALUE;
    public boolean A05;
    public C04870hr[] A06 = new C04870hr[8];
    public final AnonymousClass0HR A07;
    public final boolean A08 = true;

    public static void A00(C04830hh r5, int i) {
        int i2;
        int i3 = 0;
        if (i > 0) {
            C04870hr[] r3 = r5.A06;
            int length = r3.length;
            while (true) {
                length--;
                i2 = r5.A03;
                if (length < i2 || i <= 0) {
                    int i4 = i2 + 1;
                    System.arraycopy(r3, i4, r3, i4 + i3, r5.A01);
                    C04870hr[] r32 = r5.A06;
                    int i5 = r5.A03 + 1;
                    Arrays.fill(r32, i5, i5 + i3, (Object) null);
                    r5.A03 += i3;
                } else {
                    int i6 = r3[length].A00;
                    i -= i6;
                    r5.A00 -= i6;
                    r5.A01--;
                    i3++;
                }
            }
            int i42 = i2 + 1;
            System.arraycopy(r3, i42, r3, i42 + i3, r5.A01);
            C04870hr[] r322 = r5.A06;
            int i52 = r5.A03 + 1;
            Arrays.fill(r322, i52, i52 + i3, (Object) null);
            r5.A03 += i3;
        }
    }

    public C04830hh(AnonymousClass0HR r4) {
        this.A07 = r4;
    }

    public static final void A01(C04830hh r2, int i, int i2, int i3) {
        if (i < i2) {
            r2.A07.A09(i | i3);
            return;
        }
        AnonymousClass0HR r22 = r2.A07;
        r22.A09(i3 | i2);
        int i4 = i - i2;
        while (i4 >= 128) {
            r22.A09(128 | (i4 & Hpack.PREFIX_7_BITS));
            i4 >>>= 7;
        }
        r22.A09(i4);
    }

    public static final void A02(C04830hh r10, C04610h7 r11) throws IOException {
        int A072;
        if (r10.A08) {
            long j = 0;
            int i = 0;
            while (true) {
                A072 = r11.A07();
                if (i >= A072) {
                    break;
                }
                j += (long) C04730hP.A02[r11.A06(i) & 255];
                i++;
            }
            if (((int) ((j + 7) >> 3)) < A072) {
                AnonymousClass0HR r4 = new AnonymousClass0HR();
                int i2 = 0;
                long j2 = 0;
                byte b = 0;
                while (i2 < r11.A07()) {
                    int A062 = r11.A06(i2) & 255;
                    int i3 = C04730hP.A03[A062];
                    byte b2 = C04730hP.A02[A062];
                    j2 = (j2 << b2) | ((long) i3);
                    int i4 = b + b2;
                    while (i4 >= 8) {
                        i4 = (i4 == 1 ? 1 : 0) - 8;
                        r4.A91((int) (j2 >> i4));
                    }
                    i2++;
                    b = i4;
                }
                if (b > 0) {
                    r4.A91((int) (((long) (255 >>> b)) | (j2 << (8 - b))));
                }
                C04610h7 r2 = new C04610h7(r4.A79());
                A01(r10, r2.A07(), Hpack.PREFIX_7_BITS, 128);
                r2.A0F(r10.A07);
                return;
            }
        }
        A01(r10, r11.A07(), Hpack.PREFIX_7_BITS, 0);
        r11.A0F(r10.A07);
    }
}
