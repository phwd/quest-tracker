package X;

import androidx.annotation.VisibleForTesting;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
/* renamed from: X.1iV  reason: invalid class name */
public final class AnonymousClass1iV implements AnonymousClass0JV {
    @GuardedBy("this")
    @VisibleForTesting
    public AbstractC00820Ju<AnonymousClass1iY> A00;
    public final int A01;

    private final synchronized void A00() {
        if (isClosed()) {
            throw new AnonymousClass0JU();
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final synchronized void close() {
        AbstractC00820Ju.A03(this.A00);
        this.A00 = null;
    }

    @Override // X.AnonymousClass0JV
    public final synchronized boolean isClosed() {
        return !AbstractC00820Ju.A04(this.A00);
    }

    @Override // X.AnonymousClass0JV
    public final synchronized int size() {
        A00();
        return this.A01;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0012, code lost:
        if (r4 > r3.A06().getSize()) goto L_0x0014;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public AnonymousClass1iV(X.AbstractC00820Ju<X.AnonymousClass1iY> r3, int r4) {
        /*
            r2 = this;
            r2.<init>()
            if (r3 == 0) goto L_0x0025
            if (r4 < 0) goto L_0x0014
            java.lang.Object r0 = r3.A06()
            X.1iY r0 = (X.AnonymousClass1iY) r0
            int r1 = r0.getSize()
            r0 = 1
            if (r4 <= r1) goto L_0x0015
        L_0x0014:
            r0 = 0
        L_0x0015:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            X.C00740Ii.A01(r0)
            X.0Ju r0 = r3.clone()
            r2.A00 = r0
            r2.A01 = r4
            return
        L_0x0025:
            r0 = 0
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1iV.<init>(X.0Ju, int):void");
    }

    @Override // X.AnonymousClass0JV
    public final synchronized int read(int i, byte[] bArr, int i2, int i3) {
        A00();
        boolean z = false;
        if (i + i3 <= this.A01) {
            z = true;
        }
        C00740Ii.A01(Boolean.valueOf(z));
        return this.A00.A06().read(i, bArr, i2, i3);
    }

    @Override // X.AnonymousClass0JV
    public final synchronized byte read(int i) {
        A00();
        boolean z = true;
        boolean z2 = false;
        if (i >= 0) {
            z2 = true;
        }
        C00740Ii.A01(Boolean.valueOf(z2));
        if (i >= this.A01) {
            z = false;
        }
        C00740Ii.A01(Boolean.valueOf(z));
        return this.A00.A06().read(i);
    }
}
