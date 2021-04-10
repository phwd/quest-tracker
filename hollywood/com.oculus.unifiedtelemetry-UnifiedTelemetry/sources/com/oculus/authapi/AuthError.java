package com.oculus.authapi;

import android.os.Bundle;
import com.facebook.annotations.OkToExtend;
import com.oculus.auth.service.contract.ServiceContract;

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

    public AuthError(Bundle bundle) {
        this.mErrorCode = bundle.getInt("error_code", -7);
        this.mErrorTitle = bundle.getString(ServiceContract.EXTRA_ERROR_TITLE);
        this.mErrorMessage = bundle.getString("error_message");
    }
}
