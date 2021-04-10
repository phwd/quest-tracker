package com.oculus.os;

import android.content.Context;
import android.os.IBinder;
import android.os.PersistableBundle;
import java.util.List;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class UnifiedTelemetryLogger implements IBinder.DeathRecipient {
    public UnifiedTelemetryLogger() {
        throw new RuntimeException("Stub!");
    }

    public static synchronized UnifiedTelemetryLogger getInstance() {
        synchronized (UnifiedTelemetryLogger.class) {
            throw new RuntimeException("Stub!");
        }
    }

    public void binderDied() {
        throw new RuntimeException("Stub!");
    }

    public void endFunnel(String str) {
        throw new RuntimeException("Stub!");
    }

    public synchronized void init(Context context) {
        throw new RuntimeException("Stub!");
    }

    public void logExposure(AnalyticsEvent analyticsEvent, boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void registerFunnel(String str, int i) {
        throw new RuntimeException("Stub!");
    }

    public void reportEvent(AnalyticsEvent analyticsEvent, boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void reportEvents(List list, boolean z) {
        throw new RuntimeException("Stub!");
    }

    public void reportFunnelAction(String str, String str2, String str3, PersistableBundle persistableBundle) {
        throw new RuntimeException("Stub!");
    }

    public boolean shouldDuplicateLogging() {
        throw new RuntimeException("Stub!");
    }

    public boolean shouldUseUnifiedTelemetryService() {
        throw new RuntimeException("Stub!");
    }

    public void startFunnel(String str) {
        throw new RuntimeException("Stub!");
    }

    public void endFunnel(String str, long j) {
        throw new RuntimeException("Stub!");
    }

    public void reportFunnelAction(String str, long j, String str2, String str3, PersistableBundle persistableBundle) {
        throw new RuntimeException("Stub!");
    }

    public void startFunnel(String str, long j) {
        throw new RuntimeException("Stub!");
    }
}
