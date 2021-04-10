package X;

import com.squareup.okhttp.internal.framed.Hpack;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* renamed from: X.0hp  reason: invalid class name and case insensitive filesystem */
public final class C04850hp {
    public int A00 = 0;
    public int A01 = 0;
    public int A02 = 4096;
    public int A03 = 7;
    public C04870hr[] A04 = new C04870hr[8];
    public final int A05 = 4096;
    public final List<C04870hr> A06 = new ArrayList();
    public final AnonymousClass0Od A07;

    public static final int A00(C04850hp r3, int i, int i2) throws IOException {
        int i3 = i & i2;
        if (i3 < i2) {
            return i3;
        }
        int i4 = 0;
        while (true) {
            int readByte = r3.A07.readByte() & 255;
            if ((readByte & 128) == 0) {
                return i2 + (readByte << i4);
            }
            i2 += (readByte & Hpack.PREFIX_7_BITS) << i4;
            i4 += 7;
        }
    }

    public static void A02(C04850hp r5, int i) {
        int i2;
        int i3 = 0;
        if (i > 0) {
            C04870hr[] r3 = r5.A04;
            int length = r3.length;
            while (true) {
                length--;
                i2 = r5.A03;
                if (length < i2 || i <= 0) {
                    int i4 = i2 + 1;
                    System.arraycopy(r3, i4, r3, i4 + i3, r5.A01);
                    r5.A03 += i3;
                } else {
                    int i5 = r3[length].A00;
                    i -= i5;
                    r5.A00 -= i5;
                    r5.A01--;
                    i3++;
                }
            }
            int i42 = i2 + 1;
            System.arraycopy(r3, i42, r3, i42 + i3, r5.A01);
            r5.A03 += i3;
        }
    }

    public C04850hp(AbstractC04550h0 r3) {
        this.A07 = new AnonymousClass0HO(r3);
    }

    public static final C04610h7 A01(C04850hp r9) throws IOException {
        AnonymousClass0Od r4 = r9.A07;
        int readByte = r4.readByte() & 255;
        boolean z = false;
        if ((readByte & 128) == 128) {
            z = true;
        }
        int A002 = A00(r9, readByte, Hpack.PREFIX_7_BITS);
        if (!z) {
            return r4.A7B((long) A002);
        }
        C04730hP r2 = C04730hP.A01;
        byte[] A7A = r4.A7A((long) A002);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        C04740hQ r3 = r2.A00;
        C04740hQ r7 = r3;
        int i = 0;
        int i2 = 0;
        for (byte b : A7A) {
            i = (i << 8) | (b & 255);
            i2 += 8;
            while (i2 >= 8) {
                r7 = r7.A02[(i >>> (i2 - 8)) & 255];
                if (r7.A02 == null) {
                    byteArrayOutputStream.write(r7.A00);
                    i2 -= r7.A01;
                    r7 = r3;
                } else {
                    i2 -= 8;
                }
            }
        }
        while (i2 > 0) {
            C04740hQ r1 = r7.A02[(i << (8 - i2)) & 255];
            if (r1.A02 != null || r1.A01 > i2) {
                break;
            }
            byteArrayOutputStream.write(r1.A00);
            i2 -= r1.A01;
            r7 = r3;
        }
        return C04610h7.A05(byteArrayOutputStream.toByteArray());
    }

    public static void A03(C04850hp r5, C04870hr r6) {
        int i;
        r5.A06.add(r6);
        int i2 = r6.A00;
        int i3 = r5.A02;
        if (i2 > i3) {
            Arrays.fill(r5.A04, (Object) null);
            r5.A03 = r5.A04.length - 1;
            i = 0;
            r5.A01 = 0;
        } else {
            A02(r5, (r5.A00 + i2) - i3);
            int i4 = r5.A01 + 1;
            C04870hr[] r3 = r5.A04;
            int length = r3.length;
            if (i4 > length) {
                C04870hr[] r1 = new C04870hr[(length << 1)];
                System.arraycopy(r3, 0, r1, length, length);
                r5.A03 = r5.A04.length - 1;
                r5.A04 = r1;
                r3 = r1;
            }
            int i5 = r5.A03;
            r5.A03 = i5 - 1;
            r3[i5] = r6;
            r5.A01++;
            i = r5.A00 + i2;
        }
        r5.A00 = i;
    }
}
