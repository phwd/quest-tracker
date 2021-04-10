package com.oculus.video.analytics;

import android.content.Context;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Handler;
import androidx.core.app.NotificationCompat;

public class PowerMonitor {
    private static final long MONITORING_INTERVAL_MS = 1000;
    private final BatteryManager batteryManager;
    private double chargeDrainedMah = 0.0d;
    private final Context context;
    private double lastCurrentMa = 0.0d;
    private long lastMeasurementTimeMs = 0;
    private final Runnable monitor = new Runnable() {
        /* class com.oculus.video.analytics.PowerMonitor.AnonymousClass1 */

        public void run() {
            try {
                if (!PowerMonitor.this.isChargingOrFull()) {
                    long currentTimeMillis = System.currentTimeMillis();
                    double currentMa = PowerMonitor.this.getCurrentMa();
                    if (PowerMonitor.this.lastMeasurementTimeMs > 0) {
                        PowerMonitor.this.chargeDrainedMah += (((double) (currentTimeMillis - PowerMonitor.this.lastMeasurementTimeMs)) / 3600000.0d) * ((PowerMonitor.this.lastCurrentMa + currentMa) / 2.0d);
                    }
                    PowerMonitor.this.lastMeasurementTimeMs = currentTimeMillis;
                    PowerMonitor.this.lastCurrentMa = currentMa;
                } else {
                    PowerMonitor.this.lastMeasurementTimeMs = 0;
                }
            } finally {
                PowerMonitor.this.monitoringHandler.postDelayed(PowerMonitor.this.monitor, PowerMonitor.MONITORING_INTERVAL_MS);
            }
        }
    };
    private final Handler monitoringHandler;

    public PowerMonitor(Context context2, Handler handler) {
        this.context = context2;
        this.monitoringHandler = handler;
        this.batteryManager = (BatteryManager) context2.getSystemService("batterymanager");
        this.monitor.run();
    }

    public void release() {
        this.monitoringHandler.removeCallbacks(this.monitor);
    }

    public double getChargeDrainedMah() {
        return this.chargeDrainedMah;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean isChargingOrFull() {
        int intExtra = this.context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED")).getIntExtra(NotificationCompat.CATEGORY_STATUS, -1);
        return intExtra == 2 || intExtra == 5;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private double getCurrentMa() {
        return (double) Math.round(((float) this.batteryManager.getIntProperty(2)) / 1000.0f);
    }
}
