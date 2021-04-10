package org.chromium.chrome.browser.safe_browsing;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import org.chromium.chrome.browser.safe_browsing.settings.SafeBrowsingSettingsFragment;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SafeBrowsingSettingsLauncher {
    public static void showSafeBrowsingSettings(WebContents webContents) {
        WindowAndroid I = webContents.I();
        if (I != null) {
            Context context = (Context) I.f11022J.get();
            int i = SafeBrowsingSettingsFragment.G0;
            Bundle bundle = new Bundle();
            bundle.putInt("SafeBrowsingSettingsFragment.AccessPoint", 4);
            String name = SafeBrowsingSettingsFragment.class.getName();
            Intent l = AbstractC2531fV.l(context, XS0.class);
            if (!(context instanceof Activity)) {
                l.addFlags(268435456);
                l.addFlags(67108864);
            }
            l.putExtra("show_fragment", name);
            l.putExtra("show_fragment_args", bundle);
            U20.q(context, l);
        }
    }
}
