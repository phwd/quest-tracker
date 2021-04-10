package org.chromium.components.security_interstitials;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import org.chromium.base.ContextUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class DateAndTimeSettingsHelper {
    public static void openDateAndTimeSettings() {
        Intent intent = new Intent("android.settings.DATE_SETTINGS");
        try {
            intent.addFlags(268435456);
            ContextUtils.getApplicationContext().startActivity(intent);
        } catch (ActivityNotFoundException unused) {
        }
    }
}
