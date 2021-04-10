package defpackage;

import android.app.Activity;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.download.DownloadManagerService;

/* renamed from: FI  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class FI extends AbstractC4758sY0 {
    @Override // defpackage.AbstractC4758sY0
    public void c(Object obj) {
        DownloadManagerService.F(ContextUtils.getApplicationContext(), 6);
    }

    @Override // defpackage.AbstractC4758sY0
    public void d(Object obj) {
    }

    public View$OnClickListenerC5098uY0 e() {
        Activity activity = ApplicationStatus.hasVisibleActivities() ? ApplicationStatus.e : null;
        if (activity == null || !(activity instanceof AbstractC4928tY0)) {
            return null;
        }
        return ((AbstractC4928tY0) activity).U();
    }
}
