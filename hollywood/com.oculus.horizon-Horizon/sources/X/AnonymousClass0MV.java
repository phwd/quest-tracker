package X;

import java.io.IOException;
import java.net.ProtocolException;

/* renamed from: X.0MV  reason: invalid class name */
public final class AnonymousClass0MV implements AbstractC07640v7 {
    public long A00;
    public boolean A01;
    public final AnonymousClass0Ls A02;
    public final /* synthetic */ AnonymousClass0MU A03;

    public AnonymousClass0MV(AnonymousClass0MU r3, long j) {
        this.A03 = r3;
        this.A02 = new AnonymousClass0Ls(r3.A01.timeout());
        this.A00 = j;
    }

    @Override // X.AbstractC07640v7, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        if (!this.A01) {
            this.A01 = true;
            if (this.A00 <= 0) {
                AnonymousClass0MU.A00(this.A02);
                this.A03.A00 = 3;
                return;
            }
            throw new ProtocolException("unexpected end of stream");
        }
    }

    @Override // X.AbstractC07640v7, java.io.Flushable
    public final void flush() throws IOException {
        if (!this.A01) {
            this.A03.A01.flush();
        }
    }

    @Override // X.AbstractC07640v7
    public final void write(AnonymousClass0B3 r8, long j) throws IOException {
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

    @Override // X.AbstractC07640v7
    public final C07620v5 timeout() {
        return this.A02;
    }
}
