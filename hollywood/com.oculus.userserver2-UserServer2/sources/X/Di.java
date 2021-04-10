package X;

import java.io.IOException;
import java.io.OutputStream;

public class Di implements WG {
    public final /* synthetic */ OutputStream A00;
    public final /* synthetic */ WE A01;

    @Override // X.WG
    public final void write(AnonymousClass8k r9, long j) throws IOException {
        long j2 = j;
        WD.A00(r9.A00, 0, j2);
        while (j2 > 0) {
            this.A01.throwIfReached();
            WI wi = r9.A01;
            int i = wi.A00;
            int i2 = wi.A01;
            int min = (int) Math.min(j2, (long) (i - i2));
            this.A00.write(wi.A06, i2, min);
            int i3 = wi.A01 + min;
            wi.A01 = i3;
            long j3 = (long) min;
            j2 -= j3;
            r9.A00 -= j3;
            if (i3 == wi.A00) {
                r9.A01 = wi.A00();
                WH.A01(wi);
            }
        }
    }

    public Di(WE we, OutputStream outputStream) {
        this.A01 = we;
        this.A00 = outputStream;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, X.WG
    public final void close() throws IOException {
        this.A00.close();
    }

    @Override // X.WG, java.io.Flushable
    public final void flush() throws IOException {
        this.A00.flush();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("sink(");
        sb.append(this.A00);
        sb.append(")");
        return sb.toString();
    }

    @Override // X.WG
    public final WE timeout() {
        return this.A01;
    }
}
