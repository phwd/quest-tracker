package defpackage;

import androidx.browser.customtabs.CustomTabsSessionToken;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.customtabs.CustomTabsConnection;

/* renamed from: jC  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC3168jC implements Runnable {
    public final CustomTabsConnection F;
    public final int G;

    public RunnableC3168jC(CustomTabsConnection customTabsConnection, int i) {
        this.F = customTabsConnection;
        this.G = i;
    }

    public void run() {
        ArrayList arrayList;
        CustomTabsConnection customTabsConnection = this.F;
        int i = this.G;
        Objects.requireNonNull(customTabsConnection);
        Object obj = ThreadUtils.f10596a;
        C3287jv jvVar = customTabsConnection.f;
        synchronized (jvVar) {
            arrayList = new ArrayList();
            for (Map.Entry entry : jvVar.b.entrySet()) {
                if (((C3116iv) entry.getValue()).f10172a == i) {
                    arrayList.add((CustomTabsSessionToken) entry.getKey());
                }
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            customTabsConnection.o((CustomTabsSessionToken) it.next(), "onWarmupCompleted", null);
        }
    }
}
