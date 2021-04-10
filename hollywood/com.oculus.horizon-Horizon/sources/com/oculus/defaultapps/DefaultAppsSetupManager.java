package com.oculus.defaultapps;

import X.AbstractC06640p5;
import X.AnonymousClass0J2;
import X.AnonymousClass0NO;
import X.AnonymousClass0QC;
import X.C02600ao;
import X.C02780bN;
import X.C02880bg;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.ResultReceiver;
import androidx.annotation.VisibleForTesting;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.device.DeviceType;
import com.oculus.libraryapi.OVRLibrary;
import com.oculus.nuxpreferencesapi.OVRNuxPreferences;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

@Dependencies({"_UL__ULSEP_com_oculus_coreapps_CoreAppManager_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_defaultapps_DefaultAppsPrefs_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_defaultapps_DefaultAppsMethods_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_util_thread_ThreadUtils_ULSEP_BINDING_ID", "_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_oculustestsettings_OculusTestSettings_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_libraryapi_OVRLibrary_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_common_packagescache_PackageManagerUtils_ULSEP_BINDING_ID"})
public class DefaultAppsSetupManager {
    public static final String ACTION_HIGH_PRI_APPS_PACKAGES = "com.oculus.defaultapps.HIGH_PRI_APP_PACKAGES";
    public static final String EMPTY_PACKAGE_LIST_SENTINEL = "$";
    public static final String KEY_PACKAGE_NAMES = "package_names";
    public static final int MAX_SETUP_ATTEMPTS = 3;
    public static final String PACKAGE_COMPANION_SERVER = "com.oculus.companion.server";
    public static final String TAG = "DefaultAppsSetupManager";
    public static final AtomicBoolean isDefaultAppsSetupJobScheduled = new AtomicBoolean(false);
    public AnonymousClass0QC _UL_mInjectionContext;

    @VisibleForTesting
    public final void A00() {
        SharedPreferences.Editor edit;
        String obj;
        if (!((DefaultAppsPrefs) AnonymousClass0J2.A03(2, 0, this._UL_mInjectionContext)).mPrefs.getBoolean(DefaultAppsPrefs.SETUP_COMPLETE, false)) {
            int i = ((DefaultAppsPrefs) AnonymousClass0J2.A03(2, 0, this._UL_mInjectionContext)).mPrefs.getInt(DefaultAppsPrefs.SETUP_ATTEMPT, 0);
            if (i < 3) {
                ((DefaultAppsPrefs) AnonymousClass0J2.A03(2, 0, this._UL_mInjectionContext)).mPrefs.edit().putInt(DefaultAppsPrefs.SETUP_ATTEMPT, i + 1).apply();
                Set<String> stringSet = ((DefaultAppsPrefs) AnonymousClass0J2.A03(2, 0, this._UL_mInjectionContext)).mPrefs.getStringSet(DefaultAppsPrefs.MODIFIABLE_HIGH_PRI_APP_LIST, null);
                if (stringSet == null) {
                    stringSet = new HashSet<>();
                }
                if (stringSet.size() == 0) {
                    stringSet.add(EMPTY_PACKAGE_LIST_SENTINEL);
                }
                DefaultAppsPrefs defaultAppsPrefs = (DefaultAppsPrefs) AnonymousClass0J2.A03(2, 0, this._UL_mInjectionContext);
                if (stringSet.isEmpty()) {
                    edit = defaultAppsPrefs.mPrefs.edit();
                    obj = "";
                } else {
                    StringBuilder sb = new StringBuilder();
                    for (String str : stringSet) {
                        sb.append(str);
                        sb.append(',');
                    }
                    sb.deleteCharAt(sb.length() - 1);
                    edit = defaultAppsPrefs.mPrefs.edit();
                    obj = sb.toString();
                }
                edit.putString(DefaultAppsPrefs.HIGH_PRIORITY_APPS_PACKAGES, obj).apply();
                A01();
                ((DefaultAppsPrefs) AnonymousClass0J2.A03(2, 0, this._UL_mInjectionContext)).A00(0);
                Context context = (Context) AnonymousClass0J2.A03(5, 80, this._UL_mInjectionContext);
                DefaultAppsSetupJobService.A01(context, DefaultAppsSetupJobService.A00(context).build());
            } else if (OVRNuxPreferences.Status.values()[((DefaultAppsPrefs) AnonymousClass0J2.A03(2, 0, this._UL_mInjectionContext)).mPrefs.getInt(DefaultAppsPrefs.HIGH_PRIORITY_APPS_DOWNLOAD_STATUS, 0)] == OVRNuxPreferences.Status.UNSET) {
                ((DefaultAppsPrefs) AnonymousClass0J2.A03(2, 0, this._UL_mInjectionContext)).A02(true);
                A03(OVRNuxPreferences.Status.FAILED);
            }
        }
    }

    public final void A03(OVRNuxPreferences.Status status) {
        ((DefaultAppsPrefs) AnonymousClass0J2.A03(2, 0, this._UL_mInjectionContext)).mPrefs.edit().putInt(DefaultAppsPrefs.HIGH_PRIORITY_APPS_DOWNLOAD_STATUS, status.ordinal()).apply();
    }

    public final void A01() {
        Intent intent = new Intent();
        intent.setAction(ACTION_HIGH_PRI_APPS_PACKAGES);
        intent.putExtra(KEY_PACKAGE_NAMES, ((DefaultAppsPrefs) AnonymousClass0J2.A03(2, 0, this._UL_mInjectionContext)).mPrefs.getString(DefaultAppsPrefs.HIGH_PRIORITY_APPS_PACKAGES, ""));
        C02600ao.A00().A07(C02880bg.A02(C02780bN.A1G, Collections.singleton("com.oculus.nux.ota"))).A03(intent, (Context) AnonymousClass0J2.A03(5, 80, this._UL_mInjectionContext));
    }

    @Inject
    public DefaultAppsSetupManager(AbstractC06640p5 r3) {
        this._UL_mInjectionContext = new AnonymousClass0QC(9, r3);
    }

    public final void A02() {
        String str;
        String str2;
        if (!DeviceType.is6DOF()) {
            str = TAG;
            str2 = "triggerSetupInstalls should only be called on 6DOF devices";
        } else if (isDefaultAppsSetupJobScheduled.compareAndSet(false, true)) {
            ((OVRLibrary) AnonymousClass0J2.A03(7, 569, this._UL_mInjectionContext)).A05(new ResultReceiver() {
                /* class com.oculus.defaultapps.DefaultAppsSetupManager.AnonymousClass2 */

                public final void onReceiveResult(int i, Bundle bundle) {
                    if (i != 0) {
                        AnonymousClass0NO.A08(DefaultAppsSetupManager.TAG, "Failed to refresh library prior to triggering setup installs");
                    }
                    DefaultAppsSetupManager.this.A00();
                }
            });
            return;
        } else {
            str = TAG;
            str2 = "default apps setup blocked due to job being scheduled";
        }
        AnonymousClass0NO.A09(str, str2);
    }
}
