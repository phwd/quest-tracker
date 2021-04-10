package android.stats.style;

import com.google.protobuf.Internal;

public enum Action implements Internal.EnumLite {
    DEFAULT_ACTION(0),
    ONRESUME(1),
    ONSTOP(2),
    PICKER_SELECT(3),
    PICKER_APPLIED(4),
    WALLPAPER_OPEN_CATEGORY(5),
    WALLPAPER_SELECT(6),
    WALLPAPER_APPLIED(7),
    WALLPAPER_EXPLORE(8),
    WALLPAPER_DOWNLOAD(9),
    WALLPAPER_REMOVE(10),
    LIVE_WALLPAPER_DOWNLOAD_SUCCESS(11),
    LIVE_WALLPAPER_DOWNLOAD_FAILED(12),
    LIVE_WALLPAPER_DOWNLOAD_CANCELLED(13),
    LIVE_WALLPAPER_DELETE_SUCCESS(14),
    LIVE_WALLPAPER_DELETE_FAILED(15),
    LIVE_WALLPAPER_APPLIED(16);
    
    public static final int DEFAULT_ACTION_VALUE = 0;
    public static final int LIVE_WALLPAPER_APPLIED_VALUE = 16;
    public static final int LIVE_WALLPAPER_DELETE_FAILED_VALUE = 15;
    public static final int LIVE_WALLPAPER_DELETE_SUCCESS_VALUE = 14;
    public static final int LIVE_WALLPAPER_DOWNLOAD_CANCELLED_VALUE = 13;
    public static final int LIVE_WALLPAPER_DOWNLOAD_FAILED_VALUE = 12;
    public static final int LIVE_WALLPAPER_DOWNLOAD_SUCCESS_VALUE = 11;
    public static final int ONRESUME_VALUE = 1;
    public static final int ONSTOP_VALUE = 2;
    public static final int PICKER_APPLIED_VALUE = 4;
    public static final int PICKER_SELECT_VALUE = 3;
    public static final int WALLPAPER_APPLIED_VALUE = 7;
    public static final int WALLPAPER_DOWNLOAD_VALUE = 9;
    public static final int WALLPAPER_EXPLORE_VALUE = 8;
    public static final int WALLPAPER_OPEN_CATEGORY_VALUE = 5;
    public static final int WALLPAPER_REMOVE_VALUE = 10;
    public static final int WALLPAPER_SELECT_VALUE = 6;
    private static final Internal.EnumLiteMap<Action> internalValueMap = new Internal.EnumLiteMap<Action>() {
        /* class android.stats.style.Action.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public Action findValueByNumber(int number) {
            return Action.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static Action valueOf(int value2) {
        return forNumber(value2);
    }

    public static Action forNumber(int value2) {
        switch (value2) {
            case 0:
                return DEFAULT_ACTION;
            case 1:
                return ONRESUME;
            case 2:
                return ONSTOP;
            case 3:
                return PICKER_SELECT;
            case 4:
                return PICKER_APPLIED;
            case 5:
                return WALLPAPER_OPEN_CATEGORY;
            case 6:
                return WALLPAPER_SELECT;
            case 7:
                return WALLPAPER_APPLIED;
            case 8:
                return WALLPAPER_EXPLORE;
            case 9:
                return WALLPAPER_DOWNLOAD;
            case 10:
                return WALLPAPER_REMOVE;
            case 11:
                return LIVE_WALLPAPER_DOWNLOAD_SUCCESS;
            case 12:
                return LIVE_WALLPAPER_DOWNLOAD_FAILED;
            case 13:
                return LIVE_WALLPAPER_DOWNLOAD_CANCELLED;
            case 14:
                return LIVE_WALLPAPER_DELETE_SUCCESS;
            case 15:
                return LIVE_WALLPAPER_DELETE_FAILED;
            case 16:
                return LIVE_WALLPAPER_APPLIED;
            default:
                return null;
        }
    }

    public static Internal.EnumLiteMap<Action> internalGetValueMap() {
        return internalValueMap;
    }

    private Action(int value2) {
        this.value = value2;
    }
}
