package X;

import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.Inflater;

public final class t2 implements AbstractC0609cs {
    public int A00 = 0;
    public final CRC32 A01 = new CRC32();
    public final Inflater A02;
    public final t4 A03;
    public final C1111t0 A04;

    public static void A00(String str, int i, int i2) {
        if (i2 != i) {
            throw new IOException(String.format("%s: actual 0x%08x != expected 0x%08x", str, Integer.valueOf(i2), Integer.valueOf(i)));
        }
    }

    private void A01(AnonymousClass33 r8, long j, long j2) {
        C0606cp cpVar = r8.A01;
        while (j >= ((long) (cpVar.A00 - cpVar.A01))) {
            j -= (long) (cpVar.A00 - cpVar.A01);
            cpVar = cpVar.A02;
        }
        while (j2 > 0) {
            int i = (int) (((long) cpVar.A01) + j);
            int min = (int) Math.min((long) (cpVar.A00 - i), j2);
            this.A01.update(cpVar.A06, i, min);
            j2 -= (long) min;
            cpVar = cpVar.A02;
            j = 0;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00d9, code lost:
        if (r0 == 2) goto L_0x00db;
     */
    @Override // X.AbstractC0609cs
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final long A4c(X.AnonymousClass33 r21, long r22) {
        /*
        // Method dump skipped, instructions count: 295
        */
        throw new UnsupportedOperationException("Method not decompiled: X.t2.A4c(X.33, long):long");
    }

    @Override // X.AbstractC0609cs
    public final C0610ct A5G() {
        return this.A03.A5G();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, X.AbstractC0609cs
    public final void close() {
        this.A04.close();
    }

    public t2(AbstractC0609cs csVar) {
        if (csVar != null) {
            Inflater inflater = new Inflater(true);
            this.A02 = inflater;
            C00222y r1 = new C00222y(csVar);
            this.A03 = r1;
            this.A04 = new C1111t0(r1, inflater);
            return;
        }
        throw new IllegalArgumentException("source == null");
    }
}
