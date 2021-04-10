package com.oculus.horizon.service;

import X.AbstractC06640p5;
import X.AnonymousClass006;
import X.AnonymousClass06g;
import X.AnonymousClass0J2;
import X.AnonymousClass0NO;
import X.AnonymousClass0QC;
import X.AnonymousClass0b9;
import X.AnonymousClass0i9;
import X.AnonymousClass117;
import X.C08780ya;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.text.TextUtils;
import com.facebook.common.stringformat.StringFormatUtil;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.google.gson.annotations.SerializedName;
import com.oculus.aidl.OVRServiceInterface;
import com.oculus.appmanager.info.model.InstallerResult;
import com.oculus.appmanager.info.model.RequestOrigin;
import com.oculus.auth.credentials.Credentials;
import com.oculus.auth.credentials.CredentialsModule;
import com.oculus.horizon.api.graphql.GraphQLParamsHelper;
import com.oculus.horizon.cloudstorage2.CloudStorageManager;
import com.oculus.horizon.platform.PlatformContext;
import com.oculus.horizon.platform.PresenceManager;
import com.oculus.horizon.platformplugin.PlatformPluginManager;
import com.oculus.horizon.service.ExternalPlatformLocal;
import com.oculus.horizon.service.result.AppScopedAccessTokenResultBuilder;
import com.oculus.horizon.service.result.AppScopedAccessTokenResultBuilderProvider;
import com.oculus.horizon.service.result.DeviceScopedAccessTokenResultBuilder;
import com.oculus.horizon.service.result.DeviceScopedAccessTokenResultBuilderProvider;
import com.oculus.horizon.service.result.DurableIAPResultBuilder;
import com.oculus.horizon.service.result.DurableIAPResultBuilderProvider;
import com.oculus.horizon.service.result.EntitlementCheckResultBuilder;
import com.oculus.horizon.service.result.EntitlementCheckResultBuilderProvider;
import com.oculus.horizon.service.result.GetCloudStorage2UserDirectoryPathResultBuilder;
import com.oculus.horizon.service.result.GetCloudStorage2UserDirectoryPathResultBuilderProvider;
import com.oculus.horizon.service.result.IAPCheckoutFlowResultBuilder;
import com.oculus.horizon.service.result.IAPCheckoutFlowResultBuilderProvider;
import com.oculus.horizon.service.result.InstalledVRApplicationsResultBuilder;
import com.oculus.horizon.service.result.InstalledVRApplicationsResultBuilderProvider;
import com.oculus.horizon.service.result.InvitableUsersFlowResultBuilder;
import com.oculus.horizon.service.result.InvitableUsersFlowResultBuilderProvider;
import com.oculus.horizon.service.result.IsAppEntitledResultBuilder;
import com.oculus.horizon.service.result.IsAppEntitledResultBuilderProvider;
import com.oculus.horizon.service.result.LatestAvailableAppInformationResultBuilder;
import com.oculus.horizon.service.result.LatestAvailableAppInformationResultBuilderProvider;
import com.oculus.horizon.service.result.LaunchOtherAppResultBuilder;
import com.oculus.horizon.service.result.LaunchOtherAppResultBuilderProvider;
import com.oculus.horizon.service.result.RegisterProcessTokenResultBuilder;
import com.oculus.horizon.service.result.RegisterProcessTokenResultBuilderProvider;
import com.oculus.horizon.service.result.SetRichPresenceResultBuilder;
import com.oculus.horizon.service.result.SetRichPresenceResultBuilderProvider;
import com.oculus.http.core.annotations.OculusApiEndpoint;
import com.oculus.http.core.endpoint.EndpointModule;
import com.oculus.library.model.App;
import com.oculus.library.model.AssetInfo;
import com.oculus.library.model.InstallerCallback;
import com.oculus.libraryapi.OVRLibrary;
import com.oculus.ocms.library.provider.contract.OCMSLibraryContract;
import com.oculus.platform.util.OVRError;
import com.oculus.platform.util.Util;
import com.oculus.platforminitexception.PlatformInitException;
import com.oculus.processtokentracker.ProcessTokenTracker;
import com.oculus.security.basecomponent.OculusPublicBroadcastReceiver;
import com.oculus.security.basecomponent.OculusPublicService;
import com.oculus.socialplatform.provider.contract.SocialPlatformContent;
import com.oculus.socialplatform.util.SocialPlatformVersionUtil;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.Nullable;
import javax.inject.Provider;

