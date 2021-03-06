package com.oculus.ocms.library;

import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.facebook.debug.log.BLog;
import com.facebook.inject.ApplicationScopeClassInit;
import com.facebook.inject.ApplicationScoped;
import com.facebook.inject.BundledAndroidModule;
import com.facebook.inject.FbInjector;
import com.facebook.inject.InjectionContext;
import com.facebook.inject.InjectorLike;
import com.facebook.inject.Lazy;
import com.facebook.inject.UltralightSingletonProvider;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.facebook.ultralight.UL;
import com.oculus.appmanager.info.ApkUpdateInfo;
import com.oculus.appmanager.info.ApkUpdateInfoListener;
import com.oculus.appmanager.installer.events.DownloadProgressResponse;
import com.oculus.appmanager.installer.events.DownloadQueuedResponse;
import com.oculus.appmanager.installer.events.DownloadResponse;
import com.oculus.appmanager.installer.events.DownloadingResponse;
import com.oculus.appmanager.installer.events.InstallCancelledResponse;
import com.oculus.appmanager.installer.events.InstallResponse;
import com.oculus.appmanager.installer.events.InstallerEventBus;
import com.oculus.appmanager.uninstaller.events.UninstallRequestedEvent;
import com.oculus.appmanager.uninstaller.events.UninstallResponse;
import com.oculus.appmanager.uninstaller.events.UninstallerEventBus;
import com.oculus.common.init.INeedInit;
import com.oculus.executors.OculusThreadExecutor;
import com.oculus.library.model.App;
import com.oculus.library.model.AppStatus;
import com.oculus.library.utils.app.AppConverter;
import com.oculus.library.utils.app.AppConverterUtilsModule;
import com.oculus.libraryapi.OVRLibrary;
import com.oculus.libraryapi.OVRLibraryModule;
import com.oculus.ocms.library.OCMSLibraryModule;
import com.squareup.otto.Subscribe;
import java.util.Set;
import javax.inject.Provider;

@Dependencies({"_UL__ULSEP_com_oculus_libraryapi_OVRLibrary_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_library_utils_app_AppConverter_ULSEP_BINDING_ID", "_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID"})
@ApplicationScoped
public class EntitlementsInstallerEventListener implements INeedInit, ApkUpdateInfoListener {
    private static final String TAG = "EntitlementsInstallerEventListener";
    private static volatile EntitlementsInstallerEventListener _UL__ULSEP_com_oculus_ocms_library_EntitlementsInstallerEventListener_ULSEP_INSTANCE;
    private InjectionContext _UL_mInjectionContext;

    @Override // com.oculus.appmanager.info.ApkUpdateInfoListener
    public void onChanged(ApkUpdateInfo apkUpdateInfo, Set<String> set, ApkUpdateInfo.ApkUpdateExtras apkUpdateExtras) {
    }

    @Override // com.oculus.appmanager.info.ApkUpdateInfoListener
    public void onDeleted(ApkUpdateInfo apkUpdateInfo) {
    }

