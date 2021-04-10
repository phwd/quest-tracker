package android.os;

import com.google.protobuf.Internal;

public enum WakeLockLevelEnum implements Internal.EnumLite {
    PARTIAL_WAKE_LOCK(1),
    SCREEN_DIM_WAKE_LOCK(6),
    SCREEN_BRIGHT_WAKE_LOCK(10),
    FULL_WAKE_LOCK(26),
    PROXIMITY_SCREEN_OFF_WAKE_LOCK(32),
    DOZE_WAKE_LOCK(64),
    DRAW_WAKE_LOCK(128);
    
    public static final int DOZE_WAKE_LOCK_VALUE = 64;
    public static final int DRAW_WAKE_LOCK_VALUE = 128;
    public static final int FULL_WAKE_LOCK_VALUE = 26;
    public static final int PARTIAL_WAKE_LOCK_VALUE = 1;
    public static final int PROXIMITY_SCREEN_OFF_WAKE_LOCK_VALUE = 32;
    public static final int SCREEN_BRIGHT_WAKE_LOCK_VALUE = 10;
    public static final int SCREEN_DIM_WAKE_LOCK_VALUE = 6;
    private static final Internal.EnumLiteMap<WakeLockLevelEnum> internalValueMap = new Internal.EnumLiteMap<WakeLockLevelEnum>() {
        /* class android.os.WakeLockLevelEnum.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public WakeLockLevelEnum findValueByNumber(int number) {
            return WakeLockLevelEnum.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static WakeLockLevelEnum valueOf(int value2) {
        return forNumber(value2);
    }

    public static WakeLockLevelEnum forNumber(int value2) {
        if (value2 == 1) {
            return PARTIAL_WAKE_LOCK;
        }
        if (value2 == 6) {
            return SCREEN_DIM_WAKE_LOCK;
        }
        if (value2 == 10) {
            return SCREEN_BRIGHT_WAKE_LOCK;
        }
        if (value2 == 26) {
            return FULL_WAKE_LOCK;
        }
        if (value2 == 32) {
            return PROXIMITY_SCREEN_OFF_WAKE_LOCK;
        }
        if (value2 == 64) {
            return DOZE_WAKE_LOCK;
        }
        if (value2 != 128) {
            return null;
        }
        return DRAW_WAKE_LOCK;
    }

    public static Internal.EnumLiteMap<WakeLockLevelEnum> internalGetValueMap() {
        return internalValueMap;
    }

    private WakeLockLevelEnum(int value2) {
        this.value = value2;
    }
}
