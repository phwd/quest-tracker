package android.debug;

import com.google.protobuf.Internal;

public enum AdbConnectionStateEnum implements Internal.EnumLite {
    UNKNOWN(0),
    AWAITING_USER_APPROVAL(1),
    USER_ALLOWED(2),
    USER_DENIED(3),
    AUTOMATICALLY_ALLOWED(4),
    DENIED_INVALID_KEY(5),
    DENIED_VOLD_DECRYPT(6),
    DISCONNECTED(7);
    
    public static final int AUTOMATICALLY_ALLOWED_VALUE = 4;
    public static final int AWAITING_USER_APPROVAL_VALUE = 1;
    public static final int DENIED_INVALID_KEY_VALUE = 5;
    public static final int DENIED_VOLD_DECRYPT_VALUE = 6;
    public static final int DISCONNECTED_VALUE = 7;
    public static final int UNKNOWN_VALUE = 0;
    public static final int USER_ALLOWED_VALUE = 2;
    public static final int USER_DENIED_VALUE = 3;
    private static final Internal.EnumLiteMap<AdbConnectionStateEnum> internalValueMap = new Internal.EnumLiteMap<AdbConnectionStateEnum>() {
        /* class android.debug.AdbConnectionStateEnum.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public AdbConnectionStateEnum findValueByNumber(int number) {
            return AdbConnectionStateEnum.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static AdbConnectionStateEnum valueOf(int value2) {
        return forNumber(value2);
    }

    public static AdbConnectionStateEnum forNumber(int value2) {
        switch (value2) {
            case 0:
                return UNKNOWN;
            case 1:
                return AWAITING_USER_APPROVAL;
            case 2:
                return USER_ALLOWED;
            case 3:
                return USER_DENIED;
            case 4:
                return AUTOMATICALLY_ALLOWED;
            case 5:
                return DENIED_INVALID_KEY;
            case 6:
                return DENIED_VOLD_DECRYPT;
            case 7:
                return DISCONNECTED;
            default:
                return null;
        }
    }

    public static Internal.EnumLiteMap<AdbConnectionStateEnum> internalGetValueMap() {
        return internalValueMap;
    }

    private AdbConnectionStateEnum(int value2) {
        this.value = value2;
    }
}
