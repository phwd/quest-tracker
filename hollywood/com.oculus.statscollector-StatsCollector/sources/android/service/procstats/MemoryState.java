package android.service.procstats;

import com.google.protobuf.Internal;

public enum MemoryState implements Internal.EnumLite {
    MEMORY_STATE_UNKNOWN(0),
    MEMORY_STATE_NORMAL(1),
    MEMORY_STATE_MODERATE(2),
    MEMORY_STATE_LOW(3),
    MEMORY_STATE_CRITICAL(4);
    
    public static final int MEMORY_STATE_CRITICAL_VALUE = 4;
    public static final int MEMORY_STATE_LOW_VALUE = 3;
    public static final int MEMORY_STATE_MODERATE_VALUE = 2;
    public static final int MEMORY_STATE_NORMAL_VALUE = 1;
    public static final int MEMORY_STATE_UNKNOWN_VALUE = 0;
    private static final Internal.EnumLiteMap<MemoryState> internalValueMap = new Internal.EnumLiteMap<MemoryState>() {
        /* class android.service.procstats.MemoryState.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public MemoryState findValueByNumber(int number) {
            return MemoryState.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static MemoryState valueOf(int value2) {
        return forNumber(value2);
    }

    public static MemoryState forNumber(int value2) {
        if (value2 == 0) {
            return MEMORY_STATE_UNKNOWN;
        }
        if (value2 == 1) {
            return MEMORY_STATE_NORMAL;
        }
        if (value2 == 2) {
            return MEMORY_STATE_MODERATE;
        }
        if (value2 == 3) {
            return MEMORY_STATE_LOW;
        }
        if (value2 != 4) {
            return null;
        }
        return MEMORY_STATE_CRITICAL;
    }

    public static Internal.EnumLiteMap<MemoryState> internalGetValueMap() {
        return internalValueMap;
    }

    private MemoryState(int value2) {
        this.value = value2;
    }
}
