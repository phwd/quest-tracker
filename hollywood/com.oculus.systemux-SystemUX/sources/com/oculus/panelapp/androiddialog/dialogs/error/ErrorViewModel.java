package com.oculus.panelapp.androiddialog.dialogs.error;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import com.oculus.panelapp.androiddialog.BR;

public class ErrorViewModel extends BaseObservable {
    private boolean hasCta = false;
    private String mMessage;
    private String mTitle;

    @Bindable
    public String getTitle() {
        return this.mTitle;
    }

    public void setTitle(String str) {
        this.mTitle = str;
        notifyPropertyChanged(BR.title);
    }

    @Bindable
    public String getMessage() {
        return this.mMessage;
    }

    public void setMessage(String str) {
        this.mMessage = str;
        notifyPropertyChanged(BR.message);
    }

    @Bindable
    public boolean getHasCta() {
        return this.hasCta;
    }

    public void setHasCta(boolean z) {
        this.hasCta = z;
        notifyPropertyChanged(BR.hasCta);
    }
}
