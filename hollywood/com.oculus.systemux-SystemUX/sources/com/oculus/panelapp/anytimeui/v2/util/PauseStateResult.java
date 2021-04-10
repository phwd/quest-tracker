package com.oculus.panelapp.anytimeui.v2.util;

public enum PauseStateResult {
    RESUME("resume"),
    QUIT("quit");
    
    private String IPCString;

    private PauseStateResult(String str) {
        this.IPCString = str;
    }

    public String getIPCString() {
        return this.IPCString;
    }
}
