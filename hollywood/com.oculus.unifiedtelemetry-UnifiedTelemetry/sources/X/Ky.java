package X;

import java.io.IOException;
import java.net.ProtocolException;

public final class Ky implements AbstractC0313cc {
    public long A00;
    public boolean A01;
    public final K4 A02;
    public final /* synthetic */ C0127Kx A03;

    public Ky(C0127Kx kx, long j) {
        this.A03 = kx;
        this.A02 = new K4(kx.A01.timeout());
        this.A00 = j;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, X.AbstractC0313cc
    public final void close() throws IOException {
        if (!this.A01) {
            this.A01 = true;
            if (this.A00 <= 0) {
                C0127Kx.A00(this.A02);
                this.A03.A00 = 3;
                return;
            }
            throw new ProtocolException("unexpected end of stream");
        }
    }

    @Override // X.AbstractC0313cc, java.io.Flushable
    public final void flush() throws IOException {
        if (!this.A01) {
            this.A03.A01.flush();
        }
    }

    @Override // X.AbstractC0313cc
    public final void write(AnonymousClass98 r8, long j) throws IOException {
        if (!this.A01) {
            long j2 = r8.A00;
            if ((0 | j) < 0 || 0 > j2 || j2 - 0 < j) {
                throw new ArrayIndexOutOfBoundsException();
            }
            long j3 = this.A00;
            if (j <= j3) {
                this.A03.A01.write(r8, j);
                this.A00 -= j;
                return;
            }
            StringBuilder sb = new StringBuilder("expected ");
            sb.append(j3);
            sb.append(" bytes but received ");
            sb.append(j);
            throw new ProtocolException(sb.toString());
        }
        throw new IllegalStateException("closed");
    }

    @Override // X.AbstractC0313cc
    public final ca timeout() {
        return this.A02;
    }
}
