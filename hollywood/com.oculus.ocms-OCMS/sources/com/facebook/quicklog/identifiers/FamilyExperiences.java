package com.facebook.quicklog.identifiers;

import com.facebook.ultralight.UL;

public class FamilyExperiences {
    public static final int ACCESS_LIBRARY_DELETE = 857814851;
    public static final int ACCESS_LIBRARY_FETCH = 857814189;
    public static final int ACCESS_LIBRARY_UPDATE = 857814589;
    public static final int AUTH = 857808154;
    public static final int CAL_FLOW = 857806791;
    public static final int CAL_INIT = 857802969;
    public static final int CHANGE_ACCOUNTS = 857815852;
    public static final int CP_PHOTOSYNC_SCREEN_LOAD = 857816459;
    public static final int DECAL_DISCLOSURES_SCREEN_LOAD = 857809136;
    public static final int DECAL_FLOW = 857813060;
    public static final int DECAL_INIT = 857805867;
    public static final int DECAL_MUTATION = 857803267;
    public static final int DECAL_SCREEN_LOAD = 857812687;
    public static final int DISCLOSURES_SCREEN_LOAD = 857805039;
    public static final int DO_LINK_MUTATION = 857802604;
    public static final int DO_UNLINK_MUTATION = 857804516;
    public static final int IDENTITY_SCREEN_SELECTOR_LOAD = 857811299;
    public static final int LINK_ACCOUNTS_SCREEN_LOAD = 857807376;
    public static final int LINK_MUTATION = 857810846;
    public static final short MODULE_ID = 13089;
    public static final int NATIVE_AUTH = 857802754;
    public static final int TS_CAL_SCREEN = 857801778;
    public static final int TS_DECAL_DISCLOSURES_SCREEN = 857811434;
    public static final int TS_DECAL_SCREEN = 857816559;
    public static final int TS_DISCLOSURES_SCREEN = 857809098;
    public static final int TS_IDENTITY_SELECTION_SCREEN = 857805950;
    public static final int TS_WEB_AUTH_PRESCREEN = 857809056;
    public static final int WEB_AUTH = 857807165;
    public static final int WEB_AUTH_PRE_SCREEN = 857808598;

    public static String getMarkerName(int i) {
        switch (i) {
            case 1074:
                return "FAMILY_EXPERIENCES_TS_CAL_SCREEN";
            case 1900:
                return "FAMILY_EXPERIENCES_DO_LINK_MUTATION";
            case UL.id._UL__ULSEP_com_facebook_inject_ContextScope_ULSEP_BINDING_ID /*{ENCODED_INT: 2050}*/:
                return "FAMILY_EXPERIENCES_NATIVE_AUTH";
            case 2265:
                return "FAMILY_EXPERIENCES_CAL_INIT";
            case 2563:
                return "FAMILY_EXPERIENCES_DECAL_MUTATION";
            case 3812:
                return "FAMILY_EXPERIENCES_DO_UNLINK_MUTATION";
            case 4335:
                return "FAMILY_EXPERIENCES_DISCLOSURES_SCREEN_LOAD";
            case 5163:
                return "FAMILY_EXPERIENCES_DECAL_INIT";
            case 5246:
                return "FAMILY_EXPERIENCES_TS_IDENTITY_SELECTION_SCREEN";
            case 6087:
                return "FAMILY_EXPERIENCES_CAL_FLOW";
            case 6461:
                return "FAMILY_EXPERIENCES_WEB_AUTH";
            case 6672:
                return "FAMILY_EXPERIENCES_LINK_ACCOUNTS_SCREEN_LOAD";
            case 7450:
                return "FAMILY_EXPERIENCES_AUTH";
            case 7894:
                return "FAMILY_EXPERIENCES_WEB_AUTH_PRE_SCREEN";
            case 8352:
                return "FAMILY_EXPERIENCES_TS_WEB_AUTH_PRESCREEN";
            case 8394:
                return "FAMILY_EXPERIENCES_TS_DISCLOSURES_SCREEN";
            case 8432:
                return "FAMILY_EXPERIENCES_DECAL_DISCLOSURES_SCREEN_LOAD";
            case 10142:
                return "FAMILY_EXPERIENCES_LINK_MUTATION";
            case 10595:
                return "FAMILY_EXPERIENCES_IDENTITY_SCREEN_SELECTOR_LOAD";
            case 10730:
                return "FAMILY_EXPERIENCES_TS_DECAL_DISCLOSURES_SCREEN";
            case 11983:
                return "FAMILY_EXPERIENCES_DECAL_SCREEN_LOAD";
            case 12356:
                return "FAMILY_EXPERIENCES_DECAL_FLOW";
            case 13485:
                return "FAMILY_EXPERIENCES_ACCESS_LIBRARY_FETCH";
            case 13885:
                return "FAMILY_EXPERIENCES_ACCESS_LIBRARY_UPDATE";
            case 14147:
                return "FAMILY_EXPERIENCES_ACCESS_LIBRARY_DELETE";
            case 15148:
                return "FAMILY_EXPERIENCES_CHANGE_ACCOUNTS";
            case 15755:
                return "FAMILY_EXPERIENCES_CP_PHOTOSYNC_SCREEN_LOAD";
            case 15855:
                return "FAMILY_EXPERIENCES_TS_DECAL_SCREEN";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
