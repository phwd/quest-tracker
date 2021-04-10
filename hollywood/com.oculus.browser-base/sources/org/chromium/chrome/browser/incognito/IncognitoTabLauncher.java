package org.chromium.chrome.browser.incognito;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.browser.customtabs.CustomTabsSessionToken;
import org.chromium.base.ContextUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.customtabs.CustomTabsConnection;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class IncognitoTabLauncher extends Activity {
    public static final /* synthetic */ int F = 0;

    public static boolean a(Intent intent) {
        return S20.A(intent) && U20.d(intent, "org.chromium.chrome.browser.incognito.invoked_from_launch_new_incognito_tab", false);
    }

    public static void b(boolean z) {
        Object obj = ThreadUtils.f10596a;
        Context applicationContext = ContextUtils.getApplicationContext();
        PackageManager packageManager = applicationContext.getPackageManager();
        ComponentName componentName = new ComponentName(applicationContext, IncognitoTabLauncher.class);
        int i = z ? 1 : 2;
        if (packageManager.getComponentEnabledSetting(componentName) != i) {
            packageManager.setComponentEnabledSetting(componentName, i, 1);
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent b = S20.b(this, true);
        b.putExtra("org.chromium.chrome.browser.senders_package_name", CustomTabsConnection.f().f.c(CustomTabsSessionToken.b(getIntent())));
        b.putExtra("org.chromium.chrome.browser.incognito.invoked_from_launch_new_incognito_tab", true);
        P21 g0 = P21.g0();
        try {
            startActivity(b);
            g0.close();
            finish();
            return;
        } catch (Throwable th) {
            AbstractC0754Mh1.f8495a.a(th, th);
        }
        throw th;
    }
}
