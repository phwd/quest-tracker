package X;

/* renamed from: X.0k2  reason: invalid class name */
public final class AnonymousClass0k2 {
    public static final String A00 = "-9223372036854775808".substring(1);

    /* JADX WARNING: Code restructure failed: missing block: B:29:0x005a, code lost:
        if (r6 > 9) goto L_0x005c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int A01(java.lang.String r8) {
        /*
        // Method dump skipped, instructions count: 101
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0k2.A01(java.lang.String):int");
    }

    public static double A00(String str) throws NumberFormatException {
        if ("2.2250738585072012e-308".equals(str)) {
            return Double.MIN_VALUE;
        }
        return Double.parseDouble(str);
    }

    public static int A02(char[] cArr, int i, int i2) {
        int i3 = cArr[i] - '0';
        int i4 = i2 + i;
        int i5 = i + 1;
        if (i5 >= i4) {
            return i3;
        }
        int i6 = (i3 * 10) + (cArr[i5] - '0');
        int i7 = i5 + 1;
        if (i7 >= i4) {
            return i6;
        }
        int i8 = (i6 * 10) + (cArr[i7] - '0');
        int i9 = i7 + 1;
        if (i9 >= i4) {
            return i8;
        }
        int i10 = (i8 * 10) + (cArr[i9] - '0');
        int i11 = i9 + 1;
        if (i11 >= i4) {
            return i10;
        }
        int i12 = (i10 * 10) + (cArr[i11] - '0');
        int i13 = i11 + 1;
        if (i13 >= i4) {
            return i12;
        }
        int i14 = (i12 * 10) + (cArr[i13] - '0');
        int i15 = i13 + 1;
        if (i15 >= i4) {
            return i14;
        }
        int i16 = (i14 * 10) + (cArr[i15] - '0');
        int i17 = i15 + 1;
        if (i17 >= i4) {
            return i16;
        }
        int i18 = (i16 * 10) + (cArr[i17] - '0');
        int i19 = i17 + 1;
        if (i19 < i4) {
            return (i18 * 10) + (cArr[i19] - '0');
        }
        return i18;
    }
}
