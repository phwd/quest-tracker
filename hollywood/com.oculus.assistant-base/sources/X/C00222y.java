package X;

import java.io.EOFException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* renamed from: X.2y  reason: invalid class name and case insensitive filesystem */
public final class C00222y implements t4 {
    public boolean A00;
    public final AnonymousClass33 A01 = new AnonymousClass33();
    public final AbstractC0609cs A02;

    private final long A00(byte b) {
        long j = 0;
        if (this.A00) {
            throw new IllegalStateException("closed");
        }
        while (j < Long.MAX_VALUE) {
            AnonymousClass33 r7 = this.A01;
            long A03 = r7.A03(b, j);
            if (A03 == -1) {
                long j2 = r7.A00;
                if (j2 >= Long.MAX_VALUE || this.A02.A4c(r7, 8192) == -1) {
                    break;
                }
                j = Math.max(j, j2);
            } else {
                return A03;
            }
        }
        return -1;
    }

    private final boolean A01(long j) {
        AnonymousClass33 r3;
        if (j < 0) {
            throw new IllegalArgumentException(AnonymousClass08.A03("byteCount < 0: ", j));
        } else if (this.A00) {
            throw new IllegalStateException("closed");
        } else {
            do {
                r3 = this.A01;
                if (r3.A00 >= j) {
                    return true;
                }
            } while (this.A02.A4c(r3, 8192) != -1);
            return false;
        }
    }

    @Override // X.t4
    public final boolean A1r() {
        if (!this.A00) {
            AnonymousClass33 r3 = this.A01;
            if (!r3.A1r() || this.A02.A4c(r3, 8192) != -1) {
                return false;
            }
            return true;
        }
        throw new IllegalStateException("closed");
    }

    @Override // X.t4
    public final InputStream A3K() {
        return new C0605co(this);
    }

    @Override // X.AbstractC0609cs
    public final long A4c(AnonymousClass33 r7, long j) {
        if (r7 == null) {
            throw new IllegalArgumentException("sink == null");
        } else if (j < 0) {
            throw new IllegalArgumentException(AnonymousClass08.A03("byteCount < 0: ", j));
        } else if (!this.A00) {
            AnonymousClass33 r5 = this.A01;
            if (r5.A00 == 0 && this.A02.A4c(r5, 8192) == -1) {
                return -1;
            }
            return r5.A4c(r7, Math.min(j, r5.A00));
        } else {
            throw new IllegalStateException("closed");
        }
    }

    @Override // X.t4
    public final byte[] A4d() {
        AnonymousClass33 r6 = this.A01;
        AbstractC0609cs csVar = this.A02;
        if (csVar == null) {
            throw new IllegalArgumentException("source == null");
        }
        do {
        } while (csVar.A4c(r6, 8192) != -1);
        return r6.A4d();
    }

    @Override // X.t4
    public final long A4g() {
        A4p(1);
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
        return this.A01.A4g();
    }

    @Override // X.t4
    public final int A4h() {
        A4p(4);
        return this.A01.A4h();
    }

    @Override // X.t4
    public final short A4k() {
        A4p(2);
        return this.A01.A4k();
    }

    @Override // X.t4
    public final void A55(long j) {
        if (this.A00) {
            throw new IllegalStateException("closed");
        }
        while (j > 0) {
            AnonymousClass33 r5 = this.A01;
            if (r5.A00 == 0 && this.A02.A4c(r5, 8192) == -1) {
                throw new EOFException();
            }
            long min = Math.min(j, r5.A00);
            r5.A55(min);
            j -= min;
        }
    }

    @Override // X.AbstractC0609cs
    public final C0610ct A5G() {
        return this.A02.A5G();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, X.AbstractC0609cs, java.nio.channels.Channel
    public final void close() {
        if (!this.A00) {
            this.A00 = true;
            this.A02.close();
            this.A01.A07();
        }
    }

    public final boolean isOpen() {
        return !this.A00;
    }

    @Override // java.nio.channels.ReadableByteChannel
    public final int read(ByteBuffer byteBuffer) {
        AnonymousClass33 r5 = this.A01;
        if (r5.A00 == 0 && this.A02.A4c(r5, 8192) == -1) {
            return -1;
        }
        return r5.read(byteBuffer);
    }

    @Override // X.t4
    public final byte readByte() {
        A4p(1);
        return this.A01.readByte();
    }

    @Override // X.t4
    public final int readInt() {
        A4p(4);
        return this.A01.readInt();
    }

    @Override // X.t4
    public final short readShort() {
        A4p(2);
        return this.A01.readShort();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("buffer(");
        sb.append(this.A02);
        sb.append(")");
        return sb.toString();
    }

    public C00222y(AbstractC0609cs csVar) {
        this.A02 = csVar;
    }

    @Override // X.t4
    public final AnonymousClass33 A1M() {
        return this.A01;
    }

    @Override // X.t4
    public final long A3I(byte b) {
        return A00(b);
    }

    @Override // X.t4
    public final byte[] A4e(long j) {
        A4p(j);
        return this.A01.A4e(j);
    }

    @Override // X.t4
    public final C0603cm A4f(long j) {
        A4p(j);
        return this.A01.A4f(j);
    }

    @Override // X.t4
    public final void A4p(long j) {
        if (!A01(j)) {
            throw new EOFException();
        }
    }

    @Override // X.t4
    public final String A4l() {
        long A002 = A00((byte) 10);
        if (A002 != -1) {
            return this.A01.A05(A002);
        }
        AnonymousClass33 r7 = new AnonymousClass33();
        AnonymousClass33 r6 = this.A01;
        r6.A0E(r7, 0, Math.min(32L, r6.A00));
        StringBuilder sb = new StringBuilder("\\n not found: limit=");
        sb.append(Math.min(r6.A00, Long.MAX_VALUE));
        sb.append(" content=");
        sb.append(new C0603cm(r7.A4d()).A07());
        sb.append((char) 8230);
        throw new EOFException(sb.toString());
    }
}
