package com.oculus.panelapp.social;

import com.oculus.socialplatform.R;

public enum SocialGuidedAction {
    FB_LINK(R.drawable.oc_icon_facebook_filled_24_d2d2d2, R.string.anytime_tablet_social_guided_action_fb_link_description, R.string.anytime_tablet_social_guided_action_fb_link_button),
    CREATE_AVATAR(R.drawable.oc_icon_profile_filled_24_d2d2d2, R.string.anytime_tablet_social_guided_action_avatar_description, R.string.anytime_tablet_social_guided_action_avatar_button),
    ADD_FRIEND(R.drawable.oc_icon_social_filled_24_d2d2d2, R.string.anytime_tablet_social_guided_action_friends_description, R.string.anytime_tablet_social_guided_action_friends_button),
    SHARE_PROFILE(R.drawable.oc_icon_share_filled_24_d2d2d2, R.string.anytime_tablet_social_guided_action_share_profile_description, R.string.anytime_tablet_social_guided_action_share_profile_button),
    FIND_EVENTS(R.drawable.oc_icon_events_filled_24_d2d2d2, R.string.anytime_tablet_social_guided_action_event_description, R.string.anytime_tablet_social_guided_action_event_button),
    ADD_TO_PARTY(R.drawable.oc_icon_friends_add_filled_24_d2d2d2, R.string.anytime_tablet_social_guided_action_invite_friends_button, R.string.anytime_tablet_social_guided_action_invite_friends_button);
    
    public int mButtonStringId;
    public int mDescriptionId;
    public int mIconId;

    public int getButtonStringId() {
        return this.mButtonStringId;
    }

    public int getDescriptionId() {
        return this.mDescriptionId;
    }

    public int getIconId() {
        return this.mIconId;
    }

    /* access modifiers changed from: public */
    SocialGuidedAction(int i, int i2, int i3) {
        this.mIconId = i;
        this.mDescriptionId = i2;
        this.mButtonStringId = i3;
    }
}
