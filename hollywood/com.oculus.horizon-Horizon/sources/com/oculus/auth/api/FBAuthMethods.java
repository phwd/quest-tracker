package com.oculus.auth.api;

import X.AbstractC06640p5;
import X.AnonymousClass0J2;
import X.AnonymousClass0QC;
import X.AnonymousClass0p1;
import X.AnonymousClass117;
import X.C003008y;
import X.C01020Iw;
import android.content.Context;
import com.facebook.tigon.iface.TigonRequest;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.http.core.base.ApiError;
import com.oculus.http.core.base.ApiException;
import com.oculus.http.core.util.CountryUtils;
import com.oculus.locale.LocaleModule;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import javax.inject.Provider;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.http.FieldMap;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;
import retrofit.http.Path;

@Dependencies({"_UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_auth_api_FacebookLoginApiRestAdapter_ULSEP_BINDING_ID", "_UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_auth_api_FacebookLoginGraphRestAdapter_ULSEP_BINDING_ID", "_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID", "_UL__ULSEP_java_util_Locale_ULSEP_BINDING_ID"})
public class FBAuthMethods {
    public static final String ACCESS_TOKEN = "access_token";
    public static final String API_AUTHENTICATE_FRIENDLY_NAME = "authenticate";
    public static final String API_FRIENDLY_NAME = "fb_api_req_friendly_name";
    public static final String AUTH_LOGIN_METHOD = "auth.login";
    public static final String CLIENT_COUNTRY_CODE = "client_country_code";
    public static final String CREDENTIALS_TYPE = "credentials_type";
    public static final String EMAIL = "email";
    public static final String FB_CONNECT_ACCESS_TOKEN = "1517832211847102|5ca02b7262dc44cf9e6ef5265a14188d";
    public static final String FIRST_FACTOR = "first_factor";
    public static final String GENERATE_MACHINE_ID = "generate_machine_id";
    public static final String GRAPH_CHECK_APPROVED_FRIENDLY_NAME = "checkApprovedMachine";
    public static final String GRAPH_TWO_FAC_SMS_FRIENDLY_NAME = "login_approval_resend_code";
    public static final String MACHINE_ID = "machine_id";
    public static final String METHOD = "method";
    public static final String PASSWORD = "password";
    public static final String RESPONSE_FORMAT = "format";
    public static final String RESPONSE_TYPE_JSON = "json";
    public static final String TWO_FACTOR_CODE = "twofactor_code";
    public static final String USER_ID = "userid";
    public AnonymousClass0QC _UL_mInjectionContext;
    public final ApiMethods mApiMethods;
    public final GraphMethods mGraphMethods;
    @Inject
    public final Provider<Locale> mLocaleProvider;

    public interface ApiMethods {
        public static final String LOGIN_ENDPOINT = "/method/auth.login";

        @POST(LOGIN_ENDPOINT)
        @FormUrlEncoded
        void loginEndpoint(@FieldMap HashMap<String, String> hashMap, Callback<FBLoginResponse> callback);
    }

    public interface GraphMethods {
        public static final String CHECK_APPROVED_ENDPOINT = "/check_approved_machine";
        public static final String MACHINE_APPROVAL_LOGIN_ENDPOINT = "/{user_id}/twofacsms";

        @POST(CHECK_APPROVED_ENDPOINT)
        @FormUrlEncoded
        void checkApproved(@FieldMap HashMap<String, String> hashMap, Callback<CheckApprovedMachineResponse> callback);

        @POST(MACHINE_APPROVAL_LOGIN_ENDPOINT)
        @FormUrlEncoded
        List<Void> resendSms(@Path("user_id") String str, @FieldMap HashMap<String, String> hashMap);
    }

    @AutoGeneratedAccessMethod
    public static final AnonymousClass0p1 _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_auth_api_FBAuthMethods_ULGT__ULSEP_ACCESS_METHOD(AbstractC06640p5 r2) {
        return new C003008y(180, r2);
    }

