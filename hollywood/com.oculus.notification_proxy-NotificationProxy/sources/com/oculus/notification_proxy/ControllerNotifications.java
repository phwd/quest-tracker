package com.oculus.notification_proxy;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;
import com.google.common.collect.ImmutableMap;
import com.oculus.notification_proxy.VrPowerManagerClient;
import com.oculus.os.SettingsManager;
import com.oculus.os.SettingsObserverCallback;
import java.util.HashMap;
import java.util.Map;
import oculus.internal.BinderClient;
import oculus.internal.remote.IRemoteService;
import oculus.internal.remote.IRemoteServiceStatusCallback;
import oculus.internal.remote.RemoteStatus;

public class ControllerNotifications implements VrPowerManagerClient.Callback {
    private static final Map<ControllerState, Integer> CONTROLLER_STATE_NOTIFICATION_PRIORITY;
    private static final ControllerStateDetail DEFAULT_CONTROLLER_STATE_DETAIL = new ControllerStateDetail(ControllerState.Disconnected, 0);
    private static final int MSG_ENABLE_NOTIFICATIONS = 1;
    private static final int NOTIFICATION_DURATION_MS = 5000;
    private static final int NOTIFY_AFTER_FW_UPDATE_MS = 5000;
    private static final int NOTIFY_AFTER_MOUNT_MS = 5000;
    private static final int REMOTE_CRITICAL_BATTERY_LEVEL = 5;
    private static final int REMOTE_LOW_BATTERY_LEVEL = 10;
    private static final String TAG = "ControllerNotifications";
    private BinderClient<IRemoteService> mBinderClient = new BinderClient<IRemoteService>("OVRRemoteService", $$Lambda$ControllerNotifications$zMNP2yrGT96o5076GjiBmYHS7R4.INSTANCE) {
        /* class com.oculus.notification_proxy.ControllerNotifications.AnonymousClass1 */

        /* access modifiers changed from: protected */
        public synchronized void onServiceConnected(IRemoteService iRemoteService) {
            try {
                iRemoteService.registerStatusCallback(ControllerNotifications.this.mStatusCallback);
            } catch (RemoteException e) {
                Log.e(ControllerNotifications.TAG, "Failed to register with remote service", e);
            }
            return;
        }
    };
    private final Context mContext;
    private final HashMap<Integer, ControllerStateDetail> mControllerState = new HashMap<>();
    private boolean mHandTrackingEnabled = false;
    private final SettingsObserverCallback mHandTrackingObserver = new SettingsObserverCallback() {
        /* class com.oculus.notification_proxy.ControllerNotifications.AnonymousClass4 */

        public void onSettingChange(String str) {
            if (str.equals("hand_tracking_enabled")) {
                synchronized (ControllerNotifications.this) {
                    ControllerNotifications.this.mHandTrackingEnabled = ControllerNotifications.this.mSettingsManager.getBoolean("hand_tracking_enabled", false);
                }
            }
        }
    };
    private boolean mHeadsetIsMounted = false;
    private Handler mMessageHandler = new Handler() {
        /* class com.oculus.notification_proxy.ControllerNotifications.AnonymousClass5 */

        public void handleMessage(Message message) {
            if (message.what != ControllerNotifications.MSG_ENABLE_NOTIFICATIONS) {
                String str = ControllerNotifications.TAG;
                Log.e(str, "Invalid message received: " + message.what);
                return;
            }
            ControllerNotifications.this.handleEnableNotifications();
        }
    };
    private boolean mNotificationsEnabled = false;
    private final SettingsManager mSettingsManager;
    BroadcastReceiver mShutdownBroadcastReceiver = new BroadcastReceiver() {
        /* class com.oculus.notification_proxy.ControllerNotifications.AnonymousClass2 */

        public void onReceive(Context context, Intent intent) {
            synchronized (ControllerNotifications.this) {
                Log.d(ControllerNotifications.TAG, "Notified that system is shutting down");
                ControllerNotifications.this.mShuttingDown = true;
            }
        }
    };
    private boolean mShuttingDown = false;
    private IRemoteServiceStatusCallback mStatusCallback = new IRemoteServiceStatusCallback.Stub() {
        /* class com.oculus.notification_proxy.ControllerNotifications.AnonymousClass3 */

        public void onStatus(int i, RemoteStatus remoteStatus) {
            ControllerNotifications.this.updateNotificationsBasedOnStateChange(i, ControllerNotifications.this.updateControllerStateBasedOnStatus(i, remoteStatus), false);
        }
    };

    /* access modifiers changed from: package-private */
    public enum ControllerState {
        Disconnected,
        Updating,
        ConnectedBatteryNormal,
        ConnectedBatteryLow,
        ConnectedBatteryCritical,
        ConnectedBatteryDead
    }

    static {
        ImmutableMap.Builder builder = new ImmutableMap.Builder();
        ControllerState controllerState = ControllerState.ConnectedBatteryLow;
        Integer valueOf = Integer.valueOf((int) MSG_ENABLE_NOTIFICATIONS);
        CONTROLLER_STATE_NOTIFICATION_PRIORITY = builder.put(controllerState, valueOf).put(ControllerState.ConnectedBatteryCritical, valueOf).put(ControllerState.ConnectedBatteryDead, valueOf).put(ControllerState.ConnectedBatteryNormal, valueOf).put(ControllerState.Updating, 2).put(ControllerState.Disconnected, 3).build();
    }

