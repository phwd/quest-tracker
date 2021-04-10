package org.chromium.chrome.browser.infobar;

import java.util.Objects;
import org.chromium.chrome.browser.instantapps.InstantAppsBannerData;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class InstantAppsInfoBarDelegate {
    public static InstantAppsInfoBarDelegate create() {
        return new InstantAppsInfoBarDelegate();
    }

    public final void openInstantApp(InstantAppsBannerData instantAppsBannerData) {
        Objects.requireNonNull(N20.a());
        Objects.requireNonNull(instantAppsBannerData);
    }
}
