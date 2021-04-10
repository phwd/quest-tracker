package com.oculus.appsafety;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.os.ResultReceiver;
import android.os.SystemProperties;
import android.util.Log;
import com.oculus.os.AnalyticsEvent;
import com.oculus.os.UnifiedTelemetryLogger;

public final class PackageVerificationReceiver extends BroadcastReceiver {
    private static final String PACKAGE_VERIFICATION_RECEIVED = "oculus_appsafety_package_verification_received";
    private static final String TAG = "PackageVerificationReceiver";
    private static final String UNSUPPORTED_ACTION = "oculus_appsafety_received_unsupported_action";

    /* access modifiers changed from: private */
    public static void sendErrorToPackageManager(PackageManager pm, int verificationId) {
        pm.verifyPendingInstall(verificationId, -1);
    }

    public void onReceive(Context context, Intent intent) {
        AnalyticsEvent event;
        String action = intent.getAction();
        if ("android.intent.action.PACKAGE_NEEDS_VERIFICATION".equals(action)) {
            Bundle inputExtras = intent.getExtras();
            Log.d(TAG, "Received package verification intent");
            event = new AnalyticsEvent(TAG, PACKAGE_VERIFICATION_RECEIVED, (PersistableBundle) null, (PersistableBundle) null);
            if (inputExtras != null) {
                Intent serviceIntent = new Intent(context, AppSafetyService.class);
                serviceIntent.setAction(AppSafetyService.VERIFY_PACKAGE);
                int verificationId = inputExtras.getInt("android.content.pm.extra.VERIFICATION_ID");
                serviceIntent.setDataAndType(intent.getData(), intent.getType());
                serviceIntent.addFlags(1);
                Bundle extras = new Bundle();
                extras.putString("android.content.pm.extra.VERIFICATION_INSTALLER_PACKAGE", inputExtras.getString("android.content.pm.extra.VERIFICATION_INSTALLER_PACKAGE"));
                extras.putInt("android.content.pm.extra.VERIFICATION_INSTALL_FLAGS", intent.getIntExtra("android.content.pm.extra.VERIFICATION_INSTALL_FLAGS", 0));
                extras.putParcelable(AppSafetyService.EXTRA_VERIFICATION_RESULT_RECEIVER, new PackageVerificationResultReceiver(new Handler(), context.getPackageManager(), verificationId));
                serviceIntent.putExtras(extras);
                context.startService(serviceIntent);
            }
        } else {
            Log.w(TAG, "Unrecognized intent action: " + action);
            event = new AnalyticsEvent(TAG, UNSUPPORTED_ACTION, (PersistableBundle) null, (PersistableBundle) null);
        }
        UnifiedTelemetryLogger.getInstance(context).reportEvent(event, false);
    }

    private static class PackageVerificationResultReceiver extends ResultReceiver {
        private final PackageManager mPackageManager;
        private final int mVerificationId;

        public PackageVerificationResultReceiver(Handler handler, PackageManager packageManager, int verificationId) {
            super(handler);
            this.mVerificationId = verificationId;
            this.mPackageManager = packageManager;
        }

        /* access modifiers changed from: protected */
        public void onReceiveResult(int resultCode, Bundle resultData) {
            if (resultCode == 0) {
                boolean isVerified = resultData.getBoolean(AppSafetyService.PACKAGE_VERIFIED);
                boolean isUnlocked = SystemProperties.get("ro.boot.verifiedbootstate", "green").equals("orange");
                if (isVerified || isUnlocked) {
                    this.mPackageManager.verifyPendingInstall(this.mVerificationId, 1);
                } else {
                    this.mPackageManager.verifyPendingInstall(this.mVerificationId, -1);
                }
            } else {
                PackageVerificationReceiver.sendErrorToPackageManager(this.mPackageManager, this.mVerificationId);
            }
        }
    }
}
