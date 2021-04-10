package com.oculus.util.network;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import javax.annotation.Nullable;

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

    @Nullable
    @SuppressLint({"MissingPermission"})
    public static NetworkInfo A00(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager != null) {
            return connectivityManager.getActiveNetworkInfo();
        }
        return null;
    }

    public static String A01(Context context) {
        NetworkInfo A00 = A00(context);
        if (A00 != null) {
            int type = A00.getType();
            int subtype = A00.getSubtype();
            if (type != 1) {
                if (type == 0) {
                    switch (subtype) {
                        case 1:
                        case 2:
                        case 4:
                        case 7:
                        case 11:
                            return CONNECTION_SPEED_SLOW;
                    }
                }
            }
            return CONNECTION_SPEED_FAST;
        }
        return "unknown";
    }

    public static String A02(Context context) {
        NetworkInfo A00 = A00(context);
        if (A00 == null || !A00.isConnected()) {
            return CONNECTION_DISCONNECTED;
        }
        if (A00.getType() == 1) {
            return WIFI;
        }
        if (A00.getType() != 0) {
            return CONNECTION_TYPE_UNKNOWN;
        }
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager == null) {
            return NETWORK_CLASS_UNKNOWN;
        }
        switch (telephonyManager.getNetworkType()) {
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
                return NETWORK_CLASS_2_G;
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
                return NETWORK_CLASS_3_G;
            case 13:
                return NETWORK_CLASS_4_G;
            default:
                return NETWORK_CLASS_UNKNOWN;
        }
    }
}
