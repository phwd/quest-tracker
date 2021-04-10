package X;

/* renamed from: X.hB  reason: case insensitive filesystem */
public final class C0780hB extends AnonymousClass9B {
    /* JADX WARNING: Removed duplicated region for block: B:4:0x0011 A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean A00(java.lang.String r9, java.lang.String r10) {
        /*
            int r8 = r9.length()
            int r7 = r10.length()
            int r1 = r7 - r8
            r6 = 0
            r5 = 1
            if (r1 >= 0) goto L_0x0013
            r0 = -1
            if (r1 != r0) goto L_0x0012
        L_0x0011:
            r6 = 1
        L_0x0012:
            return r6
        L_0x0013:
            r4 = 0
            r3 = 0
            r2 = 0
        L_0x0016:
            if (r4 >= r8) goto L_0x0039
            if (r3 >= r7) goto L_0x0039
            char r1 = r9.charAt(r4)
            char r0 = r10.charAt(r3)
            if (r1 == r0) goto L_0x0034
            if (r2 == r5) goto L_0x0012
            if (r8 <= r7) goto L_0x002d
            int r4 = r4 + 1
        L_0x002a:
            int r2 = r2 + 1
            goto L_0x0016
        L_0x002d:
            if (r8 < r7) goto L_0x0031
            int r4 = r4 + 1
        L_0x0031:
            int r3 = r3 + 1
            goto L_0x002a
        L_0x0034:
            int r4 = r4 + 1
            int r3 = r3 + 1
            goto L_0x0016
        L_0x0039:
            if (r4 >= r8) goto L_0x003d
            int r2 = r2 + 1
        L_0x003d:
            if (r2 > r5) goto L_0x0012
            goto L_0x0011
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C0780hB.A00(java.lang.String, java.lang.String):boolean");
    }

    public static boolean A01(String str, String str2, String str3) {
        int length = str.length();
        int length2 = str2.length();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (i < length && i2 < length2) {
            if (str.charAt(i) == str2.charAt(i2)) {
                i++;
                i2++;
            } else if (i3 == 1) {
                return false;
            } else {
                if (!str3.equalsIgnoreCase("inputPtr")) {
                    i2++;
                }
                i++;
                i3++;
            }
        }
        if (i < length) {
            i3++;
        }
        if (i3 <= 1) {
            return true;
        }
        return false;
    }

    public C0780hB(double d, double d2, double d3, double d4, double d5) {
        super(d, d2, d3, d4, d5);
    }
}
