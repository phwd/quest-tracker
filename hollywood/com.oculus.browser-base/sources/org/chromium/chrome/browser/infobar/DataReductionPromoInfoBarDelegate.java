package org.chromium.chrome.browser.infobar;

import android.content.Context;
import com.oculus.browser.R;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.net.spdyproxy.DataReductionProxySettings;
import org.chromium.components.infobars.InfoBar;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class DataReductionPromoInfoBarDelegate {
    public static void accept() {
        Context applicationContext = ContextUtils.getApplicationContext();
        UC.a(11);
        DataReductionProxySettings.d().g(true);
        C1184Ti1.b(applicationContext, applicationContext.getString(R.string.f50490_resource_name_obfuscated_RES_2131952366), 1).b.show();
    }

    public static void onNativeDestroyed() {
        if (!DataReductionProxySettings.d().e()) {
            UC.a(12);
        }
    }

    public static InfoBar showPromoInfoBar() {
        return new MC();
    }
}
