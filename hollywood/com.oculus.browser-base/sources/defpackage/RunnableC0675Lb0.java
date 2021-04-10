package defpackage;

import org.chromium.chrome.browser.sync.settings.ManageSyncSettings;

/* renamed from: Lb0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC0675Lb0 implements Runnable {
    public final ManageSyncSettings F;

    public RunnableC0675Lb0(ManageSyncSettings manageSyncSettings) {
        this.F = manageSyncSettings;
    }

    public void run() {
        AbstractC4175p51.g(this.F.u(), "https://www.google.com/settings/chrome/sync");
    }
}
