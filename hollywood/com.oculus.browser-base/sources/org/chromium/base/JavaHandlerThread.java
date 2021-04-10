package org.chromium.base;

import android.os.Handler;
import android.os.HandlerThread;
import java.lang.Thread;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class JavaHandlerThread {

    /* renamed from: a  reason: collision with root package name */
    public final HandlerThread f10589a;
    public Throwable b;

    public JavaHandlerThread(String str, int i) {
        this.f10589a = new HandlerThread(str, i);
    }

    public static JavaHandlerThread create(String str, int i) {
        return new JavaHandlerThread(str, i);
    }

    public void a() {
        if (!(this.f10589a.getState() != Thread.State.NEW)) {
            this.f10589a.start();
        }
    }

    public final Throwable getUncaughtExceptionIfAny() {
        return this.b;
    }

    public final boolean isAlive() {
        return this.f10589a.isAlive();
    }

    public final void joinThread() {
        boolean z = false;
        while (!z) {
            try {
                this.f10589a.join();
                z = true;
            } catch (InterruptedException unused) {
            }
        }
    }

    public final void listenForUncaughtExceptionsForTesting() {
        this.f10589a.setUncaughtExceptionHandler(new A40(this));
    }

    public final void quitThreadSafely(long j) {
        new Handler(this.f10589a.getLooper()).post(new RunnableC5873z40(this, j));
        this.f10589a.getLooper().quitSafely();
    }

    public final void startAndInitialize(long j, long j2) {
        a();
        new Handler(this.f10589a.getLooper()).post(new RunnableC5703y40(this, j, j2));
    }
}
