package defpackage;

import java.util.Set;
import org.chromium.base.TraceEvent;
import org.chromium.chrome.browser.customtabs.CustomTabsConnection;
import org.chromium.content.browser.BrowserStartupControllerImpl;

/* renamed from: gC  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC2656gC implements Runnable {
    public void run() {
        Set set = CustomTabsConnection.f10648a;
        if (((BrowserStartupControllerImpl) AbstractC4280pk.a()).f()) {
            TraceEvent j0 = TraceEvent.j0("CreateSpareWebContents");
            try {
                CustomTabsConnection.c();
                if (j0 != null) {
                    j0.close();
                    return;
                }
                return;
            } catch (Throwable th) {
                AbstractC0754Mh1.f8495a.a(th, th);
            }
        } else {
            return;
        }
        throw th;
    }
}
