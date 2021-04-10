package X;

import java.util.concurrent.ThreadFactory;

public final class HF implements ThreadFactory {
    public final Thread newThread(Runnable runnable) {
        return new Thread(runnable, "com.facebook.papaya.random_thread");
    }
}
