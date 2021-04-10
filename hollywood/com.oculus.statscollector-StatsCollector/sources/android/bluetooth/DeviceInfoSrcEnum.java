package android.bluetooth;

import com.google.protobuf.Internal;

public enum DeviceInfoSrcEnum implements Internal.EnumLite {
    DEVICE_INFO_SRC_UNKNOWN(0),
    DEVICE_INFO_INTERNAL(1),
    DEVICE_INFO_EXTERNAL(2);
    
    public static final int DEVICE_INFO_EXTERNAL_VALUE = 2;
    public static final int DEVICE_INFO_INTERNAL_VALUE = 1;
    public static final int DEVICE_INFO_SRC_UNKNOWN_VALUE = 0;
    private static final Internal.EnumLiteMap<DeviceInfoSrcEnum> internalValueMap = new Internal.EnumLiteMap<DeviceInfoSrcEnum>() {
        /* class android.bluetooth.DeviceInfoSrcEnum.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public DeviceInfoSrcEnum findValueByNumber(int number) {
            return DeviceInfoSrcEnum.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static DeviceInfoSrcEnum valueOf(int value2) {
        return forNumber(value2);
    }

    public static DeviceInfoSrcEnum forNumber(int value2) {
        if (value2 == 0) {
            return DEVICE_INFO_SRC_UNKNOWN;
        }
        if (value2 == 1) {
            return DEVICE_INFO_INTERNAL;
        }
        if (value2 != 2) {
            return null;
        }
        return DEVICE_INFO_EXTERNAL;
    }

    public static Internal.EnumLiteMap<DeviceInfoSrcEnum> internalGetValueMap() {
        return internalValueMap;
    }

    private DeviceInfoSrcEnum(int value2) {
        this.value = value2;
    }
}
