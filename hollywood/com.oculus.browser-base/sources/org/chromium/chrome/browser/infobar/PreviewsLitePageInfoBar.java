package org.chromium.chrome.browser.infobar;

import com.oculus.browser.R;
import org.chromium.components.infobars.ConfirmInfoBar;
import org.chromium.components.infobars.InfoBar;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PreviewsLitePageInfoBar extends ConfirmInfoBar {
    public PreviewsLitePageInfoBar(int i, String str, String str2) {
        super(i, R.color.f12850_resource_name_obfuscated_RES_2131099975, null, str, str2, null, null);
    }

    public static InfoBar show(int i, String str, String str2) {
        return new PreviewsLitePageInfoBar(i, str, str2);
    }
}
