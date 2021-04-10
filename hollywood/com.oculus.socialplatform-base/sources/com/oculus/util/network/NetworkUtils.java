package com.oculus.util.network;

import X.AnonymousClass0MD;
import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import com.oculus.vrshell.notifications.NotificationUri;
import java.io.IOException;
import java.net.InetAddress;
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
    public static NetworkInfo getActiveNetworkInfo(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager != null) {
            return connectivityManager.getActiveNetworkInfo();
        }
        return null;
    }

    public static String getNetworkClass(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(NotificationUri.PHONE);
        if (telephonyManager != null) {
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
            }
        }
        return NETWORK_CLASS_UNKNOWN;
    }

    public static boolean isOculusAddressReachable() {
        try {
            InetAddress byName = InetAddress.getByName("oculus.com");
            if (!(byName == null || byName.getAddress() == null)) {
                if (byName.getAddress().length > 0) {
                    return true;
                }
            }
        } catch (IOException e) {
            AnonymousClass0MD.A07(TAG, "isConnectedToInternet IOException", e);
        }
        return false;
    }

    public static boolean isWifiConnected(Context context) {
        NetworkInfo activeNetworkInfo;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null || !activeNetworkInfo.isConnected()) {
            return false;
        }
        return true;
    }

    public static String getConnectionSpeed(Context context) {
        NetworkInfo activeNetworkInfo = getActiveNetworkInfo(context);
        if (activeNetworkInfo != null) {
            int type = activeNetworkInfo.getType();
            int subtype = activeNetworkInfo.getSubtype();
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

    public static String getConnectionType(Context context) {
        NetworkInfo activeNetworkInfo = getActiveNetworkInfo(context);
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            return CONNECTION_DISCONNECTED;
        }
        if (activeNetworkInfo.getType() == 1) {
            return WIFI;
        }
        if (activeNetworkInfo.getType() == 0) {
            return getNetworkClass(context);
        }
        return CONNECTION_TYPE_UNKNOWN;
    }

    public static boolean isAvailable(Context context) {
        NetworkInfo activeNetworkInfo = getActiveNetworkInfo(context);
        if (activeNetworkInfo == null || !activeNetworkInfo.isAvailable()) {
            return false;
        }
        return true;
    }

    public static boolean isConnected(Context context) {
        NetworkInfo activeNetworkInfo = getActiveNetworkInfo(context);
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            return false;
        }
        return true;
    }

    public static boolean isRoamingConnection(Context context) {
        NetworkInfo activeNetworkInfo = getActiveNetworkInfo(context);
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnectedOrConnecting() || !activeNetworkInfo.isRoaming()) {
            return false;
        }
        return true;
    }
}
