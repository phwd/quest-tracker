package com.facebook.quicklog.identifiers;

public class ContactpointAcquisition {
    public static final int CCU_BACKGROUND_JOB_FUNNEL = 325925977;
    public static final int CONTACTS_UPLOAD_FUNNEL = 325923692;
    public static final int GMAIL_ACQUISITION_QP = 325914231;
    public static final short MODULE_ID = 4973;

    public static String getMarkerName(int i) {
        return i != 3703 ? i != 13164 ? i != 15449 ? "UNDEFINED_QPL_EVENT" : "CONTACTPOINT_ACQUISITION_CCU_BACKGROUND_JOB_FUNNEL" : "CONTACTPOINT_ACQUISITION_CONTACTS_UPLOAD_FUNNEL" : "CONTACTPOINT_ACQUISITION_GMAIL_ACQUISITION_QP";
    }
}
