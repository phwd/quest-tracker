package org.chromium.base.task;

import J.N;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicReferenceArray;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PostTask {

    /* renamed from: a  reason: collision with root package name */
    public static final Object f10598a = new Object();
    public static List b = new ArrayList();
    public static volatile boolean c;
    public static final Executor d = new C3113iu();
    public static AtomicReferenceArray e;

    static {
        AtomicReferenceArray atomicReferenceArray = new AtomicReferenceArray(5);
        atomicReferenceArray.set(0, new ZD());
        e = atomicReferenceArray;
    }

    public static BS0 a(C3070if1 if1) {
        return ((AbstractC0624Ke1) e.get(if1.m)).a(if1);
    }

    public static void b(C3070if1 if1, Runnable runnable, long j) {
        if (!c || if1.o) {
            ((AbstractC0624Ke1) e.get(if1.m)).b(if1, runnable, j);
            return;
        }
        C3070if1 d2 = if1.d();
        N.MTILOhAQ(d2.j, d2.k, d2.l, d2.m, d2.n, runnable, j, runnable.getClass().getName());
    }

    public static void c(C3070if1 if1, Runnable runnable) {
        if (((AbstractC0624Ke1) e.get(if1.m)).d(if1)) {
            runnable.run();
        } else {
            b(if1, runnable, 0);
        }
    }

    @Deprecated
    public static Object d(C3070if1 if1, Callable callable) {
        FutureTask futureTask = new FutureTask(callable);
        c(if1, futureTask);
        try {
            return futureTask.get();
        } catch (Exception e2) {
            throw new RuntimeException(e2);
        }
    }

    @Deprecated
    public static void e(C3070if1 if1, Runnable runnable) {
        FutureTask futureTask = new FutureTask(runnable, null);
        c(if1, futureTask);
        try {
            futureTask.get();
        } catch (Exception e2) {
            throw new RuntimeException(e2);
        }
    }

    public static void onNativeSchedulerReady() {
        List<C2900hf1> list;
        c = true;
        synchronized (f10598a) {
            list = b;
            b = null;
        }
        for (C2900hf1 hf1 : list) {
            hf1.e();
        }
    }

    public static void onNativeSchedulerShutdownForTesting() {
    }
}
