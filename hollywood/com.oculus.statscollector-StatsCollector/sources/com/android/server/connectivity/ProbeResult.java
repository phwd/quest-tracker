package com.android.server.connectivity;

import com.google.protobuf.Internal;

public enum ProbeResult implements Internal.EnumLite {
    UNKNOWN(0),
    VALID(1),
    INVALID(2),
    PORTAL(3),
    PARTIAL(4);
    
    public static final int INVALID_VALUE = 2;
    public static final int PARTIAL_VALUE = 4;
    public static final int PORTAL_VALUE = 3;
    public static final int UNKNOWN_VALUE = 0;
    public static final int VALID_VALUE = 1;
    private static final Internal.EnumLiteMap<ProbeResult> internalValueMap = new Internal.EnumLiteMap<ProbeResult>() {
        /* class com.android.server.connectivity.ProbeResult.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public ProbeResult findValueByNumber(int number) {
            return ProbeResult.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static ProbeResult valueOf(int value2) {
        return forNumber(value2);
    }

    public static ProbeResult forNumber(int value2) {
        if (value2 == 0) {
            return UNKNOWN;
        }
        if (value2 == 1) {
            return VALID;
        }
        if (value2 == 2) {
            return INVALID;
        }
        if (value2 == 3) {
            return PORTAL;
        }
        if (value2 != 4) {
            return null;
        }
        return PARTIAL;
    }

    public static Internal.EnumLiteMap<ProbeResult> internalGetValueMap() {
        return internalValueMap;
    }

    private ProbeResult(int value2) {
        this.value = value2;
    }
}
