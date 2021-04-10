package com.oculus.common.devicelifecycle;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkRequest;
import android.os.PowerManager;
import androidx.annotation.Nullable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class DeviceLifecycleManager {
    public static final String ACTION_MOUNT_STATE_CHANGED = "com.oculus.intent.action.MOUNT_STATE_CHANGED";
    public static final String TAG = "DeviceLifecycleManager";
    @Nullable
    public static BroadcastReceiver sBroadcastReceiver;
    @Nullable
    public static Boolean sIsDeviceIdle;
    @Nullable
    public static Boolean sIsDeviceMounted;
    @Nullable
    public static Boolean sIsNetworkAvailable;
    @Nullable
    public static ConnectivityManager.NetworkCallback sNetworkCallback;
    public static final Set<DeviceLifecycleObserver> sObservers = new HashSet();

    public static /* synthetic */ String access$000() {
        return TAG;
    }

    public static synchronized boolean isDeviceIdleMode(Context context) {
        boolean isDeviceIdleMode;
        synchronized (DeviceLifecycleManager.class) {
            isDeviceIdleMode = ((PowerManager) Objects.requireNonNull((PowerManager) context.getSystemService(PowerManager.class), "PowerManager should not be null.")).isDeviceIdleMode();
        }
        return isDeviceIdleMode;
    }

    public static synchronized void registerBroadcastReceiver(Context context) {
        synchronized (DeviceLifecycleManager.class) {
            if (sBroadcastReceiver == null) {
                sBroadcastReceiver = new BroadcastReceiver() {
                    /* class com.oculus.common.devicelifecycle.DeviceLifecycleManager.AnonymousClass1 */

                    public void onReceive(Context context, Intent intent) {
                        String action = intent.getAction();
                        if ("com.oculus.intent.action.MOUNT_STATE_CHANGED".equals(action)) {
                            boolean z = intent.getExtras().getBoolean("state");
                            synchronized (DeviceLifecycleManager.class) {
                                DeviceLifecycleManager.sIsDeviceMounted = Boolean.valueOf(z);
                                for (DeviceLifecycleObserver deviceLifecycleObserver : DeviceLifecycleManager.sObservers) {
                                    deviceLifecycleObserver.onDeviceMountedStateChange(DeviceLifecycleManager.sIsDeviceMounted.booleanValue());
                                }
                            }
                        } else if ("android.os.action.DEVICE_IDLE_MODE_CHANGED".equals(action)) {
                            synchronized (DeviceLifecycleManager.class) {
                                DeviceLifecycleManager.sIsDeviceIdle = Boolean.valueOf(DeviceLifecycleManager.isDeviceIdleMode(context));
                                for (DeviceLifecycleObserver deviceLifecycleObserver2 : DeviceLifecycleManager.sObservers) {
                                    deviceLifecycleObserver2.onNetworkBlockedStateChange(DeviceLifecycleManager.sIsDeviceIdle.booleanValue());
                                }
                            }
                        }
                    }
                };
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("com.oculus.intent.action.MOUNT_STATE_CHANGED");
                intentFilter.addAction("android.os.action.DEVICE_IDLE_MODE_CHANGED");
                context.registerReceiver(sBroadcastReceiver, intentFilter);
                sIsDeviceIdle = Boolean.valueOf(isDeviceIdleMode(context));
                for (DeviceLifecycleObserver deviceLifecycleObserver : sObservers) {
                    deviceLifecycleObserver.onNetworkBlockedStateChange(sIsDeviceIdle.booleanValue());
                }
            }
        }
    }

    public static synchronized void registerDeviceLifecycleObserver(Context context, DeviceLifecycleObserver deviceLifecycleObserver) {
        synchronized (DeviceLifecycleManager.class) {
            if (!sObservers.contains(deviceLifecycleObserver)) {
                sObservers.add(deviceLifecycleObserver);
                Boolean bool = sIsDeviceMounted;
                if (bool != null) {
                    deviceLifecycleObserver.onDeviceMountedStateChange(bool.booleanValue());
                }
                Boolean bool2 = sIsDeviceIdle;
                if (bool2 != null) {
                    deviceLifecycleObserver.onNetworkBlockedStateChange(bool2.booleanValue());
                }
                Boolean bool3 = sIsNetworkAvailable;
                if (bool3 != null) {
                    deviceLifecycleObserver.onNetworkAvailabilityChange(bool3.booleanValue());
                }
                registerBroadcastReceiver(context);
                registerNetworkCallback(context);
            }
        }
    }

    public static synchronized void registerNetworkCallback(Context context) {
        synchronized (DeviceLifecycleManager.class) {
            if (sNetworkCallback == null) {
                sNetworkCallback = new ConnectivityManager.NetworkCallback() {
                    /* class com.oculus.common.devicelifecycle.DeviceLifecycleManager.AnonymousClass2 */

                    public void onAvailable(Network network) {
                        synchronized (DeviceLifecycleManager.class) {
                            DeviceLifecycleManager.sIsNetworkAvailable = true;
                            for (DeviceLifecycleObserver deviceLifecycleObserver : DeviceLifecycleManager.sObservers) {
                                deviceLifecycleObserver.onNetworkAvailabilityChange(true);
                            }
                        }
                    }

                    public void onLost(Network network) {
                        synchronized (DeviceLifecycleManager.class) {
                            DeviceLifecycleManager.sIsNetworkAvailable = false;
                            for (DeviceLifecycleObserver deviceLifecycleObserver : DeviceLifecycleManager.sObservers) {
                                deviceLifecycleObserver.onNetworkAvailabilityChange(false);
                            }
                        }
                    }
                };
                ((ConnectivityManager) Objects.requireNonNull((ConnectivityManager) context.getSystemService(ConnectivityManager.class), "ConnectivityManager should not be null.")).registerNetworkCallback(new NetworkRequest.Builder().addCapability(12).build(), sNetworkCallback);
            }
        }
    }

    public static synchronized void unregisterDeviceLifecycleObserver(Context context, DeviceLifecycleObserver deviceLifecycleObserver) {
        synchronized (DeviceLifecycleManager.class) {
            if (sObservers.contains(deviceLifecycleObserver)) {
                Set<DeviceLifecycleObserver> set = sObservers;
                set.remove(deviceLifecycleObserver);
                if (set.size() == 0) {
                    BroadcastReceiver broadcastReceiver = sBroadcastReceiver;
                    if (broadcastReceiver != null) {
                        context.unregisterReceiver(broadcastReceiver);
                        sBroadcastReceiver = null;
                    }
                    if (sNetworkCallback != null) {
                        ((ConnectivityManager) Objects.requireNonNull((ConnectivityManager) context.getSystemService(ConnectivityManager.class), "ConnectivityManager should not be null.")).unregisterNetworkCallback(sNetworkCallback);
                        sNetworkCallback = null;
                    }
                    sIsDeviceMounted = null;
                    sIsDeviceIdle = null;
                    sIsNetworkAvailable = null;
                }
            }
        }
    }
}
