package com.oculus.panelapp.socialandroidbackpanel.views.error;

import android.content.Context;
import androidx.annotation.StringRes;
import com.oculus.socialplatform.R;

public enum ErrorType {
    GENERAL(R.string.general_error_title, R.string.general_error_message),
    PARTY_GENERAL(R.string.party_general_error_title, R.string.party_general_error_message),
    PARTY_NO_LONGER_AVAILABLE(R.string.party_no_longer_available_error_title, R.string.party_no_longer_available_error_message),
    PARTY_FULL(R.string.party_is_full_error_title, R.string.party_is_full_error_message),
    PARTY_PRIVACY(R.string.party_general_error_title, R.string.party_privacy_general_error_message);
    
    @StringRes
    public final int mMessageResourceId;
    @StringRes
    public final int mTitleResourceId;

    public String getMessage(Context context) {
        return context.getString(this.mMessageResourceId);
    }

    public String getTitle(Context context) {
        return context.getString(this.mTitleResourceId);
    }

    /* access modifiers changed from: public */
    ErrorType(@StringRes int i, @StringRes int i2) {
        this.mTitleResourceId = i;
        this.mMessageResourceId = i2;
    }
}
