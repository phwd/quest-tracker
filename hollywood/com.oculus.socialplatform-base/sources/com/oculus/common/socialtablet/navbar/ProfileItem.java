package com.oculus.common.socialtablet.navbar;

import android.graphics.drawable.Drawable;

public class ProfileItem {
    public Drawable mProfilePic;
    public ProfileType mProfileType;
    public String mUsername;

    public Drawable getProfilePic() {
        return this.mProfilePic;
    }

    public ProfileType getProfileType() {
        return this.mProfileType;
    }

    public String getUsername() {
        return this.mUsername;
    }

    public ProfileItem(String str, ProfileType profileType, Drawable drawable) {
        this.mUsername = str;
        this.mProfileType = profileType;
        this.mProfilePic = drawable;
    }

    public void setProfilePic(Drawable drawable) {
        this.mProfilePic = drawable;
    }
}
