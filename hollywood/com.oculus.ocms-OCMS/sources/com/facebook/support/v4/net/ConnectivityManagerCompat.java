package com.facebook.support.v4.net;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ConnectivityManagerCompat {
    public NetworkInfo getNetworkInfoFromBroadcast(ConnectivityManager connectivityManager, Intent intent) {
        return androidx.core.net.ConnectivityManagerCompat.getNetworkInfoFromBroadcast(connectivityManager, intent);
    }

    public boolean isActiveNetworkMetered(ConnectivityManager connectivityManager) {
        return androidx.core.net.ConnectivityManagerCompat.isActiveNetworkMetered(connectivityManager);
    }
}
