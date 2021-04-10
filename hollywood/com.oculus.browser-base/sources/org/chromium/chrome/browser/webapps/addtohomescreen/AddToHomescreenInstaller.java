package org.chromium.chrome.browser.webapps.addtohomescreen;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import java.util.Objects;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.banners.AppData;
import org.chromium.chrome.browser.tab.Tab;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AddToHomescreenInstaller {
    public static boolean installOrOpenNativeApp(Tab tab, AppData appData) {
        Context applicationContext = ContextUtils.getApplicationContext();
        Objects.requireNonNull(appData);
        Intent launchIntentForPackage = AbstractC4652ru0.b(applicationContext, null) ? applicationContext.getPackageManager().getLaunchIntentForPackage(null) : null;
        if (!(launchIntentForPackage == null || AbstractC5112ud1.b(tab) == null)) {
            try {
                AbstractC5112ud1.b(tab).startActivity(launchIntentForPackage);
            } catch (ActivityNotFoundException e) {
                AbstractC1220Ua0.a("AddToHomescreen", "Failed to install or open app : %s!", null, e);
                return false;
            }
        }
        return true;
    }
}
