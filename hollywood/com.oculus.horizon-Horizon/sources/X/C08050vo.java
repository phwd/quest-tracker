package X;

import java.util.regex.Pattern;

/* renamed from: X.0vo  reason: invalid class name and case insensitive filesystem */
public final class C08050vo {
    public static final Pattern A00 = Pattern.compile(" +([^ \"=]*)=(:?\"([^\"]*)\"|([^ \"=]*)) *(:?,|$)");

    /* JADX WARNING: Removed duplicated region for block: B:232:0x00c9 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void A02(X.AbstractC08470wd r47, X.C08400wU r48, X.AnonymousClass0wX r49) {
        /*
        // Method dump skipped, instructions count: 1003
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C08050vo.A02(X.0wd, X.0wU, X.0wX):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0035, code lost:
        if (r3 == -1) goto L_0x0037;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean A03(X.C08220wC r7) {
        /*
            X.0wN r0 = r7.A07
            java.lang.String r1 = r0.A01
            java.lang.String r0 = "HEAD"
            boolean r0 = r1.equals(r0)
            r6 = 0
            if (r0 != 0) goto L_0x0046
            int r1 = r7.A01
            r0 = 100
            r5 = 1
            if (r1 < r0) goto L_0x0018
            r0 = 200(0xc8, float:2.8E-43)
            if (r1 < r0) goto L_0x0021
        L_0x0018:
            r0 = 204(0xcc, float:2.86E-43)
            if (r1 == r0) goto L_0x0021
            r0 = 304(0x130, float:4.26E-43)
            if (r1 == r0) goto L_0x0021
        L_0x0020:
            return r5
        L_0x0021:
            X.0wX r1 = r7.A06
            java.lang.String r0 = "Content-Length"
            java.lang.String r0 = r1.A00(r0)
            r3 = -1
            if (r0 == 0) goto L_0x0037
            long r3 = java.lang.Long.parseLong(r0)     // Catch:{ NumberFormatException -> 0x0031 }
        L_0x0031:
            r1 = -1
            int r0 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r0 != 0) goto L_0x0020
        L_0x0037:
            java.lang.String r0 = "Transfer-Encoding"
            java.lang.String r1 = r7.A00(r0)
            java.lang.String r0 = "chunked"
            boolean r0 = r0.equalsIgnoreCase(r1)
            if (r0 == 0) goto L_0x0046
            return r5
        L_0x0046:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C08050vo.A03(X.0wC):boolean");
    }

    public static int A00(String str, int i) {
        try {
            long parseLong = Long.parseLong(str);
            if (parseLong > 2147483647L) {
                return Integer.MAX_VALUE;
            }
            if (parseLong < 0) {
                return 0;
            }
            return (int) parseLong;
        } catch (NumberFormatException unused) {
            return i;
        }
    }

    public static int A01(String str, int i, String str2) {
        while (i < str.length() && str2.indexOf(str.charAt(i)) == -1) {
            i++;
        }
        return i;
    }
}
