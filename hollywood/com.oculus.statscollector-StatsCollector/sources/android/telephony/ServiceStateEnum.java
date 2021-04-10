package android.telephony;

import com.google.protobuf.Internal;

public enum ServiceStateEnum implements Internal.EnumLite {
    SERVICE_STATE_IN_SERVICE(0),
    SERVICE_STATE_OUT_OF_SERVICE(1),
    SERVICE_STATE_EMERGENCY_ONLY(2),
    SERVICE_STATE_POWER_OFF(3);
    
    public static final int SERVICE_STATE_EMERGENCY_ONLY_VALUE = 2;
    public static final int SERVICE_STATE_IN_SERVICE_VALUE = 0;
    public static final int SERVICE_STATE_OUT_OF_SERVICE_VALUE = 1;
    public static final int SERVICE_STATE_POWER_OFF_VALUE = 3;
    private static final Internal.EnumLiteMap<ServiceStateEnum> internalValueMap = new Internal.EnumLiteMap<ServiceStateEnum>() {
        /* class android.telephony.ServiceStateEnum.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public ServiceStateEnum findValueByNumber(int number) {
            return ServiceStateEnum.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static ServiceStateEnum valueOf(int value2) {
        return forNumber(value2);
    }

    public static ServiceStateEnum forNumber(int value2) {
        if (value2 == 0) {
            return SERVICE_STATE_IN_SERVICE;
        }
        if (value2 == 1) {
            return SERVICE_STATE_OUT_OF_SERVICE;
        }
        if (value2 == 2) {
            return SERVICE_STATE_EMERGENCY_ONLY;
        }
        if (value2 != 3) {
            return null;
        }
        return SERVICE_STATE_POWER_OFF;
    }

    public static Internal.EnumLiteMap<ServiceStateEnum> internalGetValueMap() {
        return internalValueMap;
    }

    private ServiceStateEnum(int value2) {
        this.value = value2;
    }
}
