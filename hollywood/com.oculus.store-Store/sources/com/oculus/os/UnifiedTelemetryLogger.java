package com.oculus.os;

import android.content.Context;
import android.os.IBinder;
import android.os.PersistableBundle;
import android.os.UserHandle;
import java.util.List;

public class UnifiedTelemetryLogger implements IBinder.DeathRecipient {
    UnifiedTelemetryLogger() {
        throw new RuntimeException("Stub!");
    }

    public static UnifiedTelemetryLogger getInstance(Context context) {
        throw new RuntimeException("Stub!");
    }

    @Deprecated
    public static synchronized UnifiedTelemetryLogger getInstance() {
        synchronized (UnifiedTelemetryLogger.class) {
            throw new RuntimeException("Stub!");
        }
    }

    @Deprecated
    public synchronized void init(Context context) {
        throw new RuntimeException("Stub!");
    }

    public void binderDied() {
        throw new RuntimeException("Stub!");
    }

    public void reportEvent(AnalyticsEvent analyticsEvent, boolean lowLatency) {
        throw new RuntimeException("Stub!");
    }

    public void reportEvents(List<AnalyticsEvent> list, boolean lowLatency) {
        throw new RuntimeException("Stub!");
    }

    public void logExposure(AnalyticsEvent analyticsEvent, boolean lowLatency) {
        throw new RuntimeException("Stub!");
    }

    public void registerFunnel(String funnelName, int secondsToEndSinceLastUpdate) {
        throw new RuntimeException("Stub!");
    }

    public String startSession(String sessionName) {
        throw new RuntimeException("Stub!");
    }

    public String startSessionAsUser(String sessionName, UserHandle user) {
        throw new RuntimeException("Stub!");
    }

    public void stopSession(String sessionName) {
        throw new RuntimeException("Stub!");
    }

    public void stopSessionAsUser(String sessionName, UserHandle user) {
        throw new RuntimeException("Stub!");
    }

    public void stopSessionForAllUsers(String sessionName) {
        throw new RuntimeException("Stub!");
    }

    public void startFunnel(String funnelName) {
        throw new RuntimeException("Stub!");
    }

    public void startFunnel(String funnelName, long instanceId) {
        throw new RuntimeException("Stub!");
    }

    public void reportFunnelAction(String funnelName, String actionName, String tag, PersistableBundle actionData) {
        throw new RuntimeException("Stub!");
    }

    public void reportFunnelAction(String funnelName, long instanceId, String actionName, String tag, PersistableBundle actionData) {
        throw new RuntimeException("Stub!");
    }

    public void endFunnel(String funnelName) {
        throw new RuntimeException("Stub!");
    }

    public void endFunnel(String funnelName, long instanceId) {
        throw new RuntimeException("Stub!");
    }

    public boolean shouldDuplicateLogging() {
        throw new RuntimeException("Stub!");
    }

    public boolean shouldUseUnifiedTelemetryService() {
        throw new RuntimeException("Stub!");
    }
}
