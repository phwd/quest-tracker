package com.oculus.http.core.interceptor;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import bolts.Task;
import com.facebook.debug.log.BLog;
import com.facebook.inject.FbInjector;
import com.facebook.inject.InjectionContext;
import com.facebook.inject.InjectorLike;
import com.facebook.inject.Lazy;
import com.facebook.inject.UltralightLazy;
import com.facebook.inject.UltralightProvider;
import com.facebook.ultralight.UL;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.oculus.auth.credentials.Credentials;
import com.oculus.auth.credentials.CredentialsModule;
import com.oculus.auth.storage.AuthDatastore;
import com.oculus.auth.storage.StorageModule;
import com.oculus.auth.util.AccessTokenUtils;
import com.oculus.base.app.AppInfo;
import com.oculus.base.app.AppInfoModule;
import com.oculus.dsatauth.DsatFetcher;
import com.oculus.dsatauth.DsatauthModule;
import com.oculus.http.core.base.ApiError;
import com.oculus.http.core.common.Authorization;
import com.oculus.http.core.common.DsatAuthorization;
import com.oculus.http.core.interceptor.InterceptorModule;
import com.oculus.logging.utils.Event;
import com.oculus.logging.utils.EventManager;
import com.oculus.logging.utils.UtilsModule;
import com.oculus.os.DeviceAuth;
import com.oculus.ossdk.inject.OsSdkModule;
import com.oculus.util.constants.OculusConstants;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import javax.inject.Provider;
import okhttp3.Request;
import okhttp3.Response;

public class OculusAuthorizationInterceptor extends AuthorizationInterceptor {
    private static final String ACTION_AUTO_LOGOUT = "com.oculus.auth.ACTION_AUTO_LOGOUT";
    private static final long CREDENTIALS_UPDATE_WINDOW_MS = TimeUnit.SECONDS.toMillis(5);
    private static final long DSAT_FETCH_TIMEOUT_SECONDS = 30;
    private static final String EVENT_AUTH_ACTION = "oculus_mobile_auth_action";
    private static final String EXTRA_ACTION = "action";
    private static final String EXTRA_ERROR_CODE = "error_code";
    private static final String EXTRA_ERROR_MESSAGE = "error_message";
    private static final String EXTRA_ERROR_SUBCODE = "error_subcode";
    private static final String EXTRA_ERROR_TYPE = "error_type";
    private static final String EXTRA_ERROR_USER_MESSAGE = "error_user_message";
    private static final String EXTRA_ERROR_USER_TITLE = "error_user_title";
    private static final String TAG = OculusAuthorizationInterceptor.class.getSimpleName();
    private InjectionContext $ul_mInjectionContext;
    private final Provider<AuthDatastore> mAuthDatastoreProvider;
    private final Provider<Credentials> mCredentialsProvider;

    public static final Lazy $ul_$xXXcom_facebook_inject_Lazy$x3Ccom_oculus_http_core_interceptor_OculusAuthorizationInterceptor$x3E$xXXACCESS_METHOD(InjectorLike $ul_injector) {
        return UltralightLazy.get(InterceptorModule.UL_id.$ul_$xXXcom_oculus_http_core_interceptor_OculusAuthorizationInterceptor$xXXBINDING_ID, $ul_injector);
    }

    public static final OculusAuthorizationInterceptor $ul_$xXXcom_oculus_http_core_interceptor_OculusAuthorizationInterceptor$xXXACCESS_METHOD(InjectorLike $ul_injector) {
        return (OculusAuthorizationInterceptor) UL.factorymap.get(InterceptorModule.UL_id.$ul_$xXXcom_oculus_http_core_interceptor_OculusAuthorizationInterceptor$xXXBINDING_ID, $ul_injector);
    }

    public static final OculusAuthorizationInterceptor $ul_$xXXcom_oculus_http_core_interceptor_OculusAuthorizationInterceptor$xXXFACTORY_METHOD(InjectorLike $ul_injector) {
        return new OculusAuthorizationInterceptor($ul_injector);
    }

    public static final Provider $ul_$xXXjavax_inject_Provider$x3Ccom_oculus_http_core_interceptor_OculusAuthorizationInterceptor$x3E$xXXACCESS_METHOD(InjectorLike $ul_injector) {
        return UltralightProvider.get(InterceptorModule.UL_id.$ul_$xXXcom_oculus_http_core_interceptor_OculusAuthorizationInterceptor$xXXBINDING_ID, $ul_injector);
    }

    public OculusAuthorizationInterceptor(InjectorLike $ul_injector) {
        this.$ul_mInjectionContext = new InjectionContext(4, $ul_injector);
        this.mCredentialsProvider = CredentialsModule.$ul_$xXXjavax_inject_Provider$x3Ccom_oculus_auth_credentials_Credentials$x3E$xXXACCESS_METHOD($ul_injector);
        this.mAuthDatastoreProvider = StorageModule.$ul_$xXXjavax_inject_Provider$x3Ccom_oculus_auth_storage_AuthDatastore$x3E$xXXACCESS_METHOD($ul_injector);
    }

    @Override // com.oculus.http.core.interceptor.AuthorizationInterceptor
    public Request.Builder addAuthorization(Request request) throws IOException {
        return request.newBuilder().removeHeader(DsatAuthorization.INTERNAL_PARAMETER_HEADER).header("Authorization", Authorization.generate(getAccessToken(request.header(DsatAuthorization.INTERNAL_PARAMETER_HEADER))));
    }

