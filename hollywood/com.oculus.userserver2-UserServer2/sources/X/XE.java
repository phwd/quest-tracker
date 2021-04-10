package X;

import java.util.concurrent.ThreadFactory;

public class XE implements ThreadFactory {
    public final /* synthetic */ String A00;
    public final /* synthetic */ boolean A01 = true;

    public XE(String str) {
        this.A00 = str;
    }

    public final Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable, this.A00);
        thread.setDaemon(this.A01);
        return thread;
    }
}
