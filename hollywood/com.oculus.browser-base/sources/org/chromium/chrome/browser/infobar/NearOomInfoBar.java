package org.chromium.chrome.browser.infobar;

import com.oculus.browser.R;
import org.chromium.components.infobars.InfoBar;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class NearOomInfoBar extends InfoBar {
    public NearOomInfoBar() {
        super(R.drawable.f33230_resource_name_obfuscated_RES_2131231363, R.color.f12850_resource_name_obfuscated_RES_2131099975, null, null);
    }

    public static NearOomInfoBar create() {
        return new NearOomInfoBar();
    }
}
