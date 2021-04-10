package com.oculus.panelapp.socialsettings.views;

import X.AnonymousClass1uc;
import androidx.databinding.Bindable;

public class SocialSettingsActiveStatusViewModel extends AnonymousClass1uc {
    public boolean mIsLoaded;

    @Bindable
    public boolean getIsLoaded() {
        return this.mIsLoaded;
    }

    public void setIsLoaded(boolean z) {
        this.mIsLoaded = z;
        notifyPropertyChanged(88);
    }
}
