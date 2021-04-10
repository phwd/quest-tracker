package X;

import java.io.OutputStream;

/* renamed from: X.sy  reason: case insensitive filesystem */
public final class C1110sy implements AbstractC0608cr {
    public final /* synthetic */ OutputStream A00;
    public final /* synthetic */ C0610ct A01;

    @Override // X.AbstractC0608cr
    public final void A5k(AnonymousClass33 r9, long j) {
        long j2 = j;
        C0611cu.A00(r9.A00, 0, j2);
        while (j2 > 0) {
            this.A01.A05();
            C0606cp cpVar = r9.A01;
            int i = cpVar.A00;
            int i2 = cpVar.A01;
            int min = (int) Math.min(j2, (long) (i - i2));
            this.A00.write(cpVar.A06, i2, min);
            int i3 = cpVar.A01 + min;
            cpVar.A01 = i3;
            long j3 = (long) min;
            j2 -= j3;
            r9.A00 -= j3;
            if (i3 == cpVar.A00) {
                r9.A01 = cpVar.A00();
                C0607cq.A01(cpVar);
            }
        }
    }

    public C1110sy(C0610ct ctVar, OutputStream outputStream) {
        this.A01 = ctVar;
        this.A00 = outputStream;
    }

    @Override // java.io.Closeable, X.AbstractC0608cr, java.lang.AutoCloseable
    public final void close() {
        this.A00.close();
    }

    @Override // X.AbstractC0608cr, java.io.Flushable
    public final void flush() {
        this.A00.flush();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("sink(");
        sb.append(this.A00);
        sb.append(")");
        return sb.toString();
    }

    @Override // X.AbstractC0608cr
    public final C0610ct A5G() {
        return this.A01;
    }
}
