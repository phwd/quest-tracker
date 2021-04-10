package com.oculus.panelapp.social.logging;

/* JADX INFO: Failed to restore enum class, 'enum' modifier removed */
public final class SocialPartyEvent extends Enum<SocialPartyEvent> {
    public static final /* synthetic */ SocialPartyEvent[] $VALUES;
    public static final SocialPartyEvent FB_LINK_UPSELL_DIALOG_CLOSE;
    public static final SocialPartyEvent FB_LINK_UPSELL_DIALOG_OPEN;
    public static final SocialPartyEvent FB_LINK_UPSELL_LINK_CLICK;
    public static final SocialPartyEvent INVITE_DIALOG_CLOSE;
    public static final SocialPartyEvent INVITE_DIALOG_FETCH_FRIENDS;
    public static final SocialPartyEvent INVITE_DIALOG_FETCH_FRIENDS_FAIL;
    public static final SocialPartyEvent INVITE_DIALOG_FETCH_FRIENDS_SUCCESS;
    public static final SocialPartyEvent INVITE_DIALOG_INVITE;
    public static final SocialPartyEvent INVITE_DIALOG_INVITE_FAIL;
    public static final SocialPartyEvent INVITE_DIALOG_INVITE_PARTY_CREATE;
    public static final SocialPartyEvent INVITE_DIALOG_INVITE_PARTY_CREATE_FAIL;
    public static final SocialPartyEvent INVITE_DIALOG_INVITE_PARTY_CREATE_SUCCESS;
    public static final SocialPartyEvent INVITE_DIALOG_INVITE_SUCCESS;
    public static final SocialPartyEvent INVITE_DIALOG_QUEUED_INVITES_ON_CREATE;
    public static final SocialPartyEvent JOIN_DIALOG_APP_LAUNCH;
    public static final SocialPartyEvent JOIN_DIALOG_DISMISS;
    public static final SocialPartyEvent JOIN_DIALOG_JOIN;
    public static final SocialPartyEvent JOIN_DIALOG_JOIN_FAIL;
    public static final SocialPartyEvent JOIN_DIALOG_JOIN_SUCCESS;
    public static final SocialPartyEvent PARTY_DIALOG_CHANGE_VOLUME;
    public static final SocialPartyEvent PARTY_DIALOG_DISMISS;
    public static final SocialPartyEvent PARTY_DIALOG_JOIN_BUTTON_APP_LAUNCH;
    public static final SocialPartyEvent PARTY_DIALOG_LEAVE;
    public static final SocialPartyEvent PARTY_DIALOG_LEAVE_FAIL;
    public static final SocialPartyEvent PARTY_DIALOG_LEAVE_SUCCESS;
    public static final SocialPartyEvent PARTY_DIALOG_UPDATE_MIC_STATE;
    public static final SocialPartyEvent PARTY_DIALOG_UPDATE_TYPE;
    public static final SocialPartyEvent PARTY_DIALOG_UPDATE_TYPE_FAIL;
    public static final SocialPartyEvent PARTY_DIALOG_UPDATE_TYPE_SUCCESS;
    public static final SocialPartyEvent PARTY_FOOTER_GO_TO_APP_LAUNCH;
    public static final SocialPartyEvent PARTY_FOOTER_JOIN_LEADER;
    public static final SocialPartyEvent ROOT_EVENT;
    public static final SocialPartyEvent SOCIAL_ICON_CLICK;
    public final String mPartyEvent;

