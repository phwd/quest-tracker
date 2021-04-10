package X;

import androidx.annotation.VisibleForTesting;
import com.facebook.infer.annotation.Nullsafe;
import java.io.Closeable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1qp  reason: invalid class name and case insensitive filesystem */
public final class C10021qp implements Closeable {
    @GuardedBy("this")
    @VisibleForTesting
    public AnonymousClass1qa<AbstractC10321rv> A00;
    public final int A01;

    private final synchronized void A00() {
        if (!AnonymousClass1qa.A02(this.A00)) {
            throw new AnonymousClass1uM();
        }
    }

    public final synchronized byte A01(int i) {
        A00();
        boolean z = true;
        boolean z2 = false;
        if (i >= 0) {
            z2 = true;
        }
        AnonymousClass0KU.A01(Boolean.valueOf(z2));
        if (i >= this.A01) {
            z = false;
        }
        AnonymousClass0KU.A01(Boolean.valueOf(z));
        return this.A00.A04().read(i);
    }

    public final synchronized int A02() {
        A00();
        return this.A01;
    }

    public final synchronized void A03(int i, byte[] bArr, int i2, int i3) {
        A00();
        boolean z = false;
        if (i + i3 <= this.A01) {
            z = true;
        }
        AnonymousClass0KU.A01(Boolean.valueOf(z));
        this.A00.A04().read(i, bArr, i2, i3);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final synchronized void close() {
        AnonymousClass1qa<AbstractC10321rv> r0 = this.A00;
        if (r0 != null) {
            r0.close();
        }
        this.A00 = null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0012, code lost:
        if (r4 > r3.A04().getSize()) goto L_0x0014;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public C10021qp(X.AnonymousClass1qa<X.AbstractC10321rv> r3, int r4) {
        /*
            r2 = this;
            r2.<init>()
            if (r3 == 0) goto L_0x0025
            if (r4 < 0) goto L_0x0014
            java.lang.Object r0 = r3.A04()
            X.1rv r0 = (X.AbstractC10321rv) r0
            int r1 = r0.getSize()
            r0 = 1
            if (r4 <= r1) goto L_0x0015
        L_0x0014:
            r0 = 0
        L_0x0015:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            X.AnonymousClass0KU.A01(r0)
            X.1qa r0 = r3.clone()
            r2.A00 = r0
            r2.A01 = r4
            return
        L_0x0025:
            r0 = 0
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C10021qp.<init>(X.1qa, int):void");
    }
}
