package android.server.location;

import com.google.protobuf.Internal;

public enum GnssNiType implements Internal.EnumLite {
    VOICE(1),
    UMTS_SUPL(2),
    UMTS_CTRL_PLANE(3),
    EMERGENCY_SUPL(4);
    
    public static final int EMERGENCY_SUPL_VALUE = 4;
    public static final int UMTS_CTRL_PLANE_VALUE = 3;
    public static final int UMTS_SUPL_VALUE = 2;
    public static final int VOICE_VALUE = 1;
    private static final Internal.EnumLiteMap<GnssNiType> internalValueMap = new Internal.EnumLiteMap<GnssNiType>() {
        /* class android.server.location.GnssNiType.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public GnssNiType findValueByNumber(int number) {
            return GnssNiType.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static GnssNiType valueOf(int value2) {
        return forNumber(value2);
    }

    public static GnssNiType forNumber(int value2) {
        if (value2 == 1) {
            return VOICE;
        }
        if (value2 == 2) {
            return UMTS_SUPL;
        }
        if (value2 == 3) {
            return UMTS_CTRL_PLANE;
        }
        if (value2 != 4) {
            return null;
        }
        return EMERGENCY_SUPL;
    }

    public static Internal.EnumLiteMap<GnssNiType> internalGetValueMap() {
        return internalValueMap;
    }

    private GnssNiType(int value2) {
        this.value = value2;
    }
}
