package X;

import java.io.IOException;

public class Dx implements WG {
    public final /* synthetic */ Dv A00;
    public final /* synthetic */ WG A01;

    @Override // X.WG
    public final void write(AnonymousClass8k r9, long j) throws IOException {
        long j2 = j;
        WD.A00(r9.A00, 0, j2);
        while (true) {
            long j3 = 0;
            if (j2 > 0) {
                WI wi = r9.A01;
                while (true) {
                    j3 += (long) (wi.A00 - wi.A01);
                    if (j3 < j2) {
                        wi = wi.A02;
                        if (j3 >= 65536) {
                            break;
                        }
                    } else {
                        j3 = j2;
                        break;
                    }
                }
                Dv dv = this.A00;
                dv.enter();
                try {
                    this.A01.write(r9, j3);
                    j2 -= j3;
                    dv.exit(true);
                } catch (IOException e) {
                    throw dv.exit(e);
                } catch (Throwable th) {
                    dv.exit(false);
                    throw th;
                }
            } else {
                return;
            }
        }
    }

    public Dx(Dv dv, WG wg) {
        this.A00 = dv;
        this.A01 = wg;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, X.WG
    public final void close() throws IOException {
        Dv dv = this.A00;
        dv.enter();
        try {
            this.A01.close();
            dv.exit(true);
        } catch (IOException e) {
            throw dv.exit(e);
        } catch (Throwable th) {
            dv.exit(false);
            throw th;
        }
    }

    @Override // X.WG, java.io.Flushable
    public final void flush() throws IOException {
        Dv dv = this.A00;
        dv.enter();
        try {
            this.A01.flush();
            dv.exit(true);
        } catch (IOException e) {
            throw dv.exit(e);
        } catch (Throwable th) {
            dv.exit(false);
            throw th;
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("AsyncTimeout.sink(");
        sb.append(this.A01);
        sb.append(")");
        return sb.toString();
    }

    @Override // X.WG
    public final WE timeout() {
        return this.A00;
    }
}
