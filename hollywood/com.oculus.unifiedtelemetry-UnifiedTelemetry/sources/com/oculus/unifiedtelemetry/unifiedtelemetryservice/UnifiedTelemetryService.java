package com.oculus.unifiedtelemetry.unifiedtelemetryservice;

import X.AbstractC0096Hu;
import X.C0515sp;
import X.Mu;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.oculus.unifiedtelemetry.unifiedlogging.LoggingHandler;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class UnifiedTelemetryService extends Service {
    public static final String METHOD_ADD_SERVICE = "addService";
    public static final String SERVICE_MANAGER_CLASS = "android.os.ServiceManager";
    public static final String TAG = "UnifiedTelemetryService";
    @Inject
    @Eager
    public LoggingHandler mLoggingHandler;

    public final int onStartCommand(Intent intent, int i, int i2) {
        try {
            Class.forName(SERVICE_MANAGER_CLASS).getMethod(METHOD_ADD_SERVICE, String.class, IBinder.class).invoke(null, TAG, this.mLoggingHandler.binder);
            return 1;
        } catch (ReflectiveOperationException e) {
            Mu.A02(TAG, "Service manager call to add service failed", e);
            return 1;
        }
    }

    public final IBinder onBind(Intent intent) {
        return this.mLoggingHandler.binder;
    }

    public final void onCreate() {
        super.onCreate();
        this.mLoggingHandler = (LoggingHandler) C0515sp.A00(114, AbstractC0096Hu.get(this));
    }
}
