package org.chromium.chrome.browser.crash;

import java.lang.Thread;
import org.chromium.base.ContextUtils;
import org.chromium.components.crash.CrashKeys;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PureJavaExceptionHandler implements Thread.UncaughtExceptionHandler {

    /* renamed from: a  reason: collision with root package name */
    public static boolean f10646a;
    public final Thread.UncaughtExceptionHandler b;
    public boolean c;

    public PureJavaExceptionHandler(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.b = uncaughtExceptionHandler;
    }

    public static void uninstallHandler() {
        f10646a = true;
        CrashKeys.getInstance().flushToNative();
    }

    public void uncaughtException(Thread thread, Throwable th) {
        if (!this.c && !f10646a) {
            this.c = true;
            ((PureJavaExceptionReporter) AbstractC2369eZ0.b(AbstractC2369eZ0.a(ContextUtils.getApplicationContext()), "org.chromium.chrome.browser.crash.PureJavaExceptionReporter")).c(th);
        }
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.b;
        if (uncaughtExceptionHandler != null) {
            uncaughtExceptionHandler.uncaughtException(thread, th);
        }
    }
}
