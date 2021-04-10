package com.oculus.ocms.library.provider;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.Parcel;
import android.os.ResultReceiver;
import android.text.TextUtils;
import android.util.Log;
import bolts.Continuation;
import bolts.Task;
import bolts.TaskCompletionSource;
import com.facebook.debug.log.BLog;
import com.facebook.inject.FbInjector;
import com.facebook.inject.InjectionContext;
import com.facebook.inject.InjectorLike;
import com.facebook.mobileconfig.factory.MobileConfig;
import com.facebook.mobileconfig.factory.module.MobileConfigFactoryModule;
import com.facebook.secure.pendingintent.SecurePendingIntent;
import com.facebook.ultralight.UL;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.SettableFuture;
import com.oculus.appmanager.info.model.InstallerResult;
import com.oculus.appmanager.info.model.InstallerResultError;
import com.oculus.appmanager.info.model.RequestOrigin;
import com.oculus.appmanager.installer.common.InstallerFileUtils;
import com.oculus.appmanager.installer.service.util.InstallerServiceUtil;
import com.oculus.appmanager.installer.service.util.UtilModule;
import com.oculus.appmanager.installer.util.InstallConfigBundler;
import com.oculus.appmanager.model.UpdateConfig;
import com.oculus.appmanager.uninstaller.tasks.TasksModule;
import com.oculus.appmanager.uninstaller.tasks.UninstallerAsyncTaskProvider;
import com.oculus.content.OculusFbPermissionsContentProvider;
import com.oculus.installer.AssetManager;
import com.oculus.installer.DownloadAndInstallApi;
import com.oculus.installer.InstallerModule;
import com.oculus.library.database.DatabaseModule;
import com.oculus.library.database.LibraryStorage;
import com.oculus.library.model.App;
import com.oculus.library.model.AppStatus;
import com.oculus.library.model.AssetInfo;
import com.oculus.library.model.InstallerCallbackReceiver;
import com.oculus.library.refresher.LibraryCacheRefresher;
import com.oculus.library.refresher.RefresherModule;
import com.oculus.library.security.SecurityModule;
import com.oculus.library.security.TrustedApplications;
import com.oculus.library.utils.AppSharingUtils;
import com.oculus.library.utils.UtilsModule;
import com.oculus.library.utils.app.AppConverter;
import com.oculus.library.utils.app.AppConverterUtilsModule;
import com.oculus.library.utils.app.AppsCursor;
import com.oculus.library.utils.app.AssetsCursor;
import com.oculus.ocms.library.OCMSLibraryModule;
import com.oculus.ocms.library.provider.ContentType;
import com.oculus.ocms.library.provider.MC;
import com.oculus.ocms.library.provider.contract.OCMSLibraryContract;
import com.oculus.ocms.library.receiver.InstallExistingReceiver;
import com.oculus.time.Clock;
import com.oculus.time.TimeModule;
import com.oculus.util.device.DeviceUtils;
import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.Nullable;

public class LibraryProvider extends OculusFbPermissionsContentProvider {
    private static final String TAG = "LibraryProvider";
    private InjectionContext _UL_mInjectionContext;

