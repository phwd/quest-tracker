package defpackage;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: YD  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class YD implements ThreadFactory {

    /* renamed from: a  reason: collision with root package name */
    public final AtomicInteger f9262a = new AtomicInteger(0);

    public YD(C1628aE aEVar) {
    }

    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.setName(String.format("arch_disk_io_%d", Integer.valueOf(this.f9262a.getAndIncrement())));
        return thread;
    }
}
