package com.oculus.appmanager.info;

import X.AbstractC06640p5;
import X.AnonymousClass0QC;
import X.AnonymousClass117;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.google.common.base.Absent;
import com.google.common.base.Optional;
import com.oculus.appmanager.info.ApkUpdateInfoContract;
import com.oculus.auth.credentials.Credentials;
import com.oculus.auth.credentials.CredentialsModule;
import com.oculus.auth.util.AccessTokenUtils;
import com.oculus.libraryapi.OVRLibraryInternal;
import com.oculus.util.service.ServiceFutures;
import java.util.List;
import javax.annotation.Nullable;
import javax.inject.Provider;

@Dependencies({"_UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_appmanager_info_ApkUpdateStorage_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_time_Clock_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_util_service_ServiceFutures_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_libraryapi_OVRLibraryInternal_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_auth_credentials_Credentials_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_auth_util_AccessTokenUtils_ULSEP_BINDING_ID"})
public class InfoUtils {
    public static final String BINARY_TYPE_APK = "apk";
    public static final String BINARY_TYPE_APK_PATCH = "apk_patch";
    public static final String BINARY_TYPE_OBB = "obb";
    public static final String BINARY_TYPE_OBB_PATCH = "obb_patch";
    public static final String TAG = "com.oculus.appmanager.info.InfoUtils";
    public AnonymousClass0QC _UL_mInjectionContext;
    @Inject
    @Eager
    public final AccessTokenUtils mAccessTokenUtils;
    @Inject
    @Eager
    public final ApkUpdateStorage mApkUpdateStorage;
    @Inject
    public final Provider<Credentials> mCredentialsProvider;
    @Inject
    @Eager
    public final OVRLibraryInternal mOVRLibrary;
    @Inject
    @Eager
    public final ServiceFutures mServiceFutures;

    public static class UpdateInfoCollection {
        public final ApkUpdateInfo apk;
        public final List<ApkUpdateInfo> assets;
        @Nullable
        public final ApkUpdateInfo obb;
    }

    public static final String A00(ApkUpdateInfo apkUpdateInfo) {
        ApkUpdateInfoContract.UpdateType updateType = apkUpdateInfo.updateType;
        boolean isApk = updateType.isApk();
        boolean isPatchUpdate = updateType.isPatchUpdate();
        if (isApk) {
            if (isPatchUpdate) {
                return BINARY_TYPE_APK_PATCH;
            }
            return BINARY_TYPE_APK;
        } else if (isPatchUpdate) {
            return BINARY_TYPE_OBB_PATCH;
        } else {
            return BINARY_TYPE_OBB;
        }
    }

    @Nullable
    public final ApkUpdateInfo A01(long j) {
        Optional optional;
        ApkUpdateInfo apkUpdateInfo = this.mApkUpdateStorage.A01().get(Long.valueOf(j));
        if (apkUpdateInfo == null || apkUpdateInfo.mDeleted.get()) {
            optional = Absent.INSTANCE;
        } else {
            optional = Optional.of(apkUpdateInfo);
        }
        if (!optional.isPresent()) {
            return null;
        }
        ApkUpdateInfo apkUpdateInfo2 = (ApkUpdateInfo) optional.get();
        if (apkUpdateInfo2.A01().A00(ApkUpdateInfoContract.EXTRA_RETRIED_AS_ID, -1) == -1) {
            return apkUpdateInfo2;
        }
        long A00 = apkUpdateInfo2.A01().A00(ApkUpdateInfoContract.EXTRA_RETRIED_AS_ID, -1);
        if (A00 != -1) {
            return A01(A00);
        }
        return null;
    }

    @Inject
    public InfoUtils(AbstractC06640p5 r3) {
        this._UL_mInjectionContext = new AnonymousClass0QC(2, r3);
        this.mApkUpdateStorage = (ApkUpdateStorage) AnonymousClass117.A00(111, r3);
        this.mServiceFutures = (ServiceFutures) AnonymousClass117.A00(269, r3);
        this.mOVRLibrary = (OVRLibraryInternal) AnonymousClass117.A00(508, r3);
        this.mCredentialsProvider = CredentialsModule._UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_auth_credentials_Credentials_ULGT__ULSEP_ACCESS_METHOD(r3);
        this.mAccessTokenUtils = AccessTokenUtils._UL__ULSEP_com_oculus_auth_util_AccessTokenUtils_ULSEP_ACCESS_METHOD(r3);
    }
}
