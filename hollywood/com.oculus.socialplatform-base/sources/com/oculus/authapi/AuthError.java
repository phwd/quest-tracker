package com.oculus.authapi;

import android.os.Bundle;
import com.facebook.annotations.OkToExtend;
import com.oculus.auth.service.contract.ServiceContract;

@OkToExtend
public class AuthError extends Exception {
    public static final int CODE_ACCOUNT_ALREADY_EXISTS = -17;
    public static final int CODE_UNKNOWN_ERROR = -7;
    public final int mErrorCode;
    public final String mErrorMessage;
    public final String mErrorTitle;

    public String getMessage() {
        String str = this.mErrorMessage;
        if (str == null) {
            return super.getMessage();
        }
        return str;
    }

    public int getErrorCode() {
        return this.mErrorCode;
    }

    public String getErrorTitle() {
        return this.mErrorTitle;
    }

    public AuthError(int i, String str, String str2) {
        this.mErrorCode = i;
        this.mErrorTitle = str;
        this.mErrorMessage = str2;
    }

    public AuthError(Bundle bundle) {
        this.mErrorCode = bundle.getInt("error_code", -7);
        this.mErrorTitle = bundle.getString(ServiceContract.EXTRA_ERROR_TITLE);
        this.mErrorMessage = bundle.getString("error_message");
    }
}
