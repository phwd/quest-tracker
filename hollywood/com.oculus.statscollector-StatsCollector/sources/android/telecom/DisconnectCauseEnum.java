package android.telecom;

import com.google.protobuf.Internal;

public enum DisconnectCauseEnum implements Internal.EnumLite {
    UNKNOWN(0),
    ERROR(1),
    LOCAL(2),
    REMOTE(3),
    CANCELED(4),
    MISSED(5),
    REJECTED(6),
    BUSY(7),
    RESTRICTED(8),
    OTHER(9),
    CONNECTION_MANAGER_NOT_SUPPORTED(10),
    ANSWERED_ELSEWHERE(11),
    CALL_PULLED(12);
    
    public static final int ANSWERED_ELSEWHERE_VALUE = 11;
    public static final int BUSY_VALUE = 7;
    public static final int CALL_PULLED_VALUE = 12;
    public static final int CANCELED_VALUE = 4;
    public static final int CONNECTION_MANAGER_NOT_SUPPORTED_VALUE = 10;
    public static final int ERROR_VALUE = 1;
    public static final int LOCAL_VALUE = 2;
    public static final int MISSED_VALUE = 5;
    public static final int OTHER_VALUE = 9;
    public static final int REJECTED_VALUE = 6;
    public static final int REMOTE_VALUE = 3;
    public static final int RESTRICTED_VALUE = 8;
    public static final int UNKNOWN_VALUE = 0;
    private static final Internal.EnumLiteMap<DisconnectCauseEnum> internalValueMap = new Internal.EnumLiteMap<DisconnectCauseEnum>() {
        /* class android.telecom.DisconnectCauseEnum.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public DisconnectCauseEnum findValueByNumber(int number) {
            return DisconnectCauseEnum.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static DisconnectCauseEnum valueOf(int value2) {
        return forNumber(value2);
    }

    public static DisconnectCauseEnum forNumber(int value2) {
        switch (value2) {
            case 0:
                return UNKNOWN;
            case 1:
                return ERROR;
            case 2:
                return LOCAL;
            case 3:
                return REMOTE;
            case 4:
                return CANCELED;
            case 5:
                return MISSED;
            case 6:
                return REJECTED;
            case 7:
                return BUSY;
            case 8:
                return RESTRICTED;
            case 9:
                return OTHER;
            case 10:
                return CONNECTION_MANAGER_NOT_SUPPORTED;
            case 11:
                return ANSWERED_ELSEWHERE;
            case 12:
                return CALL_PULLED;
            default:
                return null;
        }
    }

    public static Internal.EnumLiteMap<DisconnectCauseEnum> internalGetValueMap() {
        return internalValueMap;
    }

    private DisconnectCauseEnum(int value2) {
        this.value = value2;
    }
}
