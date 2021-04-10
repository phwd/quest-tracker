package defpackage;

import java.util.List;
import java.util.Set;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.customtabs.CustomTabsConnection;

/* renamed from: mC  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC3681mC implements Runnable {
    public final int F;
    public final String G;
    public final List H;

    public RunnableC3681mC(int i, String str, List list) {
        this.F = i;
        this.G = str;
        this.H = list;
    }

    public void run() {
        int i = this.F;
        String str = this.G;
        List list = this.H;
        Set set = CustomTabsConnection.f10648a;
        PostTask.b(C3070if1.f10154a, new RunnableC2485fC(i, str, list), 0);
    }
}
