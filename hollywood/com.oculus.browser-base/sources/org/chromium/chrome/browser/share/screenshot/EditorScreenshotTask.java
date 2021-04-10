package org.chromium.chrome.browser.share.screenshot;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class EditorScreenshotTask {

    /* renamed from: a  reason: collision with root package name */
    public final Activity f10759a;
    public final AbstractC4448qj b;
    public Bitmap c;
    public Runnable d;

    public EditorScreenshotTask(Activity activity, AbstractC4448qj qjVar) {
        this.f10759a = activity;
        this.b = qjVar;
    }

    public final void onBytesReceived(byte[] bArr) {
        this.c = bArr != null ? BitmapFactory.decodeByteArray(bArr, 0, bArr.length) : null;
        Runnable runnable = this.d;
        if (runnable != null) {
            runnable.run();
        }
        this.d = null;
    }
}
