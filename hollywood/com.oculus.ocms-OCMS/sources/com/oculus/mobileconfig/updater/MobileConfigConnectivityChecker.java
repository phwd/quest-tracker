package com.oculus.mobileconfig.updater;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import com.facebook.debug.log.BLog;
import com.facebook.infer.annotation.Nullsafe;
import com.oculus.executors.OculusThreadExecutor;
import com.oculus.util.network.NetworkUtils;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class MobileConfigConnectivityChecker extends BroadcastReceiver {
    private static String TAG = null;
    public static final int WIFI_CHECK_RETRY_COUNT = 5;
    private static final long WIFI_CHECK_RETRY_INTERVAL = TimeUnit.SECONDS.toMillis(5);
    public static CountDownLatch wifiCheckCountDownLatch;

    public MobileConfigConnectivityChecker(String str) {
        TAG = str + MobileConfigConnectivityChecker.class.getSimpleName();
        wifiCheckCountDownLatch = new CountDownLatch(1);
    }

    public static void registerReceiver(Context context, String str) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        context.registerReceiver(new MobileConfigConnectivityChecker(str), intentFilter);
    }

    public static void unregisterReceiver(Context context, BroadcastReceiver broadcastReceiver) {
        context.unregisterReceiver(broadcastReceiver);
    }

    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action == null) {
            BLog.w(TAG, "ConnectivityBroadcastReceiver called with null action");
            return;
        }
        BLog.d(TAG, "ConnectivityBroadcastReceiver called with action = %s", action);
        if (!"android.net.conn.CONNECTIVITY_CHANGE".equals(action)) {
            BLog.w(TAG, "Unsupported action");
        }
        if (NetworkUtils.isWifiConnected(context.getApplicationContext())) {
            BLog.d(TAG, "ConnectivityBroadcastReceiver isWifiConnected is true");
            updateCheckWithRetry(context.getApplicationContext(), new Handler(Looper.getMainLooper()), 5);
            return;
        }
        BLog.d(TAG, "ConnectivityBroadcastReceiver isWifiConnected is false");
    }

    public void updateCheckWithRetry(final Context context, final Handler handler, final int i) {
        BLog.d(TAG, "updateCheckWithRetry, retryCount = %d", Integer.valueOf(i));
        handler.postDelayed(new Runnable() {
            /* class com.oculus.mobileconfig.updater.MobileConfigConnectivityChecker.AnonymousClass1 */

            public void run() {
                OculusThreadExecutor.getInstance().execute(new Runnable() {
                    /* class com.oculus.mobileconfig.updater.MobileConfigConnectivityChecker.AnonymousClass1.AnonymousClass1 */

                    public void run() {
                        if (NetworkUtils.isOculusAddressReachable()) {
                            MobileConfigConnectivityChecker.unregisterReceiver(context, MobileConfigConnectivityChecker.this);
                            MobileConfigConnectivityChecker.wifiCheckCountDownLatch.countDown();
                            return;
                        }
                        MobileConfigConnectivityChecker.this.updateCheckWithRetry(context, handler, i - 1);
                    }
                });
            }
        }, WIFI_CHECK_RETRY_INTERVAL);
    }
}
