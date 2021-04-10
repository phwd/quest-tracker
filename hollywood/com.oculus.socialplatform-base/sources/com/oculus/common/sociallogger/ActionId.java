package com.oculus.common.sociallogger;

/* JADX INFO: Failed to restore enum class, 'enum' modifier removed */
public final class ActionId extends Enum<ActionId> {
    public static final /* synthetic */ ActionId[] $VALUES;
    public static final ActionId ACCEPT_FRIEND_REQUEST;
    public static final ActionId ADD_FRIEND;
    public static final ActionId BLOCK_USER;
    public static final ActionId CANCEL_FRIEND_REQUEST;
    public static final ActionId CHECK_WHETHER_MESSENGER_IS_AUTHENTICATED;
    public static final ActionId DENY_FRIEND_REQUEST;
    public static final ActionId FACEBOOK_BLOCK_DIALOG_BLOCK;
    public static final ActionId FACEBOOK_BLOCK_DIALOG_UNBLOCK;
    public static final ActionId FETCH_FB_FRIENDS;
    public static final ActionId FETCH_OCULUS_FRIENDS;
    public static final ActionId FETCH_OCULUS_FRIEND_REQUESTS;
    public static final ActionId FETCH_OCULUS_LINKED_ACCOUNTS;
    public static final ActionId FETCH_OCULUS_PYMK;
    public static final ActionId FETCH_OCULUS_SEARCH_RESULTS;
    public static final ActionId FETCH_OCULUS_VIEWER_PARTY_DATA;
    public static final ActionId HIDE_PYMK_USER;
    public static final ActionId MESSAGE_SEND;
    public static final ActionId MESSAGE_THREAD_INITIAL_LOAD;
    public static final ActionId MESSENGER_INTEGRITY_DIALOG_FACEBOOK_BLOCK;
    public static final ActionId MESSENGER_INTEGRITY_DIALOG_FACEBOOK_UNBLOCK;
    public static final ActionId MESSENGER_INTEGRITY_DIALOG_MESSENGER_BLOCK;
    public static final ActionId MESSENGER_INTEGRITY_DIALOG_MESSENGER_UNBLOCK;
    public static final ActionId NAVIGATE_TO_DIALOG;
    public static final ActionId NAVIGATE_TO_SHARE_SHEET;
    public static final ActionId PARTY_CANCEL_INVITE;
    public static final ActionId PARTY_CREATE;
    public static final ActionId PARTY_CREATE_TOGGLE_DISMISS_REMINDER;
    public static final ActionId PARTY_FETCH_GROUP_LAUNCH_APPS;
    public static final ActionId PARTY_FETCH_GROUP_LAUNCH_DESTINATIONS;
    public static final ActionId PARTY_FOOTER_GO_TO_APP_LAUNCH;
    public static final ActionId PARTY_FOOTER_LAUNCH_GROUP_LAUNCH;
    public static final ActionId PARTY_FOOTER_LAUNCH_SOLO;
    public static final ActionId PARTY_FOOTER_PREPARE_GROUP_LAUNCH;
    public static final ActionId PARTY_FOOTER_SET_USER_RESPONSE;
    public static final ActionId PARTY_JOIN;
    public static final ActionId PARTY_KICK_USER;
    public static final ActionId PARTY_LEAVE;
    public static final ActionId PARTY_SEND_INVITE;
    public static final ActionId PARTY_SEND_LINK_TO_THREAD;
    public static final ActionId PARTY_SET_JOIN_TYPE_FRIENDS_OF_PARTICIPANTS;
    public static final ActionId PARTY_SET_JOIN_TYPE_INVITE_ONLY;
    public static final ActionId PARTY_SET_JOIN_TYPE_UNKNOWN;
    public static final ActionId PARTY_TOGGLE_LINK_INVITE_OFF;
    public static final ActionId PARTY_TOGGLE_LINK_INVITE_ON;
    public static final ActionId PARTY_UPDATE_DESTINATION;
    public static final ActionId REAUTH_SKIP;
    public static final ActionId REAUTH_VALIDATE_PASSWORD;
    public static final ActionId REAUTH_VALIDATE_PASSWORD_ERROR_UNKNOWN;
    public static final ActionId REAUTH_VALIDATE_PASSWORD_INCORRECT_PASSWORD;
    public static final ActionId REAUTH_VALIDATE_PASSWORD_TOO_MANY_ATTEMPTS;
    public static final ActionId SET_ACTIVE_STATUS_ACTIVE;
    public static final ActionId SET_ACTIVE_STATUS_INACTIVE;
    public static final ActionId SET_ACTIVE_STATUS_TOGGLE;
    public static final ActionId SOCIAL_SETTINGS_MESSENGER_SIGN_OUT;
    public static final ActionId UNFRIEND;
    public final String mActionId;

