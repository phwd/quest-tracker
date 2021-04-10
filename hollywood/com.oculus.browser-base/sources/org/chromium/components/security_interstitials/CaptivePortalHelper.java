package org.chromium.components.security_interstitials;

import android.net.ConnectivityManager;
import android.net.Network;
import java.lang.reflect.InvocationTargetException;
import org.chromium.base.ContextUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class CaptivePortalHelper {
    public static String getCaptivePortalServerUrl() {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) ContextUtils.getApplicationContext().getSystemService("connectivity");
            return (String) connectivityManager.getClass().getMethod("getCaptivePortalServerUrl", new Class[0]).invoke(connectivityManager, new Object[0]);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
            return "http://connectivitycheck.gstatic.com/generate_204";
        }
    }

    public static void reportNetworkConnectivity() {
        ConnectivityManager connectivityManager = (ConnectivityManager) ContextUtils.getApplicationContext().getSystemService("connectivity");
        Network[] allNetworks = connectivityManager.getAllNetworks();
        for (Network network : allNetworks) {
            connectivityManager.reportNetworkConnectivity(network, true);
            connectivityManager.reportNetworkConnectivity(network, false);
        }
    }
}
