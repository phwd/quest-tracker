package android.app;

import com.google.protobuf.Internal;

public enum ProcessStateEnum implements Internal.EnumLite {
    PROCESS_STATE_UNKNOWN_TO_PROTO(PROCESS_STATE_UNKNOWN_TO_PROTO_VALUE),
    PROCESS_STATE_UNKNOWN(PROCESS_STATE_UNKNOWN_VALUE),
    PROCESS_STATE_PERSISTENT(1000),
    PROCESS_STATE_PERSISTENT_UI(1001),
    PROCESS_STATE_TOP(1002),
    PROCESS_STATE_BOUND_TOP(1020),
    PROCESS_STATE_FOREGROUND_SERVICE(1003),
    PROCESS_STATE_BOUND_FOREGROUND_SERVICE(1004),
    PROCESS_STATE_IMPORTANT_FOREGROUND(1005),
    PROCESS_STATE_IMPORTANT_BACKGROUND(1006),
    PROCESS_STATE_TRANSIENT_BACKGROUND(1007),
    PROCESS_STATE_BACKUP(1008),
    PROCESS_STATE_SERVICE(1009),
    PROCESS_STATE_RECEIVER(1010),
    PROCESS_STATE_TOP_SLEEPING(1011),
    PROCESS_STATE_HEAVY_WEIGHT(1012),
    PROCESS_STATE_HOME(1013),
    PROCESS_STATE_LAST_ACTIVITY(1014),
    PROCESS_STATE_CACHED_ACTIVITY(1015),
    PROCESS_STATE_CACHED_ACTIVITY_CLIENT(1016),
    PROCESS_STATE_CACHED_RECENT(1017),
    PROCESS_STATE_CACHED_EMPTY(1018),
    PROCESS_STATE_NONEXISTENT(1019);
    
    public static final int PROCESS_STATE_BACKUP_VALUE = 1008;
    public static final int PROCESS_STATE_BOUND_FOREGROUND_SERVICE_VALUE = 1004;
    public static final int PROCESS_STATE_BOUND_TOP_VALUE = 1020;
    public static final int PROCESS_STATE_CACHED_ACTIVITY_CLIENT_VALUE = 1016;
    public static final int PROCESS_STATE_CACHED_ACTIVITY_VALUE = 1015;
    public static final int PROCESS_STATE_CACHED_EMPTY_VALUE = 1018;
    public static final int PROCESS_STATE_CACHED_RECENT_VALUE = 1017;
    public static final int PROCESS_STATE_FOREGROUND_SERVICE_VALUE = 1003;
    public static final int PROCESS_STATE_HEAVY_WEIGHT_VALUE = 1012;
    public static final int PROCESS_STATE_HOME_VALUE = 1013;
    public static final int PROCESS_STATE_IMPORTANT_BACKGROUND_VALUE = 1006;
    public static final int PROCESS_STATE_IMPORTANT_FOREGROUND_VALUE = 1005;
    public static final int PROCESS_STATE_LAST_ACTIVITY_VALUE = 1014;
    public static final int PROCESS_STATE_NONEXISTENT_VALUE = 1019;
    public static final int PROCESS_STATE_PERSISTENT_UI_VALUE = 1001;
    public static final int PROCESS_STATE_PERSISTENT_VALUE = 1000;
    public static final int PROCESS_STATE_RECEIVER_VALUE = 1010;
    public static final int PROCESS_STATE_SERVICE_VALUE = 1009;
    public static final int PROCESS_STATE_TOP_SLEEPING_VALUE = 1011;
    public static final int PROCESS_STATE_TOP_VALUE = 1002;
    public static final int PROCESS_STATE_TRANSIENT_BACKGROUND_VALUE = 1007;
    public static final int PROCESS_STATE_UNKNOWN_TO_PROTO_VALUE = 998;
    public static final int PROCESS_STATE_UNKNOWN_VALUE = 999;
    private static final Internal.EnumLiteMap<ProcessStateEnum> internalValueMap = new Internal.EnumLiteMap<ProcessStateEnum>() {
        /* class android.app.ProcessStateEnum.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public ProcessStateEnum findValueByNumber(int number) {
            return ProcessStateEnum.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static ProcessStateEnum valueOf(int value2) {
        return forNumber(value2);
    }

    public static ProcessStateEnum forNumber(int value2) {
        switch (value2) {
            case PROCESS_STATE_UNKNOWN_TO_PROTO_VALUE:
                return PROCESS_STATE_UNKNOWN_TO_PROTO;
            case PROCESS_STATE_UNKNOWN_VALUE:
                return PROCESS_STATE_UNKNOWN;
            case 1000:
                return PROCESS_STATE_PERSISTENT;
            case 1001:
                return PROCESS_STATE_PERSISTENT_UI;
            case 1002:
                return PROCESS_STATE_TOP;
            case 1003:
                return PROCESS_STATE_FOREGROUND_SERVICE;
            case 1004:
                return PROCESS_STATE_BOUND_FOREGROUND_SERVICE;
            case 1005:
                return PROCESS_STATE_IMPORTANT_FOREGROUND;
            case 1006:
                return PROCESS_STATE_IMPORTANT_BACKGROUND;
            case 1007:
                return PROCESS_STATE_TRANSIENT_BACKGROUND;
            case 1008:
                return PROCESS_STATE_BACKUP;
            case 1009:
                return PROCESS_STATE_SERVICE;
            case 1010:
                return PROCESS_STATE_RECEIVER;
            case 1011:
                return PROCESS_STATE_TOP_SLEEPING;
            case 1012:
                return PROCESS_STATE_HEAVY_WEIGHT;
            case 1013:
                return PROCESS_STATE_HOME;
            case 1014:
                return PROCESS_STATE_LAST_ACTIVITY;
            case 1015:
                return PROCESS_STATE_CACHED_ACTIVITY;
            case 1016:
                return PROCESS_STATE_CACHED_ACTIVITY_CLIENT;
            case 1017:
                return PROCESS_STATE_CACHED_RECENT;
            case 1018:
                return PROCESS_STATE_CACHED_EMPTY;
            case 1019:
                return PROCESS_STATE_NONEXISTENT;
            case 1020:
                return PROCESS_STATE_BOUND_TOP;
            default:
                return null;
        }
    }

    public static Internal.EnumLiteMap<ProcessStateEnum> internalGetValueMap() {
        return internalValueMap;
    }

    private ProcessStateEnum(int value2) {
        this.value = value2;
    }
}
