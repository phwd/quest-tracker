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
    private static String sDummySessionId;
    private static final Set<EndpointListener> sEndpointListeners = new HashSet();
    private static final ExecutorService sExecutorService = Executors.newSingleThreadExecutor();
    private static final Set<ForegroundListener> sForegroundListeners = new HashSet();
    private static volatile IAppState sInstance;
    private static volatile String sLastEndpointValue;
    private static volatile boolean sLastForegroundValue;

    public interface EndpointListener {
        void onEndpointChanged(String str);
    }

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

    public static synchronized void addForegroundListener(ForegroundListener foregroundListener, boolean z) {
        synchronized (GlobalAppState.class) {
            sForegroundListeners.add(foregroundListener);
            if (z && sLastForegroundValue) {
                final HashSet hashSet = new HashSet();
                hashSet.add(foregroundListener);
                sExecutorService.execute(new Runnable() {
                    /* class com.facebook.errorreporting.appstate.GlobalAppState.AnonymousClass1 */

                    public void run() {
                        GlobalAppState.informForegroundListeners(hashSet, true);
                    }
                });
            }
        }
    }

    public static synchronized void updateForegroundState(final boolean z) {
        synchronized (GlobalAppState.class) {
            if (z != sLastForegroundValue) {
                sLastForegroundValue = z;
                final HashSet hashSet = new HashSet();
                hashSet.addAll(sForegroundListeners);
                sExecutorService.execute(new Runnable() {
                    /* class com.facebook.errorreporting.appstate.GlobalAppState.AnonymousClass2 */

                    public void run() {
                        GlobalAppState.informForegroundListeners(hashSet, z);
                    }
                });
            }
        }
    }

    /* access modifiers changed from: private */
    public static void informForegroundListeners(Set<ForegroundListener> set, boolean z) {
        for (ForegroundListener foregroundListener : set) {
            if (z) {
                foregroundListener.onAppEnteredForeground();
            } else {
                foregroundListener.onAppLeftForeground();
            }
        }
    }

    public static synchronized void addEndpointListener(EndpointListener endpointListener) {
        synchronized (GlobalAppState.class) {
            sEndpointListeners.add(endpointListener);
        }
    }

    public static synchronized void updateEndpoint(final String str) {
        synchronized (GlobalAppState.class) {
            if (!str.equals(sLastEndpointValue)) {
                sLastEndpointValue = str;
                final HashSet hashSet = new HashSet();
                hashSet.addAll(sEndpointListeners);
                sExecutorService.execute(new Runnable() {
                    /* class com.facebook.errorreporting.appstate.GlobalAppState.AnonymousClass3 */

                    public void run() {
                        GlobalAppState.informEndpointListeners(hashSet, str);
                    }
                });
            }
        }
    }

    /* access modifiers changed from: private */
    public static void informEndpointListeners(Set<EndpointListener> set, String str) {
        for (EndpointListener endpointListener : set) {
            endpointListener.onEndpointChanged(str);
        }
    }

    public static String getSessionId() {
        if (sInstance != null) {
            return sInstance.getSessionId();
        }
        String str = sDummySessionId;
        return str != null ? str : "unknown";
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

    public static void setInstance(IAppState iAppState) {
        sInstance = iAppState;
    }

    public static void setDummySessionId(String str) {
        sDummySessionId = str;
    }

    @Nullable
    public static String getDummySessionId() {
        return sDummySessionId;
    }
}
