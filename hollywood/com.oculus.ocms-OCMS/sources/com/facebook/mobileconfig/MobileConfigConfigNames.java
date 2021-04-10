package com.facebook.mobileconfig;

public final class MobileConfigConfigNames {
    private static String[] namesList = {"android_vrosservices_delay_doze", "mobileconfig_verify_tt", "oculus_library", "oculus_mobile_core", "oculus_security_content_provider_logging", "oculus_security_intent_logging", "oculus_shared_core"};

    public static String getConfigName(int i) {
        if (i < 0) {
            return "";
        }
        String[] strArr = namesList;
        return i >= strArr.length ? "" : strArr[i];
    }
}
