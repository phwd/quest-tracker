package defpackage;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: cG1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class ThreadFactoryC1987cG1 implements ThreadFactory {

    /* renamed from: a  reason: collision with root package name */
    public final AtomicInteger f9595a = new AtomicInteger(1);

    public final Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable, AbstractC2531fV.s(20, "gcm-task#", this.f9595a.getAndIncrement()));
        thread.setPriority(4);
        return thread;
    }
}
