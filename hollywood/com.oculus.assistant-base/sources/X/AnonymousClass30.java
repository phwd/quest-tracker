package X;

import java.nio.ByteBuffer;

/* renamed from: X.30  reason: invalid class name */
public final class AnonymousClass30 implements t6 {
    public boolean A00;
    public final AnonymousClass33 A01 = new AnonymousClass33();
    public final AbstractC0608cr A02;

    private final t6 A00() {
        if (!this.A00) {
            AnonymousClass33 r5 = this.A01;
            long j = r5.A00;
            if (j != 0) {
                C0606cp cpVar = r5.A01.A03;
                int i = cpVar.A00;
                if (i < 8192 && cpVar.A04) {
                    j -= (long) (i - cpVar.A01);
                }
                if (j > 0) {
                    this.A02.A5k(r5, j);
                }
            }
            return this;
        }
        throw new IllegalStateException("closed");
    }

    @Override // X.AbstractC0608cr
    public final C0610ct A5G() {
        return this.A02.A5G();
    }

    @Override // X.t6
    public final t6 A5i(byte[] bArr) {
        if (!this.A00) {
            AnonymousClass33 r2 = this.A01;
            if (bArr != null) {
                r2.A0F(bArr, 0, bArr.length);
                A00();
                return this;
            }
            throw new IllegalArgumentException("source == null");
        }
        throw new IllegalStateException("closed");
    }

    @Override // X.t6
    public final t6 A5j(byte[] bArr, int i, int i2) {
        if (!this.A00) {
            this.A01.A0F(bArr, i, i2);
            A00();
            return this;
        }
        throw new IllegalStateException("closed");
    }

    @Override // X.AbstractC0608cr
    public final void A5k(AnonymousClass33 r3, long j) {
        if (!this.A00) {
            this.A01.A5k(r3, j);
            A00();
            return;
        }
        throw new IllegalStateException("closed");
    }

    @Override // X.t6
    public final t6 A5m(int i) {
        if (!this.A00) {
            this.A01.A08(i);
            A00();
            return this;
        }
        throw new IllegalStateException("closed");
    }

    @Override // X.t6
    public final t6 A5p(long j) {
        if (!this.A00) {
            this.A01.A0C(j);
            A00();
            return this;
        }
        throw new IllegalStateException("closed");
    }

    @Override // X.t6
    public final t6 A5r(int i) {
        if (!this.A00) {
            this.A01.A09(i);
            A00();
            return this;
        }
        throw new IllegalStateException("closed");
    }

    @Override // X.t6
    public final t6 A5w(int i) {
        if (!this.A00) {
            this.A01.A0A(i);
            A00();
            return this;
        }
        throw new IllegalStateException("closed");
    }

    @Override // X.t6
    public final t6 A5z(String str) {
        if (!this.A00) {
            this.A01.A0D(str, 0, str.length());
            A00();
            return this;
        }
        throw new IllegalStateException("closed");
    }

    @Override // java.io.Closeable, X.AbstractC0608cr, java.lang.AutoCloseable, java.nio.channels.Channel
    public final void close() {
        if (!this.A00) {
            Throwable th = null;
            try {
                AnonymousClass33 r5 = this.A01;
                long j = r5.A00;
                if (j > 0) {
                    this.A02.A5k(r5, j);
                }
            } catch (Throwable th2) {
                th = th2;
            }
            try {
                this.A02.close();
            } catch (Throwable th3) {
                if (th == null) {
                    th = th3;
                }
            }
            this.A00 = true;
            if (th != null) {
                throw th;
            }
        }
    }

    @Override // X.t6, X.AbstractC0608cr, java.io.Flushable
    public final void flush() {
        if (!this.A00) {
            AnonymousClass33 r5 = this.A01;
            long j = r5.A00;
            if (j > 0) {
                this.A02.A5k(r5, j);
            }
            this.A02.flush();
            return;
        }
        throw new IllegalStateException("closed");
    }

    public final boolean isOpen() {
        return !this.A00;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("buffer(");
        sb.append(this.A02);
        sb.append(")");
        return sb.toString();
    }

    @Override // java.nio.channels.WritableByteChannel
    public final int write(ByteBuffer byteBuffer) {
        if (!this.A00) {
            int write = this.A01.write(byteBuffer);
            A00();
            return write;
        }
        throw new IllegalStateException("closed");
    }

    public AnonymousClass30(AbstractC0608cr crVar) {
        if (crVar != null) {
            this.A02 = crVar;
            return;
        }
        throw new NullPointerException("sink == null");
    }

    @Override // X.t6
    public final AnonymousClass33 A1M() {
        return this.A01;
    }
}
