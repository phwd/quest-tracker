package com.oculus.horizon.api.tos;

public enum TosType {
    TOS,
    PRIVACY_POLICY("PP"),
    DEVELOPERS;
    
    private String value;

    private TosType() {
        this.value = name();
    }

    private TosType(String str) {
        this.value = str;
    }

    public String toString() {
        return this.value;
    }
}
