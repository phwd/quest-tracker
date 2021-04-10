package defpackage;

import com.oculus.os.Version;
import java.math.RoundingMode;

/* renamed from: JE1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class JE1 {
    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0044, code lost:
        if (((r7 == java.math.RoundingMode.HALF_EVEN) & ((r0 & 1) != 0)) != false) goto L_0x0057;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0047, code lost:
        if (r1 > 0) goto L_0x0057;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x004a, code lost:
        if (r5 > 0) goto L_0x0057;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x004d, code lost:
        if (r5 < 0) goto L_0x0057;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int a(int r5, int r6, java.math.RoundingMode r7) {
        /*
        // Method dump skipped, instructions count: 128
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.JE1.a(int, int, java.math.RoundingMode):int");
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    public static int b(int i, RoundingMode roundingMode) {
        if (i > 0) {
            boolean z = true;
            switch (IE1.f8210a[roundingMode.ordinal()]) {
                case 1:
                    boolean z2 = i > 0;
                    if (((i - 1) & i) != 0) {
                        z = false;
                    }
                    if (!z2 || !z) {
                        throw new ArithmeticException("mode was UNNECESSARY, but rounding was necessary");
                    }
                    break;
                case 2:
                case 3:
                    break;
                case 4:
                case 5:
                    return 32 - Integer.numberOfLeadingZeros(i - 1);
                case 6:
                case Version.VERSION_7 /*{ENCODED_INT: 7}*/:
                case Version.VERSION_8 /*{ENCODED_INT: 8}*/:
                    int numberOfLeadingZeros = Integer.numberOfLeadingZeros(i);
                    return (31 - numberOfLeadingZeros) + ((~(~((-1257966797 >>> numberOfLeadingZeros) - i))) >>> 31);
                default:
                    throw new AssertionError();
            }
            return 31 - Integer.numberOfLeadingZeros(i);
        }
        StringBuilder sb = new StringBuilder(27);
        sb.append("x");
        sb.append(" (");
        sb.append(i);
        sb.append(") must be > 0");
        throw new IllegalArgumentException(sb.toString());
    }
}
