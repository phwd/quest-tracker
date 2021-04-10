package com.oculus.authapi;

import android.os.Bundle;
import com.facebook.annotations.OkToExtend;

@OkToExtend
public class AuthError extends Exception {
    public final int mErrorCode;
    public final String mErrorMessage;
    public final String mErrorTitle;

    public final String getMessage() {
        String str = this.mErrorMessage;
        if (str == null) {
            return super.getMessage();
        }
        return str;
    }

    public AuthError(int i, String str, String str2) {
        this.mErrorCode = i;
        this.mErrorTitle = str;
        this.mErrorMessage = str2;
    }

    public AuthError(Bundle bundle) {
        this.mErrorCode = bundle.getInt("error_code", -7);
        this.mErrorTitle = bundle.getString("error_title");
        this.mErrorMessage = bundle.getString("error_message");
    }
}
