package com.facebook.quicklog.identifiers;

public class Maps {
    public static final int DRAWER_CONTROLLER_INIT = 19136529;
    public static final int MAPVIEW_FULLY_LOADED = 19136523;
    public static final int MAPVIEW_GET_MAP_ASYNC = 19136522;
    public static final int MAPVIEW_GET_MAP_SYNC = 19136521;
    public static final int MAPVIEW_ONCREATE = 19136513;
    public static final int MAPVIEW_ONRESUME = 19136515;
    public static final int MAPVIEW_ONSTART = 19136514;
    public static final int MAP_CONTROLLER_INIT = 19136528;
    public static final int MAP_DRAWER_INIT = 19136527;
    public static final short MODULE_ID = 292;
    public static final int PINMANAGER_ADD_FEATURES = 19136518;
    public static final int PINMANAGER_INIT = 19136516;
    public static final int PINMANAGER_SELECT_PIN = 19136519;
    public static final int PINMANAGER_SET_GEOSJON = 19136517;
    public static final int RECOMMENDATIONS_INIT_ICONS = 19136520;

    public static String getMarkerName(int i) {
        switch (i) {
            case 1:
                return "MAPS_MAPVIEW_ONCREATE";
            case 2:
                return "MAPS_MAPVIEW_ONSTART";
            case 3:
                return "MAPS_MAPVIEW_ONRESUME";
            case 4:
                return "MAPS_PINMANAGER_INIT";
            case 5:
                return "MAPS_PINMANAGER_SET_GEOSJON";
            case 6:
                return "MAPS_PINMANAGER_ADD_FEATURES";
            case 7:
                return "MAPS_PINMANAGER_SELECT_PIN";
            case 8:
                return "MAPS_RECOMMENDATIONS_INIT_ICONS";
            case 9:
                return "MAPS_MAPVIEW_GET_MAP_SYNC";
            case 10:
                return "MAPS_MAPVIEW_GET_MAP_ASYNC";
            case 11:
                return "MAPS_MAPVIEW_FULLY_LOADED";
            case 12:
            case 13:
            case 14:
            default:
                return "UNDEFINED_QPL_EVENT";
            case 15:
                return "MAPS_MAP_DRAWER_INIT";
            case 16:
                return "MAPS_MAP_CONTROLLER_INIT";
            case 17:
                return "MAPS_DRAWER_CONTROLLER_INIT";
        }
    }
}
