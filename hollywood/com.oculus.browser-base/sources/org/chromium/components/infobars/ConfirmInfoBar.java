package org.chromium.components.infobars;

import android.graphics.Bitmap;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ConfirmInfoBar extends InfoBar {
    public ConfirmInfoBar(int i, int i2, Bitmap bitmap, String str, String str2, String str3, String str4) {
        super(i, i2, str, bitmap);
    }

    public static ConfirmInfoBar create(int i, Bitmap bitmap, String str, String str2, String str3, String str4) {
        return new ConfirmInfoBar(i, 0, bitmap, str, str2, str3, str4);
    }
}
