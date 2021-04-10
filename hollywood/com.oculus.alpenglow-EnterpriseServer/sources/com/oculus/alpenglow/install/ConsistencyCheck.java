package com.oculus.alpenglow.install;

import X.AbstractC02990bJ;
import X.AnonymousClass0Lh;
import X.AnonymousClass0NK;
import X.AnonymousClass0R7;
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.alpenglow.config.BroadcastIntentHelper;
import com.oculus.alpenglow.database.Application;
import com.oculus.alpenglow.database.ApplicationDatabase;
import com.oculus.alpenglow.lifecycle.StartupListener;
import com.oculus.alpenglow.logging.InstallLogger;
import com.oculus.alpenglow.logging.LoggerConstants;
import com.oculus.executors.OculusThreadExecutor;
import com.oculus.library.model.AppStatus;
import com.oculus.managed.ManagedMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_alpenglow_database_ApplicationDatabase_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_alpenglow_install_InstallerState_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_alpenglow_logging_InstallLogger_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_alpenglow_install_InvokeAppInstallListeners_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_managed_ManagedMode_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_alpenglow_config_BroadcastIntentHelper_ULSEP_BINDING_ID"})
public class ConsistencyCheck implements StartupListener {
    public static final String TAG = "EnterpriseServer.ConsistencyCheck";
    public AnonymousClass0R7 _UL_mInjectionContext;

    @Override // com.oculus.alpenglow.lifecycle.StartupListener
    public final void A6b(Context context) {
        if (((ManagedMode) AnonymousClass0Lh.A03(5, 86, this._UL_mInjectionContext)).isEnterpriseModeEnabled) {
            OculusThreadExecutor.A00().execute(new Runnable() {
                /* class com.oculus.alpenglow.install.$$Lambda$ConsistencyCheck$V8zUg7eEEgB4VoZHNkl0xHsE_U2 */

                public final void run() {
                    ConsistencyCheck consistencyCheck = ConsistencyCheck.this;
                    PackageManager packageManager = ((Context) AnonymousClass0Lh.A03(0, 4, consistencyCheck._UL_mInjectionContext)).getPackageManager();
                    if (packageManager != null) {
                        List<Application> A03 = ((ApplicationDatabase) AnonymousClass0Lh.A03(1, 3, consistencyCheck._UL_mInjectionContext)).A03();
                        List<ApplicationInfo> installedApplications = packageManager.getInstalledApplications(0);
                        ArrayList arrayList = new ArrayList();
                        for (ApplicationInfo applicationInfo : installedApplications) {
                            String str = applicationInfo.packageName;
                            if (str != null && (applicationInfo.flags & 1) == 0) {
                                Iterator<Application> it = A03.iterator();
                                while (true) {
                                    if (!it.hasNext()) {
                                        break;
                                    }
                                    Application next = it.next();
                                    if (next.packageName.equals(str)) {
                                        A03.remove(next);
                                        arrayList.add(next);
                                        break;
                                    }
                                }
                            }
                        }
                        Iterator it2 = arrayList.iterator();
                        while (it2.hasNext()) {
                            Application application = (Application) it2.next();
                            if (application.status != AppStatus.INSTALLED) {
                                AnonymousClass0NK.A06(ConsistencyCheck.TAG, "Application %s found on system but not marked as installed", application.packageName);
                                String str2 = application.appId;
                                AppStatus appStatus = AppStatus.INSTALLED;
                                ((ApplicationDatabase) AnonymousClass0Lh.A03(1, 3, consistencyCheck._UL_mInjectionContext)).A06(str2, appStatus);
                                ((InstallerState) AnonymousClass0Lh.A03(2, 70, consistencyCheck._UL_mInjectionContext)).A04(application.appId);
                                AnonymousClass0R7 r0 = consistencyCheck._UL_mInjectionContext;
                                BroadcastIntentHelper.A01((BroadcastIntentHelper) AnonymousClass0Lh.A03(6, 6, r0), (Context) AnonymousClass0Lh.A03(0, 4, r0), BroadcastIntentHelper.APP_INSTALLED, "com.oculus.systemux");
                                ((InstallLogger) AnonymousClass0Lh.A03(3, 133, consistencyCheck._UL_mInjectionContext)).A03(application.appId, appStatus.toString(), application.packageName, -1, false, true, null);
                                ((InvokeAppInstallListeners) AnonymousClass0Lh.A03(4, 18, consistencyCheck._UL_mInjectionContext)).A00();
                            }
                        }
                        for (Application application2 : A03) {
                            if (application2.status == AppStatus.INSTALLED) {
                                AnonymousClass0NK.A06(ConsistencyCheck.TAG, "Installed app %s found in DB but not on system", application2.packageName);
                                ((ApplicationDatabase) AnonymousClass0Lh.A03(1, 3, consistencyCheck._UL_mInjectionContext)).A05(application2.appId);
                                ((InstallerState) AnonymousClass0Lh.A03(2, 70, consistencyCheck._UL_mInjectionContext)).A04(application2.appId);
                                AnonymousClass0R7 r02 = consistencyCheck._UL_mInjectionContext;
                                BroadcastIntentHelper.A01((BroadcastIntentHelper) AnonymousClass0Lh.A03(6, 6, r02), (Context) AnonymousClass0Lh.A03(0, 4, r02), BroadcastIntentHelper.APP_UNINSTALLED, "com.oculus.systemux");
                                ((InstallLogger) AnonymousClass0Lh.A03(3, 133, consistencyCheck._UL_mInjectionContext)).A05(application2.appId, application2.packageName, true, false, "Installed app found in DB but not on system");
                                ((InvokeAppInstallListeners) AnonymousClass0Lh.A03(4, 18, consistencyCheck._UL_mInjectionContext)).A00();
                            }
                        }
                    }
                }
            });
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.PACKAGE_FULLY_REMOVED");
            intentFilter.addDataScheme(LoggerConstants.PACKAGE_KEY);
            ((Context) AnonymousClass0Lh.A03(0, 4, this._UL_mInjectionContext)).registerReceiver(new PackageChangeListener(), intentFilter);
        }
    }

    @Inject
    public ConsistencyCheck(AbstractC02990bJ r3) {
        this._UL_mInjectionContext = new AnonymousClass0R7(7, r3);
    }
}
