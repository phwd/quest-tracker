package X;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: X.1fA  reason: invalid class name and case insensitive filesystem */
public final class C08721fA {
    public final List<String> A00 = new ArrayList();
    public final Map<String, List<C08791fJ<?, ?>>> A01 = new HashMap();

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0041, code lost:
        if (r8.isAssignableFrom(r2.A02) == false) goto L_0x0043;
     */
    @androidx.annotation.NonNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized <T, R> java.util.List<java.lang.Class<R>> A00(@androidx.annotation.NonNull java.lang.Class<T> r7, @androidx.annotation.NonNull java.lang.Class<R> r8) {
        /*
            r6 = this;
            monitor-enter(r6)
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ all -> 0x0054 }
            r4.<init>()     // Catch:{ all -> 0x0054 }
            java.util.List<java.lang.String> r0 = r6.A00     // Catch:{ all -> 0x0054 }
            java.util.Iterator r5 = r0.iterator()     // Catch:{ all -> 0x0054 }
        L_0x000c:
            boolean r0 = r5.hasNext()     // Catch:{ all -> 0x0054 }
            if (r0 == 0) goto L_0x0052
            java.lang.Object r1 = r5.next()     // Catch:{ all -> 0x0054 }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ all -> 0x0054 }
            java.util.Map<java.lang.String, java.util.List<X.1fJ<?, ?>>> r0 = r6.A01     // Catch:{ all -> 0x0054 }
            java.lang.Object r0 = r0.get(r1)     // Catch:{ all -> 0x0054 }
            java.util.List r0 = (java.util.List) r0     // Catch:{ all -> 0x0054 }
            if (r0 == 0) goto L_0x000c
            java.util.Iterator r3 = r0.iterator()     // Catch:{ all -> 0x0054 }
        L_0x0026:
            boolean r0 = r3.hasNext()     // Catch:{ all -> 0x0054 }
            if (r0 == 0) goto L_0x000c
            java.lang.Object r2 = r3.next()     // Catch:{ all -> 0x0054 }
            X.1fJ r2 = (X.C08791fJ) r2     // Catch:{ all -> 0x0054 }
            java.lang.Class<T> r0 = r2.A01     // Catch:{ all -> 0x0054 }
            boolean r0 = r0.isAssignableFrom(r7)     // Catch:{ all -> 0x0054 }
            if (r0 == 0) goto L_0x0043
            java.lang.Class<R> r0 = r2.A02     // Catch:{ all -> 0x0054 }
            boolean r1 = r8.isAssignableFrom(r0)     // Catch:{ all -> 0x0054 }
            r0 = 1
            if (r1 != 0) goto L_0x0044
        L_0x0043:
            r0 = 0
        L_0x0044:
            if (r0 == 0) goto L_0x0026
            java.lang.Class<R> r1 = r2.A02     // Catch:{ all -> 0x0054 }
            boolean r0 = r4.contains(r1)     // Catch:{ all -> 0x0054 }
            if (r0 != 0) goto L_0x0026
            r4.add(r1)     // Catch:{ all -> 0x0054 }
            goto L_0x0026
        L_0x0052:
            monitor-exit(r6)
            return r4
        L_0x0054:
            r0 = move-exception
            monitor-exit(r6)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C08721fA.A00(java.lang.Class, java.lang.Class):java.util.List");
    }
}
