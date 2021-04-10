package com.oculus.authapi;

import android.os.Bundle;

public class AuthError extends Exception {
    public final int mErrorCode;
    public final String mErrorMessage;
    public final String mErrorTitle;

    public AuthError(Bundle bundle) {
        this.mErrorCode = bundle.getInt("error_code", -7);
        this.mErrorTitle = bundle.getString("error_title");
        this.mErrorMessage = bundle.getString("error_message");
    }

    public AuthError(int i, String str, String str2) {
        this.mErrorCode = i;
        this.mErrorTitle = str;
        this.mErrorMessage = str2;
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
