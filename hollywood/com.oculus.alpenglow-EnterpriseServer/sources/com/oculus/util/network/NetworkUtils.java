package com.oculus.util.network;

import X.AnonymousClass0NK;
import java.io.IOException;
import java.net.InetAddress;

public class NetworkUtils {
    public static final String CONNECTION_DISCONNECTED = "connection_disconnected";
    public static final String CONNECTION_SPEED_FAST = "fast";
    public static final String CONNECTION_SPEED_SLOW = "slow";
    public static final String CONNECTION_SPEED_UNKNOWN = "unknown";
    public static final String CONNECTION_TYPE_UNKNOWN = "connection_unknown";
    public static final String NETWORK_CLASS_2_G = "network_2g";
    public static final String NETWORK_CLASS_3_G = "network_3g";
    public static final String NETWORK_CLASS_4_G = "network_4g";
    public static final String NETWORK_CLASS_UNKNOWN = "network_unknown";
    public static final String TAG = "NetworkUtils";
    public static final String WIFI = "wifi";

    public static boolean A00() {
        try {
            InetAddress byName = InetAddress.getByName("oculus.com");
            if (!(byName == null || byName.getAddress() == null)) {
                if (byName.getAddress().length > 0) {
                    return true;
                }
            }
        } catch (IOException e) {
            AnonymousClass0NK.A04(TAG, "isConnectedToInternet IOException", e);
        }
        return false;
    }
}
