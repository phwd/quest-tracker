package X;

import com.facebook.acra.constants.ErrorReportingConstants;
import com.squareup.okhttp.internal.DiskLruCache;

/* renamed from: X.of  reason: case insensitive filesystem */
public final class C0461of {
    public static final String[] A00 = {"0", DiskLruCache.VERSION_1, "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    public static final String[] A01 = {ErrorReportingConstants.ANR_DEFAULT_RECOVERY_DELAY_VAL, "-2", "-3", "-4", "-5", "-6", "-7", "-8", "-9", "-10"};

    static {
        for (int i = 0; i < 10; i++) {
            int i2 = 0;
            do {
                int i3 = 0;
                do {
                    i3++;
                } while (i3 < 10);
                i2++;
            } while (i2 < 10);
        }
        int i4 = 0;
        do {
            i4++;
        } while (i4 < 4000);
    }
}