    /* access modifiers changed from: protected */
    @Override // com.facebook.secure.content.AbstractContentProviderNoDI
    public String doGetType(Uri uri) {
        return null;
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.secure.content.FbPermissionsContentProvider
    public String getFbPermission() {
        return "com.oculus.ocms.fbpermission.LIBRARY_PROVIDER";
    }

    private static final void _UL_injectMe(Context context, LibraryProvider libraryProvider) {
        if (UL.USE_STATIC_DI) {
            _UL_staticInjectMe((InjectorLike) FbInjector.get(context), libraryProvider);
        } else {
            FbInjector.injectMe(LibraryProvider.class, libraryProvider, context);
        }
    }

    public static final void _UL_staticInjectMe(InjectorLike injectorLike, LibraryProvider libraryProvider) {
        libraryProvider._UL_mInjectionContext = new InjectionContext(12, injectorLike);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.content.OculusFbPermissionsContentProvider
    public void doInitialization() {
        _UL_injectMe(getContext(), this);
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.secure.content.FbPermissionsContentProvider, com.facebook.secure.content.AbstractContentProviderNoDI
    public boolean onCheckPermissions() {
        return Binder.getCallingUid() == 0 || ((TrustedApplications) FbInjector.lazyInstance(9, SecurityModule.UL_id._UL__ULSEP_com_oculus_library_security_TrustedApplications_ULSEP_BINDING_ID, this._UL_mInjectionContext)).isTrusted() || super.onCheckPermissions();
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.secure.content.FbPermissionsContentProvider, com.facebook.secure.content.AbstractContentProviderNoDI
    public boolean onCheckReadOnlyPermissions() {
        return Binder.getCallingUid() == 0 || ((TrustedApplications) FbInjector.lazyInstance(9, SecurityModule.UL_id._UL__ULSEP_com_oculus_library_security_TrustedApplications_ULSEP_BINDING_ID, this._UL_mInjectionContext)).isTrusted() || super.onCheckReadOnlyPermissions();
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.secure.content.AbstractContentProviderNoDI
    public Cursor doQuery(Uri uri, @Nullable String[] strArr, @Nullable String str, @Nullable String[] strArr2, @Nullable String str2) {
        ContentType contentTypeFor = Constants.getContentTypeFor(uri);
        if (contentTypeFor != null) {
            int i = AnonymousClass9.$SwitchMap$com$oculus$ocms$library$provider$ContentType$QueryMethod[contentTypeFor.queryMethod.ordinal()];
            if (i == 1) {
                String packageFromAppsUri = OCMSLibraryContract.getPackageFromAppsUri(uri);
                if (!TextUtils.isEmpty(packageFromAppsUri)) {
                    return queryApp(packageFromAppsUri);
                }
                throw new IllegalArgumentException("Invalid packageName in uri: " + uri);
            } else if (i == 2) {
                return queryAllApps();
            } else {
                if (i == 3) {
                    String packageFromAppsUri2 = OCMSLibraryContract.getPackageFromAppsUri(uri);
                    long idFromAssetsUri = OCMSLibraryContract.getIdFromAssetsUri(uri);
                    if (TextUtils.isEmpty(packageFromAppsUri2)) {
                        throw new IllegalArgumentException("Invalid packageName in uri: " + uri);
                    } else if (idFromAssetsUri != -1) {
                        return queryAssetById(packageFromAppsUri2, idFromAssetsUri);
                    } else {
                        throw new IllegalArgumentException("Invalid asset id");
                    }
                } else if (i == 4) {
                    String packageFromAppsUri3 = OCMSLibraryContract.getPackageFromAppsUri(uri);
                    String filenameFromAssetsUri = OCMSLibraryContract.getFilenameFromAssetsUri(uri);
                    if (TextUtils.isEmpty(packageFromAppsUri3)) {
                        throw new IllegalArgumentException("Invalid packageName in uri: " + uri);
                    } else if (!TextUtils.isEmpty(filenameFromAssetsUri)) {
                        return queryAssetByFilename(packageFromAppsUri3, filenameFromAssetsUri);
                    } else {
                        throw new IllegalArgumentException("Invalid asset filename");
                    }
                } else if (i == 5) {
                    String packageFromAppsUri4 = OCMSLibraryContract.getPackageFromAppsUri(uri);
                    if (!TextUtils.isEmpty(packageFromAppsUri4)) {
                        return queryAllAssets(packageFromAppsUri4);
                    }
                    throw new IllegalArgumentException("Invalid packageName in uri: " + uri);
                } else {
                    throw new IllegalArgumentException("invalid uri");
                }
            }
        } else {
            throw new IllegalArgumentException("Invalid uri: " + uri);
        }
    }

    /* renamed from: com.oculus.ocms.library.provider.LibraryProvider$9  reason: invalid class name */
    static /* synthetic */ class AnonymousClass9 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$ocms$library$provider$ContentType$QueryMethod = new int[ContentType.QueryMethod.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0035 */
        static {
            /*
                com.oculus.ocms.library.provider.ContentType$QueryMethod[] r0 = com.oculus.ocms.library.provider.ContentType.QueryMethod.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.oculus.ocms.library.provider.LibraryProvider.AnonymousClass9.$SwitchMap$com$oculus$ocms$library$provider$ContentType$QueryMethod = r0
                int[] r0 = com.oculus.ocms.library.provider.LibraryProvider.AnonymousClass9.$SwitchMap$com$oculus$ocms$library$provider$ContentType$QueryMethod     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.oculus.ocms.library.provider.ContentType$QueryMethod r1 = com.oculus.ocms.library.provider.ContentType.QueryMethod.APPS_SINGLE     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = com.oculus.ocms.library.provider.LibraryProvider.AnonymousClass9.$SwitchMap$com$oculus$ocms$library$provider$ContentType$QueryMethod     // Catch:{ NoSuchFieldError -> 0x001f }
                com.oculus.ocms.library.provider.ContentType$QueryMethod r1 = com.oculus.ocms.library.provider.ContentType.QueryMethod.APPS_MULTIPLE     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = com.oculus.ocms.library.provider.LibraryProvider.AnonymousClass9.$SwitchMap$com$oculus$ocms$library$provider$ContentType$QueryMethod     // Catch:{ NoSuchFieldError -> 0x002a }
                com.oculus.ocms.library.provider.ContentType$QueryMethod r1 = com.oculus.ocms.library.provider.ContentType.QueryMethod.ASSETS_SINGLE_BY_ID     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = com.oculus.ocms.library.provider.LibraryProvider.AnonymousClass9.$SwitchMap$com$oculus$ocms$library$provider$ContentType$QueryMethod     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.oculus.ocms.library.provider.ContentType$QueryMethod r1 = com.oculus.ocms.library.provider.ContentType.QueryMethod.ASSETS_SINGLE_BY_FILENAME     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r0 = com.oculus.ocms.library.provider.LibraryProvider.AnonymousClass9.$SwitchMap$com$oculus$ocms$library$provider$ContentType$QueryMethod     // Catch:{ NoSuchFieldError -> 0x0040 }
                com.oculus.ocms.library.provider.ContentType$QueryMethod r1 = com.oculus.ocms.library.provider.ContentType.QueryMethod.ASSETS_MULTIPLE     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.ocms.library.provider.LibraryProvider.AnonymousClass9.<clinit>():void");
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.secure.content.AbstractContentProviderNoDI
    public Uri doInsert(Uri uri, @Nullable ContentValues contentValues) {
        throw new UnsupportedOperationException("insert not supported");
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.secure.content.AbstractContentProviderNoDI
    public int doDelete(Uri uri, @Nullable String str, @Nullable String[] strArr) {
        throw new UnsupportedOperationException("delete not supported");
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.secure.content.AbstractContentProviderNoDI
    public int doUpdate(Uri uri, ContentValues contentValues, @Nullable String str, @Nullable String[] strArr) {
        String packageFromAppsUri = OCMSLibraryContract.getPackageFromAppsUri(uri);
        if (TextUtils.isEmpty(packageFromAppsUri)) {
            throw new IllegalArgumentException("invalid uri " + uri.toString());
        } else if (contentValues != null) {
            return update(packageFromAppsUri, contentValues);
        } else {
            throw new IllegalArgumentException("no values provided");
        }
    }

    @Override // com.facebook.secure.content.AbstractContentProviderNoDI
    @Nullable
    public Bundle doCall(@Nullable String str, @Nullable String str2, @Nullable Bundle bundle) {
        if (!TextUtils.isEmpty(str)) {
            char c = 65535;
            switch (str.hashCode()) {
                case -1964612452:
                    if (str.equals(OCMSLibraryContract.METHOD_INSTALL_CUSTOM_PACKAGE)) {
                        c = 3;
                        break;
                    }
                    break;
                case -1779315490:
                    if (str.equals(OCMSLibraryContract.METHOD_CANCEL_DOWNLOAD_ASSET)) {
                        c = '\b';
                        break;
                    }
                    break;
                case -1345481908:
                    if (str.equals(OCMSLibraryContract.METHOD_INSTALL_ASSET)) {
                        c = 4;
                        break;
                    }
                    break;
                case -1225981463:
                    if (str.equals(OCMSLibraryContract.METHOD_UNINSTALL)) {
                        c = 5;
                        break;
                    }
                    break;
                case -832453293:
                    if (str.equals(OCMSLibraryContract.METHOD_UNINSTALL_ASSET)) {
                        c = 6;
                        break;
                    }
                    break;
                case -627486028:
                    if (str.equals(OCMSLibraryContract.METHOD_CANCEL_DOWNLOAD)) {
                        c = 7;
                        break;
                    }
                    break;
                case -325241438:
                    if (str.equals(OCMSLibraryContract.METHOD_INSTALL_PACKAGE)) {
                        c = 1;
                        break;
                    }
                    break;
                case 51250334:
                    if (str.equals("deployment_gk")) {
                        c = 0;
                        break;
                    }
                    break;
                case 540049915:
                    if (str.equals(OCMSLibraryContract.METHOD_GET_DEVICE_ID)) {
                        c = '\n';
                        break;
                    }
                    break;
                case 909122486:
                    if (str.equals(OCMSLibraryContract.METHOD_INSTALL_EXISTING_PACKAGE)) {
                        c = 2;
                        break;
                    }
                    break;
                case 1085071463:
                    if (str.equals(OCMSLibraryContract.METHOD_REFETCH)) {
                        c = '\t';
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    return queryDeploymentGk();
                case 1:
                case 2:
                    return install(str2, bundle);
                case 3:
                    return installCustom(bundle);
                case 4:
                    return installAsset(str2, bundle);
                case 5:
                    uninstall(str2, bundle);
                    return null;
                case 6:
                    return uninstallAsset(str2, bundle);
                case 7:
                    cancelAllDownloads(str2, bundle);
                    return null;
                case '\b':
                    cancelAssetDownload(str2, bundle);
                    return null;
                case '\t':
                    refetch(bundle != null ? (ResultReceiver) bundle.getParcelable("result_receiver") : null);
                    return null;
                case '\n':
                    return getDeviceId();
                default:
                    throw new UnsupportedOperationException("Unknown method requested");
            }
        } else {
            throw new IllegalArgumentException("method required");
        }
    }

    private Bundle getDeviceId() {
        Binder.clearCallingIdentity();
        Bundle bundle = new Bundle();
        bundle.putString(OCMSLibraryContract.EXTRA_DEVICE_ID, DeviceUtils.getDeviceId(getContext()));
        return bundle;
    }

    private Bundle queryDeploymentGk() {
        Bundle bundle = new Bundle();
        bundle.putBoolean("deployment_gk", true);
        return bundle;
    }

    private Bundle install(final String str, @Nullable Bundle bundle) {
        int i;
        Task<InstallerResult> task;
        String str2 = TAG;
        if (bundle == null) {
            i = 0;
        } else {
            i = bundle.size();
        }
        BLog.i(str2, "running install(%s, extras[%d]) for %s", str, Integer.valueOf(i), getCallingPackageViaUid());
        RequestOrigin originOrDefault = getOriginOrDefault(bundle, RequestOrigin.LIBRARY_PROVIDER);
        final ResultReceiver callbackReceiver = getCallbackReceiver("result_receiver", bundle);
        if (((LibraryStorage) FbInjector.lazyInstance(1, DatabaseModule.UL_id._UL__ULSEP_com_oculus_library_database_LibraryStorage_ULSEP_BINDING_ID, this._UL_mInjectionContext)).get(str).status == AppStatus.INSTALL_AVAILABLE) {
            task = installExisting(str);
        } else {
            task = ((DownloadAndInstallApi) FbInjector.lazyInstance(3, InstallerModule.UL_id._UL__ULSEP_com_oculus_installer_DownloadAndInstallApi_ULSEP_BINDING_ID, this._UL_mInjectionContext)).downloadAndInstallEntitlementAsync(str, getCallingPackageViaUid(), originOrDefault);
        }
        task.continueWith(new Continuation<InstallerResult, Void>() {
            /* class com.oculus.ocms.library.provider.LibraryProvider.AnonymousClass1 */

            @Override // bolts.Continuation
            public Void then(Task<InstallerResult> task) throws Exception {
                LibraryProvider.this.handleInstallTaskResult(task, str, callbackReceiver);
                return null;
            }
        });
        Bundle bundle2 = new Bundle();
        bundle2.putBoolean(OCMSLibraryContract.EXTRA_RESPONSE_CALLBACK_SUPPORTED, true);
        return bundle2;
    }

    private Bundle installCustom(@Nullable Bundle bundle) {
        if (bundle != null) {
            BLog.i(TAG, "running installCustom(extras[%d]) for %s", Integer.valueOf(bundle.size()), getCallingPackageViaUid());
            RequestOrigin originOrDefault = getOriginOrDefault(bundle, RequestOrigin.LIBRARY_PROVIDER);
            final ResultReceiver callbackReceiver = getCallbackReceiver("result_receiver", bundle);
            UpdateConfig config = InstallConfigBundler.toConfig(bundle.getBundle(OCMSLibraryContract.EXTRA_UPDATE_CONFIG_APK));
            UpdateConfig config2 = InstallConfigBundler.toConfig(bundle.getBundle(OCMSLibraryContract.EXTRA_UPDATE_CONFIG_OBB));
            List<UpdateConfig> configList = InstallConfigBundler.toConfigList(bundle.getBundle(OCMSLibraryContract.EXTRA_UPDATE_CONFIG_ASSETS));
            if (config != null) {
                final String str = config.identifier;
                if (!TextUtils.isEmpty(str)) {
                    ((DownloadAndInstallApi) FbInjector.lazyInstance(3, InstallerModule.UL_id._UL__ULSEP_com_oculus_installer_DownloadAndInstallApi_ULSEP_BINDING_ID, this._UL_mInjectionContext)).downloadAndInstallNonEntitlementAsync(str, config, config2, configList, getCallingPackageViaUid(), originOrDefault).continueWith(new Continuation<InstallerResult, Void>() {
                        /* class com.oculus.ocms.library.provider.LibraryProvider.AnonymousClass2 */

                        @Override // bolts.Continuation
                        public Void then(Task<InstallerResult> task) throws Exception {
                            LibraryProvider.this.handleInstallTaskResult(task, str, callbackReceiver);
                            return null;
                        }
                    });
                    Bundle bundle2 = new Bundle();
                    bundle2.putBoolean(OCMSLibraryContract.EXTRA_RESPONSE_CALLBACK_SUPPORTED, true);
                    return bundle2;
                }
                throw new IllegalArgumentException("required install identifier missing");
            }
            throw new IllegalArgumentException("update config is required");
        }
        throw new IllegalArgumentException("extras required");
    }

    @TargetApi(29)
    private Task<InstallerResult> installExisting(final String str) {
        BLog.i(TAG, "running installExisting(%s) for %s", str, getCallingPackageViaUid());
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("packageName required");
        } else if (((AppSharingUtils) FbInjector.lazyInstance(11, UtilsModule.UL_id._UL__ULSEP_com_oculus_library_utils_AppSharingUtils_ULSEP_BINDING_ID, this._UL_mInjectionContext)).isInstallSharingEnabled()) {
            final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
            Task.call(new Callable<Void>() {
                /* class com.oculus.ocms.library.provider.LibraryProvider.AnonymousClass3 */

                @Override // java.util.concurrent.Callable
                public Void call() throws Exception {
                    App app = ((LibraryStorage) FbInjector.lazyInstance(1, DatabaseModule.UL_id._UL__ULSEP_com_oculus_library_database_LibraryStorage_ULSEP_BINDING_ID, LibraryProvider.this._UL_mInjectionContext)).get(str);
                    if (app == null) {
                        BLog.w(LibraryProvider.TAG, "Cannot find entitlement for %s", str);
                        TaskCompletionSource taskCompletionSource = taskCompletionSource;
                        String str = str;
                        taskCompletionSource.setResult(InstallerResult.createForError(str, str, ((Clock) FbInjector.lazyInstance(4, TimeModule.UL_id._UL__ULSEP_com_oculus_time_Clock_ULSEP_BINDING_ID, LibraryProvider.this._UL_mInjectionContext)).now(), InstallerResultError.NO_ENTITLEMENT));
                        return null;
                    } else if (app.status != AppStatus.INSTALL_AVAILABLE) {
                        BLog.e(LibraryProvider.TAG, "App status needs to be %s but is %s", AppStatus.INSTALL_AVAILABLE, app.status);
                        TaskCompletionSource taskCompletionSource2 = taskCompletionSource;
                        String str2 = str;
                        taskCompletionSource2.setResult(InstallerResult.createForError(str2, str2, ((Clock) FbInjector.lazyInstance(4, TimeModule.UL_id._UL__ULSEP_com_oculus_time_Clock_ULSEP_BINDING_ID, LibraryProvider.this._UL_mInjectionContext)).now(), InstallerResultError.INVALID_INSTALL_REQUEST));
                        return null;
                    } else {
                        Context context = LibraryProvider.this.getContext();
                        if (context == null) {
                            BLog.e(LibraryProvider.TAG, "Cannot get context");
                            TaskCompletionSource taskCompletionSource3 = taskCompletionSource;
                            String str3 = str;
                            taskCompletionSource3.setResult(InstallerResult.createForError(str3, str3, ((Clock) FbInjector.lazyInstance(4, TimeModule.UL_id._UL__ULSEP_com_oculus_time_Clock_ULSEP_BINDING_ID, LibraryProvider.this._UL_mInjectionContext)).now(), InstallerResultError.UNKNOWN_ERROR));
                            return null;
                        }
                        PackageManager packageManager = context.getPackageManager();
                        if (packageManager == null) {
                            BLog.e(LibraryProvider.TAG, "Cannot get the package manager");
                            TaskCompletionSource taskCompletionSource4 = taskCompletionSource;
                            String str4 = str;
                            taskCompletionSource4.setResult(InstallerResult.createForError(str4, str4, ((Clock) FbInjector.lazyInstance(4, TimeModule.UL_id._UL__ULSEP_com_oculus_time_Clock_ULSEP_BINDING_ID, LibraryProvider.this._UL_mInjectionContext)).now(), InstallerResultError.UNKNOWN_ERROR));
                            return null;
                        }
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("android.intent.extra.RESULT_RECEIVER", LibraryProvider.this.prepareResultReceiverForIPC(new ResultReceiver(null) {
                            /* class com.oculus.ocms.library.provider.LibraryProvider.AnonymousClass3.AnonymousClass1 */

                            /* access modifiers changed from: protected */
                            public void onReceiveResult(int i, Bundle bundle) {
                                if (i == 0) {
                                    taskCompletionSource.setResult(InstallerResult.createForSuccess(str, str, ((Clock) FbInjector.lazyInstance(4, TimeModule.UL_id._UL__ULSEP_com_oculus_time_Clock_ULSEP_BINDING_ID, LibraryProvider.this._UL_mInjectionContext)).now()));
                                } else {
                                    taskCompletionSource.setResult(InstallerResult.createForError(str, str, ((Clock) FbInjector.lazyInstance(4, TimeModule.UL_id._UL__ULSEP_com_oculus_time_Clock_ULSEP_BINDING_ID, LibraryProvider.this._UL_mInjectionContext)).now(), InstallerResultError.UNKNOWN_ERROR));
                                }
                            }
                        }));
                        Intent intent = new Intent(context, InstallExistingReceiver.class);
                        intent.putExtras(bundle);
                        packageManager.getPackageInstaller().installExistingPackage(str, 4, SecurePendingIntent.builder().allowMutable().fromIntentWithExtras(intent, context.getClassLoader()).buildForBroadcast(context, 0, 134217728).getIntentSender());
                        return null;
                    }
                }
            });
            return taskCompletionSource.getTask();
        } else {
            throw new IllegalStateException("App-Sharing needs to be enabled");
        }
    }

    private SettableFuture<InstallerResult> createFutureForError(String str, InstallerResultError installerResultError) {
        SettableFuture<InstallerResult> create = SettableFuture.create();
        create.set(InstallerResult.createForError(str, ((Clock) FbInjector.lazyInstance(4, TimeModule.UL_id._UL__ULSEP_com_oculus_time_Clock_ULSEP_BINDING_ID, this._UL_mInjectionContext)).now(), installerResultError));
        return create;
    }

    private Bundle installAsset(@Nullable final String str, @Nullable Bundle bundle) {
        Task<InstallerResult> task;
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("arg required");
        } else if (bundle != null) {
            BLog.i(TAG, "running installAsset(arg, extras[%d]) for %s", Integer.valueOf(bundle.size()), getCallingPackageViaUid());
            final ResultReceiver callbackReceiver = getCallbackReceiver("result_receiver", bundle);
            App app = ((LibraryStorage) FbInjector.lazyInstance(1, DatabaseModule.UL_id._UL__ULSEP_com_oculus_library_database_LibraryStorage_ULSEP_BINDING_ID, this._UL_mInjectionContext)).get(str);
            if (app != null) {
                RequestOrigin originOrDefault = getOriginOrDefault(bundle, RequestOrigin.LIBRARY_PROVIDER);
                String string = bundle.getString("asset_name");
                long j = bundle.getLong("asset_id", -1);
                if (j != -1 || !TextUtils.isEmpty(string)) {
                    if (j != -1) {
                        task = ((AssetManager) FbInjector.lazyInstance(2, InstallerModule.UL_id._UL__ULSEP_com_oculus_installer_AssetManager_ULSEP_BINDING_ID, this._UL_mInjectionContext)).installAssetById(app, j, getCallingPackageViaUid(), originOrDefault);
                    } else {
                        task = ((AssetManager) FbInjector.lazyInstance(2, InstallerModule.UL_id._UL__ULSEP_com_oculus_installer_AssetManager_ULSEP_BINDING_ID, this._UL_mInjectionContext)).installAssetByName(app, string, getCallingPackageViaUid(), originOrDefault);
                    }
                    task.continueWith(new Continuation<InstallerResult, Void>() {
                        /* class com.oculus.ocms.library.provider.LibraryProvider.AnonymousClass4 */

                        @Override // bolts.Continuation
                        public Void then(Task<InstallerResult> task) throws Exception {
                            LibraryProvider.this.handleInstallTaskResult(task, str, callbackReceiver);
                            return null;
                        }
                    });
                    return new Bundle();
                }
                throw new IllegalArgumentException("extra missing: asset identifier");
            }
            handleInstallTaskResult(Task.forError(new DownloadAndInstallApi.ExceptionWithInstallerResult(InstallerResult.createForError(str, ((Clock) FbInjector.lazyInstance(4, TimeModule.UL_id._UL__ULSEP_com_oculus_time_Clock_ULSEP_BINDING_ID, this._UL_mInjectionContext)).now(), InstallerResultError.NO_ENTITLEMENT), null, null)), str, callbackReceiver);
            throw new IllegalArgumentException("No entitlement found for " + str);
        } else {
            throw new IllegalArgumentException("extras required");
        }
    }

    private void cancelAllDownloads(final String str, @Nullable Bundle bundle) {
        BLog.i(TAG, "running cancelAllDownloads(%s) for %s", str, getCallingPackageViaUid());
        final ResultReceiver callbackReceiver = getCallbackReceiver("result_receiver", bundle);
        Futures.addCallback(((InstallerServiceUtil) FbInjector.lazyInstance(8, UtilModule.UL_id._UL__ULSEP_com_oculus_appmanager_installer_service_util_InstallerServiceUtil_ULSEP_BINDING_ID, this._UL_mInjectionContext)).cancelAllInstallsForIdentifier(str), new FutureCallback<InstallerResult>() {
            /* class com.oculus.ocms.library.provider.LibraryProvider.AnonymousClass5 */

            public void onSuccess(@Nullable InstallerResult installerResult) {
                LibraryProvider.this.handleCancelResult(str, installerResult, callbackReceiver);
            }

            @Override // com.google.common.util.concurrent.FutureCallback
            public void onFailure(Throwable th) {
                LibraryProvider.this.handleCancelResult(str, null, callbackReceiver);
            }
        }, MoreExecutors.directExecutor());
    }

    private void cancelAssetDownload(@Nullable final String str, @Nullable Bundle bundle) {
        SettableFuture<InstallerResult> settableFuture;
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("arg required");
        } else if (bundle != null) {
            BLog.i(TAG, "running cancelAssetDownload(%s, extras[%d]) for %s", str, Integer.valueOf(bundle.size()), getCallingPackageViaUid());
            final ResultReceiver callbackReceiver = getCallbackReceiver("result_receiver", bundle);
            String string = bundle.getString("asset_name");
            long j = bundle.getLong("asset_id", -1);
            if (j != -1 || !TextUtils.isEmpty(string)) {
                if (j != -1) {
                    settableFuture = ((AssetManager) FbInjector.lazyInstance(2, InstallerModule.UL_id._UL__ULSEP_com_oculus_installer_AssetManager_ULSEP_BINDING_ID, this._UL_mInjectionContext)).cancelAssetDownload(str, j);
                } else {
                    settableFuture = ((AssetManager) FbInjector.lazyInstance(2, InstallerModule.UL_id._UL__ULSEP_com_oculus_installer_AssetManager_ULSEP_BINDING_ID, this._UL_mInjectionContext)).cancelAssetDownload(str, string);
                }
                Futures.addCallback(settableFuture, new FutureCallback<InstallerResult>() {
                    /* class com.oculus.ocms.library.provider.LibraryProvider.AnonymousClass6 */

                    public void onSuccess(InstallerResult installerResult) {
                        LibraryProvider.this.handleCancelResult(str, installerResult, callbackReceiver);
                    }

                    @Override // com.google.common.util.concurrent.FutureCallback
                    public void onFailure(Throwable th) {
                        LibraryProvider.this.handleCancelResult(str, null, callbackReceiver);
                    }
                }, MoreExecutors.directExecutor());
                return;
            }
            throw new IllegalArgumentException("extra missing: asset identifier");
        } else {
            throw new IllegalArgumentException("extras required");
        }
    }

    private void uninstall(final String str, Bundle bundle) {
        int i;
        String str2 = TAG;
        if (bundle == null) {
            i = 0;
        } else {
            i = bundle.size();
        }
        BLog.i(str2, "running uninstall(%s, extras[%d]) for %s", str, Integer.valueOf(i), getCallingPackageViaUid());
        final ResultReceiver callbackReceiver = getCallbackReceiver("result_receiver", bundle);
        RequestOrigin originOrDefault = getOriginOrDefault(bundle, RequestOrigin.LIBRARY_PROVIDER);
        boolean z = ((MobileConfig) FbInjector.lazyInstance(7, MobileConfigFactoryModule.UL_id._UL__ULSEP_com_facebook_mobileconfig_factory_MobileConfig_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getBoolean(MC.oculus_library.queue_uninstall_requests_enabled);
        BLog.d(TAG, "queueUninstallRequestsEnabled: %b", Boolean.valueOf(z));
        if (z) {
            ((DownloadAndInstallApi) FbInjector.lazyInstance(3, InstallerModule.UL_id._UL__ULSEP_com_oculus_installer_DownloadAndInstallApi_ULSEP_BINDING_ID, this._UL_mInjectionContext)).uninstallAsync(str, getCallingPackageViaUid(), originOrDefault).continueWith(new Continuation<InstallerResult, Void>() {
                /* class com.oculus.ocms.library.provider.LibraryProvider.AnonymousClass7 */

                @Override // bolts.Continuation
                public Void then(Task<InstallerResult> task) throws Exception {
                    LibraryProvider.this.handleInstallTaskResult(task, str, callbackReceiver);
                    return null;
                }
            });
        } else {
            ((UninstallerAsyncTaskProvider) FbInjector.lazyInstance(6, TasksModule.UL_id._UL__ULSEP_com_oculus_appmanager_uninstaller_tasks_UninstallerAsyncTaskProvider_ULSEP_BINDING_ID, this._UL_mInjectionContext)).get(str, getCallingPackageViaUid(), callbackReceiver).execute(new Void[0]);
        }
    }

    private Bundle uninstallAsset(@Nullable String str, @Nullable Bundle bundle) {
        int i;
        AssetInfo assetInfo;
        String str2 = TAG;
        if (bundle == null) {
            i = 0;
        } else {
            i = bundle.size();
        }
        BLog.i(str2, "running uninstallAsset(%s, extras[%d]) for %s", str, Integer.valueOf(i), getCallingPackageViaUid());
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("arg is required");
        } else if (bundle != null) {
            Bundle bundle2 = new Bundle();
            App app = ((LibraryStorage) FbInjector.lazyInstance(1, DatabaseModule.UL_id._UL__ULSEP_com_oculus_library_database_LibraryStorage_ULSEP_BINDING_ID, this._UL_mInjectionContext)).get(str);
            if (app == null) {
                BLog.w(TAG, "no entitlement found for %s", str);
                bundle2.putBoolean(OCMSLibraryContract.EXTRA_UNINSTALL_ASSET_RESULT, false);
                return bundle2;
            }
            String string = bundle.getString("asset_name");
            long j = bundle.getLong("asset_id", -1);
            if (j != -1 || !TextUtils.isEmpty(string)) {
                if (j != -1) {
                    assetInfo = ((AssetManager) FbInjector.lazyInstance(2, InstallerModule.UL_id._UL__ULSEP_com_oculus_installer_AssetManager_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getAssetById(app, j);
                } else {
                    assetInfo = ((AssetManager) FbInjector.lazyInstance(2, InstallerModule.UL_id._UL__ULSEP_com_oculus_installer_AssetManager_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getAssetByName(app, string);
                }
                if (assetInfo == null) {
                    String str3 = TAG;
                    Object[] objArr = new Object[1];
                    Object obj = string;
                    if (j != -1) {
                        obj = Long.valueOf(j);
                    }
                    objArr[0] = obj;
                    BLog.w(str3, "no asset found for identifier %s", objArr);
                    bundle2.putBoolean(OCMSLibraryContract.EXTRA_UNINSTALL_ASSET_RESULT, false);
                    return bundle2;
                }
                bundle2.putBoolean(OCMSLibraryContract.EXTRA_UNINSTALL_ASSET_RESULT, new File(InstallerFileUtils.getFinalAssetPath(str, assetInfo.name)).delete());
                return bundle2;
            }
            throw new IllegalArgumentException("extra missing: asset identifier");
        } else {
            throw new IllegalArgumentException("extras are required");
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private ResultReceiver prepareResultReceiverForIPC(ResultReceiver resultReceiver) {
        Parcel obtain = Parcel.obtain();
        resultReceiver.writeToParcel(obtain, 0);
        obtain.setDataPosition(0);
        ResultReceiver resultReceiver2 = (ResultReceiver) ResultReceiver.CREATOR.createFromParcel(obtain);
        obtain.recycle();
        return resultReceiver2;
    }

    private void refetch(@Nullable final ResultReceiver resultReceiver) {
        BLog.i(TAG, "running refetch() for %s", getCallingPackageViaUid());
        Task<ImmutableList<App>> refreshCache = ((LibraryCacheRefresher) FbInjector.lazyInstance(0, RefresherModule.UL_id._UL__ULSEP_com_oculus_library_refresher_LibraryCacheRefresher_ULSEP_BINDING_ID, this._UL_mInjectionContext)).refreshCache();
        if (resultReceiver != null) {
            refreshCache.continueWith(new Continuation<ImmutableList<App>, Void>() {
                /* class com.oculus.ocms.library.provider.LibraryProvider.AnonymousClass8 */

                @Override // bolts.Continuation
                public Void then(Task<ImmutableList<App>> task) throws Exception {
                    if (task.isFaulted()) {
                        resultReceiver.send(1, null);
                    } else {
                        resultReceiver.send(0, null);
                    }
                    return null;
                }
            });
        }
    }

    private int update(String str, ContentValues contentValues) {
        BLog.i(TAG, "running update(%s, values[%d]) for %s", str, Integer.valueOf(contentValues.size()), getCallingPackageViaUid());
        if (((AppConverter) FbInjector.lazyInstance(5, AppConverterUtilsModule.UL_id._UL__ULSEP_com_oculus_library_utils_app_AppConverter_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getAndRemoveContentValuePackingVersion(contentValues) == 2) {
            return ((LibraryStorage) FbInjector.lazyInstance(1, DatabaseModule.UL_id._UL__ULSEP_com_oculus_library_database_LibraryStorage_ULSEP_BINDING_ID, this._UL_mInjectionContext)).save(contentValues) ? 1 : 0;
        }
        throw new IllegalArgumentException("No longer supported. Please use OVRLibrary.save(App.Editor)");
    }

    private Cursor queryAllApps() {
        BLog.i(TAG, "running queryAllApps() for %s", getCallingPackageViaUid());
        ImmutableList<App> filterSharedApps = ((SharedAppsHelper) FbInjector.lazyInstance(10, OCMSLibraryModule.UL_id._UL__ULSEP_com_oculus_ocms_library_provider_SharedAppsHelper_ULSEP_BINDING_ID, this._UL_mInjectionContext)).filterSharedApps(((LibraryStorage) FbInjector.lazyInstance(1, DatabaseModule.UL_id._UL__ULSEP_com_oculus_library_database_LibraryStorage_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getAll());
        AppsCursor appsCursor = new AppsCursor(filterSharedApps.size());
        appsCursor.fill(filterSharedApps);
        return appsCursor;
    }

    private Cursor queryApp(String str) {
        BLog.i(TAG, "running queryApp(%s) for %s", str, getCallingPackageViaUid());
        App filterSharedApp = ((SharedAppsHelper) FbInjector.lazyInstance(10, OCMSLibraryModule.UL_id._UL__ULSEP_com_oculus_ocms_library_provider_SharedAppsHelper_ULSEP_BINDING_ID, this._UL_mInjectionContext)).filterSharedApp(((LibraryStorage) FbInjector.lazyInstance(1, DatabaseModule.UL_id._UL__ULSEP_com_oculus_library_database_LibraryStorage_ULSEP_BINDING_ID, this._UL_mInjectionContext)).get(str));
        if (filterSharedApp == null) {
            String str2 = TAG;
            Log.w(str2, "Cannot find entitlement for " + str);
            return null;
        }
        AppsCursor appsCursor = new AppsCursor(1);
        appsCursor.fill(ImmutableList.of(filterSharedApp));
        return appsCursor;
    }

    private Cursor queryAssetById(String str, long j) {
        BLog.i(TAG, "running queryAssetById(%s, %s) for %s", str, Long.valueOf(j), getCallingPackageViaUid());
        App app = ((LibraryStorage) FbInjector.lazyInstance(1, DatabaseModule.UL_id._UL__ULSEP_com_oculus_library_database_LibraryStorage_ULSEP_BINDING_ID, this._UL_mInjectionContext)).get(str);
        int i = 0;
        if (app == null) {
            BLog.e(TAG, "%s is not an entitlement", str);
            return null;
        }
        AssetInfo assetById = ((AssetManager) FbInjector.lazyInstance(2, InstallerModule.UL_id._UL__ULSEP_com_oculus_installer_AssetManager_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getAssetById(app, j);
        if (assetById != null) {
            i = 1;
        }
        AssetsCursor assetsCursor = new AssetsCursor(i);
        assetsCursor.fill(assetById == null ? Collections.EMPTY_LIST : Collections.singleton(assetById));
        return assetsCursor;
    }

    private Cursor queryAssetByFilename(String str, String str2) {
        BLog.i(TAG, "running queryAssetByFilename(%s, %s) for %s", str, str2, getCallingPackageViaUid());
        App app = ((LibraryStorage) FbInjector.lazyInstance(1, DatabaseModule.UL_id._UL__ULSEP_com_oculus_library_database_LibraryStorage_ULSEP_BINDING_ID, this._UL_mInjectionContext)).get(str);
        int i = 0;
        if (app == null) {
            BLog.e(TAG, "%s is not an entitlement", str);
            return null;
        }
        AssetInfo assetByName = ((AssetManager) FbInjector.lazyInstance(2, InstallerModule.UL_id._UL__ULSEP_com_oculus_installer_AssetManager_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getAssetByName(app, str2);
        if (assetByName != null) {
            i = 1;
        }
        AssetsCursor assetsCursor = new AssetsCursor(i);
        assetsCursor.fill(assetByName == null ? Collections.EMPTY_LIST : Collections.singleton(assetByName));
        return assetsCursor;
    }

    private Cursor queryAllAssets(String str) {
        BLog.i(TAG, "running queryAllAssets(%s) for %s", str, getCallingPackageViaUid());
        App app = ((LibraryStorage) FbInjector.lazyInstance(1, DatabaseModule.UL_id._UL__ULSEP_com_oculus_library_database_LibraryStorage_ULSEP_BINDING_ID, this._UL_mInjectionContext)).get(str);
        if (app == null) {
            BLog.e(TAG, "%s is not an entitlement", str);
            return null;
        }
        List<AssetInfo> assets = ((AssetManager) FbInjector.lazyInstance(2, InstallerModule.UL_id._UL__ULSEP_com_oculus_installer_AssetManager_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getAssets(app);
        AssetsCursor assetsCursor = new AssetsCursor(assets.size());
        assetsCursor.fill(assets);
        return assetsCursor;
    }

    @Nullable
    private String getCallingPackageViaUid() {
        return getContext().getPackageManager().getNameForUid(Binder.getCallingUid());
    }

    private RequestOrigin getOriginOrDefault(@Nullable Bundle bundle, RequestOrigin requestOrigin) {
        if (bundle == null || !bundle.containsKey(OCMSLibraryContract.EXTRA_REQUEST_ORIGIN)) {
            return requestOrigin;
        }
        try {
            return RequestOrigin.valueOf(bundle.getString(OCMSLibraryContract.EXTRA_REQUEST_ORIGIN));
        } catch (IllegalArgumentException e) {
            BLog.e(TAG, "Failed to parse request origin", e);
            return requestOrigin;
        }
    }

    @Nullable
    private static ResultReceiver getCallbackReceiver(String str, @Nullable Bundle bundle) {
        if (bundle != null) {
            return (ResultReceiver) bundle.getParcelable(str);
        }
        return null;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handleInstallTaskResult(Task<InstallerResult> task, String str, @Nullable ResultReceiver resultReceiver) {
        if (resultReceiver != null) {
            Bundle bundle = new Bundle();
            if (!task.isFaulted()) {
                bundle.putParcelable(InstallerCallbackReceiver.EXTRA_RESULT, task.getResult());
                resultReceiver.send(0, bundle);
            } else if (task.getError() instanceof DownloadAndInstallApi.ExceptionWithInstallerResult) {
                bundle.putParcelable(InstallerCallbackReceiver.EXTRA_RESULT, ((DownloadAndInstallApi.ExceptionWithInstallerResult) task.getError()).mInstallerResult);
                resultReceiver.send(0, bundle);
            } else {
                BLog.w(TAG, "Unhandled exception, sending callback of unknown error", task.getError());
                bundle.putParcelable(InstallerCallbackReceiver.EXTRA_RESULT, InstallerResult.createForError(str, ((Clock) FbInjector.lazyInstance(4, TimeModule.UL_id._UL__ULSEP_com_oculus_time_Clock_ULSEP_BINDING_ID, this._UL_mInjectionContext)).now(), InstallerResultError.UNKNOWN_ERROR));
                resultReceiver.send(0, bundle);
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handleCancelResult(String str, @Nullable InstallerResult installerResult, @Nullable ResultReceiver resultReceiver) {
        if (resultReceiver != null) {
            if (installerResult == null) {
                installerResult = InstallerResult.createForError(str, ((Clock) FbInjector.lazyInstance(4, TimeModule.UL_id._UL__ULSEP_com_oculus_time_Clock_ULSEP_BINDING_ID, this._UL_mInjectionContext)).now(), InstallerResultError.UNKNOWN_ERROR);
            }
            Bundle bundle = new Bundle();
            bundle.putParcelable(InstallerCallbackReceiver.EXTRA_RESULT, installerResult);
            resultReceiver.send(0, bundle);
        }
    }
}
