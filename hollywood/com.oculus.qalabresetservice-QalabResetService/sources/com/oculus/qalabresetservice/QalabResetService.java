package com.oculus.qalabresetservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.PowerManager;
import android.util.Log;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class QalabResetService extends Service {
    private static final long DEFAULT_RESET_TIME_MIN = 5;
    private static final long DEFAULT_RESET_TIME_MS = TimeUnit.MINUTES.toMillis(DEFAULT_RESET_TIME_MIN);
    private static final String TAG = QalabResetService.class.getSimpleName();
    private Timer mTimer;

    private void startTimerTask() {
        cancelTimerTask();
        this.mTimer = new Timer();
        this.mTimer.schedule(new TimerTask() {
            /* class com.oculus.qalabresetservice.QalabResetService.AnonymousClass1 */

            public void run() {
                Log.i(QalabResetService.TAG, "Device has not rebooted for 5 min, rebooting...");
                ((PowerManager) QalabResetService.this.getSystemService("power")).reboot(null);
            }
        }, DEFAULT_RESET_TIME_MS, 100);
        Log.i(TAG, "Starting reboot countdown for 5 min");
    }

    private void cancelTimerTask() {
        Timer timer = this.mTimer;
        if (timer != null) {
            timer.cancel();
            this.mTimer = null;
        }
        Log.i(TAG, "Canceled previous reboot countdown");
    }

    public void onDestroy() {
        super.onDestroy();
        cancelTimerTask();
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        startTimerTask();
        return 1;
    }

    public IBinder onBind(Intent intent) {
        return null;
    }
}
