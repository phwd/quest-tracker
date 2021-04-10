package com.facebook.quicklog.identifiers;

public class NowDivebar {
    public static final short MODULE_ID = 86;
    public static final int NOW_PERF_ENTER_ICON_LOADED = 5636097;
    public static final int NOW_PERF_ENTITY_CARD_LOADED = 5636104;
    public static final int NOW_PERF_FEED_STORY_LOADED = 5636103;
    public static final int NOW_PERF_ICONS_LOAD_TIME = 5636108;
    public static final int NOW_PERF_ICON_PICKER_LOADED = 5636100;
    public static final int NOW_PERF_ICON_SUGGESTIONS_LOAD_TIME = 5636109;
    public static final int NOW_PERF_LOCATION_PICKER_LOADED = 5636101;
    public static final int NOW_PERF_PLACE_LOAD_TIME = 5636107;
    public static final int NOW_PERF_POST_COMPLETED = 5636102;
    public static final int NOW_PERF_STATUS_LIST_LOADED = 5636098;
    public static final int NOW_PERF_STATUS_LIST_LOADED_FRESH = 5636105;
    public static final int NOW_PERF_SUGGESTIONS_LIST_LOADED = 5636099;
    public static final int NOW_PERF_SUGGESTIONS_LOAD_TIME = 5636106;

    public static String getMarkerName(int i) {
        switch (i) {
            case 1:
                return "NOW_DIVEBAR_NOW_PERF_ENTER_ICON_LOADED";
            case 2:
                return "NOW_DIVEBAR_NOW_PERF_STATUS_LIST_LOADED";
            case 3:
                return "NOW_DIVEBAR_NOW_PERF_SUGGESTIONS_LIST_LOADED";
            case 4:
                return "NOW_DIVEBAR_NOW_PERF_ICON_PICKER_LOADED";
            case 5:
                return "NOW_DIVEBAR_NOW_PERF_LOCATION_PICKER_LOADED";
            case 6:
                return "NOW_DIVEBAR_NOW_PERF_POST_COMPLETED";
            case 7:
                return "NOW_DIVEBAR_NOW_PERF_FEED_STORY_LOADED";
            case 8:
                return "NOW_DIVEBAR_NOW_PERF_ENTITY_CARD_LOADED";
            case 9:
                return "NOW_DIVEBAR_NOW_PERF_STATUS_LIST_LOADED_FRESH";
            case 10:
                return "NOW_DIVEBAR_NOW_PERF_SUGGESTIONS_LOAD_TIME";
            case 11:
                return "NOW_DIVEBAR_NOW_PERF_PLACE_LOAD_TIME";
            case 12:
                return "NOW_DIVEBAR_NOW_PERF_ICONS_LOAD_TIME";
            case 13:
                return "NOW_DIVEBAR_NOW_PERF_ICON_SUGGESTIONS_LOAD_TIME";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
