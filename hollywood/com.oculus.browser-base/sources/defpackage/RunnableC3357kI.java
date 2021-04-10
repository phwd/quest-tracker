package defpackage;

import com.oculus.browser.R;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.download.DownloadManagerService;

/* renamed from: kI  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC3357kI implements Runnable {
    public final DownloadManagerService F;

    public RunnableC3357kI(DownloadManagerService downloadManagerService) {
        this.F = downloadManagerService;
    }

    public void run() {
        FI fi = this.F.P;
        if (fi.e() != null) {
            C4076oY0 c = C4076oY0.c(ContextUtils.getApplicationContext().getString(R.string.f51200_resource_name_obfuscated_RES_2131952437), fi, 1, 24);
            c.i = false;
            c.j = 7000;
            fi.e().l(c);
        }
    }
}
