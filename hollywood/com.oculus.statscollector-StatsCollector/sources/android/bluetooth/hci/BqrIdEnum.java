package android.bluetooth.hci;

import com.google.protobuf.Internal;

public enum BqrIdEnum implements Internal.EnumLite {
    BQR_ID_UNKNOWN(0),
    BQR_ID_MONITOR_MODE(1),
    BQR_ID_APPROACH_LSTO(2),
    BQR_ID_A2DP_AUDIO_CHOPPY(3),
    BQR_ID_SCO_VOICE_CHOPPY(4);
    
    public static final int BQR_ID_A2DP_AUDIO_CHOPPY_VALUE = 3;
    public static final int BQR_ID_APPROACH_LSTO_VALUE = 2;
    public static final int BQR_ID_MONITOR_MODE_VALUE = 1;
    public static final int BQR_ID_SCO_VOICE_CHOPPY_VALUE = 4;
    public static final int BQR_ID_UNKNOWN_VALUE = 0;
    private static final Internal.EnumLiteMap<BqrIdEnum> internalValueMap = new Internal.EnumLiteMap<BqrIdEnum>() {
        /* class android.bluetooth.hci.BqrIdEnum.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public BqrIdEnum findValueByNumber(int number) {
            return BqrIdEnum.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static BqrIdEnum valueOf(int value2) {
        return forNumber(value2);
    }

    public static BqrIdEnum forNumber(int value2) {
        if (value2 == 0) {
            return BQR_ID_UNKNOWN;
        }
        if (value2 == 1) {
            return BQR_ID_MONITOR_MODE;
        }
        if (value2 == 2) {
            return BQR_ID_APPROACH_LSTO;
        }
        if (value2 == 3) {
            return BQR_ID_A2DP_AUDIO_CHOPPY;
        }
        if (value2 != 4) {
            return null;
        }
        return BQR_ID_SCO_VOICE_CHOPPY;
    }

    public static Internal.EnumLiteMap<BqrIdEnum> internalGetValueMap() {
        return internalValueMap;
    }

    private BqrIdEnum(int value2) {
        this.value = value2;
    }
}
