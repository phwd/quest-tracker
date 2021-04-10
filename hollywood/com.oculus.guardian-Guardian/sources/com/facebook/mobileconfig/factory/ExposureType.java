package com.facebook.mobileconfig.factory;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public enum ExposureType {
    AUTO_EXPOSURE("auto"),
    MANUAL_EXPOSURE("man");
    
    private String mValue;

    private ExposureType(String val) {
        this.mValue = val;
    }

    public String getValue() {
        return this.mValue;
    }

    public String toString() {
        return getValue();
    }
}
