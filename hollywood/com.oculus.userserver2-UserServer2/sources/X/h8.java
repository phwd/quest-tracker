package X;

import java.util.TimeZone;

public final class h8 {
    public static final TimeZone A00 = TimeZone.getTimeZone("UTC");

    /* JADX WARNING: Removed duplicated region for block: B:10:0x001f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int A00(java.lang.String r5, int r6, int r7) throws java.lang.NumberFormatException {
        /*
            if (r6 < 0) goto L_0x0043
            int r0 = r5.length()
            if (r7 > r0) goto L_0x0043
            if (r6 > r7) goto L_0x0043
            java.lang.String r4 = "Invalid number: "
            r3 = 10
            if (r6 >= r7) goto L_0x0030
            int r1 = r6 + 1
            char r0 = r5.charAt(r6)
            int r0 = java.lang.Character.digit(r0, r3)
            if (r0 < 0) goto L_0x0035
            int r0 = -r0
        L_0x001d:
            if (r1 >= r7) goto L_0x0033
            int r2 = r1 + 1
            char r1 = r5.charAt(r1)
            int r1 = java.lang.Character.digit(r1, r3)
            if (r1 < 0) goto L_0x0035
            int r0 = r0 * 10
            int r0 = r0 - r1
            r1 = r2
            goto L_0x001d
        L_0x0030:
            r1 = r6
            r0 = 0
            goto L_0x001d
        L_0x0033:
            int r0 = -r0
            return r0
        L_0x0035:
            java.lang.String r0 = r5.substring(r6, r7)
            java.lang.String r1 = X.AnonymousClass06.A03(r4, r0)
            java.lang.NumberFormatException r0 = new java.lang.NumberFormatException
            r0.<init>(r1)
            throw r0
        L_0x0043:
            java.lang.NumberFormatException r0 = new java.lang.NumberFormatException
            r0.<init>(r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.h8.A00(java.lang.String, int, int):int");
    }

    public static boolean A01(String str, int i, char c) {
        if (i >= str.length() || str.charAt(i) != c) {
            return false;
        }
        return true;
    }
}
