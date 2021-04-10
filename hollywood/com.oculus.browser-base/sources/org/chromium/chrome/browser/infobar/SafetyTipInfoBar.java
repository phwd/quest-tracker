package org.chromium.chrome.browser.infobar;

import android.graphics.Bitmap;
import org.chromium.components.infobars.ConfirmInfoBar;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SafetyTipInfoBar extends ConfirmInfoBar {
    public SafetyTipInfoBar(int i, int i2, Bitmap bitmap, String str, String str2, String str3, String str4, String str5) {
        super(i, i2, bitmap, str, null, str3, str4);
    }

    public static ConfirmInfoBar create(int i, Bitmap bitmap, String str, String str2, String str3, String str4, String str5) {
        return new SafetyTipInfoBar(i, 0, bitmap, str, str2, str3, str4, str5);
    }
}
