package X;

import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.Deflater;

public final class K3 implements AbstractC0313cc {
    public boolean A00;
    public final CRC32 A01 = new CRC32();
    public final Deflater A02;
    public final KJ A03;
    public final KB A04;

    @Override // java.io.Closeable, java.lang.AutoCloseable, X.AbstractC0313cc
    public final void close() throws IOException {
        if (!this.A00) {
            Throwable th = null;
            try {
                KB kb = this.A04;
                kb.A01.finish();
                KB.A00(kb, false);
                KJ kj = this.A03;
                kj.A5u((int) this.A01.getValue());
                kj.A5u((int) this.A02.getBytesRead());
            } catch (Throwable th2) {
                th = th2;
            }
            try {
                this.A02.end();
            } catch (Throwable th3) {
                if (th == null) {
                    th = th3;
                }
            }
            try {
                this.A03.close();
            } catch (Throwable th4) {
                if (th == null) {
                    th = th4;
                }
            }
            this.A00 = true;
            if (th != null) {
                throw th;
            }
        }
    }

    @Override // X.AbstractC0313cc, java.io.Flushable
    public final void flush() throws IOException {
        this.A04.flush();
    }

    @Override // X.AbstractC0313cc
    public final ca timeout() {
        return this.A03.timeout();
    }

    @Override // X.AbstractC0313cc
    public final void write(AnonymousClass98 r10, long j) throws IOException {
        if (j < 0) {
            throw new IllegalArgumentException(AnonymousClass06.A03("byteCount < 0: ", j));
        } else if (j != 0) {
            long j2 = j;
            C0315ce ceVar = r10.A01;
            while (j2 > 0) {
                int i = ceVar.A00;
                int i2 = ceVar.A01;
                int min = (int) Math.min(j2, (long) (i - i2));
                this.A01.update(ceVar.A06, i2, min);
                j2 -= (long) min;
                ceVar = ceVar.A02;
            }
            this.A04.write(r10, j);
        }
    }

    public K3(AbstractC0313cc ccVar) {
        if (ccVar != null) {
            Deflater deflater = new Deflater(-1, true);
            this.A02 = deflater;
            AnonymousClass94 r1 = new AnonymousClass94(ccVar);
            this.A03 = r1;
            this.A04 = new KB(r1, deflater);
            AnonymousClass98 A1V = r1.A1V();
            A1V.A0C(8075);
            A1V.A09(8);
            A1V.A09(0);
            A1V.A0A(0);
            A1V.A09(0);
            A1V.A09(0);
            return;
        }
        throw new IllegalArgumentException("sink == null");
    }
}
