package com.oculus.horizon.sso;

import X.AbstractC06640p5;
import X.AnonymousClass0QC;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.google.inject.name.Named;
import com.oculus.config.trusted_user.TrustedUserModule;
import com.oculus.http.core.annotations.FacebookApiRestAdapter;
import javax.inject.Provider;
import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Query;

@Dependencies({"_UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_FacebookApiRestAdapter_ULSEP_BINDING_ID", "_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_fbconnect_FBConnectHelper_ULSEP_BINDING_ID", "_UL__ULSEP_com_facebook_mobileconfig_factory_MobileConfig_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_unlockulus_ULUNDERSCORE_helper_UnlockulusHelper_ULSEP_BINDING_ID", "_UL__ULSEP_java_lang_Boolean_ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_is_ULUNDERSCORE_trusted_ULUNDERSCORE_user_ULSEP_BINDING_ID", "_UL__ULSEP_android_content_SharedPreferences_ULSEP_com_oculus_horizon_auth_shared_ULUNDERSCORE_datastore_HorizonAuthDatastoreSharedPrefs_ULSEP_BINDING_ID"})
public class SsoManager {
    public static final String BROWSER_PACKAGE_NAME = "com.oculus.browser";
    public static final String DEVICE_ID_KEY = "sso.device_id";
    public static final String MACHINE_ID_KEY = "sso.machine_id";
    public static final String TAG = "SsoManager";
    public static final String[] msColumnNames = {"domain", "expires", "expires_timestamp", "name", "path", "httponly", "secure", "value", "uid"};
    public AnonymousClass0QC _UL_mInjectionContext;
    @Inject
    @Named(TrustedUserModule.IS_TRUSTED_USER_GK)
    public final Provider<Boolean> mIsTrustedUser;
    public final Methods mMethods;

    public static class FeatureDisabledException extends Exception {
        public FeatureDisabledException() {
            super("getSessionForApp disabled by GK");
        }
    }

    public interface Methods {
        @GET("/method/auth.getSessionForApp?format=json&generate_session_cookies=1")
        GetSessionForAppResponse getSessionForApp(@Query("access_token") String str, @Query("new_app_id") String str2, @Query("machine_id") String str3, @Query("device_id") String str4, @Query("generate_machine_id") String str5);
    }

    public static class NoFacebookUserException extends Exception {
        public NoFacebookUserException() {
            super("User not logged in with Facebook");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0037, code lost:
        if (((com.oculus.unlockulus_helper.UnlockulusHelper) X.AnonymousClass0J2.A03(3, 296, r5._UL_mInjectionContext)).A00(r4) != false) goto L_0x0039;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean A00(java.lang.String r6) {
        /*
            r5 = this;
            r1 = 294(0x126, float:4.12E-43)
            X.0QC r0 = r5._UL_mInjectionContext
            r3 = 0
            java.lang.Object r0 = X.AnonymousClass0J2.A03(r3, r1, r0)
            android.content.Context r0 = (android.content.Context) r0
            boolean r0 = com.oculus.signature.SignatureHelper.A00(r0, r6)
            if (r0 != 0) goto L_0x0059
            X.0QC r0 = r5._UL_mInjectionContext
            java.lang.Object r4 = X.AnonymousClass0J2.A03(r3, r1, r0)
            android.content.Context r4 = (android.content.Context) r4
            javax.inject.Provider<java.lang.Boolean> r0 = r5.mIsTrustedUser
            java.lang.Object r0 = r0.get()
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            if (r0 != 0) goto L_0x0039
            r2 = 3
            r1 = 296(0x128, float:4.15E-43)
            X.0QC r0 = r5._UL_mInjectionContext
            java.lang.Object r0 = X.AnonymousClass0J2.A03(r2, r1, r0)
            com.oculus.unlockulus_helper.UnlockulusHelper r0 = (com.oculus.unlockulus_helper.UnlockulusHelper) r0
            boolean r1 = r0.A00(r4)
            r0 = 0
            if (r1 == 0) goto L_0x003a
        L_0x0039:
            r0 = 1
        L_0x003a:
            boolean r0 = com.oculus.signature.SignatureHelper.A02(r4, r6, r0)
            if (r0 == 0) goto L_0x005a
            r1 = 3
            r0 = 296(0x128, float:4.15E-43)
            X.0QC r2 = r5._UL_mInjectionContext
            java.lang.Object r1 = X.AnonymousClass0J2.A03(r1, r0, r2)
            com.oculus.unlockulus_helper.UnlockulusHelper r1 = (com.oculus.unlockulus_helper.UnlockulusHelper) r1
            r0 = 294(0x126, float:4.12E-43)
            java.lang.Object r0 = X.AnonymousClass0J2.A03(r3, r0, r2)
            android.content.Context r0 = (android.content.Context) r0
            boolean r0 = r1.A00(r0)
            if (r0 == 0) goto L_0x005a
        L_0x0059:
            r3 = 1
        L_0x005a:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.horizon.sso.SsoManager.A00(java.lang.String):boolean");
    }

    @Inject
    public SsoManager(AbstractC06640p5 r3, @FacebookApiRestAdapter RestAdapter restAdapter) {
        this._UL_mInjectionContext = new AnonymousClass0QC(5, r3);
        this.mIsTrustedUser = TrustedUserModule._UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Boolean_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_is_ULUNDERSCORE_trusted_ULUNDERSCORE_user_ULSEP_ACCESS_METHOD(r3);
        this.mMethods = (Methods) restAdapter.create(Methods.class);
    }

    public static class ServiceException extends Exception {
        public ServiceException(String str) {
            super(str);
        }
    }
}
