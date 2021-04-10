package android.os;

import com.google.protobuf.Internal;

public enum ThrottlingSeverityEnum implements Internal.EnumLite {
    NONE(0),
    LIGHT(1),
    MODERATE(2),
    SEVERE(3),
    CRITICAL(4),
    EMERGENCY(5),
    SHUTDOWN(6);
    
    public static final int CRITICAL_VALUE = 4;
    public static final int EMERGENCY_VALUE = 5;
    public static final int LIGHT_VALUE = 1;
    public static final int MODERATE_VALUE = 2;
    public static final int NONE_VALUE = 0;
    public static final int SEVERE_VALUE = 3;
    public static final int SHUTDOWN_VALUE = 6;
    private static final Internal.EnumLiteMap<ThrottlingSeverityEnum> internalValueMap = new Internal.EnumLiteMap<ThrottlingSeverityEnum>() {
        /* class android.os.ThrottlingSeverityEnum.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public ThrottlingSeverityEnum findValueByNumber(int number) {
            return ThrottlingSeverityEnum.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static ThrottlingSeverityEnum valueOf(int value2) {
        return forNumber(value2);
    }

    public static ThrottlingSeverityEnum forNumber(int value2) {
        switch (value2) {
            case 0:
                return NONE;
            case 1:
                return LIGHT;
            case 2:
                return MODERATE;
            case 3:
                return SEVERE;
            case 4:
                return CRITICAL;
            case 5:
                return EMERGENCY;
            case 6:
                return SHUTDOWN;
            default:
                return null;
        }
    }

    public static Internal.EnumLiteMap<ThrottlingSeverityEnum> internalGetValueMap() {
        return internalValueMap;
    }

    private ThrottlingSeverityEnum(int value2) {
        this.value = value2;
    }
}
