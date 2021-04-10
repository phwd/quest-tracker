package com.oculus.panelapp.androiddialog.dialogs.error;

import android.content.Context;
import androidx.annotation.StringRes;
import com.oculus.panelapp.androiddialog.R;

public enum ErrorType {
    GENERAL(R.string.general_error_title, R.string.general_error_message),
    PARTY_GENERAL(R.string.party_general_error_title, R.string.party_general_error_message),
    PARTY_NO_LONGER_AVAILABLE(R.string.party_no_longer_available_error_title, R.string.party_no_longer_available_error_message),
    PARTY_FULL(R.string.party_is_full_error_title, R.string.party_is_full_error_message),
    PARTY_PRIVACY(R.string.party_general_error_title, R.string.party_privacy_general_error_message);
    
    @StringRes
    private final int mMessageResourceId;
    @StringRes
    private final int mTitleResourceId;

    private ErrorType(@StringRes int i, @StringRes int i2) {
        this.mTitleResourceId = i;
        this.mMessageResourceId = i2;
    }

    public String getTitle(Context context) {
        return context.getString(this.mTitleResourceId);
    }

    public String getMessage(Context context) {
        return context.getString(this.mMessageResourceId);
    }
}
