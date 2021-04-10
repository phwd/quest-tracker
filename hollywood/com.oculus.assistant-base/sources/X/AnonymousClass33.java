package X;

import com.facebook.acra.ErrorReporter;
import com.facebook.assistant.oacr.OacrConstants;
import com.facebook.proxygen.HTTPTransportCallback;
import java.io.EOFException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.charset.Charset;

/* renamed from: X.33  reason: invalid class name */
public final class AnonymousClass33 implements t4, t6, Cloneable, ByteChannel {
    public static final byte[] A02 = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102};
    public long A00;
    public C0606cp A01;

    public final byte A01(long j) {
        C0606cp cpVar;
        int i;
        long j2 = j;
        C0611cu.A00(this.A00, j2, 1);
        long j3 = this.A00;
        if (j3 - j > j) {
            cpVar = this.A01;
            while (true) {
                int i2 = cpVar.A00;
                i = cpVar.A01;
                long j4 = (long) (i2 - i);
                if (j2 < j4) {
                    break;
                }
                j2 -= j4;
                cpVar = cpVar.A02;
            }
        } else {
            j2 = j - j3;
            cpVar = this.A01;
            do {
                cpVar = cpVar.A03;
                int i3 = cpVar.A00;
                i = cpVar.A01;
                j2 += (long) (i3 - i);
            } while (j2 < 0);
        }
        return cpVar.A06[i + ((int) j2)];
    }

    public final int A02(byte[] bArr, int i, int i2) {
        C0611cu.A00((long) bArr.length, (long) i, (long) i2);
        C0606cp cpVar = this.A01;
        if (cpVar == null) {
            return -1;
        }
        int i3 = cpVar.A00;
        int i4 = cpVar.A01;
        int min = Math.min(i2, i3 - i4);
        System.arraycopy(cpVar.A06, i4, bArr, i, min);
        int i5 = cpVar.A01 + min;
        cpVar.A01 = i5;
        this.A00 -= (long) min;
        if (i5 == cpVar.A00) {
            this.A01 = cpVar.A00();
            C0607cq.A01(cpVar);
        }
        return min;
    }

    public final C0606cp A06(int i) {
        if (i < 1 || i > 8192) {
            throw new IllegalArgumentException();
        }
        C0606cp cpVar = this.A01;
        if (cpVar == null) {
            C0606cp A002 = C0607cq.A00();
            this.A01 = A002;
            A002.A03 = A002;
            A002.A02 = A002;
            return A002;
        }
        C0606cp cpVar2 = cpVar.A03;
        if (cpVar2.A00 + i <= 8192 && cpVar2.A04) {
            return cpVar2;
        }
        C0606cp A003 = C0607cq.A00();
        cpVar2.A02(A003);
        return A003;
    }

    public final void A08(int i) {
        C0606cp A06 = A06(1);
        byte[] bArr = A06.A06;
        int i2 = A06.A00;
        A06.A00 = i2 + 1;
        bArr[i2] = (byte) i;
        this.A00++;
    }

    public final void A09(int i) {
        C0606cp A06 = A06(4);
        byte[] bArr = A06.A06;
        int i2 = A06.A00;
        int i3 = i2 + 1;
        bArr[i2] = (byte) ((i >>> 24) & 255);
        int i4 = i3 + 1;
        bArr[i3] = (byte) ((i >>> 16) & 255);
        int i5 = i4 + 1;
        bArr[i4] = (byte) ((i >>> 8) & 255);
        bArr[i5] = (byte) (i & 255);
        A06.A00 = i5 + 1;
        this.A00 += 4;
    }

    public final void A0A(int i) {
        C0606cp A06 = A06(2);
        byte[] bArr = A06.A06;
        int i2 = A06.A00;
        int i3 = i2 + 1;
        bArr[i2] = (byte) ((i >>> 8) & 255);
        bArr[i3] = (byte) (i & 255);
        A06.A00 = i3 + 1;
        this.A00 += 2;
    }

    public final void A0E(AnonymousClass33 r12, long j, long j2) {
        long j3 = j2;
        long j4 = j;
        if (r12 != null) {
            C0611cu.A00(this.A00, j4, j3);
            if (j2 != 0) {
                r12.A00 += j2;
                C0606cp cpVar = this.A01;
                while (j4 >= ((long) (cpVar.A00 - cpVar.A01))) {
                    j4 -= (long) (cpVar.A00 - cpVar.A01);
                    cpVar = cpVar.A02;
                }
                while (j3 > 0) {
                    C0606cp A012 = cpVar.A01();
                    int i = (int) (((long) A012.A01) + j4);
                    A012.A01 = i;
                    A012.A00 = Math.min(i + ((int) j3), A012.A00);
                    C0606cp cpVar2 = r12.A01;
                    if (cpVar2 == null) {
                        A012.A03 = A012;
                        A012.A02 = A012;
                        r12.A01 = A012;
                    } else {
                        cpVar2.A03.A02(A012);
                    }
                    j3 -= (long) (A012.A00 - A012.A01);
                    cpVar = cpVar.A02;
                    j4 = 0;
                }
                return;
            }
            return;
        }
        throw new IllegalArgumentException("out == null");
    }

    @Override // X.t6, X.t4
    public final AnonymousClass33 A1M() {
        return this;
    }

    @Override // X.AbstractC0608cr
    public final void A5k(AnonymousClass33 r11, long j) {
        int i;
        C0606cp cpVar;
        int i2;
        long j2 = j;
        if (r11 == null) {
            throw new IllegalArgumentException("source == null");
        } else if (r11 != this) {
            C0611cu.A00(r11.A00, 0, j2);
            while (j2 > 0) {
                C0606cp cpVar2 = r11.A01;
                C0606cp cpVar3 = cpVar2;
                int i3 = cpVar2.A00 - cpVar2.A01;
                if (j2 < ((long) i3)) {
                    C0606cp cpVar4 = this.A01;
                    if (!(cpVar4 == null || (cpVar = cpVar4.A03) == null || !cpVar.A04)) {
                        long j3 = ((long) cpVar.A00) + j2;
                        if (cpVar.A05) {
                            i2 = 0;
                        } else {
                            i2 = cpVar.A01;
                        }
                        if (j3 - ((long) i2) <= 8192) {
                            cpVar2.A03(cpVar, (int) j2);
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
                        cpVar3 = cpVar2.A01();
                    } else {
                        cpVar3 = C0607cq.A00();
                        System.arraycopy(cpVar2.A06, cpVar2.A01, cpVar3.A06, 0, i4);
                    }
                    cpVar3.A00 = cpVar3.A01 + i4;
                    cpVar2.A01 += i4;
                    cpVar2.A03.A02(cpVar3);
                    r11.A01 = cpVar3;
                }
                int i5 = cpVar3.A00 - cpVar3.A01;
                long j4 = (long) i5;
                r11.A01 = cpVar3.A00();
                C0606cp cpVar5 = this.A01;
                if (cpVar5 == null) {
                    this.A01 = cpVar3;
                    cpVar3.A03 = cpVar3;
                    cpVar3.A02 = cpVar3;
                } else {
                    cpVar5.A03.A02(cpVar3);
                    C0606cp cpVar6 = cpVar3.A03;
                    if (cpVar6 == cpVar3) {
                        throw new IllegalStateException();
                    } else if (cpVar6.A04) {
                        int i6 = 8192 - cpVar6.A00;
                        if (cpVar6.A05) {
                            i = 0;
                        } else {
                            i = cpVar6.A01;
                        }
                        if (i5 <= i6 + i) {
                            cpVar3.A03(cpVar6, i5);
                            cpVar3.A00();
                            C0607cq.A01(cpVar3);
                        }
                    }
                }
                r11.A00 -= j4;
                this.A00 += j4;
                j2 -= j4;
            }
        } else {
            throw new IllegalArgumentException("source == this");
        }
    }

    @Override // java.lang.AutoCloseable, X.AbstractC0608cr, X.AbstractC0609cs, java.io.Closeable, java.nio.channels.Channel
    public final void close() {
    }

    @Override // X.AbstractC0608cr, X.t6, java.io.Flushable
    public final void flush() {
    }

    public final boolean isOpen() {
        return true;
    }

    private final String A00(long j, Charset charset) {
        C0611cu.A00(this.A00, 0, j);
        if (charset == null) {
            throw new IllegalArgumentException("charset == null");
        } else if (j > 2147483647L) {
            throw new IllegalArgumentException(AnonymousClass08.A03("byteCount > Integer.MAX_VALUE: ", j));
        } else if (j == 0) {
            return OacrConstants.AUTO_SPEECH_DOMAIN;
        } else {
            C0606cp cpVar = this.A01;
            int i = cpVar.A01;
            if (((long) i) + j > ((long) cpVar.A00)) {
                return new String(A4e(j), charset);
            }
            String str = new String(cpVar.A06, i, (int) j, charset);
            int i2 = (int) (((long) cpVar.A01) + j);
            cpVar.A01 = i2;
            this.A00 -= j;
            if (i2 == cpVar.A00) {
                this.A01 = cpVar.A00();
                C0607cq.A01(cpVar);
            }
            return str;
        }
    }

    public final long A03(byte b, long j) {
        C0606cp cpVar;
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
        if (!(j == j4 || (cpVar = this.A01) == null)) {
            if (j5 - j >= j) {
                while (true) {
                    j5 = j3;
                    j3 = ((long) (cpVar.A00 - cpVar.A01)) + j5;
                    if (j3 >= j) {
                        break;
                    }
                    cpVar = cpVar.A02;
                }
            } else {
                while (j5 > j) {
                    cpVar = cpVar.A03;
                    j5 -= (long) (cpVar.A00 - cpVar.A01);
                }
            }
            while (j5 < j4) {
                byte[] bArr = cpVar.A06;
                int i = cpVar.A00;
                int i2 = cpVar.A01;
                long j6 = (long) i2;
                int min = (int) Math.min((long) i, (j6 + j4) - j5);
                for (int i3 = (int) ((j6 + j2) - j5); i3 < min; i3++) {
                    if (bArr[i3] == b) {
                        return ((long) (i3 - i2)) + j5;
                    }
                }
                j2 = ((long) (i - i2)) + j5;
                cpVar = cpVar.A02;
                j5 = j2;
            }
        }
        return -1;
    }

    public final String A04() {
        try {
            return A00(this.A00, C0611cu.A00);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    public final String A05(long j) {
        String A002;
        long j2 = 1;
        if (j > 0) {
            long j3 = j - 1;
            if (A01(j3) == 13) {
                A002 = A00(j3, C0611cu.A00);
                j2 = 2;
                A55(j2);
                return A002;
            }
        }
        A002 = A00(j, C0611cu.A00);
        A55(j2);
        return A002;
    }

    public final void A07() {
        try {
            A55(this.A00);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    public final void A0B(int i) {
        int i2;
        if (i >= 128) {
            int i3 = i >> 6;
            int i4 = i3 | 192;
            if (i >= 2048) {
                if (i < 65536) {
                    if (i < 55296 || i > 57343) {
                        i2 = (i >> 12) | 224;
                    } else {
                        A08(63);
                        return;
                    }
                } else if (i <= 1114111) {
                    A08((i >> 18) | 240);
                    i2 = ((i >> 12) & 63) | HTTPTransportCallback.BODY_BYTES_RECEIVED;
                } else {
                    throw new IllegalArgumentException(AnonymousClass08.A04("Unexpected code point: ", Integer.toHexString(i)));
                }
                A08(i2);
                i4 = (i3 & 63) | HTTPTransportCallback.BODY_BYTES_RECEIVED;
            }
            A08(i4);
            i = (i & 63) | HTTPTransportCallback.BODY_BYTES_RECEIVED;
        }
        A08(i);
    }

    public final void A0C(long j) {
        if (j == 0) {
            A08(48);
            return;
        }
        int numberOfTrailingZeros = (Long.numberOfTrailingZeros(Long.highestOneBit(j)) / 4) + 1;
        C0606cp A06 = A06(numberOfTrailingZeros);
        byte[] bArr = A06.A06;
        int i = A06.A00;
        int i2 = i + numberOfTrailingZeros;
        while (true) {
            i2--;
            if (i2 >= i) {
                bArr[i2] = A02[(int) (15 & j)];
                j >>>= 4;
            } else {
                A06.A00 = i2;
                this.A00 += (long) numberOfTrailingZeros;
                return;
            }
        }
    }

    public final void A0D(String str, int i, int i2) {
        char c;
        if (str == null) {
            throw new IllegalArgumentException("string == null");
        } else if (i < 0) {
            throw new IllegalArgumentException(AnonymousClass08.A00("beginIndex < 0: ", i));
        } else if (i2 >= i) {
            int length = str.length();
            if (i2 > length) {
                throw new IllegalArgumentException(AnonymousClass08.A02("endIndex > string.length: ", i2, " > ", length));
            }
            while (i < i2) {
                char charAt = str.charAt(i);
                if (charAt < 128) {
                    C0606cp A06 = A06(1);
                    byte[] bArr = A06.A06;
                    int i3 = A06.A00 - i;
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
                    int i5 = A06.A00;
                    int i6 = (i3 + i4) - i5;
                    A06.A00 = i5 + i6;
                    this.A00 += (long) i6;
                    i = i4;
                } else {
                    int i7 = (charAt >> 6) | 192;
                    if (charAt >= 2048) {
                        if (charAt < 55296 || charAt > 57343) {
                            A08((charAt >> '\f') | 224);
                            i7 = ((charAt >> 6) & 63) | HTTPTransportCallback.BODY_BYTES_RECEIVED;
                        } else {
                            int i8 = i + 1;
                            if (i8 < i2) {
                                c = str.charAt(i8);
                            } else {
                                c = 0;
                            }
                            if (charAt > 56319 || c < 56320 || c > 57343) {
                                A08(63);
                                i = i8;
                            } else {
                                int i9 = (((charAt & 10239) << 10) | (9215 & c)) + ErrorReporter.DEFAULT_OOM_RESERVATION;
                                A08((i9 >> 18) | 240);
                                A08(((i9 >> 12) & 63) | HTTPTransportCallback.BODY_BYTES_RECEIVED);
                                A08(((i9 >> 6) & 63) | HTTPTransportCallback.BODY_BYTES_RECEIVED);
                                A08((i9 & 63) | HTTPTransportCallback.BODY_BYTES_RECEIVED);
                                i += 2;
                            }
                        }
                    }
                    A08(i7);
                    A08((charAt & '?') | HTTPTransportCallback.BODY_BYTES_RECEIVED);
                    i++;
                }
            }
        } else {
            throw new IllegalArgumentException(AnonymousClass08.A02("endIndex < beginIndex: ", i2, " < ", i));
        }
    }

    public final void A0F(byte[] bArr, int i, int i2) {
        if (bArr != null) {
            long j = (long) i2;
            C0611cu.A00((long) bArr.length, (long) i, j);
            int i3 = i2 + i;
            while (i < i3) {
                C0606cp A06 = A06(1);
                int i4 = A06.A00;
                int min = Math.min(i3 - i, 8192 - i4);
                System.arraycopy(bArr, i, A06.A06, i4, min);
                i += min;
                A06.A00 += min;
            }
            this.A00 += j;
            return;
        }
        throw new IllegalArgumentException("source == null");
    }

    @Override // X.t4
    public final boolean A1r() {
        if (this.A00 == 0) {
            return true;
        }
        return false;
    }

    @Override // X.t4
    public final long A3I(byte b) {
        return A03(b, 0);
    }

    @Override // X.t4
    public final InputStream A3K() {
        return new C0602cl(this);
    }

    @Override // X.AbstractC0609cs
    public final long A4c(AnonymousClass33 r6, long j) {
        if (r6 == null) {
            throw new IllegalArgumentException("sink == null");
        } else if (j >= 0) {
            long j2 = this.A00;
            if (j2 == 0) {
                return -1;
            }
            if (j > j2) {
                j = j2;
            }
            r6.A5k(this, j);
            return j;
        } else {
            throw new IllegalArgumentException(AnonymousClass08.A03("byteCount < 0: ", j));
        }
    }

    @Override // X.t4
    public final byte[] A4d() {
        try {
            return A4e(this.A00);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    @Override // X.t4
    public final byte[] A4e(long j) {
        C0611cu.A00(this.A00, 0, j);
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
        throw new IllegalArgumentException(AnonymousClass08.A03("byteCount > Integer.MAX_VALUE: ", j));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002a, code lost:
        if (r10 > 102) goto L_0x002c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0020, code lost:
        if (r10 > 57) goto L_0x0022;
     */
    @Override // X.t4
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final long A4g() {
        /*
        // Method dump skipped, instructions count: 153
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass33.A4g():long");
    }

    @Override // X.t4
    public final void A4p(long j) {
        if (this.A00 < j) {
            throw new EOFException();
        }
    }

    @Override // X.t4
    public final void A55(long j) {
        while (j > 0) {
            C0606cp cpVar = this.A01;
            if (cpVar != null) {
                int i = cpVar.A00;
                int i2 = cpVar.A01;
                int min = (int) Math.min(j, (long) (i - i2));
                long j2 = (long) min;
                this.A00 -= j2;
                j -= j2;
                int i3 = i2 + min;
                cpVar.A01 = i3;
                if (i3 == i) {
                    this.A01 = cpVar.A00();
                    C0607cq.A01(cpVar);
                }
            } else {
                throw new EOFException();
            }
        }
    }

    @Override // X.t6
    public final /* bridge */ /* synthetic */ t6 A5i(byte[] bArr) {
        if (bArr != null) {
            A0F(bArr, 0, bArr.length);
            return this;
        }
        throw new IllegalArgumentException("source == null");
    }

    @Override // java.lang.Object
    public final Object clone() {
        AnonymousClass33 r7 = new AnonymousClass33();
        long j = this.A00;
        if (j != 0) {
            C0606cp A012 = this.A01.A01();
            r7.A01 = A012;
            A012.A03 = A012;
            A012.A02 = A012;
            C0606cp cpVar = this.A01;
            while (true) {
                cpVar = cpVar.A02;
                if (cpVar == cpVar) {
                    break;
                }
                A012.A03.A02(cpVar.A01());
            }
            r7.A00 = j;
        }
        return r7;
    }

    public final boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof AnonymousClass33) {
                AnonymousClass33 r3 = (AnonymousClass33) obj;
                long j = this.A00;
                if (j == r3.A00) {
                    long j2 = 0;
                    if (j != 0) {
                        C0606cp cpVar = this.A01;
                        C0606cp cpVar2 = r3.A01;
                        int i = cpVar.A01;
                        int i2 = cpVar2.A01;
                        while (j2 < j) {
                            long min = (long) Math.min(cpVar.A00 - i, cpVar2.A00 - i2);
                            int i3 = 0;
                            while (((long) i3) < min) {
                                int i4 = i + 1;
                                int i5 = i2 + 1;
                                if (cpVar.A06[i] == cpVar2.A06[i2]) {
                                    i3++;
                                    i = i4;
                                    i2 = i5;
                                }
                            }
                            if (i == cpVar.A00) {
                                cpVar = cpVar.A02;
                                i = cpVar.A01;
                            }
                            if (i2 == cpVar2.A00) {
                                cpVar2 = cpVar2.A02;
                                i2 = cpVar2.A01;
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
        C0606cp cpVar = this.A01;
        if (cpVar == null) {
            return 0;
        }
        int i = 1;
        do {
            int i2 = cpVar.A00;
            for (int i3 = cpVar.A01; i3 < i2; i3++) {
                i = (i * 31) + cpVar.A06[i3];
            }
            cpVar = cpVar.A02;
        } while (cpVar != cpVar);
        return i;
    }

    @Override // java.nio.channels.ReadableByteChannel
    public final int read(ByteBuffer byteBuffer) {
        C0606cp cpVar = this.A01;
        if (cpVar == null) {
            return -1;
        }
        int remaining = byteBuffer.remaining();
        int i = cpVar.A00;
        int i2 = cpVar.A01;
        int min = Math.min(remaining, i - i2);
        byteBuffer.put(cpVar.A06, i2, min);
        int i3 = cpVar.A01 + min;
        cpVar.A01 = i3;
        this.A00 -= (long) min;
        if (i3 == cpVar.A00) {
            this.A01 = cpVar.A00();
            C0607cq.A01(cpVar);
        }
        return min;
    }

    @Override // X.t4
    public final byte readByte() {
        long j = this.A00;
        if (j != 0) {
            C0606cp cpVar = this.A01;
            int i = cpVar.A01;
            int i2 = cpVar.A00;
            int i3 = i + 1;
            byte b = cpVar.A06[i];
            this.A00 = j - 1;
            if (i3 == i2) {
                this.A01 = cpVar.A00();
                C0607cq.A01(cpVar);
                return b;
            }
            cpVar.A01 = i3;
            return b;
        }
        throw new IllegalStateException("size == 0");
    }

    @Override // X.t4
    public final int readInt() {
        long j = this.A00;
        if (j >= 4) {
            C0606cp cpVar = this.A01;
            int i = cpVar.A01;
            int i2 = cpVar.A00;
            if (i2 - i < 4) {
                return ((readByte() & 255) << 24) | ((readByte() & 255) << 16) | ((readByte() & 255) << 8) | (readByte() & 255);
            }
            byte[] bArr = cpVar.A06;
            int i3 = i + 1;
            int i4 = i3 + 1;
            int i5 = i4 + 1;
            int i6 = i5 + 1;
            int i7 = ((bArr[i] & 255) << 24) | ((bArr[i3] & 255) << 16) | ((bArr[i4] & 255) << 8) | (bArr[i5] & 255);
            this.A00 = j - 4;
            if (i6 == i2) {
                this.A01 = cpVar.A00();
                C0607cq.A01(cpVar);
                return i7;
            }
            cpVar.A01 = i6;
            return i7;
        }
        throw new IllegalStateException(AnonymousClass08.A03("size < 4: ", j));
    }

    @Override // X.t4
    public final short readShort() {
        long j = this.A00;
        if (j >= 2) {
            C0606cp cpVar = this.A01;
            int i = cpVar.A01;
            int i2 = cpVar.A00;
            if (i2 - i < 2) {
                return (short) (((readByte() & 255) << 8) | (readByte() & 255));
            }
            byte[] bArr = cpVar.A06;
            int i3 = i + 1;
            int i4 = i3 + 1;
            int i5 = ((bArr[i] & 255) << 8) | (bArr[i3] & 255);
            this.A00 = j - 2;
            if (i4 == i2) {
                this.A01 = cpVar.A00();
                C0607cq.A01(cpVar);
            } else {
                cpVar.A01 = i4;
            }
            return (short) i5;
        }
        throw new IllegalStateException(AnonymousClass08.A03("size < 2: ", j));
    }

    public final String toString() {
        Object svVar;
        long j = this.A00;
        if (j <= 2147483647L) {
            int i = (int) j;
            if (i == 0) {
                svVar = C0603cm.A03;
            } else {
                svVar = new C1108sv(this, i);
            }
            return svVar.toString();
        }
        throw new IllegalArgumentException(AnonymousClass08.A03("size > Integer.MAX_VALUE: ", j));
    }

    @Override // java.nio.channels.WritableByteChannel
    public final int write(ByteBuffer byteBuffer) {
        if (byteBuffer != null) {
            int remaining = byteBuffer.remaining();
            int i = remaining;
            while (i > 0) {
                C0606cp A06 = A06(1);
                int i2 = A06.A00;
                int min = Math.min(i, 8192 - i2);
                byteBuffer.get(A06.A06, i2, min);
                i -= min;
                A06.A00 += min;
            }
            this.A00 += (long) remaining;
            return remaining;
        }
        throw new IllegalArgumentException("source == null");
    }

    @Override // X.t4
    public final C0603cm A4f(long j) {
        return new C0603cm(A4e(j));
    }

    @Override // X.t4
    public final int A4h() {
        int readInt = readInt();
        return ((readInt & 255) << 24) | ((-16777216 & readInt) >>> 24) | ((16711680 & readInt) >>> 8) | ((65280 & readInt) << 8);
    }

    @Override // X.t4
    public final short A4k() {
        int readShort = readShort() & 65535;
        return (short) (((readShort & 255) << 8) | ((65280 & readShort) >>> 8));
    }

    @Override // X.AbstractC0608cr, X.AbstractC0609cs
    public final C0610ct A5G() {
        return C0610ct.A03;
    }

    @Override // X.t6
    public final /* bridge */ /* synthetic */ t6 A5j(byte[] bArr, int i, int i2) {
        A0F(bArr, i, i2);
        return this;
    }

    @Override // X.t6
    public final /* bridge */ /* synthetic */ t6 A5z(String str) {
        A0D(str, 0, str.length());
        return this;
    }

    @Override // X.t4
    public final String A4l() {
        long A03 = A03((byte) 10, 0);
        if (A03 != -1) {
            return A05(A03);
        }
        if (Long.MAX_VALUE < this.A00 && A01(9223372036854775806L) == 13 && A01(Long.MAX_VALUE) == 10) {
            return A05(Long.MAX_VALUE);
        }
        AnonymousClass33 r8 = new AnonymousClass33();
        A0E(r8, 0, Math.min(32L, this.A00));
        StringBuilder sb = new StringBuilder("\\n not found: limit=");
        sb.append(Math.min(this.A00, Long.MAX_VALUE));
        sb.append(" content=");
        sb.append(new C0603cm(r8.A4d()).A07());
        sb.append((char) 8230);
        throw new EOFException(sb.toString());
    }

    @Override // X.t6
    public final /* bridge */ /* synthetic */ t6 A5m(int i) {
        A08(i);
        return this;
    }

    @Override // X.t6
    public final /* bridge */ /* synthetic */ t6 A5p(long j) {
        A0C(j);
        return this;
    }

    @Override // X.t6
    public final /* bridge */ /* synthetic */ t6 A5r(int i) {
        A09(i);
        return this;
    }

    @Override // X.t6
    public final /* bridge */ /* synthetic */ t6 A5w(int i) {
        A0A(i);
        return this;
    }
}
