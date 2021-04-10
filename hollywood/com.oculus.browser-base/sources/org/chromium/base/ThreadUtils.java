package org.chromium.base;

import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import java.util.concurrent.FutureTask;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ThreadUtils {

    /* renamed from: a  reason: collision with root package name */
    public static final Object f10596a = new Object();
    public static Handler b;

    public static void a() {
        if (!i()) {
            throw new IllegalStateException("Must be called on the UI thread.");
        }
    }

    public static Handler b() {
        boolean z;
        C5649xm1 xm1;
        synchronized (f10596a) {
            if (b == null) {
                b = new Handler(Looper.getMainLooper());
                z = true;
            } else {
                z = false;
            }
        }
        if (z && (xm1 = TraceEvent.G) != null) {
            xm1.j.set(true);
            if (!i()) {
                d(new RunnableC5479wm1(xm1));
            } else {
                Looper.myQueue().addIdleHandler(xm1);
                xm1.b();
            }
        }
        return b;
    }

    public static Looper c() {
        return b().getLooper();
    }

    @Deprecated
    public static void d(Runnable runnable) {
        b().post(runnable);
    }

    @Deprecated
    public static void e(Runnable runnable, long j) {
        b().postDelayed(runnable, j);
    }

    @Deprecated
    public static FutureTask f(FutureTask futureTask) {
        if (i()) {
            futureTask.run();
        } else {
            b().post(futureTask);
        }
        return futureTask;
    }

    @Deprecated
    public static void g(Runnable runnable) {
        if (i()) {
            runnable.run();
        } else {
            b().post(runnable);
        }
    }

    @Deprecated
    public static void h(Runnable runnable) {
        if (i()) {
            runnable.run();
            return;
        }
        FutureTask futureTask = new FutureTask(runnable, null);
        b().post(futureTask);
        try {
            futureTask.get();
        } catch (Exception e) {
            throw new RuntimeException("Exception occurred while waiting for runnable", e);
        }
    }

    public static boolean i() {
        return b().getLooper() == Looper.myLooper();
    }

    public static boolean isThreadPriorityAudio(int i) {
        return Process.getThreadPriority(i) == -16;
    }

    public static void setThreadPriorityAudio(int i) {
        Process.setThreadPriority(i, -16);
    }
}
