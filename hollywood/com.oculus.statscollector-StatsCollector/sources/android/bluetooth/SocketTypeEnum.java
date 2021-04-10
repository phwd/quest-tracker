package android.bluetooth;

import com.google.protobuf.Internal;

public enum SocketTypeEnum implements Internal.EnumLite {
    SOCKET_TYPE_UNKNOWN(0),
    SOCKET_TYPE_RFCOMM(1),
    SOCKET_TYPE_SCO(2),
    SOCKET_TYPE_L2CAP_BREDR(3),
    SOCKET_TYPE_L2CAP_LE(4);
    
    public static final int SOCKET_TYPE_L2CAP_BREDR_VALUE = 3;
    public static final int SOCKET_TYPE_L2CAP_LE_VALUE = 4;
    public static final int SOCKET_TYPE_RFCOMM_VALUE = 1;
    public static final int SOCKET_TYPE_SCO_VALUE = 2;
    public static final int SOCKET_TYPE_UNKNOWN_VALUE = 0;
    private static final Internal.EnumLiteMap<SocketTypeEnum> internalValueMap = new Internal.EnumLiteMap<SocketTypeEnum>() {
        /* class android.bluetooth.SocketTypeEnum.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public SocketTypeEnum findValueByNumber(int number) {
            return SocketTypeEnum.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static SocketTypeEnum valueOf(int value2) {
        return forNumber(value2);
    }

    public static SocketTypeEnum forNumber(int value2) {
        if (value2 == 0) {
            return SOCKET_TYPE_UNKNOWN;
        }
        if (value2 == 1) {
            return SOCKET_TYPE_RFCOMM;
        }
        if (value2 == 2) {
            return SOCKET_TYPE_SCO;
        }
        if (value2 == 3) {
            return SOCKET_TYPE_L2CAP_BREDR;
        }
        if (value2 != 4) {
            return null;
        }
        return SOCKET_TYPE_L2CAP_LE;
    }

    public static Internal.EnumLiteMap<SocketTypeEnum> internalGetValueMap() {
        return internalValueMap;
    }

    private SocketTypeEnum(int value2) {
        this.value = value2;
    }
}
