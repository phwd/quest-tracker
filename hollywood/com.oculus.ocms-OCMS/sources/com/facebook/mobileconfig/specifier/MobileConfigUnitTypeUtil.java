package com.facebook.mobileconfig.specifier;

import com.facebook.common.stringformat.StringFormatUtil;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class MobileConfigUnitTypeUtil {
    public static int valueOf(int i) {
        if (i == 0) {
            return 0;
        }
        int i2 = 1;
        if (i != 1) {
            i2 = 2;
            if (i != 2) {
                if (i == 3) {
                    return 3;
                }
                throw new IllegalArgumentException(StringFormatUtil.formatStrLocaleSafe("%d is not a MobileConfigUnitType", Integer.valueOf(i)));
            }
        }
        return i2;
    }

    public static String toString(int i) {
        if (i == 0) {
            return "NULL_VALUE";
        }
        if (i == 1) {
            return "SESSIONLESS";
        }
        if (i == 2) {
            return "SESSIONBASED";
        }
        if (i == 3) {
            return "PAGEID";
        }
        throw new IllegalArgumentException(StringFormatUtil.formatStrLocaleSafe("%d is not a MobileConfigUnitType", Integer.valueOf(i)));
    }
}
