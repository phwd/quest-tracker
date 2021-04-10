package com.oculus.os;

import android.content.ComponentName;
import android.content.Context;
import android.os.Handler;
import android.os.IBinder;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ActivityManagerUtils {

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public interface ForegroundActivityObserver {
        void onForegroundActivitiesChanged(int i, int i2, boolean z);
    }

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public interface WindowLayoutObserver {
        void onFocusedWindowChanged(IBinder iBinder, int i);
    }

    public ActivityManagerUtils() {
        throw new RuntimeException("Stub!");
    }

    public boolean clearForegroundActivityObserver() {
        throw new RuntimeException("Stub!");
    }

    public boolean clearWindowLayoutObserver() {
        throw new RuntimeException("Stub!");
    }

    public String getForegroundApp(Context context) {
        throw new RuntimeException("Stub!");
    }

    public boolean setForegroundActivityObserver(ForegroundActivityObserver foregroundActivityObserver, Handler handler) {
        throw new RuntimeException("Stub!");
    }

    public boolean setIsForegroundPanelService(boolean z, ComponentName componentName) {
        throw new RuntimeException("Stub!");
    }

    public boolean setWindowLayoutObserver(WindowLayoutObserver windowLayoutObserver, Handler handler) {
        throw new RuntimeException("Stub!");
    }
}
