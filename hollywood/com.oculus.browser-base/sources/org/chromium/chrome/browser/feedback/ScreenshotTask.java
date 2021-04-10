package org.chromium.chrome.browser.feedback;

import android.app.Activity;
import android.graphics.BitmapFactory;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class ScreenshotTask {

    /* renamed from: a  reason: collision with root package name */
    public final Activity f10673a;
    public boolean b;
    public Runnable c;

    public ScreenshotTask(Activity activity) {
        this.f10673a = activity;
    }

    public final void onBytesReceived(byte[] bArr) {
        if (bArr != null) {
            BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
        }
        this.b = true;
        Runnable runnable = this.c;
        if (runnable != null) {
            runnable.run();
        }
        this.c = null;
    }
}
