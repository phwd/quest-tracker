package com.oculus.systemdialog;

public enum DialogListItemImageType {
    AVATAR("avatar"),
    GLYPH("glyph");
    
    private final String mIPCString;

    private DialogListItemImageType(String str) {
        this.mIPCString = str;
    }

    public String getIPCString() {
        return this.mIPCString;
    }
}
