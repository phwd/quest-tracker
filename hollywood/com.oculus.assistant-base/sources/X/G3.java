package X;

import java.util.Collections;
import java.util.Set;

public abstract class G3 extends RO implements AbstractC1084s8 {
    public final RQ A00;
    public final Set A01;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public G3(android.content.Context r13, android.os.Looper r14, int r15, X.RQ r16, X.Qq r17, X.AbstractC0323Qx r18) {
        /*
            r12 = this;
            java.lang.Object r2 = X.RS.A01
            monitor-enter(r2)
            X.RS r0 = X.RS.A00     // Catch:{ all -> 0x0061 }
            r4 = r13
            if (r0 != 0) goto L_0x0013
            android.content.Context r1 = r13.getApplicationContext()     // Catch:{ all -> 0x0061 }
            X.sc r0 = new X.sc     // Catch:{ all -> 0x0061 }
            r0.<init>(r1)     // Catch:{ all -> 0x0061 }
            X.RS.A00 = r0     // Catch:{ all -> 0x0061 }
        L_0x0013:
            monitor-exit(r2)     // Catch:{ all -> 0x0061 }
            X.RS r6 = X.RS.A00
            com.google.android.gms.common.GoogleApiAvailability r7 = com.google.android.gms.common.GoogleApiAvailability.A00
            r1 = r17
            X.RZ.A01(r1)
            r0 = r18
            X.RZ.A01(r0)
            if (r17 != 0) goto L_0x0058
            r9 = 0
        L_0x0025:
            if (r18 != 0) goto L_0x0052
            r10 = 0
        L_0x0028:
            r0 = r16
            java.lang.String r11 = r0.A03
            r3 = r12
            r5 = r14
            r8 = r15
            r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11)
            r12.A00 = r0
            java.util.Set r2 = r0.A05
            java.util.Iterator r1 = r2.iterator()
        L_0x003a:
            boolean r0 = r1.hasNext()
            if (r0 == 0) goto L_0x005e
            java.lang.Object r0 = r1.next()
            boolean r0 = r2.contains(r0)
            if (r0 != 0) goto L_0x003a
            java.lang.String r1 = "Expanding scopes is not permitted, use implied scopes instead"
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>(r1)
            throw r0
        L_0x0052:
            X.sZ r10 = new X.sZ
            r10.<init>(r0)
            goto L_0x0028
        L_0x0058:
            X.sY r9 = new X.sY
            r9.<init>(r1)
            goto L_0x0025
        L_0x005e:
            r12.A01 = r2
            return
        L_0x0061:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.G3.<init>(android.content.Context, android.os.Looper, int, X.RQ, X.Qq, X.Qx):void");
    }

    @Override // X.AbstractC1084s8
    public final Set A2r() {
        if (A4r()) {
            return this.A01;
        }
        return Collections.emptySet();
    }
}
