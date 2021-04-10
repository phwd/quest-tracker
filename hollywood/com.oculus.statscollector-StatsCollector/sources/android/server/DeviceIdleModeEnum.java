package android.server;

import com.google.protobuf.Internal;

public enum DeviceIdleModeEnum implements Internal.EnumLite {
    DEVICE_IDLE_MODE_OFF(0),
    DEVICE_IDLE_MODE_LIGHT(1),
    DEVICE_IDLE_MODE_DEEP(2);
    
    public static final int DEVICE_IDLE_MODE_DEEP_VALUE = 2;
    public static final int DEVICE_IDLE_MODE_LIGHT_VALUE = 1;
    public static final int DEVICE_IDLE_MODE_OFF_VALUE = 0;
    private static final Internal.EnumLiteMap<DeviceIdleModeEnum> internalValueMap = new Internal.EnumLiteMap<DeviceIdleModeEnum>() {
        /* class android.server.DeviceIdleModeEnum.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public DeviceIdleModeEnum findValueByNumber(int number) {
            return DeviceIdleModeEnum.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static DeviceIdleModeEnum valueOf(int value2) {
        return forNumber(value2);
    }

    public static DeviceIdleModeEnum forNumber(int value2) {
        if (value2 == 0) {
            return DEVICE_IDLE_MODE_OFF;
        }
        if (value2 == 1) {
            return DEVICE_IDLE_MODE_LIGHT;
        }
        if (value2 != 2) {
            return null;
        }
        return DEVICE_IDLE_MODE_DEEP;
    }

    public static Internal.EnumLiteMap<DeviceIdleModeEnum> internalGetValueMap() {
        return internalValueMap;
    }

    private DeviceIdleModeEnum(int value2) {
        this.value = value2;
    }
}
