package X;

import java.io.IOException;
import java.io.OutputStream;

/* renamed from: X.0Lp  reason: invalid class name */
public class AnonymousClass0Lp implements AbstractC07640v7 {
    public final /* synthetic */ OutputStream A00;
    public final /* synthetic */ C07620v5 A01;

    @Override // X.AbstractC07640v7
    public final void write(AnonymousClass0B3 r9, long j) throws IOException {
        long j2 = j;
        C07610v4.A00(r9.A00, 0, j2);
        while (j2 > 0) {
            this.A01.throwIfReached();
            C07660v9 r5 = r9.A01;
            int i = r5.A01;
            int i2 = r5.A02;
            int min = (int) Math.min(j2, (long) (i - i2));
            this.A00.write(r5.A06, i2, min);
            int i3 = r5.A02 + min;
            r5.A02 = i3;
            long j3 = (long) min;
            j2 -= j3;
            r9.A00 -= j3;
            if (i3 == r5.A01) {
                r9.A01 = r5.A00();
                C07650v8.A01(r5);
            }
        }
    }

    public AnonymousClass0Lp(C07620v5 r1, OutputStream outputStream) {
        this.A01 = r1;
        this.A00 = outputStream;
    }

    @Override // X.AbstractC07640v7, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.A00.close();
    }

    @Override // X.AbstractC07640v7, java.io.Flushable
    public final void flush() throws IOException {
        this.A00.flush();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("sink(");
        sb.append(this.A00);
        sb.append(")");
        return sb.toString();
    }

    @Override // X.AbstractC07640v7
    public final C07620v5 timeout() {
        return this.A01;
    }
}
