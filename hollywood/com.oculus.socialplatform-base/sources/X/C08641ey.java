package X;

import java.util.ArrayList;
import java.util.List;

/* renamed from: X.1ey  reason: invalid class name and case insensitive filesystem */
public final class C08641ey {
    public final List<C08631ex<?, ?>> A00 = new ArrayList();

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0031, code lost:
        if (r6.isAssignableFrom(r1.A02) == false) goto L_0x0033;
     */
    @androidx.annotation.NonNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized <Z, R> java.util.List<java.lang.Class<R>> A00(@androidx.annotation.NonNull java.lang.Class<Z> r5, @androidx.annotation.NonNull java.lang.Class<R> r6) {
        /*
            r4 = this;
            monitor-enter(r4)
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ all -> 0x003c }
            r3.<init>()     // Catch:{ all -> 0x003c }
            boolean r0 = r6.isAssignableFrom(r5)     // Catch:{ all -> 0x003c }
            if (r0 == 0) goto L_0x0010
            r3.add(r6)     // Catch:{ all -> 0x003c }
            goto L_0x003a
        L_0x0010:
            java.util.List<X.1ex<?, ?>> r0 = r4.A00     // Catch:{ all -> 0x003c }
            java.util.Iterator r2 = r0.iterator()     // Catch:{ all -> 0x003c }
        L_0x0016:
            boolean r0 = r2.hasNext()     // Catch:{ all -> 0x003c }
            if (r0 == 0) goto L_0x003a
            java.lang.Object r1 = r2.next()     // Catch:{ all -> 0x003c }
            X.1ex r1 = (X.C08631ex) r1     // Catch:{ all -> 0x003c }
            java.lang.Class<Z> r0 = r1.A01     // Catch:{ all -> 0x003c }
            boolean r0 = r0.isAssignableFrom(r5)     // Catch:{ all -> 0x003c }
            if (r0 == 0) goto L_0x0033
            java.lang.Class<R> r0 = r1.A02     // Catch:{ all -> 0x003c }
            boolean r1 = r6.isAssignableFrom(r0)     // Catch:{ all -> 0x003c }
            r0 = 1
            if (r1 != 0) goto L_0x0034
        L_0x0033:
            r0 = 0
        L_0x0034:
            if (r0 == 0) goto L_0x0016
            r3.add(r6)     // Catch:{ all -> 0x003c }
            goto L_0x0016
        L_0x003a:
            monitor-exit(r4)
            return r3
        L_0x003c:
            r0 = move-exception
            monitor-exit(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C08641ey.A00(java.lang.Class, java.lang.Class):java.util.List");
    }
}
