package android.view;

import com.google.protobuf.Internal;

public enum TransitionTypeEnum implements Internal.EnumLite {
    TRANSIT_NONE(0),
    TRANSIT_UNSET(-1),
    TRANSIT_ACTIVITY_OPEN(6),
    TRANSIT_ACTIVITY_CLOSE(7),
    TRANSIT_TASK_OPEN(8),
    TRANSIT_TASK_CLOSE(9),
    TRANSIT_TASK_TO_FRONT(10),
    TRANSIT_TASK_TO_BACK(11),
    TRANSIT_WALLPAPER_CLOSE(12),
    TRANSIT_WALLPAPER_OPEN(13),
    TRANSIT_WALLPAPER_INTRA_OPEN(14),
    TRANSIT_WALLPAPER_INTRA_CLOSE(15),
    TRANSIT_TASK_OPEN_BEHIND(16),
    TRANSIT_TASK_IN_PLACE(17),
    TRANSIT_ACTIVITY_RELAUNCH(18),
    TRANSIT_DOCK_TASK_FROM_RECENTS(19),
    TRANSIT_KEYGUARD_GOING_AWAY(20),
    TRANSIT_KEYGUARD_GOING_AWAY_ON_WALLPAPER(21),
    TRANSIT_KEYGUARD_OCCLUDE(22),
    TRANSIT_KEYGUARD_UNOCCLUDE(23),
    TRANSIT_TRANSLUCENT_ACTIVITY_OPEN(24),
    TRANSIT_TRANSLUCENT_ACTIVITY_CLOSE(25),
    TRANSIT_CRASHING_ACTIVITY_CLOSE(26);
    
    public static final int TRANSIT_ACTIVITY_CLOSE_VALUE = 7;
    public static final int TRANSIT_ACTIVITY_OPEN_VALUE = 6;
    public static final int TRANSIT_ACTIVITY_RELAUNCH_VALUE = 18;
    public static final int TRANSIT_CRASHING_ACTIVITY_CLOSE_VALUE = 26;
    public static final int TRANSIT_DOCK_TASK_FROM_RECENTS_VALUE = 19;
    public static final int TRANSIT_KEYGUARD_GOING_AWAY_ON_WALLPAPER_VALUE = 21;
    public static final int TRANSIT_KEYGUARD_GOING_AWAY_VALUE = 20;
    public static final int TRANSIT_KEYGUARD_OCCLUDE_VALUE = 22;
    public static final int TRANSIT_KEYGUARD_UNOCCLUDE_VALUE = 23;
    public static final int TRANSIT_NONE_VALUE = 0;
    public static final int TRANSIT_TASK_CLOSE_VALUE = 9;
    public static final int TRANSIT_TASK_IN_PLACE_VALUE = 17;
    public static final int TRANSIT_TASK_OPEN_BEHIND_VALUE = 16;
    public static final int TRANSIT_TASK_OPEN_VALUE = 8;
    public static final int TRANSIT_TASK_TO_BACK_VALUE = 11;
    public static final int TRANSIT_TASK_TO_FRONT_VALUE = 10;
    public static final int TRANSIT_TRANSLUCENT_ACTIVITY_CLOSE_VALUE = 25;
    public static final int TRANSIT_TRANSLUCENT_ACTIVITY_OPEN_VALUE = 24;
    public static final int TRANSIT_UNSET_VALUE = -1;
    public static final int TRANSIT_WALLPAPER_CLOSE_VALUE = 12;
    public static final int TRANSIT_WALLPAPER_INTRA_CLOSE_VALUE = 15;
    public static final int TRANSIT_WALLPAPER_INTRA_OPEN_VALUE = 14;
    public static final int TRANSIT_WALLPAPER_OPEN_VALUE = 13;
    private static final Internal.EnumLiteMap<TransitionTypeEnum> internalValueMap = new Internal.EnumLiteMap<TransitionTypeEnum>() {
        /* class android.view.TransitionTypeEnum.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public TransitionTypeEnum findValueByNumber(int number) {
            return TransitionTypeEnum.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static TransitionTypeEnum valueOf(int value2) {
        return forNumber(value2);
    }

    public static TransitionTypeEnum forNumber(int value2) {
        if (value2 == -1) {
            return TRANSIT_UNSET;
        }
        if (value2 == 0) {
            return TRANSIT_NONE;
        }
        switch (value2) {
            case 6:
                return TRANSIT_ACTIVITY_OPEN;
            case 7:
                return TRANSIT_ACTIVITY_CLOSE;
            case 8:
                return TRANSIT_TASK_OPEN;
            case 9:
                return TRANSIT_TASK_CLOSE;
            case 10:
                return TRANSIT_TASK_TO_FRONT;
            case 11:
                return TRANSIT_TASK_TO_BACK;
            case 12:
                return TRANSIT_WALLPAPER_CLOSE;
            case 13:
                return TRANSIT_WALLPAPER_OPEN;
            case 14:
                return TRANSIT_WALLPAPER_INTRA_OPEN;
            case 15:
                return TRANSIT_WALLPAPER_INTRA_CLOSE;
            case 16:
                return TRANSIT_TASK_OPEN_BEHIND;
            case 17:
                return TRANSIT_TASK_IN_PLACE;
            case 18:
                return TRANSIT_ACTIVITY_RELAUNCH;
            case 19:
                return TRANSIT_DOCK_TASK_FROM_RECENTS;
            case 20:
                return TRANSIT_KEYGUARD_GOING_AWAY;
            case 21:
                return TRANSIT_KEYGUARD_GOING_AWAY_ON_WALLPAPER;
            case 22:
                return TRANSIT_KEYGUARD_OCCLUDE;
            case 23:
                return TRANSIT_KEYGUARD_UNOCCLUDE;
            case 24:
                return TRANSIT_TRANSLUCENT_ACTIVITY_OPEN;
            case 25:
                return TRANSIT_TRANSLUCENT_ACTIVITY_CLOSE;
            case 26:
                return TRANSIT_CRASHING_ACTIVITY_CLOSE;
            default:
                return null;
        }
    }

    public static Internal.EnumLiteMap<TransitionTypeEnum> internalGetValueMap() {
        return internalValueMap;
    }

    private TransitionTypeEnum(int value2) {
        this.value = value2;
    }
}
