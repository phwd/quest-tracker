package org.chromium.content.browser;

import android.os.Handler;
import org.chromium.base.JavaHandlerThread;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class LauncherThread {

    /* renamed from: a  reason: collision with root package name */
    public static final JavaHandlerThread f10914a;
    public static final Handler b;
    public static Handler c;

    static {
        JavaHandlerThread javaHandlerThread = new JavaHandlerThread("Chrome_ProcessLauncherThread", 0);
        f10914a = javaHandlerThread;
        javaHandlerThread.a();
        Handler handler = new Handler(javaHandlerThread.f10589a.getLooper());
        b = handler;
        c = handler;
    }

    public static JavaHandlerThread getHandlerThread() {
        return f10914a;
    }
}
