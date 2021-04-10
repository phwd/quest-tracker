package com.facebook.secure.trustedapp;

public class CustomPermissionDomain {
    private static final String DEFAULT_DOMAIN_SEPARATOR = ":";
    public static final String FACEBOOK_AUTO_COMPOSE_STATUS = "FACEBOOK_AUTO_COMPOSE_STATUS";
    public static final String FACEBOOK_INTERNAL_RELOGIN_FLOW = "FACEBOOK_INTERNAL_RELOGIN_FLOW";
    public static final String MESSENGER_AUDIO_CALL = "MESSENGER_AUDIO_CALL";
    public static final String MESSENGER_AUTOCOMPOSE = "MESSENGER_AUTOCOMPOSE";
    public static final String MESSENGER_JOIN_REQUEST = "MESSENGER_JOIN_REQUEST";
    public static final String MESSENGER_MEETUP_REQUEST = "MESSENGER_MEETUP_REQUEST";
    public static final String MESSENGER_VIDEO_CALL = "MESSENGER_VIDEO_CALL";

    public static String buildFromCallSite(String str, String str2) {
        return str + DEFAULT_DOMAIN_SEPARATOR + str2;
    }

    public static boolean equalsToDefaultDomainAtCallSite(String str, String str2, String str3) {
        return str.equals(str2 + DEFAULT_DOMAIN_SEPARATOR + str3);
    }
}
