package com.facebook.quicklog.identifiers;

public class Jobs {
    public static final int JOB_ATS_TTRC = 33962328;
    public static final int JOB_BROWSER_NON_TAB_TTRC = 33953957;
    public static final int JOB_BROWSER_TTRC = 33947649;
    public static final int JOB_DETAIL_VIEW_TTRC = 33957396;
    public static final short MODULE_ID = 518;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 6309 ? i != 9748 ? i != 14680 ? "UNDEFINED_QPL_EVENT" : "JOBS_JOB_ATS_TTRC" : "JOBS_JOB_DETAIL_VIEW_TTRC" : "JOBS_JOB_BROWSER_NON_TAB_TTRC" : "JOBS_JOB_BROWSER_TTRC";
    }
}
