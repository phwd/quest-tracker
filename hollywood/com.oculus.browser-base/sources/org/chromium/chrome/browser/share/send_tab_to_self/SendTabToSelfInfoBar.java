package org.chromium.chrome.browser.share.send_tab_to_self;

import com.oculus.browser.R;
import org.chromium.components.infobars.InfoBar;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SendTabToSelfInfoBar extends InfoBar {
    public SendTabToSelfInfoBar() {
        super(R.drawable.f33230_resource_name_obfuscated_RES_2131231363, R.color.f11230_resource_name_obfuscated_RES_2131099813, null, null);
    }

    public static SendTabToSelfInfoBar create() {
        return new SendTabToSelfInfoBar();
    }
}
