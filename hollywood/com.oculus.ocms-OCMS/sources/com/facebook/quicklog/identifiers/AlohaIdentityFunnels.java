package com.facebook.quicklog.identifiers;

public class AlohaIdentityFunnels {
    public static final int ALOHA_ACCOUNTS_REMOVAL_FUNNEL_EVENT = 214110509;
    public static final int ALOHA_ADD_ACCOUNT_FUNNEL_EVENT = 214111404;
    public static final int ALOHA_ADD_OWNER_FUNNEL_EVENT = 214114427;
    public static final int ALOHA_GLOBAL_PICKER_FUNNEL = 214117411;
    public static final int ALOHA_GLOBAL_PICKER_FUNNEL_EVENT = 214119546;
    public static final int ALOHA_NAME_YOUR_ALOHA_FUNNEL_EVENT = 214113745;
    public static final int ALOHA_OOBE_IDENTITY_FUNNEL_EVENT = 214122147;
    public static final int ALOHA_OPEN_ACCESS_ENROLLMENT_FUNNEL_EVENT = 214116273;
    public static final short MODULE_ID = 3267;

    public static String getMarkerName(int i) {
        return i != 4397 ? i != 5292 ? i != 7633 ? i != 8315 ? i != 10161 ? i != 11299 ? i != 13434 ? i != 16035 ? "UNDEFINED_QPL_EVENT" : "ALOHA_IDENTITY_FUNNELS_ALOHA_OOBE_IDENTITY_FUNNEL_EVENT" : "ALOHA_IDENTITY_FUNNELS_ALOHA_GLOBAL_PICKER_FUNNEL_EVENT" : "ALOHA_IDENTITY_FUNNELS_ALOHA_GLOBAL_PICKER_FUNNEL" : "ALOHA_IDENTITY_FUNNELS_ALOHA_OPEN_ACCESS_ENROLLMENT_FUNNEL_EVENT" : "ALOHA_IDENTITY_FUNNELS_ALOHA_ADD_OWNER_FUNNEL_EVENT" : "ALOHA_IDENTITY_FUNNELS_ALOHA_NAME_YOUR_ALOHA_FUNNEL_EVENT" : "ALOHA_IDENTITY_FUNNELS_ALOHA_ADD_ACCOUNT_FUNNEL_EVENT" : "ALOHA_IDENTITY_FUNNELS_ALOHA_ACCOUNTS_REMOVAL_FUNNEL_EVENT";
    }
}
