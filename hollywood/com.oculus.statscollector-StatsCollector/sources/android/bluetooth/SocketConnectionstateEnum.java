package android.bluetooth;

import com.google.protobuf.Internal;

public enum SocketConnectionstateEnum implements Internal.EnumLite {
    SOCKET_CONNECTION_STATE_UNKNOWN(0),
    SOCKET_CONNECTION_STATE_LISTENING(1),
    SOCKET_CONNECTION_STATE_CONNECTING(2),
    SOCKET_CONNECTION_STATE_CONNECTED(3),
    SOCKET_CONNECTION_STATE_DISCONNECTING(4),
    SOCKET_CONNECTION_STATE_DISCONNECTED(5);
    
    public static final int SOCKET_CONNECTION_STATE_CONNECTED_VALUE = 3;
    public static final int SOCKET_CONNECTION_STATE_CONNECTING_VALUE = 2;
    public static final int SOCKET_CONNECTION_STATE_DISCONNECTED_VALUE = 5;
    public static final int SOCKET_CONNECTION_STATE_DISCONNECTING_VALUE = 4;
    public static final int SOCKET_CONNECTION_STATE_LISTENING_VALUE = 1;
    public static final int SOCKET_CONNECTION_STATE_UNKNOWN_VALUE = 0;
    private static final Internal.EnumLiteMap<SocketConnectionstateEnum> internalValueMap = new Internal.EnumLiteMap<SocketConnectionstateEnum>() {
        /* class android.bluetooth.SocketConnectionstateEnum.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public SocketConnectionstateEnum findValueByNumber(int number) {
            return SocketConnectionstateEnum.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static SocketConnectionstateEnum valueOf(int value2) {
        return forNumber(value2);
    }

    public static SocketConnectionstateEnum forNumber(int value2) {
        if (value2 == 0) {
            return SOCKET_CONNECTION_STATE_UNKNOWN;
        }
        if (value2 == 1) {
            return SOCKET_CONNECTION_STATE_LISTENING;
        }
        if (value2 == 2) {
            return SOCKET_CONNECTION_STATE_CONNECTING;
        }
        if (value2 == 3) {
            return SOCKET_CONNECTION_STATE_CONNECTED;
        }
        if (value2 == 4) {
            return SOCKET_CONNECTION_STATE_DISCONNECTING;
        }
        if (value2 != 5) {
            return null;
        }
        return SOCKET_CONNECTION_STATE_DISCONNECTED;
    }

    public static Internal.EnumLiteMap<SocketConnectionstateEnum> internalGetValueMap() {
        return internalValueMap;
    }

    private SocketConnectionstateEnum(int value2) {
        this.value = value2;
    }
}
