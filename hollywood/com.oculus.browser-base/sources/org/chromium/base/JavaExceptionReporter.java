package org.chromium.base;

import J.N;
import java.lang.Thread;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class JavaExceptionReporter implements Thread.UncaughtExceptionHandler {

    /* renamed from: a  reason: collision with root package name */
    public static final /* synthetic */ int f10588a = 0;
    public final Thread.UncaughtExceptionHandler b;
    public final boolean c;
    public boolean d;

    public JavaExceptionReporter(Thread.UncaughtExceptionHandler uncaughtExceptionHandler, boolean z) {
        this.b = uncaughtExceptionHandler;
        this.c = z;
    }

    public static void installHandler(boolean z) {
        Thread.setDefaultUncaughtExceptionHandler(new JavaExceptionReporter(Thread.getDefaultUncaughtExceptionHandler(), z));
    }

    public void uncaughtException(Thread thread, Throwable th) {
        if (!this.d) {
            this.d = true;
            N.MLlibBXh(this.c, th);
        }
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.b;
        if (uncaughtExceptionHandler != null) {
            uncaughtExceptionHandler.uncaughtException(thread, th);
        }
    }
}
