package X;

import java.io.IOException;
import java.io.OutputStream;

public class Jz implements AbstractC0313cc {
    public final /* synthetic */ OutputStream A00;
    public final /* synthetic */ ca A01;

    @Override // X.AbstractC0313cc
    public final void write(AnonymousClass98 r9, long j) throws IOException {
        long j2 = j;
        C0311cZ.A00(r9.A00, 0, j2);
        while (j2 > 0) {
            this.A01.throwIfReached();
            C0315ce ceVar = r9.A01;
            int i = ceVar.A00;
            int i2 = ceVar.A01;
            int min = (int) Math.min(j2, (long) (i - i2));
            this.A00.write(ceVar.A06, i2, min);
            int i3 = ceVar.A01 + min;
            ceVar.A01 = i3;
            long j3 = (long) min;
            j2 -= j3;
            r9.A00 -= j3;
            if (i3 == ceVar.A00) {
                r9.A01 = ceVar.A00();
                C0314cd.A01(ceVar);
            }
        }
    }

    public Jz(ca caVar, OutputStream outputStream) {
        this.A01 = caVar;
        this.A00 = outputStream;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, X.AbstractC0313cc
    public final void close() throws IOException {
        this.A00.close();
    }

    @Override // X.AbstractC0313cc, java.io.Flushable
    public final void flush() throws IOException {
        this.A00.flush();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("sink(");
        sb.append(this.A00);
        sb.append(")");
        return sb.toString();
    }

    @Override // X.AbstractC0313cc
    public final ca timeout() {
        return this.A01;
    }
}
