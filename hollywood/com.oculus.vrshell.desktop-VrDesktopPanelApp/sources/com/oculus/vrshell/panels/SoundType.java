package com.oculus.vrshell.panels;

public enum SoundType {
    CLOSE("close1"),
    HOVER("hover1"),
    SELECT("select1");
    
    private final String audioName;

    private SoundType(String audioName2) {
        this.audioName = audioName2;
    }

    public String getAudioName() {
        return this.audioName;
    }
}
