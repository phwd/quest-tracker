package com.facebook.quicklog.identifiers;

public class ScrollPerf {
    public static final int GAMING_SCROLLING = 16000932;
    public static final int IG_SCROLL_PERF = 15990796;
    public static final short MODULE_ID = 244;
    public static final int NEWSFEED_SCROLLING = 15990789;
    public static final int NEWSFEED_SCROLLING_DIAG = 15990794;
    public static final int NEWSFEED_SCROLL_PERF = 15990795;
    public static final int OTHER_SURFACE_SCROLLING = 15990790;
    public static final int OTHER_SURFACE_SCROLLING_DIAG = 15990793;
    public static final int SURFACE_SCROLLING = 15995132;
    public static final int TOTAL_LARGE_FRAME_DROPS = 15990787;
    public static final int TOTAL_SINGLE_FRAME_DROPS = 15990786;
    public static final int TOTAL_SINGLE_FRAME_DROPS_FROM_PARTIAL = 15990788;
    public static final int TOTAL_TIME = 15990785;

    public static String getMarkerName(int i) {
        if (i == 4348) {
            return "SCROLL_PERF_SURFACE_SCROLLING";
        }
        if (i == 10148) {
            return "SCROLL_PERF_GAMING_SCROLLING";
        }
        switch (i) {
            case 1:
                return "SCROLL_PERF_TOTAL_TIME";
            case 2:
                return "SCROLL_PERF_TOTAL_SINGLE_FRAME_DROPS";
            case 3:
                return "SCROLL_PERF_TOTAL_LARGE_FRAME_DROPS";
            case 4:
                return "SCROLL_PERF_TOTAL_SINGLE_FRAME_DROPS_FROM_PARTIAL";
            case 5:
                return "SCROLL_PERF_NEWSFEED_SCROLLING";
            case 6:
                return "SCROLL_PERF_OTHER_SURFACE_SCROLLING";
            default:
                switch (i) {
                    case 9:
                        return "SCROLL_PERF_OTHER_SURFACE_SCROLLING_DIAG";
                    case 10:
                        return "SCROLL_PERF_NEWSFEED_SCROLLING_DIAG";
                    case 11:
                        return "SCROLL_PERF_NEWSFEED_SCROLL_PERF";
                    case 12:
                        return "SCROLL_PERF_IG_SCROLL_PERF";
                    default:
                        return "UNDEFINED_QPL_EVENT";
                }
        }
    }
}
