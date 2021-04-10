package com.facebook.quicklog.identifiers;

public class WearableDataApp {
    public static final int DATA_MANAGER_REQUEST_RESPONSE = 200870386;
    public static final short MODULE_ID = 3065;
    public static final int WIFI_DIRECT_CONNECT = 200881979;
    public static final int WIFI_DIRECT_HANDSHAKE = 200873590;
    public static final int WIFI_DIRECT_OPEN_SOCKET_AND_SEND = 200879620;
    public static final int WIFI_DIRECT_START_PEER_DISCOVERY = 200869478;

    public static String getMarkerName(int i) {
        return i != 1638 ? i != 2546 ? i != 5750 ? i != 11780 ? i != 14139 ? "UNDEFINED_QPL_EVENT" : "WEARABLE_DATA_APP_WIFI_DIRECT_CONNECT" : "WEARABLE_DATA_APP_WIFI_DIRECT_OPEN_SOCKET_AND_SEND" : "WEARABLE_DATA_APP_WIFI_DIRECT_HANDSHAKE" : "WEARABLE_DATA_APP_DATA_MANAGER_REQUEST_RESPONSE" : "WEARABLE_DATA_APP_WIFI_DIRECT_START_PEER_DISCOVERY";
    }
}
