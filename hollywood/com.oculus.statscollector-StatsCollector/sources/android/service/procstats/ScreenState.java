package android.service.procstats;

import com.google.protobuf.Internal;

public enum ScreenState implements Internal.EnumLite {
    SCREEN_STATE_UNKNOWN(0),
    SCREEN_STATE_OFF(1),
    SCREEN_STATE_ON(2);
    
    public static final int SCREEN_STATE_OFF_VALUE = 1;
    public static final int SCREEN_STATE_ON_VALUE = 2;
    public static final int SCREEN_STATE_UNKNOWN_VALUE = 0;
    private static final Internal.EnumLiteMap<ScreenState> internalValueMap = new Internal.EnumLiteMap<ScreenState>() {
        /* class android.service.procstats.ScreenState.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public ScreenState findValueByNumber(int number) {
            return ScreenState.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static ScreenState valueOf(int value2) {
        return forNumber(value2);
    }

    public static ScreenState forNumber(int value2) {
        if (value2 == 0) {
            return SCREEN_STATE_UNKNOWN;
        }
        if (value2 == 1) {
            return SCREEN_STATE_OFF;
        }
        if (value2 != 2) {
            return null;
        }
        return SCREEN_STATE_ON;
    }

    public static Internal.EnumLiteMap<ScreenState> internalGetValueMap() {
        return internalValueMap;
    }

    private ScreenState(int value2) {
        this.value = value2;
    }
}
