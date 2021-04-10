package com.facebook.quicklog.identifiers;

public class OffFacebookActivity {
    public static final short MODULE_ID = 647;
    public static final int OFA_FBLITE_APP = 42417878;
    public static final int OFA_MOBILE_APP = 42405403;
    public static final int OFA_MSITE_APP = 42406132;

    public static String getMarkerName(int i) {
        return i != 3611 ? i != 4340 ? i != 16086 ? "UNDEFINED_QPL_EVENT" : "OFF_FACEBOOK_ACTIVITY_OFA_FBLITE_APP" : "OFF_FACEBOOK_ACTIVITY_OFA_MSITE_APP" : "OFF_FACEBOOK_ACTIVITY_OFA_MOBILE_APP";
    }
}
