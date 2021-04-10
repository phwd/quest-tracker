package com.facebook.quicklog.identifiers;

public class ArDelivery {
    public static final int ASSET_FETCH_PREFETCH = 22413315;
    public static final int ASSET_FETCH_USER_REQUEST = 22413316;
    public static final int EFFECT_LOAD_PREFETCH = 22413314;
    public static final int EFFECT_LOAD_USER_REQUEST = 22413313;
    public static final int EFFECT_MANAGER_INIT = 22413319;
    public static final int MODEL_FETCH_PREFETCH = 22413318;
    public static final int MODEL_FETCH_USER_REQUEST = 22413317;
    public static final short MODULE_ID = 342;

    public static String getMarkerName(int i) {
        switch (i) {
            case 1:
                return "AR_DELIVERY_EFFECT_LOAD_USER_REQUEST";
            case 2:
                return "AR_DELIVERY_EFFECT_LOAD_PREFETCH";
            case 3:
                return "AR_DELIVERY_ASSET_FETCH_PREFETCH";
            case 4:
                return "AR_DELIVERY_ASSET_FETCH_USER_REQUEST";
            case 5:
                return "AR_DELIVERY_MODEL_FETCH_USER_REQUEST";
            case 6:
                return "AR_DELIVERY_MODEL_FETCH_PREFETCH";
            case 7:
                return "AR_DELIVERY_EFFECT_MANAGER_INIT";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
