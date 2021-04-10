package com.facebook.errorreporting.appstate;

import com.facebook.common.build.config.BuildConfig;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GlobalAppState {
    private static String sDummySessionId = null;
    private static final ExecutorService sExecutorService = Executors.newSingleThreadExecutor();
    private static volatile IAppState sInstance;
    private static final Set<Object> sListeners = new HashSet();

    public interface IAppState {
        String getSessionId();
    }

    public static String getSessionId() {
        if (sInstance != null) {
            return sInstance.getSessionId();
        }
        if (sDummySessionId != null) {
            return sDummySessionId;
        }
        return BuildConfig.VERSION_NAME;
    }
}
