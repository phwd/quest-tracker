package com.oculus.panelapp.socialandroidbackpanel.views.error;

import X.AnonymousClass1uc;
import androidx.databinding.Bindable;

public class ErrorViewModel extends AnonymousClass1uc {
    public boolean hasCta = false;
    public String mMessage;
    public String mTitle;

    @Bindable
    public boolean getHasCta() {
        return this.hasCta;
    }

    @Bindable
    public String getMessage() {
        return this.mMessage;
    }

    @Bindable
    public String getTitle() {
        return this.mTitle;
    }

    public void setHasCta(boolean z) {
        this.hasCta = z;
        notifyPropertyChanged(188);
    }

    public void setMessage(String str) {
        this.mMessage = str;
        notifyPropertyChanged(156);
    }

    public void setTitle(String str) {
        this.mTitle = str;
        notifyPropertyChanged(67);
    }
}
