package defpackage;

import java.util.HashMap;
import org.chromium.chrome.browser.payments.ServiceWorkerPaymentAppBridge;

/* renamed from: MS0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class MS0 implements Runnable {
    public final /* synthetic */ ServiceWorkerPaymentAppBridge.GetServiceWorkerPaymentAppsInfoCallback F;

    public MS0(ServiceWorkerPaymentAppBridge.GetServiceWorkerPaymentAppsInfoCallback getServiceWorkerPaymentAppsInfoCallback) {
        this.F = getServiceWorkerPaymentAppsInfoCallback;
    }

    public void run() {
        this.F.a(new HashMap());
    }
}
