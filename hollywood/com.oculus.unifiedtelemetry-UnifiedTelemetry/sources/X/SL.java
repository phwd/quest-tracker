package X;

import com.facebook.common.stringformat.StringFormatUtil;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class SL {
    public static boolean A01(long j) {
        return ((j >> 61) & 1) == 1;
    }

    public static int A00(long j) {
        int i = (int) ((j >>> 48) & 63);
        if (i == 0) {
            return 0;
        }
        if (i == 1) {
            return 1;
        }
        if (i == 2) {
            return 2;
        }
        if (i == 3) {
            return 3;
        }
        if (i == 4) {
            return 4;
        }
        throw new IllegalArgumentException(StringFormatUtil.formatStrLocaleSafe("%d is not a MobileConfigParamType", Integer.valueOf(i)));
    }
}
