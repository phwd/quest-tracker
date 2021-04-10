package com.facebook.quicklog.identifiers;

public class PagesLwiPerf {
    public static final int BIZAPP_HOME_ADS_CARD_TTI = 9910626;
    public static final int BOOST_POST_PICKER_TTI = 9895938;
    public static final int FETCH_PROMOTION_FROM_SERVER = 9895937;
    public static final int LWI_TTI = 9895943;
    public static final int LWI_TTRC = 9895944;
    public static final short MODULE_ID = 151;
    public static final int PAGES_LWI_PERF_PROMOTE_PAGE_TTI = 9895939;
    public static final int RN_LWI_BOOST_LOCAL_AWARENESS_TTI = 9895942;
    public static final int RN_LWI_BOOST_POST_TTI = 9895940;
    public static final int RN_LWI_BOOST_WEBSITE_TTI = 9895941;

    public static String getMarkerName(int i) {
        if (i == 14690) {
            return "PAGES_LWI_PERF_BIZAPP_HOME_ADS_CARD_TTI";
        }
        switch (i) {
            case 1:
                return "PAGES_LWI_PERF_FETCH_PROMOTION_FROM_SERVER";
            case 2:
                return "PAGES_LWI_PERF_BOOST_POST_PICKER_TTI";
            case 3:
                return "PAGES_LWI_PERF_PAGES_LWI_PERF_PROMOTE_PAGE_TTI";
            case 4:
                return "PAGES_LWI_PERF_RN_LWI_BOOST_POST_TTI";
            case 5:
                return "PAGES_LWI_PERF_RN_LWI_BOOST_WEBSITE_TTI";
            case 6:
                return "PAGES_LWI_PERF_RN_LWI_BOOST_LOCAL_AWARENESS_TTI";
            case 7:
                return "PAGES_LWI_PERF_LWI_TTI";
            case 8:
                return "PAGES_LWI_PERF_LWI_TTRC";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