@SuppressLint({"CatchGeneralException"})
public class OVRService extends OculusPublicService implements PlatformPluginManager.Client {
    public static final String ACTION_HMT_DISCONNECT = "com.samsung.intent.action.HMT_DISCONNECTED";
    public static final String APP_ID_KEY = "app_id";
    public static final String ASSET_FILENAME = "asset_filename";
    public static final String ASSET_ID = "asset_id";
    public static final HashSet<String> BLACKLISTED_LOGGING_KEYS = new HashSet<String>() {
        /* class com.oculus.horizon.service.OVRService.AnonymousClass1 */

        {
            add("access_token");
        }
    };
    public static final long MIC_PERMISSION_CHECK_CADENCE = 5000;
    public static final Class<?> TAG = OVRService.class;
    public static final C08780ya sGson = new C08780ya();
    public AnonymousClass0QC _UL_mInjectionContext;
    @Inject
    public AppScopedAccessTokenResultBuilderProvider mAppScopedAccessTokenResultBuilderProvider;
    public final OVRServiceInterface.Stub mBinder = new OVRServiceInterface.Stub() {
        /* class com.oculus.horizon.service.OVRService.AnonymousClass4 */

        @Override // com.oculus.aidl.OVRServiceInterface
        public Bundle getCurrentMapUuid(Bundle bundle) {
            return null;
        }

        @Override // com.oculus.aidl.OVRServiceInterface
        @Nullable
        @Deprecated
        public Map loadPublicProfileForUser(String str) {
            return null;
        }

        @Override // com.oculus.aidl.OVRServiceInterface
        public Bundle requestMap(Bundle bundle) {
            return null;
        }

        @Override // com.oculus.aidl.OVRServiceInterface
        @Nullable
        @Deprecated
        public Map savePublicProfileData(String str, String str2, String str3, String str4) {
            return null;
        }

        @Override // com.oculus.aidl.OVRServiceInterface
        public Bundle shareMap(Bundle bundle) {
            return null;
        }

        @Override // com.oculus.aidl.OVRServiceInterface
        public Bundle assetFileDeleteById(Bundle bundle) throws RemoteException {
            boolean z;
            String str;
            if (!bundle.containsKey("asset_id")) {
                str = "asset_id not supplied";
            } else {
                long j = bundle.getLong("asset_id", -1);
                if (j == -1) {
                    str = "asset_id invalid";
                } else {
                    String callingPackage = OVRService.this.mCallerUtils.getCallingPackage(bundle);
                    long clearCallingIdentity = Binder.clearCallingIdentity();
                    AssetInfo A03 = ((OVRLibrary) AnonymousClass0J2.A03(0, 569, OVRService.this._UL_mInjectionContext)).A03(callingPackage, j);
                    if (A03 == null) {
                        Binder.restoreCallingIdentity(clearCallingIdentity);
                        return Util.makeErrorResult(null, "not entitled", OVRError.STORE_INSTALLATION_ERROR.mCode);
                    }
                    Bundle bundle2 = new Bundle();
                    bundle2.putLong("asset_id", j);
                    Bundle call = ((OVRLibrary) AnonymousClass0J2.A03(0, 569, OVRService.this._UL_mInjectionContext)).mContext.getContentResolver().call(OCMSLibraryContract.A00(), OCMSLibraryContract.METHOD_UNINSTALL_ASSET, callingPackage, bundle2);
                    if (call != null) {
                        z = call.getBoolean(OCMSLibraryContract.EXTRA_UNINSTALL_ASSET_RESULT, false);
                    } else {
                        z = false;
                    }
                    Binder.restoreCallingIdentity(clearCallingIdentity);
                    return AssetBundleUtil.getBundleForOperation(callingPackage, A03, z);
                }
            }
            AnonymousClass0NO.A01(OVRService.class, str);
            return Util.makeErrorResult(null, str, OVRError.UNKNOWN_ERROR.mCode);
        }

        @Override // com.oculus.aidl.OVRServiceInterface
        public Bundle assetFileDeleteByName(Bundle bundle) throws RemoteException {
            boolean z;
            String str;
            if (!bundle.containsKey("asset_filename")) {
                str = "asset_filename not supplied";
            } else {
                String valueOf = String.valueOf(bundle.getString("asset_filename"));
                if (TextUtils.isEmpty(valueOf)) {
                    str = "asset_filename invalid";
                } else {
                    String callingPackage = OVRService.this.mCallerUtils.getCallingPackage(bundle);
                    long clearCallingIdentity = Binder.clearCallingIdentity();
                    AssetInfo A04 = ((OVRLibrary) AnonymousClass0J2.A03(0, 569, OVRService.this._UL_mInjectionContext)).A04(callingPackage, valueOf);
                    if (A04 == null) {
                        Binder.restoreCallingIdentity(clearCallingIdentity);
                        return Util.makeErrorResult(null, "not entitled", OVRError.STORE_INSTALLATION_ERROR.mCode);
                    }
                    Bundle bundle2 = new Bundle();
                    bundle2.putString("asset_name", valueOf);
                    Bundle call = ((OVRLibrary) AnonymousClass0J2.A03(0, 569, OVRService.this._UL_mInjectionContext)).mContext.getContentResolver().call(OCMSLibraryContract.A00(), OCMSLibraryContract.METHOD_UNINSTALL_ASSET, callingPackage, bundle2);
                    if (call != null) {
                        z = call.getBoolean(OCMSLibraryContract.EXTRA_UNINSTALL_ASSET_RESULT, false);
                    } else {
                        z = false;
                    }
                    Binder.restoreCallingIdentity(clearCallingIdentity);
                    return AssetBundleUtil.getBundleForOperation(callingPackage, A04, z);
                }
            }
            AnonymousClass0NO.A01(OVRService.class, str);
            return Util.makeErrorResult(null, str, OVRError.UNKNOWN_ERROR.mCode);
        }

        @Override // com.oculus.aidl.OVRServiceInterface
        public Bundle assetFileDownloadById(Bundle bundle) throws RemoteException {
            int i;
            String str;
            if (!bundle.containsKey("asset_id")) {
                i = OVRError.UNKNOWN_ERROR.mCode;
                str = "asset_id not supplied";
            } else {
                long j = bundle.getLong("asset_id", -1);
                if (j == -1) {
                    i = OVRError.UNKNOWN_ERROR.mCode;
                    str = "asset_id invalid";
                } else {
                    String callingPackage = OVRService.this.mCallerUtils.getCallingPackage(bundle);
                    long clearCallingIdentity = Binder.clearCallingIdentity();
                    AssetInfo A03 = ((OVRLibrary) AnonymousClass0J2.A03(0, 569, OVRService.this._UL_mInjectionContext)).A03(callingPackage, j);
                    if (A03 == null) {
                        Binder.restoreCallingIdentity(clearCallingIdentity);
                        i = OVRError.STORE_INSTALLATION_ERROR.mCode;
                        str = "asset not found";
                    } else {
                        RequestOrigin requestOrigin = RequestOrigin.PLATFORM_SDK;
                        Bundle bundle2 = new Bundle();
                        bundle2.putLong("asset_id", j);
                        OVRLibrary.A00((OVRLibrary) AnonymousClass0J2.A03(0, 569, OVRService.this._UL_mInjectionContext), callingPackage, requestOrigin, null, bundle2);
                        Binder.restoreCallingIdentity(clearCallingIdentity);
                        return AssetBundleUtil.getBundleForOperation(callingPackage, A03, true);
                    }
                }
            }
            return Util.makeErrorResult(null, str, i);
        }

        @Override // com.oculus.aidl.OVRServiceInterface
        public Bundle assetFileDownloadByName(Bundle bundle) throws RemoteException {
            String str;
            if (!bundle.containsKey("asset_filename")) {
                str = "asset_filename not supplied";
            } else {
                String valueOf = String.valueOf(bundle.getString("asset_filename"));
                if (TextUtils.isEmpty(valueOf)) {
                    str = "asset_filename invalid";
                } else {
                    String callingPackage = OVRService.this.mCallerUtils.getCallingPackage(bundle);
                    long clearCallingIdentity = Binder.clearCallingIdentity();
                    AssetInfo A04 = ((OVRLibrary) AnonymousClass0J2.A03(0, 569, OVRService.this._UL_mInjectionContext)).A04(callingPackage, valueOf);
                    if (A04 == null) {
                        Binder.restoreCallingIdentity(clearCallingIdentity);
                        return Util.makeErrorResult(null, "asset not found", OVRError.STORE_INSTALLATION_ERROR.mCode);
                    }
                    RequestOrigin requestOrigin = RequestOrigin.PLATFORM_SDK;
                    Bundle bundle2 = new Bundle();
                    bundle2.putString("asset_name", valueOf);
                    OVRLibrary.A00((OVRLibrary) AnonymousClass0J2.A03(0, 569, OVRService.this._UL_mInjectionContext), callingPackage, requestOrigin, null, bundle2);
                    Binder.restoreCallingIdentity(clearCallingIdentity);
                    return AssetBundleUtil.getBundleForOperation(callingPackage, A04, true);
                }
            }
            AnonymousClass0NO.A01(OVRService.class, str);
            return Util.makeErrorResult(null, str, OVRError.UNKNOWN_ERROR.mCode);
        }

        @Override // com.oculus.aidl.OVRServiceInterface
        public Bundle assetFileDownloadCancelById(Bundle bundle) throws RemoteException {
            int i;
            String str;
            if (!bundle.containsKey("asset_id")) {
                i = OVRError.UNKNOWN_ERROR.mCode;
                str = "asset_id not supplied";
            } else {
                long j = bundle.getLong("asset_id");
                String callingPackage = OVRService.this.mCallerUtils.getCallingPackage(bundle);
                long clearCallingIdentity = Binder.clearCallingIdentity();
                AssetInfo A03 = ((OVRLibrary) AnonymousClass0J2.A03(0, 569, OVRService.this._UL_mInjectionContext)).A03(callingPackage, j);
                if (A03 == null) {
                    Binder.restoreCallingIdentity(clearCallingIdentity);
                    i = OVRError.STORE_INSTALLATION_ERROR.mCode;
                    str = "not entitled";
                } else {
                    Bundle bundle2 = new Bundle();
                    bundle2.putLong("asset_id", j);
                    ((OVRLibrary) AnonymousClass0J2.A03(0, 569, OVRService.this._UL_mInjectionContext)).mContext.getContentResolver().call(OCMSLibraryContract.A00(), OCMSLibraryContract.METHOD_CANCEL_DOWNLOAD_ASSET, callingPackage, bundle2);
                    Binder.restoreCallingIdentity(clearCallingIdentity);
                    return AssetBundleUtil.getBundleForOperation(callingPackage, A03, true);
                }
            }
            return Util.makeErrorResult(null, str, i);
        }

        @Override // com.oculus.aidl.OVRServiceInterface
        public Bundle assetFileDownloadCancelByName(Bundle bundle) throws RemoteException {
            String str;
            if (!bundle.containsKey("asset_filename")) {
                str = "asset_filename not supplied";
            } else {
                String valueOf = String.valueOf(bundle.getString("asset_filename"));
                if (TextUtils.isEmpty(valueOf)) {
                    str = "asset_filename invalid";
                } else {
                    String callingPackage = OVRService.this.mCallerUtils.getCallingPackage(bundle);
                    long clearCallingIdentity = Binder.clearCallingIdentity();
                    AssetInfo A04 = ((OVRLibrary) AnonymousClass0J2.A03(0, 569, OVRService.this._UL_mInjectionContext)).A04(callingPackage, valueOf);
                    if (A04 == null) {
                        Binder.restoreCallingIdentity(clearCallingIdentity);
                        return Util.makeErrorResult(null, "not entitled", OVRError.STORE_INSTALLATION_ERROR.mCode);
                    }
                    Bundle bundle2 = new Bundle();
                    bundle2.putString("asset_name", valueOf);
                    ((OVRLibrary) AnonymousClass0J2.A03(0, 569, OVRService.this._UL_mInjectionContext)).mContext.getContentResolver().call(OCMSLibraryContract.A00(), OCMSLibraryContract.METHOD_CANCEL_DOWNLOAD_ASSET, callingPackage, bundle2);
                    Binder.restoreCallingIdentity(clearCallingIdentity);
                    return AssetBundleUtil.getBundleForOperation(callingPackage, A04, true);
                }
            }
            AnonymousClass0NO.A01(OVRService.class, str);
            return Util.makeErrorResult(null, str, OVRError.UNKNOWN_ERROR.mCode);
        }

        @Override // com.oculus.aidl.OVRServiceInterface
        public Bundle assetFileList() throws RemoteException {
            String callingPackage = OVRService.this.mCallerUtils.getCallingPackage(null);
            long clearCallingIdentity = Binder.clearCallingIdentity();
            AssetInfo[] A09 = ((OVRLibrary) AnonymousClass0J2.A03(0, 569, OVRService.this._UL_mInjectionContext)).A09(callingPackage);
            Binder.restoreCallingIdentity(clearCallingIdentity);
            return ((AssetBundleUtil) AnonymousClass0J2.A03(1, 422, OVRService.this._UL_mInjectionContext)).getAssetsListBundle(callingPackage, Arrays.asList(A09));
        }

        @Override // com.oculus.aidl.OVRServiceInterface
        public Bundle assetFileStatusById(Bundle bundle) throws RemoteException {
            int i;
            String str;
            if (!bundle.containsKey("asset_id")) {
                i = OVRError.UNKNOWN_ERROR.mCode;
                str = "asset_id not supplied";
            } else {
                long j = bundle.getLong("asset_id");
                String callingPackage = OVRService.this.mCallerUtils.getCallingPackage(bundle);
                long clearCallingIdentity = Binder.clearCallingIdentity();
                AssetInfo A03 = ((OVRLibrary) AnonymousClass0J2.A03(0, 569, OVRService.this._UL_mInjectionContext)).A03(callingPackage, j);
                Binder.restoreCallingIdentity(clearCallingIdentity);
                if (A03 != null) {
                    return ((AssetBundleUtil) AnonymousClass0J2.A03(1, 422, OVRService.this._UL_mInjectionContext)).getBundleForStatus(callingPackage, A03);
                }
                i = OVRError.STORE_INSTALLATION_ERROR.mCode;
                str = "not entitled";
            }
            return Util.makeErrorResult(null, str, i);
        }

        @Override // com.oculus.aidl.OVRServiceInterface
        public Bundle assetFileStatusByName(Bundle bundle) throws RemoteException {
            int i;
            String str;
            if (!bundle.containsKey("asset_filename")) {
                i = OVRError.UNKNOWN_ERROR.mCode;
                str = "asset_filename not supplied";
            } else {
                String valueOf = String.valueOf(bundle.getString("asset_filename"));
                if (TextUtils.isEmpty(valueOf)) {
                    i = OVRError.UNKNOWN_ERROR.mCode;
                    str = "asset_filename invalid";
                } else {
                    String callingPackage = OVRService.this.mCallerUtils.getCallingPackage(bundle);
                    long clearCallingIdentity = Binder.clearCallingIdentity();
                    AssetInfo A04 = ((OVRLibrary) AnonymousClass0J2.A03(0, 569, OVRService.this._UL_mInjectionContext)).A04(callingPackage, valueOf);
                    Binder.restoreCallingIdentity(clearCallingIdentity);
                    if (A04 != null) {
                        return ((AssetBundleUtil) AnonymousClass0J2.A03(1, 422, OVRService.this._UL_mInjectionContext)).getBundleForStatus(callingPackage, A04);
                    }
                    i = OVRError.STORE_INSTALLATION_ERROR.mCode;
                    str = "not entitled";
                }
            }
            return Util.makeErrorResult(null, str, i);
        }

        @Override // com.oculus.aidl.OVRServiceInterface
        @Nullable
        public Bundle getAppScopedAccessToken(Bundle bundle) {
            OVRService oVRService = OVRService.this;
            return new AppScopedAccessTokenResultBuilder(oVRService.mAppScopedAccessTokenResultBuilderProvider, oVRService).A00(bundle);
        }

        @Override // com.oculus.aidl.OVRServiceInterface
        @Nullable
        public Bundle getBaseURLEndpoint(Bundle bundle) {
            Bundle maybeGetErrorBundle = OVRService.this.maybeGetErrorBundle(bundle);
            if (maybeGetErrorBundle != null) {
                return maybeGetErrorBundle;
            }
            Uri parse = Uri.parse(OVRService.this.mOculusApiEndpoint);
            Bundle bundle2 = new Bundle();
            bundle2.putParcelable("base_url_endpoint", parse);
            return bundle2;
        }

        @Override // com.oculus.aidl.OVRServiceInterface
        public Bundle getCloudStorage2UserDirectoryPath(Bundle bundle) {
            OVRService oVRService = OVRService.this;
            return new GetCloudStorage2UserDirectoryPathResultBuilder(oVRService.mGetCloudStorage2UserDirectoryPathResultBuilderProvider, oVRService).A00(bundle);
        }

        @Override // com.oculus.aidl.OVRServiceInterface
        @Nullable
        public Bundle getDeviceScopedAccessToken(Bundle bundle) {
            OVRService oVRService = OVRService.this;
            return new DeviceScopedAccessTokenResultBuilder(oVRService.mDeviceScopedAccessTokenResultBuilderProvider, oVRService).A00(bundle);
        }

        @Override // com.oculus.aidl.OVRServiceInterface
        @Nullable
        public Bundle getEntitlementCheckBundle(Bundle bundle) throws RemoteException {
            OVRService oVRService = OVRService.this;
            return new EntitlementCheckResultBuilder(oVRService.mEntitlementCheckResultBuilderProvider, oVRService).A00(bundle);
        }

        @Override // com.oculus.aidl.OVRServiceInterface
        @Nullable
        public Intent getIAPCheckoutFlowIntent(String str, Bundle bundle) throws RemoteException {
            OVRService oVRService = OVRService.this;
            return (Intent) new IAPCheckoutFlowResultBuilder(oVRService.mIAPCheckoutFlowResultBuilderProvider, oVRService, str).A00(bundle);
        }

        @Override // com.oculus.aidl.OVRServiceInterface
        @Nullable
        public Bundle getInstalledVRApplications(Bundle bundle) {
            OVRService oVRService = OVRService.this;
            return new InstalledVRApplicationsResultBuilder(oVRService.mInstalledVRApplicationsResultBuilderProvider, oVRService).A00(bundle);
        }

        @Override // com.oculus.aidl.OVRServiceInterface
        @Nullable
        public Intent getInvitableUsersFlowIntent(Bundle bundle) throws RemoteException {
            OVRService oVRService = OVRService.this;
            return (Intent) new InvitableUsersFlowResultBuilder(oVRService.mInvitableUsersFlowResultBuilderProvider, oVRService).A00(bundle);
        }

        @Override // com.oculus.aidl.OVRServiceInterface
        @Nullable
        public Bundle getLatestAvailableAppInformation(Bundle bundle) {
            OVRService oVRService = OVRService.this;
            return new LatestAvailableAppInformationResultBuilder(oVRService.mLatestAvailableAppInformationResultBuilderProvider, oVRService).A00(bundle);
        }

        @Override // com.oculus.aidl.OVRServiceInterface
        public Bundle getSharedMicrophoneData(Bundle bundle) {
            Bundle bundle2;
            Bundle maybeGetErrorBundle = OVRService.this.maybeGetErrorBundle(bundle);
            if (maybeGetErrorBundle != null) {
                return maybeGetErrorBundle;
            }
            try {
                String callingPackage = OVRService.this.mCallerUtils.getCallingPackage(bundle);
                AnonymousClass06g<Long, Integer> r4 = OVRService.this.mCachedPackageMicPermission.get(callingPackage);
                long currentTimeMillis = System.currentTimeMillis();
                if (r4 == null || r4.A00.longValue() + 5000 < currentTimeMillis) {
                    int checkPermission = OVRService.this.getApplicationContext().getPackageManager().checkPermission("android.permission.RECORD_AUDIO", callingPackage);
                    OVRService.this.mCachedPackageMicPermission.put(callingPackage, new AnonymousClass06g<>(Long.valueOf(currentTimeMillis), Integer.valueOf(checkPermission)));
                    if (checkPermission == -1) {
                        OVRService.this.logPermissionDenied(bundle, callingPackage, "android.permission.RECORD_AUDIO");
                        return Util.makeErrorResultQuiet("Unable to get microphone data", OVRError.UNKNOWN_ERROR.mCode);
                    }
                } else if (r4.A01.intValue() == -1) {
                    return Util.makeErrorResultQuiet("Unable to get microphone data", OVRError.UNKNOWN_ERROR.mCode);
                }
                OVRService oVRService = OVRService.this;
                oVRService.mPlatformPluginManager.A06(oVRService);
                long j = bundle.getLong("microphone_uid");
                int i = bundle.getInt("max_size");
                PlatformPluginManager platformPluginManager = OVRService.this.mPlatformPluginManager;
                long clearCallingIdentity = Binder.clearCallingIdentity();
                try {
                    if (!PlatformPluginManager.A05(platformPluginManager)) {
                        bundle2 = Util.makeErrorResultQuiet("PlatformPlugin not initialized", OVRError.UNKNOWN_ERROR.mCode);
                    } else {
                        bundle2 = null;
                    }
                    if (bundle2 == null) {
                        bundle2 = new Bundle();
                        synchronized (platformPluginManager.mNativeCodeLock) {
                            PlatformPluginManager.nativeGetSharedMicrophoneData(j, i, bundle2);
                        }
                    }
                    return bundle2;
                } finally {
                    Binder.restoreCallingIdentity(clearCallingIdentity);
                }
            } catch (Exception e) {
                return Util.makeErrorResult(e, e.getMessage(), OVRError.UNKNOWN_ERROR.mCode);
            }
        }

        @Override // com.oculus.aidl.OVRServiceInterface
        @Nullable
        @SuppressLint({"CatchGeneralException"})
        public ParcelFileDescriptor getSharedMicrophoneSocketFD(Bundle bundle) {
            if (OVRService.this.maybeGetErrorBundle(bundle) != null) {
                return null;
            }
            try {
                String callingPackage = OVRService.this.mCallerUtils.getCallingPackage(bundle);
                if (OVRService.this.getApplicationContext().getPackageManager().checkPermission("android.permission.RECORD_AUDIO", callingPackage) != 0) {
                    OVRService.this.logPermissionDenied(bundle, callingPackage, "android.permission.RECORD_AUDIO");
                    return null;
                }
                OVRService oVRService = OVRService.this;
                oVRService.mPlatformPluginManager.A06(oVRService);
                long j = bundle.getLong("microphone_uid");
                PlatformPluginManager platformPluginManager = OVRService.this.mPlatformPluginManager;
                long clearCallingIdentity = Binder.clearCallingIdentity();
                try {
                    if (PlatformPluginManager.A01(platformPluginManager) == null) {
                        synchronized (platformPluginManager.mNativeCodeLock) {
                            int nativeGetSharedMicSocketFD = PlatformPluginManager.nativeGetSharedMicSocketFD(j);
                            if (nativeGetSharedMicSocketFD != -1) {
                                ParcelFileDescriptor adoptFd = ParcelFileDescriptor.adoptFd(nativeGetSharedMicSocketFD);
                                Binder.restoreCallingIdentity(clearCallingIdentity);
                                return adoptFd;
                            }
                        }
                    }
                    return null;
                } finally {
                    Binder.restoreCallingIdentity(clearCallingIdentity);
                }
            } catch (Exception unused) {
                return null;
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:29:0x0078, code lost:
            if (r2.mTrustedApp.A06(r2.mContext) == false) goto L_0x007a;
         */
        @Override // com.oculus.aidl.OVRServiceInterface
        @javax.annotation.Nullable
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public android.os.Bundle getSystemVoipData(android.os.Bundle r12) {
            /*
            // Method dump skipped, instructions count: 184
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.horizon.service.OVRService.AnonymousClass4.getSystemVoipData(android.os.Bundle):android.os.Bundle");
        }

        @Override // com.oculus.aidl.OVRServiceInterface
        public String getSystemVoipMicrophoneMuted(Bundle bundle) {
            Throwable th;
            String nativeGetSystemVoipMicrophoneMuted;
            String string;
            if (OVRService.this.maybeGetErrorBundle(bundle) != null) {
                return null;
            }
            OVRService oVRService = OVRService.this;
            if (oVRService.mUsePartyService) {
                Cursor cursor = null;
                try {
                    Cursor query = oVRService.getContentResolver().query(SocialPlatformContent.Parties3pApi.Queries.GET_MIC_MUTE_STATE, null, null, null, null);
                    if (query == null || !query.moveToNext() || (string = query.getString(query.getColumnIndexOrThrow("mic_mute_state"))) == null) {
                        throw new IllegalArgumentException();
                    }
                    try {
                        query.close();
                        return string;
                    } catch (Exception unused) {
                        return null;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (0 != 0) {
                        cursor.close();
                    }
                    throw th;
                }
            } else {
                PlatformPluginManager platformPluginManager = oVRService.mPlatformPluginManager;
                if (!PlatformPluginManager.A05(platformPluginManager)) {
                    return "UNKNOWN";
                }
                synchronized (platformPluginManager.mNativeCodeLock) {
                    try {
                        nativeGetSystemVoipMicrophoneMuted = PlatformPluginManager.nativeGetSystemVoipMicrophoneMuted();
                    } catch (Throwable th3) {
                        th = th3;
                        throw th;
                    }
                }
                return nativeGetSystemVoipMicrophoneMuted;
            }
        }

        @Override // com.oculus.aidl.OVRServiceInterface
        public boolean getSystemVoipPassthrough(Bundle bundle) {
            boolean nativeGetSystemVoipPassthrough;
            if (OVRService.this.maybeGetErrorBundle(bundle) == null) {
                OVRService oVRService = OVRService.this;
                if (oVRService.mUsePartyService) {
                    AnonymousClass0NO.A01(OVRService.class, "SystemVoipPassthrough is deprecated");
                } else {
                    try {
                        PlatformPluginManager platformPluginManager = oVRService.mPlatformPluginManager;
                        if (PlatformPluginManager.A05(platformPluginManager)) {
                            synchronized (platformPluginManager.mNativeCodeLock) {
                                try {
                                    nativeGetSystemVoipPassthrough = PlatformPluginManager.nativeGetSystemVoipPassthrough();
                                } catch (Throwable th) {
                                    throw th;
                                }
                            }
                            return nativeGetSystemVoipPassthrough;
                        }
                        throw new PlatformInitException("PlatformPlugin not initialized");
                    } catch (Exception unused) {
                        return false;
                    }
                }
            }
            return false;
        }

        @Override // com.oculus.aidl.OVRServiceInterface
        public String getSystemVoipStatus(Bundle bundle) {
            String nativeGetSystemVoipStatus;
            String string;
            if (OVRService.this.maybeGetErrorBundle(bundle) != null) {
                return null;
            }
            OVRService oVRService = OVRService.this;
            if (oVRService.mUsePartyService) {
                Cursor cursor = null;
                try {
                    Cursor query = oVRService.getContentResolver().query(SocialPlatformContent.Parties3pApi.Queries.GET_STATUS, null, null, null, null);
                    if (query == null || !query.moveToNext() || (string = query.getString(query.getColumnIndexOrThrow("status"))) == null) {
                        throw new IllegalArgumentException();
                    }
                    try {
                        query.close();
                        return string;
                    } catch (Exception unused) {
                        return null;
                    }
                } catch (Throwable th) {
                    if (0 != 0) {
                        cursor.close();
                        throw th;
                    }
                    throw th;
                }
            } else {
                PlatformPluginManager platformPluginManager = oVRService.mPlatformPluginManager;
                if (PlatformPluginManager.A05(platformPluginManager)) {
                    synchronized (platformPluginManager.mNativeCodeLock) {
                        try {
                            nativeGetSystemVoipStatus = PlatformPluginManager.nativeGetSystemVoipStatus();
                        } catch (Throwable th2) {
                            throw th2;
                        }
                    }
                    return nativeGetSystemVoipStatus;
                }
                throw new PlatformInitException("PlatformPlugin not initialized");
            }
        }

        @Override // com.oculus.aidl.OVRServiceInterface
        public Bundle getViewerPurchasesDurableCacheJSON(Bundle bundle) {
            OVRService oVRService = OVRService.this;
            return new DurableIAPResultBuilder(oVRService.mDurableIAPResultBuilderProvider, oVRService).A00(bundle);
        }

        @Override // com.oculus.aidl.OVRServiceInterface
        @Deprecated
        public int isAppEntitled() {
            OVRService oVRService = OVRService.this;
            return new IsAppEntitledResultBuilder(oVRService.mIsAppEntitledResultBuilderProvider, oVRService).A00(new Bundle()).ordinal();
        }

        @Override // com.oculus.aidl.OVRServiceInterface
        @Nullable
        public Bundle languagePackGetCurrent(Bundle bundle) throws RemoteException {
            int i;
            String str;
            String callingPackage = OVRService.this.mCallerUtils.getCallingPackage(null);
            long clearCallingIdentity = Binder.clearCallingIdentity();
            App A01 = ((OVRLibrary) AnonymousClass0J2.A03(0, 569, OVRService.this._UL_mInjectionContext)).A01(callingPackage);
            if (A01 == null) {
                Binder.restoreCallingIdentity(clearCallingIdentity);
                i = OVRError.STORE_INSTALLATION_ERROR.mCode;
                str = "not entitled";
            } else if (TextUtils.isEmpty(A01.currentLanguagePack)) {
                Binder.restoreCallingIdentity(clearCallingIdentity);
                i = OVRError.STORE_INSTALLATION_ERROR.mCode;
                str = "language pack not set";
            } else {
                AssetInfo[] A09 = ((OVRLibrary) AnonymousClass0J2.A03(0, 569, OVRService.this._UL_mInjectionContext)).A09(callingPackage);
                for (AssetInfo assetInfo : A09) {
                    if (TextUtils.equals(A01.currentLanguagePack, assetInfo.languageCode)) {
                        Binder.restoreCallingIdentity(clearCallingIdentity);
                        return ((AssetBundleUtil) AnonymousClass0J2.A03(1, 422, OVRService.this._UL_mInjectionContext)).getBundleForStatus(callingPackage, assetInfo);
                    }
                }
                Binder.restoreCallingIdentity(clearCallingIdentity);
                i = OVRError.STORE_INSTALLATION_ERROR.mCode;
                str = "missing language pack";
            }
            return Util.makeErrorResult(null, str, i);
        }

        @Override // com.oculus.aidl.OVRServiceInterface
        @Nullable
        public Bundle languagePackSetCurrent(Bundle bundle) throws RemoteException {
            int i;
            String str;
            final String string = bundle.getString("tag");
            if (TextUtils.isEmpty(string)) {
                i = OVRError.STORE_INSTALLATION_ERROR.mCode;
                str = "missing argument: tag";
            } else {
                String callingPackage = OVRService.this.mCallerUtils.getCallingPackage(null);
                long clearCallingIdentity = Binder.clearCallingIdentity();
                final App A01 = ((OVRLibrary) AnonymousClass0J2.A03(0, 569, OVRService.this._UL_mInjectionContext)).A01(callingPackage);
                if (A01 == null) {
                    Binder.restoreCallingIdentity(clearCallingIdentity);
                    i = OVRError.STORE_INSTALLATION_ERROR.mCode;
                    str = "not entitled";
                } else {
                    AssetInfo[] A09 = ((OVRLibrary) AnonymousClass0J2.A03(0, 569, OVRService.this._UL_mInjectionContext)).A09(callingPackage);
                    for (AssetInfo assetInfo : A09) {
                        if (TextUtils.equals(string, assetInfo.languageCode)) {
                            String str2 = assetInfo.name;
                            RequestOrigin requestOrigin = RequestOrigin.PLATFORM_SDK;
                            AnonymousClass1 r2 = new InstallerCallback() {
                                /* class com.oculus.horizon.service.OVRService.AnonymousClass4.AnonymousClass1 */

                                @Override // com.oculus.library.model.InstallerCallback
                                public void onInstallerResult(InstallerResult installerResult) {
                                    if (installerResult.error == null) {
                                        App.Editor editor = new App.Editor(A01.packageName);
                                        editor.languagePack = Optional.of(string);
                                        ((OVRLibrary) AnonymousClass0J2.A03(0, 569, OVRService.this._UL_mInjectionContext)).A06(editor);
                                    }
                                }
                            };
                            Bundle bundle2 = new Bundle();
                            bundle2.putString("asset_name", str2);
                            OVRLibrary.A00((OVRLibrary) AnonymousClass0J2.A03(0, 569, OVRService.this._UL_mInjectionContext), callingPackage, requestOrigin, r2, bundle2);
                            Binder.restoreCallingIdentity(clearCallingIdentity);
                            return AssetBundleUtil.getBundleForOperation(callingPackage, assetInfo, true);
                        }
                    }
                    Binder.restoreCallingIdentity(clearCallingIdentity);
                    return Util.makeErrorResult(null, AnonymousClass006.A05("no language pack found for ", string), OVRError.STORE_INSTALLATION_ERROR.mCode);
                }
            }
            return Util.makeErrorResult(null, str, i);
        }

        @Override // com.oculus.aidl.OVRServiceInterface
        public Bundle launchOtherApp(Bundle bundle) {
            OVRService oVRService = OVRService.this;
            return new LaunchOtherAppResultBuilder(oVRService.mLaunchOtherAppResultBuilderProvider, oVRService).A00(bundle);
        }

        @Override // com.oculus.aidl.OVRServiceInterface
        public Bundle logToMarauder(String str, Bundle bundle) {
            String str2;
            String str3;
            Bundle maybeGetErrorBundle = OVRService.this.maybeGetErrorBundle(bundle);
            if (maybeGetErrorBundle != null) {
                return maybeGetErrorBundle;
            }
            try {
                str2 = OVRService.this.mCallerUtils.getCallingPackage(bundle);
            } catch (AmbiguousCallingPackageException e) {
                AnonymousClass0NO.A07(OVRService.class, e, "Unable to determine package for calling identity.", new Object[0]);
                str2 = "";
            }
            try {
                str3 = OVRService.this.mExternalPlatformLocal.getAppID(str2);
            } catch (ExternalPlatformLocal.PackageNotInLibraryException | IOException e2) {
                AnonymousClass0NO.A07(OVRService.class, e2, "Unable to find AppId for package (%s)", str2);
                str3 = "0";
            }
            if (bundle.getBoolean("first_party_app_log", false)) {
                OVRService.this.mLogger.reportPlatformSDKFirstPartyAppLogs(str3, str2, str);
            } else {
                OVRService.this.mLogger.reportPlatformSDKLogs(str3, str2, str);
            }
            return new Bundle();
        }

        @Override // com.oculus.aidl.OVRServiceInterface
        public Bundle partyChatGetVolume(Bundle bundle) {
            float nativeGetSystemVoipVolume;
            Bundle maybeGetErrorBundle = OVRService.this.maybeGetErrorBundle(bundle);
            if (maybeGetErrorBundle != null) {
                return maybeGetErrorBundle;
            }
            OVRService oVRService = OVRService.this;
            if (oVRService.mUsePartyService) {
                try {
                    Cursor cursor = null;
                    try {
                        Cursor query = oVRService.getContentResolver().query(SocialPlatformContent.Parties3pApi.Queries.GET_VOLUME.buildUpon().appendQueryParameter(SocialPlatformContent.Parties3pApi.In.CALLER_PACKAGE, oVRService.mCallerUtils.getCallingPackage(bundle)).build(), null, null, null, null);
                        if (query == null || !query.moveToNext()) {
                            throw new IllegalArgumentException();
                        }
                        Float valueOf = Float.valueOf(query.getFloat(query.getColumnIndexOrThrow("volume")));
                        if (valueOf != null) {
                            float floatValue = valueOf.floatValue();
                            Bundle bundle2 = new Bundle();
                            bundle2.putFloat("volume", floatValue);
                            query.close();
                            return bundle2;
                        }
                        throw new IllegalArgumentException();
                    } catch (Throwable th) {
                        if (0 != 0) {
                            cursor.close();
                        }
                        throw th;
                    }
                } catch (SecurityException unused) {
                    return PlatformPluginManager.A00(bundle, "getVolume");
                }
            } else {
                try {
                    oVRService.mPlatformPluginManager.A06(oVRService);
                    OVRService oVRService2 = OVRService.this;
                    if (!oVRService2.mPlatformPluginManager.A09(oVRService2.mCallerUtils.getCallingPackage(bundle))) {
                        return PlatformPluginManager.A00(bundle, "getVolume");
                    }
                    PlatformPluginManager platformPluginManager = OVRService.this.mPlatformPluginManager;
                    Bundle A01 = PlatformPluginManager.A01(platformPluginManager);
                    if (A01 != null) {
                        return A01;
                    }
                    synchronized (platformPluginManager.mNativeCodeLock) {
                        nativeGetSystemVoipVolume = PlatformPluginManager.nativeGetSystemVoipVolume();
                    }
                    Bundle bundle3 = new Bundle();
                    bundle3.putFloat("volume", nativeGetSystemVoipVolume);
                    return bundle3;
                } catch (Exception e) {
                    return Util.makeErrorResult(e, e.getMessage(), OVRError.UNKNOWN_ERROR.mCode);
                }
            }
        }

        @Override // com.oculus.aidl.OVRServiceInterface
        public Bundle partyChatSetVolume(Bundle bundle) {
            Bundle maybeGetErrorBundle = OVRService.this.maybeGetErrorBundle(bundle);
            if (maybeGetErrorBundle != null) {
                return maybeGetErrorBundle;
            }
            OVRService oVRService = OVRService.this;
            if (oVRService.mUsePartyService) {
                try {
                    float f = bundle.getFloat("volume");
                    OVRService oVRService2 = OVRService.this;
                    String callingPackage = oVRService2.mCallerUtils.getCallingPackage(bundle);
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("volume", Float.valueOf(f));
                    if (oVRService2.getContentResolver().update(SocialPlatformContent.Parties3pApi.Updates.SET_VOLUME.buildUpon().appendQueryParameter(SocialPlatformContent.Parties3pApi.In.CALLER_PACKAGE, callingPackage).build(), contentValues, null, null) == 1) {
                        return new Bundle();
                    }
                    throw new IllegalArgumentException();
                } catch (SecurityException unused) {
                    return PlatformPluginManager.A00(bundle, "setVolume");
                }
            } else {
                try {
                    oVRService.mPlatformPluginManager.A06(oVRService);
                    OVRService oVRService3 = OVRService.this;
                    if (!oVRService3.mPlatformPluginManager.A09(oVRService3.mCallerUtils.getCallingPackage(bundle))) {
                        return PlatformPluginManager.A00(bundle, "setVolume");
                    }
                    float f2 = bundle.getFloat("volume");
                    PlatformPluginManager platformPluginManager = OVRService.this.mPlatformPluginManager;
                    Bundle A01 = PlatformPluginManager.A01(platformPluginManager);
                    if (A01 != null) {
                        return A01;
                    }
                    synchronized (platformPluginManager.mNativeCodeLock) {
                        PlatformPluginManager.nativeSetSystemVoipVolume(f2);
                    }
                    return new Bundle();
                } catch (Exception e) {
                    return Util.makeErrorResult(e, e.getMessage(), OVRError.UNKNOWN_ERROR.mCode);
                }
            }
        }

        @Override // com.oculus.aidl.OVRServiceInterface
        public Bundle registerProcessToken(Bundle bundle) {
            OVRService oVRService = OVRService.this;
            return new RegisterProcessTokenResultBuilder(oVRService.mRegisterProcessTokenResultBuilderProvider, oVRService).A00(bundle);
        }

        @Override // com.oculus.aidl.OVRServiceInterface
        public Bundle setRichPresence(Bundle bundle) {
            OVRService oVRService = OVRService.this;
            return new SetRichPresenceResultBuilder(oVRService.mSetRichPresenceResultBuilderProvider, oVRService).A00(bundle);
        }

        @Override // com.oculus.aidl.OVRServiceInterface
        @Nullable
        public Bundle setSystemVoipMicrophoneMuted(Bundle bundle) {
            Bundle maybeGetErrorBundle = OVRService.this.maybeGetErrorBundle(bundle);
            if (maybeGetErrorBundle != null) {
                return maybeGetErrorBundle;
            }
            OVRService oVRService = OVRService.this;
            if (oVRService.mUsePartyService) {
                try {
                    int i = bundle.getInt("microphone_muted", 0);
                    OVRService oVRService2 = OVRService.this;
                    String callingPackage = oVRService2.mCallerUtils.getCallingPackage(bundle);
                    if (i == 0 || i == 1 || i == 2) {
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("mic_mute_state", Integer.valueOf(i));
                        if (oVRService2.getContentResolver().update(SocialPlatformContent.Parties3pApi.Updates.SET_MIC_MUTE_STATE.buildUpon().appendQueryParameter(SocialPlatformContent.Parties3pApi.In.CALLER_PACKAGE, callingPackage).build(), contentValues, null, null) == 1) {
                            return new Bundle();
                        }
                        throw new IllegalArgumentException();
                    }
                    throw new IllegalArgumentException("Unrecognized mute state");
                } catch (SecurityException unused) {
                    return PlatformPluginManager.A00(bundle, "microphone");
                }
            } else {
                try {
                    oVRService.mPlatformPluginManager.A06(oVRService);
                    OVRService oVRService3 = OVRService.this;
                    if (!oVRService3.mPlatformPluginManager.A09(oVRService3.mCallerUtils.getCallingPackage(bundle))) {
                        return PlatformPluginManager.A00(bundle, "microphone");
                    }
                    int i2 = bundle.getInt("microphone_muted", 0);
                    PlatformPluginManager platformPluginManager = OVRService.this.mPlatformPluginManager;
                    Bundle A01 = PlatformPluginManager.A01(platformPluginManager);
                    if (A01 != null) {
                        return A01;
                    }
                    Bundle bundle2 = new Bundle();
                    synchronized (platformPluginManager.mNativeCodeLock) {
                        PlatformPluginManager.nativeSetSystemVoipMicrophoneMuted(i2, bundle2);
                    }
                    return bundle2;
                } catch (Exception e) {
                    return Util.makeErrorResult(e, e.getMessage(), OVRError.UNKNOWN_ERROR.mCode);
                }
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:10:0x0037, code lost:
            if (r2.mTrustedApp.A06(r2.mContext) == false) goto L_0x0039;
         */
        @Override // com.oculus.aidl.OVRServiceInterface
        @javax.annotation.Nullable
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public android.os.ParcelFileDescriptor setSystemVoipPassthrough(android.os.Bundle r6) {
            /*
            // Method dump skipped, instructions count: 105
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.horizon.service.OVRService.AnonymousClass4.setSystemVoipPassthrough(android.os.Bundle):android.os.ParcelFileDescriptor");
        }

        @Override // com.oculus.aidl.OVRServiceInterface
        @Nullable
        public Bundle setSystemVoipSuppressed(Bundle bundle) {
            Bundle maybeGetErrorBundle = OVRService.this.maybeGetErrorBundle(bundle);
            if (maybeGetErrorBundle == null) {
                if (OVRService.this.mUsePartyService) {
                    try {
                        boolean z = bundle.getBoolean("suppressed");
                        OVRService oVRService = OVRService.this;
                        ContentValues contentValues = new ContentValues();
                        contentValues.put(SocialPlatformContent.Parties3pApi.In.VOIP_SUPPRESSED, Boolean.valueOf(z));
                        if (oVRService.getContentResolver().update(SocialPlatformContent.Parties3pApi.Updates.SET_SUPPRESSED, contentValues, null, null) == 1) {
                            return new Bundle();
                        }
                        throw new IllegalArgumentException();
                    } catch (Exception e) {
                        return Util.makeErrorResult(e, e.getMessage(), OVRError.UNKNOWN_ERROR.mCode);
                    }
                } else {
                    boolean z2 = bundle.getBoolean("suppressed");
                    PlatformPluginManager platformPluginManager = OVRService.this.mPlatformPluginManager;
                    maybeGetErrorBundle = PlatformPluginManager.A01(platformPluginManager);
                    if (maybeGetErrorBundle == null) {
                        Bundle bundle2 = new Bundle();
                        synchronized (platformPluginManager.mNativeCodeLock) {
                            try {
                                PlatformPluginManager.nativeSetSystemVoipSuppressed(z2, bundle2);
                            } catch (Throwable th) {
                                throw th;
                            }
                        }
                        return bundle2;
                    }
                }
            }
            return maybeGetErrorBundle;
        }

        @Override // com.oculus.aidl.OVRServiceInterface
        public void sharedMicrophoneDisableNoiseSuppressor(Bundle bundle) throws RemoteException {
            if (OVRService.this.maybeGetErrorBundle(bundle) != null) {
                AnonymousClass0NO.A02(OVRService.class, "invalid bundle in disable shared microphone noise suppressor");
                return;
            }
            try {
                OVRService oVRService = OVRService.this;
                oVRService.mPlatformPluginManager.A06(oVRService);
                long j = bundle.getLong("microphone_uid");
                PlatformPluginManager platformPluginManager = OVRService.this.mPlatformPluginManager;
                long clearCallingIdentity = Binder.clearCallingIdentity();
                try {
                    if (PlatformPluginManager.A01(platformPluginManager) == null) {
                        synchronized (platformPluginManager.mNativeCodeLock) {
                            PlatformPluginManager.nativeSharedMicrophoneDisableNoiseSuppressor(j);
                        }
                    }
                } finally {
                    Binder.restoreCallingIdentity(clearCallingIdentity);
                }
            } catch (Exception unused) {
            }
        }

        @Override // com.oculus.aidl.OVRServiceInterface
        public void sharedMicrophoneEnableNoiseSuppressor(Bundle bundle) throws RemoteException {
            if (OVRService.this.maybeGetErrorBundle(bundle) != null) {
                AnonymousClass0NO.A02(OVRService.class, "invalid bundle in enable shared microphone noise suppressor");
                return;
            }
            try {
                OVRService oVRService = OVRService.this;
                oVRService.mPlatformPluginManager.A06(oVRService);
                long j = bundle.getLong("microphone_uid");
                PlatformPluginManager platformPluginManager = OVRService.this.mPlatformPluginManager;
                long clearCallingIdentity = Binder.clearCallingIdentity();
                try {
                    if (PlatformPluginManager.A01(platformPluginManager) == null) {
                        synchronized (platformPluginManager.mNativeCodeLock) {
                            PlatformPluginManager.nativeSharedMicrophoneEnableNoiseSuppressor(j);
                        }
                    }
                } finally {
                    Binder.restoreCallingIdentity(clearCallingIdentity);
                }
            } catch (Exception unused) {
            }
        }

        @Override // com.oculus.aidl.OVRServiceInterface
        public Bundle sharedMicrophoneStop(Bundle bundle) {
            Bundle maybeGetErrorBundle = OVRService.this.maybeGetErrorBundle(bundle);
            if (maybeGetErrorBundle != null) {
                return maybeGetErrorBundle;
            }
            try {
                OVRService oVRService = OVRService.this;
                oVRService.mPlatformPluginManager.A06(oVRService);
                long j = bundle.getLong("microphone_uid");
                PlatformPluginManager platformPluginManager = OVRService.this.mPlatformPluginManager;
                long clearCallingIdentity = Binder.clearCallingIdentity();
                try {
                    Bundle A01 = PlatformPluginManager.A01(platformPluginManager);
                    if (A01 == null) {
                        synchronized (platformPluginManager.mNativeCodeLock) {
                            PlatformPluginManager.nativeSharedMicrophoneStop(j);
                        }
                        A01 = new Bundle();
                    }
                    return A01;
                } finally {
                    Binder.restoreCallingIdentity(clearCallingIdentity);
                }
            } catch (Exception e) {
                return Util.makeErrorResult(e, e.getMessage(), OVRError.UNKNOWN_ERROR.mCode);
            }
        }

        @Override // com.oculus.aidl.OVRServiceInterface
        public Bundle startPartyChat(Bundle bundle) {
            Bundle maybeGetErrorBundle = OVRService.this.maybeGetErrorBundle(bundle);
            if (maybeGetErrorBundle != null) {
                return maybeGetErrorBundle;
            }
            OVRService oVRService = OVRService.this;
            if (oVRService.mUsePartyService) {
                try {
                    long j = bundle.getLong("party_id", -1);
                    OVRService oVRService2 = OVRService.this;
                    String callingPackage = oVRService2.mCallerUtils.getCallingPackage(bundle);
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("party_id", Long.valueOf(j));
                    if (oVRService2.getContentResolver().update(SocialPlatformContent.Parties3pApi.Updates.START_CHAT.buildUpon().appendQueryParameter(SocialPlatformContent.Parties3pApi.In.CALLER_PACKAGE, callingPackage).build(), contentValues, null, null) == 1) {
                        return new Bundle();
                    }
                    throw new IllegalArgumentException();
                } catch (SecurityException unused) {
                    return PlatformPluginManager.A00(bundle, "start");
                }
            } else {
                try {
                    oVRService.mPlatformPluginManager.A06(oVRService);
                    OVRService oVRService3 = OVRService.this;
                    if (!oVRService3.mPlatformPluginManager.A09(oVRService3.mCallerUtils.getCallingPackage(bundle))) {
                        return PlatformPluginManager.A00(bundle, "start");
                    }
                    return OVRService.this.mPlatformPluginManager.A9J(bundle.getLong("party_id", -1));
                } catch (Exception e) {
                    return Util.makeErrorResult(e, e.getMessage(), OVRError.UNKNOWN_ERROR.mCode);
                }
            }
        }

        @Override // com.oculus.aidl.OVRServiceInterface
        public Bundle stopPartyChat(Bundle bundle) {
            Bundle maybeGetErrorBundle = OVRService.this.maybeGetErrorBundle(bundle);
            if (maybeGetErrorBundle != null) {
                return maybeGetErrorBundle;
            }
            OVRService oVRService = OVRService.this;
            if (oVRService.mUsePartyService) {
                try {
                    if (oVRService.getContentResolver().update(SocialPlatformContent.Parties3pApi.Updates.STOP_CHAT.buildUpon().appendQueryParameter(SocialPlatformContent.Parties3pApi.In.CALLER_PACKAGE, oVRService.mCallerUtils.getCallingPackage(bundle)).build(), null, null, null) == 1) {
                        return new Bundle();
                    }
                    throw new IllegalArgumentException();
                } catch (SecurityException unused) {
                    return PlatformPluginManager.A00(bundle, "start");
                }
            } else {
                try {
                    oVRService.mPlatformPluginManager.A06(oVRService);
                    OVRService oVRService2 = OVRService.this;
                    if (!oVRService2.mPlatformPluginManager.A09(oVRService2.mCallerUtils.getCallingPackage(bundle))) {
                        return PlatformPluginManager.A00(bundle, "start");
                    }
                    return OVRService.this.mPlatformPluginManager.A9S();
                } catch (Exception e) {
                    return Util.makeErrorResult(e, e.getMessage(), OVRError.UNKNOWN_ERROR.mCode);
                }
            }
        }

        @Override // com.oculus.aidl.OVRServiceInterface
        @Nullable
        public Bundle updatePlatformContext(Bundle bundle) throws RemoteException {
            Bundle maybeGetErrorBundle = OVRService.this.maybeGetErrorBundle(bundle);
            if (maybeGetErrorBundle != null) {
                return maybeGetErrorBundle;
            }
            try {
                OVRService.this.mPlatformContext.parse(ExternalPlatformLocal.getAppID(bundle, OVRService.this.mCallerUtils.getCallingPackage(bundle), OVRService.this.mExternalPlatformLocal.mLibrary), bundle);
                return new Bundle();
            } catch (ExternalPlatformLocal.PackageNotInLibraryException | IOException unused) {
                return null;
            }
        }
    };
    public final AnonymousClass0i9 mBroadcastReceiver = new OculusPublicBroadcastReceiver("com.samsung.intent.action.HMT_DISCONNECTED") {
        /* class com.oculus.horizon.service.OVRService.AnonymousClass2 */

        @Override // com.oculus.security.basecomponent.OculusSecureBroadcastReceiverBase
        public void onReceive(Context context, Intent intent, AnonymousClass0b9 r5) {
            String action = intent.getAction();
            if (action != null && action.equals("com.samsung.intent.action.HMT_DISCONNECTED")) {
                OVRService oVRService = OVRService.this;
                oVRService.mPlatformPluginManager.A07(oVRService);
            }
        }
    };
    public final ConcurrentHashMap<String, AnonymousClass06g<Long, Integer>> mCachedPackageMicPermission = new ConcurrentHashMap<>();
    @Inject
    @Eager
    public CallerUtils mCallerUtils;
    @Inject
    @Eager
    public CloudStorageManager mCloudStorageManager;
    @Inject
    public Provider<Credentials> mCredentialsProvider;
    @Inject
    public DeviceScopedAccessTokenResultBuilderProvider mDeviceScopedAccessTokenResultBuilderProvider;
    @Inject
    public DurableIAPResultBuilderProvider mDurableIAPResultBuilderProvider;
    @Inject
    public EntitlementCheckResultBuilderProvider mEntitlementCheckResultBuilderProvider;
    @Inject
    @Eager
    public ExternalPlatformLocal mExternalPlatformLocal;
    @Inject
    public GetCloudStorage2UserDirectoryPathResultBuilderProvider mGetCloudStorage2UserDirectoryPathResultBuilderProvider;
    @Inject
    public IAPCheckoutFlowResultBuilderProvider mIAPCheckoutFlowResultBuilderProvider;
    @Inject
    public InstalledVRApplicationsResultBuilderProvider mInstalledVRApplicationsResultBuilderProvider;
    @Inject
    public InvitableUsersFlowResultBuilderProvider mInvitableUsersFlowResultBuilderProvider;
    @Inject
    public IsAppEntitledResultBuilderProvider mIsAppEntitledResultBuilderProvider;
    @Inject
    public LatestAvailableAppInformationResultBuilderProvider mLatestAvailableAppInformationResultBuilderProvider;
    @Inject
    public LaunchOtherAppResultBuilderProvider mLaunchOtherAppResultBuilderProvider;
    @Inject
    @Eager
    public ServiceLogger mLogger;
    @Inject
    @Eager
    @OculusApiEndpoint
    public String mOculusApiEndpoint;
    @Inject
    @Eager
    public PlatformContext mPlatformContext;
    @Inject
    @Eager
    public PlatformPluginManager mPlatformPluginManager;
    @Inject
    @Eager
    public PresenceManager mPresenceManager;
    @Inject
    @Eager
    public ProcessTokenTracker mProcessTokenTracker;
    @Inject
    public RegisterProcessTokenResultBuilderProvider mRegisterProcessTokenResultBuilderProvider;
    @Inject
    public SetRichPresenceResultBuilderProvider mSetRichPresenceResultBuilderProvider;
    public boolean mUsePartyService = false;

    public static class ErrorForJson extends BaseForJson {
        @SerializedName("error")
        public final Map<String, String> error;

        public ErrorForJson(Bundle bundle, Bundle bundle2, Class cls) {
            super("service_error", bundle, cls);
            this.error = OVRService.createArgsMap(bundle2, OVRService.BLACKLISTED_LOGGING_KEYS);
        }
    }

    public static class PermissionDeniedForJson extends BaseForJson {
        @SerializedName("permission_name")
        public final String permission;
        @SerializedName("package_name")
        public final String pkgName;

        public PermissionDeniedForJson(Bundle bundle, String str, String str2, Class cls) {
            super("permission_denied", bundle, cls);
            this.pkgName = str;
            this.permission = str2;
        }
    }

    public static final void _UL_staticInjectMe(AbstractC06640p5 r2, OVRService oVRService) {
        oVRService._UL_mInjectionContext = new AnonymousClass0QC(3, r2);
        oVRService.mOculusApiEndpoint = EndpointModule.A06(r2);
        oVRService.mExternalPlatformLocal = ExternalPlatformLocal._UL__ULSEP_com_oculus_horizon_service_ExternalPlatformLocal_ULSEP_ACCESS_METHOD(r2);
        oVRService.mPlatformContext = PlatformContext._UL__ULSEP_com_oculus_horizon_platform_PlatformContext_ULSEP_ACCESS_METHOD(r2);
        oVRService.mPlatformPluginManager = (PlatformPluginManager) AnonymousClass117.A00(160, r2);
        oVRService.mLogger = ServiceLogger._UL__ULSEP_com_oculus_horizon_service_ServiceLogger_ULSEP_ACCESS_METHOD(r2);
        oVRService.mCredentialsProvider = CredentialsModule._UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_auth_credentials_Credentials_ULGT__ULSEP_ACCESS_METHOD(r2);
        oVRService.mCallerUtils = CallerUtils._UL__ULSEP_com_oculus_horizon_service_CallerUtils_ULSEP_ACCESS_METHOD(r2);
        oVRService.mProcessTokenTracker = (ProcessTokenTracker) AnonymousClass117.A00(389, r2);
        oVRService.mCloudStorageManager = (CloudStorageManager) AnonymousClass117.A00(73, r2);
        oVRService.mPresenceManager = PresenceManager._UL__ULSEP_com_oculus_horizon_platform_PresenceManager_ULSEP_ACCESS_METHOD(r2);
        oVRService.mIsAppEntitledResultBuilderProvider = (IsAppEntitledResultBuilderProvider) AnonymousClass117.A00(94, r2);
        oVRService.mEntitlementCheckResultBuilderProvider = (EntitlementCheckResultBuilderProvider) AnonymousClass117.A00(581, r2);
        oVRService.mAppScopedAccessTokenResultBuilderProvider = (AppScopedAccessTokenResultBuilderProvider) AnonymousClass117.A00(313, r2);
        oVRService.mDeviceScopedAccessTokenResultBuilderProvider = (DeviceScopedAccessTokenResultBuilderProvider) AnonymousClass117.A00(295, r2);
        oVRService.mLatestAvailableAppInformationResultBuilderProvider = (LatestAvailableAppInformationResultBuilderProvider) AnonymousClass117.A00(173, r2);
        oVRService.mInstalledVRApplicationsResultBuilderProvider = (InstalledVRApplicationsResultBuilderProvider) AnonymousClass117.A00(493, r2);
        oVRService.mLaunchOtherAppResultBuilderProvider = (LaunchOtherAppResultBuilderProvider) AnonymousClass117.A00(72, r2);
        oVRService.mGetCloudStorage2UserDirectoryPathResultBuilderProvider = (GetCloudStorage2UserDirectoryPathResultBuilderProvider) AnonymousClass117.A00(185, r2);
        oVRService.mRegisterProcessTokenResultBuilderProvider = (RegisterProcessTokenResultBuilderProvider) AnonymousClass117.A00(13, r2);
        oVRService.mIAPCheckoutFlowResultBuilderProvider = (IAPCheckoutFlowResultBuilderProvider) AnonymousClass117.A00(490, r2);
        oVRService.mDurableIAPResultBuilderProvider = (DurableIAPResultBuilderProvider) AnonymousClass117.A00(196, r2);
        oVRService.mSetRichPresenceResultBuilderProvider = (SetRichPresenceResultBuilderProvider) AnonymousClass117.A00(315, r2);
        oVRService.mInvitableUsersFlowResultBuilderProvider = (InvitableUsersFlowResultBuilderProvider) AnonymousClass117.A00(95, r2);
    }

    @Override // com.oculus.horizon.platformplugin.PlatformPluginManager.Client
    public void showPartyChatResume() {
    }

    @Override // com.oculus.horizon.platformplugin.PlatformPluginManager.Client
    public void showPartyChatSuspend() {
    }

    public static class AmbiguousCallingPackageException extends RemoteException {
        public AmbiguousCallingPackageException(int i, @Nullable String str) {
            super(StringFormatUtil.formatStrLocaleSafe("Unable to lookup app id %s for ambiguous UID %d", str, Integer.valueOf(i)));
        }
    }

    public static Map<String, String> createArgsMap(Bundle bundle, Set<String> set) {
        HashMap hashMap = new HashMap();
        for (String str : bundle.keySet()) {
            Object obj = bundle.get(str);
            String str2 = null;
            if (obj != null) {
                if (set.contains(str)) {
                    str2 = AnonymousClass006.A07("<", str, ">");
                } else {
                    str2 = obj.toString();
                }
            }
            hashMap.put(str, str2);
        }
        return hashMap;
    }

    private String getCallingPackageForLogging(Bundle bundle) {
        try {
            return this.mCallerUtils.getCallingPackage(bundle);
        } catch (AmbiguousCallingPackageException e) {
            AnonymousClass0NO.A07(OVRService.class, e, "Unable to determine package for calling identity.", new Object[0]);
            return "";
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void logPermissionDenied(Bundle bundle, String str, String str2) {
        AnonymousClass0NO.A06(OVRService.class, "Calling package does not have mic permissions: %s", str);
        PermissionDeniedForJson permissionDeniedForJson = new PermissionDeniedForJson(bundle, str, str2, OVRService.class);
        String string = bundle.getString("app_id");
        ServiceLogger serviceLogger = this.mLogger;
        if (string == null) {
            string = "0";
        }
        serviceLogger.reportPlatformSDKLogs(string, str, sGson.A06(permissionDeniedForJson));
    }

    @Deprecated
    private void throwIfNotLoggedIn() throws UserNotLoggedInException {
        if (this.mCredentialsProvider.get() == null) {
            throw new UserNotLoggedInException("Oculus user login is required.");
        }
    }

    public void logErrorBundle(Bundle bundle, Bundle bundle2, Class cls) {
        this.mLogger.reportPlatformSDKLogs(bundle.getString("app_id", "0"), getCallingPackageForLogging(bundle), sGson.A06(new ErrorForJson(bundle, bundle2, cls)));
    }

    public void logRequestBundle(Bundle bundle, Class cls) {
        this.mLogger.reportPlatformSDKLogs(bundle.getString("app_id", "0"), getCallingPackageForLogging(bundle), sGson.A06(new RequestForJson(bundle, cls)));
    }

    public void logResponseBundle(Bundle bundle, Bundle bundle2, long j, Class cls) {
        this.mLogger.reportPlatformSDKLogs(bundle.getString("app_id", "0"), getCallingPackageForLogging(bundle), sGson.A06(new ResponseForJson(bundle, bundle2, j, cls)));
    }

    @Nullable
    public Bundle maybeGetErrorBundle(@Nullable Bundle bundle) {
        try {
            this.mCallerUtils.getCallingPackage(bundle);
            if (this.mCredentialsProvider.get() == null) {
                return Util.makeErrorResult(null, "user not logged in", OVRError.AUTHENTICATION_ERROR.mCode);
            }
            return null;
        } catch (AmbiguousCallingPackageException e) {
            return Util.makeErrorResult(e, "unable to determine calling package", OVRError.PERMISSIONS_FAILURE.mCode);
        }
    }

    public static final void _UL_injectMe(Context context, OVRService oVRService) {
        _UL_staticInjectMe((AbstractC06640p5) AnonymousClass0J2.get(context), oVRService);
    }

    public static /* synthetic */ Class access$200() {
        return OVRService.class;
    }

    @Deprecated
    private void throwIfCallerNotSigned() throws CallerNotSignedException {
        try {
            SignatureChecker.throwIfCallerNotSigned(getBaseContext());
        } catch (RemoteException e) {
            throw new CallerNotSignedException(e.getMessage());
        }
    }

    @Override // X.AnonymousClass1U9
    public IBinder doBind(Intent intent) {
        SignatureChecker.clear();
        return this.mBinder;
    }

    @Override // X.AnonymousClass1U9, com.oculus.security.basecomponent.OculusPublicService
    public void doCreate() {
        super.doCreate();
        _UL_injectMe((Context) this, this);
        Context applicationContext = getApplicationContext();
        AnonymousClass0i9 r1 = this.mBroadcastReceiver;
        applicationContext.registerReceiver(r1, r1.getIntentFilter());
        ProcessTokenTracker processTokenTracker = this.mProcessTokenTracker;
        processTokenTracker.mProcessListeners.add(this.mCloudStorageManager);
        ProcessTokenTracker processTokenTracker2 = this.mProcessTokenTracker;
        processTokenTracker2.mProcessListeners.add(this.mPresenceManager);
        this.mUsePartyService = SocialPlatformVersionUtil.A02(getApplicationContext());
        getApplicationContext();
        SocialPlatformVersionUtil.A01(new SocialPlatformVersionUtil.SubscribeCallback() {
            /* class com.oculus.horizon.service.OVRService.AnonymousClass3 */

            @Override // com.oculus.socialplatform.util.SocialPlatformVersionUtil.SubscribeCallback
            public void onSubscribed(boolean z) {
                OVRService.this.mUsePartyService = z;
            }
        });
    }

    @Override // X.AnonymousClass1U9
    public void doDestroy() {
        getApplicationContext().unregisterReceiver(this.mBroadcastReceiver);
        ProcessTokenTracker processTokenTracker = this.mProcessTokenTracker;
        Set<String> keySet = processTokenTracker.mTokenRegistry.keySet();
        for (String str : (String[]) keySet.toArray(new String[keySet.size()])) {
            Map<Integer, ProcessTokenTracker.ProcessDeathRecipient> map = processTokenTracker.mTokenRegistry.get(str);
            if (map != null) {
                Collection<ProcessTokenTracker.ProcessDeathRecipient> values = map.values();
                ProcessTokenTracker.ProcessDeathRecipient[] processDeathRecipientArr = (ProcessTokenTracker.ProcessDeathRecipient[]) values.toArray(new ProcessTokenTracker.ProcessDeathRecipient[values.size()]);
                for (ProcessTokenTracker.ProcessDeathRecipient processDeathRecipient : processDeathRecipientArr) {
                    if (processDeathRecipient != null) {
                        ProcessTokenTracker.A00(processTokenTracker, processDeathRecipient.packageName, processDeathRecipient.processId);
                    }
                }
            }
        }
    }

    @Deprecated
    public void throwIfCallNotAllowed() throws UserNotLoggedInException, CallerNotSignedException {
        throwIfNotLoggedIn();
        throwIfCallerNotSigned();
    }

    public static class CallerNotSignedException extends RemoteException {
        public CallerNotSignedException(String str) {
            super(str);
        }
    }

    public static class UserNotLoggedInException extends RemoteException {
        public UserNotLoggedInException(String str) {
            super(str);
        }
    }

    public static class ResponseForJson extends BaseForJson {
        @SerializedName("elapsed_time_ms")
        public final long elapsedTimeMs;
        @SerializedName("response")
        public final Map<String, String> response;

        public ResponseForJson(Bundle bundle, Bundle bundle2, long j, Class cls) {
            super("service_response", bundle, cls);
            this.response = OVRService.createArgsMap(bundle2, OVRService.BLACKLISTED_LOGGING_KEYS);
            this.elapsedTimeMs = j;
        }
    }

    public static abstract class BaseForJson {
        @SerializedName(GraphQLParamsHelper.KEY_ARGS)
        public final Map<String, String> args;
        @SerializedName("event_type")
        public final String eventType;
        @SerializedName("result_class")
        public final String resultClass;

        public BaseForJson(String str, Bundle bundle, Class cls) {
            this.eventType = str;
            this.resultClass = cls.getSimpleName();
            this.args = OVRService.createArgsMap(bundle, OVRService.BLACKLISTED_LOGGING_KEYS);
        }
    }

    public static class RequestForJson extends BaseForJson {
        public RequestForJson(Bundle bundle, Class cls) {
            super("service_request", bundle, cls);
        }
    }
}
