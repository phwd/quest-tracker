package X;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/* renamed from: X.0Au  reason: invalid class name and case insensitive filesystem */
public final class C00560Au implements AnonymousClass0Lw {
    public boolean A00;
    public final AnonymousClass0B3 A01 = new AnonymousClass0B3();
    public final AbstractC07630v6 A02;

    private final long A00(byte b) throws IOException {
        long j = 0;
        if (this.A00) {
            throw new IllegalStateException("closed");
        }
        while (j < Long.MAX_VALUE) {
            AnonymousClass0B3 r7 = this.A01;
            long A03 = r7.A03(b, j);
            if (A03 == -1) {
                long j2 = r7.A00;
                if (j2 >= Long.MAX_VALUE || this.A02.read(r7, 8192) == -1) {
                    break;
                }
                j = Math.max(j, j2);
            } else {
                return A03;
            }
        }
        return -1;
    }

    private final boolean A01(long j) throws IOException {
        AnonymousClass0B3 r3;
        if (j < 0) {
            throw new IllegalArgumentException(AnonymousClass006.A04("byteCount < 0: ", j));
        } else if (this.A00) {
            throw new IllegalStateException("closed");
        } else {
            do {
                r3 = this.A01;
                if (r3.A00 >= j) {
                    return true;
                }
            } while (this.A02.read(r3, 8192) != -1);
            return false;
        }
    }

    @Override // X.AnonymousClass0Lw
    public final boolean A2a() throws IOException {
        if (!this.A00) {
            AnonymousClass0B3 r3 = this.A01;
            if (!r3.A2a() || this.A02.read(r3, 8192) != -1) {
                return false;
            }
            return true;
        }
        throw new IllegalStateException("closed");
    }

    @Override // X.AnonymousClass0Lw
    public final InputStream A4q() {
        return new C07670vA(this);
    }

    @Override // X.AnonymousClass0Lw
    public final byte[] A7r() throws IOException {
        AnonymousClass0B3 r1 = this.A01;
        r1.AA9(this.A02);
        return r1.A7r();
    }

    @Override // X.AnonymousClass0Lw
    public final long A7u() throws IOException {
        A8I(1);
        int i = 0;
        while (true) {
            int i2 = i + 1;
            if (!A01((long) i2)) {
                break;
            }
            byte A012 = this.A01.A01((long) i);
            if (A012 < 48 || A012 > 57) {
                if (i != 0) {
                    break;
                } else if (A012 != 45) {
                    throw new NumberFormatException(String.format("Expected leading [0-9] or '-' character but was %#x", Byte.valueOf(A012)));
                }
            }
            i = i2;
        }
        return this.A01.A7u();
    }

    @Override // X.AnonymousClass0Lw
    public final long A7v() throws IOException {
        A8I(1);
        int i = 0;
        while (true) {
            int i2 = i + 1;
            if (!A01((long) i2)) {
                break;
            }
            byte A012 = this.A01.A01((long) i);
            if ((A012 >= 48 && A012 <= 57) || ((A012 >= 97 && A012 <= 102) || (A012 >= 65 && A012 <= 70))) {
                i = i2;
            } else if (i == 0) {
                throw new NumberFormatException(String.format("Expected leading [0-9a-fA-F] character but was %#x", Byte.valueOf(A012)));
            }
        }
        return this.A01.A7v();
    }

    @Override // X.AnonymousClass0Lw
    public final int A7w() throws IOException {
        A8I(4);
        return this.A01.A7w();
    }

    @Override // X.AnonymousClass0Lw
    public final short A7z() throws IOException {
        A8I(2);
        return this.A01.A7z();
    }

    @Override // X.AnonymousClass0Lw
    public final String A80(Charset charset) throws IOException {
        if (charset != null) {
            AnonymousClass0B3 r1 = this.A01;
            r1.AA9(this.A02);
            return r1.A80(charset);
        }
        throw new IllegalArgumentException("charset == null");
    }

    @Override // X.AnonymousClass0Lw
    public final void A94(long j) throws IOException {
        if (this.A00) {
            throw new IllegalStateException("closed");
        }
        while (j > 0) {
            AnonymousClass0B3 r5 = this.A01;
            if (r5.A00 == 0 && this.A02.read(r5, 8192) == -1) {
                throw new EOFException();
            }
            long min = Math.min(j, r5.A00);
            r5.A94(min);
            j -= min;
        }
    }

