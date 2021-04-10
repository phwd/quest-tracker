package com.oculus.authapi;

import android.os.Bundle;
import com.facebook.annotations.OkToExtend;

@OkToExtend
public class AuthError extends Exception {
    public final int mErrorCode;
    public final String mErrorMessage;
    public final String mErrorTitle;

    public AuthError(Bundle bundle) {
        this.mErrorCode = bundle.getInt("error_code", -7);
        this.mErrorTitle = bundle.getString("error_title");
        this.mErrorMessage = bundle.getString("error_message");
    }

    public String getMessage() {
        String str = this.mErrorMessage;
        return str != null ? str : super.getMessage();
    }

    public int getErrorCode() {
        return this.mErrorCode;
    }

    public String getErrorTitle() {
        return this.mErrorTitle;
    }
}
