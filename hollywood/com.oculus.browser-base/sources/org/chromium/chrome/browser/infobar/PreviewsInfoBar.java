package org.chromium.chrome.browser.infobar;

import com.oculus.browser.R;
import org.chromium.components.infobars.ConfirmInfoBar;
import org.chromium.components.infobars.InfoBar;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PreviewsInfoBar extends ConfirmInfoBar {
    public PreviewsInfoBar(int i, String str, String str2, String str3) {
        super(i, R.color.f12850_resource_name_obfuscated_RES_2131099975, null, str, str2, null, null);
    }

    public static InfoBar show(int i, String str, String str2, String str3) {
        return new PreviewsInfoBar(i, str, str2, str3);
    }
}
