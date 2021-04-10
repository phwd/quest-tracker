package com.oculus.platform.util;

public enum OVRError {
    UNKNOWN_ERROR(1, "An Unknown error occurred"),
    AUTHENTICATION_ERROR(2, "The user isn't signed in or their account state wasn't in a recoverable state."),
    NETWORK_ERROR(3, "The request could not be completed due to lack of network connectivity, network timeouts, etc"),
    STORE_INSTALLATION_ERROR(4, "Either the service couldn't be connected to, Horizon isn't installed, or you're running an incompatible version of Home/Horizon"),
    CALLER_NOT_SIGNED(5, "The app isn't VR signed."),
    UNKNOWN_SERVER_ERROR(6, "An unknown error occurred on the server."),
    PERMISSIONS_FAILURE(7, "Caller does not have permission to use platform on behalf of this user");
    
    public final int mCode;
    public final String mErrorMessage;

    /* access modifiers changed from: public */
    OVRError(int i, String str) {
        this.mCode = i;
        this.mErrorMessage = str;
    }

    public static OVRError fromInt(int i) {
        OVRError[] values = values();
        for (OVRError oVRError : values) {
            if (oVRError.mCode == i) {
                return oVRError;
            }
        }
        return UNKNOWN_ERROR;
    }
}
