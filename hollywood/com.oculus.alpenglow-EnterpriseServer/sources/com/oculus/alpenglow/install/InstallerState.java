package com.oculus.alpenglow.install;

import X.AbstractC02990bJ;
import X.AnonymousClass0Lh;
import X.AnonymousClass0NK;
import X.AnonymousClass0R7;
import android.os.RemoteException;
import com.facebook.inject.ApplicationScoped;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.alpenglow.database.Application;
import com.oculus.alpenglow.database.ApplicationDatabase;
import com.oculus.library.model.AppStatus;
import com.oculus.os.enterprise.internal.AppInstallInfo;
import com.oculus.os.enterprise.internal.AppInstallStatus;
import com.oculus.os.enterprise.internal.IAppInstallStatusChangeCallback;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;

@Dependencies({"_UL__ULSEP_com_oculus_alpenglow_database_ApplicationDatabase_ULSEP_BINDING_ID"})
@ApplicationScoped
public class InstallerState {
    public static final String ALL_APP_ID = "-1";
    public static final String TAG = "EnterpriseServer.InstallerState";
    public static volatile InstallerState _UL__ULSEP_com_oculus_alpenglow_install_InstallerState_ULSEP_INSTANCE;
    public AnonymousClass0R7 _UL_mInjectionContext;
    public final Map<String, List<IAppInstallStatusChangeCallback>> callbackMap = Collections.synchronizedMap(new HashMap());

    @Nullable
    public final AppInstallInfo A02(@Nullable String str, @Nullable String str2) {
        Application application;
        if (str != null) {
            application = ((ApplicationDatabase) AnonymousClass0Lh.A03(0, 3, this._UL_mInjectionContext)).mApplicationDao.A2w(str);
        } else {
            if (str2 != null) {
                List<Application> A2y = ((ApplicationDatabase) AnonymousClass0Lh.A03(0, 3, this._UL_mInjectionContext)).mApplicationDao.A2y(str2);
                if (!A2y.isEmpty()) {
                    application = A2y.get(0);
                }
            }
            application = null;
        }
        return A00(application);
    }

    public final Map<String, AppInstallInfo> A03() {
        List<Application> A03 = ((ApplicationDatabase) AnonymousClass0Lh.A03(0, 3, this._UL_mInjectionContext)).A03();
        HashMap hashMap = new HashMap();
        for (Application application : A03) {
            hashMap.put(application.appId, A00(application));
        }
        return hashMap;
    }

    public final void A05(@Nullable String str, @Nullable String str2) {
        String str3 = str2;
        if (str != null) {
            if (str2 == null) {
                str3 = "";
            }
            AppInstallInfo appInstallInfo = new AppInstallInfo(str, str3, AppInstallStatus.UNINSTALLED, -1, -1);
            A01(this, str, appInstallInfo);
            A01(this, "-1", appInstallInfo);
        }
    }

