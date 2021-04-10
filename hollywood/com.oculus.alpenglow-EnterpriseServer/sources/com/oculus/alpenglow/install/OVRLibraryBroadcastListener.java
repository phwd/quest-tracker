package com.oculus.alpenglow.install;

import X.AbstractC04990iH;
import X.AbstractC05000iI;
import X.AnonymousClass0LT;
import X.AnonymousClass0Lh;
import X.AnonymousClass0NK;
import X.AnonymousClass0R6;
import X.AnonymousClass0R7;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.oculus.alpenglow.config.BroadcastIntentHelper;
import com.oculus.alpenglow.database.AppSource;
import com.oculus.alpenglow.database.Application;
import com.oculus.alpenglow.database.ApplicationDatabase;
import com.oculus.alpenglow.logging.InstallLogger;
import com.oculus.appmanager.info.model.InstallerResult;
import com.oculus.appmanager.info.model.InstallerResultError;
import com.oculus.appmanager.info.model.InstallerUpdateResult;
import com.oculus.executors.OculusThreadExecutor;
import com.oculus.library.model.AppStatus;
import com.oculus.library.model.InstallerCallbackReceiver;
import com.oculus.library.model.InstallerUpdateCallbackReceiver;
import com.oculus.library.utils.app.ImagesBuilder;
import com.oculus.libraryapi.OVRLibrary;
import com.oculus.os.enterprise.internal.AppInstallInfo;
import com.oculus.os.enterprise.internal.AppInstallStatus;
import java.util.List;

public class OVRLibraryBroadcastListener extends AnonymousClass0LT {
    public static final String TAG = "EnterpriseServer.OVRLibraryBroadcastListener";

    public static class OVRLibraryActionReceiver implements AbstractC04990iH, AnonymousClass0R6 {
        public AnonymousClass0R7 _UL_mInjectionContext;

