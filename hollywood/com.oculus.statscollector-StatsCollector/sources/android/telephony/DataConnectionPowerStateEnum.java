package android.telephony;

import com.google.protobuf.Internal;

public enum DataConnectionPowerStateEnum implements Internal.EnumLite {
    DATA_CONNECTION_POWER_STATE_LOW(1),
    DATA_CONNECTION_POWER_STATE_MEDIUM(2),
    DATA_CONNECTION_POWER_STATE_HIGH(3),
    DATA_CONNECTION_POWER_STATE_UNKNOWN(DATA_CONNECTION_POWER_STATE_UNKNOWN_VALUE);
    
    public static final int DATA_CONNECTION_POWER_STATE_HIGH_VALUE = 3;
    public static final int DATA_CONNECTION_POWER_STATE_LOW_VALUE = 1;
    public static final int DATA_CONNECTION_POWER_STATE_MEDIUM_VALUE = 2;
    public static final int DATA_CONNECTION_POWER_STATE_UNKNOWN_VALUE = Integer.MAX_VALUE;
    private static final Internal.EnumLiteMap<DataConnectionPowerStateEnum> internalValueMap = new Internal.EnumLiteMap<DataConnectionPowerStateEnum>() {
        /* class android.telephony.DataConnectionPowerStateEnum.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public DataConnectionPowerStateEnum findValueByNumber(int number) {
            return DataConnectionPowerStateEnum.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static DataConnectionPowerStateEnum valueOf(int value2) {
        return forNumber(value2);
    }

    public static DataConnectionPowerStateEnum forNumber(int value2) {
        if (value2 == 1) {
            return DATA_CONNECTION_POWER_STATE_LOW;
        }
        if (value2 == 2) {
            return DATA_CONNECTION_POWER_STATE_MEDIUM;
        }
        if (value2 == 3) {
            return DATA_CONNECTION_POWER_STATE_HIGH;
        }
        if (value2 != Integer.MAX_VALUE) {
            return null;
        }
        return DATA_CONNECTION_POWER_STATE_UNKNOWN;
    }

    public static Internal.EnumLiteMap<DataConnectionPowerStateEnum> internalGetValueMap() {
        return internalValueMap;
    }

    private DataConnectionPowerStateEnum(int value2) {
        this.value = value2;
    }
}
