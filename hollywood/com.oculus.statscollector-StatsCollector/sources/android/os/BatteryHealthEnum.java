package android.os;

import com.google.protobuf.Internal;

public enum BatteryHealthEnum implements Internal.EnumLite {
    BATTERY_HEALTH_INVALID(0),
    BATTERY_HEALTH_UNKNOWN(1),
    BATTERY_HEALTH_GOOD(2),
    BATTERY_HEALTH_OVERHEAT(3),
    BATTERY_HEALTH_DEAD(4),
    BATTERY_HEALTH_OVER_VOLTAGE(5),
    BATTERY_HEALTH_UNSPECIFIED_FAILURE(6),
    BATTERY_HEALTH_COLD(7);
    
    public static final int BATTERY_HEALTH_COLD_VALUE = 7;
    public static final int BATTERY_HEALTH_DEAD_VALUE = 4;
    public static final int BATTERY_HEALTH_GOOD_VALUE = 2;
    public static final int BATTERY_HEALTH_INVALID_VALUE = 0;
    public static final int BATTERY_HEALTH_OVERHEAT_VALUE = 3;
    public static final int BATTERY_HEALTH_OVER_VOLTAGE_VALUE = 5;
    public static final int BATTERY_HEALTH_UNKNOWN_VALUE = 1;
    public static final int BATTERY_HEALTH_UNSPECIFIED_FAILURE_VALUE = 6;
    private static final Internal.EnumLiteMap<BatteryHealthEnum> internalValueMap = new Internal.EnumLiteMap<BatteryHealthEnum>() {
        /* class android.os.BatteryHealthEnum.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public BatteryHealthEnum findValueByNumber(int number) {
            return BatteryHealthEnum.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static BatteryHealthEnum valueOf(int value2) {
        return forNumber(value2);
    }

    public static BatteryHealthEnum forNumber(int value2) {
        switch (value2) {
            case 0:
                return BATTERY_HEALTH_INVALID;
            case 1:
                return BATTERY_HEALTH_UNKNOWN;
            case 2:
                return BATTERY_HEALTH_GOOD;
            case 3:
                return BATTERY_HEALTH_OVERHEAT;
            case 4:
                return BATTERY_HEALTH_DEAD;
            case 5:
                return BATTERY_HEALTH_OVER_VOLTAGE;
            case 6:
                return BATTERY_HEALTH_UNSPECIFIED_FAILURE;
            case 7:
                return BATTERY_HEALTH_COLD;
            default:
                return null;
        }
    }

    public static Internal.EnumLiteMap<BatteryHealthEnum> internalGetValueMap() {
        return internalValueMap;
    }

    private BatteryHealthEnum(int value2) {
        this.value = value2;
    }
}
