package com.oculus.notification_proxy;

import android.app.Application;

public class NotificationProxyApplication extends Application {
    private static final String ACTION_SEND_NOTIFICATION = "com.oculus.notification_proxy.SEND_NOTIFICATION";
    private static final String TAG = "NotificationProxyApplication";
    private BatteryReceiver mBatteryReceiver;
    private final ConnectivityEventReceiver mConnectivityEventReceiver = new ConnectivityEventReceiver();
    private ControllerNotifications mControllerNotifications;
    private OculusNotificationListenerService mListener;
    private final NotificationProxyBinder mNotificationProxyBinder = new NotificationProxyBinder(this);
    private VrPowerManagerClient mPowerManagerClient;
    private final SendNotificationBroadcastReceiver mSendNotificationReceiver = new SendNotificationBroadcastReceiver();
    private VolumeNotifications mVolumeNotifications;

    /* JADX WARN: Type inference failed for: r0v1, types: [com.oculus.notification_proxy.NotificationProxyBinder, android.os.IBinder] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onCreate() {
        /*
        // Method dump skipped, instructions count: 117
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.notification_proxy.NotificationProxyApplication.onCreate():void");
    }
}
