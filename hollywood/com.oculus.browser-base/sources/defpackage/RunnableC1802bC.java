package defpackage;

import android.content.Context;
import java.util.Objects;
import org.chromium.base.ContextUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.base.TraceEvent;
import org.chromium.chrome.browser.customtabs.CustomTabsConnection;

/* renamed from: bC  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC1802bC implements Runnable {
    public final CustomTabsConnection F;

    public RunnableC1802bC(CustomTabsConnection customTabsConnection) {
        this.F = customTabsConnection;
    }

    public void run() {
        CustomTabsConnection customTabsConnection = this.F;
        Objects.requireNonNull(customTabsConnection);
        TraceEvent j0 = TraceEvent.j0("CustomTabsConnection.initializeBrowser()");
        try {
            Context applicationContext = ContextUtils.getApplicationContext();
            Object obj = ThreadUtils.f10596a;
            C1321Vq.b().f(true, null);
            AbstractC0281Eo.a(applicationContext, true);
            C1321Vq.b().g();
            customTabsConnection.i.set(true);
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
