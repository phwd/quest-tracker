package com.facebook.mobileconfig.specifier;

import com.facebook.common.stringformat.StringFormatUtil;

public class MobileConfigParamTypeUtil {
    public static int valueOf(int val) {
        switch (val) {
            case 0:
                return 0;
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 3;
            case 4:
                return 4;
            default:
                throw new IllegalArgumentException(StringFormatUtil.formatStrLocaleSafe("%d is not a MobileConfigParamType", Integer.valueOf(val)));
        }
    }
}
