package com.oculus.http.core.interceptor;

import X.AbstractC0192Xx;
import X.BZ;
import X.Mi;
import X.Om;
import X.SZ;
import android.text.TextUtils;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.auth.credentials.Credentials;
import com.oculus.auth.credentials.CredentialsModule;
import com.oculus.auth.storage.AuthDatastore;
import com.oculus.auth.storage.StorageModule;
import com.oculus.auth.util.AccessTokenUtils;
import com.oculus.base.app.AppInfo;
import com.oculus.os.DeviceAuth;
import com.oculus.util.constants.OculusConstants;
import java.util.concurrent.TimeUnit;

@Dependencies({"_UL__ULSEP_com_oculus_auth_credentials_Credentials_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_base_app_AppInfo_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_auth_storage_AuthDatastore_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_os_DeviceAuth_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_dsatauth_DsatFetcher_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_BINDING_ID"})
public class OculusAuthorizationInterceptor extends AuthorizationInterceptor {
    public static final String ACTION_AUTO_LOGOUT = "com.oculus.auth.ACTION_AUTO_LOGOUT";
    public static final long CREDENTIALS_UPDATE_WINDOW_MS = TimeUnit.SECONDS.toMillis(5);
    public static final long DSAT_FETCH_TIMEOUT_SECONDS = 30;
    public static final String EVENT_AUTH_ACTION = "oculus_mobile_auth_action";
    public static final String EXTRA_ACTION = "action";
    public static final String EXTRA_ERROR_CODE = "error_code";
    public static final String EXTRA_ERROR_MESSAGE = "error_message";
    public static final String EXTRA_ERROR_SUBCODE = "error_subcode";
    public static final String EXTRA_ERROR_TYPE = "error_type";
    public static final String EXTRA_ERROR_USER_MESSAGE = "error_user_message";
    public static final String EXTRA_ERROR_USER_TITLE = "error_user_title";
    public static final String TAG = "OculusAuthorizationInterceptor";
    public Om _UL_mInjectionContext;
    @Inject
    public final AbstractC0192Xx<AuthDatastore> mAuthDatastoreProvider;
    @Inject
    public final AbstractC0192Xx<Credentials> mCredentialsProvider;

    public static String A00(OculusAuthorizationInterceptor oculusAuthorizationInterceptor) {
        String str;
        try {
            str = ((DeviceAuth) BZ.A02(1, 15, oculusAuthorizationInterceptor._UL_mInjectionContext)).fetchToken(OculusConstants.ALPENGLOW_HW_LOGINTOKEN).value();
        } catch (DeviceAuth.BackendException | DeviceAuth.DeviceIdentityException | DeviceAuth.NetworkException | InterruptedException e) {
            Mi.A06(TAG, e, "Error fetching device auth token");
            if (e instanceof InterruptedException) {
                Thread.currentThread().interrupt();
            }
            str = null;
        }
        if (TextUtils.isEmpty(str)) {
            return AccessTokenUtils.createLoggedOutToken((AppInfo) BZ.A02(0, 49, oculusAuthorizationInterceptor._UL_mInjectionContext), null);
        }
        return AccessTokenUtils.createLoggedOutToken((AppInfo) BZ.A02(0, 49, oculusAuthorizationInterceptor._UL_mInjectionContext), str);
    }

    @Inject
    public OculusAuthorizationInterceptor(SZ sz) {
        this._UL_mInjectionContext = new Om(4, sz);
        this.mCredentialsProvider = CredentialsModule._UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_auth_credentials_Credentials_ULGT__ULSEP_ACCESS_METHOD(sz);
        this.mAuthDatastoreProvider = StorageModule._UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_auth_storage_AuthDatastore_ULGT__ULSEP_ACCESS_METHOD(sz);
    }
}
