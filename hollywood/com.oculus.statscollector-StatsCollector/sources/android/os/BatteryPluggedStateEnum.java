package android.os;

import com.google.protobuf.Internal;

public enum BatteryPluggedStateEnum implements Internal.EnumLite {
    BATTERY_PLUGGED_NONE(0),
    BATTERY_PLUGGED_AC(1),
    BATTERY_PLUGGED_USB(2),
    BATTERY_PLUGGED_WIRELESS(4);
    
    public static final int BATTERY_PLUGGED_AC_VALUE = 1;
    public static final int BATTERY_PLUGGED_NONE_VALUE = 0;
    public static final int BATTERY_PLUGGED_USB_VALUE = 2;
    public static final int BATTERY_PLUGGED_WIRELESS_VALUE = 4;
    private static final Internal.EnumLiteMap<BatteryPluggedStateEnum> internalValueMap = new Internal.EnumLiteMap<BatteryPluggedStateEnum>() {
        /* class android.os.BatteryPluggedStateEnum.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public BatteryPluggedStateEnum findValueByNumber(int number) {
            return BatteryPluggedStateEnum.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static BatteryPluggedStateEnum valueOf(int value2) {
        return forNumber(value2);
    }

    public static BatteryPluggedStateEnum forNumber(int value2) {
        if (value2 == 0) {
            return BATTERY_PLUGGED_NONE;
        }
        if (value2 == 1) {
            return BATTERY_PLUGGED_AC;
        }
        if (value2 == 2) {
            return BATTERY_PLUGGED_USB;
        }
        if (value2 != 4) {
            return null;
        }
        return BATTERY_PLUGGED_WIRELESS;
    }

    public static Internal.EnumLiteMap<BatteryPluggedStateEnum> internalGetValueMap() {
        return internalValueMap;
    }

    private BatteryPluggedStateEnum(int value2) {
        this.value = value2;
    }
}