        @Override // X.AbstractC04990iH
        public final void onReceive(Context context, Intent intent, AbstractC05000iI r9) {
            Integer num;
            InstallerUpdateResult installerUpdateResult;
            intent.getAction();
            if (OVRLibrary.ACTION_UPDATE_BROADCAST.equals(intent.getAction())) {
                this._UL_mInjectionContext = new AnonymousClass0R7(1, AnonymousClass0Lh.get(context));
                Bundle extras = intent.getExtras();
                InstallerResult installerResult = null;
                if (extras != null) {
                    installerResult = (InstallerResult) extras.getParcelable(InstallerCallbackReceiver.EXTRA_RESULT);
                    installerUpdateResult = (InstallerUpdateResult) extras.getParcelable(InstallerUpdateCallbackReceiver.EXTRA_RESULT);
                    num = Integer.valueOf(extras.getInt(OVRLibrary.EXTRA_ERROR_CODE));
                } else {
                    num = null;
                    installerUpdateResult = null;
                }
                OCMSInstaller oCMSInstaller = (OCMSInstaller) AnonymousClass0Lh.A03(0, ImagesBuilder.IMAGE_LANDSCAPE_HEIGHT, this._UL_mInjectionContext);
                if (installerResult != null) {
                    OculusThreadExecutor.A00().execute(new Runnable(installerResult, num) {
                        /* class com.oculus.alpenglow.install.$$Lambda$OCMSInstaller$3RUESgaqxpkRmtG5fcgr_cNuHQ2 */
                        public final /* synthetic */ InstallerResult f$1;
                        public final /* synthetic */ Integer f$2;

                        {
                            this.f$1 = r2;
                            this.f$2 = r3;
                        }

                        public final void run() {
                            String str;
                            OCMSInstaller oCMSInstaller = OCMSInstaller.this;
                            InstallerResult installerResult = this.f$1;
                            Integer num = this.f$2;
                            String str2 = installerResult.installIdentifier;
                            if (str2 != null) {
                                Application A2w = ((ApplicationDatabase) AnonymousClass0Lh.A03(2, 3, oCMSInstaller._UL_mInjectionContext)).mApplicationDao.A2w(str2);
                                if (A2w == null) {
                                    List<Application> A2y = ((ApplicationDatabase) AnonymousClass0Lh.A03(2, 3, oCMSInstaller._UL_mInjectionContext)).mApplicationDao.A2y(str2);
                                    for (Application application : A2y) {
                                        if (application.appSource == AppSource.ENTITLEMENT_BASED) {
                                            A2w = application;
                                        }
                                    }
                                    if (A2w == null && !A2y.isEmpty()) {
                                        A2w = A2y.get(0);
                                    }
                                }
                                if (A2w != null) {
                                    if (installerResult.isSuccess()) {
                                        ApplicationDatabase applicationDatabase = (ApplicationDatabase) AnonymousClass0Lh.A03(2, 3, oCMSInstaller._UL_mInjectionContext);
                                        String str3 = A2w.appId;
                                        String str4 = installerResult.packageName;
                                        if (str4 == null) {
                                            str4 = "";
                                        }
                                        AppStatus appStatus = AppStatus.INSTALLED;
                                        applicationDatabase.mApplicationDao.A7k(str3, str4, appStatus.ordinal());
                                        AnonymousClass0R7 r2 = oCMSInstaller._UL_mInjectionContext;
                                        BroadcastIntentHelper.A01((BroadcastIntentHelper) AnonymousClass0Lh.A03(9, 6, r2), (Context) AnonymousClass0Lh.A03(1, 4, r2), BroadcastIntentHelper.APP_INSTALLED, "com.oculus.systemux");
                                        ((InstallerState) AnonymousClass0Lh.A03(3, 70, oCMSInstaller._UL_mInjectionContext)).A04(A2w.appId);
                                        AnonymousClass0NK.A06(OCMSInstaller.TAG, "App installed = %s", installerResult.packageName);
                                        ((InstallLogger) AnonymousClass0Lh.A03(4, 133, oCMSInstaller._UL_mInjectionContext)).A03(A2w.appId, appStatus.toString(), installerResult.packageName, -1, false, false, null);
                                    } else {
                                        ((ApplicationDatabase) AnonymousClass0Lh.A03(2, 3, oCMSInstaller._UL_mInjectionContext)).A06(A2w.appId, AppStatus.INCOMPATIBLE);
                                        InstallerState installerState = (InstallerState) AnonymousClass0Lh.A03(3, 70, oCMSInstaller._UL_mInjectionContext);
                                        String str5 = A2w.appId;
                                        if (str5 != null) {
                                            AppInstallInfo appInstallInfo = new AppInstallInfo(str5, "", AppInstallStatus.ERROR, -1, -1);
                                            InstallerState.A01(installerState, str5, appInstallInfo);
                                            InstallerState.A01(installerState, "-1", appInstallInfo);
                                        }
                                        InstallerResultError installerResultError = installerResult.error;
                                        if (installerResultError != null) {
                                            str = installerResultError.name();
                                        } else {
                                            str = null;
                                        }
                                        if (num != null) {
                                            str = str + " " + num;
                                        }
                                        AnonymousClass0NK.A06(OCMSInstaller.TAG, "errorMsg = %s", str);
                                        ((InstallLogger) AnonymousClass0Lh.A03(4, 133, oCMSInstaller._UL_mInjectionContext)).A01(A2w.appId, str);
                                    }
                                    ((InvokeAppInstallListeners) AnonymousClass0Lh.A03(6, 18, oCMSInstaller._UL_mInjectionContext)).A00();
                                    return;
                                }
                                ((InstallLogger) AnonymousClass0Lh.A03(4, 133, oCMSInstaller._UL_mInjectionContext)).A01(str2, "Application missing from database");
                            }
                        }
                    });
                }
                if (installerUpdateResult != null) {
                    OculusThreadExecutor.A00().execute(new Runnable(installerUpdateResult) {
                        /* class com.oculus.alpenglow.install.$$Lambda$OCMSInstaller$TUoRSlGp76jOHqfxbmCskjjQoF82 */
                        public final /* synthetic */ InstallerUpdateResult f$1;

                        {
                            this.f$1 = r2;
                        }

                        public final void run() {
                            OCMSInstaller oCMSInstaller = OCMSInstaller.this;
                            InstallerUpdateResult installerUpdateResult = this.f$1;
                            String str = installerUpdateResult.mIstallIdentifier;
                            Application A2w = ((ApplicationDatabase) AnonymousClass0Lh.A03(2, 3, oCMSInstaller._UL_mInjectionContext)).mApplicationDao.A2w(str);
                            if (A2w == null) {
                                List<Application> A2y = ((ApplicationDatabase) AnonymousClass0Lh.A03(2, 3, oCMSInstaller._UL_mInjectionContext)).mApplicationDao.A2y(str);
                                for (Application application : A2y) {
                                    if (application.appSource == AppSource.ENTITLEMENT_BASED) {
                                        A2w = application;
                                    }
                                }
                                if (A2w == null && (A2y.isEmpty() || (A2w = A2y.get(0)) == null)) {
                                    return;
                                }
                            }
                            AppStatus valueOf = AppStatus.valueOf(installerUpdateResult.mAppStatus.name());
                            if (A2w.status != valueOf) {
                                ((ApplicationDatabase) AnonymousClass0Lh.A03(2, 3, oCMSInstaller._UL_mInjectionContext)).A06(A2w.appId, valueOf);
                            }
                            if (installerUpdateResult.mAppStatus == InstallerUpdateResult.AppStatus.DOWNLOADING) {
                                ((ApplicationDatabase) AnonymousClass0Lh.A03(2, 3, oCMSInstaller._UL_mInjectionContext)).mApplicationDao.A8j(A2w.appId, installerUpdateResult.mDownloadedBytes, installerUpdateResult.mTotalBytes);
                            }
                            if (installerUpdateResult.mAppStatus == InstallerUpdateResult.AppStatus.INSTALLING) {
                                ((ApplicationDatabase) AnonymousClass0Lh.A03(2, 3, oCMSInstaller._UL_mInjectionContext)).mApplicationDao.A8i(A2w.appId, A2w.totalBytes);
                            }
                            ((InstallerState) AnonymousClass0Lh.A03(3, 70, oCMSInstaller._UL_mInjectionContext)).A04(A2w.appId);
                        }
                    });
                }
            }
        }
    }

    public OVRLibraryBroadcastListener() {
        super(OVRLibrary.ACTION_UPDATE_BROADCAST, new OVRLibraryActionReceiver());
    }
}
