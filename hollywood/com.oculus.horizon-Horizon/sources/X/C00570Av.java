package X;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

/* renamed from: X.0Av  reason: invalid class name and case insensitive filesystem */
public final class C00570Av implements AnonymousClass0Lx {
    public boolean A00;
    public final AnonymousClass0B3 A01 = new AnonymousClass0B3();
    public final AbstractC07640v7 A02;

    @Override // X.AnonymousClass0Lx
    public final AnonymousClass0Lx A2O() throws IOException {
        if (!this.A00) {
            AnonymousClass0B3 r5 = this.A01;
            long j = r5.A00;
            if (j > 0) {
                this.A02.write(r5, j);
            }
            return this;
        }
        throw new IllegalStateException("closed");
    }

    @Override // X.AnonymousClass0Lx
    public final AnonymousClass0Lx A2P() throws IOException {
        if (!this.A00) {
            AnonymousClass0B3 r5 = this.A01;
            long j = r5.A00;
            if (j != 0) {
                C07660v9 r2 = r5.A01.A03;
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

    @Override // X.AnonymousClass0Lx
    public final OutputStream A7F() {
        return new C07680vB(this);
    }

    @Override // X.AnonymousClass0Lx
    public final AnonymousClass0Lx AA6(C07700vD r3) throws IOException {
        if (!this.A00) {
            AnonymousClass0B3 r0 = this.A01;
            if (r3 != null) {
                r3.A0F(r0);
                A2P();
                return this;
            }
            throw new IllegalArgumentException("byteString == null");
        }
        throw new IllegalStateException("closed");
    }

    @Override // X.AnonymousClass0Lx
    public final AnonymousClass0Lx AA7(byte[] bArr) throws IOException {
        if (!this.A00) {
            this.A01.A0I(bArr);
            A2P();
            return this;
        }
        throw new IllegalStateException("closed");
    }

    @Override // X.AnonymousClass0Lx
    public final AnonymousClass0Lx AA8(byte[] bArr, int i, int i2) throws IOException {
        if (!this.A00) {
            this.A01.A0J(bArr, i, i2);
            A2P();
            return this;
        }
        throw new IllegalStateException("closed");
    }

    @Override // X.AnonymousClass0Lx
    public final long AA9(AbstractC07630v6 r8) throws IOException {
        if (r8 != null) {
            long j = 0;
            while (true) {
                long read = r8.read(this.A01, 8192);
                if (read == -1) {
                    return j;
                }
                j += read;
                A2P();
            }
        } else {
            throw new IllegalArgumentException("source == null");
        }
    }

    @Override // X.AnonymousClass0Lx
    public final AnonymousClass0Lx AAC(int i) throws IOException {
        if (!this.A00) {
            this.A01.A09(i);
            A2P();
            return this;
        }
        throw new IllegalStateException("closed");
    }

    @Override // X.AnonymousClass0Lx
    public final AnonymousClass0Lx AAD(long j) throws IOException {
        if (!this.A00) {
            this.A01.A0D(j);
            A2P();
            return this;
        }
        throw new IllegalStateException("closed");
    }

    @Override // X.AnonymousClass0Lx
    public final AnonymousClass0Lx AAE(long j) throws IOException {
        if (!this.A00) {
            this.A01.A0E(j);
            A2P();
            return this;
        }
        throw new IllegalStateException("closed");
    }

    @Override // X.AnonymousClass0Lx
    public final AnonymousClass0Lx AAF(int i) throws IOException {
        if (!this.A00) {
            this.A01.A0A(i);
            A2P();
            return this;
        }
        throw new IllegalStateException("closed");
    }

    @Override // X.AnonymousClass0Lx
    public final AnonymousClass0Lx AAH(int i) throws IOException {
        if (!this.A00) {
            this.A01.A0B(i);
            A2P();
            return this;
        }
        throw new IllegalStateException("closed");
    }

    @Override // X.AnonymousClass0Lx
    public final AnonymousClass0Lx AAI(String str) throws IOException {
        if (!this.A00) {
            this.A01.A0F(str);
            A2P();
            return this;
        }
        throw new IllegalStateException("closed");
    }

    @Override // X.AbstractC07640v7, java.io.Closeable, java.lang.AutoCloseable, java.nio.channels.Channel
    public final void close() throws IOException {
        if (!this.A00) {
            Throwable th = null;
            try {
                AnonymousClass0B3 r5 = this.A01;
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

    @Override // X.AbstractC07640v7, X.AnonymousClass0Lx, java.io.Flushable
    public final void flush() throws IOException {
        if (!this.A00) {
            AnonymousClass0B3 r5 = this.A01;
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

    @Override // X.AbstractC07640v7
    public final C07620v5 timeout() {
        return this.A02.timeout();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("buffer(");
        sb.append(this.A02);
        sb.append(")");
        return sb.toString();
    }

    public C00570Av(AbstractC07640v7 r3) {
        if (r3 != null) {
            this.A02 = r3;
            return;
        }
        throw new NullPointerException("sink == null");
    }

    @Override // X.AnonymousClass0Lx
    public final AnonymousClass0B3 A1V() {
        return this.A01;
    }

    @Override // java.nio.channels.WritableByteChannel
    public final int write(ByteBuffer byteBuffer) throws IOException {
        if (!this.A00) {
            int write = this.A01.write(byteBuffer);
            A2P();
            return write;
        }
        throw new IllegalStateException("closed");
    }

    @Override // X.AbstractC07640v7
    public final void write(AnonymousClass0B3 r3, long j) throws IOException {
        if (!this.A00) {
            this.A01.write(r3, j);
            A2P();
            return;
        }
        throw new IllegalStateException("closed");
    }
}
