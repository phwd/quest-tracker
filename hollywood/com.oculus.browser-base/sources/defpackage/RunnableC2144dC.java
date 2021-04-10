package defpackage;

import J.N;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.browser.customtabs.CustomTabsSessionToken;
import java.util.Objects;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.customtabs.CustomTabsConnection;

/* renamed from: dC  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC2144dC implements Runnable {
    public final CustomTabsConnection F;
    public final CustomTabsSessionToken G;
    public final Intent H;

    public RunnableC2144dC(CustomTabsConnection customTabsConnection, CustomTabsSessionToken customTabsSessionToken, Intent intent) {
        this.F = customTabsConnection;
        this.G = customTabsSessionToken;
        this.H = intent;
    }

    public void run() {
        int i;
        CustomTabsConnection customTabsConnection = this.F;
        CustomTabsSessionToken customTabsSessionToken = this.G;
        Intent intent = this.H;
        Objects.requireNonNull(customTabsConnection);
        Object obj = ThreadUtils.f10596a;
        if (!intent.hasExtra("android.support.customtabs.PARALLEL_REQUEST_URL")) {
            i = 0;
        } else if (!C1321Vq.b().h) {
            i = 2;
        } else {
            C3287jv jvVar = customTabsConnection.f;
            synchronized (jvVar) {
                C3116iv ivVar = (C3116iv) jvVar.b.get(customTabsSessionToken);
            }
            i = 3;
        }
        AbstractC3364kK0.g("CustomTabs.ParallelRequestStatusOnStart", i, 7);
        if (customTabsConnection.g) {
            StringBuilder i2 = AbstractC2531fV.i("handleParallelRequest() = ");
            i2.append(CustomTabsConnection.b[i]);
            AbstractC1220Ua0.f("ChromeConnection", i2.toString(), new Object[0]);
        }
        if (i != 0 && i != 2 && i != 3 && N.M09VlOh_("CCTReportParallelRequestStatus")) {
            Bundle bundle = new Bundle();
            bundle.putParcelable("url", (Uri) intent.getParcelableExtra("android.support.customtabs.PARALLEL_REQUEST_URL"));
            bundle.putInt("status", i);
            customTabsConnection.o(customTabsSessionToken, "onDetachedRequestRequested", bundle);
            if (customTabsConnection.g) {
                customTabsConnection.k("onDetachedRequestRequested", CustomTabsConnection.a(bundle).toString());
            }
        }
    }
}
