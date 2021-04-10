package org.chromium.chrome.browser.infobar;

import com.oculus.browser.R;
import org.chromium.components.infobars.InfoBar;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ReaderModeInfoBar extends InfoBar {
    public ReaderModeInfoBar() {
        super(R.drawable.f33340_resource_name_obfuscated_RES_2131231374, R.color.f12850_resource_name_obfuscated_RES_2131099975, null, null);
    }

    public static ReaderModeInfoBar create() {
        return new ReaderModeInfoBar();
    }
}
