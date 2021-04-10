package defpackage;

import org.chromium.chrome.browser.payments.ServiceWorkerPaymentAppBridge;

/* renamed from: LS0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class LS0 implements Runnable {
    public final /* synthetic */ ServiceWorkerPaymentAppBridge.HasServiceWorkerPaymentAppsCallback F;

    public LS0(ServiceWorkerPaymentAppBridge.HasServiceWorkerPaymentAppsCallback hasServiceWorkerPaymentAppsCallback) {
        this.F = hasServiceWorkerPaymentAppsCallback;
    }

    public void run() {
        this.F.a(false);
    }
}
