package android.service.procstats;

import com.google.protobuf.Internal;

public enum ProcessState implements Internal.EnumLite {
    PROCESS_STATE_UNKNOWN(0),
    PROCESS_STATE_PERSISTENT(1),
    PROCESS_STATE_TOP(2),
    PROCESS_STATE_IMPORTANT_FOREGROUND(3),
    PROCESS_STATE_IMPORTANT_BACKGROUND(4),
    PROCESS_STATE_BACKUP(5),
    PROCESS_STATE_SERVICE(6),
    PROCESS_STATE_SERVICE_RESTARTING(7),
    PROCESS_STATE_RECEIVER(8),
    PROCESS_STATE_HEAVY_WEIGHT(9),
    PROCESS_STATE_HOME(10),
    PROCESS_STATE_LAST_ACTIVITY(11),
    PROCESS_STATE_CACHED_ACTIVITY(12),
    PROCESS_STATE_CACHED_ACTIVITY_CLIENT(13),
    PROCESS_STATE_CACHED_EMPTY(14);
    
    public static final int PROCESS_STATE_BACKUP_VALUE = 5;
    public static final int PROCESS_STATE_CACHED_ACTIVITY_CLIENT_VALUE = 13;
    public static final int PROCESS_STATE_CACHED_ACTIVITY_VALUE = 12;
    public static final int PROCESS_STATE_CACHED_EMPTY_VALUE = 14;
    public static final int PROCESS_STATE_HEAVY_WEIGHT_VALUE = 9;
    public static final int PROCESS_STATE_HOME_VALUE = 10;
    public static final int PROCESS_STATE_IMPORTANT_BACKGROUND_VALUE = 4;
    public static final int PROCESS_STATE_IMPORTANT_FOREGROUND_VALUE = 3;
    public static final int PROCESS_STATE_LAST_ACTIVITY_VALUE = 11;
    public static final int PROCESS_STATE_PERSISTENT_VALUE = 1;
    public static final int PROCESS_STATE_RECEIVER_VALUE = 8;
    public static final int PROCESS_STATE_SERVICE_RESTARTING_VALUE = 7;
    public static final int PROCESS_STATE_SERVICE_VALUE = 6;
    public static final int PROCESS_STATE_TOP_VALUE = 2;
    public static final int PROCESS_STATE_UNKNOWN_VALUE = 0;
    private static final Internal.EnumLiteMap<ProcessState> internalValueMap = new Internal.EnumLiteMap<ProcessState>() {
        /* class android.service.procstats.ProcessState.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public ProcessState findValueByNumber(int number) {
            return ProcessState.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static ProcessState valueOf(int value2) {
        return forNumber(value2);
    }

    public static ProcessState forNumber(int value2) {
        switch (value2) {
            case 0:
                return PROCESS_STATE_UNKNOWN;
            case 1:
                return PROCESS_STATE_PERSISTENT;
            case 2:
                return PROCESS_STATE_TOP;
            case 3:
                return PROCESS_STATE_IMPORTANT_FOREGROUND;
            case 4:
                return PROCESS_STATE_IMPORTANT_BACKGROUND;
            case 5:
                return PROCESS_STATE_BACKUP;
            case 6:
                return PROCESS_STATE_SERVICE;
            case 7:
                return PROCESS_STATE_SERVICE_RESTARTING;
            case 8:
                return PROCESS_STATE_RECEIVER;
            case 9:
                return PROCESS_STATE_HEAVY_WEIGHT;
            case 10:
                return PROCESS_STATE_HOME;
            case 11:
                return PROCESS_STATE_LAST_ACTIVITY;
            case 12:
                return PROCESS_STATE_CACHED_ACTIVITY;
            case 13:
                return PROCESS_STATE_CACHED_ACTIVITY_CLIENT;
            case 14:
                return PROCESS_STATE_CACHED_EMPTY;
            default:
                return null;
        }
    }

    public static Internal.EnumLiteMap<ProcessState> internalGetValueMap() {
        return internalValueMap;
    }

    private ProcessState(int value2) {
        this.value = value2;
    }
}
