package com.facebook.mobileconfig.specifier;

import com.facebook.common.stringformat.StringFormatUtil;
import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class MobileConfigParamTypeUtil {
    @Nullable
    public static int valueOf(int val) {
        if (val == 0) {
            return 0;
        }
        if (val == 1) {
            return 1;
        }
        if (val == 2) {
            return 2;
        }
        if (val == 3) {
            return 3;
        }
        if (val == 4) {
            return 4;
        }
        throw new IllegalArgumentException(StringFormatUtil.formatStrLocaleSafe("%d is not a MobileConfigParamType", Integer.valueOf(val)));
    }

    public static String toString(int val) {
        if (val == 0) {
            return "NULL";
        }
        if (val == 1) {
            return "BOOLEAN";
        }
        if (val == 2) {
            return "LONG";
        }
        if (val == 3) {
            return "STRING";
        }
        if (val == 4) {
            return "DOUBLE";
        }
        throw new IllegalArgumentException(StringFormatUtil.formatStrLocaleSafe("%d is not a MobileConfigParamType", Integer.valueOf(val)));
    }
}