    /* access modifiers changed from: private */
    public static class ControllerStateDetail {
        ControllerState controllerState;
        long lastUpdateTimestamp;

        public ControllerStateDetail(ControllerState controllerState2, long j) {
            this.controllerState = controllerState2;
            this.lastUpdateTimestamp = j;
        }
    }

    public ControllerNotifications(Context context) {
        this.mContext = context;
        this.mSettingsManager = new SettingsManager(this.mContext);
        this.mSettingsManager.registerSettingsObserver("hand_tracking_enabled", this.mHandTrackingObserver, new Handler());
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.ACTION_SHUTDOWN");
        context.registerReceiver(this.mShutdownBroadcastReceiver, intentFilter);
    }

    @Override // com.oculus.notification_proxy.VrPowerManagerClient.Callback
    public synchronized void onHeadsetMounted() {
        Log.v(TAG, "Headset was mounted, requesting that notifications are enabled");
        this.mHeadsetIsMounted = true;
        queueEnableNotifications(5000);
    }

    @Override // com.oculus.notification_proxy.VrPowerManagerClient.Callback
    public synchronized void onHeadsetUnmounted() {
        Log.v(TAG, "Handling unmount notification");
        this.mHeadsetIsMounted = false;
        disableNotifications();
        NotificationManager notificationManager = (NotificationManager) this.mContext.getSystemService("notification");
        notificationManager.cancel(NotificationIds.CONTROLLER_BATTERY);
        notificationManager.cancel(NotificationIds.CONTROLLER_DISCONNECTED);
    }

    private synchronized void disableNotifications() {
        this.mMessageHandler.removeMessages(MSG_ENABLE_NOTIFICATIONS);
        this.mNotificationsEnabled = false;
    }

