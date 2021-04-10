package X;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

/* renamed from: X.0HP  reason: invalid class name */
public final class AnonymousClass0HP implements AnonymousClass0Oe {
    public boolean A00;
    public final AnonymousClass0HR A01 = new AnonymousClass0HR();
    public final AnonymousClass0h1 A02;

    @Override // X.AnonymousClass0Oe
    public final AnonymousClass0HR A1Z() {
        return this.A01;
    }

    @Override // X.AnonymousClass0Oe
    public final AnonymousClass0Oe A2H() throws IOException {
        if (!this.A00) {
            AnonymousClass0HR r5 = this.A01;
            long j = r5.A00;
            if (j > 0) {
                this.A02.write(r5, j);
            }
            return this;
        }
        throw new IllegalStateException("closed");
    }

    @Override // X.AnonymousClass0Oe
    public final AnonymousClass0Oe A2I() throws IOException {
        if (!this.A00) {
            AnonymousClass0HR r5 = this.A01;
            long j = r5.A00;
            if (j != 0) {
                C04570h3 r2 = r5.A01.A03;
                int i = r2.A01;
                if (i < 8192 && r2.A04) {
                    j -= (long) (i - r2.A02);
                }
                if (j > 0) {
                    this.A02.write(r5, j);
                }
            }
            return this;
        }
        throw new IllegalStateException("closed");
    }

    @Override // X.AnonymousClass0Oe
    public final OutputStream A6l() {
        return new C04590h5(this);
    }

    @Override // X.AnonymousClass0Oe
    public final AnonymousClass0Oe A8v(C04610h7 r3) throws IOException {
        if (!this.A00) {
            AnonymousClass0HR r0 = this.A01;
            if (r3 != null) {
                r3.A0F(r0);
                A2I();
                return this;
            }
            throw new IllegalArgumentException("byteString == null");
        }
        throw new IllegalStateException("closed");
    }

    @Override // X.AnonymousClass0Oe
    public final AnonymousClass0Oe A8w(byte[] bArr) throws IOException {
        if (!this.A00) {
            this.A01.A0I(bArr);
            A2I();
            return this;
        }
        throw new IllegalStateException("closed");
    }

    @Override // X.AnonymousClass0Oe
    public final AnonymousClass0Oe A8x(byte[] bArr, int i, int i2) throws IOException {
        if (!this.A00) {
            this.A01.A0J(bArr, i, i2);
            A2I();
            return this;
        }
        throw new IllegalStateException("closed");
    }

    @Override // X.AnonymousClass0Oe
    public final long A8y(AbstractC04550h0 r8) throws IOException {
        if (r8 != null) {
            long j = 0;
            while (true) {
                long read = r8.read(this.A01, 8192);
                if (read == -1) {
                    return j;
                }
                j += read;
                A2I();
            }
        } else {
            throw new IllegalArgumentException("source == null");
        }
    }

    @Override // X.AnonymousClass0Oe
    public final AnonymousClass0Oe A91(int i) throws IOException {
        if (!this.A00) {
            this.A01.A09(i);
            A2I();
            return this;
        }
        throw new IllegalStateException("closed");
    }

    @Override // X.AnonymousClass0Oe
    public final AnonymousClass0Oe A92(long j) throws IOException {
        if (!this.A00) {
            this.A01.A0D(j);
            A2I();
            return this;
        }
        throw new IllegalStateException("closed");
    }

    @Override // X.AnonymousClass0Oe
    public final AnonymousClass0Oe A95(long j) throws IOException {
        if (!this.A00) {
            this.A01.A0E(j);
            A2I();
            return this;
        }
        throw new IllegalStateException("closed");
    }

    @Override // X.AnonymousClass0Oe
    public final AnonymousClass0Oe A97(int i) throws IOException {
        if (!this.A00) {
            this.A01.A0A(i);
            A2I();
            return this;
        }
        throw new IllegalStateException("closed");
    }

    @Override // X.AnonymousClass0Oe
    public final AnonymousClass0Oe A9C(int i) throws IOException {
        if (!this.A00) {
            this.A01.A0B(i);
            A2I();
            return this;
        }
        throw new IllegalStateException("closed");
    }

    @Override // X.AnonymousClass0Oe
    public final AnonymousClass0Oe A9F(String str) throws IOException {
        if (!this.A00) {
            this.A01.A0F(str);
            A2I();
            return this;
        }
        throw new IllegalStateException("closed");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, X.AnonymousClass0h1, java.nio.channels.Channel
    public final void close() throws IOException {
        if (!this.A00) {
            Throwable th = null;
            try {
                AnonymousClass0HR r5 = this.A01;
                long j = r5.A00;
                if (j > 0) {
                    this.A02.write(r5, j);
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

    @Override // X.AnonymousClass0Oe, X.AnonymousClass0h1, java.io.Flushable
    public final void flush() throws IOException {
        if (!this.A00) {
            AnonymousClass0HR r5 = this.A01;
            long j = r5.A00;
            if (j > 0) {
                this.A02.write(r5, j);
            }
            this.A02.flush();
            return;
        }
        throw new IllegalStateException("closed");
    }

    public final boolean isOpen() {
        return !this.A00;
    }

    @Override // X.AnonymousClass0h1
    public final C04540gz timeout() {
        return this.A02.timeout();
    }

    public final String toString() {
        return "buffer(" + this.A02 + ")";
    }

    public AnonymousClass0HP(AnonymousClass0h1 r3) {
        if (r3 != null) {
            this.A02 = r3;
            return;
        }
        throw new NullPointerException("sink == null");
    }

    @Override // java.nio.channels.WritableByteChannel
    public final int write(ByteBuffer byteBuffer) throws IOException {
        if (!this.A00) {
            int write = this.A01.write(byteBuffer);
            A2I();
            return write;
        }
        throw new IllegalStateException("closed");
    }

    @Override // X.AnonymousClass0h1
    public final void write(AnonymousClass0HR r3, long j) throws IOException {
        if (!this.A00) {
            this.A01.write(r3, j);
            A2I();
            return;
        }
        throw new IllegalStateException("closed");
    }
}
