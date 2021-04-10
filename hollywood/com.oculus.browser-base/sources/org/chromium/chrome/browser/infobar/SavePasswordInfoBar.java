package org.chromium.chrome.browser.infobar;

import com.oculus.browser.R;
import org.chromium.components.infobars.ConfirmInfoBar;
import org.chromium.components.infobars.InfoBar;
import org.chromium.components.signin.base.AccountInfo;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SavePasswordInfoBar extends ConfirmInfoBar {
    public SavePasswordInfoBar(int i, String str, String str2, String str3, String str4, AccountInfo accountInfo) {
        super(i, R.color.f12850_resource_name_obfuscated_RES_2131099975, null, str, null, str3, str4);
    }

    public static InfoBar show(int i, String str, String str2, String str3, String str4, AccountInfo accountInfo) {
        return new SavePasswordInfoBar(i, str, str2, str3, str4, accountInfo);
    }
}
