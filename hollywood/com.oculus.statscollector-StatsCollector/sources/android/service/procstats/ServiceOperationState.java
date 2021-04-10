package android.service.procstats;

import com.google.protobuf.Internal;

public enum ServiceOperationState implements Internal.EnumLite {
    SERVICE_OPERATION_STATE_UNKNOWN(0),
    SERVICE_OPERATION_STATE_RUNNING(1),
    SERVICE_OPERATION_STATE_STARTED(2),
    SERVICE_OPERATION_STATE_FOREGROUND(3),
    SERVICE_OPERATION_STATE_BOUND(4),
    SERVICE_OPERATION_STATE_EXECUTING(5);
    
    public static final int SERVICE_OPERATION_STATE_BOUND_VALUE = 4;
    public static final int SERVICE_OPERATION_STATE_EXECUTING_VALUE = 5;
    public static final int SERVICE_OPERATION_STATE_FOREGROUND_VALUE = 3;
    public static final int SERVICE_OPERATION_STATE_RUNNING_VALUE = 1;
    public static final int SERVICE_OPERATION_STATE_STARTED_VALUE = 2;
    public static final int SERVICE_OPERATION_STATE_UNKNOWN_VALUE = 0;
    private static final Internal.EnumLiteMap<ServiceOperationState> internalValueMap = new Internal.EnumLiteMap<ServiceOperationState>() {
        /* class android.service.procstats.ServiceOperationState.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public ServiceOperationState findValueByNumber(int number) {
            return ServiceOperationState.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static ServiceOperationState valueOf(int value2) {
        return forNumber(value2);
    }

    public static ServiceOperationState forNumber(int value2) {
        if (value2 == 0) {
            return SERVICE_OPERATION_STATE_UNKNOWN;
        }
        if (value2 == 1) {
            return SERVICE_OPERATION_STATE_RUNNING;
        }
        if (value2 == 2) {
            return SERVICE_OPERATION_STATE_STARTED;
        }
        if (value2 == 3) {
            return SERVICE_OPERATION_STATE_FOREGROUND;
        }
        if (value2 == 4) {
            return SERVICE_OPERATION_STATE_BOUND;
        }
        if (value2 != 5) {
            return null;
        }
        return SERVICE_OPERATION_STATE_EXECUTING;
    }

    public static Internal.EnumLiteMap<ServiceOperationState> internalGetValueMap() {
        return internalValueMap;
    }

    private ServiceOperationState(int value2) {
        this.value = value2;
    }
}
