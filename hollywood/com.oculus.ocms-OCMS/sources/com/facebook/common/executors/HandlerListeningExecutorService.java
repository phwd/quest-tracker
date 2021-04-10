package com.facebook.common.executors;

import android.os.Handler;
import com.google.common.util.concurrent.ListeningScheduledExecutorService;

public interface HandlerListeningExecutorService extends ListeningScheduledExecutorService {
    Handler getHandler();

    boolean isHandlerThread();

    void quit();
}
