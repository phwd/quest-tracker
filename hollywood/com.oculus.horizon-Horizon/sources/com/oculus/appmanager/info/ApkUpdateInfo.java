package com.oculus.appmanager.info;

import X.AbstractC06640p5;
import X.AnonymousClass117;
import android.text.TextUtils;
import com.facebook.inject.Assisted;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import com.oculus.appmanager.info.ApkUpdateInfoContract;
import com.oculus.appmanager.info.database.ApkUpdateExtrasManager;
import com.oculus.appmanager.info.model.RequestOrigin;
import com.oculus.appmanager.installer.analytics.InstallerAnalytics;
import com.oculus.auth.service.contract.ServiceContract;
import com.oculus.debug.DebugMode;
import com.oculus.errorreporting.interfaces.IErrorReporter;
import com.oculus.extras.Extras;
import com.oculus.extras.ExtrasBuilder;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nullable;

@Dependencies({"_UL__ULSEP_com_oculus_appmanager_info_ApkUpdateInfoDispatcher_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_appmanager_info_database_ApkUpdateExtrasManager_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_debug_DebugMode_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID"})
public class ApkUpdateInfo {
    public static final long INVALID_FUTURE_ID = -1;
    public static final int INVALID_FUTURE_PID = -1;
    public static final long INVALID_UPDATE_ID = -1;
    public static final String TAG = "com.oculus.appmanager.info.ApkUpdateInfo";
    @Nullable
    public final String accessToken;
    public final long baseVersion;
    @Nullable
    public final String checksumHash;
    @Nullable
    public final String checksumHashAlg;
    public final ImmutableList<Long> dependencies;
    @Nullable
    public final String displayTitle;
    public final long downloadSize;
    @Nullable
    public final String downloadUrl;
    @Nullable
    public final String expectedSignature;
    @Nullable
    public final String externalSignatures;
    public final long id;
    public final String identifier;
    public final boolean isUpdate;
    public final ApkUpdateExtrasManager mApkUpdateExtrasManager;
    public final ApkUpdateInfoDispatcher mApkUpdateInfoDispatcher;
    public final DebugMode mDebugMode;
    public final AtomicBoolean mDeleted = new AtomicBoolean(false);
    public final IErrorReporter mErrorReporter;
    public ApkUpdateExtras mExtras;
    @Nullable
    public final Map<String, String> requestHeaders;
    public final RequestOrigin requestOrigin;
    @Nullable
    public final String requestingPackage;
    @Nullable
    public final String storeItemId;
    public final long targetVersion;
    public final ApkUpdateInfoContract.UpdateType updateType;

    public static class ApkUpdateExtras extends Extras {
        public ApkUpdateExtras(Extras extras) {
            super(extras.mData);
        }

        @Override // com.oculus.extras.Extras
        public final String toString() {
            MoreObjects.ToStringHelper stringHelper = MoreObjects.toStringHelper(ApkUpdateExtras.class);
            stringHelper.add("data", this.mData);
            return stringHelper.toString();
        }
    }

    public static class ApkUpdateExtrasBuilder extends ExtrasBuilder {
    }

    public class UpdateExtrasEditor extends ApkUpdateExtrasBuilder {
        public final Set<String> mKeysToRemove;
        public final /* synthetic */ ApkUpdateInfo this$0;
    }

    public final synchronized ApkUpdateExtras A01() {
        return this.mExtras;
    }

