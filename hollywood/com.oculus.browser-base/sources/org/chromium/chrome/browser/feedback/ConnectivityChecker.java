package org.chromium.chrome.browser.feedback;

import J.N;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Executor;
import org.chromium.base.task.PostTask;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class ConnectivityChecker {
    public static void a(boolean z, int i, C2101cy cyVar) {
        String str = z ? "https://clients4.google.com/generate_204" : "http://clients4.google.com/generate_204";
        if (!N.MGhgrVHC(str)) {
            AbstractC1220Ua0.f("feedback", "Predefined URL invalid.", new Object[0]);
            PostTask.b(Zo1.f9374a, new RunnableC1091Rx(cyVar, 4), 0);
            return;
        }
        try {
            C1152Sx sx = new C1152Sx(new URL(str), i, cyVar);
            Executor executor = AbstractC2032cb.f9616a;
            sx.f();
            ((ExecutorC1463Ya) executor).execute(sx.e);
        } catch (MalformedURLException e) {
            AbstractC1220Ua0.f("feedback", "Failed to parse predefined URL: " + e, new Object[0]);
            PostTask.b(Zo1.f9374a, new RunnableC1091Rx(cyVar, 4), 0);
        }
    }

    public static void executeCallback(Object obj, int i) {
        ((C2101cy) obj).a(i);
    }
}
