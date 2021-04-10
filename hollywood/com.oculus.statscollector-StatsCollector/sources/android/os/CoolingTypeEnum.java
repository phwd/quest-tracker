package android.os;

import com.google.protobuf.Internal;

public enum CoolingTypeEnum implements Internal.EnumLite {
    FAN(0),
    BATTERY(1),
    CPU(2),
    GPU(3),
    MODEM(4),
    NPU(5),
    COMPONENT(6);
    
    public static final int BATTERY_VALUE = 1;
    public static final int COMPONENT_VALUE = 6;
    public static final int CPU_VALUE = 2;
    public static final int FAN_VALUE = 0;
    public static final int GPU_VALUE = 3;
    public static final int MODEM_VALUE = 4;
    public static final int NPU_VALUE = 5;
    private static final Internal.EnumLiteMap<CoolingTypeEnum> internalValueMap = new Internal.EnumLiteMap<CoolingTypeEnum>() {
        /* class android.os.CoolingTypeEnum.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public CoolingTypeEnum findValueByNumber(int number) {
            return CoolingTypeEnum.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static CoolingTypeEnum valueOf(int value2) {
        return forNumber(value2);
    }

    public static CoolingTypeEnum forNumber(int value2) {
        switch (value2) {
            case 0:
                return FAN;
            case 1:
                return BATTERY;
            case 2:
                return CPU;
            case 3:
                return GPU;
            case 4:
                return MODEM;
            case 5:
                return NPU;
            case 6:
                return COMPONENT;
            default:
                return null;
        }
    }

    public static Internal.EnumLiteMap<CoolingTypeEnum> internalGetValueMap() {
        return internalValueMap;
    }

    private CoolingTypeEnum(int value2) {
        this.value = value2;
    }
}
