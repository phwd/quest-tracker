package android.hardware.sensor.assist;

import com.google.protobuf.Internal;

public enum AssistGestureStageEnum implements Internal.EnumLite {
    ASSIST_GESTURE_STAGE_UNKNOWN(0),
    ASSIST_GESTURE_STAGE_PROGRESS(1),
    ASSIST_GESTURE_STAGE_PRIMED(2),
    ASSIST_GESTURE_STAGE_DETECTED(3);
    
    public static final int ASSIST_GESTURE_STAGE_DETECTED_VALUE = 3;
    public static final int ASSIST_GESTURE_STAGE_PRIMED_VALUE = 2;
    public static final int ASSIST_GESTURE_STAGE_PROGRESS_VALUE = 1;
    public static final int ASSIST_GESTURE_STAGE_UNKNOWN_VALUE = 0;
    private static final Internal.EnumLiteMap<AssistGestureStageEnum> internalValueMap = new Internal.EnumLiteMap<AssistGestureStageEnum>() {
        /* class android.hardware.sensor.assist.AssistGestureStageEnum.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public AssistGestureStageEnum findValueByNumber(int number) {
            return AssistGestureStageEnum.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static AssistGestureStageEnum valueOf(int value2) {
        return forNumber(value2);
    }

    public static AssistGestureStageEnum forNumber(int value2) {
        if (value2 == 0) {
            return ASSIST_GESTURE_STAGE_UNKNOWN;
        }
        if (value2 == 1) {
            return ASSIST_GESTURE_STAGE_PROGRESS;
        }
        if (value2 == 2) {
            return ASSIST_GESTURE_STAGE_PRIMED;
        }
        if (value2 != 3) {
            return null;
        }
        return ASSIST_GESTURE_STAGE_DETECTED;
    }

    public static Internal.EnumLiteMap<AssistGestureStageEnum> internalGetValueMap() {
        return internalValueMap;
    }

    private AssistGestureStageEnum(int value2) {
        this.value = value2;
    }
}
