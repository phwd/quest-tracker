package com.oculus.appmanager.installer.service;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Base64;
import androidx.annotation.VisibleForTesting;
import com.facebook.common.android.AndroidModule;
import com.facebook.debug.log.BLog;
import com.facebook.inject.FbInjector;
import com.facebook.inject.InjectionContext;
import com.facebook.inject.InjectorLike;
import com.facebook.inject.Lazy;
import com.facebook.inject.UltralightLazy;
import com.facebook.inject.UltralightProvider;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.facebook.ultralight.UL;
import com.google.common.base.Preconditions;
import com.google.common.primitives.UnsignedBytes;
import com.oculus.appmanager.info.ApkUpdateInfo;
import com.oculus.appmanager.info.ApkUpdateInfoContract;
import com.oculus.appmanager.info.InfoModule;
import com.oculus.appmanager.info.InfoUtils;
import com.oculus.appmanager.info.model.InstallerResultError;
import com.oculus.appmanager.installer.analytics.AnalyticsModule;
import com.oculus.appmanager.installer.analytics.InstallerAnalytics;
import com.oculus.appmanager.installer.common.CommonModule;
import com.oculus.appmanager.installer.common.CryptoMethods;
import com.oculus.appmanager.installer.common.EcdsaSignatureVerifier;
import com.oculus.appmanager.installer.common.InstallerFileUtils;
import com.oculus.appmanager.installer.contract.errors.ErrorCategories;
import com.oculus.appmanager.installer.service.InstallerServiceModule;
import com.oculus.appmanager.vrsign.VRSignVerifier;
import com.oculus.appmanager.vrsign.VrsignModule;
import com.oculus.device.DeviceType;
import com.oculus.errorreporting.interfaces.IErrorReporter;
import com.oculus.errorreporting.interfaces.InterfacesModule;
import com.oculus.time.Clock;
import com.oculus.time.TimeModule;
import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Locale;
import javax.inject.Provider;
import org.json.JSONArray;
import org.json.JSONException;

@Dependencies({"_UL__ULSEP_com_oculus_appmanager_vrsign_VRSignVerifier_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_appmanager_info_InfoUtils_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_appmanager_installer_common_InstallerFileUtils_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_time_Clock_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_appmanager_installer_analytics_InstallerAnalytics_ULSEP_BINDING_ID", "_UL__ULSEP_android_content_pm_PackageManager_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_appmanager_installer_service_InstallerRetryHelper_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_appmanager_installer_common_CryptoMethods_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_appmanager_installer_common_EcdsaSignatureVerifier_ULSEP_BINDING_ID"})
public class InstallerServiceVerificationHelper {
    private static final int ESIG_HEADER_PREFIX_SIZE = 1;
    private static final int ESIG_HEADER_SIZE_MIN = 1;
    private static final int ESIG_KEY_ID_SIZE = 1;
    private static final int ESIG_MIN_LEN = 3;
    private static final String SOFT_ERROR_BAD_CHECKSUM = "Bad installer checksum";
    private static final String TAG = "InstallerServiceVerificationHelper";
    private InjectionContext _UL_mInjectionContext;

