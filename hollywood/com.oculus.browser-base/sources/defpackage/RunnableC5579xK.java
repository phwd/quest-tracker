package defpackage;

import org.chromium.chrome.browser.share.screenshot.EditorScreenshotTask;

/* renamed from: xK  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC5579xK implements Runnable {
    public final /* synthetic */ EditorScreenshotTask F;

    public RunnableC5579xK(EditorScreenshotTask editorScreenshotTask) {
        this.F = editorScreenshotTask;
    }

    public void run() {
        EditorScreenshotTask editorScreenshotTask = this.F;
        editorScreenshotTask.c = null;
        Runnable runnable = editorScreenshotTask.d;
        if (runnable != null) {
            runnable.run();
        }
        editorScreenshotTask.d = null;
    }
}
