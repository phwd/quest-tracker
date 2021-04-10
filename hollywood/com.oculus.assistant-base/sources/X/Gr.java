package X;

import java.util.concurrent.ThreadFactory;

public final class Gr implements ThreadFactory {
    public final Thread newThread(Runnable runnable) {
        return new Thread(runnable, "com.facebook.papaya.api_thread");
    }
}
