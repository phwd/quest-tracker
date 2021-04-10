package org.chromium.components.browser_ui.sms;

import com.oculus.browser.R;
import org.chromium.components.infobars.ConfirmInfoBar;
import org.chromium.ui.base.WindowAndroid;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class WebOTPServiceInfoBar extends ConfirmInfoBar {
    public WebOTPServiceInfoBar(WindowAndroid windowAndroid, int i, String str, String str2, String str3) {
        super(i, R.color.f12850_resource_name_obfuscated_RES_2131099975, null, str, null, str3, null);
    }

    public static WebOTPServiceInfoBar create(WindowAndroid windowAndroid, int i, String str, String str2, String str3) {
        return new WebOTPServiceInfoBar(windowAndroid, i, str, str2, str3);
    }
}
