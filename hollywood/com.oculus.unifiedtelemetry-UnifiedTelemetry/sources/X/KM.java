package X;

import java.io.IOException;

public class KM implements AbstractC0313cc {
    public final /* synthetic */ KK A00;
    public final /* synthetic */ AbstractC0313cc A01;

    @Override // X.AbstractC0313cc
    public final void write(AnonymousClass98 r9, long j) throws IOException {
        long j2 = j;
        C0311cZ.A00(r9.A00, 0, j2);
        while (true) {
            long j3 = 0;
            if (j2 > 0) {
                C0315ce ceVar = r9.A01;
                while (true) {
                    j3 += (long) (ceVar.A00 - ceVar.A01);
                    if (j3 < j2) {
                        ceVar = ceVar.A02;
                        if (j3 >= 65536) {
                            break;
                        }
                    } else {
                        j3 = j2;
                        break;
                    }
                }
                KK kk = this.A00;
                kk.enter();
                try {
                    this.A01.write(r9, j3);
                    j2 -= j3;
                    kk.exit(true);
                } catch (IOException e) {
                    throw kk.exit(e);
                } catch (Throwable th) {
                    kk.exit(false);
                    throw th;
                }
            } else {
                return;
            }
        }
    }

    public KM(KK kk, AbstractC0313cc ccVar) {
        this.A00 = kk;
        this.A01 = ccVar;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, X.AbstractC0313cc
    public final void close() throws IOException {
        KK kk = this.A00;
        kk.enter();
        try {
            this.A01.close();
            kk.exit(true);
        } catch (IOException e) {
            throw kk.exit(e);
        } catch (Throwable th) {
            kk.exit(false);
            throw th;
        }
    }

    @Override // X.AbstractC0313cc, java.io.Flushable
    public final void flush() throws IOException {
        KK kk = this.A00;
        kk.enter();
        try {
            this.A01.flush();
            kk.exit(true);
        } catch (IOException e) {
            throw kk.exit(e);
        } catch (Throwable th) {
            kk.exit(false);
            throw th;
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("AsyncTimeout.sink(");
        sb.append(this.A01);
        sb.append(")");
        return sb.toString();
    }

    @Override // X.AbstractC0313cc
    public final ca timeout() {
        return this.A00;
    }
}
