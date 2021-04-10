package com.facebook.quicklog.identifiers;

public class Reaction {
    public static final int ATTACHMENT_STYLE_MAPPER = 1966087;
    public static final int COMPONENT_STYLE_MAPPER = 1966086;
    public static final int LOCAL_SERP_INITIAL_NETWORK_WAIT_TIME = 1966094;
    public static final int LOCAL_SERP_INITIAL_WAIT_TIME = 1966092;
    public static final short MODULE_ID = 30;
    public static final int PRIOR_REACTION_LOAD_TIME = 1966090;
    public static final int REACTION_DIALOG_WAIT_TIME = 1966082;
    public static final int REACTION_INITIAL_CACHE_WAIT_TIME = 1966095;
    public static final int REACTION_INITIAL_NETWORK_WAIT_TIME = 1966088;
    public static final int REACTION_INITIAL_RENDER_WAIT_TIME = 1966089;
    public static final int REACTION_MULTI_ROW_RENDER_TIME = 1966091;
    public static final int REACTION_OVERLAY_DISPLAY = 1966081;
    public static final int REACTION_PAGE_WAIT_TIME = 1966084;
    public static final int REACTION_PLACETIPS_SIMPLE_FETCH_TIME = 1966085;
    public static final int REACTION_PLACETIPS_SUGGESTIFIER_COMBINED_FETCH_TIME = 1966083;
    public static final int REACTION_SPINNER_VISIBLE_TIME = 1966093;

    public static String getMarkerName(int i) {
        switch (i) {
            case 1:
                return "REACTION_REACTION_OVERLAY_DISPLAY";
            case 2:
                return "REACTION_REACTION_DIALOG_WAIT_TIME";
            case 3:
                return "REACTION_REACTION_PLACETIPS_SUGGESTIFIER_COMBINED_FETCH_TIME";
            case 4:
                return "REACTION_REACTION_PAGE_WAIT_TIME";
            case 5:
                return "REACTION_REACTION_PLACETIPS_SIMPLE_FETCH_TIME";
            case 6:
                return "REACTION_COMPONENT_STYLE_MAPPER";
            case 7:
                return "REACTION_ATTACHMENT_STYLE_MAPPER";
            case 8:
                return "REACTION_REACTION_INITIAL_NETWORK_WAIT_TIME";
            case 9:
                return "REACTION_REACTION_INITIAL_RENDER_WAIT_TIME";
            case 10:
                return "REACTION_PRIOR_REACTION_LOAD_TIME";
            case 11:
                return "REACTION_REACTION_MULTI_ROW_RENDER_TIME";
            case 12:
                return "REACTION_LOCAL_SERP_INITIAL_WAIT_TIME";
            case 13:
                return "REACTION_REACTION_SPINNER_VISIBLE_TIME";
            case 14:
                return "REACTION_LOCAL_SERP_INITIAL_NETWORK_WAIT_TIME";
            case 15:
                return "REACTION_REACTION_INITIAL_CACHE_WAIT_TIME";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
