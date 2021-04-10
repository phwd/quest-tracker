package com.facebook.quicklog.identifiers;

public class QuicklogModuleForEToEDontTouch {
    public static final short MODULE_ID = 10512;
    public static final int QUICKLOG_EVENT_FOR_NATIVE_FUNNEL_TESTING = 688926599;
    public static final int WEB_FUNNEL_QPL_E2E_TEST = 688924705;

    public static String getMarkerName(int i) {
        return i != 10273 ? i != 12167 ? "UNDEFINED_QPL_EVENT" : "QUICKLOG_MODULE_FOR_E_TO_E_DONT_TOUCH_QUICKLOG_EVENT_FOR_NATIVE_FUNNEL_TESTING" : "QUICKLOG_MODULE_FOR_E_TO_E_DONT_TOUCH_WEB_FUNNEL_QPL_E2E_TEST";
    }
}
