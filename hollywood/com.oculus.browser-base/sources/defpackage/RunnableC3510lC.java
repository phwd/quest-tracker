package defpackage;

import android.os.Bundle;
import androidx.browser.customtabs.CustomTabsSessionToken;
import java.util.List;
import org.chromium.chrome.browser.customtabs.CustomTabsConnection;

/* renamed from: lC  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC3510lC implements Runnable {
    public final CustomTabsConnection F;
    public final boolean G;
    public final CustomTabsSessionToken H;
    public final int I;

    /* renamed from: J  reason: collision with root package name */
    public final String f10330J;
    public final Bundle K;
    public final List L;

    public RunnableC3510lC(CustomTabsConnection customTabsConnection, boolean z, CustomTabsSessionToken customTabsSessionToken, int i, String str, Bundle bundle, List list) {
        this.F = customTabsConnection;
        this.G = z;
        this.H = customTabsSessionToken;
        this.I = i;
        this.f10330J = str;
        this.K = bundle;
        this.L = list;
    }

    public void run() {
        this.F.d(this.G, this.H, this.I, this.f10330J, this.K, this.L, true);
    }
}
