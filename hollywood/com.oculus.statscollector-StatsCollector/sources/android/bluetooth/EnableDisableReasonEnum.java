package android.bluetooth;

import com.google.protobuf.Internal;

public enum EnableDisableReasonEnum implements Internal.EnumLite {
    ENABLE_DISABLE_REASON_UNSPECIFIED(0),
    ENABLE_DISABLE_REASON_APPLICATION_REQUEST(1),
    ENABLE_DISABLE_REASON_AIRPLANE_MODE(2),
    ENABLE_DISABLE_REASON_DISALLOWED(3),
    ENABLE_DISABLE_REASON_RESTARTED(4),
    ENABLE_DISABLE_REASON_START_ERROR(5),
    ENABLE_DISABLE_REASON_SYSTEM_BOOT(6),
    ENABLE_DISABLE_REASON_CRASH(7),
    ENABLE_DISABLE_REASON_USER_SWITCH(8),
    ENABLE_DISABLE_REASON_RESTORE_USER_SETTING(9),
    ENABLE_DISABLE_REASON_FACTORY_RESET(10);
    
    public static final int ENABLE_DISABLE_REASON_AIRPLANE_MODE_VALUE = 2;
    public static final int ENABLE_DISABLE_REASON_APPLICATION_REQUEST_VALUE = 1;
    public static final int ENABLE_DISABLE_REASON_CRASH_VALUE = 7;
    public static final int ENABLE_DISABLE_REASON_DISALLOWED_VALUE = 3;
    public static final int ENABLE_DISABLE_REASON_FACTORY_RESET_VALUE = 10;
    public static final int ENABLE_DISABLE_REASON_RESTARTED_VALUE = 4;
    public static final int ENABLE_DISABLE_REASON_RESTORE_USER_SETTING_VALUE = 9;
    public static final int ENABLE_DISABLE_REASON_START_ERROR_VALUE = 5;
    public static final int ENABLE_DISABLE_REASON_SYSTEM_BOOT_VALUE = 6;
    public static final int ENABLE_DISABLE_REASON_UNSPECIFIED_VALUE = 0;
    public static final int ENABLE_DISABLE_REASON_USER_SWITCH_VALUE = 8;
    private static final Internal.EnumLiteMap<EnableDisableReasonEnum> internalValueMap = new Internal.EnumLiteMap<EnableDisableReasonEnum>() {
        /* class android.bluetooth.EnableDisableReasonEnum.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public EnableDisableReasonEnum findValueByNumber(int number) {
            return EnableDisableReasonEnum.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static EnableDisableReasonEnum valueOf(int value2) {
        return forNumber(value2);
    }

    public static EnableDisableReasonEnum forNumber(int value2) {
        switch (value2) {
            case 0:
                return ENABLE_DISABLE_REASON_UNSPECIFIED;
            case 1:
                return ENABLE_DISABLE_REASON_APPLICATION_REQUEST;
            case 2:
                return ENABLE_DISABLE_REASON_AIRPLANE_MODE;
            case 3:
                return ENABLE_DISABLE_REASON_DISALLOWED;
            case 4:
                return ENABLE_DISABLE_REASON_RESTARTED;
            case 5:
                return ENABLE_DISABLE_REASON_START_ERROR;
            case 6:
                return ENABLE_DISABLE_REASON_SYSTEM_BOOT;
            case 7:
                return ENABLE_DISABLE_REASON_CRASH;
            case 8:
                return ENABLE_DISABLE_REASON_USER_SWITCH;
            case 9:
                return ENABLE_DISABLE_REASON_RESTORE_USER_SETTING;
            case 10:
                return ENABLE_DISABLE_REASON_FACTORY_RESET;
            default:
                return null;
        }
    }

    public static Internal.EnumLiteMap<EnableDisableReasonEnum> internalGetValueMap() {
        return internalValueMap;
    }

    private EnableDisableReasonEnum(int value2) {
        this.value = value2;
    }
}
