package defpackage;

import java.util.Objects;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.profiles.Profile;

/* renamed from: Sp  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC1136Sp implements Runnable {
    public final ChromeActivity F;

    public RunnableC1136Sp(ChromeActivity chromeActivity) {
        this.F = chromeActivity;
    }

    public void run() {
        if (!this.F.v() && C5949zZ.a().d(Profile.b()).p()) {
            Objects.requireNonNull(YM.f9268a);
            ContextUtils.getApplicationContext();
        }
    }
}
