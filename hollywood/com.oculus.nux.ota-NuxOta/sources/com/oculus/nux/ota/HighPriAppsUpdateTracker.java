package com.oculus.nux.ota;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.UserHandle;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.secure.context.SecureContextHelper;
import com.facebook.secure.receiver.ActionReceiver;
import com.facebook.secure.receiver.BroadcastReceiverLike;
import com.facebook.secure.receiver.DynamicSecureBroadcastReceiver;
import com.facebook.secure.receiver.SecureBroadcastReceiver;
import com.facebook.secure.trustedapp.AllFamilyTrustedSignatures;
import com.facebook.secure.trustedapp.TrustedAppHelper;
import com.oculus.appmanager.info.model.InstallerResult;
import com.oculus.appmanager.info.model.InstallerUpdateResult;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class HighPriAppsUpdateTracker {
    private static final String TAG = ("NuxOta" + HighPriAppsUpdateTracker.class.getSimpleName());
    private static final long UPDATE_REQUEST_TIMEOUT_MS = TimeUnit.SECONDS.toMillis(20);
    private static final long UPDATE_STALLED_TIMEOUT_MS = TimeUnit.MINUTES.toMillis(5);
    private List<HighPriApp> mAppsList;
    private Context mContext;
    private NuxOta mNuxOta;
    private SecureBroadcastReceiver mPackagesReceiver;
    private float mProgress = 0.0f;
    private SecureBroadcastReceiver mProgressReceiver;
    private NuxOtaSettings mSettings;
    private boolean mStartedUpdateRequests = false;
    private StateMachine mStateMachine;
    private Telemetry mTelemetry;
    private UpdateRequestTimeoutTask mUpdateRequestTimeoutTask;
    private Timer mUpdateRequestTimeoutTimer;
    private Timer mUpdateStallTimer;
    private UpdateStallTimerTask mUpdateStallTimerTask;

    /* access modifiers changed from: private */
    public class UpdateRequestTimeoutTask extends TimerTask {
        private UpdateRequestTimeoutTask() {
        }

        public void run() {
            HighPriAppsUpdateTracker.this.requestUpdate();
        }
    }

    /* access modifiers changed from: private */
    public class UpdateStallTimerTask extends TimerTask {
        private UpdateStallTimerTask() {
        }

        public void run() {
            HighPriAppsUpdateTracker.this.setUpdateStalled(true);
        }
    }

    public HighPriAppsUpdateTracker(Context context, NuxOta nuxOta, StateMachine stateMachine, NuxOtaSettings nuxOtaSettings, Telemetry telemetry) {
        this.mContext = context;
        this.mNuxOta = nuxOta;
        this.mStateMachine = stateMachine;
        this.mSettings = nuxOtaSettings;
        this.mTelemetry = telemetry;
    }

    public void initialize() {
        NuxOtaState state = this.mSettings.getState();
        if (state == NuxOtaState.NOTIFY_ENDPOINT || state == NuxOtaState.COMPLETE) {
            onUpdateSkipped();
        } else {
            registerPackagesReceiver();
        }
    }

    private void registerPackagesReceiver() {
        if (this.mPackagesReceiver == null) {
            Log.d(TAG, "Registering packages receiver.");
            IntentFilter intentFilter = new IntentFilter("com.oculus.defaultapps.HIGH_PRI_APP_PACKAGES");
            this.mPackagesReceiver = new DynamicSecureBroadcastReceiver("com.oculus.defaultapps.HIGH_PRI_APP_PACKAGES", new ActionReceiver() {
                /* class com.oculus.nux.ota.HighPriAppsUpdateTracker.AnonymousClass1 */

                @Override // com.facebook.secure.receiver.ActionReceiver
                public void onReceive(Context context, Intent intent, BroadcastReceiverLike broadcastReceiverLike) {
                    HighPriAppsUpdateTracker.this.onAppsListUpdated(intent);
                }
            });
            SecureContextHelper.get().registerTrustedAppReceiver(this.mContext, TrustedAppHelper.createTrustedApp(Collections.singleton(AllFamilyTrustedSignatures.OCULUS_PROD_SIGNATURE_HASH_RELEASE), Collections.singleton("com.oculus.horizon")), this.mPackagesReceiver, intentFilter, null, null);
            return;
        }
        Log.e(TAG, "Packages receiver is already registered");
    }

    private void unregisterPackagesReceiver() {
        SecureBroadcastReceiver secureBroadcastReceiver = this.mPackagesReceiver;
        if (secureBroadcastReceiver != null) {
            this.mContext.unregisterReceiver(secureBroadcastReceiver);
            this.mPackagesReceiver = null;
            return;
        }
        Log.w(TAG, "Packages receiver is already unregistered");
    }

    public void onUpdateSkipped() {
        Log.d(TAG, "Update skipped.");
        this.mStartedUpdateRequests = true;
        onUpdateCompletedOrSkipped();
    }

    public void onUpdateCompleted() {
        Log.d(TAG, "Update completed.");
        onUpdateCompletedOrSkipped();
    }

    /* access modifiers changed from: package-private */
    public void onUpdateCompletedOrSkipped() {
        stopUpdateStallTimer();
        unregisterPackagesReceiver();
        unregisterProgressReceiver();
        updateProgress(100.0f);
        StateMachine stateMachine = this.mStateMachine;
        if (stateMachine != null) {
            Object lock = stateMachine.getLock();
            synchronized (lock) {
                this.mStateMachine.setHighPriAppsDownloadComplete(true);
                lock.notifyAll();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void maybeStartUpdateRequests() {
        if (!this.mStartedUpdateRequests) {
            this.mStartedUpdateRequests = true;
            startUpdateRequests();
        }
    }

    private void startUpdateRequests() {
        if (this.mUpdateRequestTimeoutTask == null) {
            this.mUpdateRequestTimeoutTask = new UpdateRequestTimeoutTask();
            this.mUpdateRequestTimeoutTimer = new Timer("High Pri Apps Update Request Timeout Timer");
            this.mUpdateRequestTimeoutTimer.scheduleAtFixedRate(this.mUpdateRequestTimeoutTask, new Date(), UPDATE_REQUEST_TIMEOUT_MS);
            startUpdateStallTimer();
        }
    }

    private void startUpdateStallTimer() {
        if (this.mUpdateStallTimer == null) {
            this.mUpdateStallTimer = new Timer("NUX High Pri Apps Update Progress Timer");
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
        stopUpdateRequests();
        updateProgress(100.0f);
        StateMachine stateMachine = this.mStateMachine;
        if (stateMachine != null) {
            Object lock = stateMachine.getLock();
            synchronized (lock) {
                if (!this.mStateMachine.getHighPriAppsDownloadComplete()) {
                    this.mStateMachine.setIsHighPriAppsDownloadStalled(z);
                    if (z) {
                        Log.d(TAG, "Update is stalled.");
                    }
                    lock.notifyAll();
                }
            }
        }
    }

    private void stopUpdateRequests() {
        Timer timer = this.mUpdateRequestTimeoutTimer;
        if (timer != null) {
            timer.cancel();
            this.mUpdateRequestTimeoutTimer.purge();
            this.mUpdateRequestTimeoutTask.cancel();
            this.mUpdateRequestTimeoutTask = null;
            this.mUpdateRequestTimeoutTimer = null;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void requestUpdate() {
        Log.d(TAG, "Sending a request to begin updating.");
        this.mContext.sendBroadcastAsUser(new Intent("com.oculus.nux.ota.NUX_OTA_DOWNLOAD_HIGH_PRI_APPS"), UserHandle.SYSTEM);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void onAppsListUpdated(Intent intent) {
        Log.d(TAG, "Received apps list update.");
        String stringExtra = intent.getStringExtra("package_names");
        if (TextUtils.isEmpty(stringExtra)) {
            Log.e(TAG, "Could not get apps list.");
            this.mTelemetry.recordError("Could not get apps list.");
        } else if ("$".equals(stringExtra)) {
            Log.d(TAG, "Apps list is empty -- skipping update.");
            stopUpdateRequests();
            onUpdateSkipped();
        } else {
            Log.d(TAG, "Apps list updated.");
            stopUpdateRequests();
            List<String> asList = Arrays.asList(stringExtra.split(","));
            this.mAppsList = new ArrayList();
            PackageManager packageManager = this.mContext.getPackageManager();
            for (String str : asList) {
                try {
                    ApplicationInfo applicationInfo = packageManager.getApplicationInfo(str, 0);
                    if ((applicationInfo.flags & 1) != 0 && (applicationInfo.flags & 128) == 0) {
                        this.mAppsList.add(new HighPriApp(str));
                    }
                } catch (PackageManager.NameNotFoundException unused) {
                    this.mAppsList.add(new HighPriApp(str));
                }
            }
            registerProgressReceiver();
        }
    }

    private void registerProgressReceiver() {
        if (this.mProgressReceiver == null) {
            Log.d(TAG, "Registering progress receiver.");
            IntentFilter intentFilter = new IntentFilter("ovr.library.update_broadcast");
            this.mProgressReceiver = new DynamicSecureBroadcastReceiver("ovr.library.update_broadcast", new ActionReceiver() {
                /* class com.oculus.nux.ota.HighPriAppsUpdateTracker.AnonymousClass2 */

                @Override // com.facebook.secure.receiver.ActionReceiver
                public void onReceive(Context context, Intent intent, BroadcastReceiverLike broadcastReceiverLike) {
                    HighPriAppsUpdateTracker.this.onProgressUpdated(intent);
                }
            });
            SecureContextHelper.get().registerTrustedAppReceiver(this.mContext, TrustedAppHelper.createTrustedApp(Collections.singleton(AllFamilyTrustedSignatures.OCULUS_PROD_SIGNATURE_HASH_RELEASE), Collections.singleton("com.oculus.ocms")), this.mProgressReceiver, intentFilter, null, null);
            return;
        }
        Log.e(TAG, "Progress receiver is already registered");
    }

    private void unregisterProgressReceiver() {
        if (this.mProgressReceiver != null) {
            Log.d(TAG, "Unregistering progress receiver.");
            this.mContext.unregisterReceiver(this.mProgressReceiver);
            this.mProgressReceiver = null;
            return;
        }
        Log.w(TAG, "Progress receiver is already unregistered");
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void onProgressUpdated(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            InstallerResult installerResult = (InstallerResult) extras.getParcelable("EXTRA_RESULT");
            if (installerResult != null) {
                onInstall(installerResult);
            }
            InstallerUpdateResult installerUpdateResult = (InstallerUpdateResult) extras.getParcelable("EXTRA_UPDATE_RESULT");
            if (installerUpdateResult != null) {
                onUpdate(installerUpdateResult);
            }
        }
    }

    private HighPriApp getApp(String str) {
        if (str == null) {
            return null;
        }
        for (HighPriApp highPriApp : this.mAppsList) {
            if (highPriApp.getAppId().equals(str)) {
                return highPriApp;
            }
        }
        return null;
    }

    private void onInstall(InstallerResult installerResult) {
        HighPriApp app = getApp(installerResult.installIdentifier);
        if (app != null && installerResult.isSuccess()) {
            app.setIsInstalled(true);
            if (this.mAppsList.stream().allMatch($$Lambda$HighPriAppsUpdateTracker$kvm64OHogaE4Ahtc9q7cuqIWyk.INSTANCE)) {
                onUpdateCompleted();
            }
        }
    }

    private void onUpdate(InstallerUpdateResult installerUpdateResult) {
        HighPriApp app = getApp(installerUpdateResult.mIstallIdentifier);
        if (app != null) {
            long j = installerUpdateResult.mTotalBytes;
            if (app.getTotalBytes() != j) {
                app.setTotalBytes(j);
            }
            long j2 = installerUpdateResult.mDownloadedBytes;
            if (app.getDownloadedBytes() != j2) {
                app.setDownloadedBytes(j2);
            }
            long j3 = 0;
            long j4 = 0;
            for (HighPriApp highPriApp : this.mAppsList) {
                j3 += highPriApp.getTotalBytes();
                j4 += highPriApp.getDownloadedBytes();
            }
            float f = (j3 <= 0 ? 0.0f : ((float) j4) / ((float) j3)) * 100.0f;
            if (f <= 0.0f) {
                f = 0.0f;
            } else if (f >= 100.0f) {
                f = 100.0f;
            }
            updateProgressAndRestartStallTimer(f);
        }
    }

    private void updateProgress(float f) {
        if (f > this.mProgress) {
            String str = TAG;
            Log.d(str, "Progress (%): " + f);
            this.mProgress = f;
            this.mNuxOta.sendUpdateProgressBroadcast(this.mProgress);
        }
    }

    private void updateProgressAndRestartStallTimer(float f) {
        if (f > this.mProgress) {
            updateProgress(f);
            Log.d(TAG, "Resetting update stall timer.");
            stopUpdateStallTimer();
            StateMachine stateMachine = this.mStateMachine;
            if (stateMachine != null && stateMachine.getIsHighPriAppsDownloadStalled()) {
                setUpdateStalled(false);
            }
            startUpdateStallTimer();
        }
    }

    /* access modifiers changed from: package-private */
    public float getProgress() {
        return this.mProgress;
    }
}
