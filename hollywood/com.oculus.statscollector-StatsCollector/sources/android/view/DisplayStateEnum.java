package android.view;

import com.google.protobuf.Internal;

public enum DisplayStateEnum implements Internal.EnumLite {
    DISPLAY_STATE_UNKNOWN(0),
    DISPLAY_STATE_OFF(1),
    DISPLAY_STATE_ON(2),
    DISPLAY_STATE_DOZE(3),
    DISPLAY_STATE_DOZE_SUSPEND(4),
    DISPLAY_STATE_VR(5),
    DISPLAY_STATE_ON_SUSPEND(6);
    
    public static final int DISPLAY_STATE_DOZE_SUSPEND_VALUE = 4;
    public static final int DISPLAY_STATE_DOZE_VALUE = 3;
    public static final int DISPLAY_STATE_OFF_VALUE = 1;
    public static final int DISPLAY_STATE_ON_SUSPEND_VALUE = 6;
    public static final int DISPLAY_STATE_ON_VALUE = 2;
    public static final int DISPLAY_STATE_UNKNOWN_VALUE = 0;
    public static final int DISPLAY_STATE_VR_VALUE = 5;
    private static final Internal.EnumLiteMap<DisplayStateEnum> internalValueMap = new Internal.EnumLiteMap<DisplayStateEnum>() {
        /* class android.view.DisplayStateEnum.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public DisplayStateEnum findValueByNumber(int number) {
            return DisplayStateEnum.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static DisplayStateEnum valueOf(int value2) {
        return forNumber(value2);
    }

    public static DisplayStateEnum forNumber(int value2) {
        switch (value2) {
            case 0:
                return DISPLAY_STATE_UNKNOWN;
            case 1:
                return DISPLAY_STATE_OFF;
            case 2:
                return DISPLAY_STATE_ON;
            case 3:
                return DISPLAY_STATE_DOZE;
            case 4:
                return DISPLAY_STATE_DOZE_SUSPEND;
            case 5:
                return DISPLAY_STATE_VR;
            case 6:
                return DISPLAY_STATE_ON_SUSPEND;
            default:
                return null;
        }
    }

    public static Internal.EnumLiteMap<DisplayStateEnum> internalGetValueMap() {
        return internalValueMap;
    }

    private DisplayStateEnum(int value2) {
        this.value = value2;
    }
}
