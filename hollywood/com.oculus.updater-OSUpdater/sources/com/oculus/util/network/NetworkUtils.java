package com.oculus.util.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.facebook.debug.log.BLog;
import java.io.IOException;
import java.net.InetAddress;

public class NetworkUtils {
    private static final String TAG = "NetworkUtils";

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
