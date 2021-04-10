package com.facebook.mobileconfig.specifier;

import com.facebook.breakpad.BreakpadManager;
import com.facebook.common.stringformat.StringFormatUtil;
import com.oculus.panellib.Qpl;

public class MobileConfigParamTypeUtil {
    public static int valueOf(int val) {
        switch (val) {
            case 0:
                return 0;
            case 1:
                return 1;
            case Qpl.ACTION_SUCCESS /*{ENCODED_INT: 2}*/:
                return 2;
            case 3:
                return 3;
            case BreakpadManager.SIGILL:
                return 4;
            default:
                throw new IllegalArgumentException(StringFormatUtil.formatStrLocaleSafe("%d is not a MobileConfigParamType", Integer.valueOf(val)));
        }
    }
}
