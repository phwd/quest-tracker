package X;

import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.Inflater;

public final class K1 implements AbstractC0312cb {
    public int A00 = 0;
    public final CRC32 A01 = new CRC32();
    public final Inflater A02;
    public final KC A03;
    public final K0 A04;

    public static void A00(String str, int i, int i2) throws IOException {
        if (i2 != i) {
            throw new IOException(String.format("%s: actual 0x%08x != expected 0x%08x", str, Integer.valueOf(i2), Integer.valueOf(i)));
        }
    }

    private void A01(AnonymousClass98 r8, long j, long j2) {
        C0315ce ceVar = r8.A01;
        while (j >= ((long) (ceVar.A00 - ceVar.A01))) {
            j -= (long) (ceVar.A00 - ceVar.A01);
            ceVar = ceVar.A02;
        }
        while (j2 > 0) {
            int i = (int) (((long) ceVar.A01) + j);
            int min = (int) Math.min((long) (ceVar.A00 - i), j2);
            this.A01.update(ceVar.A06, i, min);
            j2 -= (long) min;
            ceVar = ceVar.A02;
            j = 0;
        }
    }

    @Override // java.io.Closeable, X.AbstractC0312cb, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.A04.close();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00d9, code lost:
        if (r0 == 2) goto L_0x00db;
     */
    @Override // X.AbstractC0312cb
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final long read(X.AnonymousClass98 r21, long r22) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 289
        */
        throw new UnsupportedOperationException("Method not decompiled: X.K1.read(X.98, long):long");
    }

    @Override // X.AbstractC0312cb
    public final ca timeout() {
        return this.A03.timeout();
    }

    public K1(AbstractC0312cb cbVar) {
        if (cbVar != null) {
            Inflater inflater = new Inflater(true);
            this.A02 = inflater;
            AnonymousClass93 r1 = new AnonymousClass93(cbVar);
            this.A03 = r1;
            this.A04 = new K0(r1, inflater);
            return;
        }
        throw new IllegalArgumentException("source == null");
    }
}
