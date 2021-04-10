package android.server.location;

import com.google.protobuf.Internal;

public enum GnssUserResponseType implements Internal.EnumLite {
    RESPONSE_ACCEPT(1),
    RESPONSE_DENY(2),
    RESPONSE_NORESP(3);
    
    public static final int RESPONSE_ACCEPT_VALUE = 1;
    public static final int RESPONSE_DENY_VALUE = 2;
    public static final int RESPONSE_NORESP_VALUE = 3;
    private static final Internal.EnumLiteMap<GnssUserResponseType> internalValueMap = new Internal.EnumLiteMap<GnssUserResponseType>() {
        /* class android.server.location.GnssUserResponseType.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public GnssUserResponseType findValueByNumber(int number) {
            return GnssUserResponseType.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static GnssUserResponseType valueOf(int value2) {
        return forNumber(value2);
    }

    public static GnssUserResponseType forNumber(int value2) {
        if (value2 == 1) {
            return RESPONSE_ACCEPT;
        }
        if (value2 == 2) {
            return RESPONSE_DENY;
        }
        if (value2 != 3) {
            return null;
        }
        return RESPONSE_NORESP;
    }

    public static Internal.EnumLiteMap<GnssUserResponseType> internalGetValueMap() {
        return internalValueMap;
    }

    private GnssUserResponseType(int value2) {
        this.value = value2;
    }
}
