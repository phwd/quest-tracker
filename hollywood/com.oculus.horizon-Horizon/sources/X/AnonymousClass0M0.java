package X;

import java.io.IOException;

/* renamed from: X.0M0  reason: invalid class name */
public class AnonymousClass0M0 implements AbstractC07640v7 {
    public final /* synthetic */ AnonymousClass0Ly A00;
    public final /* synthetic */ AbstractC07640v7 A01;

    @Override // X.AbstractC07640v7
    public final void write(AnonymousClass0B3 r9, long j) throws IOException {
        long j2 = j;
        C07610v4.A00(r9.A00, 0, j2);
        while (true) {
            long j3 = 0;
            if (j2 > 0) {
                C07660v9 r5 = r9.A01;
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
                AnonymousClass0Ly r3 = this.A00;
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

    public AnonymousClass0M0(AnonymousClass0Ly r1, AbstractC07640v7 r2) {
        this.A00 = r1;
        this.A01 = r2;
    }

    @Override // X.AbstractC07640v7, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        AnonymousClass0Ly r2 = this.A00;
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

    @Override // X.AbstractC07640v7, java.io.Flushable
    public final void flush() throws IOException {
        AnonymousClass0Ly r2 = this.A00;
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

    public final String toString() {
        StringBuilder sb = new StringBuilder("AsyncTimeout.sink(");
        sb.append(this.A01);
        sb.append(")");
        return sb.toString();
    }

    @Override // X.AbstractC07640v7
    public final C07620v5 timeout() {
        return this.A00;
    }
}
