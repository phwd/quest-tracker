package com.oculus.panelapp.keyboardv2;

public enum ConnectionType {
    KEYBOARD_IME("KeyboardIME");
    
    private String mName;

    private ConnectionType(String str) {
        this.mName = str;
    }

    public String getName() {
        return this.mName;
    }
}
