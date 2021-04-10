package com.oculus.common.sociallogger;

/* JADX INFO: Failed to restore enum class, 'enum' modifier removed */
public final class SurfaceType extends Enum<SurfaceType> {
    public static final /* synthetic */ SurfaceType[] $VALUES;
    public static final SurfaceType ACTIVE_STATUS;
    public static final SurfaceType ALL_CONNECTIONS;
    public static final SurfaceType ALL_NEARBY;
    public static final SurfaceType APP_SELECT;
    public static final SurfaceType BACKGROUND_FETCHER;
    public static final SurfaceType DEEPLINKED_THREAD;
    public static final SurfaceType DESTINATION_SELECT;
    public static final SurfaceType FACEBOOK_BLOCK;
    public static final SurfaceType FB_MESSENGER_INITIALIZED;
    public static final SurfaceType FB_MESSENGER_INITIALIZING;
    public static final SurfaceType FB_MESSENGER_REACTIONS_DETAILS;
    public static final SurfaceType FB_MESSENGER_REACTIONS_PILL;
    public static final SurfaceType FB_MESSENGER_REACTIONS_SUMMARY;
    public static final SurfaceType FB_PEOPLE_INITIALIZED;
    public static final SurfaceType FB_PEOPLE_INITIALIZING;
    public static final SurfaceType FRIENDS;
    public static final SurfaceType JOIN_PARTY;
    public static final SurfaceType MESSAGE_COMPOSE;
    public static final SurfaceType MESSENGER_ACCOUNT;
    public static final SurfaceType MESSENGER_INTEGRITY;
    public static final SurfaceType MESSENGER_SIGN_OUT_DIALOG;
    public static final SurfaceType PARTIES_FOOTER;
    public static final SurfaceType PARTIES_HEADER;
    public static final SurfaceType PARTY_INITIAL_LOAD;
    public static final SurfaceType PARTY_MEMBER_LIST;
    public static final SurfaceType PARTY_OC_ADD_TO_PARTY;
    public static final SurfaceType PARTY_PRIVACY;
    public static final SurfaceType PEOPLE_NEARBY;
    public static final SurfaceType PRIVACY;
    public static final SurfaceType PROFILE_CONFIRM;
    public static final SurfaceType REAUTH;
    public static final SurfaceType REAUTH_INITIALIZED;
    public static final SurfaceType REAUTH_INITIALIZING;
    public static final SurfaceType REAUTH_NUX_DIALOG;
    public static final SurfaceType REQUESTS;
    public static final SurfaceType SEARCH;
    public static final SurfaceType SETTINGS_INITIALIZED;
    public static final SurfaceType SETTINGS_INITIALIZING;
    public static final SurfaceType SIDE_NAV;
    public static final SurfaceType START_MESSENGER_CALL;
    public static final SurfaceType SUGGESTIONS;
    public static final SurfaceType THREAD_LIST_ITEM;
    public static final SurfaceType THREAD_VIEW;
    public final String mSurfaceType;

