package com.oculus.systemdialog;

public enum DialogPrimaryButtonStyle {
    PRIMARY("primary"),
    DANGER("danger"),
    SECONDARY("secondary");
    
    private final String mIPCString;

    private DialogPrimaryButtonStyle(String str) {
        this.mIPCString = str;
    }

    public String getIPCString() {
        return this.mIPCString;
    }
}
