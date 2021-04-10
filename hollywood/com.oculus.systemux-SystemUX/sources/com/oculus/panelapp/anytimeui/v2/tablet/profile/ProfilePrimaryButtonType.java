package com.oculus.panelapp.anytimeui.v2.tablet.profile;

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;
import com.oculus.panelapp.anytimeui.R;

public enum ProfilePrimaryButtonType {
    COMPLETE_PROFILE(R.string.anytime_tablet_profile_complete_profile, R.drawable.oc_icon_edit_filled_24_d2d2d2),
    EDIT(R.string.anytime_tablet_profile_edit, R.drawable.oc_icon_edit_filled_24_d2d2d2),
    VR_CREATE_PARTY(R.string.anytime_tablet_profile_party_create_button, R.drawable.oc_icon_parties_filled_24_d2d2d2),
    VR_INVITE_TO_PARTY(R.string.anytime_tablet_profile_party_invite_button, R.drawable.oc_icon_party_add_filled_24_d2d2d2),
    VR_JOIN_PARTY(R.string.anytime_tablet_profile_party_join_button, R.drawable.oc_icon_party_join_filled_24_d2d2d2),
    VR_USER(R.string.anytime_tablet_profile_add_friend_button, R.drawable.oc_icon_friends_add_filled_24_d2d2d2),
    INCOMING_FRIEND_REQUEST(R.string.anytime_tablet_profile_accept_friend_button, R.drawable.oc_icon_friends_add_filled_24_d2d2d2),
    VR_USER_OUTGOING_REQUEST(R.string.anytime_tablet_profile_cancel_friend_request_button, R.drawable.oc_icon_friends_request_sent_filled_24_d2d2d2),
    CHAT(R.string.anytime_tablet_profile_chat_button, R.drawable.oc_icon_chat_filled_24_d2d2d2),
    FB_CHAT(R.string.anytime_tablet_profile_chat_in_messenger, R.drawable.oc_icon_messenger_filled_24_d2d2d2),
    FB_CREATE_PARTY(R.string.anytime_tablet_profile_party_create_button, R.drawable.oc_icon_parties_filled_24_d2d2d2),
    FB_INVITE_TO_PARTY(R.string.anytime_tablet_profile_party_invite_button, R.drawable.oc_icon_party_add_filled_24_d2d2d2),
    FB_JOIN_PARTY(R.string.anytime_tablet_profile_party_join_button, R.drawable.oc_icon_party_join_filled_24_d2d2d2),
    DEFAULT(R.string.anytime_tablet_profile_chat_button, R.drawable.oc_icon_chat_filled_24_d2d2d2);
    
    @DrawableRes
    private int mIconId;
    @StringRes
    private int mStringId;

    private ProfilePrimaryButtonType(int i, int i2) {
        this.mStringId = i;
        this.mIconId = i2;
    }

    public int getStringId() {
        return this.mStringId;
    }

    public int getIconId() {
        return this.mIconId;
    }
}
