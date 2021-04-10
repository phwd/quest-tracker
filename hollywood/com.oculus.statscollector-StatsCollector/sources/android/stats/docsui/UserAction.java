package android.stats.docsui;

import com.google.protobuf.Internal;

public enum UserAction implements Internal.EnumLite {
    ACTION_UNKNOWN(0),
    ACTION_OTHER(1),
    ACTION_GRID(2),
    ACTION_LIST(3),
    ACTION_SORT_NAME(4),
    ACTION_SORT_DATE(5),
    ACTION_SORT_SIZE(6),
    ACTION_SORT_TYPE(7),
    ACTION_SEARCH(8),
    ACTION_SHOW_SIZE(9),
    ACTION_HIDE_SIZE(10),
    ACTION_SETTINGS(11),
    ACTION_COPY_TO(12),
    ACTION_MOVE_TO(13),
    ACTION_DELETE(14),
    ACTION_RENAME(15),
    ACTION_CREATE_DIR(16),
    ACTION_SELECT_ALL(17),
    ACTION_SHARE(18),
    ACTION_OPEN(19),
    ACTION_SHOW_ADVANCED(20),
    ACTION_HIDE_ADVANCED(21),
    ACTION_NEW_WINDOW(22),
    ACTION_PASTE_CLIPBOARD(23),
    ACTION_COPY_CLIPBOARD(24),
    ACTION_DRAG_N_DROP(25),
    ACTION_DRAG_N_DROP_MULTI_WINDOW(26),
    ACTION_CUT_CLIPBOARD(27),
    ACTION_COMPRESS(28),
    ACTION_EXTRACT_TO(29),
    ACTION_VIEW_IN_APPLICATION(30),
    ACTION_INSPECTOR(31),
    ACTION_SEARCH_CHIP(32),
    ACTION_SEARCH_HISTORY(33);
    
    public static final int ACTION_COMPRESS_VALUE = 28;
    public static final int ACTION_COPY_CLIPBOARD_VALUE = 24;
    public static final int ACTION_COPY_TO_VALUE = 12;
    public static final int ACTION_CREATE_DIR_VALUE = 16;
    public static final int ACTION_CUT_CLIPBOARD_VALUE = 27;
    public static final int ACTION_DELETE_VALUE = 14;
    public static final int ACTION_DRAG_N_DROP_MULTI_WINDOW_VALUE = 26;
    public static final int ACTION_DRAG_N_DROP_VALUE = 25;
    public static final int ACTION_EXTRACT_TO_VALUE = 29;
    public static final int ACTION_GRID_VALUE = 2;
    public static final int ACTION_HIDE_ADVANCED_VALUE = 21;
    public static final int ACTION_HIDE_SIZE_VALUE = 10;
    public static final int ACTION_INSPECTOR_VALUE = 31;
    public static final int ACTION_LIST_VALUE = 3;
    public static final int ACTION_MOVE_TO_VALUE = 13;
    public static final int ACTION_NEW_WINDOW_VALUE = 22;
    public static final int ACTION_OPEN_VALUE = 19;
    public static final int ACTION_OTHER_VALUE = 1;
    public static final int ACTION_PASTE_CLIPBOARD_VALUE = 23;
    public static final int ACTION_RENAME_VALUE = 15;
    public static final int ACTION_SEARCH_CHIP_VALUE = 32;
    public static final int ACTION_SEARCH_HISTORY_VALUE = 33;
    public static final int ACTION_SEARCH_VALUE = 8;
    public static final int ACTION_SELECT_ALL_VALUE = 17;
    public static final int ACTION_SETTINGS_VALUE = 11;
    public static final int ACTION_SHARE_VALUE = 18;
    public static final int ACTION_SHOW_ADVANCED_VALUE = 20;
    public static final int ACTION_SHOW_SIZE_VALUE = 9;
    public static final int ACTION_SORT_DATE_VALUE = 5;
    public static final int ACTION_SORT_NAME_VALUE = 4;
    public static final int ACTION_SORT_SIZE_VALUE = 6;
    public static final int ACTION_SORT_TYPE_VALUE = 7;
    public static final int ACTION_UNKNOWN_VALUE = 0;
    public static final int ACTION_VIEW_IN_APPLICATION_VALUE = 30;
    private static final Internal.EnumLiteMap<UserAction> internalValueMap = new Internal.EnumLiteMap<UserAction>() {
        /* class android.stats.docsui.UserAction.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public UserAction findValueByNumber(int number) {
            return UserAction.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static UserAction valueOf(int value2) {
        return forNumber(value2);
    }

    public static UserAction forNumber(int value2) {
        switch (value2) {
            case 0:
                return ACTION_UNKNOWN;
            case 1:
                return ACTION_OTHER;
            case 2:
                return ACTION_GRID;
            case 3:
                return ACTION_LIST;
            case 4:
                return ACTION_SORT_NAME;
            case 5:
                return ACTION_SORT_DATE;
            case 6:
                return ACTION_SORT_SIZE;
            case 7:
                return ACTION_SORT_TYPE;
            case 8:
                return ACTION_SEARCH;
            case 9:
                return ACTION_SHOW_SIZE;
            case 10:
                return ACTION_HIDE_SIZE;
            case 11:
                return ACTION_SETTINGS;
            case 12:
                return ACTION_COPY_TO;
            case 13:
                return ACTION_MOVE_TO;
            case 14:
                return ACTION_DELETE;
            case 15:
                return ACTION_RENAME;
            case 16:
                return ACTION_CREATE_DIR;
            case 17:
                return ACTION_SELECT_ALL;
            case 18:
                return ACTION_SHARE;
            case 19:
                return ACTION_OPEN;
            case 20:
                return ACTION_SHOW_ADVANCED;
            case 21:
                return ACTION_HIDE_ADVANCED;
            case 22:
                return ACTION_NEW_WINDOW;
            case 23:
                return ACTION_PASTE_CLIPBOARD;
            case 24:
                return ACTION_COPY_CLIPBOARD;
            case 25:
                return ACTION_DRAG_N_DROP;
            case 26:
                return ACTION_DRAG_N_DROP_MULTI_WINDOW;
            case 27:
                return ACTION_CUT_CLIPBOARD;
            case 28:
                return ACTION_COMPRESS;
            case 29:
                return ACTION_EXTRACT_TO;
            case 30:
                return ACTION_VIEW_IN_APPLICATION;
            case 31:
                return ACTION_INSPECTOR;
            case 32:
                return ACTION_SEARCH_CHIP;
            case 33:
                return ACTION_SEARCH_HISTORY;
            default:
                return null;
        }
    }

    public static Internal.EnumLiteMap<UserAction> internalGetValueMap() {
        return internalValueMap;
    }

    private UserAction(int value2) {
        this.value = value2;
    }
}