    public String getAccessToken(String authorizationParameter) throws IOException {
        if (!isDsatRequired(authorizationParameter)) {
            return getCredentialsAccessToken();
        }
        Task<String> fetchTask = ((DsatFetcher) FbInjector.lazyInstance(2, DsatauthModule.UL_id.$ul_$xXXcom_oculus_dsatauth_DsatFetcher$xXXBINDING_ID, this.$ul_mInjectionContext)).fetchDsatElseRegularTokenAsync();
        try {
            if (!fetchTask.waitForCompletion(DSAT_FETCH_TIMEOUT_SECONDS, TimeUnit.SECONDS)) {
                throw createNonRetryableIOException("Timeout during fetching DSAT");
            } else if (fetchTask.isFaulted()) {
                throw createNonRetryableIOException(fetchTask.getError().getMessage());
            } else {
                String dsatOrUserAccessToken = fetchTask.getResult();
                if (TextUtils.isEmpty(dsatOrUserAccessToken)) {
                    return getLoggedOutAccessToken();
                }
                return dsatOrUserAccessToken;
            }
        } catch (InterruptedException e) {
            throw createNonRetryableIOException(e.getMessage());
        }
    }

    private IOException createNonRetryableIOException(String errorMessage) {
        return new ProtocolException(errorMessage);
    }

    private static boolean isDsatRequired(String authorizationParameter) {
        return DsatAuthorization.INTERNAL_HEADER_REQUIRES_DSAT.equals(authorizationParameter);
    }

    private String getCredentialsAccessToken() {
        Credentials credentials = this.mCredentialsProvider.get();
        return credentials != null ? credentials.getAccessToken() : getLoggedOutAccessToken();
    }

    private String getLoggedOutAccessToken() {
        String deviceAccessToken = null;
        try {
            deviceAccessToken = ((DeviceAuth) FbInjector.lazyInstance(1, OsSdkModule.UL_id.$ul_$xXXcom_oculus_os_DeviceAuth$xXXBINDING_ID, this.$ul_mInjectionContext)).fetchToken(OculusConstants.ALPENGLOW_HW_LOGINTOKEN).value();
        } catch (DeviceAuth.BackendException | DeviceAuth.DeviceIdentityException | DeviceAuth.NetworkException | InterruptedException e) {
            BLog.e(TAG, e, "Error fetching device auth token");
            if (e instanceof InterruptedException) {
                Thread.currentThread().interrupt();
            }
        }
        if (TextUtils.isEmpty(deviceAccessToken)) {
            return AccessTokenUtils.createLoggedOutToken((AppInfo) FbInjector.lazyInstance(0, AppInfoModule.UL_id.$ul_$xXXcom_oculus_base_app_AppInfo$xXXBINDING_ID, this.$ul_mInjectionContext));
        }
        return AccessTokenUtils.createLoggedOutToken((AppInfo) FbInjector.lazyInstance(0, AppInfoModule.UL_id.$ul_$xXXcom_oculus_base_app_AppInfo$xXXBINDING_ID, this.$ul_mInjectionContext), deviceAccessToken);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.http.core.interceptor.AuthorizationInterceptor
    @SuppressLint({"BadMethodUse-java.lang.System.currentTimeMillis"})
    public void onUnauthorized(Response response) {
        long credentialsUpdateTimeMillis = this.mAuthDatastoreProvider.get().getCredentialsUpdateTimeMillis();
        long currentTimeMillis = System.currentTimeMillis();
        if (Math.abs(currentTimeMillis - credentialsUpdateTimeMillis) < CREDENTIALS_UPDATE_WINDOW_MS) {
            BLog.d(TAG, "skipping logOut because token was updated recently lastUpdateTimeMillis %d, currentTimeMillis %d", Long.valueOf(credentialsUpdateTimeMillis), Long.valueOf(currentTimeMillis));
            return;
        }
        logAutoLogout(response);
        this.mAuthDatastoreProvider.get().clearCredentials();
    }

    private void logAutoLogout(Response response) {
        ApiError.FBApiErrorResponse.Error error;
        try {
            String body = response.body().string();
            BLog.e(TAG, "Logging user out, response: %s", body);
            ApiError.FBApiErrorResponse errorResponse = (ApiError.FBApiErrorResponse) new Gson().fromJson(body, ApiError.FBApiErrorResponse.class);
            Event event = ((EventManager) FbInjector.lazyInstance(3, UtilsModule.UL_id.$ul_$xXXcom_oculus_logging_utils_EventManager$xXXBINDING_ID, this.$ul_mInjectionContext)).createEvent(EVENT_AUTH_ACTION).addExtra(EXTRA_ACTION, ACTION_AUTO_LOGOUT);
            if (!(errorResponse == null || (error = errorResponse.error) == null)) {
                event.addExtra("error_code", error.code);
                event.addExtra("error_subcode", error.error_subcode);
                safeAddExtra(event, EXTRA_ERROR_TYPE, error.type);
                safeAddExtra(event, "error_message", error.message);
                safeAddExtra(event, EXTRA_ERROR_USER_TITLE, error.error_user_title);
                safeAddExtra(event, EXTRA_ERROR_USER_MESSAGE, error.error_user_msg);
            }
            event.logAndRelease();
        } catch (JsonParseException | IOException e) {
            BLog.w(TAG, e, "Error processing response");
        }
    }

    private static void safeAddExtra(Event event, String key, @Nullable String value) {
        if (value != null) {
            event.addExtra(key, value);
        }
    }
}
