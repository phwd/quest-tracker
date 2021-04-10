package com.android.server.job;

import com.google.protobuf.Internal;

public enum ConstraintEnum implements Internal.EnumLite {
    CONSTRAINT_UNKNOWN(0),
    CONSTRAINT_CHARGING(1),
    CONSTRAINT_BATTERY_NOT_LOW(2),
    CONSTRAINT_STORAGE_NOT_LOW(3),
    CONSTRAINT_TIMING_DELAY(4),
    CONSTRAINT_DEADLINE(5),
    CONSTRAINT_IDLE(6),
    CONSTRAINT_CONNECTIVITY(7),
    CONSTRAINT_CONTENT_TRIGGER(8),
    CONSTRAINT_DEVICE_NOT_DOZING(9),
    CONSTRAINT_WITHIN_QUOTA(10),
    CONSTRAINT_BACKGROUND_NOT_RESTRICTED(11);
    
    public static final int CONSTRAINT_BACKGROUND_NOT_RESTRICTED_VALUE = 11;
    public static final int CONSTRAINT_BATTERY_NOT_LOW_VALUE = 2;
    public static final int CONSTRAINT_CHARGING_VALUE = 1;
    public static final int CONSTRAINT_CONNECTIVITY_VALUE = 7;
    public static final int CONSTRAINT_CONTENT_TRIGGER_VALUE = 8;
    public static final int CONSTRAINT_DEADLINE_VALUE = 5;
    public static final int CONSTRAINT_DEVICE_NOT_DOZING_VALUE = 9;
    public static final int CONSTRAINT_IDLE_VALUE = 6;
    public static final int CONSTRAINT_STORAGE_NOT_LOW_VALUE = 3;
    public static final int CONSTRAINT_TIMING_DELAY_VALUE = 4;
    public static final int CONSTRAINT_UNKNOWN_VALUE = 0;
    public static final int CONSTRAINT_WITHIN_QUOTA_VALUE = 10;
    private static final Internal.EnumLiteMap<ConstraintEnum> internalValueMap = new Internal.EnumLiteMap<ConstraintEnum>() {
        /* class com.android.server.job.ConstraintEnum.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public ConstraintEnum findValueByNumber(int number) {
            return ConstraintEnum.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static ConstraintEnum valueOf(int value2) {
        return forNumber(value2);
    }

    public static ConstraintEnum forNumber(int value2) {
        switch (value2) {
            case 0:
                return CONSTRAINT_UNKNOWN;
            case 1:
                return CONSTRAINT_CHARGING;
            case 2:
                return CONSTRAINT_BATTERY_NOT_LOW;
            case 3:
                return CONSTRAINT_STORAGE_NOT_LOW;
            case 4:
                return CONSTRAINT_TIMING_DELAY;
            case 5:
                return CONSTRAINT_DEADLINE;
            case 6:
                return CONSTRAINT_IDLE;
            case 7:
                return CONSTRAINT_CONNECTIVITY;
            case 8:
                return CONSTRAINT_CONTENT_TRIGGER;
            case 9:
                return CONSTRAINT_DEVICE_NOT_DOZING;
            case 10:
                return CONSTRAINT_WITHIN_QUOTA;
            case 11:
                return CONSTRAINT_BACKGROUND_NOT_RESTRICTED;
            default:
                return null;
        }
    }

    public static Internal.EnumLiteMap<ConstraintEnum> internalGetValueMap() {
        return internalValueMap;
    }

    private ConstraintEnum(int value2) {
        this.value = value2;
    }
}
