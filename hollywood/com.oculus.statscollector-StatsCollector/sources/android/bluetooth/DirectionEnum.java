package android.bluetooth;

import com.google.protobuf.Internal;

public enum DirectionEnum implements Internal.EnumLite {
    DIRECTION_UNKNOWN(0),
    DIRECTION_OUTGOING(1),
    DIRECTION_INCOMING(2);
    
    public static final int DIRECTION_INCOMING_VALUE = 2;
    public static final int DIRECTION_OUTGOING_VALUE = 1;
    public static final int DIRECTION_UNKNOWN_VALUE = 0;
    private static final Internal.EnumLiteMap<DirectionEnum> internalValueMap = new Internal.EnumLiteMap<DirectionEnum>() {
        /* class android.bluetooth.DirectionEnum.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public DirectionEnum findValueByNumber(int number) {
            return DirectionEnum.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static DirectionEnum valueOf(int value2) {
        return forNumber(value2);
    }

    public static DirectionEnum forNumber(int value2) {
        if (value2 == 0) {
            return DIRECTION_UNKNOWN;
        }
        if (value2 == 1) {
            return DIRECTION_OUTGOING;
        }
        if (value2 != 2) {
            return null;
        }
        return DIRECTION_INCOMING;
    }

    public static Internal.EnumLiteMap<DirectionEnum> internalGetValueMap() {
        return internalValueMap;
    }

    private DirectionEnum(int value2) {
        this.value = value2;
    }
}
