package com.oculus.panelapp.socialsettings.views;

import X.AnonymousClass1uc;
import androidx.databinding.Bindable;

public class SocialSettingsPrivacyViewModel extends AnonymousClass1uc {
    public boolean mIsDeviceLockPatternSet;

    @Bindable
    public boolean getIsDeviceLockPatternSet() {
        return this.mIsDeviceLockPatternSet;
    }

    public void setIsDeviceLockPatternSet(boolean z) {
        this.mIsDeviceLockPatternSet = z;
        notifyPropertyChanged(85);
    }
}
