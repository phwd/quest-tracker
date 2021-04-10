package android.server.location;

import com.google.protobuf.Internal;

public enum GnssNiEncodingType implements Internal.EnumLite {
    ENC_NONE(0),
    ENC_SUPL_GSM_DEFAULT(1),
    ENC_SUPL_UTF8(2),
    ENC_SUPL_UCS2(3),
    ENC_UNKNOWN(-1);
    
    public static final int ENC_NONE_VALUE = 0;
    public static final int ENC_SUPL_GSM_DEFAULT_VALUE = 1;
    public static final int ENC_SUPL_UCS2_VALUE = 3;
    public static final int ENC_SUPL_UTF8_VALUE = 2;
    public static final int ENC_UNKNOWN_VALUE = -1;
    private static final Internal.EnumLiteMap<GnssNiEncodingType> internalValueMap = new Internal.EnumLiteMap<GnssNiEncodingType>() {
        /* class android.server.location.GnssNiEncodingType.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public GnssNiEncodingType findValueByNumber(int number) {
            return GnssNiEncodingType.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static GnssNiEncodingType valueOf(int value2) {
        return forNumber(value2);
    }

    public static GnssNiEncodingType forNumber(int value2) {
        if (value2 == -1) {
            return ENC_UNKNOWN;
        }
        if (value2 == 0) {
            return ENC_NONE;
        }
        if (value2 == 1) {
            return ENC_SUPL_GSM_DEFAULT;
        }
        if (value2 == 2) {
            return ENC_SUPL_UTF8;
        }
        if (value2 != 3) {
            return null;
        }
        return ENC_SUPL_UCS2;
    }

    public static Internal.EnumLiteMap<GnssNiEncodingType> internalGetValueMap() {
        return internalValueMap;
    }

    private GnssNiEncodingType(int value2) {
        this.value = value2;
    }
}
