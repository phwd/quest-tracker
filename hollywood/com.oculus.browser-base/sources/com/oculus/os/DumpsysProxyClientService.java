package com.oculus.os;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class DumpsysProxyClientService extends Service {
    public DumpsysProxyClientService() {
        throw new RuntimeException("Stub!");
    }

    public abstract String dumpState();

    public abstract String getAppName();

    public IBinder onBind(Intent intent) {
        throw new RuntimeException("Stub!");
    }

    public void onCreate() {
        throw new RuntimeException("Stub!");
    }
}
