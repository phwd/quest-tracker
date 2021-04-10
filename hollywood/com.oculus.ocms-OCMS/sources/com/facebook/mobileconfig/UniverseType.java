package com.facebook.mobileconfig;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public enum UniverseType {
    Facebook(0),
    Oculus(1),
    Flash(2),
    Onavo(4),
    ExpressWiFi(5),
    Anna(6),
    INSTAGRAM(7),
    HARDWARE(8);
    
    private int value;

    private UniverseType(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
