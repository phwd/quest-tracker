package android.server.location;

import com.google.protobuf.Internal;

public enum GpsSignalQualityEnum implements Internal.EnumLite {
    GPS_SIGNAL_QUALITY_UNKNOWN(-1),
    GPS_SIGNAL_QUALITY_POOR(0),
    GPS_SIGNAL_QUALITY_GOOD(1);
    
    public static final int GPS_SIGNAL_QUALITY_GOOD_VALUE = 1;
    public static final int GPS_SIGNAL_QUALITY_POOR_VALUE = 0;
    public static final int GPS_SIGNAL_QUALITY_UNKNOWN_VALUE = -1;
    private static final Internal.EnumLiteMap<GpsSignalQualityEnum> internalValueMap = new Internal.EnumLiteMap<GpsSignalQualityEnum>() {
        /* class android.server.location.GpsSignalQualityEnum.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public GpsSignalQualityEnum findValueByNumber(int number) {
            return GpsSignalQualityEnum.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static GpsSignalQualityEnum valueOf(int value2) {
        return forNumber(value2);
    }

    public static GpsSignalQualityEnum forNumber(int value2) {
        if (value2 == -1) {
            return GPS_SIGNAL_QUALITY_UNKNOWN;
        }
        if (value2 == 0) {
            return GPS_SIGNAL_QUALITY_POOR;
        }
        if (value2 != 1) {
            return null;
        }
        return GPS_SIGNAL_QUALITY_GOOD;
    }

    public static Internal.EnumLiteMap<GpsSignalQualityEnum> internalGetValueMap() {
        return internalValueMap;
    }

    private GpsSignalQualityEnum(int value2) {
        this.value = value2;
    }
}
