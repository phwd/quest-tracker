package com.oculus.nux.ota;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.util.Log;
import com.oculus.osupdaterapi.OsUpdater;
import com.oculus.osupdaterapi.UpdaterOtaAvailability;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class OsUpdateTracker {
    private static final String TAG = ("NuxOta" + OsUpdateTracker.class.getSimpleName());
    private static final long UPDATE_STALLED_TIMEOUT_MS = TimeUnit.MINUTES.toMillis(5);
    private BroadcastReceiver mBatteryStatusReceiver;
    private Context mContext;
    private boolean mIsUpdateStallTimerPaused = false;
    private NuxOta mNuxOta;
    private float mProgress = 0.0f;
    private NuxOtaSettings mSettings;
    private StateMachine mStateMachine;
    private Telemetry mTelemetry;
    private BroadcastReceiver mUpdateReceiver;
    private Timer mUpdateStallTimer;
    private UpdateStallTimerTask mUpdateStallTimerTask;

    /* access modifiers changed from: private */
    public static class UpdateAvailabilityCallback implements OsUpdater.UpdaterOtaAvailabilityCallback {
        @Override // com.oculus.osupdaterapi.OsUpdater.UpdaterOtaAvailabilityCallback
        public void onReceive(UpdaterOtaAvailability updaterOtaAvailability) {
        }

        private UpdateAvailabilityCallback() {
        }
    }

    /* access modifiers changed from: private */
    public static class UpdateProgressCallback implements OsUpdater.OtaUpdateProgressCallback {
        @Override // com.oculus.osupdaterapi.OsUpdater.OtaUpdateProgressCallback
        public void onComplete() {
        }

        @Override // com.oculus.osupdaterapi.OsUpdater.OtaUpdateProgressCallback
        public void onError(String str) {
        }

        @Override // com.oculus.osupdaterapi.OsUpdater.OtaUpdateProgressCallback
        public void onProgress(float f) {
        }

        private UpdateProgressCallback() {
        }
    }

    /* access modifiers changed from: private */
    public class UpdateStallTimerTask extends TimerTask {
        private UpdateStallTimerTask() {
        }

        public void run() {
            OsUpdateTracker.this.setUpdateStalled(true);
        }
    }

    public OsUpdateTracker(Context context, NuxOta nuxOta, StateMachine stateMachine, NuxOtaSettings nuxOtaSettings, Telemetry telemetry) {
        this.mContext = context;
        this.mNuxOta = nuxOta;
        this.mStateMachine = stateMachine;
        this.mSettings = nuxOtaSettings;
        this.mTelemetry = telemetry;
    }

    public void initialize() {
        registerUpdateReceiver();
        registerBatteryStatusReceiver();
    }

    private void registerUpdateReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.oculus.updater.STATE_NOTIFICATION");
        this.mUpdateReceiver = new BroadcastReceiver() {
            /* class com.oculus.nux.ota.OsUpdateTracker.AnonymousClass1 */

            public void onReceive(Context context, Intent intent) {
                if ("com.oculus.updater.STATE_NOTIFICATION".equals(intent.getAction())) {
                    OsUpdateTracker.this.onOsUpdateStatusChanged(intent);
                }
            }
        };
        this.mContext.registerReceiver(this.mUpdateReceiver, intentFilter);
    }

    private void registerBatteryStatusReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.BATTERY_CHANGED");
        this.mBatteryStatusReceiver = new BroadcastReceiver() {
            /* class com.oculus.nux.ota.OsUpdateTracker.AnonymousClass2 */

            public void onReceive(Context context, Intent intent) {
                OsUpdateTracker.this.onBatteryStatusChanged();
            }
        };
        this.mContext.registerReceiver(this.mBatteryStatusReceiver, intentFilter);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void onOsUpdateStatusChanged(Intent intent) {
        Log.d(TAG, "Got OS update status notification.");
        String stringExtra = intent.getStringExtra("state");
        if ("checking_for_updates".equals(stringExtra)) {
            Log.d(TAG, "Status: Checking for OS update.");
        } else if ("no_updates_available".equals(stringExtra)) {
            Log.d(TAG, "Status: No OS update available.");
            updateProgress(100.0f);
            setNoUpdate();
        } else if ("error_while_checking_for_updates".equals(stringExtra)) {
            String str = "Status: Error while checking for OS update: " + intent.getStringExtra("error_details");
            Log.e(TAG, str);
            this.mTelemetry.recordError(str);
            startUpdateStallTimerIfNotStarted();
        } else if ("error_while_applying_update".equals(stringExtra)) {
            String str2 = "Status: Error while applying OS update: " + intent.getStringExtra("error_details");
            Log.e(TAG, str2);
            this.mTelemetry.recordError(str2);
            startUpdateStallTimerIfNotStarted();
        } else if ("applying_update".equals(stringExtra)) {
            float floatExtra = intent.getFloatExtra("progress", 0.0f) * 100.0f;
            Log.i(TAG, "Status: Applying OS update (%): " + floatExtra);
            updateProgressAndStallTimer(floatExtra);
        } else if ("verifying_update".equals(stringExtra)) {
            Log.d(TAG, "Status: Verifying OS update.");
            updateProgress(100.0f);
        } else if ("waiting_for_reboot".equals(stringExtra)) {
            Log.d(TAG, "Status: OS update is ready. Waiting for reboot.");
            updateProgress(100.0f);
            setUpdateReady();
        }
    }

    private void setNoUpdate() {
        Log.d(TAG, "No OS update available.");
        StateMachine stateMachine = this.mStateMachine;
        if (stateMachine != null) {
            Object lock = stateMachine.getLock();
            synchronized (lock) {
                this.mStateMachine.setNoOta(true);
                lock.notifyAll();
            }
        }
    }

    private void setUpdateReady() {
        Log.d(TAG, "OS update is ready.");
        StateMachine stateMachine = this.mStateMachine;
        if (stateMachine != null) {
            Object lock = stateMachine.getLock();
            synchronized (lock) {
                this.mStateMachine.setOtaReady(true);
                lock.notifyAll();
            }
        }
    }

    public void updateProgress(float f) {
        if (f > this.mProgress) {
            String str = TAG;
            Log.d(str, "Progress (%): " + f);
            this.mProgress = f;
            this.mNuxOta.sendUpdateProgressBroadcast(this.mProgress);
        }
    }

    private void updateProgressAndStallTimer(float f) {
        if (this.mProgress != f) {
            stopUpdateStallTimer();
            StateMachine stateMachine = this.mStateMachine;
            if (stateMachine != null && stateMachine.getIsOtaDownloadStalled()) {
                setUpdateStalled(false);
            }
            startUpdateStallTimer();
            updateProgress(f);
        }
    }

    public void startUpdateStallTimerIfNotStarted() {
        if (this.mUpdateStallTimerTask == null && !this.mIsUpdateStallTimerPaused) {
            startUpdateStallTimer();
        }
    }

    private void startUpdateStallTimer() {
        if (this.mUpdateStallTimer == null) {
            this.mUpdateStallTimer = new Timer("NUX OS Update Stall Timer");
        }
        this.mUpdateStallTimerTask = new UpdateStallTimerTask();
        this.mUpdateStallTimer.schedule(this.mUpdateStallTimerTask, UPDATE_STALLED_TIMEOUT_MS);
    }

    private void stopUpdateStallTimer() {
        UpdateStallTimerTask updateStallTimerTask = this.mUpdateStallTimerTask;
        if (updateStallTimerTask != null) {
            updateStallTimerTask.cancel();
            this.mUpdateStallTimerTask = null;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUpdateStalled(boolean z) {
        StateMachine stateMachine = this.mStateMachine;
        if (stateMachine != null) {
            Object lock = stateMachine.getLock();
            synchronized (lock) {
                if (!this.mStateMachine.getOtaReady() && !this.mStateMachine.getNoOta()) {
                    this.mStateMachine.setIsOtaDownloadStalled(z);
                    if (z) {
                        Log.d(TAG, "OS update is stalled.");
                    }
                    lock.notifyAll();
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void checkForUpdates() {
        Log.d(TAG, "Requesting OS Updater to download update if available.");
        new OsUpdater(this.mContext).downloadUpdateIfAvailable(false, null, new UpdateAvailabilityCallback(), new UpdateProgressCallback());
    }

    /* access modifiers changed from: package-private */
    public float getProgress() {
        return this.mProgress;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void onBatteryStatusChanged() {
        boolean isBatteryLevelNormal = isBatteryLevelNormal();
        if (!isBatteryLevelNormal && !this.mIsUpdateStallTimerPaused && this.mUpdateStallTimerTask != null) {
            this.mIsUpdateStallTimerPaused = true;
            stopUpdateStallTimer();
            Log.e(TAG, "Low battery level. Pausing OS update stall timer.");
            this.mTelemetry.recordError("Low battery level. Pausing OS update stall timer.");
        } else if (isBatteryLevelNormal && this.mIsUpdateStallTimerPaused) {
            this.mIsUpdateStallTimerPaused = false;
            startUpdateStallTimer();
            Log.d(TAG, "Battery level restored. Re-starting OS update stall timer.");
        }
    }

    private boolean isBatteryLevelNormal() {
        BatteryManager batteryManager = (BatteryManager) this.mContext.getSystemService("batterymanager");
        if ((batteryManager != null ? batteryManager.getIntProperty(4) : 0) >= 30) {
            return true;
        }
        return false;
    }
}
