package defpackage;

import android.content.Context;
import java.util.Iterator;
import java.util.Objects;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.download.DownloadManagerService;

/* renamed from: eI  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC2332eI implements Runnable {
    public final DownloadManagerService F;

    public RunnableC2332eI(DownloadManagerService downloadManagerService) {
        this.F = downloadManagerService;
    }

    public void run() {
        boolean z;
        D51 d51 = this.F.f10662J;
        Objects.requireNonNull(d51);
        Context applicationContext = ContextUtils.getApplicationContext();
        Iterator it = DI.f7880a.f7952a.iterator();
        while (true) {
            if (it.hasNext()) {
                if (C5234vI.a(applicationContext, (BI) it.next())) {
                    z = true;
                    break;
                }
            } else {
                z = false;
                break;
            }
        }
        if (z) {
            d51.b().i();
        }
    }
}
