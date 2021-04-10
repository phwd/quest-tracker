package android.bluetooth;

import com.google.protobuf.Internal;

public enum UnbondReasonEnum implements Internal.EnumLite {
    UNBOND_REASON_UNKNOWN(0),
    UNBOND_REASON_AUTH_FAILED(1),
    UNBOND_REASON_AUTH_REJECTED(2),
    UNBOND_REASON_AUTH_CANCELED(3),
    UNBOND_REASON_REMOTE_DEVICE_DOWN(4),
    UNBOND_REASON_DISCOVERY_IN_PROGRESS(5),
    UNBOND_REASON_AUTH_TIMEOUT(6),
    UNBOND_REASON_REPEATED_ATTEMPTS(7),
    UNBOND_REASON_REMOTE_AUTH_CANCELED(8),
    UNBOND_REASON_REMOVED(9);
    
    public static final int UNBOND_REASON_AUTH_CANCELED_VALUE = 3;
    public static final int UNBOND_REASON_AUTH_FAILED_VALUE = 1;
    public static final int UNBOND_REASON_AUTH_REJECTED_VALUE = 2;
    public static final int UNBOND_REASON_AUTH_TIMEOUT_VALUE = 6;
    public static final int UNBOND_REASON_DISCOVERY_IN_PROGRESS_VALUE = 5;
    public static final int UNBOND_REASON_REMOTE_AUTH_CANCELED_VALUE = 8;
    public static final int UNBOND_REASON_REMOTE_DEVICE_DOWN_VALUE = 4;
    public static final int UNBOND_REASON_REMOVED_VALUE = 9;
    public static final int UNBOND_REASON_REPEATED_ATTEMPTS_VALUE = 7;
    public static final int UNBOND_REASON_UNKNOWN_VALUE = 0;
    private static final Internal.EnumLiteMap<UnbondReasonEnum> internalValueMap = new Internal.EnumLiteMap<UnbondReasonEnum>() {
        /* class android.bluetooth.UnbondReasonEnum.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public UnbondReasonEnum findValueByNumber(int number) {
            return UnbondReasonEnum.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static UnbondReasonEnum valueOf(int value2) {
        return forNumber(value2);
    }

    public static UnbondReasonEnum forNumber(int value2) {
        switch (value2) {
            case 0:
                return UNBOND_REASON_UNKNOWN;
            case 1:
                return UNBOND_REASON_AUTH_FAILED;
            case 2:
                return UNBOND_REASON_AUTH_REJECTED;
            case 3:
                return UNBOND_REASON_AUTH_CANCELED;
            case 4:
                return UNBOND_REASON_REMOTE_DEVICE_DOWN;
            case 5:
                return UNBOND_REASON_DISCOVERY_IN_PROGRESS;
            case 6:
                return UNBOND_REASON_AUTH_TIMEOUT;
            case 7:
                return UNBOND_REASON_REPEATED_ATTEMPTS;
            case 8:
                return UNBOND_REASON_REMOTE_AUTH_CANCELED;
            case 9:
                return UNBOND_REASON_REMOVED;
            default:
                return null;
        }
    }

    public static Internal.EnumLiteMap<UnbondReasonEnum> internalGetValueMap() {
        return internalValueMap;
    }

    private UnbondReasonEnum(int value2) {
        this.value = value2;
    }
}
