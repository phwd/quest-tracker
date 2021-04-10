package org.chromium.chrome.browser.payments;

import J.N;
import android.graphics.Bitmap;
import android.util.Pair;
import java.util.HashMap;
import java.util.Map;
import org.chromium.base.ThreadUtils;
import org.chromium.content_public.browser.WebContents;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ServiceWorkerPaymentAppBridge {

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public interface GetServiceWorkerPaymentAppsInfoCallback {
        void a(Map map);
    }

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public interface HasServiceWorkerPaymentAppsCallback {
        void a(boolean z);
    }

    public static void a(WebContents webContents, int i) {
        if (webContents != null && !webContents.g()) {
            N.MAan0VNK(webContents, i);
        }
    }

    public static void addPaymentAppInfo(Object obj, String str, String str2, Bitmap bitmap) {
        ((Map) obj).put(str, new Pair(str2, bitmap));
    }

    public static Object createPaymentAppsInfo() {
        return new HashMap();
    }

    public static void onGetServiceWorkerPaymentAppsInfo(GetServiceWorkerPaymentAppsInfoCallback getServiceWorkerPaymentAppsInfoCallback, Object obj) {
        Object obj2 = ThreadUtils.f10596a;
        getServiceWorkerPaymentAppsInfoCallback.a((Map) obj);
    }

    public static void onHasServiceWorkerPaymentApps(HasServiceWorkerPaymentAppsCallback hasServiceWorkerPaymentAppsCallback, boolean z) {
        Object obj = ThreadUtils.f10596a;
        hasServiceWorkerPaymentAppsCallback.a(z);
    }
}
