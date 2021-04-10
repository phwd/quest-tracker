package X;

import java.net.ProtocolException;

/* renamed from: X.37  reason: invalid class name */
public final class AnonymousClass37 extends AbstractC1135tb {
    public long A00;
    public final /* synthetic */ tY A01;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass37(tY tYVar, long j) {
        super(tYVar);
        this.A01 = tYVar;
        this.A00 = j;
        if (j == 0) {
            A00(true);
        }
    }

    @Override // X.AbstractC0609cs
    public final long A4c(AnonymousClass33 r10, long j) {
        if (j < 0) {
            throw new IllegalArgumentException(AnonymousClass08.A03("byteCount < 0: ", j));
        } else if (!super.A00) {
            long j2 = this.A00;
            if (j2 == 0) {
                return -1;
            }
            long A4c = this.A01.A04.A4c(r10, Math.min(j2, j));
            if (A4c != -1) {
                long j3 = this.A00 - A4c;
                this.A00 = j3;
                if (j3 == 0) {
                    A00(true);
                }
                return A4c;
            }
            A00(false);
            throw new ProtocolException("unexpected end of stream");
        } else {
            throw new IllegalStateException("closed");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0014, code lost:
        if (X.C0561by.A0A(r5, 100, java.util.concurrent.TimeUnit.MILLISECONDS) == false) goto L_0x0016;
     */
    @Override // java.io.Closeable, java.lang.AutoCloseable, X.AbstractC0609cs
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void close() {
        /*
            r5 = this;
            boolean r0 = r5.A00
            if (r0 != 0) goto L_0x001d
            long r3 = r5.A00
            r1 = 0
            int r0 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r0 == 0) goto L_0x001a
            java.util.concurrent.TimeUnit r1 = java.util.concurrent.TimeUnit.MILLISECONDS
            r0 = 100
            boolean r0 = X.C0561by.A0A(r5, r0, r1)     // Catch:{ IOException -> 0x0016 }
            if (r0 != 0) goto L_0x001a
        L_0x0016:
            r0 = 0
            r5.A00(r0)
        L_0x001a:
            r0 = 1
            r5.A00 = r0
        L_0x001d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass37.close():void");
    }
}
