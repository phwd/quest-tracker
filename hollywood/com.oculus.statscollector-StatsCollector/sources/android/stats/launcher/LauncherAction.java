package android.stats.launcher;

import com.google.protobuf.Internal;

public enum LauncherAction implements Internal.EnumLite {
    DEFAULT_ACTION(0),
    LAUNCH_APP(1),
    LAUNCH_TASK(2),
    DISMISS_TASK(3),
    LONGPRESS(4),
    DRAGDROP(5),
    SWIPE_UP(6),
    SWIPE_DOWN(7),
    SWIPE_LEFT(8),
    SWIPE_RIGHT(9);
    
    public static final int DEFAULT_ACTION_VALUE = 0;
    public static final int DISMISS_TASK_VALUE = 3;
    public static final int DRAGDROP_VALUE = 5;
    public static final int LAUNCH_APP_VALUE = 1;
    public static final int LAUNCH_TASK_VALUE = 2;
    public static final int LONGPRESS_VALUE = 4;
    public static final int SWIPE_DOWN_VALUE = 7;
    public static final int SWIPE_LEFT_VALUE = 8;
    public static final int SWIPE_RIGHT_VALUE = 9;
    public static final int SWIPE_UP_VALUE = 6;
    private static final Internal.EnumLiteMap<LauncherAction> internalValueMap = new Internal.EnumLiteMap<LauncherAction>() {
        /* class android.stats.launcher.LauncherAction.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public LauncherAction findValueByNumber(int number) {
            return LauncherAction.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static LauncherAction valueOf(int value2) {
        return forNumber(value2);
    }

    public static LauncherAction forNumber(int value2) {
        switch (value2) {
            case 0:
                return DEFAULT_ACTION;
            case 1:
                return LAUNCH_APP;
            case 2:
                return LAUNCH_TASK;
            case 3:
                return DISMISS_TASK;
            case 4:
                return LONGPRESS;
            case 5:
                return DRAGDROP;
            case 6:
                return SWIPE_UP;
            case 7:
                return SWIPE_DOWN;
            case 8:
                return SWIPE_LEFT;
            case 9:
                return SWIPE_RIGHT;
            default:
                return null;
        }
    }

    public static Internal.EnumLiteMap<LauncherAction> internalGetValueMap() {
        return internalValueMap;
    }

    private LauncherAction(int value2) {
        this.value = value2;
    }
}
