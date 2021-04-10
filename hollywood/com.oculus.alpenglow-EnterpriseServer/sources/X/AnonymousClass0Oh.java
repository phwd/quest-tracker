package X;

import java.io.IOException;

/* renamed from: X.0Oh  reason: invalid class name */
public class AnonymousClass0Oh implements AnonymousClass0h1 {
    public final /* synthetic */ AnonymousClass0Of A00;
    public final /* synthetic */ AnonymousClass0h1 A01;

    @Override // X.AnonymousClass0h1
    public final void write(AnonymousClass0HR r9, long j) throws IOException {
        long j2 = j;
        C04530gy.A00(r9.A00, 0, j2);
        while (true) {
            long j3 = 0;
            if (j2 > 0) {
                C04570h3 r5 = r9.A01;
                while (true) {
                    j3 += (long) (r5.A01 - r5.A02);
                    if (j3 < j2) {
                        r5 = r5.A00;
                        if (j3 >= 65536) {
                            break;
                        }
                    } else {
                        j3 = j2;
                        break;
                    }
                }
                AnonymousClass0Of r3 = this.A00;
                r3.enter();
                try {
                    this.A01.write(r9, j3);
                    j2 -= j3;
                    r3.exit(true);
                } catch (IOException e) {
                    throw r3.exit(e);
                } catch (Throwable th) {
                    r3.exit(false);
                    throw th;
                }
            } else {
                return;
            }
        }
    }

    public AnonymousClass0Oh(AnonymousClass0Of r1, AnonymousClass0h1 r2) {
        this.A00 = r1;
        this.A01 = r2;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, X.AnonymousClass0h1
    public final void close() throws IOException {
        AnonymousClass0Of r2 = this.A00;
        r2.enter();
        try {
            this.A01.close();
            r2.exit(true);
        } catch (IOException e) {
            throw r2.exit(e);
        } catch (Throwable th) {
            r2.exit(false);
            throw th;
        }
    }

    @Override // X.AnonymousClass0h1, java.io.Flushable
    public final void flush() throws IOException {
        AnonymousClass0Of r2 = this.A00;
        r2.enter();
        try {
            this.A01.flush();
            r2.exit(true);
        } catch (IOException e) {
            throw r2.exit(e);
        } catch (Throwable th) {
            r2.exit(false);
            throw th;
        }
    }

    @Override // X.AnonymousClass0h1
    public final C04540gz timeout() {
        return this.A00;
    }

    public final String toString() {
        return "AsyncTimeout.sink(" + this.A01 + ")";
    }
}
