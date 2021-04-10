package defpackage;

import java.util.List;
import java.util.Set;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.customtabs.CustomTabsConnection;

/* renamed from: kC  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC3339kC implements Runnable {
    public final int F;
    public final String G;
    public final List H;

    public RunnableC3339kC(int i, String str, List list) {
        this.F = i;
        this.G = str;
        this.H = list;
    }

    public void run() {
        int i = this.F;
        String str = this.G;
        List list = this.H;
        Set set = CustomTabsConnection.f10648a;
        Object obj = ThreadUtils.f10596a;
        C1321Vq.b().i(new RunnableC3681mC(i, str, list));
    }
}
