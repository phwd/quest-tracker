package defpackage;

import java.util.Set;
import org.chromium.chrome.browser.download.DownloadNotificationServiceObserver;

/* renamed from: AH  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AH {
    public static Set a() {
        return NU0.f8549a.j("ForegroundServiceObservers");
    }

    public static DownloadNotificationServiceObserver b(String str) {
        try {
            return (DownloadNotificationServiceObserver) Class.forName(str).newInstance();
        } catch (Throwable th) {
            AbstractC1220Ua0.f("DownloadFgServiceObs", "getObserverFromClassName(): %s", str, th);
            return null;
        }
    }
}
