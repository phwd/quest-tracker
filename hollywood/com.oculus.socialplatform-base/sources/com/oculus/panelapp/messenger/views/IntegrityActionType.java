package com.oculus.panelapp.messenger.views;

public enum IntegrityActionType {
    Block("Block"),
    Report("Report");
    
    public final String mParamString;

    public String getParamString() {
        return this.mParamString;
    }

    /* access modifiers changed from: public */
    IntegrityActionType(String str) {
        this.mParamString = str;
    }
}
