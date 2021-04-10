package com.facebook.quicklog.identifiers;

public class UriMap {
    public static final int APP_MANAGER_INTENT = 5701650;
    public static final int DEEP_LINK = 5701638;
    public static final int DIODE = 5701641;
    public static final int FACEBOOK_URL = 5701644;
    public static final int FACEWEB = 5701637;
    public static final int FACEWEB_FALLBACK = 5701635;
    public static final int FACEWEB_INTENT = 5701647;
    public static final int GOOGLE_PLAY = 5701636;
    public static final int MESSENGER = 5701640;
    public static final short MODULE_ID = 87;
    public static final int MULTI_BINDING = 5701653;
    public static final int REACT_NATIVE = 5701655;
    public static final int SIGN = 5701652;
    public static final int SMS_INTENT = 5701651;
    public static final int THIRD_PARTY = 5701645;
    public static final int URI_LOOKUP_DFA = 5701654;
    public static final int URI_LOOKUP_FALLBACK = 5701649;
    public static final int URI_LOOKUP_STATIC = 5701648;
    public static final int URI_MAPPING = 5701633;
    public static final int WEBVIEW_REDIRECT = 5701642;
    public static final int WEBVIEW_URI_REDIRECTOR_CONSTRUCT = 5701656;

    public static String getMarkerName(int i) {
        switch (i) {
            case 1:
                return "URI_MAP_URI_MAPPING";
            case 2:
            case 7:
            case 11:
            case 14:
            default:
                return "UNDEFINED_QPL_EVENT";
            case 3:
                return "URI_MAP_FACEWEB_FALLBACK";
            case 4:
                return "URI_MAP_GOOGLE_PLAY";
            case 5:
                return "URI_MAP_FACEWEB";
            case 6:
                return "URI_MAP_DEEP_LINK";
            case 8:
                return "URI_MAP_MESSENGER";
            case 9:
                return "URI_MAP_DIODE";
            case 10:
                return "URI_MAP_WEBVIEW_REDIRECT";
            case 12:
                return "URI_MAP_FACEBOOK_URL";
            case 13:
                return "URI_MAP_THIRD_PARTY";
            case 15:
                return "URI_MAP_FACEWEB_INTENT";
            case 16:
                return "URI_MAP_URI_LOOKUP_STATIC";
            case 17:
                return "URI_MAP_URI_LOOKUP_FALLBACK";
            case 18:
                return "URI_MAP_APP_MANAGER_INTENT";
            case 19:
                return "URI_MAP_SMS_INTENT";
            case 20:
                return "URI_MAP_SIGN";
            case 21:
                return "URI_MAP_MULTI_BINDING";
            case 22:
                return "URI_MAP_URI_LOOKUP_DFA";
            case 23:
                return "URI_MAP_REACT_NATIVE";
            case 24:
                return "URI_MAP_WEBVIEW_URI_REDIRECTOR_CONSTRUCT";
        }
    }
}
