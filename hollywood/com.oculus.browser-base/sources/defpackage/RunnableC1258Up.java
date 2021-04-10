package defpackage;

import java.util.Objects;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.partnercustomizations.PartnerBrowserCustomizations;

/* renamed from: Up  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC1258Up implements Runnable {
    public final ChromeActivity F;

    public RunnableC1258Up(ChromeActivity chromeActivity) {
        this.F = chromeActivity;
    }

    public void run() {
        ChromeActivity chromeActivity = this.F;
        Objects.requireNonNull(chromeActivity);
        if (PartnerBrowserCustomizations.isIncognitoDisabled()) {
            chromeActivity.p1();
        }
    }
}
