package com.oculus.appmanager.info;

import android.content.Intent;
import com.facebook.common.util.exception.ExceptionUtil;
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
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.facebook.ultralight.UL;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.util.concurrent.SettableFuture;
import com.oculus.appmanager.info.ApkUpdateInfo;
import com.oculus.appmanager.info.ApkUpdateInfoContract;
import com.oculus.appmanager.info.InfoModule;
import com.oculus.appmanager.info.model.InstallerResult;
import com.oculus.appmanager.info.model.InstallerResultError;
import com.oculus.appmanager.info.model.RequestOrigin;
import com.oculus.appmanager.installer.contract.errors.ErrorCategories;
import com.oculus.auth.credentials.Credentials;
import com.oculus.auth.credentials.CredentialsModule;
import com.oculus.auth.util.AccessTokenUtils;
import com.oculus.errorreporting.interfaces.IErrorReporter;
import com.oculus.errorreporting.interfaces.InterfacesModule;
import com.oculus.extras.Extras;
import com.oculus.libraryapi.OVRLibraryInternal;
import com.oculus.libraryapi.OVRLibraryModule;
import com.oculus.time.Clock;
import com.oculus.time.TimeModule;
import com.oculus.util.service.ServiceFutures;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Set;
import javax.annotation.Nullable;
import javax.inject.Provider;

@Dependencies({"_UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_appmanager_info_ApkUpdateStorage_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_time_Clock_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_util_service_ServiceFutures_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_libraryapi_OVRLibraryInternal_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_auth_credentials_Credentials_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_auth_util_AccessTokenUtils_ULSEP_BINDING_ID"})
public class InfoUtils {
    public static final String BINARY_TYPE_APK = "apk";
    public static final String BINARY_TYPE_APK_PATCH = "apk_patch";
    public static final String BINARY_TYPE_OBB = "obb";
    public static final String BINARY_TYPE_OBB_PATCH = "obb_patch";
    public static final String TAG = "com.oculus.appmanager.info.InfoUtils";
    private InjectionContext _UL_mInjectionContext;
    @Inject
    @Eager
    private final AccessTokenUtils mAccessTokenUtils;
    @Inject
    @Eager
    private final ApkUpdateStorage mApkUpdateStorage;
    @Inject
    private final Provider<Credentials> mCredentialsProvider;
    @Inject
    @Eager
    private final OVRLibraryInternal mOVRLibrary;
    @Inject
    @Eager
    private final ServiceFutures mServiceFutures;

    public boolean isValidId(long j) {
        return j != -1;
    }

