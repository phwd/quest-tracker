package defpackage;

import java.util.Arrays;
import java.util.List;

/* renamed from: wI  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC5404wI {

    /* renamed from: a  reason: collision with root package name */
    public static List f11536a = Arrays.asList("android.intent.action.DOWNLOAD_NOTIFICATION_CLICKED", "org.chromium.chrome.browser.download.DOWNLOAD_OPEN", "org.chromium.chrome.browser.download.DOWNLOAD_CANCEL", "org.chromium.chrome.browser.download.DOWNLOAD_PAUSE", "org.chromium.chrome.browser.download.DOWNLOAD_RESUME");

    public static void a(int i) {
        AbstractC3364kK0.g("MobileDownload.DownloadResumption", i, 7);
    }

    public static void b(int i) {
        if (C2474f80.f9900a.f()) {
            AbstractC3364kK0.g("Android.DownloadManager.ForegroundServiceLifecycle", i, 3);
        }
    }

    public static void c(int i) {
        if (C2474f80.f9900a.f()) {
            AbstractC3364kK0.g("Android.DownloadManager.ServiceStopped.DownloadForeground", i, 5);
        }
    }
}
