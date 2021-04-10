package com.oculus.mobileconfigservice;

import X.AbstractC06640p5;
import X.AnonymousClass006;
import X.AnonymousClass0DC;
import X.AnonymousClass0DD;
import X.AnonymousClass0J2;
import X.AnonymousClass0NO;
import X.AnonymousClass0QC;
import X.C08780ya;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.google.gson.annotations.SerializedName;
import com.oculus.auth.credentials.Credentials;
import com.oculus.auth.credentials.CredentialsModule;
import com.oculus.config.updater.logging.ConfigUpdaterLogger;
import com.oculus.deviceconfigclient.ConfigStorageCache;
import com.oculus.http.useragent.UserAgentBuilder;
import com.oculus.locale.LocaleModule;
import com.oculus.util.constants.OculusConstants;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.annotation.Nullable;
import javax.inject.Provider;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Query;

@Dependencies({"_UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_CustomUserAgentOculusRestAdapter_ULSEP_BINDING_ID", "_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID", "_UL__ULSEP_java_util_Locale_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_auth_credentials_Credentials_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_config_updater_logging_ConfigUpdaterLogger_ULSEP_BINDING_ID"})
public class MobileConfigUpdater {
    public static final String APP_NAME_OCULUS_MEDIA_GALLERY = "OculusGallery";
    public static final String MOBILE_CONFIG_API_VERSION = "6";
    public static final String PACKAGE_NAME_OCULUS_MEDIA_GALLERY = "com.oculus.mediagallery";
    public static final String TAG = "MobileConfigUpdater";
    public static final Object mMobileConfigFileDefaultLocker = new Object();
    public static final Object mMobileConfigFileGalleryLocker = new Object();
    public static final Object mMobileConfigFileHomeLocker = new Object();
    public AnonymousClass0QC _UL_mInjectionContext;
    @Inject
    @Eager
    @Nullable
    public final Credentials mCredentials;
    @Inject
    public final Provider<Locale> mLocaleProvider;

    public static class Config {
        @SerializedName("fields")
        public List<ConfigField> fields;
        @SerializedName("hash")
        public String hash;
    }

    public static class ConfigDetailsFromMetadataJson {
        @SerializedName("defaultValue")
        public String defaultValue;
    }

    public static class ConfigField {
        @SerializedName("bln")
        public Integer bln;
        @SerializedName("dbl")
        public Double dbl;
        @SerializedName("i64")
        public Integer i64;
        @SerializedName("li")
        public String loggingIdentifier;
        @SerializedName("pname")
        public String name;
        @SerializedName("str")
        public String str;
    }

    public interface Methods {
        @POST("/mobileconfig")
        void fetchConfigs(@Header("User-Agent") String str, @Query("access_token") String str2, @Query("queries[]") List<String> list, @Query("api_version") String str3, Callback<MobileConfigResponse> callback);
    }

    public static class MobileConfigCombinedResponse {
        @SerializedName("sessionbased")
        public MobileConfigResponse sessionbased;
        @SerializedName(ConfigStorageCache.PARAM_SESSIONLESS_JSON_KEY)
        public MobileConfigResponse sessionless;
    }

    public static class MobileConfigResponse {
        @SerializedName("configs")
        public Map<String, Config> configs;
        @SerializedName("query_hash")
        public String queryHash;
    }

    public static class MobileConfigSchemasFromMetadataJson {
        @SerializedName("schema")
        public final Map<String, ConfigDetailsFromMetadataJson> schemas;
    }

    public enum AppsToUpdate {
        OCULUS_GALLERY(MobileConfigUpdater.APP_NAME_OCULUS_MEDIA_GALLERY, "com.oculus.mediagallery", MobileConfigUpdater.mMobileConfigFileGalleryLocker),
        OCULUS_HOME(OculusConstants.HOME_SHELL_USER_AGENT_APP_NAME, "com.oculus.vrshell.home", MobileConfigUpdater.mMobileConfigFileHomeLocker);
        
        public String appName;
        public Object fileLocker;
        public String packageName;

        /* access modifiers changed from: public */
        AppsToUpdate(String str, String str2, Object obj) {
            this.appName = str;
            this.packageName = str2;
            this.fileLocker = obj;
        }
    }