    static {
        ActionId actionId = new ActionId("PARTY_CREATE", 0, "PARTY_CREATE");
        PARTY_CREATE = actionId;
        ActionId actionId2 = new ActionId("PARTY_CREATE_TOGGLE_DISMISS_REMINDER", 1, "PARTY_CREATE_TOGGLE_DISMISS_REMINDER");
        PARTY_CREATE_TOGGLE_DISMISS_REMINDER = actionId2;
        ActionId actionId3 = new ActionId("PARTY_JOIN", 2, "PARTY_JOIN");
        PARTY_JOIN = actionId3;
        ActionId actionId4 = new ActionId("PARTY_FETCH_GROUP_LAUNCH_APPS", 3, "PARTY_FETCH_GROUP_LAUNCH_APPS");
        PARTY_FETCH_GROUP_LAUNCH_APPS = actionId4;
        ActionId actionId5 = new ActionId("PARTY_FETCH_GROUP_LAUNCH_DESTINATIONS", 4, "PARTY_FETCH_GROUP_LAUNCH_DESTINATIONS");
        PARTY_FETCH_GROUP_LAUNCH_DESTINATIONS = actionId5;
        ActionId actionId6 = new ActionId("PARTY_SEND_LINK_TO_THREAD", 5, "PARTY_SEND_LINK_TO_THREAD");
        PARTY_SEND_LINK_TO_THREAD = actionId6;
        ActionId actionId7 = new ActionId("PARTY_SET_JOIN_TYPE_UNKNOWN", 6, "PARTY_SET_JOIN_TYPE_UNKNOWN");
        PARTY_SET_JOIN_TYPE_UNKNOWN = actionId7;
        ActionId actionId8 = new ActionId("PARTY_SET_JOIN_TYPE_INVITE_ONLY", 7, "PARTY_SET_JOIN_TYPE_INVITE_ONLY");
        PARTY_SET_JOIN_TYPE_INVITE_ONLY = actionId8;
        ActionId actionId9 = new ActionId("PARTY_SET_JOIN_TYPE_FRIENDS_OF_PARTICIPANTS", 8, "PARTY_SET_JOIN_TYPE_FRIENDS_OF_PARTICIPANTS");
        PARTY_SET_JOIN_TYPE_FRIENDS_OF_PARTICIPANTS = actionId9;
        ActionId actionId10 = new ActionId("PARTY_TOGGLE_LINK_INVITE_OFF", 9, "PARTY_TOGGLE_LINK_INVITE_OFF");
        PARTY_TOGGLE_LINK_INVITE_OFF = actionId10;
        ActionId actionId11 = new ActionId("PARTY_TOGGLE_LINK_INVITE_ON", 10, "PARTY_TOGGLE_LINK_INVITE_ON");
        PARTY_TOGGLE_LINK_INVITE_ON = actionId11;
        ActionId actionId12 = new ActionId("PARTY_UPDATE_DESTINATION", 11, "PARTY_UPDATE_DESTINATION");
        PARTY_UPDATE_DESTINATION = actionId12;
        ActionId actionId13 = new ActionId("PARTY_LEAVE", 12, "PARTY_LEAVE");
        PARTY_LEAVE = actionId13;
        ActionId actionId14 = new ActionId("PARTY_SEND_INVITE", 13, "PARTY_SEND_INVITE");
        PARTY_SEND_INVITE = actionId14;
        ActionId actionId15 = new ActionId("PARTY_CANCEL_INVITE", 14, "PARTY_CANCEL_INVITE");
        PARTY_CANCEL_INVITE = actionId15;
        ActionId actionId16 = new ActionId("PARTY_KICK_USER", 15, "PARTY_KICK_USER");
        PARTY_KICK_USER = actionId16;
        ActionId actionId17 = new ActionId("NAVIGATE_TO_SHARE_SHEET", 16, "NAVIGATE_TO_SHARE_SHEET");
        NAVIGATE_TO_SHARE_SHEET = actionId17;
        ActionId actionId18 = new ActionId("BLOCK_USER", 17, "BLOCK_USER");
        BLOCK_USER = actionId18;
        ActionId actionId19 = new ActionId("NAVIGATE_TO_DIALOG", 18, "NAVIGATE_TO_DIALOG");
        NAVIGATE_TO_DIALOG = actionId19;
        ActionId actionId20 = new ActionId("PARTY_FOOTER_GO_TO_APP_LAUNCH", 19, "GO_TO_APP_LAUNCH");
        PARTY_FOOTER_GO_TO_APP_LAUNCH = actionId20;
        ActionId actionId21 = new ActionId("PARTY_FOOTER_LAUNCH_SOLO", 20, "LAUNCH_SOLO");
        PARTY_FOOTER_LAUNCH_SOLO = actionId21;
        ActionId actionId22 = new ActionId("PARTY_FOOTER_SET_USER_RESPONSE", 21, "SET_USER_RESPONSE");
        PARTY_FOOTER_SET_USER_RESPONSE = actionId22;
        ActionId actionId23 = new ActionId("PARTY_FOOTER_PREPARE_GROUP_LAUNCH", 22, "PREPARE_GROUP_LAUNCH");
        PARTY_FOOTER_PREPARE_GROUP_LAUNCH = actionId23;
        ActionId actionId24 = new ActionId("PARTY_FOOTER_LAUNCH_GROUP_LAUNCH", 23, "LAUNCH_GROUP_LAUNCH");
        PARTY_FOOTER_LAUNCH_GROUP_LAUNCH = actionId24;
        ActionId actionId25 = new ActionId("MESSAGE_SEND", 24, "MESSAGE_SEND");
        MESSAGE_SEND = actionId25;
        ActionId actionId26 = new ActionId("MESSAGE_THREAD_INITIAL_LOAD", 25, "MESSAGE_THREAD_INITIAL_LOAD");
        MESSAGE_THREAD_INITIAL_LOAD = actionId26;
        ActionId actionId27 = new ActionId("FACEBOOK_BLOCK_DIALOG_BLOCK", 26, "FACEBOOK_BLOCK_DIALOG_BLOCK");
        FACEBOOK_BLOCK_DIALOG_BLOCK = actionId27;
        ActionId actionId28 = new ActionId("FACEBOOK_BLOCK_DIALOG_UNBLOCK", 27, "FACEBOOK_BLOCK_DIALOG_UNBLOCK");
        FACEBOOK_BLOCK_DIALOG_UNBLOCK = actionId28;
        ActionId actionId29 = new ActionId("MESSENGER_INTEGRITY_DIALOG_MESSENGER_BLOCK", 28, "MESSENGER_INTEGRITY_DIALOG_MESSENGER_BLOCK");
        MESSENGER_INTEGRITY_DIALOG_MESSENGER_BLOCK = actionId29;
        ActionId actionId30 = new ActionId("MESSENGER_INTEGRITY_DIALOG_MESSENGER_UNBLOCK", 29, "MESSENGER_INTEGRITY_DIALOG_MESSENGER_UNBLOCK");
        MESSENGER_INTEGRITY_DIALOG_MESSENGER_UNBLOCK = actionId30;
        ActionId actionId31 = new ActionId("MESSENGER_INTEGRITY_DIALOG_FACEBOOK_BLOCK", 30, "MESSENGER_INTEGRITY_DIALOG_FACEBOOK_BLOCK");
        MESSENGER_INTEGRITY_DIALOG_FACEBOOK_BLOCK = actionId31;
        ActionId actionId32 = new ActionId("MESSENGER_INTEGRITY_DIALOG_FACEBOOK_UNBLOCK", 31, "MESSENGER_INTEGRITY_DIALOG_FACEBOOK_UNBLOCK");
        MESSENGER_INTEGRITY_DIALOG_FACEBOOK_UNBLOCK = actionId32;
        ActionId actionId33 = new ActionId("ACCEPT_FRIEND_REQUEST", 32, "ACCEPT_FRIEND_REQUEST");
        ACCEPT_FRIEND_REQUEST = actionId33;
        ActionId actionId34 = new ActionId("ADD_FRIEND", 33, "ADD_FRIEND");
        ADD_FRIEND = actionId34;
        ActionId actionId35 = new ActionId("CANCEL_FRIEND_REQUEST", 34, "CANCEL_FRIEND_REQUEST");
        CANCEL_FRIEND_REQUEST = actionId35;
        ActionId actionId36 = new ActionId("DENY_FRIEND_REQUEST", 35, "DENY_FRIEND_REQUEST");
        DENY_FRIEND_REQUEST = actionId36;
        ActionId actionId37 = new ActionId("HIDE_PYMK_USER", 36, "HIDE_PYMK_USER");
        HIDE_PYMK_USER = actionId37;
        ActionId actionId38 = new ActionId("UNFRIEND", 37, "UNFRIEND");
        UNFRIEND = actionId38;
        ActionId actionId39 = new ActionId("FETCH_FB_FRIENDS", 38, "FETCH_FB_FRIENDS");
        FETCH_FB_FRIENDS = actionId39;
        ActionId actionId40 = new ActionId("FETCH_OCULUS_VIEWER_PARTY_DATA", 39, "FETCH_OCULUS_VIEWER_PARTY_DATA");
        FETCH_OCULUS_VIEWER_PARTY_DATA = actionId40;
        ActionId actionId41 = new ActionId("FETCH_OCULUS_FRIEND_REQUESTS", 40, "FETCH_OCULUS_FRIEND_REQUESTS");
        FETCH_OCULUS_FRIEND_REQUESTS = actionId41;
        ActionId actionId42 = new ActionId("FETCH_OCULUS_FRIENDS", 41, "FETCH_OCULUS_FRIENDS");
        FETCH_OCULUS_FRIENDS = actionId42;
        ActionId actionId43 = new ActionId("FETCH_OCULUS_LINKED_ACCOUNTS", 42, "FETCH_OCULUS_LINKED_ACCOUNTS");
        FETCH_OCULUS_LINKED_ACCOUNTS = actionId43;
        ActionId actionId44 = new ActionId("FETCH_OCULUS_PYMK", 43, "FETCH_OCULUS_PYMK");
        FETCH_OCULUS_PYMK = actionId44;
        ActionId actionId45 = new ActionId("FETCH_OCULUS_SEARCH_RESULTS", 44, "FETCH_OCULUS_SEARCH_RESULTS");
        FETCH_OCULUS_SEARCH_RESULTS = actionId45;
        ActionId actionId46 = new ActionId("REAUTH_SKIP", 45, "REAUTH_SKIP");
        REAUTH_SKIP = actionId46;
        ActionId actionId47 = new ActionId("REAUTH_VALIDATE_PASSWORD", 46, "REAUTH_VALIDATE_PASSWORD");
        REAUTH_VALIDATE_PASSWORD = actionId47;
        ActionId actionId48 = new ActionId("REAUTH_VALIDATE_PASSWORD_INCORRECT_PASSWORD", 47, "REAUTH_VALIDATE_PASSWORD_INCORRECT_PASSWORD");
        REAUTH_VALIDATE_PASSWORD_INCORRECT_PASSWORD = actionId48;
        ActionId actionId49 = new ActionId("REAUTH_VALIDATE_PASSWORD_TOO_MANY_ATTEMPTS", 48, "REAUTH_VALIDATE_PASSWORD_TOO_MANY_ATTEMPTS");
        REAUTH_VALIDATE_PASSWORD_TOO_MANY_ATTEMPTS = actionId49;
        ActionId actionId50 = new ActionId("REAUTH_VALIDATE_PASSWORD_ERROR_UNKNOWN", 49, "REAUTH_VALIDATE_PASSWORD_ERROR_UNKNOWN");
        REAUTH_VALIDATE_PASSWORD_ERROR_UNKNOWN = actionId50;
        ActionId actionId51 = new ActionId("SET_ACTIVE_STATUS_ACTIVE", 50, "SET_ACTIVE_STATUS_ACTIVE");
        SET_ACTIVE_STATUS_ACTIVE = actionId51;
        ActionId actionId52 = new ActionId("SET_ACTIVE_STATUS_INACTIVE", 51, "SET_ACTIVE_STATUS_INACTIVE");
        SET_ACTIVE_STATUS_INACTIVE = actionId52;
        ActionId actionId53 = new ActionId("SET_ACTIVE_STATUS_TOGGLE", 52, "SET_ACTIVE_STATUS_TOGGLE");
        SET_ACTIVE_STATUS_TOGGLE = actionId53;
        ActionId actionId54 = new ActionId("SOCIAL_SETTINGS_MESSENGER_SIGN_OUT", 53, "SOCIAL_SETTINGS_MESSENGER_SIGN_OUT");
        SOCIAL_SETTINGS_MESSENGER_SIGN_OUT = actionId54;
        ActionId actionId55 = new ActionId("CHECK_WHETHER_MESSENGER_IS_AUTHENTICATED", 54, "CHECK_WHETHER_MESSENGER_IS_AUTHENTICATED");
        CHECK_WHETHER_MESSENGER_IS_AUTHENTICATED = actionId55;
        ActionId[] actionIdArr = new ActionId[55];
        System.arraycopy(new ActionId[]{actionId, actionId2, actionId3, actionId4, actionId5, actionId6, actionId7, actionId8, actionId9, actionId10, actionId11, actionId12, actionId13, actionId14, actionId15, actionId16, actionId17, actionId18, actionId19, actionId20, actionId21, actionId22, actionId23, actionId24, actionId25, actionId26, actionId27}, 0, actionIdArr, 0, 27);
        System.arraycopy(new ActionId[]{actionId28, actionId29, actionId30, actionId31, actionId32, actionId33, actionId34, actionId35, actionId36, actionId37, actionId38, actionId39, actionId40, actionId41, actionId42, actionId43, actionId44, actionId45, actionId46, actionId47, actionId48, actionId49, actionId50, actionId51, actionId52, actionId53, actionId54}, 0, actionIdArr, 27, 27);
        System.arraycopy(new ActionId[]{actionId55}, 0, actionIdArr, 54, 1);
        $VALUES = actionIdArr;
    }

    public static ActionId valueOf(String str) {
        return (ActionId) Enum.valueOf(ActionId.class, str);
    }

    public static ActionId[] values() {
        return (ActionId[]) $VALUES.clone();
    }

    public String getTelemetryActionId() {
        return this.mActionId;
    }

    public ActionId(String str, int i, String str2) {
        this.mActionId = str2;
    }
}
