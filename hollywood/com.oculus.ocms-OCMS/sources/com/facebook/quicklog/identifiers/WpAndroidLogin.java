package com.facebook.quicklog.identifiers;

public class WpAndroidLogin {
    public static final int FORCE_PASSWORD_RESET = 55391775;
    public static final int LOGIN_WITH_ACCESS_CODE = 55377925;
    public static final int LOGIN_WITH_PASSWORD = 55377921;
    public static final int LOGIN_WITH_SSO = 55377922;
    public static final int LOGIN_WITH_SSO_E2E = 55393073;
    public static final short MODULE_ID = 845;
    public static final int REAUTH_WITH_SSO = 55377923;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 5 ? i != 13855 ? i != 15153 ? "UNDEFINED_QPL_EVENT" : "WP_ANDROID_LOGIN_LOGIN_WITH_SSO_E2E" : "WP_ANDROID_LOGIN_FORCE_PASSWORD_RESET" : "WP_ANDROID_LOGIN_LOGIN_WITH_ACCESS_CODE" : "WP_ANDROID_LOGIN_REAUTH_WITH_SSO" : "WP_ANDROID_LOGIN_LOGIN_WITH_SSO" : "WORK_LOGIN_WITH_PASSWORD";
    }
}
