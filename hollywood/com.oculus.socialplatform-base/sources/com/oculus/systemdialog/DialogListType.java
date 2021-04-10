package com.oculus.systemdialog;

public enum DialogListType {
    NO_SELECT("noSelect"),
    SINGLE_SELECT("singleSelect"),
    MULTI_SELECT("multiSelect");
    
    public final String mIPCString;

    public String getIPCString() {
        return this.mIPCString;
    }

    /* access modifiers changed from: public */
    DialogListType(String str) {
        this.mIPCString = str;
    }
}
