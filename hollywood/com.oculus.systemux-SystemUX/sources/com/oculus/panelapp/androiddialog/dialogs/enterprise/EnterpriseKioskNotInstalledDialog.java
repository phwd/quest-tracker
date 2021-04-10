package com.oculus.panelapp.androiddialog.dialogs.enterprise;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.panelapp.androiddialog.AndroidDialogPanelApp;
import com.oculus.panelapp.androiddialog.Dialog;
import com.oculus.panelapp.androiddialog.databinding.EnterpriseKioskNotInstalledDialogBinding;
import com.oculus.panelapp.androiddialog.dialogs.enterprise.EnterpriseKioskNotInstalledDialog;
import java.util.Timer;
import java.util.TimerTask;

public final class EnterpriseKioskNotInstalledDialog extends ConstraintLayout implements Dialog {
    private static final String ACTION_NUX_OTA_PROGRESS_UPDATE = "com.oculus.nux.ota.NUX_OTA_PROGRESS_UPDATE";
    private static final int CHECK_KIOSK_APP_INSTALLED_INTERVAL_MS = 5000;
    private static final String ENVIRONMENT_ARG_ENTERPRISE_CONFIGURATION_MODE_INDEX = "_oc_shell_enterprise_configuration_mode_index";
    private static final String EXTRA_NUX_OTA_PROGRESS = "PROGRESS";
    private static final String TAG = LoggingUtil.tag(EnterpriseKioskNotInstalledDialog.class);
    private EnterpriseKioskNotInstalledDialogBinding mBinding;
    private long mConfigUnixTime;
    private final Context mContext;
    private String mDefaultAppPackage;
    private final Handler mHandler;
    private boolean mIsDefaultAppInstalled = false;
    private boolean mIsDestroyed = false;
    private boolean mIsNuxOtaDone;
    private NuxOtaProgressBroadcastReceiver mNuxOtaProgressReceiver;
    private AndroidDialogPanelApp mPanelApp;
    private Timer mTimer;
    private TimerTask mTimerTask;

    @Override // com.oculus.panelapp.androiddialog.Dialog
    public boolean onControllerBackButton() {
        return false;
    }

