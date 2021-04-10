package com.oculus.horizoncontent.social;

import android.util.SparseArray;

public enum PartyMicrophoneInputLocation {
    APP(0),
    PARTY(1);
    
    private static final SparseArray<PartyMicrophoneInputLocation> ENUM_MAP;
    private Integer mLocation;

    static {
        SparseArray<PartyMicrophoneInputLocation> sparseArray = new SparseArray<>();
        PartyMicrophoneInputLocation[] values = values();
        for (PartyMicrophoneInputLocation partyMicrophoneInputLocation : values) {
            sparseArray.put(partyMicrophoneInputLocation.getLocation().intValue(), partyMicrophoneInputLocation);
        }
        ENUM_MAP = sparseArray;
    }

    private PartyMicrophoneInputLocation(int i) {
        this.mLocation = Integer.valueOf(i);
    }

    public Integer getLocation() {
        return this.mLocation;
    }

    public static PartyMicrophoneInputLocation get(Integer num) {
        return ENUM_MAP.get(num.intValue());
    }
}
