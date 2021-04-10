package android.bluetooth;

import com.google.protobuf.Internal;

public enum SocketRoleEnum implements Internal.EnumLite {
    SOCKET_ROLE_UNKNOWN(0),
    SOCKET_ROLE_LISTEN(1),
    SOCKET_ROLE_CONNECTION(2);
    
    public static final int SOCKET_ROLE_CONNECTION_VALUE = 2;
    public static final int SOCKET_ROLE_LISTEN_VALUE = 1;
    public static final int SOCKET_ROLE_UNKNOWN_VALUE = 0;
    private static final Internal.EnumLiteMap<SocketRoleEnum> internalValueMap = new Internal.EnumLiteMap<SocketRoleEnum>() {
        /* class android.bluetooth.SocketRoleEnum.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public SocketRoleEnum findValueByNumber(int number) {
            return SocketRoleEnum.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static SocketRoleEnum valueOf(int value2) {
        return forNumber(value2);
    }

    public static SocketRoleEnum forNumber(int value2) {
        if (value2 == 0) {
            return SOCKET_ROLE_UNKNOWN;
        }
        if (value2 == 1) {
            return SOCKET_ROLE_LISTEN;
        }
        if (value2 != 2) {
            return null;
        }
        return SOCKET_ROLE_CONNECTION;
    }

    public static Internal.EnumLiteMap<SocketRoleEnum> internalGetValueMap() {
        return internalValueMap;
    }

    private SocketRoleEnum(int value2) {
        this.value = value2;
    }
}
