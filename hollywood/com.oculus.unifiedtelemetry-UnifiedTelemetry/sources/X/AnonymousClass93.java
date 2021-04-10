package X;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/* renamed from: X.93  reason: invalid class name */
public final class AnonymousClass93 implements KC {
    public boolean A00;
    public final AnonymousClass98 A01 = new AnonymousClass98();
    public final AbstractC0312cb A02;

    private final long A00(byte b) throws IOException {
        long j = 0;
        if (this.A00) {
            throw new IllegalStateException("closed");
        }
        while (j < Long.MAX_VALUE) {
            AnonymousClass98 r7 = this.A01;
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
        AnonymousClass98 r3;
        if (j < 0) {
            throw new IllegalArgumentException(AnonymousClass06.A03("byteCount < 0: ", j));
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

    @Override // X.KC
    public final boolean A1z() throws IOException {
        if (!this.A00) {
            AnonymousClass98 r3 = this.A01;
            if (!r3.A1z() || this.A02.read(r3, 8192) != -1) {
                return false;
            }
            return true;
        }
        throw new IllegalStateException("closed");
    }

    @Override // X.KC
    public final InputStream A37() {
        return new C0316cf(this);
    }

    @Override // X.KC
    public final byte[] A4S() throws IOException {
        AnonymousClass98 r1 = this.A01;
        r1.A5p(this.A02);
        return r1.A4S();
    }

    @Override // X.KC
    public final long A4V() throws IOException {
        A4l(1);
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
        return this.A01.A4V();
    }

    @Override // X.KC
    public final long A4W() throws IOException {
        A4l(1);
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
        return this.A01.A4W();
    }

    @Override // X.KC
    public final int A4X() throws IOException {
        A4l(4);
        return this.A01.A4X();
    }

    @Override // X.KC
    public final short A4a() throws IOException {
        A4l(2);
        return this.A01.A4a();
    }

    @Override // X.KC
    public final String A4b(Charset charset) throws IOException {
        if (charset != null) {
            AnonymousClass98 r1 = this.A01;
            r1.A5p(this.A02);
            return r1.A4b(charset);
        }
        throw new IllegalArgumentException("charset == null");
    }

    @Override // X.KC
    public final void A5F(long j) throws IOException {
        if (this.A00) {
            throw new IllegalStateException("closed");
        }
        while (j > 0) {
            AnonymousClass98 r5 = this.A01;
            if (r5.A00 == 0 && this.A02.read(r5, 8192) == -1) {
                throw new EOFException();
            }
            long min = Math.min(j, r5.A00);
            r5.A5F(min);
            j -= min;
        }
    }

    @Override // java.io.Closeable, X.AbstractC0312cb, java.lang.AutoCloseable, java.nio.channels.Channel
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

    @Override // X.KC
    public final byte readByte() throws IOException {
        A4l(1);
        return this.A01.readByte();
    }

    @Override // X.KC
    public final int readInt() throws IOException {
        A4l(4);
        return this.A01.readInt();
    }

    @Override // X.KC
    public final short readShort() throws IOException {
        A4l(2);
        return this.A01.readShort();
    }

    @Override // X.AbstractC0312cb
    public final ca timeout() {
        return this.A02.timeout();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("buffer(");
        sb.append(this.A02);
        sb.append(")");
        return sb.toString();
    }

    public AnonymousClass93(AbstractC0312cb cbVar) {
        if (cbVar != null) {
            this.A02 = cbVar;
            return;
        }
        throw new NullPointerException("source == null");
    }

    @Override // X.KC
    public final long A34(byte b) throws IOException {
        return A00(b);
    }

    @Override // X.KC
    public final boolean A4P(long j, ci ciVar) throws IOException {
        int A07 = ciVar.A07();
        if (this.A00) {
            throw new IllegalStateException("closed");
        } else if (j < 0 || A07 < 0 || A07 - 0 < A07) {
            return false;
        } else {
            for (int i = 0; i < A07; i++) {
                long j2 = ((long) i) + j;
                if (!A01(1 + j2) || this.A01.A01(j2) != ciVar.A06(0 + i)) {
                    return false;
                }
            }
            return true;
        }
    }

    @Override // X.KC
    public final byte[] A4T(long j) throws IOException {
        A4l(j);
        return this.A01.A4T(j);
    }

    @Override // X.KC
    public final ci A4U(long j) throws IOException {
        A4l(j);
        return this.A01.A4U(j);
    }

    @Override // X.KC
    public final void A4l(long j) throws IOException {
        if (!A01(j)) {
            throw new EOFException();
        }
    }

    @Override // X.KC
    public final AnonymousClass98 A1V() {
        return this.A01;
    }

    @Override // X.KC
    public final String A4c() throws IOException {
        long A002 = A00((byte) 10);
        if (A002 != -1) {
            return this.A01.A05(A002);
        }
        AnonymousClass98 r7 = new AnonymousClass98();
        AnonymousClass98 r6 = this.A01;
        r6.A0I(r7, 0, Math.min(32L, r6.A00));
        StringBuilder sb = new StringBuilder("\\n not found: limit=");
        sb.append(Math.min(r6.A00, Long.MAX_VALUE));
        sb.append(" content=");
        sb.append(new ci(r7.A4S()).A09());
        sb.append((char) 8230);
        throw new EOFException(sb.toString());
    }

    @Override // java.nio.channels.ReadableByteChannel
    public final int read(ByteBuffer byteBuffer) throws IOException {
        AnonymousClass98 r5 = this.A01;
        if (r5.A00 == 0 && this.A02.read(r5, 8192) == -1) {
            return -1;
        }
        return r5.read(byteBuffer);
    }

    @Override // X.AbstractC0312cb
    public final long read(AnonymousClass98 r7, long j) throws IOException {
        String str;
        if (r7 == null) {
            str = "sink == null";
        } else if (j < 0) {
            str = AnonymousClass06.A03("byteCount < 0: ", j);
        } else if (!this.A00) {
            AnonymousClass98 r5 = this.A01;
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
