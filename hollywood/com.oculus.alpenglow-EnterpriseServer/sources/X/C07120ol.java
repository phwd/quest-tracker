package X;

import java.lang.reflect.Field;
import java.util.EnumMap;
import java.util.EnumSet;

/* renamed from: X.0ol  reason: invalid class name and case insensitive filesystem */
public class C07120ol {
    public static final C07120ol A02 = new C07120ol();
    public final Field A00 = A00(EnumMap.class);
    public final Field A01 = A00(EnumSet.class);

    /* JADX WARN: Incorrect args count in method signature: (Ljava/lang/Class<*>;Ljava/lang/String;Ljava/lang/Class<*>;)Ljava/lang/reflect/Field; */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0037, code lost:
        if (r2 != null) goto L_0x0020;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.reflect.Field A00(java.lang.Class r9) {
        /*
            java.lang.String r8 = "elementType"
            java.lang.Class<java.lang.Class> r7 = java.lang.Class.class
            java.lang.reflect.Field[] r6 = r9.getDeclaredFields()
            int r5 = r6.length
            r4 = 0
            r1 = 0
        L_0x000b:
            r3 = 0
            if (r1 >= r5) goto L_0x0025
            r2 = r6[r1]
            java.lang.String r0 = r2.getName()
            boolean r0 = r8.equals(r0)
            if (r0 == 0) goto L_0x0022
            java.lang.Class r0 = r2.getType()
            if (r0 != r7) goto L_0x0022
        L_0x0020:
            r0 = 1
            goto L_0x003a
        L_0x0022:
            int r1 = r1 + 1
            goto L_0x000b
        L_0x0025:
            r2 = r3
        L_0x0026:
            if (r4 >= r5) goto L_0x0037
            r1 = r6[r4]
            java.lang.Class r0 = r1.getType()
            if (r0 != r7) goto L_0x0034
            if (r2 == 0) goto L_0x0033
            return r3
        L_0x0033:
            r2 = r1
        L_0x0034:
            int r4 = r4 + 1
            goto L_0x0026
        L_0x0037:
            if (r2 == 0) goto L_0x003d
            goto L_0x0020
        L_0x003a:
            r2.setAccessible(r0)     // Catch:{ all -> 0x003d }
        L_0x003d:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C07120ol.A00(java.lang.Class):java.lang.reflect.Field");
    }
}
