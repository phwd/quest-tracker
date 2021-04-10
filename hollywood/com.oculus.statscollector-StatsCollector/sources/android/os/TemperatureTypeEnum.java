package android.os;

import com.google.protobuf.Internal;

public enum TemperatureTypeEnum implements Internal.EnumLite {
    TEMPERATURE_TYPE_UNKNOWN(-1),
    TEMPERATURE_TYPE_CPU(0),
    TEMPERATURE_TYPE_GPU(1),
    TEMPERATURE_TYPE_BATTERY(2),
    TEMPERATURE_TYPE_SKIN(3),
    TEMPERATURE_TYPE_USB_PORT(4),
    TEMPERATURE_TYPE_POWER_AMPLIFIER(5),
    TEMPERATURE_TYPE_BCL_VOLTAGE(6),
    TEMPERATURE_TYPE_BCL_CURRENT(7),
    TEMPERATURE_TYPE_BCL_PERCENTAGE(8),
    TEMPERATURE_TYPE_NPU(9);
    
    public static final int TEMPERATURE_TYPE_BATTERY_VALUE = 2;
    public static final int TEMPERATURE_TYPE_BCL_CURRENT_VALUE = 7;
    public static final int TEMPERATURE_TYPE_BCL_PERCENTAGE_VALUE = 8;
    public static final int TEMPERATURE_TYPE_BCL_VOLTAGE_VALUE = 6;
    public static final int TEMPERATURE_TYPE_CPU_VALUE = 0;
    public static final int TEMPERATURE_TYPE_GPU_VALUE = 1;
    public static final int TEMPERATURE_TYPE_NPU_VALUE = 9;
    public static final int TEMPERATURE_TYPE_POWER_AMPLIFIER_VALUE = 5;
    public static final int TEMPERATURE_TYPE_SKIN_VALUE = 3;
    public static final int TEMPERATURE_TYPE_UNKNOWN_VALUE = -1;
    public static final int TEMPERATURE_TYPE_USB_PORT_VALUE = 4;
    private static final Internal.EnumLiteMap<TemperatureTypeEnum> internalValueMap = new Internal.EnumLiteMap<TemperatureTypeEnum>() {
        /* class android.os.TemperatureTypeEnum.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public TemperatureTypeEnum findValueByNumber(int number) {
            return TemperatureTypeEnum.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static TemperatureTypeEnum valueOf(int value2) {
        return forNumber(value2);
    }

    public static TemperatureTypeEnum forNumber(int value2) {
        switch (value2) {
            case -1:
                return TEMPERATURE_TYPE_UNKNOWN;
            case 0:
                return TEMPERATURE_TYPE_CPU;
            case 1:
                return TEMPERATURE_TYPE_GPU;
            case 2:
                return TEMPERATURE_TYPE_BATTERY;
            case 3:
                return TEMPERATURE_TYPE_SKIN;
            case 4:
                return TEMPERATURE_TYPE_USB_PORT;
            case 5:
                return TEMPERATURE_TYPE_POWER_AMPLIFIER;
            case 6:
                return TEMPERATURE_TYPE_BCL_VOLTAGE;
            case 7:
                return TEMPERATURE_TYPE_BCL_CURRENT;
            case 8:
                return TEMPERATURE_TYPE_BCL_PERCENTAGE;
            case 9:
                return TEMPERATURE_TYPE_NPU;
            default:
                return null;
        }
    }

    public static Internal.EnumLiteMap<TemperatureTypeEnum> internalGetValueMap() {
        return internalValueMap;
    }

    private TemperatureTypeEnum(int value2) {
        this.value = value2;
    }
}
