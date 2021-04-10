package X;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/* renamed from: X.8h  reason: invalid class name and case insensitive filesystem */
public final class C00148h implements Dp {
    public boolean A00;
    public final AnonymousClass8k A01 = new AnonymousClass8k();
    public final WF A02;

    private final long A00(byte b) throws IOException {
        long j = 0;
        if (this.A00) {
            throw new IllegalStateException("closed");
        }
        while (j < Long.MAX_VALUE) {
            AnonymousClass8k r7 = this.A01;
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
        AnonymousClass8k r3;
        if (j < 0) {
            throw new IllegalArgumentException(AnonymousClass06.A02("byteCount < 0: ", j));
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

    @Override // X.Dp
    public final boolean A1V() throws IOException {
        if (!this.A00) {
            AnonymousClass8k r3 = this.A01;
            if (!r3.A1V() || this.A02.read(r3, 8192) != -1) {
                return false;
            }
            return true;
        }
        throw new IllegalStateException("closed");
    }

    @Override // X.Dp
    public final InputStream A28() {
        return new WJ(this);
    }

    @Override // X.Dp
    public final byte[] A31() throws IOException {
        AnonymousClass8k r1 = this.A01;
        r1.A3x(this.A02);
        return r1.A31();
    }

    @Override // X.Dp
    public final long A34() throws IOException {
        A3N(1);
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
        return this.A01.A34();
    }

    @Override // X.Dp
    public final long A35() throws IOException {
        A3N(1);
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
        return this.A01.A35();
    }

    @Override // X.Dp
    public final int A36() throws IOException {
        A3N(4);
        return this.A01.A36();
    }

    @Override // X.Dp
    public final short A39() throws IOException {
        A3N(2);
        return this.A01.A39();
    }

    @Override // X.Dp
    public final String A3A(Charset charset) throws IOException {
        if (charset != null) {
            AnonymousClass8k r1 = this.A01;
            r1.A3x(this.A02);
            return r1.A3A(charset);
        }
        throw new IllegalArgumentException("charset == null");
    }

    @Override // X.Dp
    public final void A3i(long j) throws IOException {
        if (this.A00) {
            throw new IllegalStateException("closed");
        }
        while (j > 0) {
            AnonymousClass8k r5 = this.A01;
            if (r5.A00 == 0 && this.A02.read(r5, 8192) == -1) {
                throw new EOFException();
            }
            long min = Math.min(j, r5.A00);
            r5.A3i(min);
            j -= min;
        }
    }

    @Override // java.io.Closeable, X.WF, java.lang.AutoCloseable, java.nio.channels.Channel
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

    @Override // X.Dp
    public final byte readByte() throws IOException {
        A3N(1);
        return this.A01.readByte();
    }

    @Override // X.Dp
    public final int readInt() throws IOException {
        A3N(4);
        return this.A01.readInt();
    }

    @Override // X.Dp
    public final short readShort() throws IOException {
        A3N(2);
        return this.A01.readShort();
    }

    @Override // X.WF
    public final WE timeout() {
        return this.A02.timeout();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("buffer(");
        sb.append(this.A02);
        sb.append(")");
        return sb.toString();
    }

    public C00148h(WF wf) {
        if (wf != null) {
            this.A02 = wf;
            return;
        }
        throw new NullPointerException("source == null");
    }

    @Override // X.Dp
    public final AnonymousClass8k A16() {
        return this.A01;
    }

    @Override // X.Dp
    public final long A27(byte b) throws IOException {
        return A00(b);
    }

    @Override // X.Dp
    public final boolean A2y(long j, WM wm) throws IOException {
        int A07 = wm.A07();
        if (this.A00) {
            throw new IllegalStateException("closed");
        } else if (j < 0 || A07 < 0 || A07 - 0 < A07) {
            return false;
        } else {
            for (int i = 0; i < A07; i++) {
                long j2 = ((long) i) + j;
                if (!A01(1 + j2) || this.A01.A01(j2) != wm.A06(0 + i)) {
                    return false;
                }
            }
            return true;
        }
    }

    @Override // X.Dp
    public final byte[] A32(long j) throws IOException {
        A3N(j);
        return this.A01.A32(j);
    }

    @Override // X.Dp
    public final WM A33(long j) throws IOException {
        A3N(j);
        return this.A01.A33(j);
    }

    @Override // X.Dp
    public final void A3N(long j) throws IOException {
        if (!A01(j)) {
            throw new EOFException();
        }
    }

    @Override // X.Dp
    public final String A3B() throws IOException {
        long A002 = A00((byte) 10);
        if (A002 != -1) {
            return this.A01.A05(A002);
        }
        AnonymousClass8k r7 = new AnonymousClass8k();
        AnonymousClass8k r6 = this.A01;
        r6.A0H(r7, 0, Math.min(32L, r6.A00));
        StringBuilder sb = new StringBuilder("\\n not found: limit=");
        sb.append(Math.min(r6.A00, Long.MAX_VALUE));
        sb.append(" content=");
        sb.append(new WM(r7.A31()).A09());
        sb.append((char) 8230);
        throw new EOFException(sb.toString());
    }

    @Override // java.nio.channels.ReadableByteChannel
    public final int read(ByteBuffer byteBuffer) throws IOException {
        AnonymousClass8k r5 = this.A01;
        if (r5.A00 == 0 && this.A02.read(r5, 8192) == -1) {
            return -1;
        }
        return r5.read(byteBuffer);
    }

    @Override // X.WF
    public final long read(AnonymousClass8k r7, long j) throws IOException {
        String str;
        if (r7 == null) {
            str = "sink == null";
        } else if (j < 0) {
            str = AnonymousClass06.A02("byteCount < 0: ", j);
        } else if (!this.A00) {
            AnonymousClass8k r5 = this.A01;
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
