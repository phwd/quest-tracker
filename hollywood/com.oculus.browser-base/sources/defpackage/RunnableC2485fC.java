package defpackage;

import java.util.List;
import java.util.Set;
import org.chromium.base.ContextUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.customtabs.CustomTabsConnection;

/* renamed from: fC  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC2485fC implements Runnable {
    public final int F;
    public final String G;
    public final List H;

    public RunnableC2485fC(int i, String str, List list) {
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
        String[] packagesForUid = ContextUtils.getApplicationContext().getApplicationContext().getPackageManager().getPackagesForUid(i);
        if (packagesForUid != null && packagesForUid.length != 0) {
            PostTask.b(Zo1.f9374a, new RunnableC3852nC(str, list, packagesForUid), 0);
        }
    }
}
