package com.oculus.os.chargecontrol;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import oculus.internal.NotificationChannelCompat;

public class ChargeMonitorService extends Service {
    private static final SysFsProperty BATTERY = new SysFsProperty("/sys/class/power_supply/battery/");
    private static final String fileName_ = (isSmbCharger() ? "charging_enabled" : "input_suspend");
    private TimerTask chargeMonitor_ = null;
    private final Timer timer_ = new Timer("charge-monitor");

    public IBinder onBind(Intent intent) {
        return null;
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        if (intent.getAction().equals("com.oculus.os.chargecontrol.action.start")) {
            PendingIntent activity = PendingIntent.getActivity(this, 0, new Intent(this, ChargeControl.class), 0);
            Notification.Builder builder = new Notification.Builder(this);
            builder.setContentTitle(getText(R.string.service_label)).setTicker(getText(R.string.service_label)).setContentText("¯\\_(ツ)_/¯").setSmallIcon(R.drawable.ic_notification).setContentIntent(activity).setPriority(-1);
            NotificationChannelCompat notificationChannelCompat = new NotificationChannelCompat();
            notificationChannelCompat.setupNotificationChannel(this, "com.oculus.os.chargecontrol.notif_channel", "Charge Control", "Notification channel for Charge Control");
            notificationChannelCompat.setChannelIdForNotification("com.oculus.os.chargecontrol.notif_channel", builder);
            startForeground(101, builder.build());
            this.chargeMonitor_ = new ChargeMonitorTask();
            this.timer_.scheduleAtFixedRate(this.chargeMonitor_, 0, 180000);
            Log.d("ChargeControl", "MONITOR: started");
        } else if (intent.getAction().equals("com.oculus.os.chargecontrol.action.stop")) {
            stopForeground(true);
            this.chargeMonitor_.cancel();
            this.chargeMonitor_ = null;
            Log.d("ChargeControl", "MONITOR: stopped");
        }
        return 1;
    }

    static int getCurrentCharge(Context context) {
        Intent registerReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        int intExtra = registerReceiver.getIntExtra("level", -1);
        int intExtra2 = registerReceiver.getIntExtra("scale", -1);
        return ((intExtra * 100) + (intExtra2 / 2)) / intExtra2;
    }

    static boolean isSmbCharger() {
        return Build.DEVICE.equals("Pacific");
    }

    static boolean isChargingEnabled() {
        try {
            long longValue = BATTERY.getLong(fileName_).get().longValue();
            if (isSmbCharger()) {
                if (longValue > 0) {
                    return true;
                }
                return false;
            } else if (longValue == 0) {
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            Log.e("ChargeControl", "Unable to read " + fileName_, e);
            return false;
        }
    }

    static boolean isCharging(Context context) {
        return context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED")).getIntExtra("status", -1) == 2;
    }

    private class ChargeMonitorTask extends TimerTask {
        private boolean isCancelled;
        private boolean isInTargetChargeRange_;

        private ChargeMonitorTask() {
            this.isCancelled = false;
            this.isInTargetChargeRange_ = false;
        }

        public void run() {
            int currentCharge = ChargeMonitorService.getCurrentCharge(ChargeMonitorService.this);
            Log.d("ChargeControl", "Current charge: " + currentCharge);
            synchronized (this) {
                if (!this.isCancelled) {
                    boolean isChargingEnabled = ChargeMonitorService.isChargingEnabled();
                    if (currentCharge >= 58) {
                        BusyWaiter.instance.start(ChargeMonitorService.this);
                        if (isChargingEnabled) {
                            enableCharging(false);
                            Log.i("ChargeControl", "NEW_STATE: discharge");
                        }
                    } else if (currentCharge <= 52) {
                        BusyWaiter.instance.stop();
                        if (!isChargingEnabled) {
                            enableCharging(true);
                            Log.i("ChargeControl", "NEW_STATE: charge");
                        }
                    } else {
                        tryBlinkLedGreen();
                    }
                }
            }
        }

        public boolean cancel() {
            synchronized (this) {
                BusyWaiter.instance.stop();
                tryCancelLedBlinking();
                enableCharging(true);
                Log.i("ChargeControl", "NEW_STATE: cancelled");
                this.isCancelled = true;
            }
            return super.cancel();
        }

        private final void enableCharging(boolean z) {
            long j = z ? 1 : 0;
            try {
                if (!ChargeMonitorService.isSmbCharger()) {
                    j ^= 1;
                }
                ChargeMonitorService.BATTERY.putLong(ChargeMonitorService.fileName_, j);
            } catch (IOException e) {
                Log.e("ChargeControl", "Failed to restore " + ChargeMonitorService.fileName_, e);
            }
        }

        private void tryBlinkLedGreen() {
            if (!this.isInTargetChargeRange_) {
                this.isInTargetChargeRange_ = true;
                Notification.Builder builder = new Notification.Builder(ChargeMonitorService.this);
                builder.setSmallIcon(R.drawable.ic_notification).setContentTitle("ChargeControl").setContentText("battery has reached shipping range").setLights(-16711936, 500, 500).setVibrate(new long[]{100, 100}).setPriority(1);
                NotificationChannelCompat notificationChannelCompat = new NotificationChannelCompat();
                notificationChannelCompat.setupNotificationChannel(ChargeMonitorService.this, "com.oculus.os.chargecontrol.notif_channel", "Charge Control", "Notification channel for Charge Control");
                notificationChannelCompat.setChannelIdForNotification("com.oculus.os.chargecontrol.notif_channel", builder);
                ((NotificationManager) ChargeMonitorService.this.getSystemService("notification")).notify(1397, builder.build());
            }
        }

        private void tryCancelLedBlinking() {
            if (this.isInTargetChargeRange_) {
                this.isInTargetChargeRange_ = false;
                ((NotificationManager) ChargeMonitorService.this.getSystemService("notification")).cancel(1397);
            }
        }
    }
}
