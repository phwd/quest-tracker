package com.facebook.mobileconfig;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public enum ConsistencyType {
    API(0),
    API2(1),
    STORAGE(2),
    OMNISTORE(3),
    OMNISTORE_SHADOW(4),
    OMNISTORE_SQLITE(5),
    DEBUG_UI_API(6),
    DEBUG_UI_STORAGE(7),
    EARLY_ACCESS(8),
    RN(10),
    SERVICE(11);
    
    private int mValue;

    private ConsistencyType(int i) {
        this.mValue = i;
    }

    public int getValue() {
        return this.mValue;
    }
}
