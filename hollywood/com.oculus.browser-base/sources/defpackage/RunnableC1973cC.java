package defpackage;

import J.N;
import android.net.Uri;
import androidx.browser.customtabs.CustomTabsSessionToken;
import java.util.Objects;
import org.chromium.chrome.browser.customtabs.CustomTabsConnection;
import org.chromium.content_public.browser.WebContents;

/* renamed from: cC  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC1973cC implements Runnable {
    public final CustomTabsConnection F;
    public final CustomTabsSessionToken G;
    public final int H;
    public final C4649rt0 I;

    public RunnableC1973cC(CustomTabsConnection customTabsConnection, CustomTabsSessionToken customTabsSessionToken, int i, C4649rt0 rt0) {
        this.F = customTabsConnection;
        this.G = customTabsSessionToken;
        this.H = i;
        this.I = rt0;
    }

    public void run() {
        CustomTabsConnection customTabsConnection = this.F;
        CustomTabsSessionToken customTabsSessionToken = this.G;
        int i = this.H;
        C4649rt0 rt0 = this.I;
        Objects.requireNonNull(customTabsConnection);
        if (N.M09VlOh_("CCTPostMessageAPI")) {
            Uri q = customTabsConnection.q(i);
            if (q == null) {
                C3287jv jvVar = customTabsConnection.f;
                synchronized (jvVar) {
                    jvVar.f(customTabsSessionToken, 1, rt0, true);
                }
                return;
            }
            C3287jv jvVar2 = customTabsConnection.f;
            synchronized (jvVar2) {
                C3116iv ivVar = (C3116iv) jvVar2.b.get(customTabsSessionToken);
                if (ivVar != null) {
                    IE0 ie0 = ivVar.d;
                    ie0.e = q;
                    WebContents webContents = ie0.c;
                    if (webContents != null && !webContents.g()) {
                        ie0.c(ie0.c);
                    }
                }
            }
        }
    }
}
