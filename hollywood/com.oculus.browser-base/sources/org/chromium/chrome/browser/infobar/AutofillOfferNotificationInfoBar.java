package org.chromium.chrome.browser.infobar;

import org.chromium.components.infobars.ConfirmInfoBar;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AutofillOfferNotificationInfoBar extends ConfirmInfoBar {
    public AutofillOfferNotificationInfoBar(long j, int i, String str, String str2, String str3) {
        super(0, 0, null, str, null, str2, null);
    }

    public static AutofillOfferNotificationInfoBar create(long j, int i, String str, String str2, String str3) {
        return new AutofillOfferNotificationInfoBar(j, i, str, str2, str3);
    }

    public final void setCreditCardDetails(String str, int i) {
    }
}
