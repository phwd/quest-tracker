package X;

import java.util.zip.Inflater;

/* renamed from: X.t0  reason: case insensitive filesystem */
public final class C1111t0 implements AbstractC0609cs {
    public int A00;
    public boolean A01;
    public final Inflater A02;
    public final t4 A03;

    /* JADX WARNING: Removed duplicated region for block: B:22:0x005d A[Catch:{ DataFormatException -> 0x00af }] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0051 A[SYNTHETIC] */
    @Override // X.AbstractC0609cs
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final long A4c(X.AnonymousClass33 r8, long r9) {
        /*
        // Method dump skipped, instructions count: 210
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C1111t0.A4c(X.33, long):long");
    }

    @Override // X.AbstractC0609cs
    public final C0610ct A5G() {
        return this.A03.A5G();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, X.AbstractC0609cs
    public final void close() {
        if (!this.A01) {
            this.A02.end();
            this.A01 = true;
            this.A03.close();
        }
    }

    public C1111t0(t4 t4Var, Inflater inflater) {
        this.A03 = t4Var;
        this.A02 = inflater;
    }
}
