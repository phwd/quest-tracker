package com.facebook.quicklog.identifiers;

public class BusinessCrm {
    public static final int BUSINESS_CRM_CONTACT_LIST_LOAD = 36503553;
    public static final int BUSINESS_CRM_CONTACT_LOAD = 36503554;
    public static final short MODULE_ID = 557;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? "UNDEFINED_QPL_EVENT" : "BUSINESS_CRM_BUSINESS_CRM_CONTACT_LOAD" : "BUSINESS_CRM_BUSINESS_CRM_CONTACT_LIST_LOAD";
    }
}
