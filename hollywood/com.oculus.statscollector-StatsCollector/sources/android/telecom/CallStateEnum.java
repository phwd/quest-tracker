package android.telecom;

import com.google.protobuf.Internal;

public enum CallStateEnum implements Internal.EnumLite {
    NEW(0),
    CONNECTING(1),
    SELECT_PHONE_ACCOUNT(2),
    DIALING(3),
    RINGING(4),
    ACTIVE(5),
    ON_HOLD(6),
    DISCONNECTED(7),
    ABORTED(8),
    DISCONNECTING(9),
    PULLING(10);
    
    public static final int ABORTED_VALUE = 8;
    public static final int ACTIVE_VALUE = 5;
    public static final int CONNECTING_VALUE = 1;
    public static final int DIALING_VALUE = 3;
    public static final int DISCONNECTED_VALUE = 7;
    public static final int DISCONNECTING_VALUE = 9;
    public static final int NEW_VALUE = 0;
    public static final int ON_HOLD_VALUE = 6;
    public static final int PULLING_VALUE = 10;
    public static final int RINGING_VALUE = 4;
    public static final int SELECT_PHONE_ACCOUNT_VALUE = 2;
    private static final Internal.EnumLiteMap<CallStateEnum> internalValueMap = new Internal.EnumLiteMap<CallStateEnum>() {
        /* class android.telecom.CallStateEnum.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public CallStateEnum findValueByNumber(int number) {
            return CallStateEnum.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static CallStateEnum valueOf(int value2) {
        return forNumber(value2);
    }

    public static CallStateEnum forNumber(int value2) {
        switch (value2) {
            case 0:
                return NEW;
            case 1:
                return CONNECTING;
            case 2:
                return SELECT_PHONE_ACCOUNT;
            case 3:
                return DIALING;
            case 4:
                return RINGING;
            case 5:
                return ACTIVE;
            case 6:
                return ON_HOLD;
            case 7:
                return DISCONNECTED;
            case 8:
                return ABORTED;
            case 9:
                return DISCONNECTING;
            case 10:
                return PULLING;
            default:
                return null;
        }
    }

    public static Internal.EnumLiteMap<CallStateEnum> internalGetValueMap() {
        return internalValueMap;
    }

    private CallStateEnum(int value2) {
        this.value = value2;
    }
}
