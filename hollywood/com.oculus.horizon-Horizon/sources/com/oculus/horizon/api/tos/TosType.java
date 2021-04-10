package com.oculus.horizon.api.tos;

public enum TosType {
    TOS,
    PRIVACY_POLICY("PP"),
    DEVELOPERS;
    
    public String value;

    public String toString() {
        return this.value;
    }

    /* access modifiers changed from: public */
    TosType() {
        this.value = name();
    }

    /* access modifiers changed from: public */
    TosType(String str) {
        this.value = str;
    }
}
