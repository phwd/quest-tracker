package X;

import android.annotation.SuppressLint;

@SuppressLint({"CatchGeneralException", "DefaultLocale"})
/* renamed from: X.fj  reason: case insensitive filesystem */
public final class C0218fj {
    public static C0218fj A01;
    public AbstractC0201ew A00 = new SC();

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:18:0x003d */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r3v1, types: [java.util.List<java.lang.String>] */
    /* JADX WARN: Type inference failed for: r3v2, types: [java.util.AbstractCollection, java.util.ArrayList] */
    /* JADX WARNING: Unknown variable types count: 1 */
    @androidx.annotation.VisibleForTesting(otherwise = 2)
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.List<java.lang.String> A00(android.content.Context r4, java.lang.String r5) {
        /*
            java.util.List r3 = java.util.Collections.emptyList()
            android.content.pm.PackageManager r1 = r4.getPackageManager()     // Catch:{ NameNotFoundException -> 0x003e }
            r0 = 128(0x80, float:1.794E-43)
            android.content.pm.ApplicationInfo r0 = r1.getApplicationInfo(r5, r0)     // Catch:{ NameNotFoundException -> 0x003e }
            android.os.Bundle r1 = r0.metaData
            if (r1 == 0) goto L_0x003d
            int r0 = r1.size()
            if (r0 <= 0) goto L_0x003d
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            java.util.Set r0 = r1.keySet()
            java.util.Iterator r2 = r0.iterator()
        L_0x0025:
            boolean r0 = r2.hasNext()
            if (r0 == 0) goto L_0x003d
            java.lang.Object r1 = r2.next()
            java.lang.String r1 = (java.lang.String) r1
            java.lang.String r0 = ".*\\.fbpermission\\..*"
            boolean r0 = r1.matches(r0)
            if (r0 == 0) goto L_0x0025
            r3.add(r1)
            goto L_0x0025
        L_0x003d:
            return r3
        L_0x003e:
            java.util.List r0 = java.util.Collections.emptyList()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C0218fj.A00(android.content.Context, java.lang.String):java.util.List");
    }
}
