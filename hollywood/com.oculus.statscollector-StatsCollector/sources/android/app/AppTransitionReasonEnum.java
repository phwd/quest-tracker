package android.app;

import com.google.protobuf.Internal;

public enum AppTransitionReasonEnum implements Internal.EnumLite {
    APP_TRANSITION_REASON_UNKNOWN(0),
    APP_TRANSITION_SPLASH_SCREEN(1),
    APP_TRANSITION_WINDOWS_DRAWN(2),
    APP_TRANSITION_TIMEOUT(3),
    APP_TRANSITION_SNAPSHOT(4),
    APP_TRANSITION_RECENTS_ANIM(5);
    
    public static final int APP_TRANSITION_REASON_UNKNOWN_VALUE = 0;
    public static final int APP_TRANSITION_RECENTS_ANIM_VALUE = 5;
    public static final int APP_TRANSITION_SNAPSHOT_VALUE = 4;
    public static final int APP_TRANSITION_SPLASH_SCREEN_VALUE = 1;
    public static final int APP_TRANSITION_TIMEOUT_VALUE = 3;
    public static final int APP_TRANSITION_WINDOWS_DRAWN_VALUE = 2;
    private static final Internal.EnumLiteMap<AppTransitionReasonEnum> internalValueMap = new Internal.EnumLiteMap<AppTransitionReasonEnum>() {
        /* class android.app.AppTransitionReasonEnum.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public AppTransitionReasonEnum findValueByNumber(int number) {
            return AppTransitionReasonEnum.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static AppTransitionReasonEnum valueOf(int value2) {
        return forNumber(value2);
    }

    public static AppTransitionReasonEnum forNumber(int value2) {
        if (value2 == 0) {
            return APP_TRANSITION_REASON_UNKNOWN;
        }
        if (value2 == 1) {
            return APP_TRANSITION_SPLASH_SCREEN;
        }
        if (value2 == 2) {
            return APP_TRANSITION_WINDOWS_DRAWN;
        }
        if (value2 == 3) {
            return APP_TRANSITION_TIMEOUT;
        }
        if (value2 == 4) {
            return APP_TRANSITION_SNAPSHOT;
        }
        if (value2 != 5) {
            return null;
        }
        return APP_TRANSITION_RECENTS_ANIM;
    }

    public static Internal.EnumLiteMap<AppTransitionReasonEnum> internalGetValueMap() {
        return internalValueMap;
    }

    private AppTransitionReasonEnum(int value2) {
        this.value = value2;
    }
}
