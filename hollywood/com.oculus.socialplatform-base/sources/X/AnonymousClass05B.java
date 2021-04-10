package X;

import androidx.annotation.Nullable;

/* renamed from: X.05B  reason: invalid class name */
public final class AnonymousClass05B {
    public static boolean A00(@Nullable AnonymousClass05A[] r5, @Nullable AnonymousClass05A[] r6) {
        int length;
        if (!(r5 == null || r6 == null || (length = r5.length) != r6.length)) {
            for (int i = 0; i < length; i++) {
                if (r5[i].A00 == r6[i].A00 && r5[i].A01.length == r6[i].A01.length) {
                }
            }
            return true;
        }
        return false;
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0039  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x00bc A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static X.AnonymousClass05A[] A01(java.lang.String r15) {
        /*
        // Method dump skipped, instructions count: 262
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass05B.A01(java.lang.String):X.05A[]");
    }

    public static AnonymousClass05A[] A02(AnonymousClass05A[] r5) {
        if (r5 == null) {
            return null;
        }
        int length = r5.length;
        AnonymousClass05A[] r3 = new AnonymousClass05A[length];
        for (int i = 0; i < length; i++) {
            r3[i] = new AnonymousClass05A(r5[i]);
        }
        return r3;
    }
}
