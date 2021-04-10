package com.oculus.http.core.interceptor;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import bolts.Task;
import com.facebook.debug.log.BLog;
import com.facebook.inject.FbInjector;
import com.facebook.inject.InjectionContext;
import com.facebook.inject.InjectorLike;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
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
import com.oculus.http.core.interceptor.InterceptorModule;
import com.oculus.logging.utils.Event;
import com.oculus.logging.utils.EventManager;
import com.oculus.logging.utils.UtilsModule;
import com.oculus.os.DeviceAuth;
import com.oculus.ossdk.inject.OsSdkModule;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import javax.inject.Provider;
import okhttp3.Request;
import okhttp3.Response;

@Dependencies
public class OculusAuthorizationInterceptor extends AuthorizationInterceptor {
    private static final long CREDENTIALS_UPDATE_WINDOW_MS = TimeUnit.SECONDS.toMillis(5);
    private static final String TAG = "OculusAuthorizationInterceptor";
    private InjectionContext _UL_mInjectionContext;
    @Inject
    private final Provider<AuthDatastore> mAuthDatastoreProvider;
    @Inject
    private final Provider<Credentials> mCredentialsProvider;

    @AutoGeneratedAccessMethod
    public static final OculusAuthorizationInterceptor _UL__ULSEP_com_oculus_http_core_interceptor_OculusAuthorizationInterceptor_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (OculusAuthorizationInterceptor) UL.factorymap.get(InterceptorModule.UL_id._UL__ULSEP_com_oculus_http_core_interceptor_OculusAuthorizationInterceptor_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedFactoryMethod
    public static final OculusAuthorizationInterceptor _UL__ULSEP_com_oculus_http_core_interceptor_OculusAuthorizationInterceptor_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        return new OculusAuthorizationInterceptor(injectorLike);
    }

    @Inject
    public OculusAuthorizationInterceptor(InjectorLike injectorLike) {
        this._UL_mInjectionContext = new InjectionContext(4, injectorLike);
        this.mCredentialsProvider = CredentialsModule._UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_auth_credentials_Credentials_ULGT__ULSEP_ACCESS_METHOD(injectorLike);
        this.mAuthDatastoreProvider = StorageModule._UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_auth_storage_AuthDatastore_ULGT__ULSEP_ACCESS_METHOD(injectorLike);
    }

    @Override // com.oculus.http.core.interceptor.AuthorizationInterceptor
    public Request.Builder addAuthorization(Request request) throws IOException {
        return request.newBuilder().removeHeader("Internal-Authorization-Parameter").header("Authorization", Authorization.generate(getAccessToken(request.header("Internal-Authorization-Parameter"))));
    }

    public String getAccessToken(String str) throws IOException {
        if (!isDsatRequired(str)) {
            return getCredentialsAccessToken();
        }
        Task<String> fetchDsatElseRegularTokenAsync = ((DsatFetcher) FbInjector.lazyInstance(2, DsatauthModule.UL_id._UL__ULSEP_com_oculus_dsatauth_DsatFetcher_ULSEP_BINDING_ID, this._UL_mInjectionContext)).fetchDsatElseRegularTokenAsync();
        try {
            if (!fetchDsatElseRegularTokenAsync.waitForCompletion(30, TimeUnit.SECONDS)) {
                throw createNonRetryableIOException("Timeout during fetching DSAT");
            } else if (!fetchDsatElseRegularTokenAsync.isFaulted()) {
                String result = fetchDsatElseRegularTokenAsync.getResult();
                if (TextUtils.isEmpty(result)) {
                    return getLoggedOutAccessToken();
                }
                return result;
            } else {
                throw createNonRetryableIOException(fetchDsatElseRegularTokenAsync.getError().getMessage());
            }
        } catch (InterruptedException e) {
            throw createNonRetryableIOException(e.getMessage());
        }
    }

    private IOException createNonRetryableIOException(String str) {
        return new ProtocolException(str);
    }

    private static boolean isDsatRequired(String str) {
        return "api_requires_dsat".equals(str);
    }

    private String getCredentialsAccessToken() {
        Credentials credentials = this.mCredentialsProvider.get();
        return credentials != null ? credentials.getAccessToken() : getLoggedOutAccessToken();
    }

    private String getLoggedOutAccessToken() {
        String str;
        try {
            str = ((DeviceAuth) FbInjector.lazyInstance(1, OsSdkModule.UL_id._UL__ULSEP_com_oculus_os_DeviceAuth_ULSEP_BINDING_ID, this._UL_mInjectionContext)).fetchToken("3866e1ca90d719f060ac45dba1ea3a24").value();
        } catch (DeviceAuth.BackendException | DeviceAuth.DeviceIdentityException | DeviceAuth.NetworkException | InterruptedException e) {
            BLog.e(TAG, (Throwable) e, "Error fetching device auth token");
            if (e instanceof InterruptedException) {
                Thread.currentThread().interrupt();
            }
            str = null;
        }
        if (TextUtils.isEmpty(str)) {
            return AccessTokenUtils.createLoggedOutToken((AppInfo) FbInjector.lazyInstance(0, AppInfoModule.UL_id._UL__ULSEP_com_oculus_base_app_AppInfo_ULSEP_BINDING_ID, this._UL_mInjectionContext));
        }
        return AccessTokenUtils.createLoggedOutToken((AppInfo) FbInjector.lazyInstance(0, AppInfoModule.UL_id._UL__ULSEP_com_oculus_base_app_AppInfo_ULSEP_BINDING_ID, this._UL_mInjectionContext), str);
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
            String string = response.body().string();
            BLog.e(TAG, "Logging user out, response: %s", string);
            ApiError.FBApiErrorResponse fBApiErrorResponse = (ApiError.FBApiErrorResponse) new Gson().fromJson(string, ApiError.FBApiErrorResponse.class);
            Event addExtra = ((EventManager) FbInjector.lazyInstance(3, UtilsModule.UL_id._UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_BINDING_ID, this._UL_mInjectionContext)).createEvent("oculus_mobile_auth_action").addExtra("action", "com.oculus.auth.ACTION_AUTO_LOGOUT");
            if (!(fBApiErrorResponse == null || (error = fBApiErrorResponse.error) == null)) {
                addExtra.addExtra("error_code", error.code);
                addExtra.addExtra("error_subcode", error.error_subcode);
                safeAddExtra(addExtra, "error_type", error.type);
                safeAddExtra(addExtra, "error_message", error.message);
                safeAddExtra(addExtra, "error_user_title", error.error_user_title);
                safeAddExtra(addExtra, "error_user_message", error.error_user_msg);
            }
            addExtra.logAndRelease();
        } catch (JsonParseException | IOException e) {
            BLog.w(TAG, e, "Error processing response");
        }
    }

    private static void safeAddExtra(Event event, String str, @Nullable String str2) {
        if (str2 != null) {
            event.addExtra(str, str2);
        }
    }
}