package org.chromium.chrome.browser.media.router;

import org.chromium.base.ApplicationStatus;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ChromeMediaRouterClient {

    /* renamed from: a  reason: collision with root package name */
    public static ChromeMediaRouterClient f10694a;

    public static void initialize() {
        if (f10694a == null) {
            f10694a = new ChromeMediaRouterClient();
        }
    }

    public KS a() {
        AbstractActivityC3892nS nSVar = (AbstractActivityC3892nS) ApplicationStatus.e;
        if (nSVar == null) {
            return null;
        }
        return nSVar.Y();
    }
}
