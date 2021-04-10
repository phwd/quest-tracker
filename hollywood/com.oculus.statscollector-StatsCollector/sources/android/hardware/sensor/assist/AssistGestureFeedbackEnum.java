package android.hardware.sensor.assist;

import com.google.protobuf.Internal;

public enum AssistGestureFeedbackEnum implements Internal.EnumLite {
    ASSIST_GESTURE_FEEDBACK_UNKNOWN(0),
    ASSIST_GESTURE_FEEDBACK_NOT_USED(1),
    ASSIST_GESTURE_FEEDBACK_USED(2);
    
    public static final int ASSIST_GESTURE_FEEDBACK_NOT_USED_VALUE = 1;
    public static final int ASSIST_GESTURE_FEEDBACK_UNKNOWN_VALUE = 0;
    public static final int ASSIST_GESTURE_FEEDBACK_USED_VALUE = 2;
    private static final Internal.EnumLiteMap<AssistGestureFeedbackEnum> internalValueMap = new Internal.EnumLiteMap<AssistGestureFeedbackEnum>() {
        /* class android.hardware.sensor.assist.AssistGestureFeedbackEnum.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public AssistGestureFeedbackEnum findValueByNumber(int number) {
            return AssistGestureFeedbackEnum.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static AssistGestureFeedbackEnum valueOf(int value2) {
        return forNumber(value2);
    }

    public static AssistGestureFeedbackEnum forNumber(int value2) {
        if (value2 == 0) {
            return ASSIST_GESTURE_FEEDBACK_UNKNOWN;
        }
        if (value2 == 1) {
            return ASSIST_GESTURE_FEEDBACK_NOT_USED;
        }
        if (value2 != 2) {
            return null;
        }
        return ASSIST_GESTURE_FEEDBACK_USED;
    }

    public static Internal.EnumLiteMap<AssistGestureFeedbackEnum> internalGetValueMap() {
        return internalValueMap;
    }

    private AssistGestureFeedbackEnum(int value2) {
        this.value = value2;
    }
}
