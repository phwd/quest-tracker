package defpackage;

import J.N;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.content.browser.BrowserStartupControllerImpl;

/* renamed from: x00  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC5521x00 implements Runnable {
    public void run() {
        if (!Z00.a()) {
            AbstractC4841t00.a();
            if (((BrowserStartupControllerImpl) AbstractC4280pk.a()).f() && Profile.b().e()) {
                Profile c = Profile.b().c();
                N.MScIZBOB(c.b, c);
            }
        }
    }
}
