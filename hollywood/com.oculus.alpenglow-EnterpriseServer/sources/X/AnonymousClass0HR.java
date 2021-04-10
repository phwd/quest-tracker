package X;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.charset.Charset;
import javax.annotation.Nullable;

/* renamed from: X.0HR  reason: invalid class name */
public final class AnonymousClass0HR implements AnonymousClass0Oe, AnonymousClass0Od, Cloneable, ByteChannel {
    public static final byte[] A02 = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102};
    public long A00;
    @Nullable
    public C04570h3 A01;

    public final byte A01(long j) {
        C04570h3 r4;
        int i;
        long j2 = j;
        C04530gy.A00(this.A00, j2, 1);
        long j3 = this.A00;
        if (j3 - j > j) {
            r4 = this.A01;
            while (true) {
                int i2 = r4.A01;
                i = r4.A02;
                long j4 = (long) (i2 - i);
                if (j2 < j4) {
                    break;
                }
                j2 -= j4;
                r4 = r4.A00;
            }
        } else {
            j2 = j - j3;
            r4 = this.A01;
            do {
                r4 = r4.A03;
                int i3 = r4.A01;
                i = r4.A02;
                j2 += (long) (i3 - i);
            } while (j2 < 0);
        }
        return r4.A06[i + ((int) j2)];
    }

    public final int A02(byte[] bArr, int i, int i2) {
        C04530gy.A00((long) bArr.length, (long) i, (long) i2);
        C04570h3 r6 = this.A01;
        if (r6 == null) {
            return -1;
        }
        int i3 = r6.A01;
        int i4 = r6.A02;
        int min = Math.min(i2, i3 - i4);
        System.arraycopy(r6.A06, i4, bArr, i, min);
        int i5 = r6.A02 + min;
        r6.A02 = i5;
        this.A00 -= (long) min;
        if (i5 == r6.A01) {
            this.A01 = r6.A00();
            C04560h2.A01(r6);
        }
        return min;
    }

    public final C04570h3 A07(int i) {
        if (i < 1 || i > 8192) {
            throw new IllegalArgumentException();
        }
        C04570h3 r0 = this.A01;
        if (r0 == null) {
            C04570h3 A002 = C04560h2.A00();
            this.A01 = A002;
            A002.A03 = A002;
            A002.A00 = A002;
            return A002;
        }
        C04570h3 r1 = r0.A03;
        if (r1.A01 + i <= 8192 && r1.A04) {
            return r1;
        }
        C04570h3 A003 = C04560h2.A00();
        r1.A02(A003);
        return A003;
    }

    public final void A09(int i) {
        C04570h3 A07 = A07(1);
        byte[] bArr = A07.A06;
        int i2 = A07.A01;
        A07.A01 = i2 + 1;
        bArr[i2] = (byte) i;
        this.A00++;
    }

    public final void A0A(int i) {
        C04570h3 A07 = A07(4);
        byte[] bArr = A07.A06;
        int i2 = A07.A01;
        int i3 = i2 + 1;
        bArr[i2] = (byte) ((i >>> 24) & 255);
        int i4 = i3 + 1;
        bArr[i3] = (byte) ((i >>> 16) & 255);
        int i5 = i4 + 1;
        bArr[i4] = (byte) ((i >>> 8) & 255);
        bArr[i5] = (byte) (i & 255);
        A07.A01 = i5 + 1;
        this.A00 += 4;
    }

    public final void A0B(int i) {
        C04570h3 A07 = A07(2);
        byte[] bArr = A07.A06;
        int i2 = A07.A01;
        int i3 = i2 + 1;
        bArr[i2] = (byte) ((i >>> 8) & 255);
        bArr[i3] = (byte) (i & 255);
        A07.A01 = i3 + 1;
        this.A00 += 2;
    }

    public final void A0H(AnonymousClass0HR r12, long j, long j2) {
        long j3 = j2;
        long j4 = j;
        if (r12 != null) {
            C04530gy.A00(this.A00, j4, j3);
            if (j2 != 0) {
                r12.A00 += j2;
                C04570h3 r2 = this.A01;
                while (j4 >= ((long) (r2.A01 - r2.A02))) {
                    j4 -= (long) (r2.A01 - r2.A02);
                    r2 = r2.A00;
                }
                while (j3 > 0) {
                    C04570h3 A012 = r2.A01();
                    int i = (int) (((long) A012.A02) + j4);
                    A012.A02 = i;
                    A012.A01 = Math.min(i + ((int) j3), A012.A01);
                    C04570h3 r0 = r12.A01;
                    if (r0 == null) {
                        A012.A03 = A012;
                        A012.A00 = A012;
                        r12.A01 = A012;
                    } else {
                        r0.A03.A02(A012);
                    }
                    j3 -= (long) (A012.A01 - A012.A02);
                    r2 = r2.A00;
                    j4 = 0;
                }
                return;
            }
            return;
        }
        throw new IllegalArgumentException("out == null");
    }

    @Override // X.AnonymousClass0Od, X.AnonymousClass0Oe
    public final AnonymousClass0HR A1Z() {
        return this;
    }

    @Override // X.AnonymousClass0Oe
    public final AnonymousClass0Oe A2H() {
        return this;
    }

    @Override // java.lang.AutoCloseable, X.AnonymousClass0h1, X.AbstractC04550h0, java.io.Closeable, java.nio.channels.Channel
    public final void close() {
    }

    @Override // X.AnonymousClass0h1, X.AnonymousClass0Oe, java.io.Flushable
    public final void flush() {
    }

    public final boolean isOpen() {
        return true;
    }

    private final String A00(long j, Charset charset) throws EOFException {
        String str;
        C04530gy.A00(this.A00, 0, j);
        if (charset == null) {
            str = "charset == null";
        } else if (j > 2147483647L) {
            str = AnonymousClass006.A04("byteCount > Integer.MAX_VALUE: ", j);
        } else if (j == 0) {
            return "";
        } else {
            C04570h3 r4 = this.A01;
            int i = r4.A02;
            if (((long) i) + j > ((long) r4.A01)) {
                return new String(A7A(j), charset);
            }
            String str2 = new String(r4.A06, i, (int) j, charset);
            int i2 = (int) (((long) r4.A02) + j);
            r4.A02 = i2;
            this.A00 -= j;
            if (i2 == r4.A01) {
                this.A01 = r4.A00();
                C04560h2.A01(r4);
            }
            return str2;
        }
        throw new IllegalArgumentException(str);
    }

    public final long A03(byte b, long j) {
        C04570h3 r13;
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
        if (!(j == j4 || (r13 = this.A01) == null)) {
            if (j5 - j >= j) {
                while (true) {
                    j5 = j3;
                    j3 = ((long) (r13.A01 - r13.A02)) + j5;
                    if (j3 >= j) {
                        break;
                    }
                    r13 = r13.A00;
                }
            } else {
                while (j5 > j) {
                    r13 = r13.A03;
                    j5 -= (long) (r13.A01 - r13.A02);
                }
            }
            while (j5 < j4) {
                byte[] bArr = r13.A06;
                int i = r13.A01;
                int i2 = r13.A02;
                long j6 = (long) i2;
                int min = (int) Math.min((long) i, (j6 + j4) - j5);
                for (int i3 = (int) ((j6 + j2) - j5); i3 < min; i3++) {
                    if (bArr[i3] == b) {
                        return ((long) (i3 - i2)) + j5;
                    }
                }
                j2 = ((long) (i - i2)) + j5;
                r13 = r13.A00;
                j5 = j2;
            }
        }
        return -1;
    }

    public final String A04() {
        try {
            return A00(this.A00, C04530gy.A00);
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
                A002 = A00(j3, C04530gy.A00);
                j2 = 2;
                A8T(j2);
                return A002;
            }
        }
        A002 = A00(j, C04530gy.A00);
        A8T(j2);
        return A002;
    }

    public final C04610h7 A06() {
        long j = this.A00;
        if (j <= 2147483647L) {
            int i = (int) j;
            if (i == 0) {
                return C04610h7.A03;
            }
            return new AnonymousClass0OR(this, i);
        }
        throw new IllegalArgumentException(AnonymousClass006.A04("size > Integer.MAX_VALUE: ", j));
    }

    public final void A08() {
        try {
            A8T(this.A00);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    public final void A0C(int i) {
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
                    throw new IllegalArgumentException(AnonymousClass006.A05("Unexpected code point: ", Integer.toHexString(i)));
                }
                A09(i2);
                i4 = (i3 & 63) | 128;
            }
            A09(i4);
            i = (i & 63) | 128;
        }
        A09(i);
    }

    public final void A0D(long j) {
        if (j == 0) {
            A09(48);
            return;
        }
        boolean z = false;
        int i = 1;
        if (j < 0) {
            j = -j;
            if (j < 0) {
                A0F("-9223372036854775808");
                return;
            }
            z = true;
        }
        if (j < 100000000) {
            if (j < 10000) {
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
        C04570h3 A07 = A07(i);
        byte[] bArr = A07.A06;
        int i2 = A07.A01 + i;
        while (j != 0) {
            i2--;
            bArr[i2] = A02[(int) (j % 10)];
            j /= 10;
        }
        if (z) {
            bArr[i2 - 1] = 45;
        }
        A07.A01 = i2;
        this.A00 += (long) i;
    }

    public final void A0E(long j) {
        if (j == 0) {
            A09(48);
            return;
        }
        int numberOfTrailingZeros = (Long.numberOfTrailingZeros(Long.highestOneBit(j)) / 4) + 1;
        C04570h3 A07 = A07(numberOfTrailingZeros);
        byte[] bArr = A07.A06;
        int i = A07.A01;
        int i2 = i + numberOfTrailingZeros;
        while (true) {
            i2--;
            if (i2 >= i) {
                bArr[i2] = A02[(int) (15 & j)];
                j >>>= 4;
            } else {
                A07.A01 = i2;
                this.A00 += (long) numberOfTrailingZeros;
                return;
            }
        }
    }

    public final void A0G(String str, int i, int i2) {
        String A012;
        char c;
        if (i < 0) {
            A012 = AnonymousClass006.A01("beginIndex < 0: ", i);
        } else if (i2 >= i) {
            int length = str.length();
            if (i2 > length) {
                A012 = AnonymousClass006.A03("endIndex > string.length: ", i2, " > ", length);
            } else {
                while (i < i2) {
                    char charAt = str.charAt(i);
                    if (charAt < 128) {
                        C04570h3 A07 = A07(1);
                        byte[] bArr = A07.A06;
                        int i3 = A07.A01 - i;
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
                        int i5 = A07.A01;
                        int i6 = (i3 + i4) - i5;
                        A07.A01 = i5 + i6;
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
            A012 = AnonymousClass006.A03("endIndex < beginIndex: ", i2, " < ", i);
        }
        throw new IllegalArgumentException(A012);
    }

    public final void A0I(byte[] bArr) {
        if (bArr != null) {
            A0J(bArr, 0, bArr.length);
            return;
        }
        throw new IllegalArgumentException("source == null");
    }

    public final void A0J(byte[] bArr, int i, int i2) {
        if (bArr != null) {
            long j = (long) i2;
            C04530gy.A00((long) bArr.length, (long) i, j);
            int i3 = i2 + i;
            while (i < i3) {
                C04570h3 A07 = A07(1);
                int i4 = A07.A01;
                int min = Math.min(i3 - i, 8192 - i4);
                System.arraycopy(bArr, i, A07.A06, i4, min);
                i += min;
                A07.A01 += min;
            }
            this.A00 += j;
            return;
        }
        throw new IllegalArgumentException("source == null");
    }

    @Override // X.AnonymousClass0Oe
    public final AnonymousClass0Oe A2I() throws IOException {
        return this;
    }

    @Override // X.AnonymousClass0Od
    public final boolean A2T() {
        if (this.A00 == 0) {
            return true;
        }
        return false;
    }

    @Override // X.AnonymousClass0Od
    public final long A5A(byte b) {
        return A03(b, 0);
    }

    @Override // X.AnonymousClass0Od
    public final InputStream A5H() {
        return new C04620h8(this);
    }

    @Override // X.AnonymousClass0Oe
    public final OutputStream A6l() {
        return new C04630h9(this);
    }

    @Override // X.AnonymousClass0Od
    public final byte[] A79() {
        try {
            return A7A(this.A00);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    @Override // X.AnonymousClass0Od
    public final byte[] A7A(long j) throws EOFException {
        C04530gy.A00(this.A00, 0, j);
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
        throw new IllegalArgumentException(AnonymousClass006.A04("byteCount > Integer.MAX_VALUE: ", j));
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
        throw new java.lang.NumberFormatException(X.AnonymousClass006.A05(r1, r0));
     */
    @Override // X.AnonymousClass0Od
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final long A7C() {
        /*
        // Method dump skipped, instructions count: 158
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0HR.A7C():long");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002a, code lost:
        if (r10 > 102) goto L_0x002c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x004b, code lost:
        if (r7 != r3) goto L_0x0063;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x004d, code lost:
        r15.A01 = r8.A00();
        X.C04560h2.A01(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0056, code lost:
        if (r12 != false) goto L_0x005c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0063, code lost:
        r8.A02 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0020, code lost:
        if (r10 > 57) goto L_0x0022;
     */
    @Override // X.AnonymousClass0Od
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final long A7D() {
        /*
        // Method dump skipped, instructions count: 144
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0HR.A7D():long");
    }

    @Override // X.AnonymousClass0Od
    public final void A7R(long j) throws EOFException {
        if (this.A00 < j) {
            throw new EOFException();
        }
    }

    @Override // X.AnonymousClass0Od
    public final void A8T(long j) throws EOFException {
        while (j > 0) {
            C04570h3 r6 = this.A01;
            if (r6 != null) {
                int i = r6.A01;
                int i2 = r6.A02;
                int min = (int) Math.min(j, (long) (i - i2));
                long j2 = (long) min;
                this.A00 -= j2;
                j -= j2;
                int i3 = i2 + min;
                r6.A02 = i3;
                if (i3 == i) {
                    this.A01 = r6.A00();
                    C04560h2.A01(r6);
                }
            } else {
                throw new EOFException();
            }
        }
    }

    @Override // X.AnonymousClass0Oe
    public final /* bridge */ /* synthetic */ AnonymousClass0Oe A8v(C04610h7 r3) throws IOException {
        if (r3 != null) {
            r3.A0F(this);
            return this;
        }
        throw new IllegalArgumentException("byteString == null");
    }

    @Override // X.AnonymousClass0Oe
    public final long A8y(AbstractC04550h0 r8) throws IOException {
        if (r8 != null) {
            long j = 0;
            while (true) {
                long read = r8.read(this, 8192);
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
        AnonymousClass0HR r7 = new AnonymousClass0HR();
        long j = this.A00;
        if (j != 0) {
            C04570h3 A012 = this.A01.A01();
            r7.A01 = A012;
            A012.A03 = A012;
            A012.A00 = A012;
            C04570h3 r3 = this.A01;
            while (true) {
                r3 = r3.A00;
                if (r3 == r3) {
                    break;
                }
                A012.A03.A02(r3.A01());
            }
            r7.A00 = j;
        }
        return r7;
    }

    public final boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof AnonymousClass0HR) {
                AnonymousClass0HR r3 = (AnonymousClass0HR) obj;
                long j = this.A00;
                if (j == r3.A00) {
                    long j2 = 0;
                    if (j != 0) {
                        C04570h3 r9 = this.A01;
                        C04570h3 r8 = r3.A01;
                        int i = r9.A02;
                        int i2 = r8.A02;
                        while (j2 < j) {
                            long min = (long) Math.min(r9.A01 - i, r8.A01 - i2);
                            int i3 = 0;
                            while (((long) i3) < min) {
                                int i4 = i + 1;
                                int i5 = i2 + 1;
                                if (r9.A06[i] == r8.A06[i2]) {
                                    i3++;
                                    i = i4;
                                    i2 = i5;
                                }
                            }
                            if (i == r9.A01) {
                                r9 = r9.A00;
                                i = r9.A02;
                            }
                            if (i2 == r8.A01) {
                                r8 = r8.A00;
                                i2 = r8.A02;
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
        C04570h3 r5 = this.A01;
        if (r5 == null) {
            return 0;
        }
        int i = 1;
        do {
            int i2 = r5.A01;
            for (int i3 = r5.A02; i3 < i2; i3++) {
                i = (i * 31) + r5.A06[i3];
            }
            r5 = r5.A00;
        } while (r5 != r5);
        return i;
    }

    @Override // X.AnonymousClass0Od
    public final byte readByte() {
        long j = this.A00;
        if (j != 0) {
            C04570h3 r7 = this.A01;
            int i = r7.A02;
            int i2 = r7.A01;
            int i3 = i + 1;
            byte b = r7.A06[i];
            this.A00 = j - 1;
            if (i3 == i2) {
                this.A01 = r7.A00();
                C04560h2.A01(r7);
                return b;
            }
            r7.A02 = i3;
            return b;
        }
        throw new IllegalStateException("size == 0");
    }

    @Override // X.AnonymousClass0Od
    public final int readInt() {
        long j = this.A00;
        if (j >= 4) {
            C04570h3 r5 = this.A01;
            int i = r5.A02;
            int i2 = r5.A01;
            if (i2 - i < 4) {
                return ((readByte() & 255) << 24) | ((readByte() & 255) << 16) | ((readByte() & 255) << 8) | (readByte() & 255);
            }
            byte[] bArr = r5.A06;
            int i3 = i + 1;
            int i4 = i3 + 1;
            int i5 = i4 + 1;
            int i6 = i5 + 1;
            int i7 = ((bArr[i] & 255) << 24) | ((bArr[i3] & 255) << 16) | ((bArr[i4] & 255) << 8) | (bArr[i5] & 255);
            this.A00 = j - 4;
            if (i6 == i2) {
                this.A01 = r5.A00();
                C04560h2.A01(r5);
                return i7;
            }
            r5.A02 = i6;
            return i7;
        }
        throw new IllegalStateException(AnonymousClass006.A04("size < 4: ", j));
    }

    @Override // X.AnonymousClass0Od
    public final short readShort() {
        long j = this.A00;
        if (j >= 2) {
            C04570h3 r8 = this.A01;
            int i = r8.A02;
            int i2 = r8.A01;
            if (i2 - i < 2) {
                return (short) (((readByte() & 255) << 8) | (readByte() & 255));
            }
            byte[] bArr = r8.A06;
            int i3 = i + 1;
            int i4 = i3 + 1;
            int i5 = ((bArr[i] & 255) << 8) | (bArr[i3] & 255);
            this.A00 = j - 2;
            if (i4 == i2) {
                this.A01 = r8.A00();
                C04560h2.A01(r8);
            } else {
                r8.A02 = i4;
            }
            return (short) i5;
        }
        throw new IllegalStateException(AnonymousClass006.A04("size < 2: ", j));
    }

    @Override // X.AnonymousClass0h1, X.AbstractC04550h0
    public final C04540gz timeout() {
        return C04540gz.NONE;
    }

    public final void A0F(String str) {
        A0G(str, 0, str.length());
    }

    @Override // X.AnonymousClass0Od
    public final C04610h7 A7B(long j) throws EOFException {
        return new C04610h7(A7A(j));
    }

    @Override // X.AnonymousClass0Od
    public final int A7E() {
        int readInt = readInt();
        return ((readInt & 255) << 24) | ((-16777216 & readInt) >>> 24) | ((16711680 & readInt) >>> 8) | ((65280 & readInt) << 8);
    }

    @Override // X.AnonymousClass0Od
    public final short A7H() {
        int readShort = readShort() & 65535;
        return (short) (((readShort & 255) << 8) | ((65280 & readShort) >>> 8));
    }

    public final String toString() {
        return A06().toString();
    }

    @Override // X.AnonymousClass0Oe
    public final /* bridge */ /* synthetic */ AnonymousClass0Oe A8w(byte[] bArr) throws IOException {
        A0I(bArr);
        return this;
    }

    @Override // X.AnonymousClass0Oe
    public final /* bridge */ /* synthetic */ AnonymousClass0Oe A91(int i) throws IOException {
        A09(i);
        return this;
    }

    @Override // X.AnonymousClass0Oe
    public final /* bridge */ /* synthetic */ AnonymousClass0Oe A92(long j) throws IOException {
        A0D(j);
        return this;
    }

    @Override // X.AnonymousClass0Oe
    public final /* bridge */ /* synthetic */ AnonymousClass0Oe A95(long j) throws IOException {
        A0E(j);
        return this;
    }

    @Override // X.AnonymousClass0Oe
    public final /* bridge */ /* synthetic */ AnonymousClass0Oe A97(int i) throws IOException {
        A0A(i);
        return this;
    }

    @Override // X.AnonymousClass0Oe
    public final /* bridge */ /* synthetic */ AnonymousClass0Oe A9C(int i) throws IOException {
        A0B(i);
        return this;
    }

    @Override // X.AnonymousClass0Oe
    public final /* bridge */ /* synthetic */ AnonymousClass0Oe A9F(String str) throws IOException {
        A0F(str);
        return this;
    }

    @Override // X.AnonymousClass0Od
    public final String A7I() throws EOFException {
        long A03 = A03((byte) 10, 0);
        if (A03 != -1) {
            return A05(A03);
        }
        if (Long.MAX_VALUE < this.A00 && A01(9223372036854775806L) == 13 && A01(Long.MAX_VALUE) == 10) {
            return A05(Long.MAX_VALUE);
        }
        AnonymousClass0HR r8 = new AnonymousClass0HR();
        A0H(r8, 0, Math.min(32L, this.A00));
        throw new EOFException("\\n not found: limit=" + Math.min(this.A00, Long.MAX_VALUE) + " content=" + new C04610h7(r8.A79()).A09() + (char) 8230);
    }

    @Override // X.AnonymousClass0Oe
    public final /* bridge */ /* synthetic */ AnonymousClass0Oe A8x(byte[] bArr, int i, int i2) throws IOException {
        A0J(bArr, i, i2);
        return this;
    }

    @Override // java.nio.channels.ReadableByteChannel
    public final int read(ByteBuffer byteBuffer) throws IOException {
        C04570h3 r6 = this.A01;
        if (r6 == null) {
            return -1;
        }
        int remaining = byteBuffer.remaining();
        int i = r6.A01;
        int i2 = r6.A02;
        int min = Math.min(remaining, i - i2);
        byteBuffer.put(r6.A06, i2, min);
        int i3 = r6.A02 + min;
        r6.A02 = i3;
        this.A00 -= (long) min;
        if (i3 == r6.A01) {
            this.A01 = r6.A00();
            C04560h2.A01(r6);
        }
        return min;
    }

    @Override // X.AbstractC04550h0
    public final long read(AnonymousClass0HR r6, long j) {
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
            str = AnonymousClass006.A04("byteCount < 0: ", j);
        }
        throw new IllegalArgumentException(str);
    }

    @Override // X.AnonymousClass0h1
    public final void write(AnonymousClass0HR r11, long j) {
        String str;
        int i;
        C04570h3 r5;
        int i2;
        long j2 = j;
        if (r11 == null) {
            str = "source == null";
        } else if (r11 != this) {
            C04530gy.A00(r11.A00, 0, j2);
            while (j2 > 0) {
                C04570h3 r6 = r11.A01;
                C04570h3 r4 = r6;
                int i3 = r6.A01 - r6.A02;
                if (j2 < ((long) i3)) {
                    C04570h3 r0 = this.A01;
                    if (!(r0 == null || (r5 = r0.A03) == null || !r5.A04)) {
                        long j3 = ((long) r5.A01) + j2;
                        if (r5.A05) {
                            i2 = 0;
                        } else {
                            i2 = r5.A02;
                        }
                        if (j3 - ((long) i2) <= 8192) {
                            r6.A03(r5, (int) j2);
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
                        r4 = r6.A01();
                    } else {
                        r4 = C04560h2.A00();
                        System.arraycopy(r6.A06, r6.A02, r4.A06, 0, i4);
                    }
                    r4.A01 = r4.A02 + i4;
                    r6.A02 += i4;
                    r6.A03.A02(r4);
                    r11.A01 = r4;
                }
                int i5 = r4.A01 - r4.A02;
                long j4 = (long) i5;
                r11.A01 = r4.A00();
                C04570h3 r02 = this.A01;
                if (r02 == null) {
                    this.A01 = r4;
                    r4.A03 = r4;
                    r4.A00 = r4;
                } else {
                    r02.A03.A02(r4);
                    C04570h3 r52 = r4.A03;
                    if (r52 == r4) {
                        throw new IllegalStateException();
                    } else if (r52.A04) {
                        int i6 = 8192 - r52.A01;
                        if (r52.A05) {
                            i = 0;
                        } else {
                            i = r52.A02;
                        }
                        if (i5 <= i6 + i) {
                            r4.A03(r52, i5);
                            r4.A00();
                            C04560h2.A01(r4);
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
                C04570h3 A07 = A07(1);
                int i2 = A07.A01;
                int min = Math.min(i, 8192 - i2);
                byteBuffer.get(A07.A06, i2, min);
                i -= min;
                A07.A01 += min;
            }
            this.A00 += (long) remaining;
            return remaining;
        }
        throw new IllegalArgumentException("source == null");
    }
}
