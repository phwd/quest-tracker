package android.bluetooth;

import com.google.protobuf.Internal;

public enum ConnectionStateEnum implements Internal.EnumLite {
    CONNECTION_STATE_DISCONNECTED(0),
    CONNECTION_STATE_CONNECTING(1),
    CONNECTION_STATE_CONNECTED(2),
    CONNECTION_STATE_DISCONNECTING(3);
    
    public static final int CONNECTION_STATE_CONNECTED_VALUE = 2;
    public static final int CONNECTION_STATE_CONNECTING_VALUE = 1;
    public static final int CONNECTION_STATE_DISCONNECTED_VALUE = 0;
    public static final int CONNECTION_STATE_DISCONNECTING_VALUE = 3;
    private static final Internal.EnumLiteMap<ConnectionStateEnum> internalValueMap = new Internal.EnumLiteMap<ConnectionStateEnum>() {
        /* class android.bluetooth.ConnectionStateEnum.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public ConnectionStateEnum findValueByNumber(int number) {
            return ConnectionStateEnum.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static ConnectionStateEnum valueOf(int value2) {
        return forNumber(value2);
    }

    public static ConnectionStateEnum forNumber(int value2) {
        if (value2 == 0) {
            return CONNECTION_STATE_DISCONNECTED;
        }
        if (value2 == 1) {
            return CONNECTION_STATE_CONNECTING;
        }
        if (value2 == 2) {
            return CONNECTION_STATE_CONNECTED;
        }
        if (value2 != 3) {
            return null;
        }
        return CONNECTION_STATE_DISCONNECTING;
    }

    public static Internal.EnumLiteMap<ConnectionStateEnum> internalGetValueMap() {
        return internalValueMap;
    }

    private ConnectionStateEnum(int value2) {
        this.value = value2;
    }
}
