package X;

import com.facebook.FacebookSdk;
import com.squareup.okhttp.internal.framed.Hpack;
import java.io.IOException;
import java.util.Arrays;

/* renamed from: X.0vg  reason: invalid class name and case insensitive filesystem */
public final class C07970vg {
    public int A00 = 0;
    public int A01 = 0;
    public int A02 = 4096;
    public int A03 = 7;
    public int A04 = Integer.MAX_VALUE;
    public boolean A05;
    public C07990vi[] A06 = new C07990vi[8];
    public final AnonymousClass0B3 A07;
    public final boolean A08 = true;

    public static void A00(C07970vg r5, int i) {
        int i2;
        int i3 = 0;
        if (i > 0) {
            C07990vi[] r3 = r5.A06;
            int length = r3.length;
            while (true) {
                length--;
                i2 = r5.A03;
                if (length < i2 || i <= 0) {
                    int i4 = i2 + 1;
                    System.arraycopy(r3, i4, r3, i4 + i3, r5.A01);
                    C07990vi[] r32 = r5.A06;
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
            C07990vi[] r322 = r5.A06;
            int i52 = r5.A03 + 1;
            Arrays.fill(r322, i52, i52 + i3, (Object) null);
            r5.A03 += i3;
        }
    }

    public C07970vg(AnonymousClass0B3 r4) {
        this.A07 = r4;
    }

    public static final void A01(C07970vg r2, int i, int i2, int i3) {
        if (i < i2) {
            r2.A07.A09(i | i3);
            return;
        }
        AnonymousClass0B3 r22 = r2.A07;
        r22.A09(i3 | i2);
        int i4 = i - i2;
        while (i4 >= 128) {
            r22.A09(128 | (i4 & Hpack.PREFIX_7_BITS));
            i4 >>>= 7;
        }
        r22.A09(i4);
    }

    public static final void A02(C07970vg r10, C07700vD r11) throws IOException {
        int A072;
        if (r10.A08) {
            long j = 0;
            int i = 0;
            while (true) {
                A072 = r11.A07();
                if (i >= A072) {
                    break;
                }
                j += (long) C07870vW.A02[r11.A06(i) & 255];
                i++;
            }
            if (((int) ((j + 7) >> 3)) < A072) {
                AnonymousClass0B3 r4 = new AnonymousClass0B3();
                int i2 = 0;
                long j2 = 0;
                byte b = 0;
                while (i2 < r11.A07()) {
                    int A062 = r11.A06(i2) & 255;
                    int i3 = C07870vW.A03[A062];
                    byte b2 = C07870vW.A02[A062];
                    j2 = (j2 << b2) | ((long) i3);
                    int i4 = b + b2;
                    while (i4 >= 8) {
                        i4 = (i4 == 1 ? 1 : 0) - 8;
                        r4.AAC((int) (j2 >> i4));
                    }
                    i2++;
                    b = i4;
                }
                if (b > 0) {
                    r4.AAC((int) (((long) (255 >>> b)) | (j2 << (8 - b))));
                }
                C07700vD r2 = new C07700vD(r4.A7r());
                A01(r10, r2.A07(), Hpack.PREFIX_7_BITS, FacebookSdk.DEFAULT_MAXIMUM_POOL_SIZE);
                r2.A0F(r10.A07);
                return;
            }
        }
        A01(r10, r11.A07(), Hpack.PREFIX_7_BITS, 0);
        r11.A0F(r10.A07);
    }
}
