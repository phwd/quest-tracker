package com.facebook.quicklog.identifiers;

public class WpAndroidSignup {
    public static final int ENTER_EMAIL_FOR_PROVISIONING = 56295428;
    public static final int LOAD_PHONE_CONTACTS_FOR_PROVISIONING = 56295430;
    public static final short MODULE_ID = 859;
    public static final int NAVIGATE_TO_ADD_PEOPLE_SCREEN = 56295427;
    public static final int PROVISION_ALL_ELIGIBLE_EMAIL_CONTACTS = 56295429;
    public static final int PROVISION_USER = 56295426;
    public static final int SIGNUP = 56295439;
    public static final int UNEXPECTED_NAVIGATION = 56295431;

    public static String getMarkerName(int i) {
        if (i == 15) {
            return "WP_ANDROID_SIGNUP_SIGNUP";
        }
        switch (i) {
            case 2:
                return "WP_ANDROID_SIGNUP_PROVISION_USER";
            case 3:
                return "WP_ANDROID_SIGNUP_NAVIGATE_TO_ADD_PEOPLE_SCREEN";
            case 4:
                return "WP_ANDROID_SIGNUP_ENTER_EMAIL_FOR_PROVISIONING";
            case 5:
                return "WP_ANDROID_SIGNUP_PROVISION_ALL_ELIGIBLE_EMAIL_CONTACTS";
            case 6:
                return "WP_ANDROID_SIGNUP_LOAD_PHONE_CONTACTS_FOR_PROVISIONING";
            case 7:
                return "WP_ANDROID_SIGNUP_UNEXPECTED_NAVIGATION";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
