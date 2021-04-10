package com.oculus.vrshell.panels;

public enum SoundType {
    CLOSE("close1"),
    HOVER("hover1"),
    SELECT("select1");
    
    public final String audioName;

    public String getAudioName() {
        return this.audioName;
    }

    /* access modifiers changed from: public */
    SoundType(String str) {
        this.audioName = str;
    }
}
