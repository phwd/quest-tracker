package defpackage;

import android.content.Context;
import android.content.Intent;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.LoadUrlParams;

/* renamed from: sX  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC4755sX {
    public static void a(ChromeActivity chromeActivity, Tab tab) {
        Context applicationContext = ContextUtils.getApplicationContext();
        if (chromeActivity.h0) {
            tab.c(new LoadUrlParams("chrome-native://history/", 0));
            return;
        }
        Intent l = AbstractC2531fV.l(applicationContext, AbstractActivityC4244pX.class);
        l.putExtra("org.chromium.chrome.browser.parent_component", chromeActivity.getComponentName());
        l.putExtra("org.chromium.chrome.browser.incognito_mode", ((AbstractC0246Ea1) chromeActivity.P()).r());
        chromeActivity.startActivity(l);
    }
}
