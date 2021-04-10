package android.telephony;

import com.google.protobuf.Internal;

public enum SimStateEnum implements Internal.EnumLite {
    SIM_STATE_UNKNOWN(0),
    SIM_STATE_ABSENT(1),
    SIM_STATE_PIN_REQUIRED(2),
    SIM_STATE_PUK_REQUIRED(3),
    SIM_STATE_NETWORK_LOCKED(4),
    SIM_STATE_READY(5),
    SIM_STATE_NOT_READY(6),
    SIM_STATE_PERM_DISABLED(7),
    SIM_STATE_CARD_IO_ERROR(8),
    SIM_STATE_CARD_RESTRICTED(9),
    SIM_STATE_LOADED(10),
    SIM_STATE_PRESENT(11);
    
    public static final int SIM_STATE_ABSENT_VALUE = 1;
    public static final int SIM_STATE_CARD_IO_ERROR_VALUE = 8;
    public static final int SIM_STATE_CARD_RESTRICTED_VALUE = 9;
    public static final int SIM_STATE_LOADED_VALUE = 10;
    public static final int SIM_STATE_NETWORK_LOCKED_VALUE = 4;
    public static final int SIM_STATE_NOT_READY_VALUE = 6;
    public static final int SIM_STATE_PERM_DISABLED_VALUE = 7;
    public static final int SIM_STATE_PIN_REQUIRED_VALUE = 2;
    public static final int SIM_STATE_PRESENT_VALUE = 11;
    public static final int SIM_STATE_PUK_REQUIRED_VALUE = 3;
    public static final int SIM_STATE_READY_VALUE = 5;
    public static final int SIM_STATE_UNKNOWN_VALUE = 0;
    private static final Internal.EnumLiteMap<SimStateEnum> internalValueMap = new Internal.EnumLiteMap<SimStateEnum>() {
        /* class android.telephony.SimStateEnum.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public SimStateEnum findValueByNumber(int number) {
            return SimStateEnum.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static SimStateEnum valueOf(int value2) {
        return forNumber(value2);
    }

    public static SimStateEnum forNumber(int value2) {
        switch (value2) {
            case 0:
                return SIM_STATE_UNKNOWN;
            case 1:
                return SIM_STATE_ABSENT;
            case 2:
                return SIM_STATE_PIN_REQUIRED;
            case 3:
                return SIM_STATE_PUK_REQUIRED;
            case 4:
                return SIM_STATE_NETWORK_LOCKED;
            case 5:
                return SIM_STATE_READY;
            case 6:
                return SIM_STATE_NOT_READY;
            case 7:
                return SIM_STATE_PERM_DISABLED;
            case 8:
                return SIM_STATE_CARD_IO_ERROR;
            case 9:
                return SIM_STATE_CARD_RESTRICTED;
            case 10:
                return SIM_STATE_LOADED;
            case 11:
                return SIM_STATE_PRESENT;
            default:
                return null;
        }
    }

    public static Internal.EnumLiteMap<SimStateEnum> internalGetValueMap() {
        return internalValueMap;
    }

    private SimStateEnum(int value2) {
        this.value = value2;
    }
}
