package org.chromium.base;

import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Build;
import android.os.Process;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RadioUtils {

    /* renamed from: a  reason: collision with root package name */
    public static Boolean f10594a;
    public static Boolean b;

    public static int getCellDataActivity() {
        try {
            return ((TelephonyManager) ContextUtils.getApplicationContext().getSystemService("phone")).getDataActivity();
        } catch (SecurityException unused) {
            return -1;
        }
    }

    public static int getCellSignalLevel() {
        try {
            SignalStrength signalStrength = ((TelephonyManager) ContextUtils.getApplicationContext().getSystemService("phone")).getSignalStrength();
            if (signalStrength != null) {
                return signalStrength.getLevel();
            }
            return -1;
        } catch (SecurityException unused) {
            return -1;
        }
    }

    public static boolean isSupported() {
        if (Build.VERSION.SDK_INT >= 28) {
            if (f10594a == null) {
                f10594a = Boolean.valueOf(AbstractC3153j7.a(ContextUtils.getApplicationContext(), "android.permission.ACCESS_NETWORK_STATE", Process.myPid(), Process.myUid()) == 0);
            }
            if (f10594a.booleanValue()) {
                if (b == null) {
                    b = Boolean.valueOf(AbstractC3153j7.a(ContextUtils.getApplicationContext(), "android.permission.ACCESS_WIFI_STATE", Process.myPid(), Process.myUid()) == 0);
                }
                if (b.booleanValue()) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isWifiConnected() {
        NetworkCapabilities networkCapabilities;
        ConnectivityManager connectivityManager = (ConnectivityManager) ContextUtils.getApplicationContext().getSystemService("connectivity");
        Network activeNetwork = connectivityManager.getActiveNetwork();
        if (activeNetwork == null || (networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)) == null) {
            return false;
        }
        return networkCapabilities.hasTransport(1);
    }
}
