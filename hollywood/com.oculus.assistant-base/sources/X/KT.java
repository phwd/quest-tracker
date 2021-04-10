package X;

import java.io.Closeable;

public abstract class KT implements Closeable {
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0019 A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean A00() {
        /*
            r4 = this;
            boolean r0 = r4 instanceof X.C0978pu
            if (r0 != 0) goto L_0x0045
            boolean r0 = r4 instanceof X.C0976ps
            if (r0 != 0) goto L_0x002c
            boolean r0 = r4 instanceof X.C0970pm
            if (r0 != 0) goto L_0x001b
            r0 = r4
            X.pi r0 = (X.C0966pi) r0
            int r2 = r0.A00
            X.pj r0 = r0.A01
            X.pk[] r0 = r0.A00
            int r1 = r0.length
            r0 = 0
            if (r2 >= r1) goto L_0x001a
        L_0x0019:
            r0 = 1
        L_0x001a:
            return r0
        L_0x001b:
            r1 = r4
            X.pm r1 = (X.C0970pm) r1
            X.pn r0 = r1.A01
            r0.A01()
            int r2 = r1.A00
            X.pl[] r0 = r0.A00
            int r1 = r0.length
            r0 = 0
            if (r2 >= r1) goto L_0x001a
            goto L_0x0019
        L_0x002c:
            r3 = r4
            X.ps r3 = (X.C0976ps) r3
            int r2 = r3.A00
        L_0x0031:
            X.pt r0 = r3.A02
            X.pr[] r0 = r0.A02
            int r1 = r0.length
            if (r2 >= r1) goto L_0x0041
            r0 = r0[r2]
            boolean r0 = r0.A00
            if (r0 != 0) goto L_0x0041
            int r2 = r2 + 1
            goto L_0x0031
        L_0x0041:
            r0 = 0
            if (r2 >= r1) goto L_0x001a
            goto L_0x0019
        L_0x0045:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.KT.A00():boolean");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        if (this instanceof C0976ps) {
            ((C0976ps) this).A01.close();
        }
    }
}