    public EnterpriseKioskNotInstalledDialog(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        Log.d(TAG, "EnterpriseKioskNotInstalledDialog dialog constructed");
        this.mContext = context;
        this.mHandler = new Handler(Looper.getMainLooper());
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0099  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x009c  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0074  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void initialize(com.oculus.panelapp.androiddialog.AndroidDialogPanelApp r7, com.oculus.panelapp.androiddialog.databinding.EnterpriseKioskNotInstalledDialogBinding r8) {
        /*
        // Method dump skipped, instructions count: 199
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.androiddialog.dialogs.enterprise.EnterpriseKioskNotInstalledDialog.initialize(com.oculus.panelapp.androiddialog.AndroidDialogPanelApp, com.oculus.panelapp.androiddialog.databinding.EnterpriseKioskNotInstalledDialogBinding):void");
    }

    public /* synthetic */ void lambda$initialize$2$EnterpriseKioskNotInstalledDialog(View view) {
        onConfigureClicked();
    }

    @Override // com.oculus.panelapp.androiddialog.Dialog
    public synchronized void destroy() {
        Log.d(TAG, "Destroying EnterpriseKioskNotInstalledDialog");
        this.mIsDestroyed = true;
        if (this.mTimerTask != null) {
            cleanUpTimer();
        }
        if (this.mNuxOtaProgressReceiver != null) {
            cleanUpNuxOtaProgressReceiver();
        }
    }

    private void setupAndStartKioskAppCheckTimer() {
        this.mTimer = new Timer(true);
        this.mTimerTask = new TimerTask() {
            /* class com.oculus.panelapp.androiddialog.dialogs.enterprise.EnterpriseKioskNotInstalledDialog.AnonymousClass1 */

            public void run() {
                EnterpriseKioskNotInstalledDialog.this.mHandler.post(new Runnable() {
                    /* class com.oculus.panelapp.androiddialog.dialogs.enterprise.$$Lambda$EnterpriseKioskNotInstalledDialog$1$ImaXBkeqMaugCdD6SScnoEcoN0 */

                    public final void run() {
                        EnterpriseKioskNotInstalledDialog.AnonymousClass1.this.lambda$run$3$EnterpriseKioskNotInstalledDialog$1();
                    }
                });
            }

            public /* synthetic */ void lambda$run$3$EnterpriseKioskNotInstalledDialog$1() {
                synchronized (this) {
                    String str = EnterpriseKioskNotInstalledDialog.TAG;
                    Log.d(str, "Enterprise device timer for default package: " + EnterpriseKioskNotInstalledDialog.this.mDefaultAppPackage + " " + EnterpriseKioskNotInstalledDialog.this.mConfigUnixTime);
                    if (!EnterpriseKioskNotInstalledDialog.this.mIsDestroyed) {
                        if (EnterpriseKioskNotInstalledDialog.this.defaultAppPackageExists() && EnterpriseKioskNotInstalledDialog.this.mTimerTask != null) {
                            EnterpriseKioskNotInstalledDialog.this.mIsDefaultAppInstalled = true;
                            EnterpriseKioskNotInstalledDialog.this.cleanUpTimer();
                        }
                        EnterpriseKioskNotInstalledDialog.this.dismissIfReady();
                    }
                }
            }
        };
        this.mTimer.scheduleAtFixedRate(this.mTimerTask, 0, 5000);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void cleanUpTimer() {
        this.mTimerTask.cancel();
        this.mTimer.purge();
        this.mTimerTask = null;
        this.mTimer = null;
    }

    private void cleanUpNuxOtaProgressReceiver() {
        this.mContext.unregisterReceiver(this.mNuxOtaProgressReceiver);
        this.mNuxOtaProgressReceiver = null;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private synchronized void handleNuxOtaProgress(Intent intent) {
        if (!this.mIsDestroyed) {
            float floatExtra = intent.getFloatExtra(EXTRA_NUX_OTA_PROGRESS, 0.0f);
            Log.d(TAG, String.format("NUX OTA progress updated to %.2f%%.", Float.valueOf(floatExtra)));
            if (floatExtra >= 100.0f) {
                this.mIsNuxOtaDone = true;
                cleanUpNuxOtaProgressReceiver();
                dismissIfReady();
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void dismissIfReady() {
        String str = TAG;
        Log.d(str, "Dismissing if ready: " + this.mIsDefaultAppInstalled + " " + this.mIsNuxOtaDone);
        if (this.mIsDefaultAppInstalled && this.mIsNuxOtaDone) {
            dismissDialog();
        }
    }

    private void dismissDialog() {
        this.mPanelApp.getCommandChannel().sendCommand("enterprise shutdown");
    }

    private synchronized void onConfigureClicked() {
        if (!this.mIsDestroyed) {
            if (this.mTimerTask != null) {
                cleanUpTimer();
            }
            if (this.mNuxOtaProgressReceiver != null) {
                cleanUpNuxOtaProgressReceiver();
            }
            dismissDialog();
            this.mPanelApp.getCommandChannel().launch("systemux://settings");
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean defaultAppPackageExists() {
        try {
            this.mContext.createPackageContext(this.mDefaultAppPackage, 2);
            Log.d(TAG, "Enterprise device found default package.");
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            Log.d(TAG, "Enterprise device waiting for default package to be available.");
            return false;
        }
    }

    /* access modifiers changed from: private */
    public class NuxOtaProgressBroadcastReceiver extends BroadcastReceiver {
        private NuxOtaProgressBroadcastReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            String str = EnterpriseKioskNotInstalledDialog.TAG;
            Log.d(str, "NuxOtaProgress got action: " + action);
            if (!action.equals(EnterpriseKioskNotInstalledDialog.ACTION_NUX_OTA_PROGRESS_UPDATE)) {
                String str2 = EnterpriseKioskNotInstalledDialog.TAG;
                Log.d(str2, "Got unexpected action: " + action);
                return;
            }
            EnterpriseKioskNotInstalledDialog.this.handleNuxOtaProgress(intent);
        }
    }
}
