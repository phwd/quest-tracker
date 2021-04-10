package defpackage;

import android.graphics.Typeface;
import android.os.Handler;
import android.os.Looper;

/* renamed from: wM0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC5414wM0 {
    public final void a(int i, Handler handler) {
        if (handler == null) {
            handler = new Handler(Looper.getMainLooper());
        }
        handler.post(new RunnableC5244vM0(this, i));
    }

    public final void b(Typeface typeface, Handler handler) {
        if (handler == null) {
            handler = new Handler(Looper.getMainLooper());
        }
        handler.post(new RunnableC5074uM0(this, typeface));
    }

    public abstract void c(int i);

    public abstract void d(Typeface typeface);
}
