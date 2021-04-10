package com.oculus.coreapps;

import X.AbstractC06640p5;
import X.AbstractC07380s1;
import X.AnonymousClass006;
import X.AnonymousClass04J;
import X.AnonymousClass0DD;
import X.AnonymousClass0J2;
import X.AnonymousClass0KU;
import X.AnonymousClass0NO;
import X.AnonymousClass0QC;
import X.AnonymousClass117;
import android.content.ContentResolver;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import com.facebook.inject.ApplicationScoped;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.oculus.appmanager.info.ApkUpdateInfoContract;
import com.oculus.appmanager.info.model.RequestOrigin;
import com.oculus.appmanager.installer.util.InstallConfigBundler;
import com.oculus.appmanager.model.UpdateConfig;
import com.oculus.auth.credentials.Credentials;
import com.oculus.auth.credentials.CredentialsModule;
import com.oculus.auth.device.DeviceAuthTokenStore;
import com.oculus.auth.util.AccessTokenUtils;
import com.oculus.base.app.AppInfo;
import com.oculus.coreapps.CoreApp;
import com.oculus.debug.DebugMode;
import com.oculus.horizon.api.ApiRequestManager;
import com.oculus.horizon.api.common.MinimumAppVersion;
import com.oculus.horizon.logging.LoggingEvents;
import com.oculus.horizon.logging.OculusLogger;
import com.oculus.horizon.service_media.OVRMediaServiceManager;
import com.oculus.http.core.base.ApiCallback;
import com.oculus.libraryapi.OVRLibrary;
import com.oculus.libraryapi.OVRLibraryModule;
import com.oculus.ocms.library.provider.contract.OCMSLibraryContract;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;
import javax.inject.Provider;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_common_packagescache_PackageManagerUtils_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_api_ApiRequestManager_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_logging_OculusLogger_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_debug_DebugMode_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_auth_credentials_Credentials_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_libraryapi_OVRLibrary_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_auth_device_DeviceAuthTokenStore_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_base_app_AppInfo_ULSEP_BINDING_ID"})
@ApplicationScoped
public class CoreAppManager {
    public static final String TAG = "CoreAppManager";
    public static volatile CoreAppManager _UL__ULSEP_com_oculus_coreapps_CoreAppManager_ULSEP_INSTANCE;
    public AnonymousClass0QC _UL_mInjectionContext;
    @Inject
    @Eager
    public final ApiRequestManager mApiRequestManager;
    public final LinkedHashMap<String, CoreApp> mCoreApps = new LinkedHashMap<>();
    public final List<CoreAppsListener> mCoreAppsListeners = new ArrayList();
    @Inject
    public final Provider<Credentials> mCredentialsProvider;
    @Inject
    @Eager
    public final DebugMode mDebugMode;
    public final OVRLibrary.DownloadListener mDownloadListener = new OVRLibrary.DownloadListener() {
        /* class com.oculus.coreapps.CoreAppManager.AnonymousClass1 */

        /* JADX WARNING: Code restructure failed: missing block: B:10:0x0022, code lost:
            if (r10.error != null) goto L_0x006c;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:11:0x0024, code lost:
            r2 = r9.this$0;
            com.oculus.coreapps.CoreAppManager.A01(r2, new com.oculus.coreapps.CoreApp(r4, r4.status));
            r1 = r2.mOculusLogger;
            r6 = r2.mInstallId.toString();
            r0 = r4.entry;
            r5 = r0.package_name;
            r4 = r0.minimum_version;
            r3 = r2.mInstallRequestSource;
            r1 = ((com.oculus.logging.utils.EventManager) X.AnonymousClass0J2.A03(0, 242, r1._UL_mInjectionContext)).A22(com.oculus.horizon.logging.LoggingEvents.CORE_APP_DOWNLOAD_SUCCESS);
            r1.A1G();
            r1.A15("event_value", r5);
            r1.A15(com.oculus.horizon.logging.LoggingKeys.INSTALL_ID, r6);
            r1.A13(com.oculus.horizon.logging.LoggingKeys.APP_VERSION_CODE, r4);
            r1.A15("source", r3);
            r1.A5L();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x006c, code lost:
            r2 = r9.this$0;
            com.oculus.coreapps.CoreAppManager.A01(r2, new com.oculus.coreapps.CoreApp(r4, com.oculus.coreapps.CoreApp.Status.DOWNLOAD_FAILED));
            r7 = r10.error;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x007a, code lost:
            if (r7 == null) goto L_0x00ec;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x007c, code lost:
            r3 = r4.entry;
            X.AnonymousClass0NO.A0E(com.oculus.coreapps.CoreAppManager.TAG, "Failed to download core package %s (%d) reason: %s", r3.package_name, java.lang.Integer.valueOf(r3.minimum_version), r7.name());
            r1 = r2.mOculusLogger;
            r7 = r2.mInstallId.toString();
            r0 = r4.entry;
            r8 = r0.package_name;
            r6 = r0.minimum_version;
            r5 = r2.mInstallRequestSource;
            r4 = r10.error.code;
            r1 = ((com.oculus.logging.utils.EventManager) X.AnonymousClass0J2.A03(0, 242, r1._UL_mInjectionContext)).A22(com.oculus.horizon.logging.LoggingEvents.CORE_APP_DOWNLOAD_FAILURE);
            r1.A1G();
            r1.A15("event_value", r8);
            r1.A15(com.oculus.horizon.logging.LoggingKeys.INSTALL_ID, r7);
            r1.A13(com.oculus.horizon.logging.LoggingKeys.APP_VERSION_CODE, r6);
            r1.A15("source", r5);
            r1.A13("error_code", r4);
            r1.A5L();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:15:0x00e5, code lost:
            if (r2.mInstallOrder != com.oculus.coreapps.CoreAppManager.InstallOrder.SEQUENTIAL) goto L_?;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:16:0x00e7, code lost:
            com.oculus.coreapps.CoreAppManager.A02(r2, true);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x00eb, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:0x00f3, code lost:
            throw new java.lang.IllegalStateException("not actually an failure");
         */
        /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
            return;
         */
        @Override // com.oculus.libraryapi.OVRLibrary.DownloadListener
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void A5w(com.oculus.appmanager.info.model.InstallerResult r10) {
            /*
            // Method dump skipped, instructions count: 247
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.coreapps.CoreAppManager.AnonymousClass1.A5w(com.oculus.appmanager.info.model.InstallerResult):void");
        }
    };
    public UUID mInstallId;
    public final OVRLibrary.InstallListener mInstallListener = new OVRLibrary.InstallListener() {
        /* class com.oculus.coreapps.CoreAppManager.AnonymousClass2 */

        /* JADX WARNING: Code restructure failed: missing block: B:10:0x0022, code lost:
            if (r10.error != null) goto L_0x007b;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:11:0x0024, code lost:
            r3 = r9.this$0;
            com.oculus.coreapps.CoreAppManager.A01(r3, new com.oculus.coreapps.CoreApp(r4, com.oculus.coreapps.CoreApp.Status.INSTALL_SUCCESS));
            r1 = r3.mOculusLogger;
            r7 = r3.mInstallId.toString();
            r0 = r4.entry;
            r6 = r0.package_name;
            r5 = r0.minimum_version;
            r4 = r3.mInstallRequestSource;
            r1 = ((com.oculus.logging.utils.EventManager) X.AnonymousClass0J2.A03(0, 242, r1._UL_mInjectionContext)).A22(com.oculus.horizon.logging.LoggingEvents.CORE_APP_INSTALL_SUCCESS);
            r1.A1G();
            r1.A15("event_value", r6);
            r1.A15(com.oculus.horizon.logging.LoggingKeys.INSTALL_ID, r7);
            r1.A13(com.oculus.horizon.logging.LoggingKeys.APP_VERSION_CODE, r5);
            r1.A15("source", r4);
            r1.A5L();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x006f, code lost:
            if (r3.mInstallOrder != com.oculus.coreapps.CoreAppManager.InstallOrder.SEQUENTIAL) goto L_0x0075;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x0071, code lost:
            com.oculus.coreapps.CoreAppManager.A02(r3, true);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x0075, code lost:
            com.oculus.coreapps.CoreAppManager.A00(r9.this$0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:15:0x007a, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:16:0x007b, code lost:
            r2 = r9.this$0;
            com.oculus.coreapps.CoreAppManager.A01(r2, new com.oculus.coreapps.CoreApp(r4, com.oculus.coreapps.CoreApp.Status.INSTALL_FAILED));
            r7 = r10.error;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x0089, code lost:
            if (r7 == null) goto L_0x00fe;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:18:0x008b, code lost:
            r3 = r4.entry;
            X.AnonymousClass0NO.A0E(com.oculus.coreapps.CoreAppManager.TAG, "Failed to install core package %s to version (%d) reason: %s", r3.package_name, java.lang.Integer.valueOf(r3.minimum_version), r7.name());
            r1 = r2.mOculusLogger;
            r7 = r2.mInstallId.toString();
            r0 = r4.entry;
            r8 = r0.package_name;
            r6 = r0.minimum_version;
            r5 = r2.mInstallRequestSource;
            r4 = r10.error.name();
            r1 = ((com.oculus.logging.utils.EventManager) X.AnonymousClass0J2.A03(0, 242, r1._UL_mInjectionContext)).A22(com.oculus.horizon.logging.LoggingEvents.CORE_APP_INSTALL_FAILURE);
            r1.A1G();
            r1.A15("event_value", r8);
            r1.A15(com.oculus.horizon.logging.LoggingKeys.INSTALL_ID, r7);
            r1.A13(com.oculus.horizon.logging.LoggingKeys.APP_VERSION_CODE, r6);
            r1.A15("source", r5);
            r1.A15("error_code", r4);
            r1.A5L();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:0x00f6, code lost:
            if (r2.mInstallOrder != com.oculus.coreapps.CoreAppManager.InstallOrder.SEQUENTIAL) goto L_0x0075;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:20:0x00f8, code lost:
            com.oculus.coreapps.CoreAppManager.A02(r2, true);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:22:0x0105, code lost:
            throw new java.lang.IllegalStateException("not actually an failure");
         */
        @Override // com.oculus.libraryapi.OVRLibrary.InstallListener
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void A6E(com.oculus.appmanager.info.model.InstallerResult r10) {
            /*
            // Method dump skipped, instructions count: 265
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.coreapps.CoreAppManager.AnonymousClass2.A6E(com.oculus.appmanager.info.model.InstallerResult):void");
        }
    };
    public InstallOrder mInstallOrder = InstallOrder.PARALLEL;
    public String mInstallRequestSource;
    @Inject
    @Eager
    public final OVRLibrary mOVRLibrary;
    @Inject
    @Eager
    public final OculusLogger mOculusLogger;
    public RequestOrigin mRequestOrigin = RequestOrigin.ROADBLOCK;
    public AnonymousClass0DD<CoreApp[]> mRunningTaskCompletionSource;

    /* renamed from: com.oculus.coreapps.CoreAppManager$3  reason: invalid class name */
    public class AnonymousClass3 extends ApiCallback<MinimumAppVersion> {
        public final /* synthetic */ CoreAppManager this$0;
        public final /* synthetic */ String val$installRequestSource;
        public final /* synthetic */ boolean val$skipHorizon;
    }

    public interface CoreAppsListener {
        void A5t(CoreApp[] coreAppArr);
    }

    public enum InstallOrder {
        SEQUENTIAL,
        PARALLEL
    }

    public static void A00(CoreAppManager coreAppManager) {
        boolean z;
        synchronized (coreAppManager) {
            if (coreAppManager.A06()) {
                synchronized (coreAppManager) {
                    synchronized (coreAppManager.mCoreApps) {
                        Iterator<CoreApp> it = coreAppManager.mCoreApps.values().iterator();
                        while (true) {
                            if (it.hasNext()) {
                                if (!it.next().A00()) {
                                    z = false;
                                    break;
                                }
                            } else {
                                z = true;
                                break;
                            }
                        }
                    }
                }
                if (!z) {
                    AnonymousClass0KU.A01(Boolean.valueOf(coreAppManager.A06()));
                    synchronized (coreAppManager.mCoreApps) {
                        for (CoreApp coreApp : coreAppManager.mCoreApps.values()) {
                            if ("com.oculus.horizon".equals(coreApp.entry.package_name)) {
                                if (coreApp.status != CoreApp.Status.IN_PROGRESS && !coreApp.A00()) {
                                }
                            } else if (!coreApp.A00()) {
                            }
                            return;
                        }
                        A02(coreAppManager, false);
                        return;
                    }
                }
            }
        }
        if (coreAppManager.A06()) {
            AnonymousClass0DD<CoreApp[]> r4 = coreAppManager.mRunningTaskCompletionSource;
            coreAppManager.mRunningTaskCompletionSource = null;
            OVRLibrary oVRLibrary = coreAppManager.mOVRLibrary;
            OVRLibrary.DownloadListener downloadListener = coreAppManager.mDownloadListener;
            synchronized (oVRLibrary) {
                ContentResolver contentResolver = oVRLibrary.mContext.getContentResolver();
                ArrayList<OVRLibrary.DownloadContentObserver> remove = oVRLibrary.mDownloadListeners.remove(downloadListener);
                if (remove != null) {
                    for (OVRLibrary.DownloadContentObserver downloadContentObserver : remove) {
                        contentResolver.unregisterContentObserver(downloadContentObserver);
                        Handler handler = downloadContentObserver.handler;
                        if (handler != null) {
                            handler.removeCallbacksAndMessages(null);
                        }
                    }
                }
            }
            OVRLibrary oVRLibrary2 = coreAppManager.mOVRLibrary;
            OVRLibrary.InstallListener installListener = coreAppManager.mInstallListener;
            synchronized (oVRLibrary2) {
                ContentResolver contentResolver2 = oVRLibrary2.mContext.getContentResolver();
                ArrayList<OVRLibrary.InstallContentObserver> remove2 = oVRLibrary2.mInstallListeners.remove(installListener);
                if (remove2 != null) {
                    for (OVRLibrary.InstallContentObserver installContentObserver : remove2) {
                        contentResolver2.unregisterContentObserver(installContentObserver);
                        Handler handler2 = installContentObserver.handler;
                        if (handler2 != null) {
                            handler2.removeCallbacksAndMessages(null);
                        }
                    }
                }
            }
            CoreApp[] A04 = A04(coreAppManager);
            for (int i = 0; i < A04.length; i++) {
            }
            A03(coreAppManager, A04, false);
            synchronized (coreAppManager.mCoreAppsListeners) {
                AbstractC07380s1 A0K = ImmutableList.A0C(coreAppManager.mCoreAppsListeners).iterator();
                while (A0K.hasNext()) {
                    ((CoreAppsListener) A0K.next()).A5t(A04);
                }
            }
            r4.A02(A04);
        }
    }

    public static final synchronized void A03(CoreAppManager coreAppManager, CoreApp[] coreAppArr, boolean z) {
        synchronized (coreAppManager) {
            int length = coreAppArr.length;
            String[] strArr = new String[length];
            String[] strArr2 = new String[length];
            for (int i = 0; i < length; i++) {
                strArr[i] = coreAppArr[i].entry.package_name;
                strArr2[i] = coreAppArr[i].status.toString();
            }
            if (z) {
                OculusLogger.A00(coreAppManager.mOculusLogger, LoggingEvents.CORE_APP_START, coreAppManager.mInstallId.toString(), strArr, strArr2, coreAppManager.mInstallRequestSource);
            } else {
                OculusLogger.A00(coreAppManager.mOculusLogger, LoggingEvents.CORE_APP_END, coreAppManager.mInstallId.toString(), strArr, strArr2, coreAppManager.mInstallRequestSource);
            }
        }
    }

    public static final synchronized CoreApp[] A04(CoreAppManager coreAppManager) {
        ArrayList arrayList;
        CoreApp[] coreAppArr;
        synchronized (coreAppManager) {
            synchronized (coreAppManager.mCoreApps) {
                arrayList = new ArrayList(coreAppManager.mCoreApps.values());
            }
            coreAppArr = (CoreApp[]) arrayList.toArray(new CoreApp[arrayList.size()]);
        }
        return coreAppArr;
    }

    public final synchronized void A05(List<MinimumAppVersion.MinimumAppVersionEntry> list, boolean z) {
        if (A06()) {
            AnonymousClass0NO.A09(TAG, "Install already in progress, ignoring duplicate request.");
        } else {
            synchronized (this.mCoreApps) {
                this.mCoreApps.clear();
                for (MinimumAppVersion.MinimumAppVersionEntry minimumAppVersionEntry : list) {
                    String str = minimumAppVersionEntry.package_name;
                    if (!"com.oculus.horizon".equals(str) || !z) {
                        this.mCoreApps.put(str, new CoreApp(minimumAppVersionEntry));
                    }
                }
            }
        }
    }

    public final synchronized boolean A06() {
        boolean z;
        z = false;
        if (this.mRunningTaskCompletionSource != null) {
            z = true;
        }
        return z;
    }

    public static void A01(CoreAppManager coreAppManager, CoreApp coreApp) {
        synchronized (coreAppManager.mCoreApps) {
            coreAppManager.mCoreApps.put(coreApp.entry.package_name, coreApp);
        }
        synchronized (coreAppManager.mCoreAppsListeners) {
            AbstractC07380s1 A0K = ImmutableList.A0C(coreAppManager.mCoreAppsListeners).iterator();
            while (A0K.hasNext()) {
                A0K.next();
            }
        }
    }

    public static void A02(CoreAppManager coreAppManager, boolean z) {
        String str;
        long j;
        String str2;
        UpdateConfig updateConfig;
        long j2;
        synchronized (coreAppManager.mCoreApps) {
            for (CoreApp coreApp : coreAppManager.mCoreApps.values()) {
                if (!coreApp.A00()) {
                    if (!"com.oculus.horizon".equals(coreApp.entry.package_name) || !z) {
                        if (AnonymousClass04J.A01((Context) AnonymousClass0J2.A03(0, 294, coreAppManager._UL_mInjectionContext), OVRMediaServiceManager.EXTERNAL_STORAGE_PERMISSION) == 0) {
                            A01(coreAppManager, new CoreApp(coreApp, CoreApp.Status.IN_PROGRESS));
                            Credentials credentials = coreAppManager.mCredentialsProvider.get();
                            if (credentials == null) {
                                AnonymousClass0QC r5 = coreAppManager._UL_mInjectionContext;
                                str = AccessTokenUtils.createLoggedOutToken((AppInfo) AnonymousClass0J2.A03(3, 560, r5), ((DeviceAuthTokenStore) AnonymousClass0J2.A03(2, 249, r5)).mAuthToken);
                            } else {
                                str = credentials.mAccessToken;
                            }
                            MinimumAppVersion.MinimumAppVersionEntry minimumAppVersionEntry = coreApp.entry;
                            String str3 = minimumAppVersionEntry.package_name;
                            long j3 = (long) minimumAppVersionEntry.download_version;
                            ApkUpdateInfoContract.UpdateType updateType = ApkUpdateInfoContract.UpdateType.STORE_FULL_APK;
                            String str4 = minimumAppVersionEntry.download_uri;
                            Optional<Long> optional = minimumAppVersionEntry.size;
                            if (optional.isPresent()) {
                                j = optional.get().longValue();
                            } else {
                                j = -1;
                            }
                            String str5 = minimumAppVersionEntry.display_name;
                            Optional<String> optional2 = minimumAppVersionEntry.external_signature;
                            if (optional2.isPresent()) {
                                str2 = optional2.get();
                            } else {
                                str2 = "";
                            }
                            UpdateConfig updateConfig2 = new UpdateConfig(str3, j3, updateType, str4, j, str, str5, str2);
                            Optional<String> optional3 = minimumAppVersionEntry.obbUri;
                            if (optional3.isPresent()) {
                                ApkUpdateInfoContract.UpdateType updateType2 = ApkUpdateInfoContract.UpdateType.STORE_FULL_OBB;
                                String str6 = optional3.get();
                                Optional<Long> optional4 = minimumAppVersionEntry.obbSize;
                                if (optional4.isPresent()) {
                                    j2 = optional4.get().longValue();
                                } else {
                                    j2 = -1;
                                }
                                updateConfig = new UpdateConfig(str3, j3, updateType2, str6, j2, str, str5, null);
                            } else {
                                updateConfig = null;
                            }
                            OVRLibrary oVRLibrary = coreAppManager.mOVRLibrary;
                            RequestOrigin requestOrigin = coreAppManager.mRequestOrigin;
                            Bundle bundle = new Bundle();
                            if (requestOrigin != null) {
                                bundle.putString(OCMSLibraryContract.EXTRA_REQUEST_ORIGIN, requestOrigin.name());
                            }
                            bundle.putBundle(OCMSLibraryContract.EXTRA_UPDATE_CONFIG_APK, InstallConfigBundler.A00(updateConfig2));
                            if (updateConfig != null) {
                                bundle.putBundle(OCMSLibraryContract.EXTRA_UPDATE_CONFIG_OBB, InstallConfigBundler.A00(updateConfig));
                            }
                            oVRLibrary.mContext.getContentResolver().call(OCMSLibraryContract.A00(), OCMSLibraryContract.METHOD_INSTALL_CUSTOM_PACKAGE, (String) null, bundle);
                            if (coreAppManager.mInstallOrder == InstallOrder.SEQUENTIAL) {
                                break;
                            }
                        } else {
                            AnonymousClass0NO.A08(TAG, AnonymousClass006.A07("Unable to download ", coreApp.entry.package_name, ", do not have external storage permission"));
                            A01(coreAppManager, new CoreApp(coreApp, CoreApp.Status.DOWNLOAD_FAILED));
                        }
                    }
                }
            }
        }
        A00(coreAppManager);
    }

    @Inject
    public CoreAppManager(AbstractC06640p5 r3) {
        this._UL_mInjectionContext = new AnonymousClass0QC(4, r3);
        this.mApiRequestManager = ApiRequestManager._UL__ULSEP_com_oculus_horizon_api_ApiRequestManager_ULSEP_ACCESS_METHOD(r3);
        this.mOculusLogger = (OculusLogger) AnonymousClass117.A00(574, r3);
        this.mDebugMode = DebugMode.A00(r3);
        this.mCredentialsProvider = CredentialsModule._UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_auth_credentials_Credentials_ULGT__ULSEP_ACCESS_METHOD(r3);
        this.mOVRLibrary = OVRLibraryModule.A00(r3);
    }
}
