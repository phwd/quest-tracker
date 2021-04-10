package defpackage;

import android.os.Bundle;
import androidx.browser.customtabs.CustomTabsSessionToken;
import java.util.List;
import org.chromium.chrome.browser.customtabs.CustomTabsConnection;

/* renamed from: oC  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC4023oC implements Runnable {
    public final CustomTabsConnection F;
    public final boolean G;
    public final CustomTabsSessionToken H;
    public final int I;

    /* renamed from: J  reason: collision with root package name */
    public final String f10537J;
    public final Bundle K;
    public final List L;

    public RunnableC4023oC(CustomTabsConnection customTabsConnection, boolean z, CustomTabsSessionToken customTabsSessionToken, int i, String str, Bundle bundle, List list) {
        this.F = customTabsConnection;
        this.G = z;
        this.H = customTabsSessionToken;
        this.I = i;
        this.f10537J = str;
        this.K = bundle;
        this.L = list;
    }

    public void run() {
        this.F.d(this.G, this.H, this.I, this.f10537J, this.K, this.L, false);
    }
}
