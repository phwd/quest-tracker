package X;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* renamed from: X.0HO  reason: invalid class name */
public final class AnonymousClass0HO implements AnonymousClass0Od {
    public boolean A00;
    public final AnonymousClass0HR A01 = new AnonymousClass0HR();
    public final AbstractC04550h0 A02;

    private final long A00(byte b) throws IOException {
        long j = 0;
        if (this.A00) {
            throw new IllegalStateException("closed");
        }
        while (j < Long.MAX_VALUE) {
            AnonymousClass0HR r7 = this.A01;
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
        AnonymousClass0HR r3;
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

    @Override // X.AnonymousClass0Od
    public final AnonymousClass0HR A1Z() {
        return this.A01;
    }

    @Override // X.AnonymousClass0Od
    public final boolean A2T() throws IOException {
        if (!this.A00) {
            AnonymousClass0HR r3 = this.A01;
            if (!r3.A2T() || this.A02.read(r3, 8192) != -1) {
                return false;
            }
            return true;
        }
        throw new IllegalStateException("closed");
    }

    @Override // X.AnonymousClass0Od
    public final InputStream A5H() {
        return new C04580h4(this);
    }

    @Override // X.AnonymousClass0Od
    public final byte[] A79() throws IOException {
        AnonymousClass0HR r1 = this.A01;
        r1.A8y(this.A02);
        return r1.A79();
    }

    @Override // X.AnonymousClass0Od
    public final long A7C() throws IOException {
        A7R(1);
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
        return this.A01.A7C();
    }

    @Override // X.AnonymousClass0Od
    public final long A7D() throws IOException {
        A7R(1);
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
        return this.A01.A7D();
    }

    @Override // X.AnonymousClass0Od
    public final int A7E() throws IOException {
        A7R(4);
        return this.A01.A7E();
    }

    @Override // X.AnonymousClass0Od
    public final short A7H() throws IOException {
        A7R(2);
        return this.A01.A7H();
    }

    @Override // X.AnonymousClass0Od
    public final void A8T(long j) throws IOException {
        if (this.A00) {
            throw new IllegalStateException("closed");
        }
        while (j > 0) {
            AnonymousClass0HR r5 = this.A01;
            if (r5.A00 == 0 && this.A02.read(r5, 8192) == -1) {
                throw new EOFException();
            }
            long min = Math.min(j, r5.A00);
            r5.A8T(min);
            j -= min;
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, X.AbstractC04550h0, java.nio.channels.Channel
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

    @Override // X.AnonymousClass0Od
    public final byte readByte() throws IOException {
        A7R(1);
        return this.A01.readByte();
    }

    @Override // X.AnonymousClass0Od
    public final int readInt() throws IOException {
        A7R(4);
        return this.A01.readInt();
    }

    @Override // X.AnonymousClass0Od
    public final short readShort() throws IOException {
        A7R(2);
        return this.A01.readShort();
    }

    @Override // X.AbstractC04550h0
    public final C04540gz timeout() {
        return this.A02.timeout();
    }

    public final String toString() {
        return "buffer(" + this.A02 + ")";
    }

    public AnonymousClass0HO(AbstractC04550h0 r3) {
        if (r3 != null) {
            this.A02 = r3;
            return;
        }
        throw new NullPointerException("source == null");
    }

    @Override // X.AnonymousClass0Od
    public final long A5A(byte b) throws IOException {
        return A00(b);
    }

    @Override // X.AnonymousClass0Od
    public final byte[] A7A(long j) throws IOException {
        A7R(j);
        return this.A01.A7A(j);
    }

    @Override // X.AnonymousClass0Od
    public final C04610h7 A7B(long j) throws IOException {
        A7R(j);
        return this.A01.A7B(j);
    }

    @Override // X.AnonymousClass0Od
    public final void A7R(long j) throws IOException {
        if (!A01(j)) {
            throw new EOFException();
        }
    }

    @Override // X.AnonymousClass0Od
    public final String A7I() throws IOException {
        long A002 = A00((byte) 10);
        if (A002 != -1) {
            return this.A01.A05(A002);
        }
        AnonymousClass0HR r7 = new AnonymousClass0HR();
        AnonymousClass0HR r6 = this.A01;
        r6.A0H(r7, 0, Math.min(32L, r6.A00));
        throw new EOFException("\\n not found: limit=" + Math.min(r6.A00, Long.MAX_VALUE) + " content=" + new C04610h7(r7.A79()).A09() + (char) 8230);
    }

    @Override // java.nio.channels.ReadableByteChannel
    public final int read(ByteBuffer byteBuffer) throws IOException {
        AnonymousClass0HR r5 = this.A01;
        if (r5.A00 == 0 && this.A02.read(r5, 8192) == -1) {
            return -1;
        }
        return r5.read(byteBuffer);
    }

    @Override // X.AbstractC04550h0
    public final long read(AnonymousClass0HR r7, long j) throws IOException {
        String str;
        if (r7 == null) {
            str = "sink == null";
        } else if (j < 0) {
            str = AnonymousClass006.A04("byteCount < 0: ", j);
        } else if (!this.A00) {
            AnonymousClass0HR r5 = this.A01;
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
