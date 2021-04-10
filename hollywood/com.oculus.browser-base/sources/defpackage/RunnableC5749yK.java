package defpackage;

import android.app.Activity;
import android.graphics.Bitmap;
import org.chromium.chrome.browser.share.screenshot.EditorScreenshotTask;

/* renamed from: yK  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC5749yK implements Runnable {
    public final /* synthetic */ Activity F;
    public final /* synthetic */ EditorScreenshotTask G;

    public RunnableC5749yK(EditorScreenshotTask editorScreenshotTask, Activity activity) {
        this.G = editorScreenshotTask;
        this.F = activity;
    }

    public void run() {
        Bitmap c = AbstractC2417ep1.c(this.F.getWindow().getDecorView().getRootView(), 0, Bitmap.Config.ARGB_8888);
        EditorScreenshotTask editorScreenshotTask = this.G;
        editorScreenshotTask.c = c;
        Runnable runnable = editorScreenshotTask.d;
        if (runnable != null) {
            runnable.run();
        }
        editorScreenshotTask.d = null;
    }
}
