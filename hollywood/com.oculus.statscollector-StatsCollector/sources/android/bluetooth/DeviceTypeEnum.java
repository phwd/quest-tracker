package android.bluetooth;

import com.google.protobuf.Internal;

public enum DeviceTypeEnum implements Internal.EnumLite {
    DEVICE_TYPE_UNKNOWN(0),
    DEVICE_TYPE_CLASSIC(1),
    DEVICE_TYPE_LE(2),
    DEVICE_TYPE_DUAL(3);
    
    public static final int DEVICE_TYPE_CLASSIC_VALUE = 1;
    public static final int DEVICE_TYPE_DUAL_VALUE = 3;
    public static final int DEVICE_TYPE_LE_VALUE = 2;
    public static final int DEVICE_TYPE_UNKNOWN_VALUE = 0;
    private static final Internal.EnumLiteMap<DeviceTypeEnum> internalValueMap = new Internal.EnumLiteMap<DeviceTypeEnum>() {
        /* class android.bluetooth.DeviceTypeEnum.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public DeviceTypeEnum findValueByNumber(int number) {
            return DeviceTypeEnum.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static DeviceTypeEnum valueOf(int value2) {
        return forNumber(value2);
    }

    public static DeviceTypeEnum forNumber(int value2) {
        if (value2 == 0) {
            return DEVICE_TYPE_UNKNOWN;
        }
        if (value2 == 1) {
            return DEVICE_TYPE_CLASSIC;
        }
        if (value2 == 2) {
            return DEVICE_TYPE_LE;
        }
        if (value2 != 3) {
            return null;
        }
        return DEVICE_TYPE_DUAL;
    }

    public static Internal.EnumLiteMap<DeviceTypeEnum> internalGetValueMap() {
        return internalValueMap;
    }

    private DeviceTypeEnum(int value2) {
        this.value = value2;
    }
}
