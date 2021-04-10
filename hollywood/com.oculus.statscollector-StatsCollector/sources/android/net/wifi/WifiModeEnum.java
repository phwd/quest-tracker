package android.net.wifi;

import com.google.protobuf.Internal;

public enum WifiModeEnum implements Internal.EnumLite {
    WIFI_MODE_FULL(1),
    WIFI_MODE_SCAN_ONLY(2),
    WIFI_MODE_FULL_HIGH_PERF(3),
    WIFI_MODE_FULL_LOW_LATENCY(4);
    
    public static final int WIFI_MODE_FULL_HIGH_PERF_VALUE = 3;
    public static final int WIFI_MODE_FULL_LOW_LATENCY_VALUE = 4;
    public static final int WIFI_MODE_FULL_VALUE = 1;
    public static final int WIFI_MODE_SCAN_ONLY_VALUE = 2;
    private static final Internal.EnumLiteMap<WifiModeEnum> internalValueMap = new Internal.EnumLiteMap<WifiModeEnum>() {
        /* class android.net.wifi.WifiModeEnum.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public WifiModeEnum findValueByNumber(int number) {
            return WifiModeEnum.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static WifiModeEnum valueOf(int value2) {
        return forNumber(value2);
    }

    public static WifiModeEnum forNumber(int value2) {
        if (value2 == 1) {
            return WIFI_MODE_FULL;
        }
        if (value2 == 2) {
            return WIFI_MODE_SCAN_ONLY;
        }
        if (value2 == 3) {
            return WIFI_MODE_FULL_HIGH_PERF;
        }
        if (value2 != 4) {
            return null;
        }
        return WIFI_MODE_FULL_LOW_LATENCY;
    }

    public static Internal.EnumLiteMap<WifiModeEnum> internalGetValueMap() {
        return internalValueMap;
    }

    private WifiModeEnum(int value2) {
        this.value = value2;
    }
}
