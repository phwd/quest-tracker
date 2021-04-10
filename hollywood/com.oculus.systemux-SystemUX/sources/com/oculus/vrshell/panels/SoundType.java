package com.oculus.vrshell.panels;

public enum SoundType {
    CLOSE("close1"),
    HOVER("hover1"),
    SELECT("select1");
    
    private final String audioName;

    private SoundType(String str) {
        this.audioName = str;
    }

    public String getAudioName() {
        return this.audioName;
    }
}
