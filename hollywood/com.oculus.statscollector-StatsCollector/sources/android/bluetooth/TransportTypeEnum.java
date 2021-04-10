package android.bluetooth;

import com.google.protobuf.Internal;

public enum TransportTypeEnum implements Internal.EnumLite {
    TRANSPORT_TYPE_AUTO(0),
    TRANSPORT_TYPE_BREDR(1),
    TRANSPORT_TYPE_LE(2);
    
    public static final int TRANSPORT_TYPE_AUTO_VALUE = 0;
    public static final int TRANSPORT_TYPE_BREDR_VALUE = 1;
    public static final int TRANSPORT_TYPE_LE_VALUE = 2;
    private static final Internal.EnumLiteMap<TransportTypeEnum> internalValueMap = new Internal.EnumLiteMap<TransportTypeEnum>() {
        /* class android.bluetooth.TransportTypeEnum.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public TransportTypeEnum findValueByNumber(int number) {
            return TransportTypeEnum.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static TransportTypeEnum valueOf(int value2) {
        return forNumber(value2);
    }

    public static TransportTypeEnum forNumber(int value2) {
        if (value2 == 0) {
            return TRANSPORT_TYPE_AUTO;
        }
        if (value2 == 1) {
            return TRANSPORT_TYPE_BREDR;
        }
        if (value2 != 2) {
            return null;
        }
        return TRANSPORT_TYPE_LE;
    }

    public static Internal.EnumLiteMap<TransportTypeEnum> internalGetValueMap() {
        return internalValueMap;
    }

    private TransportTypeEnum(int value2) {
        this.value = value2;
    }
}
