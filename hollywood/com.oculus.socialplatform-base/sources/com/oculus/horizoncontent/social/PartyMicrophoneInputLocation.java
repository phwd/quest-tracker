package com.oculus.horizoncontent.social;

import android.util.SparseArray;

public enum PartyMicrophoneInputLocation {
    APP(0),
    PARTY(1);
    
    public static final SparseArray<PartyMicrophoneInputLocation> ENUM_MAP;
    public Integer mLocation;

    /* access modifiers changed from: public */
    static {
        SparseArray<PartyMicrophoneInputLocation> sparseArray = new SparseArray<>();
        PartyMicrophoneInputLocation[] values = values();
        for (PartyMicrophoneInputLocation partyMicrophoneInputLocation : values) {
            sparseArray.put(partyMicrophoneInputLocation.getLocation().intValue(), partyMicrophoneInputLocation);
        }
        ENUM_MAP = sparseArray;
    }

    public static PartyMicrophoneInputLocation get(Integer num) {
        return ENUM_MAP.get(num.intValue());
    }

    public Integer getLocation() {
        return this.mLocation;
    }

    /* access modifiers changed from: public */
    PartyMicrophoneInputLocation(int i) {
        this.mLocation = Integer.valueOf(i);
    }
}
