package X;

import com.squareup.okhttp.internal.framed.Hpack;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* renamed from: X.Wp  reason: case insensitive filesystem */
public final class C0164Wp {
    public int A00 = 0;
    public int A01 = 0;
    public int A02 = 4096;
    public int A03 = 7;
    public C0165Wq[] A04 = new C0165Wq[8];
    public final int A05 = 4096;
    public final List<C0165Wq> A06 = new ArrayList();
    public final Dp A07;

    public static final int A00(C0164Wp wp, int i, int i2) throws IOException {
        int i3 = i & i2;
        if (i3 < i2) {
            return i3;
        }
        int i4 = 0;
        while (true) {
            int readByte = wp.A07.readByte() & 255;
            if ((readByte & 128) == 0) {
                return i2 + (readByte << i4);
            }
            i2 += (readByte & Hpack.PREFIX_7_BITS) << i4;
            i4 += 7;
        }
    }

    public static void A02(C0164Wp wp, int i) {
        int i2;
        int i3 = 0;
        if (i > 0) {
            C0165Wq[] wqArr = wp.A04;
            int length = wqArr.length;
            while (true) {
                length--;
                i2 = wp.A03;
                if (length < i2 || i <= 0) {
                    int i4 = i2 + 1;
                    System.arraycopy(wqArr, i4, wqArr, i4 + i3, wp.A01);
                    wp.A03 += i3;
                } else {
                    int i5 = wqArr[length].A00;
                    i -= i5;
                    wp.A00 -= i5;
                    wp.A01--;
                    i3++;
                }
            }
            int i42 = i2 + 1;
            System.arraycopy(wqArr, i42, wqArr, i42 + i3, wp.A01);
            wp.A03 += i3;
        }
    }

    public C0164Wp(WF wf) {
        this.A07 = new C00148h(wf);
    }

    public static final WM A01(C0164Wp wp) throws IOException {
        Dp dp = wp.A07;
        int readByte = dp.readByte() & 255;
        boolean z = false;
        if ((readByte & 128) == 128) {
            z = true;
        }
        int A002 = A00(wp, readByte, Hpack.PREFIX_7_BITS);
        if (!z) {
            return dp.A33((long) A002);
        }
        C0153We we = C0153We.A01;
        byte[] A32 = dp.A32((long) A002);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        C0154Wf wf = we.A00;
        C0154Wf wf2 = wf;
        int i = 0;
        int i2 = 0;
        for (byte b : A32) {
            i = (i << 8) | (b & 255);
            i2 += 8;
            while (i2 >= 8) {
                wf2 = wf2.A02[(i >>> (i2 - 8)) & 255];
                if (wf2.A02 == null) {
                    byteArrayOutputStream.write(wf2.A00);
                    i2 -= wf2.A01;
                    wf2 = wf;
                } else {
                    i2 -= 8;
                }
            }
        }
        while (i2 > 0) {
            C0154Wf wf3 = wf2.A02[(i << (8 - i2)) & 255];
            if (wf3.A02 != null || wf3.A01 > i2) {
                break;
            }
            byteArrayOutputStream.write(wf3.A00);
            i2 -= wf3.A01;
            wf2 = wf;
        }
        return WM.A05(byteArrayOutputStream.toByteArray());
    }

    public static void A03(C0164Wp wp, C0165Wq wq) {
        int i;
        wp.A06.add(wq);
        int i2 = wq.A00;
        int i3 = wp.A02;
        if (i2 > i3) {
            Arrays.fill(wp.A04, (Object) null);
            wp.A03 = wp.A04.length - 1;
            i = 0;
            wp.A01 = 0;
        } else {
            A02(wp, (wp.A00 + i2) - i3);
            int i4 = wp.A01 + 1;
            C0165Wq[] wqArr = wp.A04;
            int length = wqArr.length;
            if (i4 > length) {
                C0165Wq[] wqArr2 = new C0165Wq[(length << 1)];
                System.arraycopy(wqArr, 0, wqArr2, length, length);
                wp.A03 = wp.A04.length - 1;
                wp.A04 = wqArr2;
                wqArr = wqArr2;
            }
            int i5 = wp.A03;
            wp.A03 = i5 - 1;
            wqArr[i5] = wq;
            wp.A01++;
            i = wp.A00 + i2;
        }
        wp.A00 = i;
    }
}
