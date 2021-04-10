package com.facebook.quicklog.identifiers;

public class WearableConnectivity {
    public static final int BT_CONNECTION = 1041956871;
    public static final int BT_CONNECTION_SESSION = 1041969814;
    public static final int BT_PAIRING = 1041956870;
    public static final int BT_PAIRING_SESSION = 1041971314;
    public static final int CONNECT_TO_REMOTE_SERVICE_SUSPEND = 1041965883;
    public static final int CONNECT_TO_REMOTE_SERVICE_THREADS = 1041961428;
    public static final int DATA_MANAGER_REQUEST_RESPONSE = 1041956866;
    public static final int DATA_MANAGER_REQUEST_WATERFALL = 1041970037;
    public static final int DATA_MANAGER_STREAM_REQUEST = 1041963928;
    public static final int DATA_TRANSFER_SYNC_REQUEST = 1041971407;
    public static final short MODULE_ID = 15899;
    public static final int WIFI_DIRECT_CONNECT = 1041956869;
    public static final int WIFI_DIRECT_HANDSHAKE = 1041956867;
    public static final int WIFI_DIRECT_OPEN_SOCKET_AND_SEND = 1041956868;
    public static final int WIFI_DIRECT_START_PEER_DISCOVERY = 1041956865;

    public static String getMarkerName(int i) {
        if (i == 4564) {
            return "WEARABLE_CONNECTIVITY_CONNECT_TO_REMOTE_SERVICE_THREADS";
        }
        if (i == 7064) {
            return "WEARABLE_CONNECTIVITY_DATA_MANAGER_STREAM_REQUEST";
        }
        if (i == 9019) {
            return "WEARABLE_CONNECTIVITY_CONNECT_TO_REMOTE_SERVICE_SUSPEND";
        }
        if (i == 12950) {
            return "WEARABLE_CONNECTIVITY_BT_CONNECTION_SESSION";
        }
        if (i == 13173) {
            return "WEARABLE_CONNECTIVITY_DATA_MANAGER_REQUEST_WATERFALL";
        }
        if (i == 14450) {
            return "WEARABLE_CONNECTIVITY_BT_PAIRING_SESSION";
        }
        if (i == 14543) {
            return "WEARABLE_CONNECTIVITY_DATA_TRANSFER_SYNC_REQUEST";
        }
        switch (i) {
            case 1:
                return "WEARABLE_CONNECTIVITY_WIFI_DIRECT_START_PEER_DISCOVERY";
            case 2:
                return "WEARABLE_CONNECTIVITY_DATA_MANAGER_REQUEST_RESPONSE";
            case 3:
                return "WEARABLE_CONNECTIVITY_WIFI_DIRECT_HANDSHAKE";
            case 4:
                return "WEARABLE_CONNECTIVITY_WIFI_DIRECT_OPEN_SOCKET_AND_SEND";
            case 5:
                return "WEARABLE_CONNECTIVITY_WIFI_DIRECT_CONNECT";
            case 6:
                return "WEARABLE_CONNECTIVITY_BT_PAIRING";
            case 7:
                return "WEARABLE_CONNECTIVITY_BT_CONNECTION";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
