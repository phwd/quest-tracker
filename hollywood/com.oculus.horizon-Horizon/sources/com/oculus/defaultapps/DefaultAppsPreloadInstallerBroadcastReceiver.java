package com.oculus.defaultapps;

import X.AnonymousClass0DC;
import X.AnonymousClass0J2;
import X.AnonymousClass0NO;
import X.AnonymousClass0QB;
import X.AnonymousClass117;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.oculus.device.DeviceType;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Callable;

public class DefaultAppsPreloadInstallerBroadcastReceiver extends BroadcastReceiver implements AnonymousClass0QB {
    public static final String ACTION_INSTALL_COMPLETE = "com.oculus.preloader.INSTALL_COMPLETE";
    public static final String PACKAGE_INSTALLER_EXTRA_LEGACY_STATUS = "android.content.pm.extra.LEGACY_STATUS";
    public static final int PACKAGE_MANAGER_INSTALL_FAILED_INTERNAL_ERROR = -110;
    public static final int PACKAGE_MANAGER_INSTALL_FAILED_VERSION_DOWNGRADE = -25;
    public static final String PRELOAD_INSTALLER_COMPLETE = "com.oculus.preloader.ALL_INSTALLS_COMPLETE";
    public static final String TAG = "DefaultAppsPreloadInstallerBroadcastReceiver";
    @Inject
    @Eager
    public DefaultAppsSetupManager mDefaultAppsSetupManager;
    @Inject
    @Eager
    public DefaultAppsPrefs mPrefs;

    public final void onReceive(Context context, final Intent intent) {
        AnonymousClass0J2 r1 = AnonymousClass0J2.get(context);
        this.mPrefs = (DefaultAppsPrefs) AnonymousClass117.A00(0, r1);
        this.mDefaultAppsSetupManager = (DefaultAppsSetupManager) AnonymousClass117.A00(436, r1);
        if (DeviceType.is6DOF()) {
            String action = intent.getAction();
            if (action == null) {
                AnonymousClass0NO.A08("DefaultAppsPreloadInstallerBroadcastReceiver", "null action received");
                return;
            }
            int hashCode = action.hashCode();
            if (hashCode != -1197564142) {
                if (hashCode == 1278124685 && action.equals(ACTION_INSTALL_COMPLETE)) {
                    AnonymousClass0DC.A06(new Callable<Void>() {
                        /* class com.oculus.defaultapps.DefaultAppsPreloadInstallerBroadcastReceiver.AnonymousClass1 */

                        /* Return type fixed from 'java.lang.Object' to match base method */
                        /* JADX WARNING: Code restructure failed: missing block: B:5:0x001c, code lost:
                            if (r2 == -25) goto L_0x001e;
                         */
                        @Override // java.util.concurrent.Callable
                        /* Code decompiled incorrectly, please refer to instructions dump. */
                        public final java.lang.Void call() throws java.lang.Exception {
                            /*
                            // Method dump skipped, instructions count: 213
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.oculus.defaultapps.DefaultAppsPreloadInstallerBroadcastReceiver.AnonymousClass1.call():java.lang.Object");
                        }
                    });
                    return;
                }
            } else if (action.equals(PRELOAD_INSTALLER_COMPLETE)) {
                AnonymousClass0DC.A06(new Callable<Void>() {
                    /* class com.oculus.defaultapps.DefaultAppsPreloadInstallerBroadcastReceiver.AnonymousClass2 */

                    /* Return type fixed from 'java.lang.Object' to match base method */
                    @Override // java.util.concurrent.Callable
                    public final Void call() throws Exception {
                        DefaultAppsPreloadInstallerBroadcastReceiver defaultAppsPreloadInstallerBroadcastReceiver = DefaultAppsPreloadInstallerBroadcastReceiver.this;
                        defaultAppsPreloadInstallerBroadcastReceiver.mPrefs.mPrefs.edit().putBoolean(DefaultAppsPrefs.PRELOAD_INSTALLER_COMPLETE, true).apply();
                        if (!defaultAppsPreloadInstallerBroadcastReceiver.mPrefs.mPrefs.getBoolean(DefaultAppsPrefs.HIGH_PRIORITY_APPS_FETCH, false)) {
                            return null;
                        }
                        Set<String> stringSet = defaultAppsPreloadInstallerBroadcastReceiver.mPrefs.mPrefs.getStringSet(DefaultAppsPrefs.MODIFIABLE_HIGH_PRI_APP_LIST, null);
                        if (stringSet == null) {
                            AnonymousClass0NO.A09("DefaultAppsPreloadInstallerBroadcastReceiver", "The high Priority Apps List is null after the fetch is complete!");
                            stringSet = new HashSet<>();
                        }
                        SharedPreferences sharedPreferences = defaultAppsPreloadInstallerBroadcastReceiver.mPrefs.mPrefs;
                        Set<String> set = Collections.EMPTY_SET;
                        Set<String> stringSet2 = sharedPreferences.getStringSet(DefaultAppsPrefs.COMPLETED_PRELOAD_APP_LIST, set);
                        Set<String> stringSet3 = defaultAppsPreloadInstallerBroadcastReceiver.mPrefs.mPrefs.getStringSet(DefaultAppsPrefs.FAILED_PRELOAD_APP_LIST, set);
                        String string = defaultAppsPreloadInstallerBroadcastReceiver.mPrefs.mPrefs.getString(DefaultAppsPrefs.DEFAULT_ENVIRONMENT_PACKAGE_NAME, "");
                        Iterator<String> it = stringSet.iterator();
                        while (it.hasNext()) {
                            String next = it.next();
                            if (!next.equals(string) && (stringSet3.contains(next) || !stringSet2.contains(next))) {
                                it.remove();
                            }
                        }
                        defaultAppsPreloadInstallerBroadcastReceiver.mPrefs.A01(stringSet);
                        defaultAppsPreloadInstallerBroadcastReceiver.mDefaultAppsSetupManager.A02();
                        return null;
                    }
                });
                return;
            }
            AnonymousClass0NO.A0E("DefaultAppsPreloadInstallerBroadcastReceiver", "Unrecognized broadcast action: %s", action);
        }
    }
}
