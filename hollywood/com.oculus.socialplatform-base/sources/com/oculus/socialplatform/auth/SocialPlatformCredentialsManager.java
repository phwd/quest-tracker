package com.oculus.socialplatform.auth;

import X.AbstractC03180ld;
import X.AnonymousClass0MD;
import X.AnonymousClass0Qj;
import X.AnonymousClass0RE;
import X.AnonymousClass0VB;
import X.AnonymousClass0VF;
import X.AnonymousClass0lg;
import X.AnonymousClass1TK;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import bolts.Continuation;
import bolts.Task;
import bolts.TaskCompletionSource;
import com.facebook.inject.ApplicationScoped;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.aidl.IAuthService2;
import com.oculus.auth.credentials.Credentials;
import com.oculus.auth.credentials.CredentialsManager;
import com.oculus.auth.handler.LoginHandler;
import com.oculus.auth.handler.LogoutHandler;
import com.oculus.auth.receiver.BaseLoginHandler;
import com.oculus.auth.service.contract.ServiceContract;
import com.oculus.authapi.AuthCredentials;
import com.oculus.authapi.AuthError;
import com.oculus.authapi.AuthResultCallback;
import com.oculus.authapi.OVRAuth;
import com.oculus.common.init.INeedInit;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.Nullable;
import javax.inject.Provider;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_authapi_OVRAuth_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_socialplatform_auth_SocialPlatformConfigurationPrefs_ULSEP_BINDING_ID"})
@ApplicationScoped
public class SocialPlatformCredentialsManager implements INeedInit, CredentialsManager, BaseLoginHandler {
    public static final String TAG = "SocialPlatformCredentialsManager";
    public static volatile SocialPlatformCredentialsManager _UL__ULSEP_com_oculus_socialplatform_auth_SocialPlatformCredentialsManager_ULSEP_INSTANCE;
    public AnonymousClass0RE _UL_mInjectionContext;
    @Nullable
    public Credentials mCredentials;
    public final Set<LoginHandler> mLoginHandlers = new HashSet();
    public final Set<LogoutHandler> mLogoutHandlers = new HashSet();
    public final ServiceConnection mServiceConnection = new ServiceConnection() {
        /* class com.oculus.socialplatform.auth.SocialPlatformCredentialsManager.AnonymousClass1 */

        public void onServiceDisconnected(ComponentName componentName) {
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                SocialPlatformCredentialsManager.this.handleAuthQueryCallback(IAuthService2.Stub.asInterface(iBinder).getCredentials());
            } catch (RemoteException e) {
                AnonymousClass0MD.A07(SocialPlatformCredentialsManager.TAG, "Exception while bound to auth service", e);
            } catch (Throwable th) {
                ((Context) AnonymousClass0VF.A03(0, 3, SocialPlatformCredentialsManager.this._UL_mInjectionContext)).unbindService(this);
                throw th;
            }
            ((Context) AnonymousClass0VF.A03(0, 3, SocialPlatformCredentialsManager.this._UL_mInjectionContext)).unbindService(this);
        }
    };

    public interface FetchDsatCallBack {
        void onFetchedDsat(@Nullable String str);
    }

    @Override // com.oculus.common.init.INeedInit
    public void init() {
    }

    @Override // com.oculus.auth.receiver.BaseLoginHandler
    public void onLogin() {
        refreshCredentials((Context) AnonymousClass0VF.A03(0, 3, this._UL_mInjectionContext));
    }

    @AutoGeneratedAccessMethod
    public static final AbstractC03180ld _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_socialplatform_auth_SocialPlatformCredentialsManager_ULGT__ULSEP_ACCESS_METHOD(AnonymousClass0lg r2) {
        return new AnonymousClass0VB(51, r2);
    }

    @AutoGeneratedAccessMethod
    public static final SocialPlatformCredentialsManager _UL__ULSEP_com_oculus_socialplatform_auth_SocialPlatformCredentialsManager_ULSEP_ACCESS_METHOD(AnonymousClass0lg r2) {
        return (SocialPlatformCredentialsManager) AnonymousClass1TK.A00(51, r2, null);
    }

    @AutoGeneratedFactoryMethod
    public static final SocialPlatformCredentialsManager _UL__ULSEP_com_oculus_socialplatform_auth_SocialPlatformCredentialsManager_ULSEP_FACTORY_METHOD(AnonymousClass0lg r4, Object obj) {
        if (_UL__ULSEP_com_oculus_socialplatform_auth_SocialPlatformCredentialsManager_ULSEP_INSTANCE == null) {
            synchronized (SocialPlatformCredentialsManager.class) {
                AnonymousClass0Qj A00 = AnonymousClass0Qj.A00(_UL__ULSEP_com_oculus_socialplatform_auth_SocialPlatformCredentialsManager_ULSEP_INSTANCE, r4);
                if (A00 != null) {
                    try {
                        _UL__ULSEP_com_oculus_socialplatform_auth_SocialPlatformCredentialsManager_ULSEP_INSTANCE = new SocialPlatformCredentialsManager(r4.getApplicationInjector());
                    } finally {
                        A00.A01();
                    }
                }
            }
        }
        return _UL__ULSEP_com_oculus_socialplatform_auth_SocialPlatformCredentialsManager_ULSEP_INSTANCE;
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_socialplatform_auth_SocialPlatformCredentialsManager_ULGT__ULSEP_ACCESS_METHOD(AnonymousClass0lg r2) {
        return new AnonymousClass0VB(51, r2);
    }

    private Task<String> fetchDsatElseRegularToken() {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        ((OVRAuth) AnonymousClass0VF.A03(1, 63, this._UL_mInjectionContext)).fetchDeviceScopedCredentials(new AuthResultCallback<AuthCredentials, AuthError>() {
            /* class com.oculus.socialplatform.auth.SocialPlatformCredentialsManager.AnonymousClass3 */

            @Override // com.oculus.authapi.AuthResultCallback
            public void onError(AuthError authError) {
                int i = authError.mErrorCode;
                if (i == -16) {
                    AnonymousClass0MD.A0A(SocialPlatformCredentialsManager.TAG, "Unable to fetch DSAT: ", Integer.valueOf(i));
                    taskCompletionSource.setResult(null);
                    return;
                }
                taskCompletionSource.setError(authError);
            }

            public void onResult(AuthCredentials authCredentials) {
                taskCompletionSource.setResult(authCredentials.mAccessToken);
            }
        });
        return taskCompletionSource.task;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handleAuthQueryCallback(Bundle bundle) {
        String str;
        String str2;
        if (bundle == null) {
            str = TAG;
            str2 = "null bundle received from OVRAuth";
        } else {
            String string = bundle.getString("user_id");
            String string2 = bundle.getString("access_token");
            if (TextUtils.isEmpty(string) || TextUtils.isEmpty(string2)) {
                str = TAG;
                str2 = "Received invalid uid or access token from OVRAuth";
            } else {
                synchronized (this) {
                    Credentials credentials = this.mCredentials;
                    if (credentials == null || !string.equals(credentials.mUserId) || !string2.equals(credentials.mAccessToken)) {
                        this.mCredentials = new Credentials(string, string2);
                        ((SocialPlatformConfigurationPrefs) AnonymousClass0VF.A03(2, 46, this._UL_mInjectionContext)).setCredentials(string, string2);
                        for (LoginHandler loginHandler : this.mLoginHandlers) {
                            loginHandler.afterLoginAsync();
                        }
                    }
                }
                return;
            }
        }
        AnonymousClass0MD.A05(str, str2);
    }

    private void refreshCredentials(Context context) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.oculus.horizon", ServiceContract.BOUND_SERVICE));
        if (!context.bindService(intent, this.mServiceConnection, 1)) {
            AnonymousClass0MD.A04(TAG, "Unable to bind to the auth service");
        }
    }

    public void addLoginHandler(LoginHandler loginHandler) {
        this.mLoginHandlers.add(loginHandler);
    }

    public void addLogoutHandler(LogoutHandler logoutHandler) {
        this.mLogoutHandlers.add(logoutHandler);
    }

    @Override // com.oculus.auth.receiver.BaseLoginHandler
    public void onLogout() {
        if (this.mCredentials != null) {
            for (LogoutHandler logoutHandler : this.mLogoutHandlers) {
                logoutHandler.beforeLogout();
            }
            this.mCredentials = null;
            ((SocialPlatformConfigurationPrefs) AnonymousClass0VF.A03(2, 46, this._UL_mInjectionContext)).setCredentials(null, null);
        }
    }

    @Inject
    public SocialPlatformCredentialsManager(AnonymousClass0lg r5) {
        Credentials credentials;
        AnonymousClass0RE r1 = new AnonymousClass0RE(3, r5);
        this._UL_mInjectionContext = r1;
        String userId = ((SocialPlatformConfigurationPrefs) AnonymousClass0VF.A03(2, 46, r1)).getUserId();
        String accessToken = ((SocialPlatformConfigurationPrefs) AnonymousClass0VF.A03(2, 46, this._UL_mInjectionContext)).getAccessToken();
        synchronized (this) {
            if (TextUtils.isEmpty(userId) || TextUtils.isEmpty(accessToken)) {
                credentials = null;
            } else {
                credentials = new Credentials(userId, accessToken);
            }
            this.mCredentials = credentials;
        }
        refreshCredentials((Context) AnonymousClass0VF.A03(0, 3, this._UL_mInjectionContext));
    }

    public static /* synthetic */ String access$100() {
        return TAG;
    }

    public void fetchDeviceScopedAccessToken(final FetchDsatCallBack fetchDsatCallBack) {
        final Task<String> fetchDsatElseRegularToken = fetchDsatElseRegularToken();
        fetchDsatElseRegularToken.continueWith(new Continuation<String, Void>() {
            /* class com.oculus.socialplatform.auth.SocialPlatformCredentialsManager.AnonymousClass2 */

            @Override // bolts.Continuation
            public Void then(Task<String> task) {
                if (fetchDsatElseRegularToken.isFaulted()) {
                    AnonymousClass0MD.A0A(SocialPlatformCredentialsManager.TAG, "Unable to fetch DSAT.", task.getError().getMessage());
                    fetchDsatCallBack.onFetchedDsat(null);
                    return null;
                }
                String result = task.getResult();
                if (TextUtils.isEmpty(result)) {
                    AnonymousClass0MD.A05(SocialPlatformCredentialsManager.TAG, "Backend returned null or empty DSAT");
                }
                fetchDsatCallBack.onFetchedDsat(result);
                return null;
            }
        });
    }

    @Override // com.oculus.auth.credentials.CredentialsManager
    @Nullable
    public Credentials getCredentials() {
        return this.mCredentials;
    }

    @Override // com.oculus.auth.receiver.BaseLoginHandler
    public void onRelogin() {
        onLogin();
    }
}
