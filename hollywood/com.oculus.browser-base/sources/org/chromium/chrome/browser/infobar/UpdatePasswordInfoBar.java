package org.chromium.chrome.browser.infobar;

import com.oculus.browser.R;
import org.chromium.components.infobars.ConfirmInfoBar;
import org.chromium.components.infobars.InfoBar;
import org.chromium.components.signin.base.AccountInfo;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class UpdatePasswordInfoBar extends ConfirmInfoBar {
    public final String[] I;

    public UpdatePasswordInfoBar(int i, String[] strArr, int i2, String str, String str2, String str3, AccountInfo accountInfo) {
        super(i, R.color.f12850_resource_name_obfuscated_RES_2131099975, null, str, null, str3, null);
        this.I = strArr;
    }

    public static InfoBar show(int i, String[] strArr, int i2, String str, String str2, String str3, AccountInfo accountInfo) {
        return new UpdatePasswordInfoBar(i, strArr, i2, str, str2, str3, accountInfo);
    }

    public final int getSelectedUsername() {
        if (this.I.length == 1) {
            return 0;
        }
        throw null;
    }
}
