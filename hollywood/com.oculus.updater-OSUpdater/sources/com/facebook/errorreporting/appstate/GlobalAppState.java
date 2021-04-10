package com.facebook.errorreporting.appstate;

import androidx.annotation.Nullable;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.infer.annotation.ThreadSafe;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Nullsafe(Nullsafe.Mode.LOCAL)
@ThreadSafe
public class GlobalAppState {
    @Nullable
    private static String sDummySessionId;
    private static final Set<Object> sEndpointListeners = new HashSet();
    private static final ExecutorService sExecutorService = Executors.newSingleThreadExecutor();
    private static final Set<Object> sForegroundListeners = new HashSet();
    private static volatile IAppState sInstance;

    public interface IAppState {
        String getSessionId();
    }

    public static String getSessionId() {
        if (sInstance != null) {
            return sInstance.getSessionId();
        }
        String str = sDummySessionId;
        return str != null ? str : "unknown";
    }
}