    @Override // X.AbstractC07630v6, java.io.Closeable, java.lang.AutoCloseable, java.nio.channels.Channel
    public final void close() throws IOException {
        if (!this.A00) {
            this.A00 = true;
            this.A02.close();
            this.A01.A08();
        }
    }

    public final boolean isOpen() {
        return !this.A00;
    }

    @Override // X.AnonymousClass0Lw
    public final byte readByte() throws IOException {
        A8I(1);
        return this.A01.readByte();
    }

    @Override // X.AnonymousClass0Lw
    public final int readInt() throws IOException {
        A8I(4);
        return this.A01.readInt();
    }

    @Override // X.AnonymousClass0Lw
    public final short readShort() throws IOException {
        A8I(2);
        return this.A01.readShort();
    }

    @Override // X.AbstractC07630v6
    public final C07620v5 timeout() {
        return this.A02.timeout();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("buffer(");
        sb.append(this.A02);
        sb.append(")");
        return sb.toString();
    }

    public C00560Au(AbstractC07630v6 r3) {
        if (r3 != null) {
            this.A02 = r3;
            return;
        }
        throw new NullPointerException("source == null");
    }

    @Override // X.AnonymousClass0Lw
    public final AnonymousClass0B3 A1V() {
        return this.A01;
    }

    @Override // X.AnonymousClass0Lw
    public final long A4n(byte b) throws IOException {
        return A00(b);
    }

    @Override // X.AnonymousClass0Lw
    public final boolean A7n(long j, C07700vD r11) throws IOException {
        int A07 = r11.A07();
        if (this.A00) {
            throw new IllegalStateException("closed");
        } else if (j < 0 || A07 < 0 || A07 - 0 < A07) {
            return false;
        } else {
            for (int i = 0; i < A07; i++) {
                long j2 = ((long) i) + j;
                if (!A01(1 + j2) || this.A01.A01(j2) != r11.A06(0 + i)) {
                    return false;
                }
            }
            return true;
        }
    }

    @Override // X.AnonymousClass0Lw
    public final byte[] A7s(long j) throws IOException {
        A8I(j);
        return this.A01.A7s(j);
    }

    @Override // X.AnonymousClass0Lw
    public final C07700vD A7t(long j) throws IOException {
        A8I(j);
        return this.A01.A7t(j);
    }

    @Override // X.AnonymousClass0Lw
    public final void A8I(long j) throws IOException {
        if (!A01(j)) {
            throw new EOFException();
        }
    }

    @Override // X.AnonymousClass0Lw
    public final String A81() throws IOException {
        long A002 = A00((byte) 10);
        if (A002 != -1) {
            return this.A01.A05(A002);
        }
        AnonymousClass0B3 r7 = new AnonymousClass0B3();
        AnonymousClass0B3 r6 = this.A01;
        r6.A0H(r7, 0, Math.min(32L, r6.A00));
        StringBuilder sb = new StringBuilder("\\n not found: limit=");
        sb.append(Math.min(r6.A00, Long.MAX_VALUE));
        sb.append(" content=");
        sb.append(new C07700vD(r7.A7r()).A09());
        sb.append((char) 8230);
        throw new EOFException(sb.toString());
    }

    @Override // java.nio.channels.ReadableByteChannel
    public final int read(ByteBuffer byteBuffer) throws IOException {
        AnonymousClass0B3 r5 = this.A01;
        if (r5.A00 == 0 && this.A02.read(r5, 8192) == -1) {
            return -1;
        }
        return r5.read(byteBuffer);
    }

    @Override // X.AbstractC07630v6
    public final long read(AnonymousClass0B3 r7, long j) throws IOException {
        String str;
        if (r7 == null) {
            str = "sink == null";
        } else if (j < 0) {
            str = AnonymousClass006.A04("byteCount < 0: ", j);
        } else if (!this.A00) {
            AnonymousClass0B3 r5 = this.A01;
            if (r5.A00 == 0 && this.A02.read(r5, 8192) == -1) {
                return -1;
            }
            return r5.read(r7, Math.min(j, r5.A00));
        } else {
            throw new IllegalStateException("closed");
        }
        throw new IllegalArgumentException(str);
    }
}
