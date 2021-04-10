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
    public static long appStartTimeMs;
    @Nullable
    private static String sDummySessionId = null;
    private static final ExecutorService sExecutorService = Executors.newSingleThreadExecutor();
    private static volatile IAppState sInstance;
    private static volatile boolean sLastForegroundValue;
    private static final Set<ForegroundListener> sListeners = new HashSet();

    public enum ErrorReportingSystem {
        NONE_INITIALIZED_YET,
        LACRIMA,
        APP_STATE_LOGGER_AND_ACRA
    }

    public interface ForegroundListener {
        void onAppEnteredForeground();

        void onAppLeftForeground();
    }

    public interface IAppState {
        ErrorReportingSystem getActiveErrorReportingSystem();

        @Nullable
        AppStateCustomData getAppStateCustomData();

        @Nullable
        AppStateForegroundTime getAppStateForegroundTime();

        String getEndpoint();

        String getNavigationModule();

        String getSessionId();

        boolean isAppInForegroundV1();

        boolean isAppInForegroundV2();
    }

    public interface IAppStateUpdatable {
        void setForeground(boolean z);

        void setSessionId(String str);

        void updateEndpoint(String str);

        void updateNavigationModule(String str);
    }

    public static synchronized void addForegroundListener(ForegroundListener listener, boolean performInitialCallbackIfInForeground) {
        synchronized (GlobalAppState.class) {
            sListeners.add(listener);
            if (performInitialCallbackIfInForeground && sLastForegroundValue) {
                final Set<ForegroundListener> newListener = new HashSet<>();
                newListener.add(listener);
                sExecutorService.execute(new Runnable() {
                    /* class com.facebook.errorreporting.appstate.GlobalAppState.AnonymousClass1 */

                    public void run() {
                        GlobalAppState.informListeners(newListener, true);
                    }
                });
            }
        }
    }

    public static synchronized void updateForegroundState(final boolean isInForeground) {
        synchronized (GlobalAppState.class) {
            if (isInForeground != sLastForegroundValue) {
                sLastForegroundValue = isInForeground;
                final HashSet<ForegroundListener> listeners = new HashSet<>();
                listeners.addAll(sListeners);
                sExecutorService.execute(new Runnable() {
                    /* class com.facebook.errorreporting.appstate.GlobalAppState.AnonymousClass2 */

                    public void run() {
                        GlobalAppState.informListeners(listeners, isInForeground);
                    }
                });
            }
        }
    }

    /* access modifiers changed from: private */
    public static void informListeners(Set<ForegroundListener> listeners, boolean isInForeground) {
        for (ForegroundListener foregroundListener : listeners) {
            if (isInForeground) {
                foregroundListener.onAppEnteredForeground();
            } else {
                foregroundListener.onAppLeftForeground();
            }
        }
    }

    public static String getSessionId() {
        if (sInstance != null) {
            return sInstance.getSessionId();
        }
        if (sDummySessionId != null) {
            return sDummySessionId;
        }
        return "unknown";
    }

    public static boolean isAppInForegroundV1() {
        if (sInstance == null) {
            return false;
        }
        return sInstance.isAppInForegroundV1();
    }

    public static boolean isAppInForegroundV2() {
        if (sInstance == null) {
            return false;
        }
        return sInstance.isAppInForegroundV2();
    }

    public static String getEndpoint() {
        if (sInstance == null) {
            return "";
        }
        return sInstance.getEndpoint();
    }

    public static String getNavigationModule() {
        if (sInstance == null) {
            return "";
        }
        return sInstance.getNavigationModule();
    }

    public static ErrorReportingSystem getActiveErrorReportingSystem() {
        if (sInstance == null) {
            return ErrorReportingSystem.NONE_INITIALIZED_YET;
        }
        return sInstance.getActiveErrorReportingSystem();
    }

    @Nullable
    public static AppStateForegroundTime getAppStateForegroundTime() {
        if (sInstance == null) {
            return null;
        }
        return sInstance.getAppStateForegroundTime();
    }

    @Nullable
    public static IAppState getInstance() {
        return sInstance;
    }

    public static void setInstance(IAppState appState) {
        sInstance = appState;
    }

    public static void setDummySessionId(String sessionId) {
        sDummySessionId = sessionId;
    }

    @Nullable
    public static String getDummySessionId() {
        return sDummySessionId;
    }
}
