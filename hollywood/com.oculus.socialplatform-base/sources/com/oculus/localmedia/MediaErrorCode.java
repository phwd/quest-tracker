package com.oculus.localmedia;

public enum MediaErrorCode {
    NONE,
    INTERNAL_ERROR,
    READ_PERMISSION,
    SERVER_DISABLED,
    NOT_AUTHORIZED,
    INVALID_TOKEN;

    public static MediaErrorCode fromHttpStatusCode(int i) {
        if (i == 401) {
            return NOT_AUTHORIZED;
        }
        if (i == 503) {
            return SERVER_DISABLED;
        }
        if (i == 403) {
            return READ_PERMISSION;
        }
        return INTERNAL_ERROR;
    }
}
