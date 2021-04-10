package com.oculus.authapi;

import android.os.Bundle;

public class AuthError extends Exception {
    public final int mErrorCode;
    public final String mErrorMessage;
    public final String mErrorTitle;

    public AuthError(Bundle resultData) {
        this.mErrorCode = resultData.getInt("error_code", -7);
        this.mErrorTitle = resultData.getString("error_title");
        this.mErrorMessage = resultData.getString("error_message");
    }

    public AuthError(int errorCode, String errorTitle, String errorMessage) {
        this.mErrorCode = errorCode;
        this.mErrorTitle = errorTitle;
        this.mErrorMessage = errorMessage;
    }

    public String getMessage() {
        return this.mErrorMessage != null ? this.mErrorMessage : super.getMessage();
    }

    public int getErrorCode() {
        return this.mErrorCode;
    }

    public String getErrorTitle() {
        return this.mErrorTitle;
    }
}
