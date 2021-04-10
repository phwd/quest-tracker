package X;

import java.util.concurrent.ThreadFactory;

/* renamed from: X.0k0  reason: invalid class name and case insensitive filesystem */
public class ThreadFactoryC05580k0 implements ThreadFactory {
    public final /* synthetic */ String A00;
    public final /* synthetic */ boolean A01;

    public ThreadFactoryC05580k0(String str, boolean z) {
        this.A00 = str;
        this.A01 = z;
    }

    public final Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable, this.A00);
        thread.setDaemon(this.A01);
        return thread;
    }
}
