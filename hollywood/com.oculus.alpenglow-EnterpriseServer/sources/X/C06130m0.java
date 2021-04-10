package X;

import com.oculus.util.constants.OculusConstants;
import com.squareup.okhttp.internal.DiskLruCache;

/* renamed from: X.0m0  reason: invalid class name and case insensitive filesystem */
public final class C06130m0 {
    public static final char[] A00;
    public static final char[] A01;
    public static final String[] A02 = {OculusConstants.DEFAULT_ENTERPRISE_USER_ID, DiskLruCache.VERSION_1, "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    public static final String[] A03 = {"-1", "-2", "-3", "-4", "-5", "-6", "-7", "-8", "-9", "-10"};

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001e, code lost:
        if (r6 != 0) goto L_0x0020;
     */
    static {
        /*
        // Method dump skipped, instructions count: 126
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C06130m0.<clinit>():void");
    }

    public static int A00(int i, char[] cArr, int i2) {
        int A022;
        char c;
        if (i < 0) {
            if (i == Integer.MIN_VALUE) {
                return A03((long) i, cArr, i2);
            }
            cArr[i2] = '-';
            i = -i;
            i2++;
        }
        if (i >= 1000000) {
            boolean z = false;
            int i3 = i2;
            if (i >= 1000000000) {
                z = true;
                i -= 1000000000;
                if (i >= 1000000000) {
                    i -= 1000000000;
                    i3 = i2 + 1;
                    c = '2';
                } else {
                    i3 = i2 + 1;
                    c = '1';
                }
                cArr[i2] = c;
            }
            int i4 = i / 1000;
            int i5 = i - (i4 * 1000);
            int i6 = i4 / 1000;
            int i7 = i4 - (i6 * 1000);
            if (z) {
                A022 = A01(i6, cArr, i3);
            } else {
                A022 = A02(i6, cArr, i3);
            }
            return A01(i5, cArr, A01(i7, cArr, A022));
        } else if (i >= 1000) {
            int i8 = i / 1000;
            return A01(i - (i8 * 1000), cArr, A02(i8, cArr, i2));
        } else if (i >= 10) {
            return A02(i, cArr, i2);
        } else {
            int i9 = i2 + 1;
            cArr[i2] = (char) (i + 48);
            return i9;
        }
    }

    public static int A01(int i, char[] cArr, int i2) {
        int i3 = i << 2;
        int i4 = i2 + 1;
        char[] cArr2 = A00;
        int i5 = i3 + 1;
        cArr[i2] = cArr2[i3];
        int i6 = i4 + 1;
        int i7 = i5 + 1;
        cArr[i4] = cArr2[i5];
        int i8 = i6 + 1;
        cArr[i6] = cArr2[i7];
        return i8;
    }

    public static int A02(int i, char[] cArr, int i2) {
        int i3 = i << 2;
        char[] cArr2 = A01;
        int i4 = i3 + 1;
        char c = cArr2[i3];
        if (c != 0) {
            cArr[i2] = c;
            i2++;
        }
        int i5 = i4 + 1;
        char c2 = cArr2[i4];
        if (c2 != 0) {
            cArr[i2] = c2;
            i2++;
        }
        int i6 = i2 + 1;
        cArr[i2] = cArr2[i5];
        return i6;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0024, code lost:
        if (r7 <= 2147483647L) goto L_0x0026;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int A03(long r7, char[] r9, int r10) {
        /*
        // Method dump skipped, instructions count: 120
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C06130m0.A03(long, char[], int):int");
    }
}
