package defpackage;

import org.chromium.chrome.browser.feedback.ScreenshotTask;

/* renamed from: zP0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC5930zP0 implements Runnable {
    public final /* synthetic */ ScreenshotTask F;

    public RunnableC5930zP0(ScreenshotTask screenshotTask) {
        this.F = screenshotTask;
    }

    public void run() {
        ScreenshotTask screenshotTask = this.F;
        screenshotTask.b = true;
        Runnable runnable = screenshotTask.c;
        if (runnable != null) {
            runnable.run();
        }
        screenshotTask.c = null;
    }
}