    @AutoGeneratedAccessMethod
    public static final Lazy _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_ocms_library_EntitlementsInstallerEventListener_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightSingletonProvider.get(OCMSLibraryModule.UL_id._UL__ULSEP_com_oculus_ocms_library_EntitlementsInstallerEventListener_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final EntitlementsInstallerEventListener _UL__ULSEP_com_oculus_ocms_library_EntitlementsInstallerEventListener_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (EntitlementsInstallerEventListener) UL.factorymap.get(OCMSLibraryModule.UL_id._UL__ULSEP_com_oculus_ocms_library_EntitlementsInstallerEventListener_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedFactoryMethod
    public static final EntitlementsInstallerEventListener _UL__ULSEP_com_oculus_ocms_library_EntitlementsInstallerEventListener_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        if (_UL__ULSEP_com_oculus_ocms_library_EntitlementsInstallerEventListener_ULSEP_INSTANCE == null) {
            synchronized (EntitlementsInstallerEventListener.class) {
                ApplicationScopeClassInit start = ApplicationScopeClassInit.start(_UL__ULSEP_com_oculus_ocms_library_EntitlementsInstallerEventListener_ULSEP_INSTANCE, injectorLike);
                if (start != null) {
                    try {
                        _UL__ULSEP_com_oculus_ocms_library_EntitlementsInstallerEventListener_ULSEP_INSTANCE = new EntitlementsInstallerEventListener(injectorLike.getApplicationInjector());
                    } finally {
                        start.finish();
                    }
                }
            }
        }
        return _UL__ULSEP_com_oculus_ocms_library_EntitlementsInstallerEventListener_ULSEP_INSTANCE;
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_ocms_library_EntitlementsInstallerEventListener_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightSingletonProvider.get(OCMSLibraryModule.UL_id._UL__ULSEP_com_oculus_ocms_library_EntitlementsInstallerEventListener_ULSEP_BINDING_ID, injectorLike);
    }

    @Inject
    public EntitlementsInstallerEventListener(InjectorLike injectorLike) {
        this._UL_mInjectionContext = new InjectionContext(3, injectorLike);
    }

    @Override // com.oculus.common.init.INeedInit
    public void init() {
        InstallerEventBus.getInstance().register(this);
        UninstallerEventBus.getInstance().register(this);
    }

    @Override // com.oculus.appmanager.info.ApkUpdateInfoListener
    public void onCreated(ApkUpdateInfo apkUpdateInfo, ApkUpdateInfoListener.CreationType creationType) {
        BLog.d(TAG, "onCreated(%s, %s)", apkUpdateInfo, creationType.name());
        final String str = apkUpdateInfo.identifier;
        if (!TextUtils.isEmpty(str) && ((OVRLibrary) FbInjector.lazyInstance(0, OVRLibraryModule.UL_id._UL__ULSEP_com_oculus_libraryapi_OVRLibrary_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getApp(str) != null && creationType == ApkUpdateInfoListener.CreationType.NEW) {
            OculusThreadExecutor.getInstance().execute(new Runnable() {
                /* class com.oculus.ocms.library.EntitlementsInstallerEventListener.AnonymousClass1 */

                public void run() {
                    ((OVRLibrary) FbInjector.lazyInstance(0, OVRLibraryModule.UL_id._UL__ULSEP_com_oculus_libraryapi_OVRLibrary_ULSEP_BINDING_ID, EntitlementsInstallerEventListener.this._UL_mInjectionContext)).save(new App.Editor(str).withStatus(AppStatus.DOWNLOAD_QUEUED).withDownloadedSizeBytes(0));
                }
            });
        }
    }

    @Subscribe
    public void onInstallCancelled(InstallCancelledResponse installCancelledResponse) {
        BLog.d(TAG, "onInstallCancelled(%s)", installCancelledResponse);
        String str = installCancelledResponse.installIdentifier;
        if (!TextUtils.isEmpty(str) && ((OVRLibrary) FbInjector.lazyInstance(0, OVRLibraryModule.UL_id._UL__ULSEP_com_oculus_libraryapi_OVRLibrary_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getApp(str) != null) {
            AppStatus appStatus = AppStatus.ENTITLED;
            if (isAppInstalled(str)) {
                appStatus = AppStatus.INSTALLED;
            }
            updateEntitlementState(str, appStatus);
        }
    }

    @Subscribe
    public void onDownloadQueued(DownloadQueuedResponse downloadQueuedResponse) {
        BLog.d(TAG, "onDownloadQueued(%s)", downloadQueuedResponse);
        String str = downloadQueuedResponse.installIdentifier;
        if (!TextUtils.isEmpty(str) && ((OVRLibrary) FbInjector.lazyInstance(0, OVRLibraryModule.UL_id._UL__ULSEP_com_oculus_libraryapi_OVRLibrary_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getApp(str) != null) {
            updateEntitlementState(str, AppStatus.DOWNLOAD_QUEUED);
        }
    }

    @Subscribe
    public void onDownloadProgress(DownloadProgressResponse downloadProgressResponse) {
        BLog.d(TAG, "onDownloadProgress(%s)", downloadProgressResponse);
        String str = downloadProgressResponse.installIdentifier;
        if (!TextUtils.isEmpty(str) && ((OVRLibrary) FbInjector.lazyInstance(0, OVRLibraryModule.UL_id._UL__ULSEP_com_oculus_libraryapi_OVRLibrary_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getApp(str) != null) {
            updateEntitlementDownloadProgress(str, downloadProgressResponse.downloadingBytes);
        }
    }

    @Subscribe
    public void onDownloadingResponse(final DownloadingResponse downloadingResponse) {
        BLog.d(TAG, "onDownloadingResponse(%s)", downloadingResponse);
        final String str = downloadingResponse.installIdentifier;
        if (!TextUtils.isEmpty(str) && ((OVRLibrary) FbInjector.lazyInstance(0, OVRLibraryModule.UL_id._UL__ULSEP_com_oculus_libraryapi_OVRLibrary_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getApp(str) != null) {
            OculusThreadExecutor.getInstance().execute(new Runnable() {
                /* class com.oculus.ocms.library.EntitlementsInstallerEventListener.AnonymousClass2 */

                public void run() {
                    ((OVRLibrary) FbInjector.lazyInstance(0, OVRLibraryModule.UL_id._UL__ULSEP_com_oculus_libraryapi_OVRLibrary_ULSEP_BINDING_ID, EntitlementsInstallerEventListener.this._UL_mInjectionContext)).save(new App.Editor(str).withStatus(AppStatus.DOWNLOADING).withDownloadSizeBytes(downloadingResponse.downloadSize));
                }
            });
        }
    }

    @Subscribe
    public void onDownloadResponse(DownloadResponse downloadResponse) {
        BLog.d(TAG, "onDownloadResponse(%s)", downloadResponse);
        String str = downloadResponse.installIdentifier;
        if (!TextUtils.isEmpty(str) && ((OVRLibrary) FbInjector.lazyInstance(0, OVRLibraryModule.UL_id._UL__ULSEP_com_oculus_libraryapi_OVRLibrary_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getApp(str) != null) {
            if (downloadResponse.isSuccess) {
                updateEntitlementState(str, AppStatus.INSTALLING);
            } else {
                updateEntitlementState(str, getDefaultStatusForPackage(str));
            }
        }
    }

    @Subscribe
    public void onInstallResponse(final InstallResponse installResponse) {
        BLog.d(TAG, "onInstallResponse(%s)", installResponse);
        final String str = installResponse.installIdentifier;
        if (!TextUtils.isEmpty(str) && ((OVRLibrary) FbInjector.lazyInstance(0, OVRLibraryModule.UL_id._UL__ULSEP_com_oculus_libraryapi_OVRLibrary_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getApp(str) != null) {
            OculusThreadExecutor.getInstance().execute(new Runnable() {
                /* class com.oculus.ocms.library.EntitlementsInstallerEventListener.AnonymousClass3 */

                public void run() {
                    ((OVRLibrary) FbInjector.lazyInstance(0, OVRLibraryModule.UL_id._UL__ULSEP_com_oculus_libraryapi_OVRLibrary_ULSEP_BINDING_ID, EntitlementsInstallerEventListener.this._UL_mInjectionContext)).save(new App.Editor(str).withStatus(EntitlementsInstallerEventListener.this.getDefaultStatusForPackage(str)).withIsUnseen(installResponse.isSuccess));
                }
            });
        }
    }

    @Subscribe
    public void onUninstallRequestedEvent(UninstallRequestedEvent uninstallRequestedEvent) {
        BLog.d(TAG, "onUninstallRequestedEvent(%s)", uninstallRequestedEvent);
        String str = uninstallRequestedEvent.installerIdentifier;
        if (!TextUtils.isEmpty(str) && ((OVRLibrary) FbInjector.lazyInstance(0, OVRLibraryModule.UL_id._UL__ULSEP_com_oculus_libraryapi_OVRLibrary_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getApp(str) != null) {
            updateEntitlementState(str, AppStatus.UNINSTALLING);
        }
    }

    @Subscribe
    public void onUninstallResponse(UninstallResponse uninstallResponse) {
        BLog.d(TAG, "onUninstallResponse(%s)", uninstallResponse);
        String str = uninstallResponse.installerIdentifier;
        if (!TextUtils.isEmpty(str) && ((OVRLibrary) FbInjector.lazyInstance(0, OVRLibraryModule.UL_id._UL__ULSEP_com_oculus_libraryapi_OVRLibrary_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getApp(str) != null) {
            updateEntitlementState(str, getDefaultStatusForPackage(str));
        }
    }

    private boolean isAppInstalled(String str) {
        try {
            ((Context) FbInjector.lazyInstance(2, BundledAndroidModule.UL_id._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getPackageManager().getApplicationInfo(str, 0);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private AppStatus getDefaultStatusForPackage(String str) {
        App app = ((OVRLibrary) FbInjector.lazyInstance(0, OVRLibraryModule.UL_id._UL__ULSEP_com_oculus_libraryapi_OVRLibrary_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getApp(str);
        if (app == null) {
            return AppStatus.UNKNOWN;
        }
        return ((AppConverter) FbInjector.lazyInstance(1, AppConverterUtilsModule.UL_id._UL__ULSEP_com_oculus_library_utils_app_AppConverter_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getDefaultStatus(app);
    }

    private void updateEntitlementState(final String str, final AppStatus appStatus) {
        OculusThreadExecutor.getInstance().execute(new Runnable() {
            /* class com.oculus.ocms.library.EntitlementsInstallerEventListener.AnonymousClass4 */

            public void run() {
                BLog.i(EntitlementsInstallerEventListener.TAG, "Updating status of %s to %s", str, appStatus.name());
                int save = ((OVRLibrary) FbInjector.lazyInstance(0, OVRLibraryModule.UL_id._UL__ULSEP_com_oculus_libraryapi_OVRLibrary_ULSEP_BINDING_ID, EntitlementsInstallerEventListener.this._UL_mInjectionContext)).save(new App.Editor(str).withStatus(appStatus));
                if (save != 1) {
                    BLog.e(EntitlementsInstallerEventListener.TAG, "Unable to save entitlement status for %s to %s. result=%d", str, appStatus.name(), Integer.valueOf(save));
                }
            }
        });
    }

    private void updateEntitlementDownloadProgress(final String str, final long j) {
        OculusThreadExecutor.getInstance().execute(new Runnable() {
            /* class com.oculus.ocms.library.EntitlementsInstallerEventListener.AnonymousClass5 */

            public void run() {
                int save = ((OVRLibrary) FbInjector.lazyInstance(0, OVRLibraryModule.UL_id._UL__ULSEP_com_oculus_libraryapi_OVRLibrary_ULSEP_BINDING_ID, EntitlementsInstallerEventListener.this._UL_mInjectionContext)).save(new App.Editor(str).withDownloadedSizeBytes(j));
                if (save != 1) {
                    BLog.e(EntitlementsInstallerEventListener.TAG, "Error saving entitlement download size. pkg=%s downloaded=%d result=%d", str, Long.valueOf(j), Integer.valueOf(save));
                }
            }
        });
    }
}
