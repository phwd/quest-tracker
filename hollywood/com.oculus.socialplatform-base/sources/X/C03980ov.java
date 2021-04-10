package X;

import java.util.regex.Pattern;

/* renamed from: X.0ov  reason: invalid class name and case insensitive filesystem */
public final class C03980ov {
    public static final Pattern A01 = Pattern.compile("[-_./;:]");
    public final C03690oJ A00;

    public static C03690oJ A02(String str, String str2, String str3) {
        int i;
        String str4 = null;
        if (str != null) {
            String trim = str.trim();
            if (trim.length() != 0) {
                String[] split = A01.split(trim);
                int i2 = 0;
                int A002 = A00(split[0]);
                int length = split.length;
                if (length > 1) {
                    i = A00(split[1]);
                } else {
                    i = 0;
                }
                if (length > 2) {
                    i2 = A00(split[2]);
                }
                if (length > 3) {
                    str4 = split[3];
                }
                return new C03690oJ(A002, i, i2, str4, str2, str3);
            }
        }
        return null;
    }

    public static final void A03() {
        throw new RuntimeException("Internal error: this code path should never get executed");
    }

    public C03980ov() {
        C03690oJ r0;
        try {
            r0 = A01(getClass()) == null ? C03690oJ.A00 : r0;
        } catch (Exception unused) {
            System.err.println(AnonymousClass006.A09("ERROR: Failed to load Version information for bundle (via ", getClass().getName(), ")."));
        }
        this.A00 = r0;
    }

    public static int A00(String str) {
        int length = str.length();
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            char charAt = str.charAt(i2);
            if (charAt > '9' || charAt < '0') {
                break;
            }
            i = (i * 10) + (charAt - '0');
        }
        return i;
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Can't wrap try/catch for region: R(17:22|23|24|25|26|(3:27|28|(13:30|31|(2:33|34)|44|45|(1:48)|(1:50)|51|52|53|54|55|59))|35|44|45|(0)|(0)|51|52|53|54|55|59) */
    /* JADX WARNING: Can't wrap try/catch for region: R(5:65|66|67|68|72) */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0088, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x008d, code lost:
        r1 = null;
        r0 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00a9, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x00af, code lost:
        throw new java.lang.RuntimeException(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:?, code lost:
        r0 = X.C03690oJ.A00;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:?, code lost:
        r5.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x00bc, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x00c2, code lost:
        throw new java.lang.RuntimeException(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x00c3, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x00c4, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:?, code lost:
        r5.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x00c8, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x00c9, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x00cf, code lost:
        throw new java.lang.RuntimeException(r1);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:44:0x008f */
    /* JADX WARNING: Missing exception handler attribute for start block: B:54:0x00a5 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:65:0x00b6 */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0088 A[ExcHandler: all (r0v13 'th' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:27:0x0075] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0094  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x009a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static X.C03690oJ A01(java.lang.Class<?> r5) {
        /*
        // Method dump skipped, instructions count: 208
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C03980ov.A01(java.lang.Class):X.0oJ");
    }
}
