package defpackage;

import J.N;
import java.util.Set;
import org.chromium.base.ThreadUtils;
import org.chromium.base.TraceEvent;
import org.chromium.chrome.browser.customtabs.CustomTabsConnection;
import org.chromium.chrome.browser.profiles.Profile;

/* renamed from: iC  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC2998iC implements Runnable {
    public void run() {
        Set set = CustomTabsConnection.f10648a;
        TraceEvent j0 = TraceEvent.j0("WarmupInternalFinishInitialization");
        try {
            Profile b = Profile.b();
            Object obj = ThreadUtils.f10596a;
            N.MejOrYY2(b);
            C2173dM0.b();
            if (j0 != null) {
                j0.close();
                return;
            }
            return;
        } catch (Throwable th) {
            AbstractC0754Mh1.f8495a.a(th, th);
        }
        throw th;
    }
}
