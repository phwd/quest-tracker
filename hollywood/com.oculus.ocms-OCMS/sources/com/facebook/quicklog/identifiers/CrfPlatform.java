package com.facebook.quicklog.identifiers;

public class CrfPlatform {
    public static final int CRF_DATA_SENT_TO_UI = 36896776;
    public static final int CRF_INFRA_1ST_FETCH_ANDROID = 36896773;
    public static final int CRF_POOL_EDGE_RETRIEVAL = 36896780;
    public static final int CRF_RANKING_SIGNAL_CREATION_ANDROID = 36896774;
    public static final int CRF_RECENT_VPV_TEMP = 36896779;
    public static final int CRF_STORAGE_STATS = 36896772;
    public static final int CSR_STORAGE_INSERT_ANDROID = 36896785;
    public static final int EDGE_INFLATION_ANDROID = 36896770;
    public static final short MODULE_ID = 563;
    public static final int NETWORK_FETCH_LOGGER_ANDROID = 36896769;
    public static final int RANKING_SIGNAL_EXTRA_DATA_INFLATION = 36896775;
    public static final int RANKING_SIGNAL_EXTRA_DATA_VALIDATION = 36896778;

    public static String getMarkerName(int i) {
        switch (i) {
            case 1:
                return "CRF_PLATFORM_NETWORK_FETCH_LOGGER_ANDROID";
            case 2:
                return "CRF_PLATFORM_EDGE_INFLATION_ANDROID";
            case 3:
            case 9:
            case 13:
            case 14:
            case 15:
            case 16:
            default:
                return "UNDEFINED_QPL_EVENT";
            case 4:
                return "CRF_PLATFORM_CRF_STORAGE_STATS";
            case 5:
                return "CRF_PLATFORM_CRF_INFRA_1ST_FETCH_ANDROID";
            case 6:
                return "CRF_PLATFORM_CRF_RANKING_SIGNAL_CREATION_ANDROID";
            case 7:
                return "CRF_PLATFORM_RANKING_SIGNAL_EXTRA_DATA_INFLATION";
            case 8:
                return "CRF_PLATFORM_CRF_DATA_SENT_TO_UI";
            case 10:
                return "CRF_PLATFORM_RANKING_SIGNAL_EXTRA_DATA_VALIDATION";
            case 11:
                return "CRF_PLATFORM_CRF_RECENT_VPV_TEMP";
            case 12:
                return "CRF_PLATFORM_CRF_POOL_EDGE_RETRIEVAL";
            case 17:
                return "CRF_PLATFORM_CSR_STORAGE_INSERT_ANDROID";
        }
    }
}
