package com.oculus.panelapp.people.views;

import X.AnonymousClass1uc;
import androidx.databinding.Bindable;
import com.oculus.common.logutilities.LoggingUtil;

public class PeopleQuickMessagePopupViewModel extends AnonymousClass1uc {
    public static final String TAG = LoggingUtil.tag(PeopleQuickMessagePopupViewModel.class);
    public boolean mIsLoading = false;
    public boolean mIsSuccess = false;

    @Bindable
    public boolean getIsLoading() {
        return this.mIsLoading;
    }

    @Bindable
    public boolean getIsSuccess() {
        return this.mIsSuccess;
    }

    public void setIsLoading(boolean z) {
        this.mIsLoading = z;
        notifyPropertyChanged(23);
    }

    public void setIsSuccess(boolean z) {
        this.mIsSuccess = z;
        notifyPropertyChanged(74);
    }
}
