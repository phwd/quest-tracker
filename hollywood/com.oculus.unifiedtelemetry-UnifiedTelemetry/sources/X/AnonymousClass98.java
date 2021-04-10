package X;

import com.facebook.acra.config.StartupBlockingConfig;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.charset.Charset;
import javax.annotation.Nullable;

/* renamed from: X.98  reason: invalid class name */
public final class AnonymousClass98 implements KJ, KC, Cloneable, ByteChannel {
    public static final byte[] A02 = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102};
    public long A00;
    @Nullable
    public C0315ce A01;

    public final byte A01(long j) {
        C0315ce ceVar;
        int i;
        long j2 = j;
        C0311cZ.A00(this.A00, j2, 1);
        long j3 = this.A00;
        if (j3 - j > j) {
            ceVar = this.A01;
            while (true) {
                int i2 = ceVar.A00;
                i = ceVar.A01;
                long j4 = (long) (i2 - i);
                if (j2 < j4) {
                    break;
                }
                j2 -= j4;
                ceVar = ceVar.A02;
            }
        } else {
            j2 = j - j3;
            ceVar = this.A01;
            do {
                ceVar = ceVar.A03;
                int i3 = ceVar.A00;
                i = ceVar.A01;
                j2 += (long) (i3 - i);
            } while (j2 < 0);
        }
        return ceVar.A06[i + ((int) j2)];
    }

    public final int A02(byte[] bArr, int i, int i2) {
        C0311cZ.A00((long) bArr.length, (long) i, (long) i2);
        C0315ce ceVar = this.A01;
        if (ceVar == null) {
            return -1;
        }
        int i3 = ceVar.A00;
        int i4 = ceVar.A01;
        int min = Math.min(i2, i3 - i4);
        System.arraycopy(ceVar.A06, i4, bArr, i, min);
        int i5 = ceVar.A01 + min;
        ceVar.A01 = i5;
        this.A00 -= (long) min;
        if (i5 == ceVar.A00) {
            this.A01 = ceVar.A00();
            C0314cd.A01(ceVar);
        }
        return min;
    }

    public final C0315ce A07(int i) {
        if (i < 1 || i > 8192) {
            throw new IllegalArgumentException();
        }
        C0315ce ceVar = this.A01;
        if (ceVar == null) {
            C0315ce A002 = C0314cd.A00();
            this.A01 = A002;
            A002.A03 = A002;
            A002.A02 = A002;
            return A002;
        }
        C0315ce ceVar2 = ceVar.A03;
        if (ceVar2.A00 + i <= 8192 && ceVar2.A04) {
            return ceVar2;
        }
        C0315ce A003 = C0314cd.A00();
        ceVar2.A02(A003);
        return A003;
    }

    public final void A09(int i) {
        C0315ce A07 = A07(1);
        byte[] bArr = A07.A06;
        int i2 = A07.A00;
        A07.A00 = i2 + 1;
        bArr[i2] = (byte) i;
        this.A00++;
    }

    public final void A0A(int i) {
        C0315ce A07 = A07(4);
        byte[] bArr = A07.A06;
        int i2 = A07.A00;
        int i3 = i2 + 1;
        bArr[i2] = (byte) ((i >>> 24) & 255);
        int i4 = i3 + 1;
        bArr[i3] = (byte) ((i >>> 16) & 255);
        int i5 = i4 + 1;
        bArr[i4] = (byte) ((i >>> 8) & 255);
        bArr[i5] = (byte) (i & 255);
        A07.A00 = i5 + 1;
        this.A00 += 4;
    }

    public final void A0C(int i) {
        C0315ce A07 = A07(2);
        byte[] bArr = A07.A06;
        int i2 = A07.A00;
        int i3 = i2 + 1;
        bArr[i2] = (byte) ((i >>> 8) & 255);
        bArr[i3] = (byte) (i & 255);
        A07.A00 = i3 + 1;
        this.A00 += 2;
    }

    public final void A0I(AnonymousClass98 r12, long j, long j2) {
        long j3 = j2;
        long j4 = j;
        if (r12 != null) {
            C0311cZ.A00(this.A00, j4, j3);
            if (j2 != 0) {
                r12.A00 += j2;
                C0315ce ceVar = this.A01;
                while (j4 >= ((long) (ceVar.A00 - ceVar.A01))) {
                    j4 -= (long) (ceVar.A00 - ceVar.A01);
                    ceVar = ceVar.A02;
                }
                while (j3 > 0) {
                    C0315ce A012 = ceVar.A01();
                    int i = (int) (((long) A012.A01) + j4);
                    A012.A01 = i;
                    A012.A00 = Math.min(i + ((int) j3), A012.A00);
                    C0315ce ceVar2 = r12.A01;
                    if (ceVar2 == null) {
                        A012.A03 = A012;
                        A012.A02 = A012;
                        r12.A01 = A012;
                    } else {
                        ceVar2.A03.A02(A012);
                    }
                    j3 -= (long) (A012.A00 - A012.A01);
                    ceVar = ceVar.A02;
                    j4 = 0;
                }
                return;
            }
            return;
        }
        throw new IllegalArgumentException("out == null");
    }

    @Override // X.KC, X.KJ
    public final AnonymousClass98 A1V() {
        return this;
    }

    @Override // X.KJ
    public final KJ A1u() {
        return this;
    }

    @Override // java.lang.AutoCloseable, java.io.Closeable, X.AbstractC0312cb, X.AbstractC0313cc, java.nio.channels.Channel
    public final void close() {
    }

    @Override // X.KJ, X.AbstractC0313cc, java.io.Flushable
    public final void flush() {
    }

    public final boolean isOpen() {
        return true;
    }

    private final String A00(long j, Charset charset) throws EOFException {
        String str;
        C0311cZ.A00(this.A00, 0, j);
        if (charset == null) {
            str = "charset == null";
        } else if (j > 2147483647L) {
            str = AnonymousClass06.A03("byteCount > Integer.MAX_VALUE: ", j);
        } else if (j == 0) {
            return "";
        } else {
            C0315ce ceVar = this.A01;
            int i = ceVar.A01;
            if (((long) i) + j > ((long) ceVar.A00)) {
                return new String(A4T(j), charset);
            }
            String str2 = new String(ceVar.A06, i, (int) j, charset);
            int i2 = (int) (((long) ceVar.A01) + j);
            ceVar.A01 = i2;
            this.A00 -= j;
            if (i2 == ceVar.A00) {
                this.A01 = ceVar.A00();
                C0314cd.A01(ceVar);
            }
            return str2;
        }
        throw new IllegalArgumentException(str);
    }

    public final long A03(byte b, long j) {
        C0315ce ceVar;
        long j2 = j;
        long j3 = 0;
        if (j < 0 || Long.MAX_VALUE < j) {
            throw new IllegalArgumentException(String.format("size=%s fromIndex=%s toIndex=%s", Long.valueOf(this.A00), Long.valueOf(j2), Long.MAX_VALUE));
        }
        long j4 = this.A00;
        long j5 = j4;
        if (Long.MAX_VALUE <= j4) {
            j4 = Long.MAX_VALUE;
        }
        if (!(j == j4 || (ceVar = this.A01) == null)) {
            if (j5 - j >= j) {
                while (true) {
                    j5 = j3;
                    j3 = ((long) (ceVar.A00 - ceVar.A01)) + j5;
                    if (j3 >= j) {
                        break;
                    }
                    ceVar = ceVar.A02;
                }
            } else {
                while (j5 > j) {
                    ceVar = ceVar.A03;
                    j5 -= (long) (ceVar.A00 - ceVar.A01);
                }
            }
            while (j5 < j4) {
                byte[] bArr = ceVar.A06;
                int i = ceVar.A00;
                int i2 = ceVar.A01;
                long j6 = (long) i2;
                int min = (int) Math.min((long) i, (j6 + j4) - j5);
                for (int i3 = (int) ((j6 + j2) - j5); i3 < min; i3++) {
                    if (bArr[i3] == b) {
                        return ((long) (i3 - i2)) + j5;
                    }
                }
                j2 = ((long) (i - i2)) + j5;
                ceVar = ceVar.A02;
                j5 = j2;
            }
        }
        return -1;
    }

    public final String A04() {
        try {
            return A00(this.A00, C0311cZ.A00);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    public final String A05(long j) throws EOFException {
        String A002;
        long j2 = 1;
        if (j > 0) {
            long j3 = j - 1;
            if (A01(j3) == 13) {
                A002 = A00(j3, C0311cZ.A00);
                j2 = 2;
                A5F(j2);
                return A002;
            }
        }
        A002 = A00(j, C0311cZ.A00);
        A5F(j2);
        return A002;
    }

    public final ci A06() {
        long j = this.A00;
        if (j <= 2147483647L) {
            int i = (int) j;
            if (i == 0) {
                return ci.A03;
            }
            return new Jx(this, i);
        }
        throw new IllegalArgumentException(AnonymousClass06.A03("size > Integer.MAX_VALUE: ", j));
    }

    public final void A08() {
        try {
            A5F(this.A00);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    public final void A0B(int i) {
        A0A(((i & 255) << 24) | ((-16777216 & i) >>> 24) | ((16711680 & i) >>> 8) | ((65280 & i) << 8));
    }

    public final void A0D(int i) {
        int i2;
        if (i >= 128) {
            int i3 = i >> 6;
            int i4 = i3 | 192;
            if (i >= 2048) {
                if (i < 65536) {
                    if (i < 55296 || i > 57343) {
                        i2 = (i >> 12) | 224;
                    } else {
                        A09(63);
                        return;
                    }
                } else if (i <= 1114111) {
                    A09((i >> 18) | 240);
                    i2 = ((i >> 12) & 63) | 128;
                } else {
                    throw new IllegalArgumentException(AnonymousClass06.A04("Unexpected code point: ", Integer.toHexString(i)));
                }
                A09(i2);
                i4 = (i3 & 63) | 128;
            }
            A09(i4);
            i = (i & 63) | 128;
        }
        A09(i);
    }

    public final void A0E(long j) {
        if (j == 0) {
            A09(48);
            return;
        }
        boolean z = false;
        int i = 1;
        if (j < 0) {
            j = -j;
            if (j < 0) {
                A0G("-9223372036854775808");
                return;
            }
            z = true;
        }
        if (j < 100000000) {
            if (j < StartupBlockingConfig.BLOCKING_UPLOAD_MAX_WAIT_MILLIS) {
                if (j >= 100) {
                    i = 4;
                    if (j < 1000) {
                        i = 3;
                    }
                } else if (j >= 10) {
                    i = 2;
                }
            } else if (j < 1000000) {
                i = 6;
                if (j < 100000) {
                    i = 5;
                }
            } else {
                i = 8;
                if (j < 10000000) {
                    i = 7;
                }
            }
        } else if (j < 1000000000000L) {
            if (j < 10000000000L) {
                i = 10;
                if (j < 1000000000) {
                    i = 9;
                }
            } else {
                i = 12;
                if (j < 100000000000L) {
                    i = 11;
                }
            }
        } else if (j < 1000000000000000L) {
            if (j < 10000000000000L) {
                i = 13;
            } else {
                i = 15;
                if (j < 100000000000000L) {
                    i = 14;
                }
            }
        } else if (j < 100000000000000000L) {
            i = 17;
            if (j < 10000000000000000L) {
                i = 16;
            }
        } else {
            i = 19;
            if (j < 1000000000000000000L) {
                i = 18;
            }
        }
        if (z) {
            i++;
        }
        C0315ce A07 = A07(i);
        byte[] bArr = A07.A06;
        int i2 = A07.A00 + i;
        while (j != 0) {
            i2--;
            bArr[i2] = A02[(int) (j % 10)];
            j /= 10;
        }
        if (z) {
            bArr[i2 - 1] = 45;
        }
        A07.A00 = i2;
        this.A00 += (long) i;
    }

    public final void A0F(long j) {
        if (j == 0) {
            A09(48);
            return;
        }
        int numberOfTrailingZeros = (Long.numberOfTrailingZeros(Long.highestOneBit(j)) / 4) + 1;
        C0315ce A07 = A07(numberOfTrailingZeros);
        byte[] bArr = A07.A06;
        int i = A07.A00;
        int i2 = i + numberOfTrailingZeros;
        while (true) {
            i2--;
            if (i2 >= i) {
                bArr[i2] = A02[(int) (15 & j)];
                j >>>= 4;
            } else {
                A07.A00 = i2;
                this.A00 += (long) numberOfTrailingZeros;
                return;
            }
        }
    }

    public final void A0H(String str, int i, int i2) {
        String A012;
        StringBuilder sb;
        char c;
        if (i >= 0) {
            if (i2 >= i) {
                int length = str.length();
                if (i2 > length) {
                    sb = new StringBuilder("endIndex > string.length: ");
                    sb.append(i2);
                    sb.append(" > ");
                    sb.append(length);
                } else {
                    while (i < i2) {
                        char charAt = str.charAt(i);
                        if (charAt < 128) {
                            C0315ce A07 = A07(1);
                            byte[] bArr = A07.A06;
                            int i3 = A07.A00 - i;
                            int min = Math.min(i2, 8192 - i3);
                            int i4 = i + 1;
                            bArr[i + i3] = (byte) charAt;
                            while (i4 < min) {
                                char charAt2 = str.charAt(i4);
                                if (charAt2 >= 128) {
                                    break;
                                }
                                bArr[i4 + i3] = (byte) charAt2;
                                i4++;
                            }
                            int i5 = A07.A00;
                            int i6 = (i3 + i4) - i5;
                            A07.A00 = i5 + i6;
                            this.A00 += (long) i6;
                            i = i4;
                        } else {
                            int i7 = (charAt >> 6) | 192;
                            if (charAt >= 2048) {
                                if (charAt < 55296 || charAt > 57343) {
                                    A09((charAt >> '\f') | 224);
                                    i7 = ((charAt >> 6) & 63) | 128;
                                } else {
                                    int i8 = i + 1;
                                    if (i8 < i2) {
                                        c = str.charAt(i8);
                                    } else {
                                        c = 0;
                                    }
                                    if (charAt > 56319 || c < 56320 || c > 57343) {
                                        A09(63);
                                        i = i8;
                                    } else {
                                        int i9 = (((charAt & 10239) << 10) | (9215 & c)) + 65536;
                                        A09((i9 >> 18) | 240);
                                        A09(((i9 >> 12) & 63) | 128);
                                        A09(((i9 >> 6) & 63) | 128);
                                        A09((i9 & 63) | 128);
                                        i += 2;
                                    }
                                }
                            }
                            A09(i7);
                            A09((charAt & '?') | 128);
                            i++;
                        }
                    }
                    return;
                }
            } else {
                sb = new StringBuilder("endIndex < beginIndex: ");
                sb.append(i2);
                sb.append(" < ");
                sb.append(i);
            }
            A012 = sb.toString();
        } else {
            A012 = AnonymousClass06.A01("beginIndex < 0: ", i);
        }
        throw new IllegalArgumentException(A012);
    }

    public final void A0J(byte[] bArr) {
        if (bArr != null) {
            A0K(bArr, 0, bArr.length);
            return;
        }
        throw new IllegalArgumentException("source == null");
    }

    public final void A0K(byte[] bArr, int i, int i2) {
        if (bArr != null) {
            long j = (long) i2;
            C0311cZ.A00((long) bArr.length, (long) i, j);
            int i3 = i2 + i;
            while (i < i3) {
                C0315ce A07 = A07(1);
                int i4 = A07.A00;
                int min = Math.min(i3 - i, 8192 - i4);
                System.arraycopy(bArr, i, A07.A06, i4, min);
                i += min;
                A07.A00 += min;
            }
            this.A00 += j;
            return;
        }
        throw new IllegalArgumentException("source == null");
    }

    @Override // X.KC
    public final boolean A1z() {
        if (this.A00 == 0) {
            return true;
        }
        return false;
    }

    @Override // X.KC
    public final long A34(byte b) {
        return A03(b, 0);
    }

    @Override // X.KC
    public final InputStream A37() {
        return new cj(this);
    }

    @Override // X.KJ
    public final OutputStream A48() {
        return new C0319ck(this);
    }

    @Override // X.KC
    public final byte[] A4S() {
        try {
            return A4T(this.A00);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    @Override // X.KC
    public final byte[] A4T(long j) throws EOFException {
        C0311cZ.A00(this.A00, 0, j);
        if (j <= 2147483647L) {
            int i = (int) j;
            byte[] bArr = new byte[i];
            int i2 = 0;
            while (i2 < i) {
                int A022 = A02(bArr, i2, i - i2);
                if (A022 != -1) {
                    i2 += A022;
                } else {
                    throw new EOFException();
                }
            }
            return bArr;
        }
        throw new IllegalArgumentException(AnonymousClass06.A03("byteCount > Integer.MAX_VALUE: ", j));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0042, code lost:
        if (r16 != false) goto L_0x0047;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0044, code lost:
        r0.readByte();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0047, code lost:
        r1 = "Number too large: ";
        r0 = r0.A04();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0056, code lost:
        throw new java.lang.NumberFormatException(X.AnonymousClass06.A04(r1, r0));
     */
    @Override // X.KC
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final long A4V() {
        /*
        // Method dump skipped, instructions count: 158
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass98.A4V():long");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002a, code lost:
        if (r10 > 102) goto L_0x002c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x004b, code lost:
        if (r7 != r3) goto L_0x0063;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x004d, code lost:
        r15.A01 = r8.A00();
        X.C0314cd.A01(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0056, code lost:
        if (r12 != false) goto L_0x005c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0063, code lost:
        r8.A01 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0020, code lost:
        if (r10 > 57) goto L_0x0022;
     */
    @Override // X.KC
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final long A4W() {
        /*
        // Method dump skipped, instructions count: 144
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass98.A4W():long");
    }

    @Override // X.KC
    public final String A4b(Charset charset) {
        try {
            return A00(this.A00, charset);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    @Override // X.KC
    public final void A4l(long j) throws EOFException {
        if (this.A00 < j) {
            throw new EOFException();
        }
    }

    @Override // X.KC
    public final void A5F(long j) throws EOFException {
        while (j > 0) {
            C0315ce ceVar = this.A01;
            if (ceVar != null) {
                int i = ceVar.A00;
                int i2 = ceVar.A01;
                int min = (int) Math.min(j, (long) (i - i2));
                long j2 = (long) min;
                this.A00 -= j2;
                j -= j2;
                int i3 = i2 + min;
                ceVar.A01 = i3;
                if (i3 == i) {
                    this.A01 = ceVar.A00();
                    C0314cd.A01(ceVar);
                }
            } else {
                throw new EOFException();
            }
        }
    }

    @Override // X.KJ
    public final /* bridge */ /* synthetic */ KJ A5m(ci ciVar) throws IOException {
        if (ciVar != null) {
            ciVar.A0F(this);
            return this;
        }
        throw new IllegalArgumentException("byteString == null");
    }

    @Override // X.KJ
    public final long A5p(AbstractC0312cb cbVar) throws IOException {
        if (cbVar != null) {
            long j = 0;
            while (true) {
                long read = cbVar.read(this, 8192);
                if (read == -1) {
                    return j;
                }
                j += read;
            }
        } else {
            throw new IllegalArgumentException("source == null");
        }
    }

    @Override // java.lang.Object
    public final Object clone() throws CloneNotSupportedException {
        AnonymousClass98 r7 = new AnonymousClass98();
        long j = this.A00;
        if (j != 0) {
            C0315ce A012 = this.A01.A01();
            r7.A01 = A012;
            A012.A03 = A012;
            A012.A02 = A012;
            C0315ce ceVar = this.A01;
            while (true) {
                ceVar = ceVar.A02;
                if (ceVar == ceVar) {
                    break;
                }
                A012.A03.A02(ceVar.A01());
            }
            r7.A00 = j;
        }
        return r7;
    }

    public final boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof AnonymousClass98) {
                AnonymousClass98 r3 = (AnonymousClass98) obj;
                long j = this.A00;
                if (j == r3.A00) {
                    long j2 = 0;
                    if (j != 0) {
                        C0315ce ceVar = this.A01;
                        C0315ce ceVar2 = r3.A01;
                        int i = ceVar.A01;
                        int i2 = ceVar2.A01;
                        while (j2 < j) {
                            long min = (long) Math.min(ceVar.A00 - i, ceVar2.A00 - i2);
                            int i3 = 0;
                            while (((long) i3) < min) {
                                int i4 = i + 1;
                                int i5 = i2 + 1;
                                if (ceVar.A06[i] == ceVar2.A06[i2]) {
                                    i3++;
                                    i = i4;
                                    i2 = i5;
                                }
                            }
                            if (i == ceVar.A00) {
                                ceVar = ceVar.A02;
                                i = ceVar.A01;
                            }
                            if (i2 == ceVar2.A00) {
                                ceVar2 = ceVar2.A02;
                                i2 = ceVar2.A01;
                            }
                            j2 += min;
                        }
                    }
                }
            }
            return false;
        }
        return true;
    }

    public final int hashCode() {
        C0315ce ceVar = this.A01;
        if (ceVar == null) {
            return 0;
        }
        int i = 1;
        do {
            int i2 = ceVar.A00;
            for (int i3 = ceVar.A01; i3 < i2; i3++) {
                i = (i * 31) + ceVar.A06[i3];
            }
            ceVar = ceVar.A02;
        } while (ceVar != ceVar);
        return i;
    }

    @Override // X.KC
    public final byte readByte() {
        long j = this.A00;
        if (j != 0) {
            C0315ce ceVar = this.A01;
            int i = ceVar.A01;
            int i2 = ceVar.A00;
            int i3 = i + 1;
            byte b = ceVar.A06[i];
            this.A00 = j - 1;
            if (i3 == i2) {
                this.A01 = ceVar.A00();
                C0314cd.A01(ceVar);
                return b;
            }
            ceVar.A01 = i3;
            return b;
        }
        throw new IllegalStateException("size == 0");
    }

    @Override // X.KC
    public final int readInt() {
        long j = this.A00;
        if (j >= 4) {
            C0315ce ceVar = this.A01;
            int i = ceVar.A01;
            int i2 = ceVar.A00;
            if (i2 - i < 4) {
                return ((readByte() & 255) << 24) | ((readByte() & 255) << 16) | ((readByte() & 255) << 8) | (readByte() & 255);
            }
            byte[] bArr = ceVar.A06;
            int i3 = i + 1;
            int i4 = i3 + 1;
            int i5 = i4 + 1;
            int i6 = i5 + 1;
            int i7 = ((bArr[i] & 255) << 24) | ((bArr[i3] & 255) << 16) | ((bArr[i4] & 255) << 8) | (bArr[i5] & 255);
            this.A00 = j - 4;
            if (i6 == i2) {
                this.A01 = ceVar.A00();
                C0314cd.A01(ceVar);
                return i7;
            }
            ceVar.A01 = i6;
            return i7;
        }
        throw new IllegalStateException(AnonymousClass06.A03("size < 4: ", j));
    }

    @Override // X.KC
    public final short readShort() {
        long j = this.A00;
        if (j >= 2) {
            C0315ce ceVar = this.A01;
            int i = ceVar.A01;
            int i2 = ceVar.A00;
            if (i2 - i < 2) {
                return (short) (((readByte() & 255) << 8) | (readByte() & 255));
            }
            byte[] bArr = ceVar.A06;
            int i3 = i + 1;
            int i4 = i3 + 1;
            int i5 = ((bArr[i] & 255) << 8) | (bArr[i3] & 255);
            this.A00 = j - 2;
            if (i4 == i2) {
                this.A01 = ceVar.A00();
                C0314cd.A01(ceVar);
            } else {
                ceVar.A01 = i4;
            }
            return (short) i5;
        }
        throw new IllegalStateException(AnonymousClass06.A03("size < 2: ", j));
    }

    public final void A0G(String str) {
        A0H(str, 0, str.length());
    }

    @Override // X.KC
    public final boolean A4P(long j, ci ciVar) {
        int A07 = ciVar.A07();
        if (j < 0 || A07 < 0 || this.A00 - j < ((long) A07) || A07 - 0 < A07) {
            return false;
        }
        for (int i = 0; i < A07; i++) {
            if (A01(((long) i) + j) != ciVar.A06(0 + i)) {
                return false;
            }
        }
        return true;
    }

    @Override // X.KC
    public final ci A4U(long j) throws EOFException {
        return new ci(A4T(j));
    }

    @Override // X.KC
    public final int A4X() {
        int readInt = readInt();
        return ((readInt & 255) << 24) | ((-16777216 & readInt) >>> 24) | ((16711680 & readInt) >>> 8) | ((65280 & readInt) << 8);
    }

    @Override // X.KC
    public final short A4a() {
        int readShort = readShort() & 65535;
        return (short) (((readShort & 255) << 8) | ((65280 & readShort) >>> 8));
    }

    public final String toString() {
        return A06().toString();
    }

    @Override // X.KJ
    public final KJ A1v() throws IOException {
        return this;
    }

    @Override // X.AbstractC0312cb, X.AbstractC0313cc
    public final ca timeout() {
        return ca.NONE;
    }

    @Override // X.KC
    public final String A4c() throws EOFException {
        long A03 = A03((byte) 10, 0);
        if (A03 != -1) {
            return A05(A03);
        }
        if (Long.MAX_VALUE < this.A00 && A01(9223372036854775806L) == 13 && A01(Long.MAX_VALUE) == 10) {
            return A05(Long.MAX_VALUE);
        }
        AnonymousClass98 r8 = new AnonymousClass98();
        A0I(r8, 0, Math.min(32L, this.A00));
        StringBuilder sb = new StringBuilder("\\n not found: limit=");
        sb.append(Math.min(this.A00, Long.MAX_VALUE));
        sb.append(" content=");
        sb.append(new ci(r8.A4S()).A09());
        sb.append((char) 8230);
        throw new EOFException(sb.toString());
    }

    @Override // X.KJ
    public final /* bridge */ /* synthetic */ KJ A5n(byte[] bArr) throws IOException {
        A0J(bArr);
        return this;
    }

    @Override // X.KJ
    public final /* bridge */ /* synthetic */ KJ A5q(int i) throws IOException {
        A09(i);
        return this;
    }

    @Override // X.KJ
    public final /* bridge */ /* synthetic */ KJ A5r(long j) throws IOException {
        A0E(j);
        return this;
    }

    @Override // X.KJ
    public final /* bridge */ /* synthetic */ KJ A5s(long j) throws IOException {
        A0F(j);
        return this;
    }

    @Override // X.KJ
    public final /* bridge */ /* synthetic */ KJ A5t(int i) throws IOException {
        A0A(i);
        return this;
    }

    @Override // X.KJ
    public final /* bridge */ /* synthetic */ KJ A5u(int i) throws IOException {
        A0B(i);
        return this;
    }

    @Override // X.KJ
    public final /* bridge */ /* synthetic */ KJ A5x(int i) throws IOException {
        A0C(i);
        return this;
    }

    @Override // X.KJ
    public final /* bridge */ /* synthetic */ KJ A5y(String str) throws IOException {
        A0G(str);
        return this;
    }

    @Override // X.KJ
    public final /* bridge */ /* synthetic */ KJ A5o(byte[] bArr, int i, int i2) throws IOException {
        A0K(bArr, i, i2);
        return this;
    }

    @Override // java.nio.channels.ReadableByteChannel
    public final int read(ByteBuffer byteBuffer) throws IOException {
        C0315ce ceVar = this.A01;
        if (ceVar == null) {
            return -1;
        }
        int remaining = byteBuffer.remaining();
        int i = ceVar.A00;
        int i2 = ceVar.A01;
        int min = Math.min(remaining, i - i2);
        byteBuffer.put(ceVar.A06, i2, min);
        int i3 = ceVar.A01 + min;
        ceVar.A01 = i3;
        this.A00 -= (long) min;
        if (i3 == ceVar.A00) {
            this.A01 = ceVar.A00();
            C0314cd.A01(ceVar);
        }
        return min;
    }

    @Override // X.AbstractC0312cb
    public final long read(AnonymousClass98 r6, long j) {
        String str;
        if (r6 == null) {
            str = "sink == null";
        } else if (j >= 0) {
            long j2 = this.A00;
            if (j2 == 0) {
                return -1;
            }
            if (j > j2) {
                j = j2;
            }
            r6.write(this, j);
            return j;
        } else {
            str = AnonymousClass06.A03("byteCount < 0: ", j);
        }
        throw new IllegalArgumentException(str);
    }

    @Override // X.AbstractC0313cc
    public final void write(AnonymousClass98 r11, long j) {
        String str;
        int i;
        C0315ce ceVar;
        int i2;
        long j2 = j;
        if (r11 == null) {
            str = "source == null";
        } else if (r11 != this) {
            C0311cZ.A00(r11.A00, 0, j2);
            while (j2 > 0) {
                C0315ce ceVar2 = r11.A01;
                C0315ce ceVar3 = ceVar2;
                int i3 = ceVar2.A00 - ceVar2.A01;
                if (j2 < ((long) i3)) {
                    C0315ce ceVar4 = this.A01;
                    if (!(ceVar4 == null || (ceVar = ceVar4.A03) == null || !ceVar.A04)) {
                        long j3 = ((long) ceVar.A00) + j2;
                        if (ceVar.A05) {
                            i2 = 0;
                        } else {
                            i2 = ceVar.A01;
                        }
                        if (j3 - ((long) i2) <= 8192) {
                            ceVar2.A03(ceVar, (int) j2);
                            r11.A00 -= j2;
                            this.A00 += j2;
                            return;
                        }
                    }
                    int i4 = (int) j2;
                    if (i4 <= 0 || i4 > i3) {
                        throw new IllegalArgumentException();
                    }
                    if (i4 >= 1024) {
                        ceVar3 = ceVar2.A01();
                    } else {
                        ceVar3 = C0314cd.A00();
                        System.arraycopy(ceVar2.A06, ceVar2.A01, ceVar3.A06, 0, i4);
                    }
                    ceVar3.A00 = ceVar3.A01 + i4;
                    ceVar2.A01 += i4;
                    ceVar2.A03.A02(ceVar3);
                    r11.A01 = ceVar3;
                }
                int i5 = ceVar3.A00 - ceVar3.A01;
                long j4 = (long) i5;
                r11.A01 = ceVar3.A00();
                C0315ce ceVar5 = this.A01;
                if (ceVar5 == null) {
                    this.A01 = ceVar3;
                    ceVar3.A03 = ceVar3;
                    ceVar3.A02 = ceVar3;
                } else {
                    ceVar5.A03.A02(ceVar3);
                    C0315ce ceVar6 = ceVar3.A03;
                    if (ceVar6 == ceVar3) {
                        throw new IllegalStateException();
                    } else if (ceVar6.A04) {
                        int i6 = 8192 - ceVar6.A00;
                        if (ceVar6.A05) {
                            i = 0;
                        } else {
                            i = ceVar6.A01;
                        }
                        if (i5 <= i6 + i) {
                            ceVar3.A03(ceVar6, i5);
                            ceVar3.A00();
                            C0314cd.A01(ceVar3);
                        }
                    }
                }
                r11.A00 -= j4;
                this.A00 += j4;
                j2 -= j4;
            }
            return;
        } else {
            str = "source == this";
        }
        throw new IllegalArgumentException(str);
    }

    @Override // java.nio.channels.WritableByteChannel
    public final int write(ByteBuffer byteBuffer) throws IOException {
        if (byteBuffer != null) {
            int remaining = byteBuffer.remaining();
            int i = remaining;
            while (i > 0) {
                C0315ce A07 = A07(1);
                int i2 = A07.A00;
                int min = Math.min(i, 8192 - i2);
                byteBuffer.get(A07.A06, i2, min);
                i -= min;
                A07.A00 += min;
            }
            this.A00 += (long) remaining;
            return remaining;
        }
        throw new IllegalArgumentException("source == null");
    }
}
