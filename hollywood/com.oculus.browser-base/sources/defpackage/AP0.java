package defpackage;

import android.app.Activity;
import android.graphics.Bitmap;
import org.chromium.chrome.browser.feedback.ScreenshotTask;

/* renamed from: AP0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AP0 implements Runnable {
    public final /* synthetic */ Activity F;
    public final /* synthetic */ ScreenshotTask G;

    public AP0(ScreenshotTask screenshotTask, Activity activity) {
        this.G = screenshotTask;
        this.F = activity;
    }

    public void run() {
        AbstractC2417ep1.c(this.F.getWindow().getDecorView().getRootView(), 600, Bitmap.Config.ARGB_8888);
        ScreenshotTask screenshotTask = this.G;
        screenshotTask.b = true;
        Runnable runnable = screenshotTask.c;
        if (runnable != null) {
            runnable.run();
        }
        screenshotTask.c = null;
    }
}
