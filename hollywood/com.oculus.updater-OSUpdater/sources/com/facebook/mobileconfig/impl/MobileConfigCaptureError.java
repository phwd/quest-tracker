package com.facebook.mobileconfig.impl;

public class MobileConfigCaptureError {
    private MobileConfigError mError = null;

    public void setError(MobileConfigError mobileConfigError) {
        this.mError = mobileConfigError;
    }

    public MobileConfigError getError() {
        return this.mError;
    }
}