    @AutoGeneratedAccessMethod
    public static final Lazy _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_appmanager_installer_service_InstallerServiceVerificationHelper_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightLazy.get(InstallerServiceModule.UL_id._UL__ULSEP_com_oculus_appmanager_installer_service_InstallerServiceVerificationHelper_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final InstallerServiceVerificationHelper _UL__ULSEP_com_oculus_appmanager_installer_service_InstallerServiceVerificationHelper_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (InstallerServiceVerificationHelper) UL.factorymap.get(InstallerServiceModule.UL_id._UL__ULSEP_com_oculus_appmanager_installer_service_InstallerServiceVerificationHelper_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedFactoryMethod
    public static final InstallerServiceVerificationHelper _UL__ULSEP_com_oculus_appmanager_installer_service_InstallerServiceVerificationHelper_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        return new InstallerServiceVerificationHelper(injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_appmanager_installer_service_InstallerServiceVerificationHelper_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightProvider.get(InstallerServiceModule.UL_id._UL__ULSEP_com_oculus_appmanager_installer_service_InstallerServiceVerificationHelper_ULSEP_BINDING_ID, injectorLike);
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public static class HandledVerificationException extends Exception {
        HandledVerificationException(Throwable th) {
            super(th);
        }

        HandledVerificationException(String str) {
            super(str);
        }
    }

    @Inject
    public InstallerServiceVerificationHelper(InjectorLike injectorLike) {
        this._UL_mInjectionContext = new InjectionContext(9, injectorLike);
    }

    /* access modifiers changed from: package-private */
    public void verifyDownload(ApkUpdateInfo apkUpdateInfo, boolean z) {
        BLog.d(TAG, "verifyDownload(%s)", apkUpdateInfo);
        long now = ((Clock) FbInjector.lazyInstance(2, TimeModule.UL_id._UL__ULSEP_com_oculus_time_Clock_ULSEP_BINDING_ID, this._UL_mInjectionContext)).now();
        apkUpdateInfo.edit().putState(ApkUpdateInfoContract.UpdateState.VERIFYING, now).putVerificationStartTimeMs(now).save();
        try {
            maybeVerifyChecksum(apkUpdateInfo);
            maybeVerifyInstalledVersion(apkUpdateInfo);
            maybePerformPatch(apkUpdateInfo);
            if (apkUpdateInfo.updateType.isApk()) {
                moveToInternalStorage(apkUpdateInfo, z);
                maybeProcessMetadata(apkUpdateInfo);
                verifySandboxedFile(apkUpdateInfo);
            }
            apkUpdateInfo.edit().putState(ApkUpdateInfoContract.UpdateState.VERIFIED, ((Clock) FbInjector.lazyInstance(2, TimeModule.UL_id._UL__ULSEP_com_oculus_time_Clock_ULSEP_BINDING_ID, this._UL_mInjectionContext)).now()).putVerificationDurationMs(((Clock) FbInjector.lazyInstance(2, TimeModule.UL_id._UL__ULSEP_com_oculus_time_Clock_ULSEP_BINDING_ID, this._UL_mInjectionContext)).now() - now).save();
        } catch (HandledVerificationException unused) {
        } catch (Exception e) {
            onException(apkUpdateInfo, e);
        }
    }

    private void maybeVerifyChecksum(ApkUpdateInfo apkUpdateInfo) throws HandledVerificationException {
        if (!TextUtils.isEmpty(apkUpdateInfo.checksumHash)) {
            String downloadedFileHash = ((InstallerFileUtils) FbInjector.lazyInstance(1, CommonModule.UL_id._UL__ULSEP_com_oculus_appmanager_installer_common_InstallerFileUtils_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getDownloadedFileHash(apkUpdateInfo, TextUtils.isEmpty(apkUpdateInfo.checksumHashAlg) ? "MD5" : apkUpdateInfo.checksumHashAlg);
            if (!apkUpdateInfo.checksumHash.equalsIgnoreCase(downloadedFileHash)) {
                ((IErrorReporter) FbInjector.lazyInstance(6, InterfacesModule.UL_id._UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID, this._UL_mInjectionContext)).softError(SOFT_ERROR_BAD_CHECKSUM, String.format(Locale.US, "Expected checksum %s but got %s for %s %s %s -> %s", apkUpdateInfo.checksumHash, downloadedFileHash, apkUpdateInfo.identifier, apkUpdateInfo.updateType.name(), Long.valueOf(apkUpdateInfo.baseVersion), Long.valueOf(apkUpdateInfo.targetVersion)));
                if (((InstallerRetryHelper) FbInjector.lazyInstance(5, InstallerServiceModule.UL_id._UL__ULSEP_com_oculus_appmanager_installer_service_InstallerRetryHelper_ULSEP_BINDING_ID, this._UL_mInjectionContext)).maybeRetryForFullVersion(apkUpdateInfo) != -1) {
                    throw new HandledVerificationException("retried update due to bad download checksum");
                }
                ((InfoUtils) FbInjector.lazyInstance(0, InfoModule.UL_id._UL__ULSEP_com_oculus_appmanager_info_InfoUtils_ULSEP_BINDING_ID, this._UL_mInjectionContext)).failSoft(apkUpdateInfo, ErrorCategories.VERIFICATION_CHECKSUM_FAILED, "Checksum failed for downloaded file", InstallerResultError.VERIFICATION_FAILURE, null);
                throw new HandledVerificationException("Checksum failed for downloaded file");
            }
            BLog.i(TAG, "Checksum verified for %s", apkUpdateInfo);
            return;
        }
        BLog.w(TAG, "Skipping checksum check for %s, none was provided", apkUpdateInfo.identifier);
    }

    private void maybeVerifyInstalledVersion(ApkUpdateInfo apkUpdateInfo) throws HandledVerificationException {
        String packageName = apkUpdateInfo.getPackageName();
        if (apkUpdateInfo.isUpdate && !TextUtils.isEmpty(packageName)) {
            try {
                if (((long) ((PackageManager) FbInjector.lazyInstance(4, AndroidModule.UL_id._UL__ULSEP_android_content_pm_PackageManager_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getPackageInfo(packageName, 0).versionCode) != apkUpdateInfo.baseVersion) {
                    ((InstallerAnalytics) FbInjector.lazyInstance(3, AnalyticsModule.UL_id._UL__ULSEP_com_oculus_appmanager_installer_analytics_InstallerAnalytics_ULSEP_BINDING_ID, this._UL_mInjectionContext)).reportInstallFunnelBaseVersionChanged(apkUpdateInfo);
                    ((InfoUtils) FbInjector.lazyInstance(0, InfoModule.UL_id._UL__ULSEP_com_oculus_appmanager_info_InfoUtils_ULSEP_BINDING_ID, this._UL_mInjectionContext)).failQuiet(apkUpdateInfo, ErrorCategories.VERIFICATION_BASE_VERSION_CHANGED, "Expected different version to be installed", InstallerResultError.UNEXPECTED_CHANGE, null);
                    throw new HandledVerificationException("Expected different version to be installed");
                }
            } catch (PackageManager.NameNotFoundException unused) {
                BLog.w(TAG, "Expected app to be installed: %s", packageName);
                if (apkUpdateInfo.updateType.isPatchUpdate()) {
                    ((InfoUtils) FbInjector.lazyInstance(0, InfoModule.UL_id._UL__ULSEP_com_oculus_appmanager_info_InfoUtils_ULSEP_BINDING_ID, this._UL_mInjectionContext)).failQuiet(apkUpdateInfo, ErrorCategories.VERIFICATION_BASE_VERSION_CHANGED, "Expected app to be installed for patch update", InstallerResultError.UNEXPECTED_CHANGE, null);
                    throw new HandledVerificationException("Expected app to be installed for patch update");
                }
            }
        }
    }

    private void maybePerformPatch(ApkUpdateInfo apkUpdateInfo) throws HandledVerificationException {
        try {
            String applyPatchIfNecessary = ((InstallerFileUtils) FbInjector.lazyInstance(1, CommonModule.UL_id._UL__ULSEP_com_oculus_appmanager_installer_common_InstallerFileUtils_ULSEP_BINDING_ID, this._UL_mInjectionContext)).applyPatchIfNecessary(apkUpdateInfo);
            apkUpdateInfo.edit().putFinalPatchedExternalFile(applyPatchIfNecessary).putUpdatePayloadSize(((InstallerFileUtils) FbInjector.lazyInstance(1, CommonModule.UL_id._UL__ULSEP_com_oculus_appmanager_installer_common_InstallerFileUtils_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getFileSize(applyPatchIfNecessary)).save();
        } catch (IOException e) {
            onException(apkUpdateInfo, e);
            throw new HandledVerificationException(e);
        } catch (RuntimeException e2) {
            boolean z = false;
            BLog.e(TAG, e2, "Exception thrown when trying to verify %s", apkUpdateInfo);
            if (e2.getMessage().contains("rs_result") && ((InstallerRetryHelper) FbInjector.lazyInstance(5, InstallerServiceModule.UL_id._UL__ULSEP_com_oculus_appmanager_installer_service_InstallerRetryHelper_ULSEP_BINDING_ID, this._UL_mInjectionContext)).maybeRetryForFullVersion(apkUpdateInfo) != -1) {
                z = true;
            }
            if (!z) {
                onException(apkUpdateInfo, e2);
            } else {
                BLog.i(TAG, "retry successful");
            }
            throw new HandledVerificationException(e2);
        }
    }

    private void moveToInternalStorage(ApkUpdateInfo apkUpdateInfo, boolean z) throws HandledVerificationException, IOException {
        String moveApkToInternalStorage = ((InstallerFileUtils) FbInjector.lazyInstance(1, CommonModule.UL_id._UL__ULSEP_com_oculus_appmanager_installer_common_InstallerFileUtils_ULSEP_BINDING_ID, this._UL_mInjectionContext)).moveApkToInternalStorage(apkUpdateInfo, z);
        if (moveApkToInternalStorage != null) {
            apkUpdateInfo.edit().putSandboxedFile(moveApkToInternalStorage).putUpdatePayloadSize(((InstallerFileUtils) FbInjector.lazyInstance(1, CommonModule.UL_id._UL__ULSEP_com_oculus_appmanager_installer_common_InstallerFileUtils_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getFileSize(moveApkToInternalStorage)).save();
            return;
        }
        throw new HandledVerificationException("failed to move file to internal storage");
    }

    private void maybeProcessMetadata(ApkUpdateInfo apkUpdateInfo) throws HandledVerificationException {
        if (TextUtils.isEmpty(apkUpdateInfo.getPackageName()) && apkUpdateInfo.updateType.isApk() && apkUpdateInfo.updateType.isFullUpdate()) {
            PackageInfo packageArchiveInfo = ((PackageManager) FbInjector.lazyInstance(4, AndroidModule.UL_id._UL__ULSEP_android_content_pm_PackageManager_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getPackageArchiveInfo(apkUpdateInfo.getExtras().getSandboxedFilePath(), 64);
            if (packageArchiveInfo == null || TextUtils.isEmpty(packageArchiveInfo.packageName)) {
                ((InfoUtils) FbInjector.lazyInstance(0, InfoModule.UL_id._UL__ULSEP_com_oculus_appmanager_info_InfoUtils_ULSEP_BINDING_ID, this._UL_mInjectionContext)).failQuiet(apkUpdateInfo, ErrorCategories.VERIFICATION_METADATA_FAILED, "unable to determine apk package name", InstallerResultError.VERIFICATION_FAILURE, null);
                throw new HandledVerificationException("unable to determine apk package name");
            }
            for (ApkUpdateInfo apkUpdateInfo2 : ((InfoUtils) FbInjector.lazyInstance(0, InfoModule.UL_id._UL__ULSEP_com_oculus_appmanager_info_InfoUtils_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getAllUpdatesInTree(apkUpdateInfo)) {
                apkUpdateInfo2.edit().putPackageName(packageArchiveInfo.packageName).putParsedVersionCode((long) packageArchiveInfo.versionCode).save();
            }
            BLog.i(TAG, "Successfully set package name to %s for install %s", packageArchiveInfo.packageName, apkUpdateInfo);
        }
    }

    private void verifySandboxedFile(ApkUpdateInfo apkUpdateInfo) throws IOException, GeneralSecurityException, HandledVerificationException {
        if (DeviceType.is6DOF()) {
            if (apkUpdateInfo.updateType.isFromStore()) {
                verifyEsigOrThrow(apkUpdateInfo);
            }
            BLog.d(TAG, "esig verified for %s", apkUpdateInfo);
        } else {
            verifyOsigOrThrow(apkUpdateInfo);
            BLog.d(TAG, "osig verified for %s", apkUpdateInfo);
        }
        verifyApkSignature(apkUpdateInfo);
    }

    public void verifyOsigOrThrow(ApkUpdateInfo apkUpdateInfo) throws IOException {
        Preconditions.checkArgument(apkUpdateInfo.getExtras().hasKey(ApkUpdateInfoContract.EXTRA_SANDBOXED_FILE));
        String sandboxedFilePath = apkUpdateInfo.getExtras().getSandboxedFilePath();
        if (!((VRSignVerifier) FbInjector.localInstance(VrsignModule.UL_id._UL__ULSEP_com_oculus_appmanager_vrsign_VRSignVerifier_ULSEP_BINDING_ID, this._UL_mInjectionContext)).verifyVRSig(sandboxedFilePath)) {
            throw new SecurityException("APK " + sandboxedFilePath + " failed vr sig verification");
        }
    }

    public void verifyEsigOrThrow(ApkUpdateInfo apkUpdateInfo) throws IOException, SignatureException, InvalidKeySpecException, NoSuchAlgorithmException, InvalidKeyException, HandledVerificationException {
        Preconditions.checkArgument(apkUpdateInfo.getExtras().hasKey(ApkUpdateInfoContract.EXTRA_SANDBOXED_FILE));
        try {
            String string = new JSONArray(apkUpdateInfo.externalSignatures).getString(0);
            if (!TextUtils.isEmpty(string)) {
                byte[] decode = Base64.decode(string, 0);
                if (decode.length >= 3) {
                    int i = UnsignedBytes.toInt(decode[0]) + 1 + 1;
                    if (decode.length >= i) {
                        if (((EcdsaSignatureVerifier) FbInjector.lazyInstance(8, CommonModule.UL_id._UL__ULSEP_com_oculus_appmanager_installer_common_EcdsaSignatureVerifier_ULSEP_BINDING_ID, this._UL_mInjectionContext)).verifySignature(((CryptoMethods) FbInjector.lazyInstance(7, CommonModule.UL_id._UL__ULSEP_com_oculus_appmanager_installer_common_CryptoMethods_ULSEP_BINDING_ID, this._UL_mInjectionContext)).generateDigestFromFile(new File(apkUpdateInfo.getExtras().getSandboxedFilePath()), "SHA-256"), Arrays.copyOfRange(decode, i, decode.length))) {
                            return;
                        }
                        if (((InstallerRetryHelper) FbInjector.lazyInstance(5, InstallerServiceModule.UL_id._UL__ULSEP_com_oculus_appmanager_installer_service_InstallerRetryHelper_ULSEP_BINDING_ID, this._UL_mInjectionContext)).maybeRetryForFullVersion(apkUpdateInfo) != -1) {
                            throw new HandledVerificationException("esig signature verification error");
                        }
                        throw new SecurityException("esig signature verification error");
                    }
                    throw new IllegalArgumentException("esig too short");
                }
                throw new IllegalArgumentException("esig too short to fetch length");
            }
            throw new IllegalArgumentException("esig is empty");
        } catch (JSONException e) {
            throw new IllegalArgumentException("Unable to parse esig json", e);
        }
    }

    @VisibleForTesting
    public void verifyApkSignature(ApkUpdateInfo apkUpdateInfo) throws HandledVerificationException {
        if (!TextUtils.isEmpty(apkUpdateInfo.expectedSignature)) {
            String sandboxedFilePath = apkUpdateInfo.getExtras().getSandboxedFilePath();
            PackageInfo packageArchiveInfo = ((PackageManager) FbInjector.lazyInstance(4, AndroidModule.UL_id._UL__ULSEP_android_content_pm_PackageManager_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getPackageArchiveInfo(sandboxedFilePath, 64);
            if (packageArchiveInfo == null) {
                String str = "could not get packageInfo for apk: " + sandboxedFilePath;
                onSignatureVerificationFailure(apkUpdateInfo, str, "");
                throw new HandledVerificationException(str);
            } else if (packageArchiveInfo.signatures.length == 1) {
                String makeSHA1HashBase64 = ((CryptoMethods) FbInjector.lazyInstance(7, CommonModule.UL_id._UL__ULSEP_com_oculus_appmanager_installer_common_CryptoMethods_ULSEP_BINDING_ID, this._UL_mInjectionContext)).makeSHA1HashBase64(packageArchiveInfo.signatures[0].toByteArray());
                if (apkUpdateInfo.expectedSignature.equals(makeSHA1HashBase64)) {
                    BLog.d(TAG, "signature verification succeeded for %s", apkUpdateInfo);
                    return;
                }
                String str2 = "Signature mismatch for apk: " + sandboxedFilePath;
                onSignatureVerificationFailure(apkUpdateInfo, str2, makeSHA1HashBase64);
                throw new HandledVerificationException(str2);
            } else {
                String str3 = "unexpected number of signatures for apk: " + sandboxedFilePath;
                onSignatureVerificationFailure(apkUpdateInfo, str3, "");
                throw new HandledVerificationException(str3);
            }
        }
    }

    private void onSignatureVerificationFailure(ApkUpdateInfo apkUpdateInfo, String str, String str2) {
        ((InstallerAnalytics) FbInjector.lazyInstance(3, AnalyticsModule.UL_id._UL__ULSEP_com_oculus_appmanager_installer_analytics_InstallerAnalytics_ULSEP_BINDING_ID, this._UL_mInjectionContext)).reportSignatureVerificationFailed(apkUpdateInfo, str2);
        ((InstallerAnalytics) FbInjector.lazyInstance(3, AnalyticsModule.UL_id._UL__ULSEP_com_oculus_appmanager_installer_analytics_InstallerAnalytics_ULSEP_BINDING_ID, this._UL_mInjectionContext)).reportInstallFunnelSignatureVerificationFailed(apkUpdateInfo);
        ((IErrorReporter) FbInjector.lazyInstance(6, InterfacesModule.UL_id._UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID, this._UL_mInjectionContext)).softError(ErrorCategories.VERIFICATION_SIGNATURE_FAILED, str);
        if (((InstallerRetryHelper) FbInjector.lazyInstance(5, InstallerServiceModule.UL_id._UL__ULSEP_com_oculus_appmanager_installer_service_InstallerRetryHelper_ULSEP_BINDING_ID, this._UL_mInjectionContext)).maybeRetryForFullVersion(apkUpdateInfo) == -1) {
            ((InfoUtils) FbInjector.lazyInstance(0, InfoModule.UL_id._UL__ULSEP_com_oculus_appmanager_info_InfoUtils_ULSEP_BINDING_ID, this._UL_mInjectionContext)).failQuiet(apkUpdateInfo, ErrorCategories.VERIFICATION_SIGNATURE_FAILED, str, InstallerResultError.VERIFICATION_FAILURE, null);
        }
    }

    private void onException(ApkUpdateInfo apkUpdateInfo, Exception exc) {
        ((InfoUtils) FbInjector.lazyInstance(0, InfoModule.UL_id._UL__ULSEP_com_oculus_appmanager_info_InfoUtils_ULSEP_BINDING_ID, this._UL_mInjectionContext)).failQuiet(apkUpdateInfo, ErrorCategories.VERIFICATION_FAILED, exc.getClass().getSimpleName() + ": " + exc.getMessage(), InstallerResultError.VERIFICATION_FAILURE, exc);
    }
}
