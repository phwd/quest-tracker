package com.oculus.systemdialog;

public enum DialogListType {
    NO_SELECT("noSelect"),
    SINGLE_SELECT("singleSelect"),
    MULTI_SELECT("multiSelect");
    
    private final String mIPCString;

    private DialogListType(String str) {
        this.mIPCString = str;
    }

    public String getIPCString() {
        return this.mIPCString;
    }
}