    private synchronized void queueEnableNotifications(int i) {
        String str = TAG;
        Log.v(str, "Queueing enable of notifications in " + i + "ms");
        this.mMessageHandler.sendEmptyMessageDelayed(MSG_ENABLE_NOTIFICATIONS, (long) i);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private synchronized void handleEnableNotifications() {
        Log.d(TAG, "Enabling notifications");
        this.mNotificationsEnabled = true;
        IRemoteService service = this.mBinderClient.getService();
        if (service == null) {
            Log.e(TAG, "Remote service not available.  Cannot display notifs.");
            return;
        }
        try {
            int[] supportedDeviceTypes = service.getSupportedDeviceTypes();
            if (!(supportedDeviceTypes.length == 2 && (supportedDeviceTypes[0] ^ MSG_ENABLE_NOTIFICATIONS) == supportedDeviceTypes[MSG_ENABLE_NOTIFICATIONS])) {
                Log.wtf(TAG, "Supported device types are not only DEVICE_PRIMARY(0) and DEVICE_SECONDARY(1) any more");
            }
            RemoteStatus pairedDeviceStatus = service.getPairedDeviceStatus(supportedDeviceTypes[0]);
            RemoteStatus pairedDeviceStatus2 = service.getPairedDeviceStatus(supportedDeviceTypes[MSG_ENABLE_NOTIFICATIONS]);
            ControllerState updateControllerStateBasedOnStatus = updateControllerStateBasedOnStatus(supportedDeviceTypes[0], pairedDeviceStatus);
            updateControllerStateBasedOnStatus(supportedDeviceTypes[MSG_ENABLE_NOTIFICATIONS], pairedDeviceStatus2);
            updateNotificationsBasedOnStateChange(supportedDeviceTypes[0], updateControllerStateBasedOnStatus, true);
        } catch (RemoteException e) {
            Log.e(TAG, "Exception communicating with remote service", e);
        } catch (IllegalStateException unused) {
            Log.e(TAG, "Remote service became unavailable. Cannot display notifs.");
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private synchronized ControllerState updateControllerStateBasedOnStatus(int i, RemoteStatus remoteStatus) {
        ControllerState controllerState;
        controllerState = this.mControllerState.getOrDefault(Integer.valueOf(i), DEFAULT_CONTROLLER_STATE_DETAIL).controllerState;
        this.mControllerState.put(Integer.valueOf(i), new ControllerStateDetail(getCurrentControllerState(remoteStatus), SystemClock.elapsedRealtime()));
        return controllerState;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private synchronized void updateNotificationsBasedOnStateChange(int i, ControllerState controllerState, boolean z) {
        ControllerState controllerState2 = this.mControllerState.getOrDefault(Integer.valueOf(i), DEFAULT_CONTROLLER_STATE_DETAIL).controllerState;
        if (controllerState == ControllerState.Updating && controllerState2 != controllerState && this.mHeadsetIsMounted) {
            Log.i(TAG, "Exited firmware update, temporarily suppress notifications");
            disableNotifications();
            queueEnableNotifications(5000);
        } else if (!this.mShuttingDown && this.mNotificationsEnabled && !this.mHandTrackingEnabled) {
            if (controllerState != controllerState2 || z) {
                String str = TAG;
                Log.i(str, "Updating input notifications based on state " + controllerState2);
                updateConnectedNotification(i, controllerState2);
                updateBatteryNotifications(i, controllerState2);
            }
        }
    }

    private static boolean isControllerBatteryLow(ControllerState controllerState) {
        return controllerState == ControllerState.ConnectedBatteryLow || controllerState == ControllerState.ConnectedBatteryDead;
    }

    private void notifySingleControllerDisconnected(int i) {
        NotificationBuildingHelper.notify(this.mContext, i == 0 ? R.string.dual_controller_right_controller_disconnected_title : R.string.dual_controller_left_controller_disconnected_title, R.string.dual_controller_disconnected_text, NotificationIds.CONTROLLER_DISCONNECTED);
    }

    private void notifySingleControllerBatteryLow(int i) {
        NotificationBuildingHelper.notify(this.mContext, i == 0 ? R.string.dual_controller_right_controller_low_battery_title : i == MSG_ENABLE_NOTIFICATIONS ? R.string.dual_controller_left_controller_low_battery_title : R.string.controller_low_battery_title, R.string.dual_controller_low_battery_text, NotificationIds.CONTROLLER_BATTERY);
    }

    private void notifyBothControllerBatteryLow() {
        NotificationBuildingHelper.notify(this.mContext, R.string.dual_controller_both_controller_low_battery_title, R.string.dual_controller_low_battery_text, NotificationIds.CONTROLLER_BATTERY);
    }

    private static boolean stateNotificationOverridden(ControllerState controllerState, ControllerStateDetail controllerStateDetail) {
        ControllerState controllerState2 = controllerStateDetail.controllerState;
        long j = controllerStateDetail.lastUpdateTimestamp;
        if (CONTROLLER_STATE_NOTIFICATION_PRIORITY.getOrDefault(controllerState2, 0).intValue() <= CONTROLLER_STATE_NOTIFICATION_PRIORITY.getOrDefault(controllerState, 0).intValue() || SystemClock.elapsedRealtime() - j >= 5000) {
            return false;
        }
        return true;
    }

    private void updateConnectedNotification(int i, ControllerState controllerState) {
        int i2 = i ^ MSG_ENABLE_NOTIFICATIONS;
        ControllerState controllerState2 = this.mControllerState.getOrDefault(Integer.valueOf(i2), DEFAULT_CONTROLLER_STATE_DETAIL).controllerState;
        ControllerState controllerState3 = ControllerState.Disconnected;
        if (controllerState == controllerState3) {
            if (controllerState2 == controllerState3) {
                ((NotificationManager) this.mContext.getSystemService("notification")).cancel(NotificationIds.CONTROLLER_DISCONNECTED);
                NotificationBuildingHelper.notify(this.mContext, R.string.dual_controller_both_controller_disconnected_title, R.string.dual_controller_disconnected_text, NotificationIds.CONTROLLER_DISCONNECTED);
                return;
            }
            notifySingleControllerDisconnected(i);
        } else if (controllerState2 != controllerState3) {
            ((NotificationManager) this.mContext.getSystemService("notification")).cancel(NotificationIds.CONTROLLER_DISCONNECTED);
        } else {
            notifySingleControllerDisconnected(i2);
        }
    }

    private void updateBatteryNotifications(int i, ControllerState controllerState) {
        ControllerStateDetail orDefault = this.mControllerState.getOrDefault(Integer.valueOf(i ^ MSG_ENABLE_NOTIFICATIONS), DEFAULT_CONTROLLER_STATE_DETAIL);
        ControllerState controllerState2 = orDefault.controllerState;
        if (isControllerBatteryLow(controllerState)) {
            if (isControllerBatteryLow(controllerState2)) {
                notifyBothControllerBatteryLow();
            } else if (!stateNotificationOverridden(controllerState, orDefault)) {
                notifySingleControllerBatteryLow(i);
            }
        } else if (controllerState == ControllerState.ConnectedBatteryNormal) {
            NotificationManager notificationManager = (NotificationManager) this.mContext.getSystemService("notification");
            if (controllerState2 == ControllerState.ConnectedBatteryNormal) {
                notificationManager.cancel(NotificationIds.CONTROLLER_BATTERY);
            }
        }
    }

    private static ControllerState getCurrentControllerState(RemoteStatus remoteStatus) {
        if (remoteStatus == null || remoteStatus.isDisconnected()) {
            return ControllerState.Disconnected;
        }
        if (remoteStatus.isUpdating()) {
            return ControllerState.Updating;
        }
        if (remoteStatus.batteryLevel == 0 || remoteStatus.isBatteryDead()) {
            return ControllerState.ConnectedBatteryDead;
        }
        if (remoteStatus.batteryLevel < REMOTE_LOW_BATTERY_LEVEL) {
            return ControllerState.ConnectedBatteryLow;
        }
        return ControllerState.ConnectedBatteryNormal;
    }
}
