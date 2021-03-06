package com.oculus.horizon.service;

import X.AbstractC06640p5;
import X.AnonymousClass0J2;
import X.AnonymousClass0NO;
import X.AnonymousClass0QC;
import X.AnonymousClass0p1;
import X.AnonymousClass117;
import X.C003008y;
import X.C01020Iw;
import android.os.Bundle;
import android.text.TextUtils;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.appmanager.assets.model.IapStatus;
import com.oculus.appmanager.installer.common.InstallerFileUtils;
import com.oculus.errorreporting.interfaces.IErrorReporter;
import com.oculus.library.model.AssetInfo;
import java.io.File;
import java.util.Collection;
import java.util.Locale;
import javax.annotation.Nullable;
import javax.inject.Provider;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit.Endpoints;

@Dependencies({"_UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_appmanager_installer_common_InstallerFileUtils_ULSEP_BINDING_ID"})
public class AssetBundleUtil {
    public static final String EXTRA_ASSET_ID = "asset_id";
    public static final String EXTRA_ASSET_ID_DEPRECATED = "asset_file_id";
    public static final String EXTRA_ASSET_METADATA = "metadata";
    public static final String EXTRA_ASSET_NAME = "asset_name";
    public static final String EXTRA_ASSET_TYPE = "asset_type";
    public static final String EXTRA_DOWNLOAD_STATUS = "download_status";
    public static final String EXTRA_FILEPATH = "filepath";
    public static final String EXTRA_IAP_STATUS = "iap_status";
    public static final String EXTRA_LANGUAGE_ENGLISH_NAME = "english_name";
    public static final String EXTRA_LANGUAGE_NATIVE_NAME = "native_name";
    public static final String EXTRA_LANGUAGE_PACK_INFO = "language";
    public static final String EXTRA_LANGUAGE_TAG = "tag";
    public static final String EXTRA_SUCCESS = "success";
    public static final String TAG = "AssetBundleUtil";
    public AnonymousClass0QC _UL_mInjectionContext;

    public enum AssetType {
        DEFAULT(Endpoints.DEFAULT_NAME),
        STORE("store"),
        LANGUAGE_PACK("language_pack");
        
        public final String value;

        /* access modifiers changed from: public */
        AssetType(String str) {
            this.value = str;
        }
    }

    public enum DownloadStatus {
        INSTALLED("installed"),
        AVAILABLE("available"),
        IN_PROGRESS("in-progress");
        
        public final String value;

        /* access modifiers changed from: public */
        DownloadStatus(String str) {
            this.value = str;
        }
    }

    @AutoGeneratedAccessMethod
    public static final AnonymousClass0p1 _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_horizon_service_AssetBundleUtil_ULGT__ULSEP_ACCESS_METHOD(AbstractC06640p5 r2) {
        return new C003008y(422, r2);
    }

    @AutoGeneratedAccessMethod
    public static final AssetBundleUtil _UL__ULSEP_com_oculus_horizon_service_AssetBundleUtil_ULSEP_ACCESS_METHOD(AbstractC06640p5 r1) {
        return (AssetBundleUtil) AnonymousClass117.A00(422, r1);
    }

