package com.oculus.ocms.testing;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.text.TextUtils;
import bolts.Continuation;
import bolts.Task;
import com.facebook.debug.log.BLog;
import com.facebook.inject.FbInjector;
import com.facebook.inject.InjectionContext;
import com.facebook.inject.InjectorLike;
import com.facebook.mobileconfig.factory.MobileConfig;
import com.facebook.mobileconfig.factory.module.MobileConfigFactoryModule;
import com.facebook.mobileconfig.impl.MobileConfigManagerSingletonHolder;
import com.facebook.mobileconfig.impl.MobileConfigValueUtil;
import com.facebook.mobileconfig.impl.module.MobileConfigFactoryImplModule;
import com.facebook.secure.content.PublicContentProvider;
import com.facebook.ultralight.Inject;
import com.facebook.ultralight.UL;
import com.google.common.base.MoreObjects;
import com.oculus.appmanager.info.ApkUpdateInfo;
import com.oculus.appmanager.info.ApkUpdateInfoContract;
import com.oculus.appmanager.info.model.InstallerResult;
import com.oculus.appmanager.info.model.RequestOrigin;
import com.oculus.appmanager.installer.events.InstallerEventBus;
import com.oculus.appmanager.model.UpdateConfig;
import com.oculus.autoupdates.AutoUpdatesManager;
import com.oculus.autoupdates.AutoUpdatesModule;
import com.oculus.common.init.AppInitLock;
import com.oculus.common.init.AppInitModule;
import com.oculus.library.database.contract.LibraryDBContract;
import com.oculus.library.model.App;
import com.oculus.libraryapi.OVRLibrary;
import com.oculus.libraryapi.OVRLibraryModule;
import com.oculus.ocms.locationservices.LocationRequestHelper;
import com.oculus.ocms.testing.MC;
import com.oculus.testprovider.AsyncTestRunner;
import com.oculus.testprovider.ResultBundler;
import com.oculus.testprovider.TestResult;
import com.oculus.testprovider.TestRunnable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import javax.annotation.Nullable;
import javax.inject.Provider;

public class TestProvider extends PublicContentProvider {
    private static final String APK_URI_KEY = "apk_uri";
    private static final String ASSET_URIS_FILENAMES_KEY = "asset_uris_filenames";
    private static final String CANCEL_PACKAGE = "cancel_package";
    private static final String CUSTOM_INSTALL_PACKAGE = "custom_install_package";
    private static final String DUMP_LIBRARY_TO_LOGCAT = "dump_library_to_logcat";
    private static final String INSTALL_ASSET_SYNC = "install_asset_sync";
    private static final String INSTALL_ID_KEY = "install_id";
    private static final String INSTALL_PACKAGE_SYNC = "install_package_sync";
    private static final String INVOKE_LIBRARY_REFRESH = "invoke_ovrlibrary_refresh";
    private static final String OBB_URI_KEY = "obb_uri";
    private static final String QUERY_LOCATION_SERVICE = "query_location_service";
    private static final String REQUEST_HEADERS_KEY = "request_headers";
    private static final String TAG = "TestProvider";
    private static final String TEST_AUTO_UPDATE = "test_auto_update";
    private static final String UNINSTALL_PACKAGE_SYNC = "uninstall_package_sync";
    private InjectionContext _UL_mInjectionContext;
    @Inject
    private Provider<MobileConfig> mMobileConfigFactoryProvider;
    @Inject
    private Provider<MobileConfigManagerSingletonHolder> mMobileConfigManagerHolderProvider;
    private MobileConfigValueUtil mMobileConfigValueUtil;

    private static final void _UL_injectMe(Context context, TestProvider testProvider) {
        if (UL.USE_STATIC_DI) {
            _UL_staticInjectMe(FbInjector.get(context), testProvider);
        } else {
            FbInjector.injectMe(TestProvider.class, testProvider, context);
        }
    }

