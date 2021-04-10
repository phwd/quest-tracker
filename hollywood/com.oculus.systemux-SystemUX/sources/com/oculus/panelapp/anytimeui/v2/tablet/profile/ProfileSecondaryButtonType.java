package com.oculus.panelapp.anytimeui.v2.tablet.profile;

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;
import com.oculus.panelapp.anytimeui.R;

public enum ProfileSecondaryButtonType {
    ADD_FRIEND(R.string.anytime_tablet_profile_add_friend_button, R.drawable.oc_icon_friends_add_filled_24_d2d2d2),
    CHAT(R.string.anytime_tablet_profile_chat_button, R.drawable.oc_icon_chat_filled_24_d2d2d2),
    FB_CHAT(R.string.anytime_tablet_profile_chat_in_messenger, R.drawable.oc_icon_messenger_filled_24_d2d2d2),
    DEFAULT(R.string.anytime_tablet_profile_chat_button, R.drawable.oc_icon_chat_filled_24_d2d2d2);
    
    @DrawableRes
    private int mIconId;
    @StringRes
    private int mStringId;

    private ProfileSecondaryButtonType(int i, int i2) {
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
