package android.app.job;

import com.google.protobuf.Internal;

public enum StopReasonEnum implements Internal.EnumLite {
    STOP_REASON_UNKNOWN(-1),
    STOP_REASON_CANCELLED(0),
    STOP_REASON_CONSTRAINTS_NOT_SATISFIED(1),
    STOP_REASON_PREEMPT(2),
    STOP_REASON_TIMEOUT(3),
    STOP_REASON_DEVICE_IDLE(4),
    STOP_REASON_DEVICE_THERMAL(5);
    
    public static final int STOP_REASON_CANCELLED_VALUE = 0;
    public static final int STOP_REASON_CONSTRAINTS_NOT_SATISFIED_VALUE = 1;
    public static final int STOP_REASON_DEVICE_IDLE_VALUE = 4;
    public static final int STOP_REASON_DEVICE_THERMAL_VALUE = 5;
    public static final int STOP_REASON_PREEMPT_VALUE = 2;
    public static final int STOP_REASON_TIMEOUT_VALUE = 3;
    public static final int STOP_REASON_UNKNOWN_VALUE = -1;
    private static final Internal.EnumLiteMap<StopReasonEnum> internalValueMap = new Internal.EnumLiteMap<StopReasonEnum>() {
        /* class android.app.job.StopReasonEnum.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public StopReasonEnum findValueByNumber(int number) {
            return StopReasonEnum.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static StopReasonEnum valueOf(int value2) {
        return forNumber(value2);
    }

    public static StopReasonEnum forNumber(int value2) {
        switch (value2) {
            case -1:
                return STOP_REASON_UNKNOWN;
            case 0:
                return STOP_REASON_CANCELLED;
            case 1:
                return STOP_REASON_CONSTRAINTS_NOT_SATISFIED;
            case 2:
                return STOP_REASON_PREEMPT;
            case 3:
                return STOP_REASON_TIMEOUT;
            case 4:
                return STOP_REASON_DEVICE_IDLE;
            case 5:
                return STOP_REASON_DEVICE_THERMAL;
            default:
                return null;
        }
    }

    public static Internal.EnumLiteMap<StopReasonEnum> internalGetValueMap() {
        return internalValueMap;
    }

    private StopReasonEnum(int value2) {
        this.value = value2;
    }
}
