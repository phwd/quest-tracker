package org.chromium.chrome.browser.init;

import org.chromium.base.task.PostTask;
import org.chromium.content.browser.BrowserStartupControllerImpl;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class NativeStartupBridge {
    public static void loadFullBrowser() {
        if (!((BrowserStartupControllerImpl) AbstractC4280pk.a()).f()) {
            PostTask.b(Zo1.f9374a, new RunnableC0278Em0(new C0217Dm0()), 0);
        }
    }
}
