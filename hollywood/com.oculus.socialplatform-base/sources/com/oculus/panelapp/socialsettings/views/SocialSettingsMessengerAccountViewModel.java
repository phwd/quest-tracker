package com.oculus.panelapp.socialsettings.views;

import X.AnonymousClass1uc;
import androidx.databinding.Bindable;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.tablet.view.ViewModelLifecycle;

public class SocialSettingsMessengerAccountViewModel extends AnonymousClass1uc implements ViewModelLifecycle {
    public static final String TAG = LoggingUtil.tag(SocialSettingsMessengerAccountViewModel.class);
    public boolean mIsSigningOut;
    public String mUserName;

    @Override // com.oculus.tablet.view.ViewModelLifecycle
    public void destroy() {
    }

    @Bindable
    public boolean getSignOutButtonEnabled() {
        return !this.mIsSigningOut;
    }

    @Bindable
    public String getUserName() {
        return this.mUserName;
    }

    public void setIsSigningOut(boolean z) {
        this.mIsSigningOut = z;
        notifyPropertyChanged(86);
    }

    public void setUserName(String str) {
        this.mUserName = str;
        notifyPropertyChanged(87);
    }
}
