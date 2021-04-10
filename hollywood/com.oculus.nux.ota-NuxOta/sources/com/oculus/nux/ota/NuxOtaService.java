package com.oculus.nux.ota;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.oculus.os.FirstTimeNuxManager;
import com.oculus.os.SettingsManager;
import com.oculus.os.SettingsObserverCallback;
import oculus.internal.NotificationBuilderCompat;
import oculus.internal.NotificationChannelCompat;

public class NuxOtaService extends Service {
    private static final String TAG = "NuxOtaService";
    private NuxOta mNuxOta;
    private ServiceHandler mServiceHandler;
    private Looper mServiceLooper;
    private SettingsObserverCallback mSettingsObserverCallback;

    public IBinder onBind(Intent intent) {
        return null;
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        return 1;
    }

    private enum ServiceMessage {
        INITIALIZE(0);
        
        final int value;

        private ServiceMessage(int i) {
            this.value = i;
        }

        public static ServiceMessage fromInt(int i) {
            ServiceMessage[] values = values();
            for (ServiceMessage serviceMessage : values) {
                if (serviceMessage.value == i) {
                    return serviceMessage;
                }
            }
            throw new UnsupportedOperationException("Unknown message type.");
        }
    }

    private final class ServiceHandler extends Handler {
        public ServiceHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            ServiceMessage fromInt = ServiceMessage.fromInt(message.what);
            if (NuxOta.DEBUG) {
                String str = NuxOtaService.TAG;
                Log.d(str, "Received a message: " + fromInt.toString());
            }
            if (AnonymousClass2.$SwitchMap$com$oculus$nux$ota$NuxOtaService$ServiceMessage[fromInt.ordinal()] == 1) {
                NuxOtaService.this.initialize();
            }
        }
    }

    /* renamed from: com.oculus.nux.ota.NuxOtaService$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$nux$ota$NuxOtaService$ServiceMessage = new int[ServiceMessage.values().length];

        static {
            try {
                $SwitchMap$com$oculus$nux$ota$NuxOtaService$ServiceMessage[ServiceMessage.INITIALIZE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public void onCreate() {
        if (NuxOta.DEBUG) {
            Log.d(TAG, "Creating NuxOtaService.");
        }
        HandlerThread handlerThread = new HandlerThread(TAG + " Handler Thread");
        handlerThread.start();
        this.mServiceLooper = handlerThread.getLooper();
        this.mServiceHandler = new ServiceHandler(this.mServiceLooper);
        this.mServiceHandler.sendEmptyMessage(ServiceMessage.INITIALIZE.value);
        Notification.Builder category = new NotificationBuilderCompat().getBuilder(this, "com.oculus.nux.ota.notif_channel").setOngoing(true).setSmallIcon(17301514).setCategory("service");
        NotificationChannelCompat notificationChannelCompat = new NotificationChannelCompat();
        notificationChannelCompat.setupNotificationChannel(this, "com.oculus.nux.ota.notif_channel", "nux_ota", "NUX OTA foreground service.");
        notificationChannelCompat.setChannelIdForNotification("com.oculus.nux.ota.notif_channel", category);
        startForeground(1111, category.build());
    }

    public void onDestroy() {
        if (NuxOta.DEBUG) {
            Log.d(TAG, "Destroying NuxOtaService.");
        }
        this.mServiceLooper.quit();
        unregisterSettingObserver();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private synchronized void initialize() {
        new BackfillSettings(getApplicationContext()).runBackfill();
        if (FirstTimeNuxManager.getFirstTimeNuxComplete(ActivityManager.getCurrentUser())) {
            Log.e(TAG, "Unable to initialize; NUX is completed.");
            stopSelf();
        } else if (this.mNuxOta == null) {
            if (NuxOta.DEBUG) {
                Log.d(TAG, "Initializing.");
            }
            this.mNuxOta = NuxOta.initialize(getApplicationContext(), R.raw.sfx_update_done);
            registerSettingsObservers();
        } else {
            Log.w(TAG, "Already initialized.");
        }
    }

    private void registerSettingsObservers() {
        this.mSettingsObserverCallback = new SettingsObserverCallback() {
            /* class com.oculus.nux.ota.NuxOtaService.AnonymousClass1 */

            public void onSettingChange(String str) {
                NuxOtaService.this.onSettingChanged(str);
            }
        };
        registerSettingObserver();
    }

    private void registerSettingObserver() {
        new SettingsManager(getApplicationContext()).registerSettingsObserver("first_time_nux_complete", this.mSettingsObserverCallback, new Handler(Looper.getMainLooper()));
    }

    private void unregisterSettingObserver() {
        new SettingsManager(getApplicationContext()).unregisterSettingsObserver("first_time_nux_ota_state", this.mSettingsObserverCallback);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void onSettingChanged(String str) {
        if (((str.hashCode() == 1007642250 && str.equals("first_time_nux_complete")) ? (char) 0 : 65535) == 0 && FirstTimeNuxManager.getFirstTimeNuxComplete(ActivityManager.getCurrentUser())) {
            if (NuxOta.DEBUG) {
                Log.d(TAG, "NUX completed. Stopping service.");
            }
            stopSelf();
        }
    }
}
