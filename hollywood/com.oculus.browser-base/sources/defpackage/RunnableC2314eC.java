package defpackage;

import androidx.browser.customtabs.CustomTabsSessionToken;
import org.chromium.chrome.browser.customtabs.CustomTabsConnection;

/* renamed from: eC  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC2314eC implements Runnable {
    public final CustomTabsConnection F;
    public final CustomTabsSessionToken G;

    public RunnableC2314eC(CustomTabsConnection customTabsConnection, CustomTabsSessionToken customTabsSessionToken) {
        this.F = customTabsConnection;
        this.G = customTabsSessionToken;
    }

    public void run() {
        CustomTabsConnection customTabsConnection = this.F;
        CustomTabsSessionToken customTabsSessionToken = this.G;
        C3287jv jvVar = customTabsConnection.f;
        synchronized (jvVar) {
            if (customTabsSessionToken.b != null) {
                ((C3116iv) jvVar.b.get(customTabsSessionToken)).b = null;
            } else {
                jvVar.a(customTabsSessionToken);
            }
        }
    }
}
