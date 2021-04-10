package com.facebook.mobileconfig.specifier;

import com.facebook.common.stringformat.StringFormatUtil;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class MobileConfigUnitTypeUtil {
    public static int valueOf(int i) {
        if (i == 0) {
            return 0;
        }
        if (i == 1) {
            return 1;
        }
        if (i == 2) {
            return 2;
        }
        throw new IllegalArgumentException(StringFormatUtil.formatStrLocaleSafe("%d is not a MobileConfigUnitType", Integer.valueOf(i)));
    }

    public static String toString(int i) {
        if (i == 0) {
            return "SESSIONBASED";
        }
        if (i == 1) {
            return "SESSIONLESS";
        }
        if (i == 2) {
            return "PAGEID";
        }
        throw new IllegalArgumentException(StringFormatUtil.formatStrLocaleSafe("%d is not a MobileConfigUnitType", Integer.valueOf(i)));
    }
}
