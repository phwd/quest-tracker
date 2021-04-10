package com.oculus.common.socialtablet.navbar;

import X.AnonymousClass1uc;
import androidx.databinding.Bindable;

public class SocialTabletSideNavViewModel extends AnonymousClass1uc {
    public boolean mIsHidingNavElements;
    public ProfileType mProfileType;

    @Bindable
    public int getChatVisibility() {
        if (this.mIsHidingNavElements) {
            return 8;
        }
        return 0;
    }

    @Bindable
    public int getPeopleVisibility() {
        if (this.mIsHidingNavElements) {
            return 8;
        }
        return 0;
    }

    @Bindable
    public int getSettingsVisibility() {
        if (this.mProfileType == ProfileType.OCULUS || this.mIsHidingNavElements) {
            return 8;
        }
        return 0;
    }

    public void setIsHidingNavElements(boolean z) {
        this.mIsHidingNavElements = z;
        notifyPropertyChanged(230);
        notifyPropertyChanged(231);
        notifyPropertyChanged(229);
    }

    public void setProfileType(ProfileType profileType) {
        this.mProfileType = profileType;
        notifyPropertyChanged(230);
        notifyPropertyChanged(231);
        notifyPropertyChanged(229);
    }
}
