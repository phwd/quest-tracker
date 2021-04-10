package defpackage;

import android.os.SystemClock;
import java.util.List;
import org.chromium.base.Callback;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.feedback.ScreenshotTask;

/* renamed from: Cr  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC0165Cr implements Runnable {
    public static final /* synthetic */ int F = 0;
    public final long G = SystemClock.elapsedRealtime();
    public List H;
    public List I;

    /* renamed from: J  reason: collision with root package name */
    public ScreenshotTask f7843J;
    public Callback K;

    /* JADX WARNING: Removed duplicated region for block: B:32:0x0101  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x012a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public RunnableC0165Cr(android.app.Activity r2, java.lang.String r3, java.lang.String r4, org.chromium.chrome.browser.feedback.ScreenshotTask r5, defpackage.C0104Br r6, org.chromium.base.Callback r7) {
        /*
        // Method dump skipped, instructions count: 338
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.RunnableC0165Cr.<init>(android.app.Activity, java.lang.String, java.lang.String, org.chromium.chrome.browser.feedback.ScreenshotTask, Br, org.chromium.base.Callback):void");
    }

    public final void a() {
        if (this.K != null) {
            ScreenshotTask screenshotTask = this.f7843J;
            if (screenshotTask == null || screenshotTask.b) {
                if (this.I.size() > 0 && SystemClock.elapsedRealtime() - this.G < 500) {
                    for (AbstractC0183Da da : this.I) {
                        if (!da.a()) {
                            return;
                        }
                    }
                }
                Callback callback = this.K;
                this.K = null;
                PostTask.b(Zo1.f9374a, callback.a(this), 0);
            }
        }
    }

    public void run() {
        a();
    }
}
