package com.oculus.alpenglow.os;

import X.AnonymousClass006;
import X.AnonymousClass0NK;
import X.AnonymousClass0R6;
import X.AnonymousClass0R7;
import com.facebook.infer.annotation.Nullsafe;
import com.oculus.alpenglow.constants.Constants;
import com.oculus.security.basecomponent.OculusSystemSecureBroadcastReceiver;
import java.io.IOException;
import java.net.InetAddress;
import java.util.concurrent.TimeUnit;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class NetworkCheck extends OculusSystemSecureBroadcastReceiver implements AnonymousClass0R6 {
    public static final String TAG = AnonymousClass006.A05(Constants.TAG_PREFIX, "NetworkCheck");
    public static final int WIFI_CHECK_RETRY_COUNT = 5;
    public static final long WIFI_CHECK_RETRY_INTERVAL = TimeUnit.SECONDS.toMillis(5);
    public AnonymousClass0R7 _UL_mInjectionContext;

    public NetworkCheck() {
        super("android.net.conn.CONNECTIVITY_CHANGE");
    }

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
