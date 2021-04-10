package com.oculus.panelapp.socialsettings.views;

import X.AnonymousClass1uc;
import androidx.databinding.Bindable;

public class SocialSettingsViewModel extends AnonymousClass1uc {
    public SocialSettingsViewType mSelectedSetting;

    @Bindable
    public SocialSettingsViewType getSelectedSetting() {
        return this.mSelectedSetting;
    }

    public void setSelectedSetting(SocialSettingsViewType socialSettingsViewType) {
        this.mSelectedSetting = socialSettingsViewType;
        notifyPropertyChanged(84);
    }
}
