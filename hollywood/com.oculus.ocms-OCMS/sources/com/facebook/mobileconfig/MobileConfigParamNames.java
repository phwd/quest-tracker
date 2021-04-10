package com.facebook.mobileconfig;

import com.facebook.mobileconfig.specifier.MobileConfigSpecifierUtil;

public class MobileConfigParamNames {
    public static String getParamName(long j) {
        int paramType = MobileConfigSpecifierUtil.getParamType(j);
        switch (Math.abs(((paramType * 31) + MobileConfigSpecifierUtil.getSlotId(j)) % 8)) {
            case 0:
                if (paramType == 1) {
                    return MobileConfigParamNames0.getBooleanParamName(j);
                }
                if (paramType == 2) {
                    return MobileConfigParamNames0.getLongParamName(j);
                }
                if (paramType == 3) {
                    return MobileConfigParamNames0.getStringParamName(j);
                }
                if (paramType != 4) {
                    return "";
                }
                return MobileConfigParamNames0.getDoubleParamName(j);
            case 1:
                if (paramType == 1) {
                    return MobileConfigParamNames1.getBooleanParamName(j);
                }
                if (paramType == 2) {
                    return MobileConfigParamNames1.getLongParamName(j);
                }
                if (paramType == 3) {
                    return MobileConfigParamNames1.getStringParamName(j);
                }
                if (paramType != 4) {
                    return "";
                }
                return MobileConfigParamNames1.getDoubleParamName(j);
            case 2:
                if (paramType == 1) {
                    return MobileConfigParamNames2.getBooleanParamName(j);
                }
                if (paramType == 2) {
                    return MobileConfigParamNames2.getLongParamName(j);
                }
                if (paramType == 3) {
                    return MobileConfigParamNames2.getStringParamName(j);
                }
                if (paramType != 4) {
                    return "";
                }
                return MobileConfigParamNames2.getDoubleParamName(j);
            case 3:
                if (paramType == 1) {
                    return MobileConfigParamNames3.getBooleanParamName(j);
                }
                if (paramType == 2) {
                    return MobileConfigParamNames3.getLongParamName(j);
                }
                if (paramType == 3) {
                    return MobileConfigParamNames3.getStringParamName(j);
                }
                if (paramType != 4) {
                    return "";
                }
                return MobileConfigParamNames3.getDoubleParamName(j);
            case 4:
                if (paramType == 1) {
                    return MobileConfigParamNames4.getBooleanParamName(j);
                }
                if (paramType == 2) {
                    return MobileConfigParamNames4.getLongParamName(j);
                }
                if (paramType == 3) {
                    return MobileConfigParamNames4.getStringParamName(j);
                }
                if (paramType != 4) {
                    return "";
                }
                return MobileConfigParamNames4.getDoubleParamName(j);
            case 5:
                if (paramType == 1) {
                    return MobileConfigParamNames5.getBooleanParamName(j);
                }
                if (paramType == 2) {
                    return MobileConfigParamNames5.getLongParamName(j);
                }
                if (paramType == 3) {
                    return MobileConfigParamNames5.getStringParamName(j);
                }
                if (paramType != 4) {
                    return "";
                }
                return MobileConfigParamNames5.getDoubleParamName(j);
            case 6:
                if (paramType == 1) {
                    return MobileConfigParamNames6.getBooleanParamName(j);
                }
                if (paramType == 2) {
                    return MobileConfigParamNames6.getLongParamName(j);
                }
                if (paramType == 3) {
                    return MobileConfigParamNames6.getStringParamName(j);
                }
                if (paramType != 4) {
                    return "";
                }
                return MobileConfigParamNames6.getDoubleParamName(j);
            case 7:
                if (paramType == 1) {
                    return MobileConfigParamNames7.getBooleanParamName(j);
                }
                if (paramType == 2) {
                    return MobileConfigParamNames7.getLongParamName(j);
                }
                if (paramType != 3) {
                    return paramType != 4 ? "" : MobileConfigParamNames7.getDoubleParamName(j);
                }
                return MobileConfigParamNames7.getStringParamName(j);
            default:
                return "";
        }
    }
}
