package com.oculus.appmanager.constants;

public enum OxygenInstallerCodes {
    INVALID_CODE(-10001),
    PERMISSION_NOT_GRANTED(-10002),
    UNEXPECTED_EXCEPTION_ERROR(-10003),
    ILLEGAL_ARGUMENT_ERROR(-10004),
    APK_FILE_IS_WRITABLE(-10005),
    UNTRUSTED_APK_ERROR(-10006);
    
    public final int code;

    private OxygenInstallerCodes(int i) {
        this.code = i;
    }
}
