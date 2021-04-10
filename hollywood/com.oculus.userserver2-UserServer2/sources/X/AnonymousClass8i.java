package X;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

/* renamed from: X.8i  reason: invalid class name */
public final class AnonymousClass8i implements Du {
    public boolean A00;
    public final AnonymousClass8k A01 = new AnonymousClass8k();
    public final WG A02;

    @Override // X.Du
    public final Du A1S() throws IOException {
        if (!this.A00) {
            AnonymousClass8k r5 = this.A01;
            long j = r5.A00;
            if (j > 0) {
                this.A02.write(r5, j);
            }
            return this;
        }
        throw new IllegalStateException("closed");
    }

    @Override // X.Du
    public final Du A1T() throws IOException {
        if (!this.A00) {
            AnonymousClass8k r5 = this.A01;
            long j = r5.A00;
            if (j != 0) {
                WI wi = r5.A01.A03;
                int i = wi.A00;
                if (i < 8192 && wi.A04) {
                    j -= (long) (i - wi.A01);
                }
                if (j > 0) {
                    this.A02.write(r5, j);
                }
            }
            return this;
        }
        throw new IllegalStateException("closed");
    }

    @Override // X.Du
    public final OutputStream A2l() {
        return new WK(this);
    }

    @Override // X.Du
    public final Du A3u(WM wm) throws IOException {
        if (!this.A00) {
            AnonymousClass8k r0 = this.A01;
            if (wm != null) {
                wm.A0F(r0);
                A1T();
                return this;
            }
            throw new IllegalArgumentException("byteString == null");
        }
        throw new IllegalStateException("closed");
    }

    @Override // X.Du
    public final Du A3v(byte[] bArr) throws IOException {
        if (!this.A00) {
            this.A01.A0I(bArr);
            A1T();
            return this;
        }
        throw new IllegalStateException("closed");
    }

    @Override // X.Du
    public final Du A3w(byte[] bArr, int i, int i2) throws IOException {
        if (!this.A00) {
            this.A01.A0J(bArr, i, i2);
            A1T();
            return this;
        }
        throw new IllegalStateException("closed");
    }

    @Override // X.Du
    public final long A3x(WF wf) throws IOException {
        if (wf != null) {
            long j = 0;
            while (true) {
                long read = wf.read(this.A01, 8192);
                if (read == -1) {
                    return j;
                }
                j += read;
                A1T();
            }
        } else {
            throw new IllegalArgumentException("source == null");
        }
    }

    @Override // X.Du
    public final Du A3y(int i) throws IOException {
        if (!this.A00) {
            this.A01.A09(i);
            A1T();
            return this;
        }
        throw new IllegalStateException("closed");
    }

    @Override // X.Du
    public final Du A3z(long j) throws IOException {
        if (!this.A00) {
            this.A01.A0D(j);
            A1T();
            return this;
        }
        throw new IllegalStateException("closed");
    }

    @Override // X.Du
    public final Du A40(long j) throws IOException {
        if (!this.A00) {
            this.A01.A0E(j);
            A1T();
            return this;
        }
        throw new IllegalStateException("closed");
    }

    @Override // X.Du
    public final Du A41(int i) throws IOException {
        if (!this.A00) {
            this.A01.A0A(i);
            A1T();
            return this;
        }
        throw new IllegalStateException("closed");
    }

    @Override // X.Du
    public final Du A43(int i) throws IOException {
        if (!this.A00) {
            this.A01.A0B(i);
            A1T();
            return this;
        }
        throw new IllegalStateException("closed");
    }

    @Override // X.Du
    public final Du A44(String str) throws IOException {
        if (!this.A00) {
            this.A01.A0F(str);
            A1T();
            return this;
        }
        throw new IllegalStateException("closed");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, X.WG, java.nio.channels.Channel
    public final void close() throws IOException {
        if (!this.A00) {
            Throwable th = null;
            try {
                AnonymousClass8k r5 = this.A01;
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

    @Override // X.WG, X.Du, java.io.Flushable
    public final void flush() throws IOException {
        if (!this.A00) {
            AnonymousClass8k r5 = this.A01;
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

    @Override // X.WG
    public final WE timeout() {
        return this.A02.timeout();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("buffer(");
        sb.append(this.A02);
        sb.append(")");
        return sb.toString();
    }

    public AnonymousClass8i(WG wg) {
        if (wg != null) {
            this.A02 = wg;
            return;
        }
        throw new NullPointerException("sink == null");
    }

    @Override // X.Du
    public final AnonymousClass8k A16() {
        return this.A01;
    }

    @Override // java.nio.channels.WritableByteChannel
    public final int write(ByteBuffer byteBuffer) throws IOException {
        if (!this.A00) {
            int write = this.A01.write(byteBuffer);
            A1T();
            return write;
        }
        throw new IllegalStateException("closed");
    }

    @Override // X.WG
    public final void write(AnonymousClass8k r3, long j) throws IOException {
        if (!this.A00) {
            this.A01.write(r3, j);
            A1T();
            return;
        }
        throw new IllegalStateException("closed");
    }
}
