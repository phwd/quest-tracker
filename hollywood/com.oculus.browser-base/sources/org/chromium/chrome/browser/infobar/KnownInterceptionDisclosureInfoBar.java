package org.chromium.chrome.browser.infobar;

import android.graphics.Bitmap;
import org.chromium.components.infobars.ConfirmInfoBar;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class KnownInterceptionDisclosureInfoBar extends ConfirmInfoBar {
    public KnownInterceptionDisclosureInfoBar(int i, int i2, Bitmap bitmap, String str, String str2, String str3, String str4) {
        super(i, i2, bitmap, str, str2, str3, "");
    }

    public static ConfirmInfoBar create(int i, Bitmap bitmap, String str, String str2, String str3, String str4) {
        return new KnownInterceptionDisclosureInfoBar(i, 0, bitmap, str, str2, str3, str4);
    }
}
