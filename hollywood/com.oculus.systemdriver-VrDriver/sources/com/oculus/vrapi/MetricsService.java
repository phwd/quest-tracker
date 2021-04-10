package com.oculus.vrapi;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.oculus.metrics.OVRMonitorMetricsServiceInterface;
import java.util.Date;

public class MetricsService {
    private static final String METRICS_SERVICE_ACTIVITY_FULL_NAME = "com.oculus.ovrmonitormetricsservice.MetricsService";
    private static final String METRICS_SERVICE_ACTIVITY_NAME = "MetricsService";
    private static final String METRICS_SERVICE_NAME = "com.oculus.ovrmonitormetricsservice";
    private static final int MINIMUM_METRICS_SERVICE_VERSION = 14;
    private static final String TAG = MetricsService.class.getSimpleName();
    private static Object sMutex = new Object();
    private static ServiceConnection sServiceConnection = new ServiceConnection() {
        /* class com.oculus.vrapi.MetricsService.AnonymousClass1 */

        public void onServiceConnected(ComponentName name, IBinder service) {
            synchronized (MetricsService.sMutex) {
                String str = MetricsService.TAG;
                Log.d(str, "Connected to metrics service: " + name);
                OVRMonitorMetricsServiceInterface unused = MetricsService.sServiceInterface = OVRMonitorMetricsServiceInterface.Stub.asInterface(service);
            }
        }

        public void onServiceDisconnected(ComponentName name) {
            synchronized (MetricsService.sMutex) {
                Log.d(MetricsService.TAG, "Disconnected from metrics service");
                OVRMonitorMetricsServiceInterface unused = MetricsService.sServiceInterface = null;
            }
        }
    };
    private static boolean sServiceEnabled = true;
    private static OVRMonitorMetricsServiceInterface sServiceInterface = null;
    private static int sServiceVersion = -1;
    private static boolean shouldUnbindFromService = false;

    private static boolean isServiceBound() {
        boolean z;
        synchronized (sMutex) {
            z = sServiceInterface != null;
        }
        return z;
    }

    public static void bind(Context context) {
        synchronized (sMutex) {
            if (!isServiceBound()) {
                if (isServiceAvailable(context)) {
                    ComponentName serviceComponent = new ComponentName(METRICS_SERVICE_NAME, METRICS_SERVICE_ACTIVITY_FULL_NAME);
                    Intent serviceIntent = new Intent();
                    serviceIntent.setComponent(serviceComponent);
                    shouldUnbindFromService = context.bindService(serviceIntent, sServiceConnection, 1);
                }
            }
        }
    }

    public static void unbind(Context context) {
        synchronized (sMutex) {
            if (shouldUnbindFromService) {
                context.unbindService(sServiceConnection);
                shouldUnbindFromService = false;
                sServiceInterface = null;
            }
        }
    }

    public static boolean updateMetrics(Context context, String metricsJson) {
        boolean z;
        synchronized (sMutex) {
            if (sServiceEnabled && sServiceInterface != null) {
                try {
                    sServiceEnabled = sServiceInterface.updateMetrics2(context.getPackageName(), new Date().getTime(), metricsJson);
                } catch (RemoteException e) {
                    String str = TAG;
                    Log.d(str, "RemoteException, Can't send updated metrics to metrics service: " + e.getMessage());
                    sServiceEnabled = false;
                } catch (SecurityException e2) {
                    String str2 = TAG;
                    Log.d(str2, "SecurityException, Can't send updated metrics to metrics service: " + e2.getMessage());
                    sServiceEnabled = false;
                }
                if (!sServiceEnabled) {
                    unbind(context);
                }
            }
            z = sServiceEnabled;
        }
        return z;
    }

    public static boolean isServiceAvailable(Context context) {
        return isServiceAvailable(context, MINIMUM_METRICS_SERVICE_VERSION);
    }

    public static boolean isServiceAvailable(Context context, int minimumVersionCode) {
        if (sServiceVersion == -1) {
            try {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(METRICS_SERVICE_NAME, 128);
                if (packageInfo != null) {
                    sServiceVersion = packageInfo.versionCode;
                }
            } catch (PackageManager.NameNotFoundException e) {
            }
        }
        return sServiceVersion >= minimumVersionCode;
    }

    public static void shutdown(Context context) {
        unbind(context);
        sServiceVersion = -1;
        sServiceEnabled = true;
    }
}