    public final synchronized String toString() {
        MoreObjects.ToStringHelper stringHelper;
        String join;
        ApkUpdateExtras A01 = A01();
        stringHelper = MoreObjects.toStringHelper(ApkUpdateInfo.class);
        stringHelper.add("id", this.id);
        stringHelper.add(ServiceContract.EXTRA_IDENTIFIER, this.identifier);
        stringHelper.add(InstallerAnalytics.Extras.UPDATE_TYPE, this.updateType.name());
        stringHelper.add("state_str", A02().name());
        if (this.dependencies.isEmpty()) {
            join = "none";
        } else {
            join = TextUtils.join(",", this.dependencies);
        }
        stringHelper.add("dependencies", join);
        if (A01.mData.containsKey("download_id")) {
            stringHelper.add("download_id", A01.A00("download_id", 0));
        }
        if (A01.mData.containsKey("failed_category")) {
            stringHelper.add("failed_category", A01.A01("failed_category", null));
        }
        if (A01.mData.containsKey(ApkUpdateInfoContract.EXTRA_FAILED_DESCR)) {
            stringHelper.add(ApkUpdateInfoContract.EXTRA_FAILED_DESCR, A01.A01("failed_category", null));
        }
        if (A01.mData.containsKey("failed_state")) {
            int asInt = ApkUpdateInfoContract.UpdateState.NEW.asInt();
            if (A01.mData.containsKey("failed_state")) {
                try {
                    asInt = Integer.parseInt(A01.mData.get("failed_state"));
                } catch (NumberFormatException unused) {
                }
            }
            stringHelper.add("failed_state", ApkUpdateInfoContract.UpdateState.fromInt(asInt).name());
        }
        if (A01.mData.containsKey(ApkUpdateInfoContract.EXTRA_IS_RETRY_OF)) {
            stringHelper.add(ApkUpdateInfoContract.EXTRA_IS_RETRY_OF, A01.A00(ApkUpdateInfoContract.EXTRA_IS_RETRY_OF, -1));
        }
        if (A01.mData.containsKey(ApkUpdateInfoContract.EXTRA_RETRIED_AS_ID)) {
            stringHelper.add(ApkUpdateInfoContract.EXTRA_RETRIED_AS_ID, A01.A00(ApkUpdateInfoContract.EXTRA_RETRIED_AS_ID, -1));
        }
        return stringHelper.toString();
    }

    @AutoGeneratedAccessMethod
    public static final ApkUpdateInfoProvider A00(AbstractC06640p5 r1) {
        return (ApkUpdateInfoProvider) AnonymousClass117.A00(262, r1);
    }

    public final ApkUpdateInfoContract.UpdateState A02() {
        ApkUpdateExtras A01 = A01();
        int asInt = ApkUpdateInfoContract.UpdateState.NEW.asInt();
        if (A01.mData.containsKey("state")) {
            try {
                asInt = Integer.parseInt(A01.mData.get("state"));
            } catch (NumberFormatException unused) {
            }
        }
        return ApkUpdateInfoContract.UpdateState.fromInt(asInt);
    }

    @Inject
    public ApkUpdateInfo(@Assisted long j, @Assisted String str, @Assisted ApkUpdateInfoContract.UpdateType updateType2, @Assisted long j2, @Assisted long j3, @Assisted boolean z, @Assisted long j4, @Assisted @Nullable String str2, @Assisted RequestOrigin requestOrigin2, @Assisted ImmutableList<Long> immutableList, @Assisted @Nullable String str3, @Assisted @Nullable String str4, @Assisted @Nullable String str5, @Assisted @Nullable String str6, @Assisted @Nullable String str7, @Assisted @Nullable String str8, @Assisted @Nullable String str9, @Assisted @Nullable String str10, @Assisted Extras extras, @Assisted @Nullable Map<String, String> map, ApkUpdateInfoDispatcher apkUpdateInfoDispatcher, ApkUpdateExtrasManager apkUpdateExtrasManager, DebugMode debugMode, IErrorReporter iErrorReporter) {
        this.id = j;
        this.identifier = str;
        this.updateType = updateType2;
        this.baseVersion = j2;
        this.targetVersion = j3;
        this.isUpdate = z;
        this.downloadSize = j4;
        this.downloadUrl = str2;
        this.requestOrigin = requestOrigin2;
        this.dependencies = immutableList;
        this.accessToken = str3;
        this.storeItemId = str4;
        this.checksumHash = str5;
        this.checksumHashAlg = str6;
        this.displayTitle = str7;
        this.requestingPackage = str8;
        this.expectedSignature = str9;
        this.externalSignatures = str10;
        this.requestHeaders = map;
        this.mApkUpdateInfoDispatcher = apkUpdateInfoDispatcher;
        this.mApkUpdateExtrasManager = apkUpdateExtrasManager;
        this.mDebugMode = debugMode;
        this.mErrorReporter = iErrorReporter;
        this.mExtras = new ApkUpdateExtras(extras);
    }
}
