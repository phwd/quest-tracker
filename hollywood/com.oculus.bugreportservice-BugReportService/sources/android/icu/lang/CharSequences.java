package android.icu.lang;

public class CharSequences {
    public static int compare(CharSequence charSequence, int i) {
        int charAt;
        if (i < 0 || i > 1114111) {
            throw new IllegalArgumentException();
        }
        int length = charSequence.length();
        if (length == 0) {
            return -1;
        }
        char charAt2 = charSequence.charAt(0);
        int i2 = i - 65536;
        if (i2 < 0) {
            int i3 = charAt2 - i;
            return i3 != 0 ? i3 : length - 1;
        }
        int i4 = charAt2 - ((char) ((i2 >>> 10) + 55296));
        if (i4 != 0) {
            return i4;
        }
        return (length <= 1 || (charAt = charSequence.charAt(1) - ((char) ((i2 & 1023) + 56320))) == 0) ? length - 2 : charAt;
    }

    public static int getSingleCodePoint(CharSequence charSequence) {
        int length = charSequence.length();
        boolean z = true;
        if (length < 1 || length > 2) {
            return Integer.MAX_VALUE;
        }
        int codePointAt = Character.codePointAt(charSequence, 0);
        boolean z2 = codePointAt < 65536;
        if (length != 1) {
            z = false;
        }
        if (z2 == z) {
            return codePointAt;
        }
        return Integer.MAX_VALUE;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v1, resolved type: int[] */
    /* JADX DEBUG: Multi-variable search result rejected for r6v0, resolved type: int */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v1, types: [char] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int[] codePoints(java.lang.CharSequence r8) {
        /*
            int r0 = r8.length()
            int[] r0 = new int[r0]
            r1 = 0
            r2 = r1
            r3 = r2
        L_0x0009:
            int r4 = r8.length()
            if (r2 >= r4) goto L_0x003d
            char r4 = r8.charAt(r2)
            r5 = 56320(0xdc00, float:7.8921E-41)
            if (r4 < r5) goto L_0x0035
            r5 = 57343(0xdfff, float:8.0355E-41)
            if (r4 > r5) goto L_0x0035
            if (r2 == 0) goto L_0x0035
            int r5 = r3 + -1
            r6 = r0[r5]
            char r6 = (char) r6
            r7 = 55296(0xd800, float:7.7486E-41)
            if (r6 < r7) goto L_0x0035
            r7 = 56319(0xdbff, float:7.892E-41)
            if (r6 > r7) goto L_0x0035
            int r4 = java.lang.Character.toCodePoint(r6, r4)
            r0[r5] = r4
            goto L_0x003a
        L_0x0035:
            int r5 = r3 + 1
            r0[r3] = r4
            r3 = r5
        L_0x003a:
            int r2 = r2 + 1
            goto L_0x0009
        L_0x003d:
            int r8 = r0.length
            if (r3 != r8) goto L_0x0041
            return r0
        L_0x0041:
            int[] r8 = new int[r3]
            java.lang.System.arraycopy(r0, r1, r8, r1, r3)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.lang.CharSequences.codePoints(java.lang.CharSequence):int[]");
    }
}