    /* renamed from: com.oculus.alpenglow.install.InstallerState$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$library$model$AppStatus;

        /* JADX WARNING: Can't wrap try/catch for region: R(16:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|(3:15|16|18)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(18:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|18) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0036 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x003f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0048 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0024 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002d */
        static {
            /*
                com.oculus.library.model.AppStatus[] r0 = com.oculus.library.model.AppStatus.values()
                int r0 = r0.length
                int[] r2 = new int[r0]
                com.oculus.alpenglow.install.InstallerState.AnonymousClass1.$SwitchMap$com$oculus$library$model$AppStatus = r2
                com.oculus.library.model.AppStatus r0 = com.oculus.library.model.AppStatus.UNKNOWN     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0 = 1
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                com.oculus.library.model.AppStatus r0 = com.oculus.library.model.AppStatus.ENTITLED     // Catch:{ NoSuchFieldError -> 0x001b }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x001b }
                r0 = 2
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x001b }
            L_0x001b:
                com.oculus.library.model.AppStatus r0 = com.oculus.library.model.AppStatus.DOWNLOAD_QUEUED     // Catch:{ NoSuchFieldError -> 0x0024 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0024 }
                r0 = 3
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0024 }
            L_0x0024:
                com.oculus.library.model.AppStatus r0 = com.oculus.library.model.AppStatus.DOWNLOADING     // Catch:{ NoSuchFieldError -> 0x002d }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x002d }
                r0 = 4
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x002d }
            L_0x002d:
                com.oculus.library.model.AppStatus r0 = com.oculus.library.model.AppStatus.INSTALLING     // Catch:{ NoSuchFieldError -> 0x0036 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0036 }
                r0 = 5
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0036 }
            L_0x0036:
                com.oculus.library.model.AppStatus r0 = com.oculus.library.model.AppStatus.INSTALLED     // Catch:{ NoSuchFieldError -> 0x003f }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x003f }
                r0 = 6
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x003f }
            L_0x003f:
                com.oculus.library.model.AppStatus r0 = com.oculus.library.model.AppStatus.UNINSTALLING     // Catch:{ NoSuchFieldError -> 0x0048 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0048 }
                r0 = 7
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0048 }
            L_0x0048:
                com.oculus.library.model.AppStatus r0 = com.oculus.library.model.AppStatus.INCOMPATIBLE     // Catch:{ NoSuchFieldError -> 0x0052 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0052 }
                r0 = 8
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0052 }
            L_0x0052:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.alpenglow.install.InstallerState.AnonymousClass1.<clinit>():void");
        }
    }

    @Nullable
    public static AppInstallInfo A00(@Nullable Application application) {
        AppInstallStatus appInstallStatus;
        if (application == null) {
            return null;
        }
        String str = application.appId;
        String str2 = application.packageName;
        AppStatus appStatus = application.status;
        if (appStatus != null) {
            switch (appStatus.ordinal()) {
                case 0:
                case 1:
                case 3:
                    appInstallStatus = AppInstallStatus.QUEUED;
                    break;
                case 4:
                    appInstallStatus = AppInstallStatus.DOWNLOADING;
                    break;
                case 5:
                    appInstallStatus = AppInstallStatus.INSTALLING;
                    break;
                case 6:
                    appInstallStatus = AppInstallStatus.INSTALLED;
                    break;
                case 7:
                    appInstallStatus = AppInstallStatus.UNINSTALLING;
                    break;
            }
            return new AppInstallInfo(str, str2, appInstallStatus, application.downloadedBytes, application.totalBytes);
        }
        appInstallStatus = AppInstallStatus.ERROR;
        return new AppInstallInfo(str, str2, appInstallStatus, application.downloadedBytes, application.totalBytes);
    }

    public static void A01(@Nullable InstallerState installerState, @Nullable String str, AppInstallInfo appInstallInfo) {
        if (!(str == null || appInstallInfo == null)) {
            HashMap hashMap = new HashMap();
            hashMap.put(appInstallInfo.appId, appInstallInfo);
            List<IAppInstallStatusChangeCallback> list = installerState.callbackMap.get(str);
            if (!(list == null || list.isEmpty())) {
                ArrayList<Object> arrayList = new ArrayList();
                for (IAppInstallStatusChangeCallback iAppInstallStatusChangeCallback : list) {
                    try {
                        iAppInstallStatusChangeCallback.onStatusChange(hashMap);
                    } catch (RemoteException e) {
                        AnonymousClass0NK.A04(TAG, "RemoteException on update", e);
                        arrayList.add(iAppInstallStatusChangeCallback);
                    }
                }
                if (!arrayList.isEmpty()) {
                    for (Object obj : arrayList) {
                        list.remove(obj);
                    }
                }
            }
        }
    }

    public final void A04(@Nullable String str) {
        if (str != null) {
            AppInstallInfo A00 = A00(((ApplicationDatabase) AnonymousClass0Lh.A03(0, 3, this._UL_mInjectionContext)).mApplicationDao.A2w(str));
            A01(this, str, A00);
            A01(this, "-1", A00);
        }
    }

    @Inject
    public InstallerState(AbstractC02990bJ r3) {
        this._UL_mInjectionContext = new AnonymousClass0R7(1, r3);
    }
}
