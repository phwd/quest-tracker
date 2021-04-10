package defpackage;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: pq0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ThreadFactoryC4299pq0 implements ThreadFactory {

    /* renamed from: a  reason: collision with root package name */
    public final String f11091a;
    public final AtomicInteger b = new AtomicInteger();
    public final ThreadFactory c = Executors.defaultThreadFactory();

    public ThreadFactoryC4299pq0(String str) {
        SE0.i(str, "Name must not be null");
        this.f11091a = str;
    }

    public Thread newThread(Runnable runnable) {
        Thread newThread = this.c.newThread(new RunnableC4367qC1(runnable, 0));
        String str = this.f11091a;
        int andIncrement = this.b.getAndIncrement();
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 13);
        sb.append(str);
        sb.append("[");
        sb.append(andIncrement);
        sb.append("]");
        newThread.setName(sb.toString());
        return newThread;
    }
}
