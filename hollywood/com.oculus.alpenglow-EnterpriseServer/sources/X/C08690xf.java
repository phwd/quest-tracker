package X;

import com.squareup.okhttp.internal.framed.Hpack;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;

/* renamed from: X.0xf  reason: invalid class name and case insensitive filesystem */
public final class C08690xf {
    public static final byte[] A06;
    @Nullable
    public Byte A00 = null;
    @Nullable
    public Short A01 = null;
    public List<Short> A02 = new ArrayList(15);
    public short A03 = 0;
    public final OutputStream A04;
    public final byte[] A05 = new byte[10];

    public static void A00(C08690xf r2, byte b, short s, byte b2) throws IOException {
        int i;
        if (b2 == -1) {
            b2 = A06[b];
        }
        short s2 = r2.A03;
        if (s <= s2 || (i = s - s2) > 15) {
            r2.A04.write(b2);
            A01(r2, (s >> 31) ^ (s << 1));
        } else {
            r2.A04.write((byte) ((i << 4) | b2));
        }
        r2.A03 = s;
    }

    public static void A01(C08690xf r5, int i) throws IOException {
        int i2 = 0;
        while ((i & -128) != 0) {
            r5.A05[i2] = (byte) ((i & Hpack.PREFIX_7_BITS) | 128);
            i >>>= 7;
            i2++;
        }
        byte[] bArr = r5.A05;
        bArr[i2] = (byte) i;
        r5.A04.write(bArr, 0, i2 + 1);
    }

    public final void A04(long j) throws IOException {
        long j2 = (j >> 63) ^ (j << 1);
        int i = 0;
        while (true) {
            int i2 = ((-128 & j2) > 0 ? 1 : ((-128 & j2) == 0 ? 0 : -1));
            byte[] bArr = this.A05;
            int i3 = i + 1;
            if (i2 == 0) {
                bArr[i] = (byte) ((int) j2);
                this.A04.write(bArr, 0, i3);
                return;
            }
            bArr[i] = (byte) ((int) ((127 & j2) | 128));
            j2 >>>= 7;
            i = i3;
        }
    }

    static {
        byte[] bArr = new byte[20];
        A06 = bArr;
        bArr[0] = 0;
        bArr[2] = 1;
        bArr[3] = 3;
        bArr[6] = 4;
        bArr[8] = 5;
        bArr[10] = 6;
        bArr[4] = 7;
        bArr[11] = 8;
        bArr[15] = 9;
        bArr[14] = 10;
        bArr[13] = 11;
        bArr[12] = 12;
        bArr[19] = 13;
    }

    public final void A02() throws IOException {
        this.A02.add(Short.valueOf(this.A03));
        this.A03 = 0;
    }

    public final void A03(byte b, int i) throws IOException {
        if (i <= 14) {
            this.A04.write((byte) (A06[b] | (i << 4)));
            return;
        }
        this.A04.write((byte) (A06[b] | 240));
        A01(this, i);
    }

    public final void A05(String str) throws IOException {
        try {
            byte[] bytes = str.getBytes("UTF-8");
            int length = bytes.length;
            A01(this, length);
            this.A04.write(bytes, 0, length);
        } catch (UnsupportedEncodingException unused) {
            throw new IOException("UTF-8 not supported!");
        }
    }

    public final void A06(boolean z) throws IOException {
        Short sh = this.A01;
        byte b = 1;
        if (sh != null) {
            byte byteValue = this.A00.byteValue();
            short shortValue = sh.shortValue();
            if (!z) {
                b = 2;
            }
            A00(this, byteValue, shortValue, b);
            this.A00 = null;
            this.A01 = null;
            return;
        }
        if (!z) {
            b = 2;
        }
        this.A04.write(b);
    }

    public C08690xf(OutputStream outputStream) {
        this.A04 = outputStream;
    }
}
