package X;

import java.io.IOException;
import java.io.OutputStream;

/* renamed from: X.0OT  reason: invalid class name */
public class AnonymousClass0OT implements AnonymousClass0h1 {
    public final /* synthetic */ OutputStream A00;
    public final /* synthetic */ C04540gz A01;

    @Override // X.AnonymousClass0h1
    public final void write(AnonymousClass0HR r9, long j) throws IOException {
        long j2 = j;
        C04530gy.A00(r9.A00, 0, j2);
        while (j2 > 0) {
            this.A01.throwIfReached();
            C04570h3 r5 = r9.A01;
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
                C04560h2.A01(r5);
            }
        }
    }

    public AnonymousClass0OT(C04540gz r1, OutputStream outputStream) {
        this.A01 = r1;
        this.A00 = outputStream;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, X.AnonymousClass0h1
    public final void close() throws IOException {
        this.A00.close();
    }

    @Override // X.AnonymousClass0h1, java.io.Flushable
    public final void flush() throws IOException {
        this.A00.flush();
    }

    @Override // X.AnonymousClass0h1
    public final C04540gz timeout() {
        return this.A01;
    }

    public final String toString() {
        return "sink(" + this.A00 + ")";
    }
}