    static {
        SocialPartyEvent socialPartyEvent = new SocialPartyEvent("ROOT_EVENT", 0, "oculus_aui_party");
        ROOT_EVENT = socialPartyEvent;
        SocialPartyEvent socialPartyEvent2 = new SocialPartyEvent("SOCIAL_ICON_CLICK", 1, "social_icon_click");
        SOCIAL_ICON_CLICK = socialPartyEvent2;
        SocialPartyEvent socialPartyEvent3 = new SocialPartyEvent("INVITE_DIALOG_CLOSE", 2, "invite_dialog_close");
        INVITE_DIALOG_CLOSE = socialPartyEvent3;
        SocialPartyEvent socialPartyEvent4 = new SocialPartyEvent("FB_LINK_UPSELL_DIALOG_CLOSE", 3, "oculus_fb_link_upsell_cancel");
        FB_LINK_UPSELL_DIALOG_CLOSE = socialPartyEvent4;
        SocialPartyEvent socialPartyEvent5 = new SocialPartyEvent("FB_LINK_UPSELL_DIALOG_OPEN", 4, "oculus_fb_link_upsell_impression");
        FB_LINK_UPSELL_DIALOG_OPEN = socialPartyEvent5;
        SocialPartyEvent socialPartyEvent6 = new SocialPartyEvent("FB_LINK_UPSELL_LINK_CLICK", 5, "oculus_fb_link_upsell_confirm");
        FB_LINK_UPSELL_LINK_CLICK = socialPartyEvent6;
        SocialPartyEvent socialPartyEvent7 = new SocialPartyEvent("INVITE_DIALOG_FETCH_FRIENDS", 6, "invite_dialog_fetch_friends");
        INVITE_DIALOG_FETCH_FRIENDS = socialPartyEvent7;
        SocialPartyEvent socialPartyEvent8 = new SocialPartyEvent("INVITE_DIALOG_FETCH_FRIENDS_SUCCESS", 7, "invite_dialog_fetch_friends_success");
        INVITE_DIALOG_FETCH_FRIENDS_SUCCESS = socialPartyEvent8;
        SocialPartyEvent socialPartyEvent9 = new SocialPartyEvent("INVITE_DIALOG_FETCH_FRIENDS_FAIL", 8, "invite_dialog_fetch_friends_fail");
        INVITE_DIALOG_FETCH_FRIENDS_FAIL = socialPartyEvent9;
        SocialPartyEvent socialPartyEvent10 = new SocialPartyEvent("INVITE_DIALOG_INVITE", 9, "invite_dialog_invite");
        INVITE_DIALOG_INVITE = socialPartyEvent10;
        SocialPartyEvent socialPartyEvent11 = new SocialPartyEvent("INVITE_DIALOG_INVITE_SUCCESS", 10, "invite_dialog_invite_success");
        INVITE_DIALOG_INVITE_SUCCESS = socialPartyEvent11;
        SocialPartyEvent socialPartyEvent12 = new SocialPartyEvent("INVITE_DIALOG_INVITE_FAIL", 11, "invite_dialog_invite_fail");
        INVITE_DIALOG_INVITE_FAIL = socialPartyEvent12;
        SocialPartyEvent socialPartyEvent13 = new SocialPartyEvent("INVITE_DIALOG_INVITE_PARTY_CREATE", 12, "invite_dialog_invite_party_create");
        INVITE_DIALOG_INVITE_PARTY_CREATE = socialPartyEvent13;
        SocialPartyEvent socialPartyEvent14 = new SocialPartyEvent("INVITE_DIALOG_INVITE_PARTY_CREATE_SUCCESS", 13, "invite_dialog_invite_party_create_success");
        INVITE_DIALOG_INVITE_PARTY_CREATE_SUCCESS = socialPartyEvent14;
        SocialPartyEvent socialPartyEvent15 = new SocialPartyEvent("INVITE_DIALOG_INVITE_PARTY_CREATE_FAIL", 14, "invite_dialog_invite_party_create_fail");
        INVITE_DIALOG_INVITE_PARTY_CREATE_FAIL = socialPartyEvent15;
        SocialPartyEvent socialPartyEvent16 = new SocialPartyEvent("INVITE_DIALOG_QUEUED_INVITES_ON_CREATE", 15, "invite_dialog_queued_invites_on_create");
        INVITE_DIALOG_QUEUED_INVITES_ON_CREATE = socialPartyEvent16;
        SocialPartyEvent socialPartyEvent17 = new SocialPartyEvent("PARTY_DIALOG_DISMISS", 16, "party_dialog_dismiss");
        PARTY_DIALOG_DISMISS = socialPartyEvent17;
        SocialPartyEvent socialPartyEvent18 = new SocialPartyEvent("PARTY_DIALOG_JOIN_BUTTON_APP_LAUNCH", 17, "party_dialog_join_button_app_launch");
        PARTY_DIALOG_JOIN_BUTTON_APP_LAUNCH = socialPartyEvent18;
        SocialPartyEvent socialPartyEvent19 = new SocialPartyEvent("PARTY_DIALOG_LEAVE", 18, "party_dialog_leave");
        PARTY_DIALOG_LEAVE = socialPartyEvent19;
        SocialPartyEvent socialPartyEvent20 = new SocialPartyEvent("PARTY_DIALOG_LEAVE_SUCCESS", 19, "party_dialog_leave_success");
        PARTY_DIALOG_LEAVE_SUCCESS = socialPartyEvent20;
        SocialPartyEvent socialPartyEvent21 = new SocialPartyEvent("PARTY_DIALOG_LEAVE_FAIL", 20, "party_dialog_leave_fail");
        PARTY_DIALOG_LEAVE_FAIL = socialPartyEvent21;
        SocialPartyEvent socialPartyEvent22 = new SocialPartyEvent("PARTY_DIALOG_UPDATE_TYPE", 21, "party_dialog_update_type");
        PARTY_DIALOG_UPDATE_TYPE = socialPartyEvent22;
        SocialPartyEvent socialPartyEvent23 = new SocialPartyEvent("PARTY_DIALOG_UPDATE_TYPE_SUCCESS", 22, "party_dialog_update_type_success");
        PARTY_DIALOG_UPDATE_TYPE_SUCCESS = socialPartyEvent23;
        SocialPartyEvent socialPartyEvent24 = new SocialPartyEvent("PARTY_DIALOG_UPDATE_TYPE_FAIL", 23, "party_dialog_update_type_fail");
        PARTY_DIALOG_UPDATE_TYPE_FAIL = socialPartyEvent24;
        SocialPartyEvent socialPartyEvent25 = new SocialPartyEvent("PARTY_DIALOG_CHANGE_VOLUME", 24, "party_dialog_change_volume");
        PARTY_DIALOG_CHANGE_VOLUME = socialPartyEvent25;
        SocialPartyEvent socialPartyEvent26 = new SocialPartyEvent("PARTY_DIALOG_UPDATE_MIC_STATE", 25, "party_dialog_update_mic_state");
        PARTY_DIALOG_UPDATE_MIC_STATE = socialPartyEvent26;
        SocialPartyEvent socialPartyEvent27 = new SocialPartyEvent("JOIN_DIALOG_DISMISS", 26, "join_dialog_dismiss");
        JOIN_DIALOG_DISMISS = socialPartyEvent27;
        SocialPartyEvent socialPartyEvent28 = new SocialPartyEvent("JOIN_DIALOG_JOIN", 27, "join_dialog_join");
        JOIN_DIALOG_JOIN = socialPartyEvent28;
        SocialPartyEvent socialPartyEvent29 = new SocialPartyEvent("JOIN_DIALOG_JOIN_SUCCESS", 28, "join_dialog_join_success");
        JOIN_DIALOG_JOIN_SUCCESS = socialPartyEvent29;
        SocialPartyEvent socialPartyEvent30 = new SocialPartyEvent("JOIN_DIALOG_JOIN_FAIL", 29, "join_dialog_join_fail");
        JOIN_DIALOG_JOIN_FAIL = socialPartyEvent30;
        SocialPartyEvent socialPartyEvent31 = new SocialPartyEvent("JOIN_DIALOG_APP_LAUNCH", 30, "join_dialog_app_launch");
        JOIN_DIALOG_APP_LAUNCH = socialPartyEvent31;
        SocialPartyEvent socialPartyEvent32 = new SocialPartyEvent("PARTY_FOOTER_JOIN_LEADER", 31, "party_footer_join_leader");
        PARTY_FOOTER_JOIN_LEADER = socialPartyEvent32;
        SocialPartyEvent socialPartyEvent33 = new SocialPartyEvent("PARTY_FOOTER_GO_TO_APP_LAUNCH", 32, "party_footer_go_to_app_launch");
        PARTY_FOOTER_GO_TO_APP_LAUNCH = socialPartyEvent33;
        SocialPartyEvent[] socialPartyEventArr = new SocialPartyEvent[33];
        System.arraycopy(new SocialPartyEvent[]{socialPartyEvent, socialPartyEvent2, socialPartyEvent3, socialPartyEvent4, socialPartyEvent5, socialPartyEvent6, socialPartyEvent7, socialPartyEvent8, socialPartyEvent9, socialPartyEvent10, socialPartyEvent11, socialPartyEvent12, socialPartyEvent13, socialPartyEvent14, socialPartyEvent15, socialPartyEvent16, socialPartyEvent17, socialPartyEvent18, socialPartyEvent19, socialPartyEvent20, socialPartyEvent21, socialPartyEvent22, socialPartyEvent23, socialPartyEvent24, socialPartyEvent25, socialPartyEvent26, socialPartyEvent27}, 0, socialPartyEventArr, 0, 27);
        System.arraycopy(new SocialPartyEvent[]{socialPartyEvent28, socialPartyEvent29, socialPartyEvent30, socialPartyEvent31, socialPartyEvent32, socialPartyEvent33}, 0, socialPartyEventArr, 27, 6);
        $VALUES = socialPartyEventArr;
    }

    public static SocialPartyEvent valueOf(String str) {
        return (SocialPartyEvent) Enum.valueOf(SocialPartyEvent.class, str);
    }

    public static SocialPartyEvent[] values() {
        return (SocialPartyEvent[]) $VALUES.clone();
    }

    public String toString() {
        return this.mPartyEvent;
    }

    public SocialPartyEvent(String str, int i, String str2) {
        this.mPartyEvent = str2;
    }
}