    static {
        SurfaceType surfaceType = new SurfaceType("APP_SELECT", 0, "APP_SELECT");
        APP_SELECT = surfaceType;
        SurfaceType surfaceType2 = new SurfaceType("DESTINATION_SELECT", 1, "DESTINATION_SELECT");
        DESTINATION_SELECT = surfaceType2;
        SurfaceType surfaceType3 = new SurfaceType("PROFILE_CONFIRM", 2, "PROFILE_CONFIRM");
        PROFILE_CONFIRM = surfaceType3;
        SurfaceType surfaceType4 = new SurfaceType("JOIN_PARTY", 3, "JOIN_PARTY");
        JOIN_PARTY = surfaceType4;
        SurfaceType surfaceType5 = new SurfaceType("FACEBOOK_BLOCK", 4, "FACEBOOK_BLOCK");
        FACEBOOK_BLOCK = surfaceType5;
        SurfaceType surfaceType6 = new SurfaceType("MESSENGER_INTEGRITY", 5, "MESSENGER_INTEGRITY");
        MESSENGER_INTEGRITY = surfaceType6;
        SurfaceType surfaceType7 = new SurfaceType("DEEPLINKED_THREAD", 6, "DEEPLINKED_THREAD");
        DEEPLINKED_THREAD = surfaceType7;
        SurfaceType surfaceType8 = new SurfaceType("FB_MESSENGER_INITIALIZING", 7, "FB_MESSENGER_INITIALIZING");
        FB_MESSENGER_INITIALIZING = surfaceType8;
        SurfaceType surfaceType9 = new SurfaceType("FB_MESSENGER_INITIALIZED", 8, "FB_MESSENGER_INITIALIZED");
        FB_MESSENGER_INITIALIZED = surfaceType9;
        SurfaceType surfaceType10 = new SurfaceType("FB_MESSENGER_REACTIONS_SUMMARY", 9, "FB_MESSENGER_REACTIONS_SUMMARY");
        FB_MESSENGER_REACTIONS_SUMMARY = surfaceType10;
        SurfaceType surfaceType11 = new SurfaceType("FB_MESSENGER_REACTIONS_DETAILS", 10, "FB_MESSENGER_REACTIONS_DETAILS");
        FB_MESSENGER_REACTIONS_DETAILS = surfaceType11;
        SurfaceType surfaceType12 = new SurfaceType("FB_MESSENGER_REACTIONS_PILL", 11, "FB_MESSENGER_REACTIONS_PILL");
        FB_MESSENGER_REACTIONS_PILL = surfaceType12;
        SurfaceType surfaceType13 = new SurfaceType("MESSAGE_COMPOSE", 12, "MESSAGE_COMPOSE");
        MESSAGE_COMPOSE = surfaceType13;
        SurfaceType surfaceType14 = new SurfaceType("THREAD_LIST_ITEM", 13, "THREAD_LIST_ITEM");
        THREAD_LIST_ITEM = surfaceType14;
        SurfaceType surfaceType15 = new SurfaceType("THREAD_VIEW", 14, "THREAD_VIEW");
        THREAD_VIEW = surfaceType15;
        SurfaceType surfaceType16 = new SurfaceType("PARTY_INITIAL_LOAD", 15, "PARTY_INITIAL_LOAD");
        PARTY_INITIAL_LOAD = surfaceType16;
        SurfaceType surfaceType17 = new SurfaceType("PARTY_MEMBER_LIST", 16, "PARTY_MEMBER_LIST");
        PARTY_MEMBER_LIST = surfaceType17;
        SurfaceType surfaceType18 = new SurfaceType("PARTIES_HEADER", 17, "PARTIES_HEADER");
        PARTIES_HEADER = surfaceType18;
        SurfaceType surfaceType19 = new SurfaceType("PARTIES_FOOTER", 18, "PARTIES_FOOTER");
        PARTIES_FOOTER = surfaceType19;
        SurfaceType surfaceType20 = new SurfaceType("PARTY_PRIVACY", 19, "PARTY_PRIVACY");
        PARTY_PRIVACY = surfaceType20;
        SurfaceType surfaceType21 = new SurfaceType("PARTY_OC_ADD_TO_PARTY", 20, "PARTY_OC_ADD_TO_PARTY");
        PARTY_OC_ADD_TO_PARTY = surfaceType21;
        SurfaceType surfaceType22 = new SurfaceType("FB_PEOPLE_INITIALIZING", 21, "FB_PEOPLE_INITIALIZING");
        FB_PEOPLE_INITIALIZING = surfaceType22;
        SurfaceType surfaceType23 = new SurfaceType("FB_PEOPLE_INITIALIZED", 22, "FB_PEOPLE_INITIALIZED");
        FB_PEOPLE_INITIALIZED = surfaceType23;
        SurfaceType surfaceType24 = new SurfaceType("ALL_CONNECTIONS", 23, "ALL_CONNECTIONS");
        ALL_CONNECTIONS = surfaceType24;
        SurfaceType surfaceType25 = new SurfaceType("ALL_NEARBY", 24, "ALL_NEARBY");
        ALL_NEARBY = surfaceType25;
        SurfaceType surfaceType26 = new SurfaceType("FRIENDS", 25, "FRIENDS");
        FRIENDS = surfaceType26;
        SurfaceType surfaceType27 = new SurfaceType("REQUESTS", 26, "REQUESTS");
        REQUESTS = surfaceType27;
        SurfaceType surfaceType28 = new SurfaceType("SEARCH", 27, "SEARCH");
        SEARCH = surfaceType28;
        SurfaceType surfaceType29 = new SurfaceType("SUGGESTIONS", 28, "SUGGESTIONS");
        SUGGESTIONS = surfaceType29;
        SurfaceType surfaceType30 = new SurfaceType("PEOPLE_NEARBY", 29, "PEOPLE_NEARBY");
        PEOPLE_NEARBY = surfaceType30;
        SurfaceType surfaceType31 = new SurfaceType("BACKGROUND_FETCHER", 30, "BACKGROUND_FETCHER");
        BACKGROUND_FETCHER = surfaceType31;
        SurfaceType surfaceType32 = new SurfaceType("REAUTH_INITIALIZING", 31, "REAUTH_INITIALIZING");
        REAUTH_INITIALIZING = surfaceType32;
        SurfaceType surfaceType33 = new SurfaceType("REAUTH_INITIALIZED", 32, "REAUTH_INITIALIZED");
        REAUTH_INITIALIZED = surfaceType33;
        SurfaceType surfaceType34 = new SurfaceType("REAUTH_NUX_DIALOG", 33, "REAUTH_NUX_DIALOG");
        REAUTH_NUX_DIALOG = surfaceType34;
        SurfaceType surfaceType35 = new SurfaceType("REAUTH", 34, "REAUTH");
        REAUTH = surfaceType35;
        SurfaceType surfaceType36 = new SurfaceType("SETTINGS_INITIALIZING", 35, "SETTINGS_INITIALIZING");
        SETTINGS_INITIALIZING = surfaceType36;
        SurfaceType surfaceType37 = new SurfaceType("SETTINGS_INITIALIZED", 36, "SETTINGS_INITIALIZED");
        SETTINGS_INITIALIZED = surfaceType37;
        SurfaceType surfaceType38 = new SurfaceType("ACTIVE_STATUS", 37, "ACTIVE_STATUS");
        ACTIVE_STATUS = surfaceType38;
        SurfaceType surfaceType39 = new SurfaceType("PRIVACY", 38, "PRIVACY");
        PRIVACY = surfaceType39;
        SurfaceType surfaceType40 = new SurfaceType("MESSENGER_ACCOUNT", 39, "MESSENGER_ACCOUNT");
        MESSENGER_ACCOUNT = surfaceType40;
        SurfaceType surfaceType41 = new SurfaceType("MESSENGER_SIGN_OUT_DIALOG", 40, "MESSENGER_SIGN_OUT_DIALOG");
        MESSENGER_SIGN_OUT_DIALOG = surfaceType41;
        SurfaceType surfaceType42 = new SurfaceType("SIDE_NAV", 41, "SIDE_NAV");
        SIDE_NAV = surfaceType42;
        SurfaceType surfaceType43 = new SurfaceType("START_MESSENGER_CALL", 42, "START_MESSENGER_CALL");
        START_MESSENGER_CALL = surfaceType43;
        SurfaceType[] surfaceTypeArr = new SurfaceType[43];
        System.arraycopy(new SurfaceType[]{surfaceType, surfaceType2, surfaceType3, surfaceType4, surfaceType5, surfaceType6, surfaceType7, surfaceType8, surfaceType9, surfaceType10, surfaceType11, surfaceType12, surfaceType13, surfaceType14, surfaceType15, surfaceType16, surfaceType17, surfaceType18, surfaceType19, surfaceType20, surfaceType21, surfaceType22, surfaceType23, surfaceType24, surfaceType25, surfaceType26, surfaceType27}, 0, surfaceTypeArr, 0, 27);
        System.arraycopy(new SurfaceType[]{surfaceType28, surfaceType29, surfaceType30, surfaceType31, surfaceType32, surfaceType33, surfaceType34, surfaceType35, surfaceType36, surfaceType37, surfaceType38, surfaceType39, surfaceType40, surfaceType41, surfaceType42, surfaceType43}, 0, surfaceTypeArr, 27, 16);
        $VALUES = surfaceTypeArr;
    }

    public static SurfaceType valueOf(String str) {
        return (SurfaceType) Enum.valueOf(SurfaceType.class, str);
    }

    public static SurfaceType[] values() {
        return (SurfaceType[]) $VALUES.clone();
    }

    public String getTelemetrySurfaceType() {
        return this.mSurfaceType;
    }

    public SurfaceType(String str, int i, String str2) {
        this.mSurfaceType = str2;
    }
}
