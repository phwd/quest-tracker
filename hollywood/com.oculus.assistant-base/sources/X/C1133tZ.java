package X;

import java.net.ProtocolException;

/* renamed from: X.tZ  reason: case insensitive filesystem */
public final class C1133tZ implements AbstractC0608cr {
    public long A00;
    public boolean A01;
    public final t3 A02;
    public final /* synthetic */ tY A03;

    public C1133tZ(tY tYVar, long j) {
        this.A03 = tYVar;
        this.A02 = new t3(tYVar.A01.A5G());
        this.A00 = j;
    }

    @Override // X.AbstractC0608cr
    public final void A5k(AnonymousClass33 r8, long j) {
        if (!this.A01) {
            long j2 = r8.A00;
            if ((0 | j) < 0 || 0 > j2 || j2 - 0 < j) {
                throw new ArrayIndexOutOfBoundsException();
            }
            long j3 = this.A00;
            if (j <= j3) {
                this.A03.A01.A5k(r8, j);
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

    @Override // java.io.Closeable, X.AbstractC0608cr, java.lang.AutoCloseable
    public final void close() {
        if (!this.A01) {
            this.A01 = true;
            if (this.A00 <= 0) {
                t3 t3Var = this.A02;
                C0610ct ctVar = t3Var.A00;
                t3Var.A00 = C0610ct.A03;
                ctVar.A01();
                ctVar.A02();
                this.A03.A00 = 3;
                return;
            }
            throw new ProtocolException("unexpected end of stream");
        }
    }

    @Override // X.AbstractC0608cr, java.io.Flushable
    public final void flush() {
        if (!this.A01) {
            this.A03.A01.flush();
        }
    }

    @Override // X.AbstractC0608cr
    public final C0610ct A5G() {
        return this.A02;
    }
}
