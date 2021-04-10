package X;

import java.io.IOException;
import java.net.ProtocolException;

public final class EO implements WG {
    public long A00;
    public boolean A01;
    public final Dl A02;
    public final /* synthetic */ EN A03;

    public EO(EN en, long j) {
        this.A03 = en;
        this.A02 = new Dl(en.A01.timeout());
        this.A00 = j;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, X.WG
    public final void close() throws IOException {
        if (!this.A01) {
            this.A01 = true;
            if (this.A00 <= 0) {
                EN.A00(this.A02);
                this.A03.A00 = 3;
                return;
            }
            throw new ProtocolException("unexpected end of stream");
        }
    }

    @Override // X.WG, java.io.Flushable
    public final void flush() throws IOException {
        if (!this.A01) {
            this.A03.A01.flush();
        }
    }

    @Override // X.WG
    public final void write(AnonymousClass8k r8, long j) throws IOException {
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

    @Override // X.WG
    public final WE timeout() {
        return this.A02;
    }
}
