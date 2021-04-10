package org.chromium.chrome.browser.sharing;

import android.telephony.TelephonyManager;
import org.chromium.base.ContextUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SharingJNIBridge {
    public static boolean isTelephonySupported() {
        return ((TelephonyManager) ContextUtils.getApplicationContext().getSystemService("phone")).getPhoneType() != 0;
    }
}