    @AutoGeneratedFactoryMethod
    public static final AssetBundleUtil _UL__ULSEP_com_oculus_horizon_service_AssetBundleUtil_ULSEP_FACTORY_METHOD(AbstractC06640p5 r1) {
        return new AssetBundleUtil(r1);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_horizon_service_AssetBundleUtil_ULGT__ULSEP_ACCESS_METHOD(AbstractC06640p5 r2) {
        return new C01020Iw(422, r2);
    }

    public static AssetType getAssetType(AssetInfo assetInfo) {
        String str = assetInfo.type;
        if (str == null) {
            return AssetType.DEFAULT;
        }
        return AssetType.valueOf(str);
    }

    public static IapStatus getIapStatus(AssetInfo assetInfo) {
        String str = assetInfo.entitlement;
        IapStatus iapStatus = IapStatus.FREE;
        if (str.equals(iapStatus.name())) {
            return iapStatus;
        }
        IapStatus iapStatus2 = IapStatus.ENTITLED;
        if (!str.equals(iapStatus2.name())) {
            return IapStatus.NOT_ENTITLED;
        }
        return iapStatus2;
    }

    public static Bundle makeBasicBundle(String str, AssetInfo assetInfo) {
        Bundle bundle = new Bundle();
        bundle.putString("asset_id", assetInfo.id);
        bundle.putString("asset_file_id", assetInfo.id);
        bundle.putString("asset_name", assetInfo.name);
        bundle.putString(EXTRA_ASSET_METADATA, assetInfo.metadata);
        bundle.putString("filepath", InstallerFileUtils.A00(str, assetInfo.name));
        String str2 = assetInfo.type;
        Locale locale = Locale.US;
        if (str2.toLowerCase(locale).equals(AssetType.LANGUAGE_PACK.value.toLowerCase(locale))) {
            Bundle bundle2 = new Bundle();
            bundle2.putString("tag", assetInfo.languageCode);
            bundle2.putString(EXTRA_LANGUAGE_ENGLISH_NAME, assetInfo.languageEnglishName);
            bundle2.putString(EXTRA_LANGUAGE_NATIVE_NAME, assetInfo.languageNativeName);
            bundle.putBundle(EXTRA_LANGUAGE_PACK_INFO, bundle2);
        }
        return bundle;
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:30:0x00cc */
    /* JADX DEBUG: Multi-variable search result rejected for r2v6, resolved type: java.lang.Object */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v5, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v7, types: [org.json.JSONObject] */
    @Nullable
    public Bundle getAssetsListBundle(String str, Collection<AssetInfo> collection) {
        DownloadStatus downloadStatus;
        String str2;
        Object obj;
        Bundle bundle = new Bundle();
        try {
            JSONArray jSONArray = new JSONArray();
            for (AssetInfo assetInfo : collection) {
                Object A00 = InstallerFileUtils.A00(str, assetInfo.name);
                if (new File(InstallerFileUtils.A00(str, assetInfo.name)).exists()) {
                    downloadStatus = DownloadStatus.INSTALLED;
                } else {
                    downloadStatus = DownloadStatus.AVAILABLE;
                }
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("asset_id", assetInfo.id);
                jSONObject.put("asset_name", assetInfo.name);
                jSONObject.put(EXTRA_ASSET_METADATA, assetInfo.metadata);
                jSONObject.put("filepath", A00);
                String str3 = assetInfo.entitlement;
                IapStatus iapStatus = IapStatus.FREE;
                if (!str3.equals(iapStatus.name())) {
                    iapStatus = IapStatus.ENTITLED;
                    if (!str3.equals(iapStatus.name())) {
                        iapStatus = IapStatus.NOT_ENTITLED;
                    }
                }
                String name = iapStatus.name();
                Locale locale = Locale.US;
                jSONObject.put(EXTRA_IAP_STATUS, name.toLowerCase(locale));
                jSONObject.put("asset_type", assetInfo.type.toLowerCase(locale));
                jSONObject.put("download_status", downloadStatus.name().toLowerCase(locale));
                if (TextUtils.isEmpty(assetInfo.languageCode) || TextUtils.isEmpty(assetInfo.languageEnglishName) || TextUtils.isEmpty(assetInfo.languageNativeName)) {
                    str2 = EXTRA_LANGUAGE_PACK_INFO;
                    obj = JSONObject.NULL;
                } else {
                    obj = new JSONObject();
                    obj.put("tag", assetInfo.languageCode);
                    obj.put(EXTRA_LANGUAGE_ENGLISH_NAME, assetInfo.languageEnglishName);
                    obj.put(EXTRA_LANGUAGE_NATIVE_NAME, assetInfo.languageNativeName);
                    str2 = EXTRA_LANGUAGE_PACK_INFO;
                }
                jSONObject.put(str2, obj);
                jSONArray.put(jSONObject);
            }
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("data", jSONArray);
            bundle.putString("result", jSONObject2.toString());
            return bundle;
        } catch (JSONException e) {
            ((IErrorReporter) AnonymousClass0J2.A03(0, 428, this._UL_mInjectionContext)).A97(TAG, "error creating json payload", e);
            AnonymousClass0NO.A0K(TAG, e, "Error creating json payload for %s", str);
            bundle.putString("result", "{result={\"data\":[]}}");
            return bundle;
        }
    }

    public Bundle getBundleForStatus(String str, AssetInfo assetInfo) {
        DownloadStatus downloadStatus;
        if (new File(InstallerFileUtils.A00(assetInfo.packageName, assetInfo.name)).exists()) {
            downloadStatus = DownloadStatus.INSTALLED;
        } else {
            downloadStatus = DownloadStatus.AVAILABLE;
        }
        Bundle makeBasicBundle = makeBasicBundle(str, assetInfo);
        makeBasicBundle.putString(EXTRA_IAP_STATUS, getIapStatus(assetInfo).value);
        makeBasicBundle.putString("download_status", downloadStatus.value);
        makeBasicBundle.putString("asset_type", getAssetType(assetInfo).value);
        return makeBasicBundle;
    }

    @Inject
    public AssetBundleUtil(AbstractC06640p5 r3) {
        this._UL_mInjectionContext = new AnonymousClass0QC(2, r3);
    }

    public static Bundle getBundleForOperation(String str, AssetInfo assetInfo, boolean z) {
        Bundle makeBasicBundle = makeBasicBundle(str, assetInfo);
        makeBasicBundle.putBoolean("success", z);
        return makeBasicBundle;
    }
}