    @AutoGeneratedAccessMethod
    public static final Lazy _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_appmanager_info_InfoUtils_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightLazy.get(InfoModule.UL_id._UL__ULSEP_com_oculus_appmanager_info_InfoUtils_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final InfoUtils _UL__ULSEP_com_oculus_appmanager_info_InfoUtils_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (InfoUtils) UL.factorymap.get(InfoModule.UL_id._UL__ULSEP_com_oculus_appmanager_info_InfoUtils_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedFactoryMethod
    public static final InfoUtils _UL__ULSEP_com_oculus_appmanager_info_InfoUtils_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        return new InfoUtils(injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_appmanager_info_InfoUtils_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightProvider.get(InfoModule.UL_id._UL__ULSEP_com_oculus_appmanager_info_InfoUtils_ULSEP_BINDING_ID, injectorLike);
    }

    public static class UpdateInfoCollection {
        public final ApkUpdateInfo apk;
        public final List<ApkUpdateInfo> assets;
        @Nullable
        public final ApkUpdateInfo obb;

        public UpdateInfoCollection(ApkUpdateInfo apkUpdateInfo, ApkUpdateInfo apkUpdateInfo2, List<ApkUpdateInfo> list) {
            this.apk = apkUpdateInfo;
            this.obb = apkUpdateInfo2;
            this.assets = list;
        }
    }

    @Inject
    public InfoUtils(InjectorLike injectorLike) {
        this._UL_mInjectionContext = new InjectionContext(2, injectorLike);
        this.mApkUpdateStorage = ApkUpdateStorage._UL__ULSEP_com_oculus_appmanager_info_ApkUpdateStorage_ULSEP_ACCESS_METHOD(injectorLike);
        this.mServiceFutures = ServiceFutures._UL__ULSEP_com_oculus_util_service_ServiceFutures_ULSEP_ACCESS_METHOD(injectorLike);
        this.mOVRLibrary = OVRLibraryModule._UL__ULSEP_com_oculus_libraryapi_OVRLibraryInternal_ULSEP_ACCESS_METHOD(injectorLike);
        this.mCredentialsProvider = CredentialsModule._UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_auth_credentials_Credentials_ULGT__ULSEP_ACCESS_METHOD(injectorLike);
        this.mAccessTokenUtils = AccessTokenUtils._UL__ULSEP_com_oculus_auth_util_AccessTokenUtils_ULSEP_ACCESS_METHOD(injectorLike);
    }

    @Nullable
    public ApkUpdateInfo getUpdate(long j) {
        return getUpdate(j, true);
    }

    @Nullable
    public ApkUpdateInfo getUpdate(long j, boolean z) {
        Optional<ApkUpdateInfo> findUpdateById = this.mApkUpdateStorage.findUpdateById(j);
        if (!findUpdateById.isPresent()) {
            return null;
        }
        ApkUpdateInfo apkUpdateInfo = findUpdateById.get();
        return (z && isValidId(apkUpdateInfo.getExtras().getRetriedUpdate())) ? getRetriedUpdate(apkUpdateInfo) : apkUpdateInfo;
    }

    @Nullable
    public ApkUpdateInfo getRetriedUpdate(ApkUpdateInfo apkUpdateInfo) {
        long retriedUpdate = apkUpdateInfo.getExtras().getRetriedUpdate();
        if (!isValidId(retriedUpdate)) {
            return null;
        }
        return getUpdate(retriedUpdate);
    }

    public ImmutableList<ApkUpdateInfo> getAllUpdates() {
        return this.mApkUpdateStorage.getAllUpdates();
    }

    public ApkUpdateInfo addUpdate(String str, ApkUpdateInfoContract.UpdateType updateType, long j, long j2, boolean z, long j3, @Nullable String str2, RequestOrigin requestOrigin, @Nullable ImmutableList<Long> immutableList, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable String str7, @Nullable String str8, @Nullable String str9, @Nullable String str10, @Nullable Extras extras, @Nullable ImmutableMap<String, String> immutableMap) {
        return this.mApkUpdateStorage.addUpdate(str, updateType, j, j2, z, j3, str2, requestOrigin, immutableList, str3, str4, str5, str6, str7, str8, str9, str10, extras, immutableMap);
    }

    public void deleteUpdate(long j) {
        this.mApkUpdateStorage.deleteUpdate(j);
    }

    public List<ApkUpdateInfo> findUpdates(Predicate<ApkUpdateInfo> predicate) {
        return this.mApkUpdateStorage.findUpdates(predicate);
    }

    @Nullable
    public ApkUpdateInfo findFirstUpdate(Predicate<ApkUpdateInfo> predicate) {
        return this.mApkUpdateStorage.findFirstUpdate(predicate);
    }

    @Nullable
    public ApkUpdateInfo getParentUpdate(ApkUpdateInfo apkUpdateInfo) {
        long dependentStartedBy = apkUpdateInfo.getExtras().getDependentStartedBy();
        if (!isValidId(dependentStartedBy)) {
            return null;
        }
        ApkUpdateInfo update = getUpdate(dependentStartedBy);
        if (update != null) {
            return update;
        }
        String format = String.format(Locale.US, "cannot find parent update with id %d", Long.valueOf(dependentStartedBy));
        BLog.w(TAG, format);
        ((IErrorReporter) FbInjector.lazyInstance(0, InterfacesModule.UL_id._UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID, this._UL_mInjectionContext)).softError(ErrorCategories.UTIL_UPDATEINFO_NOT_FOUND_IN_STORAGE, format);
        return null;
    }

    public ApkUpdateInfo getRootUpdate(ApkUpdateInfo apkUpdateInfo) {
        while (true) {
            ApkUpdateInfo parentUpdate = getParentUpdate(apkUpdateInfo);
            if (parentUpdate == null) {
                return apkUpdateInfo;
            }
            if (!hasParentUpdate(parentUpdate)) {
                return parentUpdate;
            }
            apkUpdateInfo = parentUpdate;
        }
    }

    public Set<ApkUpdateInfo> getAllUpdatesInTree(ApkUpdateInfo apkUpdateInfo) {
        ApkUpdateInfo rootUpdate = getRootUpdate(apkUpdateInfo);
        Set<ApkUpdateInfo> allUpdatesInTreeImpl = getAllUpdatesInTreeImpl(rootUpdate);
        allUpdatesInTreeImpl.add(rootUpdate);
        return allUpdatesInTreeImpl;
    }

    private Set<ApkUpdateInfo> getAllUpdatesInTreeImpl(ApkUpdateInfo apkUpdateInfo) {
        List<ApkUpdateInfo> dependencies = getDependencies(apkUpdateInfo);
        HashSet hashSet = new HashSet(dependencies);
        for (ApkUpdateInfo apkUpdateInfo2 : dependencies) {
            hashSet.addAll(getAllUpdatesInTreeImpl(apkUpdateInfo2));
        }
        return hashSet;
    }

    public boolean hasParentUpdate(ApkUpdateInfo apkUpdateInfo) {
        return apkUpdateInfo.getExtras().hasKey(ApkUpdateInfoContract.EXTRA_DEPENDENT_START_BY);
    }

    public boolean isRetry(ApkUpdateInfo apkUpdateInfo) {
        return apkUpdateInfo.getExtras().hasKey(ApkUpdateInfoContract.EXTRA_IS_RETRY_OF);
    }

    public boolean isRoot(ApkUpdateInfo apkUpdateInfo) {
        return !apkUpdateInfo.getExtras().hasKey(ApkUpdateInfoContract.EXTRA_DEPENDENT_START_BY);
    }

    @Nullable
    public ApkUpdateInfo getFromIntent(Intent intent, String str) {
        if (intent.hasExtra(str)) {
            return getUpdate(intent.getLongExtra(str, -1));
        }
        BLog.w(TAG, "Intent is missing update id in extras");
        ((IErrorReporter) FbInjector.lazyInstance(0, InterfacesModule.UL_id._UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID, this._UL_mInjectionContext)).softError(ErrorCategories.UTIL_INTENT_NO_UPDATE_ID, "Intent is missing update id in extras");
        return null;
    }

    @Nullable
    public ApkUpdateInfo getFromExtras(Extras extras, String str) {
        long j = extras.getLong(str, -1);
        if (j != -1) {
            return getUpdate(j);
        }
        BLog.w(TAG, "Extra is missing update id");
        if (new Random().nextInt(1000) != 0) {
            return null;
        }
        ((IErrorReporter) FbInjector.lazyInstance(0, InterfacesModule.UL_id._UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID, this._UL_mInjectionContext)).softError(ErrorCategories.UTIL_EXTRA_NO_UPDATE_ID, "Extra is missing update id");
        return null;
    }

    public List<ApkUpdateInfo> getDependencies(ApkUpdateInfo apkUpdateInfo) {
        ArrayList arrayList = new ArrayList(apkUpdateInfo.dependencies.size());
        UnmodifiableIterator<Long> it = apkUpdateInfo.dependencies.iterator();
        while (it.hasNext()) {
            Long next = it.next();
            ApkUpdateInfo update = getUpdate(next.longValue());
            if (update == null) {
                BLog.w(TAG, "Unable to find dependency with id %d. Ignoring...", next);
            } else {
                arrayList.add(update);
            }
        }
        return arrayList;
    }

    public List<ApkUpdateInfo> searchUpdateAndDependencies(ApkUpdateInfo apkUpdateInfo, Predicate<ApkUpdateInfo> predicate) {
        return searchDependencyTreeImpl(apkUpdateInfo, predicate);
    }

    public List<ApkUpdateInfo> searchEntireDependencyTree(ApkUpdateInfo apkUpdateInfo, Predicate<ApkUpdateInfo> predicate) {
        return searchDependencyTreeImpl(getRootUpdate(apkUpdateInfo), predicate);
    }

    private List<ApkUpdateInfo> searchDependencyTreeImpl(ApkUpdateInfo apkUpdateInfo, Predicate<ApkUpdateInfo> predicate) {
        ArrayList arrayList = new ArrayList();
        if (predicate.apply(apkUpdateInfo)) {
            arrayList.add(apkUpdateInfo);
        }
        for (ApkUpdateInfo apkUpdateInfo2 : getDependencies(apkUpdateInfo)) {
            arrayList.addAll(searchDependencyTreeImpl(apkUpdateInfo2, predicate));
        }
        return arrayList;
    }

    @Nullable
    public ApkUpdateInfo searchFirstInDependencyTree(ApkUpdateInfo apkUpdateInfo, Predicate<ApkUpdateInfo> predicate) {
        return searchFirstInDependencyTreeImpl(getRootUpdate(apkUpdateInfo), predicate);
    }

    @Nullable
    private ApkUpdateInfo searchFirstInDependencyTreeImpl(ApkUpdateInfo apkUpdateInfo, Predicate<ApkUpdateInfo> predicate) {
        if (predicate.apply(apkUpdateInfo)) {
            return apkUpdateInfo;
        }
        for (ApkUpdateInfo apkUpdateInfo2 : getDependencies(apkUpdateInfo)) {
            ApkUpdateInfo searchFirstInDependencyTreeImpl = searchFirstInDependencyTreeImpl(apkUpdateInfo2, predicate);
            if (searchFirstInDependencyTreeImpl != null) {
                return searchFirstInDependencyTreeImpl;
            }
        }
        return null;
    }

    public String getBinaryType(ApkUpdateInfo apkUpdateInfo) {
        return apkUpdateInfo.updateType.isApk() ? apkUpdateInfo.updateType.isPatchUpdate() ? BINARY_TYPE_APK_PATCH : BINARY_TYPE_APK : apkUpdateInfo.updateType.isPatchUpdate() ? BINARY_TYPE_OBB_PATCH : BINARY_TYPE_OBB;
    }

    @Nullable
    public InstallerResult resultForFinalUpdate(ApkUpdateInfo apkUpdateInfo) {
        if (apkUpdateInfo.getState().isFinal()) {
            return defaultInstallerResult(apkUpdateInfo);
        }
        throw new IllegalArgumentException("update has not completed");
    }

    public InstallerResult defaultInstallerResult(ApkUpdateInfo apkUpdateInfo) {
        InstallerResultError createResultError = createResultError(apkUpdateInfo);
        if (createResultError == null) {
            return InstallerResult.createForSuccess(apkUpdateInfo.identifier, apkUpdateInfo.getPackageName(), apkUpdateInfo.getExtras().getStateTimestamp());
        }
        return InstallerResult.createForError(apkUpdateInfo.identifier, apkUpdateInfo.getPackageName(), apkUpdateInfo.getExtras().getStateTimestamp(), createResultError);
    }

    @Nullable
    private static InstallerResultError createResultError(ApkUpdateInfo apkUpdateInfo) {
        int failureCode = apkUpdateInfo.getExtras().getFailureCode();
        if (failureCode == -1) {
            return null;
        }
        return InstallerResultError.fromInt(failureCode);
    }

    public UpdateInfoCollection getUpdateInfoCollection(ApkUpdateInfo apkUpdateInfo) {
        ApkUpdateInfo retriedUpdate;
        boolean z = true;
        boolean z2 = isRetry(apkUpdateInfo) && (retriedUpdate = getRetriedUpdate(apkUpdateInfo)) != null && !hasParentUpdate(retriedUpdate);
        boolean isApk = apkUpdateInfo.updateType.isApk();
        Preconditions.checkArgument(isApk, "root of the tree must be of type apk, not " + apkUpdateInfo.updateType.name());
        if (hasParentUpdate(apkUpdateInfo) && !z2) {
            z = false;
        }
        Preconditions.checkArgument(z, "update is not the root of the tree");
        ApkUpdateInfo apkUpdateInfo2 = null;
        ArrayList arrayList = new ArrayList();
        for (ApkUpdateInfo apkUpdateInfo3 : getDependencies(apkUpdateInfo)) {
            if (apkUpdateInfo3.updateType.isObb()) {
                if (apkUpdateInfo2 == null) {
                    apkUpdateInfo2 = apkUpdateInfo3;
                } else {
                    throw new IllegalStateException(">1 obb dependencies found for update");
                }
            } else if (apkUpdateInfo3.updateType.isAsset()) {
                arrayList.add(apkUpdateInfo3);
            } else if (apkUpdateInfo3.updateType.isApk()) {
                throw new IllegalStateException("apk dependencies are not supported");
            } else {
                throw new IllegalStateException("unknown update type: " + apkUpdateInfo3.updateType.name());
            }
        }
        return new UpdateInfoCollection(apkUpdateInfo, apkUpdateInfo2, arrayList);
    }

    public void failSoftTransitive(ApkUpdateInfo apkUpdateInfo, ApkUpdateInfo apkUpdateInfo2, InstallerResultError installerResultError) {
        String format = String.format(Locale.US, "Update failed due to dependency failure from update %d", Long.valueOf(apkUpdateInfo2.id));
        BLog.e(TAG, format);
        apkUpdateInfo.edit().putState(ApkUpdateInfoContract.UpdateState.FAILED, ((Clock) FbInjector.lazyInstance(1, TimeModule.UL_id._UL__ULSEP_com_oculus_time_Clock_ULSEP_BINDING_ID, this._UL_mInjectionContext)).now()).putIsTransitiveFailure(true).putFailedState(apkUpdateInfo.getState()).putFailedCategory("Transitive failure").putFailedDescription(format).putFailureCode(toInt(installerResultError)).save();
    }

    public void failQuiet(ApkUpdateInfo apkUpdateInfo, String str, String str2, InstallerResultError installerResultError, @Nullable Throwable th) {
        ApkUpdateInfo.ApkUpdateExtrasBuilder putFailureCode = apkUpdateInfo.edit().putState(ApkUpdateInfoContract.UpdateState.FAILED, ((Clock) FbInjector.lazyInstance(1, TimeModule.UL_id._UL__ULSEP_com_oculus_time_Clock_ULSEP_BINDING_ID, this._UL_mInjectionContext)).now()).putIsTransitiveFailure(false).putFailedState(apkUpdateInfo.getState()).putFailedCategory(str).putFailedDescription(str2).putFailureCode(toInt(installerResultError));
        if (th == null) {
            BLog.e(TAG, str2);
        } else {
            BLog.e(TAG, th, str2);
            putFailureCode.putStackTrace(ExceptionUtil.getStackTraceString(th));
        }
        putFailureCode.save();
    }

    public void failSoft(ApkUpdateInfo apkUpdateInfo, String str, String str2, InstallerResultError installerResultError, @Nullable Throwable th) {
        ((IErrorReporter) FbInjector.lazyInstance(0, InterfacesModule.UL_id._UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID, this._UL_mInjectionContext)).softError(str, str2, th);
        failQuiet(apkUpdateInfo, str, str2, installerResultError, th);
    }

    public void failHard(ApkUpdateInfo apkUpdateInfo, String str, String str2, InstallerResultError installerResultError, @Nullable Throwable th) {
        failSoft(apkUpdateInfo, str, str2, installerResultError, th);
        throw new IllegalStateException(str2, th);
    }

    private int toInt(@Nullable InstallerResultError installerResultError) {
        if (installerResultError == null) {
            return -1;
        }
        return installerResultError.code;
    }

    @Nullable
    public <T> SettableFuture<T> getFuture(ApkUpdateInfo apkUpdateInfo) {
        if (!apkUpdateInfo.getExtras().hasKey(ApkUpdateInfoContract.EXTRA_FUTURE_ID) || !apkUpdateInfo.getExtras().hasKey(ApkUpdateInfoContract.EXTRA_FUTURE_PID)) {
            return null;
        }
        return this.mServiceFutures.getFuture(apkUpdateInfo.getExtras().getFutureId(), apkUpdateInfo.getExtras().getFuturePid());
    }
}
