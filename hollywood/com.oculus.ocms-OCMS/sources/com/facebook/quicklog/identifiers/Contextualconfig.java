package com.facebook.quicklog.identifiers;

public class Contextualconfig {
    public static final int CC_CONTEXT = 37945347;
    public static final int CC_INIT = 37945345;
    public static final int CC_RESOLVE = 37945346;
    public static final int CC_RESOLVE_DENSE = 37945349;
    public static final int CC_RESOLVE_RESOLVED = 37945350;
    public static final int CC_RESOLVE_TABLE = 37945348;
    public static final short MODULE_ID = 579;

    public static String getMarkerName(int i) {
        switch (i) {
            case 1:
                return "CONTEXTUALCONFIG_CC_INIT";
            case 2:
                return "CONTEXTUALCONFIG_CC_RESOLVE";
            case 3:
                return "CONTEXTUALCONFIG_CC_CONTEXT";
            case 4:
                return "CONTEXTUALCONFIG_CC_RESOLVE_TABLE";
            case 5:
                return "CONTEXTUALCONFIG_CC_RESOLVE_DENSE";
            case 6:
                return "CONTEXTUALCONFIG_CC_RESOLVE_RESOLVED";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
