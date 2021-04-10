package X;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

/* renamed from: X.94  reason: invalid class name */
public final class AnonymousClass94 implements KJ {
    public boolean A00;
    public final AnonymousClass98 A01 = new AnonymousClass98();
    public final AbstractC0313cc A02;

    @Override // X.KJ
    public final KJ A1u() throws IOException {
        if (!this.A00) {
            AnonymousClass98 r5 = this.A01;
            long j = r5.A00;
            if (j > 0) {
                this.A02.write(r5, j);
            }
            return this;
        }
        throw new IllegalStateException("closed");
    }

    @Override // X.KJ
    public final KJ A1v() throws IOException {
        if (!this.A00) {
            AnonymousClass98 r5 = this.A01;
            long j = r5.A00;
            if (j != 0) {
                C0315ce ceVar = r5.A01.A03;
                int i = ceVar.A00;
                if (i < 8192 && ceVar.A04) {
                    j -= (long) (i - ceVar.A01);
                }
                if (j > 0) {
                    this.A02.write(r5, j);
                }
            }
            return this;
        }
        throw new IllegalStateException("closed");
    }

    @Override // X.KJ
    public final OutputStream A48() {
        return new C0317cg(this);
    }

    @Override // X.KJ
    public final KJ A5m(ci ciVar) throws IOException {
        if (!this.A00) {
            AnonymousClass98 r0 = this.A01;
            if (ciVar != null) {
                ciVar.A0F(r0);
                A1v();
                return this;
            }
            throw new IllegalArgumentException("byteString == null");
        }
        throw new IllegalStateException("closed");
    }

    @Override // X.KJ
    public final KJ A5n(byte[] bArr) throws IOException {
        if (!this.A00) {
            this.A01.A0J(bArr);
            A1v();
            return this;
        }
        throw new IllegalStateException("closed");
    }

    @Override // X.KJ
    public final KJ A5o(byte[] bArr, int i, int i2) throws IOException {
        if (!this.A00) {
            this.A01.A0K(bArr, i, i2);
            A1v();
            return this;
        }
        throw new IllegalStateException("closed");
    }

    @Override // X.KJ
    public final long A5p(AbstractC0312cb cbVar) throws IOException {
        if (cbVar != null) {
            long j = 0;
            while (true) {
                long read = cbVar.read(this.A01, 8192);
                if (read == -1) {
                    return j;
                }
                j += read;
                A1v();
            }
        } else {
            throw new IllegalArgumentException("source == null");
        }
    }

    @Override // X.KJ
    public final KJ A5q(int i) throws IOException {
        if (!this.A00) {
            this.A01.A09(i);
            A1v();
            return this;
        }
        throw new IllegalStateException("closed");
    }

    @Override // X.KJ
    public final KJ A5r(long j) throws IOException {
        if (!this.A00) {
            this.A01.A0E(j);
            A1v();
            return this;
        }
        throw new IllegalStateException("closed");
    }

    @Override // X.KJ
    public final KJ A5s(long j) throws IOException {
        if (!this.A00) {
            this.A01.A0F(j);
            A1v();
            return this;
        }
        throw new IllegalStateException("closed");
    }

    @Override // X.KJ
    public final KJ A5t(int i) throws IOException {
        if (!this.A00) {
            this.A01.A0A(i);
            A1v();
            return this;
        }
        throw new IllegalStateException("closed");
    }

    @Override // X.KJ
    public final KJ A5u(int i) throws IOException {
        if (!this.A00) {
            this.A01.A0B(i);
            A1v();
            return this;
        }
        throw new IllegalStateException("closed");
    }

    @Override // X.KJ
    public final KJ A5x(int i) throws IOException {
        if (!this.A00) {
            this.A01.A0C(i);
            A1v();
            return this;
        }
        throw new IllegalStateException("closed");
    }

    @Override // X.KJ
    public final KJ A5y(String str) throws IOException {
        if (!this.A00) {
            this.A01.A0G(str);
            A1v();
            return this;
        }
        throw new IllegalStateException("closed");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, X.AbstractC0313cc, java.nio.channels.Channel
    public final void close() throws IOException {
        if (!this.A00) {
            Throwable th = null;
            try {
                AnonymousClass98 r5 = this.A01;
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

    @Override // X.KJ, X.AbstractC0313cc, java.io.Flushable
    public final void flush() throws IOException {
        if (!this.A00) {
            AnonymousClass98 r5 = this.A01;
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

    @Override // X.AbstractC0313cc
    public final ca timeout() {
        return this.A02.timeout();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("buffer(");
        sb.append(this.A02);
        sb.append(")");
        return sb.toString();
    }

    public AnonymousClass94(AbstractC0313cc ccVar) {
        if (ccVar != null) {
            this.A02 = ccVar;
            return;
        }
        throw new NullPointerException("sink == null");
    }

    @Override // X.KJ
    public final AnonymousClass98 A1V() {
        return this.A01;
    }

    @Override // java.nio.channels.WritableByteChannel
    public final int write(ByteBuffer byteBuffer) throws IOException {
        if (!this.A00) {
            int write = this.A01.write(byteBuffer);
            A1v();
            return write;
        }
        throw new IllegalStateException("closed");
    }

    @Override // X.AbstractC0313cc
    public final void write(AnonymousClass98 r3, long j) throws IOException {
        if (!this.A00) {
            this.A01.write(r3, j);
            A1v();
            return;
        }
        throw new IllegalStateException("closed");
    }
}
