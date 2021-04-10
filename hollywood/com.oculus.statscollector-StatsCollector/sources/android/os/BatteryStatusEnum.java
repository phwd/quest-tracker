package android.os;

import com.google.protobuf.Internal;

public enum BatteryStatusEnum implements Internal.EnumLite {
    BATTERY_STATUS_INVALID(0),
    BATTERY_STATUS_UNKNOWN(1),
    BATTERY_STATUS_CHARGING(2),
    BATTERY_STATUS_DISCHARGING(3),
    BATTERY_STATUS_NOT_CHARGING(4),
    BATTERY_STATUS_FULL(5);
    
    public static final int BATTERY_STATUS_CHARGING_VALUE = 2;
    public static final int BATTERY_STATUS_DISCHARGING_VALUE = 3;
    public static final int BATTERY_STATUS_FULL_VALUE = 5;
    public static final int BATTERY_STATUS_INVALID_VALUE = 0;
    public static final int BATTERY_STATUS_NOT_CHARGING_VALUE = 4;
    public static final int BATTERY_STATUS_UNKNOWN_VALUE = 1;
    private static final Internal.EnumLiteMap<BatteryStatusEnum> internalValueMap = new Internal.EnumLiteMap<BatteryStatusEnum>() {
        /* class android.os.BatteryStatusEnum.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public BatteryStatusEnum findValueByNumber(int number) {
            return BatteryStatusEnum.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static BatteryStatusEnum valueOf(int value2) {
        return forNumber(value2);
    }

    public static BatteryStatusEnum forNumber(int value2) {
        if (value2 == 0) {
            return BATTERY_STATUS_INVALID;
        }
        if (value2 == 1) {
            return BATTERY_STATUS_UNKNOWN;
        }
        if (value2 == 2) {
            return BATTERY_STATUS_CHARGING;
        }
        if (value2 == 3) {
            return BATTERY_STATUS_DISCHARGING;
        }
        if (value2 == 4) {
            return BATTERY_STATUS_NOT_CHARGING;
        }
        if (value2 != 5) {
            return null;
        }
        return BATTERY_STATUS_FULL;
    }

    public static Internal.EnumLiteMap<BatteryStatusEnum> internalGetValueMap() {
        return internalValueMap;
    }

    private BatteryStatusEnum(int value2) {
        this.value = value2;
    }
}
