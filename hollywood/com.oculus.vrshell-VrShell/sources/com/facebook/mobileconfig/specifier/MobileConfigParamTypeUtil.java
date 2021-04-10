package com.facebook.mobileconfig.specifier;

import com.facebook.common.stringformat.StringFormatUtil;
import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class MobileConfigParamTypeUtil {
    @Nullable
    public static int valueOf(int i) {
        if (i == 0) {
            return 0;
        }
        int i2 = 1;
        if (i != 1) {
            i2 = 2;
            if (i != 2) {
                i2 = 3;
                if (i != 3) {
                    if (i == 4) {
                        return 4;
                    }
                    throw new IllegalArgumentException(StringFormatUtil.formatStrLocaleSafe("%d is not a MobileConfigParamType", Integer.valueOf(i)));
                }
            }
        }
        return i2;
    }

    public static String toString(int i) {
        if (i == 0) {
            return "NULL";
        }
        if (i == 1) {
            return "BOOLEAN";
        }
        if (i == 2) {
            return "LONG";
        }
        if (i == 3) {
            return "STRING";
        }
        if (i == 4) {
            return "DOUBLE";
        }
        throw new IllegalArgumentException(StringFormatUtil.formatStrLocaleSafe("%d is not a MobileConfigParamType", Integer.valueOf(i)));
    }
}
