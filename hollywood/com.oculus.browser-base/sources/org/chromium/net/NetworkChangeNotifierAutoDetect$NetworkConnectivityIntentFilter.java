package org.chromium.net;

import android.content.IntentFilter;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class NetworkChangeNotifierAutoDetect$NetworkConnectivityIntentFilter extends IntentFilter {
    public NetworkChangeNotifierAutoDetect$NetworkConnectivityIntentFilter() {
        addAction("android.net.conn.CONNECTIVITY_CHANGE");
    }
}
