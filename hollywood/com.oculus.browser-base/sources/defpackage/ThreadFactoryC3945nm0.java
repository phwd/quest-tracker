package defpackage;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/* renamed from: nm0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ThreadFactoryC3945nm0 implements ThreadFactory {

    /* renamed from: a  reason: collision with root package name */
    public final String f10511a;
    public final ThreadFactory b = Executors.defaultThreadFactory();

    public ThreadFactoryC3945nm0(String str) {
        SE0.i(str, "Name must not be null");
        this.f10511a = str;
    }

    public Thread newThread(Runnable runnable) {
        Thread newThread = this.b.newThread(new RunnableC4367qC1(runnable, 0));
        newThread.setName(this.f10511a);
        return newThread;
    }
}
