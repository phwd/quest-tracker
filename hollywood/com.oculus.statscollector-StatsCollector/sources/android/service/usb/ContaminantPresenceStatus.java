package android.service.usb;

import com.google.protobuf.Internal;

public enum ContaminantPresenceStatus implements Internal.EnumLite {
    CONTAMINANT_STATUS_UNKNOWN(0),
    CONTAMINANT_STATUS_NOT_SUPPORTED(1),
    CONTAMINANT_STATUS_DISABLED(2),
    CONTAMINANT_STATUS_NOT_DETECTED(3),
    CONTAMINANT_STATUS_DETECTED(4);
    
    public static final int CONTAMINANT_STATUS_DETECTED_VALUE = 4;
    public static final int CONTAMINANT_STATUS_DISABLED_VALUE = 2;
    public static final int CONTAMINANT_STATUS_NOT_DETECTED_VALUE = 3;
    public static final int CONTAMINANT_STATUS_NOT_SUPPORTED_VALUE = 1;
    public static final int CONTAMINANT_STATUS_UNKNOWN_VALUE = 0;
    private static final Internal.EnumLiteMap<ContaminantPresenceStatus> internalValueMap = new Internal.EnumLiteMap<ContaminantPresenceStatus>() {
        /* class android.service.usb.ContaminantPresenceStatus.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public ContaminantPresenceStatus findValueByNumber(int number) {
            return ContaminantPresenceStatus.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static ContaminantPresenceStatus valueOf(int value2) {
        return forNumber(value2);
    }

    public static ContaminantPresenceStatus forNumber(int value2) {
        if (value2 == 0) {
            return CONTAMINANT_STATUS_UNKNOWN;
        }
        if (value2 == 1) {
            return CONTAMINANT_STATUS_NOT_SUPPORTED;
        }
        if (value2 == 2) {
            return CONTAMINANT_STATUS_DISABLED;
        }
        if (value2 == 3) {
            return CONTAMINANT_STATUS_NOT_DETECTED;
        }
        if (value2 != 4) {
            return null;
        }
        return CONTAMINANT_STATUS_DETECTED;
    }

    public static Internal.EnumLiteMap<ContaminantPresenceStatus> internalGetValueMap() {
        return internalValueMap;
    }

    private ContaminantPresenceStatus(int value2) {
        this.value = value2;
    }
}
