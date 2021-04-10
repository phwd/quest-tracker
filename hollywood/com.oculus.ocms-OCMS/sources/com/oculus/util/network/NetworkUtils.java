package com.oculus.util.network;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import com.facebook.debug.log.BLog;
import java.io.IOException;
import java.net.InetAddress;
import javax.annotation.Nullable;

public class NetworkUtils {
    private static final String CONNECTION_DISCONNECTED = "connection_disconnected";
    private static final String CONNECTION_SPEED_FAST = "fast";
    private static final String CONNECTION_SPEED_SLOW = "slow";
    private static final String CONNECTION_SPEED_UNKNOWN = "unknown";
    private static final String CONNECTION_TYPE_UNKNOWN = "connection_unknown";
    private static final String NETWORK_CLASS_2_G = "network_2g";
    private static final String NETWORK_CLASS_3_G = "network_3g";
    private static final String NETWORK_CLASS_4_G = "network_4g";
    private static final String NETWORK_CLASS_UNKNOWN = "network_unknown";
    private static final String TAG = "NetworkUtils";
    private static final String WIFI = "wifi";

    @Nullable
    @SuppressLint({"MissingPermission"})
    private static NetworkInfo getActiveNetworkInfo(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager != null) {
            return connectivityManager.getActiveNetworkInfo();
        }
        return null;
    }

    public static boolean isConnected(Context context) {
        NetworkInfo activeNetworkInfo = getActiveNetworkInfo(context);
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static boolean isAvailable(Context context) {
        NetworkInfo activeNetworkInfo = getActiveNetworkInfo(context);
        return activeNetworkInfo != null && activeNetworkInfo.isAvailable();
    }

    public static String getConnectionType(Context context) {
        NetworkInfo activeNetworkInfo = getActiveNetworkInfo(context);
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            return CONNECTION_DISCONNECTED;
        }
        if (activeNetworkInfo.getType() == 1) {
            return WIFI;
        }
        return activeNetworkInfo.getType() == 0 ? getNetworkClass(context) : CONNECTION_TYPE_UNKNOWN;
    }

    public static String getConnectionSpeed(Context context) {
        NetworkInfo activeNetworkInfo = getActiveNetworkInfo(context);
        if (activeNetworkInfo == null) {
            return "unknown";
        }
        int type = activeNetworkInfo.getType();
        int subtype = activeNetworkInfo.getSubtype();
        if (type == 1) {
            return CONNECTION_SPEED_FAST;
        }
        if (type != 0) {
            return "unknown";
        }
        switch (subtype) {
            case 1:
            case 2:
                return CONNECTION_SPEED_SLOW;
            case 3:
                return CONNECTION_SPEED_FAST;
            case 4:
                return CONNECTION_SPEED_SLOW;
            case 5:
            case 6:
                return CONNECTION_SPEED_FAST;
            case 7:
                return CONNECTION_SPEED_SLOW;
            case 8:
            case 9:
            case 10:
                return CONNECTION_SPEED_FAST;
            case 11:
                return CONNECTION_SPEED_SLOW;
            case 12:
            case 13:
            case 14:
            case 15:
                return CONNECTION_SPEED_FAST;
            default:
                return "unknown";
        }
    }

    public static boolean isRoamingConnection(Context context) {
        NetworkInfo activeNetworkInfo = getActiveNetworkInfo(context);
        if (activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting() && activeNetworkInfo.isRoaming()) {
            return true;
        }
        return false;
    }

    private static String getNetworkClass(Context context) {
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

    public static boolean isWifiConnected(Context context) {
        NetworkInfo activeNetworkInfo;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null || !activeNetworkInfo.isConnected()) {
            return false;
        }
        return true;
    }

    public static boolean isOculusAddressReachable() {
        try {
            InetAddress byName = InetAddress.getByName("oculus.com");
            if (byName == null || byName.getAddress() == null || byName.getAddress().length <= 0) {
                return false;
            }
            return true;
        } catch (IOException e) {
            BLog.e(TAG, "isConnectedToInternet IOException", e);
            return false;
        }
    }
}
