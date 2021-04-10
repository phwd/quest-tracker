package com.oculus.localmedia;

public enum MediaErrorCode {
    NONE,
    INTERNAL_ERROR,
    READ_PERMISSION,
    SERVER_DISABLED,
    NOT_AUTHORIZED,
    INVALID_TOKEN;

    public static MediaErrorCode fromHttpStatusCode(int statusCode) {
        if (statusCode == 401) {
            return NOT_AUTHORIZED;
        }
        if (statusCode == 503) {
            return SERVER_DISABLED;
        }
        if (statusCode == 403) {
            return READ_PERMISSION;
        }
        return INTERNAL_ERROR;
    }
}
