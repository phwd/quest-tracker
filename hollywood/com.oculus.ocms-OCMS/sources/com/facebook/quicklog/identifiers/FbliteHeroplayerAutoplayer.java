package com.facebook.quicklog.identifiers;

public class FbliteHeroplayerAutoplayer {
    public static final int LITE_HERO_SCROLLER_PERF_LOGGER_LOG_SERVICE_BIND_END = 48824322;
    public static final int LITE_HERO_SCROLLER_PERF_LOGGER_LOG_SERVICE_BIND_START = 48824321;
    public static final int LITE_HERO_SCROLLER_PERF_LOG_PLAYER_ACTION_END = 48824325;
    public static final int LITE_HERO_SCROLLER_PERF_LOG_PLAYER_ACTION_START = 48824326;
    public static final int LITE_HERO_SCROLLER_PERF_LOG_PLAYER_INIT_END = 48824324;
    public static final int LITE_HERO_SCROLLER_PERF_LOG_PLAYER_INIT_START = 48824323;
    public static final int LITE_HERO_SERVICE_CLIENT_ON_SERVICE_CONNECTED_END = 48824340;
    public static final int LITE_HERO_SERVICE_CLIENT_ON_SERVICE_CONNECTED_START = 48824339;
    public static final int LITE_HERO_SERVICE_CLIENT_ON_SERVICE_DISCONNECTED_END = 48824342;
    public static final int LITE_HERO_SERVICE_CLIENT_ON_SERVICE_DISCONNECTED_START = 48824341;
    public static final int LITE_HERO_SERVICE_CLIENT_PREFETCH_END = 48824338;
    public static final int LITE_HERO_SERVICE_CLIENT_PREFETCH_START = 48824337;
    public static final short MODULE_ID = 745;
    public static final int VIDEO_PLAYER_CLIENT_ON_SURFACE_AVAILABLE_END = 48824332;
    public static final int VIDEO_PLAYER_CLIENT_ON_SURFACE_AVAILABLE_START = 48824331;
    public static final int VIDEO_PLAYER_CLIENT_ON_SURFACE_DESTROYED_END = 48824333;
    public static final int VIDEO_PLAYER_CLIENT_ON_SURFACE_DESTROYED_START = 48824334;
    public static final int VIDEO_PLAYER_CLIENT_PAUSE = 48824329;
    public static final int VIDEO_PLAYER_CLIENT_RESUME = 48824330;
    public static final int VIDEO_PLAYER_CLIENT_START = 48824327;
    public static final int VIDEO_PLAYER_CLIENT_START_END = 48824328;
    public static final int VIDEO_PLAYER_CLIENT_VERBOSE_DEBUG_END = 48824336;
    public static final int VIDEO_PLAYER_CLIENT_VERBOSE_DEBUG_START = 48824335;

    public static String getMarkerName(int i) {
        switch (i) {
            case 1:
                return "FBLITE_HEROPLAYER_AUTOPLAYER_LITE_HERO_SCROLLER_PERF_LOGGER_LOG_SERVICE_BIND_START";
            case 2:
                return "FBLITE_HEROPLAYER_AUTOPLAYER_LITE_HERO_SCROLLER_PERF_LOGGER_LOG_SERVICE_BIND_END";
            case 3:
                return "FBLITE_HEROPLAYER_AUTOPLAYER_LITE_HERO_SCROLLER_PERF_LOG_PLAYER_INIT_START";
            case 4:
                return "FBLITE_HEROPLAYER_AUTOPLAYER_LITE_HERO_SCROLLER_PERF_LOG_PLAYER_INIT_END";
            case 5:
                return "FBLITE_HEROPLAYER_AUTOPLAYER_LITE_HERO_SCROLLER_PERF_LOG_PLAYER_ACTION_END";
            case 6:
                return "FBLITE_HEROPLAYER_AUTOPLAYER_LITE_HERO_SCROLLER_PERF_LOG_PLAYER_ACTION_START";
            case 7:
                return "FBLITE_HEROPLAYER_AUTOPLAYER_VIDEO_PLAYER_CLIENT_START";
            case 8:
                return "FBLITE_HEROPLAYER_AUTOPLAYER_VIDEO_PLAYER_CLIENT_START_END";
            case 9:
                return "FBLITE_HEROPLAYER_AUTOPLAYER_VIDEO_PLAYER_CLIENT_PAUSE";
            case 10:
                return "FBLITE_HEROPLAYER_AUTOPLAYER_VIDEO_PLAYER_CLIENT_RESUME";
            case 11:
                return "FBLITE_HEROPLAYER_AUTOPLAYER_VIDEO_PLAYER_CLIENT_ON_SURFACE_AVAILABLE_START";
            case 12:
                return "FBLITE_HEROPLAYER_AUTOPLAYER_VIDEO_PLAYER_CLIENT_ON_SURFACE_AVAILABLE_END";
            case 13:
                return "FBLITE_HEROPLAYER_AUTOPLAYER_VIDEO_PLAYER_CLIENT_ON_SURFACE_DESTROYED_END";
            case 14:
                return "FBLITE_HEROPLAYER_AUTOPLAYER_VIDEO_PLAYER_CLIENT_ON_SURFACE_DESTROYED_START";
            case 15:
                return "FBLITE_HEROPLAYER_AUTOPLAYER_VIDEO_PLAYER_CLIENT_VERBOSE_DEBUG_START";
            case 16:
                return "FBLITE_HEROPLAYER_AUTOPLAYER_VIDEO_PLAYER_CLIENT_VERBOSE_DEBUG_END";
            case 17:
                return "FBLITE_HEROPLAYER_AUTOPLAYER_LITE_HERO_SERVICE_CLIENT_PREFETCH_START";
            case 18:
                return "FBLITE_HEROPLAYER_AUTOPLAYER_LITE_HERO_SERVICE_CLIENT_PREFETCH_END";
            case 19:
                return "FBLITE_HEROPLAYER_AUTOPLAYER_LITE_HERO_SERVICE_CLIENT_ON_SERVICE_CONNECTED_START";
            case 20:
                return "FBLITE_HEROPLAYER_AUTOPLAYER_LITE_HERO_SERVICE_CLIENT_ON_SERVICE_CONNECTED_END";
            case 21:
                return "FBLITE_HEROPLAYER_AUTOPLAYER_LITE_HERO_SERVICE_CLIENT_ON_SERVICE_DISCONNECTED_START";
            case 22:
                return "FBLITE_HEROPLAYER_AUTOPLAYER_LITE_HERO_SERVICE_CLIENT_ON_SERVICE_DISCONNECTED_END";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}