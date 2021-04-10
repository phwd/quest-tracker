package defpackage;

import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.components.page_info.PageInfoController;
import org.chromium.content_public.browser.WebContents;

/* renamed from: Dz0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC0243Dz0 implements Runnable {
    public final C0426Gz0 F;

    public RunnableC0243Dz0(C0426Gz0 gz0) {
        this.F = gz0;
    }

    public void run() {
        C0426Gz0 gz0 = this.F;
        ChromeActivity chromeActivity = gz0.d;
        WebContents webContents = gz0.c;
        chromeActivity.getClass();
        PageInfoController.l(chromeActivity, webContents, null, 2, new C4985ts(chromeActivity, webContents, new C0365Fz0(chromeActivity), new C2084cs0(gz0.c)), new C0411Gs());
    }
}
