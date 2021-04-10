package com.oculus.systemdialog;

public enum DialogListItemImageType {
    AVATAR("avatar"),
    GLYPH("glyph");
    
    public final String mIPCString;

    public String getIPCString() {
        return this.mIPCString;
    }

    /* access modifiers changed from: public */
    DialogListItemImageType(String str) {
        this.mIPCString = str;
    }
}
