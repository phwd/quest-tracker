package com.oculus.common.socialtablet.navbar;

import com.oculus.socialplatform.R;

public enum ProfileType {
    FACEBOOK(R.string.anytime_tablet_messenger_profile_switcher_facebook),
    OCULUS(R.string.anytime_tablet_messenger_profile_switcher_oculus);
    
    public final int mStringId;

    public int getStringId() {
        return this.mStringId;
    }

    /* access modifiers changed from: public */
    ProfileType(int i) {
        this.mStringId = i;
    }
}
