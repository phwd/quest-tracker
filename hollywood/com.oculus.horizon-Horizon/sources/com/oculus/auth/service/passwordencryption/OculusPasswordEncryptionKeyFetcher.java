package com.oculus.auth.service.passwordencryption;

import X.AbstractC06640p5;
import X.AnonymousClass006;
import X.AnonymousClass0J2;
import X.AnonymousClass0NO;
import X.AnonymousClass0QC;
import X.AnonymousClass0p1;
import X.AnonymousClass117;
import X.AnonymousClass1Dm;
import X.AnonymousClass1Dp;
import X.AnonymousClass1Dq;
import X.C003008y;
import X.C01020Iw;
import androidx.annotation.VisibleForTesting;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.google.gson.annotations.SerializedName;
import com.oculus.auth.service.contract.ServiceContract;
import com.oculus.base.app.AppInfo;
import com.oculus.http.core.annotations.FacebookGraphRestAdapter;
import com.oculus.http.core.base.ApiCallback;
import com.oculus.http.core.base.ApiError;
import com.oculus.http.core.interceptor.AuthorizationInterceptor;
import com.squareup.okhttp.internal.framed.Hpack;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;
import javax.inject.Provider;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Query;

@Dependencies({"_UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_FacebookGraphRestAdapter_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_base_app_AppInfo_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_auth_service_passwordencryption_PasswordEncryptionLogger_ULSEP_BINDING_ID"})
public class OculusPasswordEncryptionKeyFetcher {
    public static final String TAG = "OculusPasswordEncryptionKeyFetcher";
    public AnonymousClass0QC _UL_mInjectionContext;
    public final FetchKeyMethod mFetchKeyMethod;

    @ThreadSafe
    public interface FetchKeyMethod {
        @GET("/pwd_key_fetch")
        void fetchKey(@Header("Authorization") String str, @Query("version") int i, @Query("flow") AnonymousClass1Dp v, Callback<FetchKeyResult> callback);
    }

    @VisibleForTesting
    public static class FetchKeyResult {
        @SerializedName("key_id")
        public final int mKeyId;
        @SerializedName("public_key")
        public final String mPublicKey;
        @SerializedName(ServiceContract.EXTRA_ENCRYPTION_KEY_PKG_SECONDS_TO_LIVE)
        public final long mSecondsToLive;
    }

    @AutoGeneratedAccessMethod
    public static final AnonymousClass0p1 _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_auth_service_passwordencryption_OculusPasswordEncryptionKeyFetcher_ULGT__ULSEP_ACCESS_METHOD(AbstractC06640p5 r2) {
        return new C003008y(Hpack.PREFIX_7_BITS, r2);
    }

    @AutoGeneratedAccessMethod
    public static final OculusPasswordEncryptionKeyFetcher _UL__ULSEP_com_oculus_auth_service_passwordencryption_OculusPasswordEncryptionKeyFetcher_ULSEP_ACCESS_METHOD(AbstractC06640p5 r1) {
        return (OculusPasswordEncryptionKeyFetcher) AnonymousClass117.A00(Hpack.PREFIX_7_BITS, r1);
    }

    @AutoGeneratedFactoryMethod
    public static final OculusPasswordEncryptionKeyFetcher _UL__ULSEP_com_oculus_auth_service_passwordencryption_OculusPasswordEncryptionKeyFetcher_ULSEP_FACTORY_METHOD(AbstractC06640p5 r2) {
        return new OculusPasswordEncryptionKeyFetcher(r2, (RestAdapter) AnonymousClass117.A00(AuthorizationInterceptor.HTTP_STATUS_UNAUTHORIZED, r2));
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_auth_service_passwordencryption_OculusPasswordEncryptionKeyFetcher_ULGT__ULSEP_ACCESS_METHOD(AbstractC06640p5 r2) {
        return new C01020Iw(Hpack.PREFIX_7_BITS, r2);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void logKeyFetchingEvent(String str, @Nullable String str2) {
        ((PasswordEncryptionLogger) AnonymousClass0J2.A03(1, 359, this._UL_mInjectionContext)).logKeyFetchingEvent(str, null, str2);
    }

    public void fetchKey(int i, AnonymousClass1Dp r6, final AnonymousClass1Dm r7) {
        AppInfo appInfo = (AppInfo) AnonymousClass0J2.A03(0, 560, this._UL_mInjectionContext);
        this.mFetchKeyMethod.fetchKey(AnonymousClass006.A08("OAuth ", appInfo.appId, "|", appInfo.appSecret), i, r6, new ApiCallback<FetchKeyResult>() {
            /* class com.oculus.auth.service.passwordencryption.OculusPasswordEncryptionKeyFetcher.AnonymousClass1 */

            @Override // com.oculus.http.core.base.ApiCallback
            public void onError(ApiError apiError) {
                AnonymousClass0NO.A0B(OculusPasswordEncryptionKeyFetcher.TAG, "fetchKey failed with error: ", apiError);
                String str = null;
                r7.A6F(null, apiError);
                OculusPasswordEncryptionKeyFetcher oculusPasswordEncryptionKeyFetcher = OculusPasswordEncryptionKeyFetcher.this;
                if (apiError != null) {
                    str = apiError.getMessage();
                }
                oculusPasswordEncryptionKeyFetcher.logKeyFetchingEvent(PasswordEncryptionLogger.EVENT_KEY_FETCHING_FAILURE, str);
            }

            public void onResponse(FetchKeyResult fetchKeyResult) {
                OculusPasswordEncryptionKeyFetcher oculusPasswordEncryptionKeyFetcher;
                String str;
                if (fetchKeyResult != null) {
                    r7.A6F(new AnonymousClass1Dq(fetchKeyResult.mKeyId, fetchKeyResult.mPublicKey, fetchKeyResult.mSecondsToLive), null);
                    oculusPasswordEncryptionKeyFetcher = OculusPasswordEncryptionKeyFetcher.this;
                    str = PasswordEncryptionLogger.EVENT_KEY_FETCHING_SUCCESS;
                } else {
                    AnonymousClass0NO.A08(OculusPasswordEncryptionKeyFetcher.TAG, "fetchKey failed with null response");
                    r7.A6F(null, null);
                    oculusPasswordEncryptionKeyFetcher = OculusPasswordEncryptionKeyFetcher.this;
                    str = PasswordEncryptionLogger.EVENT_KEY_FETCHING_FAILURE;
                }
                oculusPasswordEncryptionKeyFetcher.logKeyFetchingEvent(str, null);
            }
        });
    }

    @Inject
    public OculusPasswordEncryptionKeyFetcher(AbstractC06640p5 r3, @FacebookGraphRestAdapter RestAdapter restAdapter) {
        this._UL_mInjectionContext = new AnonymousClass0QC(2, r3);
        this.mFetchKeyMethod = (FetchKeyMethod) restAdapter.create(FetchKeyMethod.class);
    }

    public static /* synthetic */ String access$000() {
        return TAG;
    }
}