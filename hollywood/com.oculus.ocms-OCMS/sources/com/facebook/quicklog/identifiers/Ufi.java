package com.facebook.quicklog.identifiers;

public class Ufi {
    public static final int COMMENT_FLYOUT_TTRC = 3735607;
    public static final int DASH_FLYOUT_LOAD_CACHED = 3735565;
    public static final int DASH_FLYOUT_LOAD_NETWORK = 3735566;
    public static final int FLYOUT_NETWORK_TIME_EXECUTOR_FEEDBACK_ID = 3735568;
    public static final int FLYOUT_NETWORK_TIME_FEEDBACK_ID = 3735567;
    public static final int FLYOUT_NETWORK_TIME_PHOTO_ID = 3735569;
    public static final int LOAD_MORE_COMMENTS = 3735577;
    public static final short MODULE_ID = 57;
    public static final int NNF_FLYOUT_ANIMATION_ADJUSTED_WAIT_TIME = 3735578;
    public static final int NNF_FLYOUT_ANIMATION_TO_DATA_FETCH = 3735580;
    public static final int NNF_FLYOUT_ANIMATION_WAIT_TIME = 3735575;
    public static final int NNF_FLYOUT_BG_INFLATABLE_FEEDBACK_TOTAL_TIME = 3735584;
    public static final int NNF_FLYOUT_BG_INFLATION_TIME = 3735585;
    public static final int NNF_FLYOUT_FRAGMENT_CREATE_TIME = 3735562;
    public static final int NNF_FLYOUT_LOAD_COMPLETE_FLOW = 3735559;
    public static final int NNF_FLYOUT_LOAD_COMPLETE_FLOW_AND_RENDER = 3735560;
    public static final int NNF_FLYOUT_LOAD_COMPLETE_FLOW_TO_RENDER = 3735576;
    public static final int NNF_FLYOUT_LOAD_DB_CACHE = 3735553;
    public static final int NNF_FLYOUT_LOAD_DB_CACHE_AND_RENDER = 3735554;
    public static final int NNF_FLYOUT_LOAD_NETWORK = 3735555;
    public static final int NNF_FLYOUT_LOAD_NETWORK_AND_RENDER = 3735556;
    public static final int NNF_FLYOUT_LOAD_NETWORK_WITHOUT_CACHE = 3735557;
    public static final int NNF_FLYOUT_LOAD_NETWORK_WITHOUT_CACHE_AND_RENDER = 3735558;
    public static final int NNF_FLYOUT_LOAD_NETWORK_WITH_CACHE = 3735583;
    public static final int NNF_FLYOUT_ON_ACTIVITYCRAETED_TIME = 3735572;
    public static final int NNF_FLYOUT_ON_CREATEVIEW_TIME = 3735570;
    public static final int NNF_FLYOUT_ON_CREATE_TIME = 3735561;
    public static final int NNF_FLYOUT_ON_RESUME_TIME = 3735573;
    public static final int NNF_FLYOUT_ON_VIEWCREATED_TIME = 3735571;
    public static final int NNF_FLYOUT_RESUME_TO_ANIMATION_WAIT = 3735579;
    public static final int NNF_FLYOUT_RESUME_TO_RENDER_TIME = 3735574;
    public static final int PERF_MARKER_OPTIMISTIC_COMMENT = 3735582;
    public static final int PERF_MARKER_POST_COMMENT = 3735581;
    public static final int PHOTO_FLYOUT_LOAD_CACHED = 3735563;
    public static final int PHOTO_FLYOUT_LOAD_NETWORK = 3735564;
    public static final int SINGLELINECOMMENTCOMPOSERVIEW_INITIALIZATION = 3735588;
    public static final int THREADED_FLYOUT_LOAD_COMPLETE_FLOW_AND_RENDER = 3735587;

    public static String getMarkerName(int i) {
        if (i == 35) {
            return "UFI_THREADED_FLYOUT_LOAD_COMPLETE_FLOW_AND_RENDER";
        }
        if (i == 36) {
            return "UFI_SINGLELINECOMMENTCOMPOSERVIEW_INITIALIZATION";
        }
        if (i == 55) {
            return "UFI_COMMENT_FLYOUT_TTRC";
        }
        switch (i) {
            case 1:
                return "UFI_NNF_FLYOUT_LOAD_DB_CACHE";
            case 2:
                return "UFI_NNF_FLYOUT_LOAD_DB_CACHE_AND_RENDER";
            case 3:
                return "UFI_NNF_FLYOUT_LOAD_NETWORK";
            case 4:
                return "UFI_NNF_FLYOUT_LOAD_NETWORK_AND_RENDER";
            case 5:
                return "UFI_NNF_FLYOUT_LOAD_NETWORK_WITHOUT_CACHE";
            case 6:
                return "UFI_NNF_FLYOUT_LOAD_NETWORK_WITHOUT_CACHE_AND_RENDER";
            case 7:
                return "NNF_FlyoutLoadCompleteFlow";
            case 8:
                return "NNF_FlyoutLoadCompleteFlowAndRender";
            case 9:
                return "UFI_NNF_FLYOUT_ON_CREATE_TIME";
            case 10:
                return "UFI_NNF_FLYOUT_FRAGMENT_CREATE_TIME";
            case 11:
                return "UFI_PHOTO_FLYOUT_LOAD_CACHED";
            case 12:
                return "UFI_PHOTO_FLYOUT_LOAD_NETWORK";
            case 13:
                return "UFI_DASH_FLYOUT_LOAD_CACHED";
            case 14:
                return "UFI_DASH_FLYOUT_LOAD_NETWORK";
            case 15:
                return "UFI_FLYOUT_NETWORK_TIME_FEEDBACK_ID";
            case 16:
                return "UFI_FLYOUT_NETWORK_TIME_EXECUTOR_FEEDBACK_ID";
            case 17:
                return "UFI_FLYOUT_NETWORK_TIME_PHOTO_ID";
            case 18:
                return "UFI_NNF_FLYOUT_ON_CREATEVIEW_TIME";
            case 19:
                return "UFI_NNF_FLYOUT_ON_VIEWCREATED_TIME";
            case 20:
                return "UFI_NNF_FLYOUT_ON_ACTIVITYCRAETED_TIME";
            case 21:
                return "UFI_NNF_FLYOUT_ON_RESUME_TIME";
            case 22:
                return "UFI_NNF_FLYOUT_RESUME_TO_RENDER_TIME";
            case 23:
                return "UFI_NNF_FLYOUT_ANIMATION_WAIT_TIME";
            case 24:
                return "UFI_NNF_FLYOUT_LOAD_COMPLETE_FLOW_TO_RENDER";
            case 25:
                return "UfiLoadMoreComments";
            case 26:
                return "UFI_NNF_FLYOUT_ANIMATION_ADJUSTED_WAIT_TIME";
            case 27:
                return "UFI_NNF_FLYOUT_RESUME_TO_ANIMATION_WAIT";
            case 28:
                return "UFI_NNF_FLYOUT_ANIMATION_TO_DATA_FETCH";
            case 29:
                return "UfiFuturesPostComment";
            case 30:
                return "UFI_PERF_MARKER_OPTIMISTIC_COMMENT";
            case 31:
                return "NNF_FlyoutLoadNetworkWithCache";
            case 32:
                return "UFI_NNF_FLYOUT_BG_INFLATABLE_FEEDBACK_TOTAL_TIME";
            case 33:
                return "UFI_NNF_FLYOUT_BG_INFLATION_TIME";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
