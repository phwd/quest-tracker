package com.oculus.panelapp.social.logging;

public enum SocialPartyEvent {
    ROOT_EVENT("oculus_aui_party"),
    SOCIAL_ICON_CLICK("social_icon_click"),
    INVITE_DIALOG_CLOSE("invite_dialog_close"),
    FB_LINK_UPSELL_DIALOG_CLOSE("oculus_fb_link_upsell_cancel"),
    FB_LINK_UPSELL_DIALOG_OPEN("oculus_fb_link_upsell_impression"),
    FB_LINK_UPSELL_LINK_CLICK("oculus_fb_link_upsell_confirm"),
    INVITE_DIALOG_FETCH_FRIENDS("invite_dialog_fetch_friends"),
    INVITE_DIALOG_FETCH_FRIENDS_SUCCESS("invite_dialog_fetch_friends_success"),
    INVITE_DIALOG_FETCH_FRIENDS_FAIL("invite_dialog_fetch_friends_fail"),
    INVITE_DIALOG_INVITE("invite_dialog_invite"),
    INVITE_DIALOG_INVITE_SUCCESS("invite_dialog_invite_success"),
    INVITE_DIALOG_INVITE_FAIL("invite_dialog_invite_fail"),
    INVITE_DIALOG_INVITE_PARTY_CREATE("invite_dialog_invite_party_create"),
    INVITE_DIALOG_INVITE_PARTY_CREATE_SUCCESS("invite_dialog_invite_party_create_success"),
    INVITE_DIALOG_INVITE_PARTY_CREATE_FAIL("invite_dialog_invite_party_create_fail"),
    INVITE_DIALOG_QUEUED_INVITES_ON_CREATE("invite_dialog_queued_invites_on_create"),
    PARTY_DIALOG_DISMISS("party_dialog_dismiss"),
    PARTY_DIALOG_JOIN_BUTTON_APP_LAUNCH("party_dialog_join_button_app_launch"),
    PARTY_DIALOG_LEAVE("party_dialog_leave"),
    PARTY_DIALOG_LEAVE_SUCCESS("party_dialog_leave_success"),
    PARTY_DIALOG_LEAVE_FAIL("party_dialog_leave_fail"),
    PARTY_DIALOG_UPDATE_TYPE("party_dialog_update_type"),
    PARTY_DIALOG_UPDATE_TYPE_SUCCESS("party_dialog_update_type_success"),
    PARTY_DIALOG_UPDATE_TYPE_FAIL("party_dialog_update_type_fail"),
    PARTY_DIALOG_CHANGE_VOLUME("party_dialog_change_volume"),
    PARTY_DIALOG_UPDATE_MIC_STATE("party_dialog_update_mic_state"),
    JOIN_DIALOG_DISMISS("join_dialog_dismiss"),
    JOIN_DIALOG_JOIN("join_dialog_join"),
    JOIN_DIALOG_JOIN_SUCCESS("join_dialog_join_success"),
    JOIN_DIALOG_JOIN_FAIL("join_dialog_join_fail"),
    JOIN_DIALOG_APP_LAUNCH("join_dialog_app_launch"),
    PARTY_FOOTER_JOIN_LEADER("party_footer_join_leader"),
    PARTY_FOOTER_GO_TO_APP_LAUNCH("party_footer_go_to_app_launch");
    
    private final String mPartyEvent;

    private SocialPartyEvent(String str) {
        this.mPartyEvent = str;
    }

    public String toString() {
        return this.mPartyEvent;
    }
}
