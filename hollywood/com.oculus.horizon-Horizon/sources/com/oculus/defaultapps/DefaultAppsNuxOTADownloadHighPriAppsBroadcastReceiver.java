package com.oculus.defaultapps;

import X.AnonymousClass0DC;
import X.AnonymousClass0J2;
import X.AnonymousClass0NO;
import X.AnonymousClass0QC;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import java.util.concurrent.Callable;

public class DefaultAppsNuxOTADownloadHighPriAppsBroadcastReceiver extends BroadcastReceiver {
    public static final String ACTION_NUX_OTA_DOWNLOAD_HIGH_PRI_APPS = "com.oculus.nux.ota.NUX_OTA_DOWNLOAD_HIGH_PRI_APPS";
    public static final String TAG = "DefaultAppsPreloadInstallerBroadcastReceiver";
    public AnonymousClass0QC _UL_mInjectionContext;

    public final void onReceive(Context context, Intent intent) {
        this._UL_mInjectionContext = new AnonymousClass0QC(3, AnonymousClass0J2.get(context));
        String action = intent.getAction();
        if (action == null) {
            AnonymousClass0NO.A08("DefaultAppsPreloadInstallerBroadcastReceiver", "null action received");
        } else if (TextUtils.equals(action, ACTION_NUX_OTA_DOWNLOAD_HIGH_PRI_APPS)) {
            AnonymousClass0DC.A06(new Callable<Void>() {
                /* class com.oculus.defaultapps.DefaultAppsNuxOTADownloadHighPriAppsBroadcastReceiver.AnonymousClass1 */

                /* Return type fixed from 'java.lang.Object' to match base method */
                @Override // java.util.concurrent.Callable
                public final Void call() throws Exception {
                    if (((DefaultAppsPrefs) AnonymousClass0J2.A03(1, 0, DefaultAppsNuxOTADownloadHighPriAppsBroadcastReceiver.this._UL_mInjectionContext)).mPrefs.getBoolean(DefaultAppsPrefs.HIGH_PRIORITY_APPS_FETCH, false)) {
                        ((DefaultAppsSetupManager) AnonymousClass0J2.A03(2, 436, DefaultAppsNuxOTADownloadHighPriAppsBroadcastReceiver.this._UL_mInjectionContext)).A01();
                        return null;
                    }
                    if (!((DefaultAppsPrefs) AnonymousClass0J2.A03(1, 0, DefaultAppsNuxOTADownloadHighPriAppsBroadcastReceiver.this._UL_mInjectionContext)).mPrefs.getBoolean(DefaultAppsPrefs.NUX_OTA_DOWNLOAD_HIGH_PRI_APPS_BROADCAST, false)) {
                        ((DefaultAppsPrefs) AnonymousClass0J2.A03(1, 0, DefaultAppsNuxOTADownloadHighPriAppsBroadcastReceiver.this._UL_mInjectionContext)).mPrefs.edit().putBoolean(DefaultAppsPrefs.NUX_OTA_DOWNLOAD_HIGH_PRI_APPS_BROADCAST, true).apply();
                    }
                    ((DefaultAppsSetupListener) AnonymousClass0J2.A03(0, 176, DefaultAppsNuxOTADownloadHighPriAppsBroadcastReceiver.this._UL_mInjectionContext)).A00();
                    return null;
                }
            });
        } else {
            AnonymousClass0NO.A0E("DefaultAppsPreloadInstallerBroadcastReceiver", "Unrecognized broadcast action: %s", action);
        }
    }
}
