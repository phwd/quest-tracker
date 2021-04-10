package com.oculus.platform.aidl;

public class RemoteConstants {
    public static final String BUNDLE_KEY_ERROR_CODE = "error_code";
    public static final String BUNDLE_KEY_ERROR_MESSAGE = "error_message";
    public static final String BUNDLE_KEY_PAYLOAD = "payload";

    public enum EntitlementResult {
        EXCEPTION,
        ENTITLED,
        NOT_ENTITLED,
        NOT_LOGGED_IN,
        NOT_VR_SIGNED
    }
}