    /* JADX WARN: Incorrect args count in method signature: (Ljava/lang/String;Ljava/lang/String;)LX/0DC<Ljava/lang/Void;>; */
    public static AnonymousClass0DC A00(MobileConfigUpdater mobileConfigUpdater, final String str, final String str2) {
        String str3;
        final AnonymousClass0DD r4 = new AnonymousClass0DD();
        try {
            ((ConfigUpdaterLogger) AnonymousClass0J2.A03(1, 228, mobileConfigUpdater._UL_mInjectionContext)).logMobileConfigStatus("mc_config_fetch_backend_started", str2);
            if (mobileConfigUpdater.mCredentials == null) {
                r4.A01(new NullPointerException("credentials are null"));
                return r4.A00;
            }
            PackageInfo packageInfo = ((Context) AnonymousClass0J2.A03(0, 294, mobileConfigUpdater._UL_mInjectionContext)).getPackageManager().getPackageInfo(str, 0);
            HashMap hashMap = new HashMap();
            AppsToUpdate[] values = AppsToUpdate.values();
            for (AppsToUpdate appsToUpdate : values) {
                try {
                    PackageInfo packageInfo2 = ((Context) AnonymousClass0J2.A03(0, 294, mobileConfigUpdater._UL_mInjectionContext)).getPackageManager().getPackageInfo(appsToUpdate.packageName, 0);
                    if (packageInfo2 != null) {
                        hashMap.put(appsToUpdate.appName, String.valueOf(packageInfo2.versionCode));
                    }
                } catch (PackageManager.NameNotFoundException e) {
                    AnonymousClass0NO.A0E(TAG, "failed to get package info for %s", appsToUpdate.packageName, e);
                }
            }
            String obj = hashMap.toString();
            UserAgentBuilder userAgentBuilder = new UserAgentBuilder((Context) AnonymousClass0J2.A03(0, 294, mobileConfigUpdater._UL_mInjectionContext));
            userAgentBuilder.mHttpAgent = System.getProperty("http.agent");
            AppsToUpdate[] values2 = AppsToUpdate.values();
            int length = values2.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    str3 = "";
                    break;
                }
                AppsToUpdate appsToUpdate2 = values2[i];
                if (appsToUpdate2.packageName.equals(str)) {
                    str3 = appsToUpdate2.appName;
                    break;
                }
                i++;
            }
            userAgentBuilder.mProperties.put("FBAN", str3);
            userAgentBuilder.mProperties.put("FBAV", packageInfo.versionName);
            userAgentBuilder.mProperties.put("FBBV", String.valueOf(packageInfo.versionCode));
            userAgentBuilder.mProperties.put("FBLC", mobileConfigUpdater.mLocaleProvider.get().toString());
            userAgentBuilder.mProperties.put(UserAgentBuilder.FB_DEVICE_WIDE_STATE, obj);
            userAgentBuilder.mProperties.put("FBPN", str);
            String A01 = userAgentBuilder.A01();
            HashMap hashMap2 = new HashMap();
            for (Map.Entry<String, ConfigDetailsFromMetadataJson> entry : ((MobileConfigSchemasFromMetadataJson) new C08780ya().A05(str2, MobileConfigSchemasFromMetadataJson.class)).schemas.entrySet()) {
                String[] split = entry.getKey().split(":");
                if (hashMap2.containsKey(split[0])) {
                    String str4 = split[0];
                    hashMap2.put(str4, AnonymousClass006.A07((String) hashMap2.get(str4), ":", split[1]));
                } else {
                    String str5 = split[0];
                    hashMap2.put(str5, AnonymousClass006.A07(str5, ":", split[1]));
                }
            }
            ((ConfigUpdaterLogger) AnonymousClass0J2.A03(1, 228, mobileConfigUpdater._UL_mInjectionContext)).logMobileConfigStatus("mc_fetch_queries_finalised", hashMap2.toString());
            ((Methods) ((RestAdapter) AnonymousClass0J2.A04(280, mobileConfigUpdater._UL_mInjectionContext)).create(Methods.class)).fetchConfigs(A01, mobileConfigUpdater.mCredentials.mAccessToken, new ArrayList(hashMap2.values()), MOBILE_CONFIG_API_VERSION, new Callback<MobileConfigResponse>() {
                /* class com.oculus.mobileconfigservice.MobileConfigUpdater.AnonymousClass1 */

                @Override // retrofit.Callback
                public final void failure(RetrofitError retrofitError) {
                    AnonymousClass0NO.A0B(MobileConfigUpdater.TAG, "failed to fetch config from server - ", retrofitError);
                    ((ConfigUpdaterLogger) AnonymousClass0J2.A03(1, 228, MobileConfigUpdater.this._UL_mInjectionContext)).logMobileConfigStatus("mc_fetch_failed", AnonymousClass006.A05("failed_fetch_from_backend ", retrofitError.getMessage()));
                    r4.A01(retrofitError);
                }

                /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, retrofit.client.Response] */
                @Override // retrofit.Callback
                public final void success(MobileConfigResponse mobileConfigResponse, Response response) {
                    MobileConfigResponse mobileConfigResponse2 = mobileConfigResponse;
                    try {
                        MobileConfigCombinedResponse mobileConfigCombinedResponse = new MobileConfigCombinedResponse();
                        mobileConfigCombinedResponse.sessionbased = mobileConfigResponse2;
                        String A06 = new C08780ya().A06(mobileConfigCombinedResponse);
                        ((ConfigUpdaterLogger) AnonymousClass0J2.A03(1, 228, MobileConfigUpdater.this._UL_mInjectionContext)).logMobileConfigStatus("mc_config_fetch_backend_success", AnonymousClass006.A0B("config definition - ", str2, "; config values - ", A06, "; file name - ", str, "_config_values.txt"));
                        synchronized (MobileConfigUpdater.A01(str)) {
                            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(((Context) AnonymousClass0J2.A03(0, 294, MobileConfigUpdater.this._UL_mInjectionContext)).openFileOutput(AnonymousClass006.A05(str, "_config_values.txt"), 0));
                            outputStreamWriter.write(A06);
                            outputStreamWriter.close();
                        }
                        r4.A02(null);
                    } catch (IOException e) {
                        AnonymousClass0NO.A0B(MobileConfigUpdater.TAG, "failed to write config values to disk - ", e);
                        ((ConfigUpdaterLogger) AnonymousClass0J2.A03(1, 228, MobileConfigUpdater.this._UL_mInjectionContext)).logMobileConfigStatus("mc_fetch_failed", AnonymousClass006.A05("failed_write_values_to_disk ", e.getMessage()));
                        r4.A01(e);
                    }
                }
            });
            return r4.A00;
        } catch (PackageManager.NameNotFoundException e2) {
            AnonymousClass0NO.A0B(TAG, "failed to get package from package manager - ", e2);
            ((ConfigUpdaterLogger) AnonymousClass0J2.A03(1, 228, mobileConfigUpdater._UL_mInjectionContext)).logMobileConfigStatus("mc_fetch_failed", AnonymousClass006.A05("package_not_found ", e2.getMessage()));
            r4.A01(e2);
        }
    }

    public static String A02(InputStream inputStream) {
        if (inputStream == null) {
            return "";
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    return sb.toString();
                }
                sb.append(readLine);
            }
        } catch (IOException e) {
            AnonymousClass0NO.A0B(TAG, "failed to read config values ", e);
            return "";
        }
    }

    @Inject
    public MobileConfigUpdater(AbstractC06640p5 r3) {
        this._UL_mInjectionContext = new AnonymousClass0QC(2, r3);
        this.mLocaleProvider = LocaleModule.A01(r3);
        this.mCredentials = CredentialsModule._UL__ULSEP_com_oculus_auth_credentials_Credentials_ULSEP_ACCESS_METHOD(r3);
    }

    public static Object A01(String str) {
        AppsToUpdate[] values = AppsToUpdate.values();
        for (AppsToUpdate appsToUpdate : values) {
            if (appsToUpdate.packageName.equals(str)) {
                return appsToUpdate.fileLocker;
            }
        }
        return mMobileConfigFileDefaultLocker;
    }
}