    public static final void _UL_staticInjectMe(InjectorLike injectorLike, TestProvider testProvider) {
        testProvider._UL_mInjectionContext = new InjectionContext(3, injectorLike);
        testProvider.mMobileConfigFactoryProvider = MobileConfigFactoryModule._UL__ULSEP_javax_inject_Provider_ULLT_com_facebook_mobileconfig_factory_MobileConfig_ULGT__ULSEP_ACCESS_METHOD(injectorLike);
        testProvider.mMobileConfigManagerHolderProvider = MobileConfigFactoryImplModule._UL__ULSEP_javax_inject_Provider_ULLT_com_facebook_mobileconfig_impl_MobileConfigManagerSingletonHolder_ULGT__ULSEP_ACCESS_METHOD(injectorLike);
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.secure.content.AbstractContentProviderNoDI
    public void onInitialize() {
        _UL_injectMe(getContext(), this);
        this.mMobileConfigValueUtil = new MobileConfigValueUtil(this.mMobileConfigManagerHolderProvider.get(), this.mMobileConfigFactoryProvider.get(), this.mMobileConfigFactoryProvider.get());
        ((AppInitLock) FbInjector.lazyInstance(1, AppInitModule.UL_id._UL__ULSEP_com_oculus_common_init_AppInitLock_ULSEP_BINDING_ID, this._UL_mInjectionContext)).waitForInitialization();
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.secure.content.PublicContentProvider, com.facebook.secure.content.AbstractContentProviderNoDI
    public boolean onCheckPermissions() {
        return Binder.getCallingUid() == 0;
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.secure.content.AbstractContentProviderNoDI
    public int doDelete(Uri uri, String str, String[] strArr) {
        throw new UnsupportedOperationException("Not supported");
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.secure.content.AbstractContentProviderNoDI
    public String doGetType(Uri uri) {
        throw new UnsupportedOperationException("Not supported");
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.secure.content.AbstractContentProviderNoDI
    public Uri doInsert(Uri uri, ContentValues contentValues) {
        throw new UnsupportedOperationException("Not supported");
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.secure.content.AbstractContentProviderNoDI
    public Cursor doQuery(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        throw new UnsupportedOperationException("Not supported");
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.secure.content.AbstractContentProviderNoDI
    public int doUpdate(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        throw new UnsupportedOperationException("Not supported");
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.secure.content.AbstractContentProviderNoDI
    @Nullable
    public Bundle doCall(String str, String str2, Bundle bundle) {
        Binder.clearCallingIdentity();
        BLog.i(TAG, "doCall(%s, %s, %d)", str, str2, Integer.valueOf(bundle.size()));
        if (TextUtils.equals(TEST_AUTO_UPDATE, str)) {
            return ResultBundler.makeStringResult(str, testAutoUpdate(str));
        }
        if (TextUtils.equals(INVOKE_LIBRARY_REFRESH, str)) {
            return ResultBundler.makeStringResult(str, invokeLibraryRefresh(str));
        }
        if (TextUtils.equals(INSTALL_ASSET_SYNC, str)) {
            return ResultBundler.makeStringResult(str, invokeAssetInstallSync(str, str2, bundle));
        }
        if (TextUtils.equals(INSTALL_PACKAGE_SYNC, str)) {
            return ResultBundler.makeStringResult(str, invokeAppInstallSync(str, str2));
        }
        if (TextUtils.equals(UNINSTALL_PACKAGE_SYNC, str)) {
            return ResultBundler.makeStringResult(str, invokeAppUninstallSync(str, str2));
        }
        if (TextUtils.equals(CANCEL_PACKAGE, str)) {
            return ResultBundler.makeStringResult(str, invokeCancelPackageInstall(str, str2));
        }
        if (TextUtils.equals(CUSTOM_INSTALL_PACKAGE, str)) {
            return ResultBundler.makeStringResult(str, invokeCustomInstallPackage(str, bundle));
        }
        if (TextUtils.equals(QUERY_LOCATION_SERVICE, str)) {
            return ResultBundler.makeStringResult(str, invokeQueryLocationService(str));
        }
        if (TextUtils.equals(DUMP_LIBRARY_TO_LOGCAT, str)) {
            return ResultBundler.makeStringResult(str, dumpLibraryToLogcat(str, str2));
        }
        return ResultBundler.makeErrorResult(str, new IllegalArgumentException("unknown test method"));
    }

    /* access modifiers changed from: private */
    public static String createErrorString(String str, @Nullable String str2) {
        Locale locale = Locale.US;
        Object[] objArr = new Object[2];
        objArr[0] = str;
        if (str2 == null) {
            str2 = "";
        }
        objArr[1] = str2;
        return String.format(locale, "%s. Extra info: %s", objArr);
    }

    /* access modifiers changed from: private */
    public static String createSuccessString(@Nullable String str) {
        Locale locale = Locale.US;
        Object[] objArr = new Object[1];
        if (str == null) {
            str = "";
        }
        objArr[0] = str;
        return String.format(locale, "success. Extra info: %s", objArr);
    }

    private TestResult<String> invokeCustomInstallPackage(final String str, final Bundle bundle) {
        return new AsyncTestRunner(new TestRunnable<String>() {
            /* class com.oculus.ocms.testing.TestProvider.AnonymousClass1 */

            @Override // com.oculus.testprovider.TestRunnable
            public void run(TestResult<String> testResult) {
                String string = bundle.getString("install_id");
                if (string == null) {
                    testResult.setError(new RuntimeException(TestProvider.createErrorString(str, "Required install id is missing")));
                    return;
                }
                String string2 = bundle.getString(TestProvider.APK_URI_KEY);
                if (string2 == null) {
                    testResult.setError(new RuntimeException(TestProvider.createErrorString(str, "Required apk uri is missing")));
                    return;
                }
                String string3 = bundle.getString(TestProvider.OBB_URI_KEY);
                String string4 = bundle.getString(TestProvider.ASSET_URIS_FILENAMES_KEY);
                String string5 = bundle.getString(TestProvider.REQUEST_HEADERS_KEY);
                HashMap hashMap = new HashMap();
                if (!TextUtils.isEmpty(string5)) {
                    for (String str : string5.split(";")) {
                        String[] split = str.split("=", 2);
                        if (split.length != 2) {
                            testResult.setError(new RuntimeException(TestProvider.createErrorString(str, "Invalid request header format")));
                            return;
                        } else {
                            hashMap.put(split[0], split[1]);
                        }
                    }
                }
                String str2 = "=";
                UpdateConfig updateConfig = new UpdateConfig(string, LibraryDBContract.VERSION_NOT_INSTALLED, ApkUpdateInfoContract.UpdateType.DIRECT_APK, string2, -1, "", "", "", "", "", "", "", new ApkUpdateInfo.ApkUpdateExtrasBuilder().getData(), hashMap);
                UpdateConfig updateConfig2 = !TextUtils.isEmpty(string3) ? new UpdateConfig(string, LibraryDBContract.VERSION_NOT_INSTALLED, ApkUpdateInfoContract.UpdateType.DIRECT_OBB, string3, -1, "", "", "", "", "", "", "", new ApkUpdateInfo.ApkUpdateExtrasBuilder().getData(), hashMap) : null;
                ArrayList arrayList = new ArrayList();
                if (!TextUtils.isEmpty(string4)) {
                    String[] split2 = string4.split(";");
                    int length = split2.length;
                    int i = 0;
                    while (i < length) {
                        String[] split3 = split2[i].split(str2, 2);
                        if (split3.length != 2) {
                            testResult.setError(new RuntimeException(TestProvider.createErrorString(str, "Invalid asset uri and filename format")));
                            return;
                        }
                        try {
                            String decode = URLDecoder.decode(split3[0], Charset.defaultCharset().name());
                            String str3 = split3[1];
                            ApkUpdateInfo.ApkUpdateExtrasBuilder apkUpdateExtrasBuilder = new ApkUpdateInfo.ApkUpdateExtrasBuilder();
                            apkUpdateExtrasBuilder.putAssetRequiredFilename(str3);
                            arrayList.add(new UpdateConfig(string, LibraryDBContract.VERSION_NOT_INSTALLED, ApkUpdateInfoContract.UpdateType.DIRECT_ASSET, decode, -1, "", "", "", "", "", "", "", apkUpdateExtrasBuilder.getData(), hashMap));
                            i++;
                            arrayList = arrayList;
                            length = length;
                            split2 = split2;
                            str2 = str2;
                        } catch (UnsupportedEncodingException unused) {
                            testResult.setError(new RuntimeException(TestProvider.createErrorString(str, "Unable to url decode asset uri")));
                            return;
                        }
                    }
                }
                TestProvider.handleInstallerResult(str, ((OVRLibrary) FbInjector.lazyInstance(2, OVRLibraryModule.UL_id._UL__ULSEP_com_oculus_libraryapi_OVRLibrary_ULSEP_BINDING_ID, TestProvider.this._UL_mInjectionContext)).customDownloadAndInstall(updateConfig, updateConfig2, arrayList, RequestOrigin.TEST_PROVIDER), testResult);
            }
        }).run();
    }

    private TestResult<String> invokeAssetInstallSync(final String str, final String str2, final Bundle bundle) {
        return new AsyncTestRunner(new TestRunnable<String>() {
            /* class com.oculus.ocms.testing.TestProvider.AnonymousClass2 */

            @Override // com.oculus.testprovider.TestRunnable
            public void run(TestResult<String> testResult) {
                String string = bundle.getString("asset_name");
                if (TextUtils.isEmpty(string)) {
                    testResult.setError(new RuntimeException(TestProvider.createErrorString(str, "Invalid asset identifier")));
                    return;
                }
                TestProvider.handleInstallerResult(str, ((OVRLibrary) FbInjector.lazyInstance(2, OVRLibraryModule.UL_id._UL__ULSEP_com_oculus_libraryapi_OVRLibrary_ULSEP_BINDING_ID, TestProvider.this._UL_mInjectionContext)).assetDownloadAndInstallAwait(str2, string, RequestOrigin.TEST_PROVIDER), testResult);
            }
        }).run();
    }

    private TestResult<String> invokeAppInstallSync(final String str, final String str2) {
        return new AsyncTestRunner(new TestRunnable<String>() {
            /* class com.oculus.ocms.testing.TestProvider.AnonymousClass3 */

            @Override // com.oculus.testprovider.TestRunnable
            public void run(TestResult<String> testResult) {
                TestProvider.handleInstallerResult(str, ((OVRLibrary) FbInjector.lazyInstance(2, OVRLibraryModule.UL_id._UL__ULSEP_com_oculus_libraryapi_OVRLibrary_ULSEP_BINDING_ID, TestProvider.this._UL_mInjectionContext)).downloadAndInstallAwait(str2, RequestOrigin.TEST_PROVIDER), testResult);
            }
        }).run();
    }

    private TestResult<String> invokeAppUninstallSync(final String str, final String str2) {
        return new AsyncTestRunner(new TestRunnable<String>() {
            /* class com.oculus.ocms.testing.TestProvider.AnonymousClass4 */

            @Override // com.oculus.testprovider.TestRunnable
            public void run(TestResult<String> testResult) {
                TestProvider.handleInstallerResult(str, ((OVRLibrary) FbInjector.lazyInstance(2, OVRLibraryModule.UL_id._UL__ULSEP_com_oculus_libraryapi_OVRLibrary_ULSEP_BINDING_ID, TestProvider.this._UL_mInjectionContext)).uninstallAwait(str2, RequestOrigin.TEST_PROVIDER), testResult);
            }
        }).run();
    }

    private TestResult<String> invokeCancelPackageInstall(final String str, final String str2) {
        return new AsyncTestRunner(new TestRunnable<String>() {
            /* class com.oculus.ocms.testing.TestProvider.AnonymousClass5 */

            @Override // com.oculus.testprovider.TestRunnable
            public void run(TestResult<String> testResult) {
                TestProvider.handleInstallerResult(str, ((OVRLibrary) FbInjector.lazyInstance(2, OVRLibraryModule.UL_id._UL__ULSEP_com_oculus_libraryapi_OVRLibrary_ULSEP_BINDING_ID, TestProvider.this._UL_mInjectionContext)).cancelDownloadAwait(str2), testResult);
            }
        }).run();
    }

    /* access modifiers changed from: private */
    public static void handleInstallerResult(String str, InstallerResult installerResult, TestResult<String> testResult) {
        if (installerResult.isSuccess()) {
            testResult.setResult(createSuccessString(null));
        } else {
            testResult.setError(new RuntimeException(createErrorString(str, installerResult.error.name())));
        }
    }

    private TestResult<String> invokeLibraryRefresh(String str) {
        return new AsyncTestRunner(new TestRunnable<String>() {
            /* class com.oculus.ocms.testing.TestProvider.AnonymousClass6 */

            @Override // com.oculus.testprovider.TestRunnable
            public void run(final TestResult<String> testResult) {
                ((OVRLibrary) FbInjector.lazyInstance(2, OVRLibraryModule.UL_id._UL__ULSEP_com_oculus_libraryapi_OVRLibrary_ULSEP_BINDING_ID, TestProvider.this._UL_mInjectionContext)).refetch(new ResultReceiver(null) {
                    /* class com.oculus.ocms.testing.TestProvider.AnonymousClass6.AnonymousClass1 */

                    public void onReceiveResult(int i, Bundle bundle) {
                        if (i == 0) {
                            testResult.setResult(TestProvider.createSuccessString(null));
                            return;
                        }
                        TestResult testResult = testResult;
                        testResult.setError(new RuntimeException(TestProvider.createErrorString("library refresh failed", "Error code: " + i)));
                    }
                });
            }
        }).run();
    }

    private TestResult<String> invokeQueryLocationService(String str) {
        return new AsyncTestRunner(new TestRunnable<String>() {
            /* class com.oculus.ocms.testing.TestProvider.AnonymousClass7 */

            @Override // com.oculus.testprovider.TestRunnable
            public void run(TestResult<String> testResult) {
                Context context = TestProvider.this.getContext();
                if (context == null) {
                    testResult.setError(new RuntimeException("Null context"));
                    return;
                }
                LocationRequestHelper.Result location = new LocationRequestHelper(context).getLocation();
                if (location.success) {
                    testResult.setResult(TestProvider.createSuccessString(location.output));
                } else {
                    testResult.setError(new RuntimeException(TestProvider.createErrorString("Error calling LocationService", location.error)));
                }
            }
        }).run();
    }

    private TestResult<String> testAutoUpdate(final String str) {
        return new AsyncTestRunner(new TestRunnable<String>() {
            /* class com.oculus.ocms.testing.TestProvider.AnonymousClass8 */

            @Override // com.oculus.testprovider.TestRunnable
            public void run(final TestResult<String> testResult) {
                TestProvider.this.mMobileConfigValueUtil.setOverride(MC.oculus_library.auto_update_allow_while_in_use, true);
                TestProvider.this.mMobileConfigValueUtil.setOverride(MC.oculus_library.auto_update_max_retries, 99999L);
                TestProvider.this.mMobileConfigValueUtil.setOverride(MC.oculus_library.auto_update_min_battery_percent, 0L);
                InstallerEventBus.getInstance().register((AutoUpdatesManager) FbInjector.lazyInstance(0, AutoUpdatesModule.UL_id._UL__ULSEP_com_oculus_autoupdates_AutoUpdatesManager_ULSEP_BINDING_ID, TestProvider.this._UL_mInjectionContext));
                ((AutoUpdatesManager) FbInjector.lazyInstance(0, AutoUpdatesModule.UL_id._UL__ULSEP_com_oculus_autoupdates_AutoUpdatesManager_ULSEP_BINDING_ID, TestProvider.this._UL_mInjectionContext)).checkForUpdatesAndInstallIfNecessary().continueWithTask(new Continuation<Integer, Task<Void>>() {
                    /* class com.oculus.ocms.testing.TestProvider.AnonymousClass8.AnonymousClass1 */

                    @Override // bolts.Continuation
                    @Nullable
                    public Task<Void> then(Task<Integer> task) {
                        InstallerEventBus.getInstance().unregister((AutoUpdatesManager) FbInjector.lazyInstance(0, AutoUpdatesModule.UL_id._UL__ULSEP_com_oculus_autoupdates_AutoUpdatesManager_ULSEP_BINDING_ID, TestProvider.this._UL_mInjectionContext));
                        TestProvider.this.mMobileConfigValueUtil.removeAllOverrides();
                        if (task.isFaulted()) {
                            testResult.setError(new RuntimeException(TestProvider.createErrorString(str, task.getError().toString())));
                            return null;
                        }
                        testResult.setResult(TestProvider.createSuccessString(String.format(Locale.US, "%d updateable entitlements.", task.getResult())));
                        return null;
                    }
                });
            }
        }).run();
    }

    private TestResult<String> dumpLibraryToLogcat(String str, @Nullable final String str2) {
        return new AsyncTestRunner(new TestRunnable<String>() {
            /* class com.oculus.ocms.testing.TestProvider.AnonymousClass9 */

            @Override // com.oculus.testprovider.TestRunnable
            public void run(TestResult<String> testResult) {
                App[] allApps = ((OVRLibrary) FbInjector.lazyInstance(2, OVRLibraryModule.UL_id._UL__ULSEP_com_oculus_libraryapi_OVRLibrary_ULSEP_BINDING_ID, TestProvider.this._UL_mInjectionContext)).getAllApps();
                int i = 0;
                for (App app : allApps) {
                    if (TextUtils.isEmpty(str2) || TextUtils.equals(str2, app.packageName)) {
                        MoreObjects.ToStringHelper stringHelper = MoreObjects.toStringHelper((Class<?>) App.class);
                        stringHelper.add("pkg", app.packageName);
                        stringHelper.add("id", app.id);
                        stringHelper.add("status", app.status.name());
                        stringHelper.add("download", app.downloadSizeBytes);
                        stringHelper.add("downloaded", app.downloadedSizeBytes);
                        stringHelper.add("versionCode", app.versionCode);
                        stringHelper.add("versionName", app.versionName);
                        stringHelper.add("latestCode", app.latestVersionCode);
                        stringHelper.add("latestName", app.latestVersionName);
                        i++;
                        BLog.i(TestProvider.TAG, "%s: %s", Integer.valueOf(i), stringHelper.toString());
                    }
                }
                testResult.setResult(TestProvider.createSuccessString(String.format(Locale.US, "%s entitlements printed", Integer.valueOf(i))));
            }
        }).run();
    }
}
