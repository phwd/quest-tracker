package org.chromium.chrome.browser.infobar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.oculus.browser.R;
import org.chromium.base.ContextUtils;
import org.chromium.components.browser_ui.site_settings.SingleWebsiteSettings;
import org.chromium.components.infobars.InfoBar;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SearchGeolocationDisclosureInfoBar extends InfoBar {
    public SearchGeolocationDisclosureInfoBar(int i, String str, int i2, int i3) {
        super(i, R.color.f12850_resource_name_obfuscated_RES_2131099975, str, null);
    }

    public static InfoBar show(int i, String str, int i2, int i3) {
        return new SearchGeolocationDisclosureInfoBar(i, str, i2, i3);
    }

    public static void showSettingsPage(String str) {
        Context applicationContext = ContextUtils.getApplicationContext();
        Bundle k1 = SingleWebsiteSettings.k1(str);
        String name = SingleWebsiteSettings.class.getName();
        Intent l = AbstractC2531fV.l(applicationContext, XS0.class);
        if (!(applicationContext instanceof Activity)) {
            l.addFlags(268435456);
            l.addFlags(67108864);
        }
        if (name != null) {
            l.putExtra("show_fragment", name);
        }
        l.putExtra("show_fragment_args", k1);
        U20.q(applicationContext, l);
    }
}
