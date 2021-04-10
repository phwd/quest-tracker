package org.chromium.chrome.browser.infobar;

import java.util.Objects;
import org.chromium.chrome.browser.instantapps.InstantAppsBannerData;
import org.chromium.components.infobars.ConfirmInfoBar;
import org.chromium.components.infobars.InfoBar;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class InstantAppsInfoBar extends ConfirmInfoBar {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public InstantAppsInfoBar(InstantAppsBannerData instantAppsBannerData) {
        super(0, 0, null, null, null, null, null);
        Objects.requireNonNull(instantAppsBannerData);
    }

    public static InfoBar create(InstantAppsBannerData instantAppsBannerData) {
        return new InstantAppsInfoBar(instantAppsBannerData);
    }
}
