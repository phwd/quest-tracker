package com.android.server.connectivity;

import com.google.protobuf.Internal;

public enum ApBand implements Internal.EnumLite {
    AP_BAND_UNKNOWN(0),
    AP_BAND_2GHZ(1),
    AP_BAND_5GHZ(2);
    
    public static final int AP_BAND_2GHZ_VALUE = 1;
    public static final int AP_BAND_5GHZ_VALUE = 2;
    public static final int AP_BAND_UNKNOWN_VALUE = 0;
    private static final Internal.EnumLiteMap<ApBand> internalValueMap = new Internal.EnumLiteMap<ApBand>() {
        /* class com.android.server.connectivity.ApBand.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public ApBand findValueByNumber(int number) {
            return ApBand.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static ApBand valueOf(int value2) {
        return forNumber(value2);
    }

    public static ApBand forNumber(int value2) {
        if (value2 == 0) {
            return AP_BAND_UNKNOWN;
        }
        if (value2 == 1) {
            return AP_BAND_2GHZ;
        }
        if (value2 != 2) {
            return null;
        }
        return AP_BAND_5GHZ;
    }

    public static Internal.EnumLiteMap<ApBand> internalGetValueMap() {
        return internalValueMap;
    }

    private ApBand(int value2) {
        this.value = value2;
    }
}
