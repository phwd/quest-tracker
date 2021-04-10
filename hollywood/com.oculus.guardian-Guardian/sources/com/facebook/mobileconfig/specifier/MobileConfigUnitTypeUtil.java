package com.facebook.mobileconfig.specifier;

import com.facebook.common.stringformat.StringFormatUtil;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class MobileConfigUnitTypeUtil {
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
        throw new IllegalArgumentException(StringFormatUtil.formatStrLocaleSafe("%d is not a MobileConfigUnitType", Integer.valueOf(val)));
    }

    public static String toString(int val) {
        if (val == 0) {
            return "SESSIONBASED";
        }
        if (val == 1) {
            return "SESSIONLESS";
        }
        if (val == 2) {
            return "PAGEID";
        }
        throw new IllegalArgumentException(StringFormatUtil.formatStrLocaleSafe("%d is not a MobileConfigUnitType", Integer.valueOf(val)));
    }
}