    @AutoGeneratedAccessMethod
    public static final FBAuthMethods _UL__ULSEP_com_oculus_auth_api_FBAuthMethods_ULSEP_ACCESS_METHOD(AbstractC06640p5 r1) {
        return (FBAuthMethods) AnonymousClass117.A00(180, r1);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_auth_api_FBAuthMethods_ULGT__ULSEP_ACCESS_METHOD(AbstractC06640p5 r2) {
        return new C01020Iw(180, r2);
    }

    public void checkApproved(FBAuthRequest fBAuthRequest, Callback<CheckApprovedMachineResponse> callback) throws ApiException {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("access_token", FB_CONNECT_ACCESS_TOKEN);
        hashMap.put("u", fBAuthRequest.uid);
        hashMap.put("m", fBAuthRequest.machineId);
        hashMap.put("method", TigonRequest.GET);
        hashMap.put(API_FRIENDLY_NAME, GRAPH_CHECK_APPROVED_FRIENDLY_NAME);
        hashMap.put("format", "json");
        hashMap.put(CLIENT_COUNTRY_CODE, CountryUtils.A00((Context) AnonymousClass0J2.A03(0, 294, this._UL_mInjectionContext), this.mLocaleProvider.get()));
        try {
            this.mGraphMethods.checkApproved(hashMap, callback);
        } catch (RetrofitError e) {
            throw new ApiException(e, new ApiError(e));
        }
    }

    public void loginRequest(FBAuthRequest fBAuthRequest, Callback<FBLoginResponse> callback) throws ApiException {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("access_token", FB_CONNECT_ACCESS_TOKEN);
        hashMap.put("format", "json");
        hashMap.put(API_FRIENDLY_NAME, API_AUTHENTICATE_FRIENDLY_NAME);
        hashMap.put("method", AUTH_LOGIN_METHOD);
        hashMap.put("email", fBAuthRequest.email);
        hashMap.put("password", fBAuthRequest.password);
        hashMap.put(CLIENT_COUNTRY_CODE, CountryUtils.A00((Context) AnonymousClass0J2.A03(0, 294, this._UL_mInjectionContext), this.mLocaleProvider.get()));
        String str = fBAuthRequest.credentialsType;
        if (str != null) {
            hashMap.put(CREDENTIALS_TYPE, str);
        } else {
            hashMap.put(CREDENTIALS_TYPE, "password");
        }
        String str2 = fBAuthRequest.machineId;
        if (str2 == null) {
            hashMap.put(GENERATE_MACHINE_ID, "true");
        } else {
            hashMap.put("machine_id", str2);
        }
        String str3 = fBAuthRequest.uid;
        if (str3 != null) {
            hashMap.put("userid", str3);
        }
        String str4 = fBAuthRequest.firstFactor;
        if (str4 != null) {
            hashMap.put("first_factor", str4);
            hashMap.put(TWO_FACTOR_CODE, fBAuthRequest.password);
        }
        try {
            this.mApiMethods.loginEndpoint(hashMap, callback);
        } catch (RetrofitError e) {
            throw new ApiException(e, new ApiError(e));
        }
    }

    public List<Void> resendSms(FBAuthRequest fBAuthRequest) throws ApiException {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("access_token", FB_CONNECT_ACCESS_TOKEN);
        hashMap.put(API_FRIENDLY_NAME, GRAPH_TWO_FAC_SMS_FRIENDLY_NAME);
        hashMap.put("format", "json");
        hashMap.put("first_factor", fBAuthRequest.firstFactor);
        hashMap.put(CLIENT_COUNTRY_CODE, CountryUtils.A00((Context) AnonymousClass0J2.A03(0, 294, this._UL_mInjectionContext), this.mLocaleProvider.get()));
        try {
            return this.mGraphMethods.resendSms(fBAuthRequest.uid, hashMap);
        } catch (RetrofitError e) {
            throw new ApiException(e, new ApiError(e));
        }
    }

    @Inject
    public FBAuthMethods(AbstractC06640p5 r3, @FacebookLoginApiRestAdapter RestAdapter restAdapter, @FacebookLoginGraphRestAdapter RestAdapter restAdapter2) {
        this._UL_mInjectionContext = new AnonymousClass0QC(1, r3);
        this.mLocaleProvider = LocaleModule.A01(r3);
        this.mApiMethods = (ApiMethods) restAdapter.create(ApiMethods.class);
        this.mGraphMethods = (GraphMethods) restAdapter2.create(GraphMethods.class);
    }

    @AutoGeneratedFactoryMethod
    public static final FBAuthMethods _UL__ULSEP_com_oculus_auth_api_FBAuthMethods_ULSEP_FACTORY_METHOD(AbstractC06640p5 r3) {
        return new FBAuthMethods(r3, ApiModule._UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_auth_api_FacebookLoginApiRestAdapter_ULSEP_ACCESS_METHOD(r3), ApiModule._UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_auth_api_FacebookLoginGraphRestAdapter_ULSEP_ACCESS_METHOD(r3));
    }
}
