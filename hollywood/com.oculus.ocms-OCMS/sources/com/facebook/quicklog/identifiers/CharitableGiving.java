package com.facebook.quicklog.identifiers;

public class CharitableGiving {
    public static final int FUNDRAISER_HUB_CREATE_RN_TTI = 13107203;
    public static final int FUNDRAISER_HUB_NT_TTI = 13118654;
    public static final int FUNDRAISER_HUB_RN_TTI = 13107202;
    public static final short MODULE_ID = 200;
    public static final int NONPROFIT_SELECTOR_RN_TTI = 13107201;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 11454 ? "UNDEFINED_QPL_EVENT" : "CHARITABLE_GIVING_FUNDRAISER_HUB_NT_TTI" : "CHARITABLE_GIVING_FUNDRAISER_HUB_CREATE_RN_TTI" : "CHARITABLE_GIVING_FUNDRAISER_HUB_RN_TTI" : "CHARITABLE_GIVING_NONPROFIT_SELECTOR_RN_TTI";
    }
}
