package com.oculus.modules;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;
import com.oculus.auth.service.contract.AuthTwoFactorMethod;
import com.oculus.authapi.AuthAuthWithOculusEmailAndPasswordForAccountLinkingResult;
import com.oculus.authapi.AuthCredentials;
import com.oculus.authapi.AuthError;
import com.oculus.authapi.AuthFbLoginError;
import com.oculus.authapi.AuthFbLoginResult;
import com.oculus.authapi.AuthFetchFbInfoForAccountLinkingError;
import com.oculus.authapi.AuthFetchFbInfoForAccountLinkingResult;
import com.oculus.authapi.AuthLoginError;
import com.oculus.authapi.AuthLoginWithFbAuthError;
import com.oculus.authapi.AuthResultCallback;
import com.oculus.authapi.AuthVerifyLoginForAccountLinkingResult;
import com.oculus.authapi.OVRAuth;
import com.oculus.modules.codegen.AuthModule;
import com.oculus.modules.codegen.Resolver;
import com.oculus.secure.trustedapp.CallerInfoHelper;
import java.util.ArrayList;
import javax.annotation.Nullable;

public class AuthModuleImpl extends AuthModule {
    public static final String AUTH_BROADCAST_LOGIN = "com.oculus.auth.BROADCAST_LOGIN";
    public static final String TAG = "AuthModuleImpl";
    public final Context mContext;
    public OVRAuth mOVRAuth;

    private void onLogin() {
        emitOnLogin(null);
    }

    private synchronized OVRAuth ovrAuth() {
        OVRAuth oVRAuth;
        oVRAuth = this.mOVRAuth;
        if (oVRAuth == null) {
            oVRAuth = new OVRAuth(this.mContext, new OVRAuth.CallerInfoProvider() {
                /* class com.oculus.modules.AuthModuleImpl.AnonymousClass19 */

                @Override // com.oculus.authapi.OVRAuth.CallerInfoProvider
                public Intent attachCallerInfo(Intent intent) {
                    try {
                        CallerInfoHelper.attachCallerInfo(intent, AuthModuleImpl.this.mContext, null);
                        return intent;
                    } catch (Exception e) {
                        Log.e(AuthModuleImpl.TAG, "Error attaching caller info", e);
                        return intent;
                    }
                }
            });
            this.mOVRAuth = oVRAuth;
        }
        return oVRAuth;
    }

    @Override // com.oculus.modules.codegen.AuthModule
    public double marshallJSConstantErrorCodeAccountConflict() {
        return -18.0d;
    }

    @Override // com.oculus.modules.codegen.AuthModule
    public double marshallJSConstantErrorCodeAnomalousLogin() {
        return -2.0d;
    }

    @Override // com.oculus.modules.codegen.AuthModule
    public double marshallJSConstantErrorCodeCannotMergeAccountsBecauseAlreadyLinked() {
        return -19.0d;
    }

    @Override // com.oculus.modules.codegen.AuthModule
    public double marshallJSConstantErrorCodeDeviceAlreadyClaimed() {
        return -14.0d;
    }

    @Override // com.oculus.modules.codegen.AuthModule
    public double marshallJSConstantErrorCodeHttpError() {
        return -6.0d;
    }

    @Override // com.oculus.modules.codegen.AuthModule
    public double marshallJSConstantErrorCodeInvalidCreds() {
        return -1.0d;
    }

    @Override // com.oculus.modules.codegen.AuthModule
    public double marshallJSConstantErrorCodeLoginApprovalsInvalidCode() {
        return -10.0d;
    }

    @Override // com.oculus.modules.codegen.AuthModule
    public double marshallJSConstantErrorCodeNetworkError() {
        return -4.0d;
    }

    @Override // com.oculus.modules.codegen.AuthModule
    public double marshallJSConstantErrorCodeNoDeviceIdentity() {
        return -16.0d;
    }

    @Override // com.oculus.modules.codegen.AuthModule
    public double marshallJSConstantErrorCodeNoLinkedAccount() {
        return -13.0d;
    }

    @Override // com.oculus.modules.codegen.AuthModule
    public double marshallJSConstantErrorCodeRateLimited() {
        return -5.0d;
    }

    @Override // com.oculus.modules.codegen.AuthModule
    public double marshallJSConstantErrorCodeTwoFactorLoginRequired() {
        return -8.0d;
    }

    @Override // com.oculus.modules.codegen.AuthModule
    public double marshallJSConstantErrorCodeUserAlreadyOnDevice() {
        return -17.0d;
    }

    public static /* synthetic */ AuthModule.AuthLoginResult access$100() {
        return new AuthModule.AuthLoginResult();
    }

    public static /* synthetic */ AuthModule.AuthSendTwoFactorCodeResult access$1500() {
        return new AuthModule.AuthSendTwoFactorCodeResult();
    }

    public static /* synthetic */ AuthModule.AuthVerifyLoginResult access$1700() {
        return new AuthModule.AuthVerifyLoginResult();
    }

    public static /* synthetic */ AuthModule.AuthResult access$2200() {
        return new AuthModule.AuthResult();
    }

    public static /* synthetic */ AuthModule.AuthFbLinkResult access$300() {
        return new AuthModule.AuthFbLinkResult();
    }

    public static /* synthetic */ AuthModule.AuthLoginWithFbAuthResult access$500() {
        return new AuthModule.AuthLoginWithFbAuthResult();
    }

    @Override // com.oculus.panellib.modules.AbstractBroadcastReceiverModule
    public IntentFilter getIntentFilter() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.oculus.auth.BROADCAST_LOGIN");
        return intentFilter;
    }

    public AuthModuleImpl(Context context) {
        super(context);
        this.mContext = context;
    }

    public static /* synthetic */ String access$000() {
        return TAG;
    }

    @Override // com.oculus.modules.codegen.AuthModule
    public void authWithOculusEmailAndPasswordForAccountLinkingImpl(String str, String str2, final Resolver<AuthModule.AuthAuthWithOculusEmailAndPasswordForAccountLinkingResult> resolver) {
        ovrAuth().authWithOculusEmailAndPasswordForAccountLinking(str, str2, new AuthResultCallback<AuthAuthWithOculusEmailAndPasswordForAccountLinkingResult, AuthLoginError>() {
            /* class com.oculus.modules.AuthModuleImpl.AnonymousClass7 */

            public void onError(AuthLoginError authLoginError) {
                Log.e(AuthModuleImpl.TAG, "authWithOculusEmailAndPasswordForAccountLinkingImpl() failed", authLoginError);
                resolver.resolve(AuthModuleImpl.makeAuthWithEmailAndPasswordResult(authLoginError));
            }

            public void onResult(AuthAuthWithOculusEmailAndPasswordForAccountLinkingResult authAuthWithOculusEmailAndPasswordForAccountLinkingResult) {
                resolver.resolve(AuthModuleImpl.makeAuthWithEmailAndPasswordResult(authAuthWithOculusEmailAndPasswordForAccountLinkingResult));
            }
        });
    }

    @Override // com.oculus.modules.codegen.AuthModule
    public void checkFbMachineApprovalImpl(String str, String str2, final Resolver<AuthModule.AuthCheckFbMachineApprovalResult> resolver) {
        ovrAuth().checkFbMachineApproval(str, str2, new AuthResultCallback<Boolean, AuthError>() {
            /* class com.oculus.modules.AuthModuleImpl.AnonymousClass9 */

            @Override // com.oculus.authapi.AuthResultCallback
            public void onError(AuthError authError) {
                Log.e(AuthModuleImpl.TAG, "checkFbMachineApprovalImpl() failed", authError);
                resolver.resolve(AuthModuleImpl.makeCheckFbMachineApprovalResult(authError));
            }

            public void onResult(Boolean bool) {
                resolver.resolve(AuthModuleImpl.makeCheckFbMachineApprovalResult(bool.booleanValue()));
            }
        });
    }

    @Override // com.oculus.modules.codegen.AuthModule
    public void completeFbLoginAfterMachineApprovalImpl(String str, String str2, String str3, final Resolver<AuthModule.AuthFbLoginResult> resolver) {
        ovrAuth().completeFbLoginAfterMachineApproval(str, str2, str3, new AuthResultCallback<AuthFbLoginResult, AuthError>() {
            /* class com.oculus.modules.AuthModuleImpl.AnonymousClass14 */

            @Override // com.oculus.authapi.AuthResultCallback
            public void onError(AuthError authError) {
                Log.e(AuthModuleImpl.TAG, "completeFbLoginAfterMachineApproval() failed", authError);
                resolver.resolve(AuthModuleImpl.makeFbLoginResult(authError));
            }

            public void onResult(AuthFbLoginResult authFbLoginResult) {
                resolver.resolve(AuthModuleImpl.makeFbLoginResult(authFbLoginResult));
            }
        });
    }

    @Override // com.oculus.modules.codegen.AuthModule
    public void completeFbLoginWithTwoFactorCodeImpl(String str, String str2, String str3, String str4, String str5, final Resolver<AuthModule.AuthFbLoginResult> resolver) {
        ovrAuth().completeFbLoginWithTwoFactorCode(str, str2, str3, str4, str5, new AuthResultCallback<AuthFbLoginResult, AuthError>() {
            /* class com.oculus.modules.AuthModuleImpl.AnonymousClass15 */

            @Override // com.oculus.authapi.AuthResultCallback
            public void onError(AuthError authError) {
                Log.e(AuthModuleImpl.TAG, "completeFbLoginWithTwoFactorCodeImpl() failed", authError);
                resolver.resolve(AuthModuleImpl.makeFbLoginResult(authError));
            }

            public void onResult(AuthFbLoginResult authFbLoginResult) {
                resolver.resolve(AuthModuleImpl.makeFbLoginResult(authFbLoginResult));
            }
        });
    }

    @Override // com.oculus.modules.codegen.AuthModule
    public void ensureDeviceOwnershipImpl(final Resolver<AuthModule.AuthResult> resolver) {
        ovrAuth().ensureDeviceOwnership(new AuthResultCallback<Void, AuthError>() {
            /* class com.oculus.modules.AuthModuleImpl.AnonymousClass17 */

            @Override // com.oculus.authapi.AuthResultCallback
            public void onError(AuthError authError) {
                Log.e(AuthModuleImpl.TAG, "ensureDeviceOwnershipImpl() failed", authError);
                resolver.resolve(AuthModuleImpl.makeResult(authError));
            }

            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
            @Override // com.oculus.authapi.AuthResultCallback
            public /* bridge */ /* synthetic */ void onResult(Void r2) {
                onResult((Void) null);
            }

            public void onResult(Void r3) {
                resolver.resolve(new AuthModule.AuthResult());
            }
        });
    }

    @Override // com.oculus.modules.codegen.AuthModule
    public void fetchDeviceScopedCredentialsImpl(final Resolver<AuthModule.CredentialsResult> resolver) {
        try {
            ovrAuth().fetchDeviceScopedCredentials(new AuthResultCallback<AuthCredentials, AuthError>() {
                /* class com.oculus.modules.AuthModuleImpl.AnonymousClass18 */

                @Override // com.oculus.authapi.AuthResultCallback
                public void onError(AuthError authError) {
                    Log.e(AuthModuleImpl.TAG, "fetchDeviceScopedCredentialsImpl() failed", authError);
                    resolver.resolve(AuthModuleImpl.makeCredentialsResult(authError));
                }

                public void onResult(AuthCredentials authCredentials) {
                    resolver.resolve(AuthModuleImpl.makeCredentialsResult(authCredentials));
                }
            });
        } catch (Exception e) {
            e.getMessage();
            resolver.reject(e);
        }
    }

    @Override // com.oculus.modules.codegen.AuthModule
    public void fetchFbInfoForAccountLinkingImpl(String str, final Resolver<AuthModule.AuthFetchFbInfoForAccountLinkingResult> resolver) {
        ovrAuth().fetchFbInfoForAccountLinking(str, new AuthResultCallback<AuthFetchFbInfoForAccountLinkingResult, AuthFetchFbInfoForAccountLinkingError>() {
            /* class com.oculus.modules.AuthModuleImpl.AnonymousClass6 */

            public void onError(AuthFetchFbInfoForAccountLinkingError authFetchFbInfoForAccountLinkingError) {
                Log.e(AuthModuleImpl.TAG, "fetchFbInfoForAccountLinkingImpl() failed", authFetchFbInfoForAccountLinkingError);
                resolver.resolve(AuthModuleImpl.makeFetchFbInfoForAccountLinkingResult(authFetchFbInfoForAccountLinkingError));
            }

            public void onResult(AuthFetchFbInfoForAccountLinkingResult authFetchFbInfoForAccountLinkingResult) {
                resolver.resolve(AuthModuleImpl.makeFetchFbInfoForAccountLinkingResult(authFetchFbInfoForAccountLinkingResult));
            }
        });
    }

    @Override // com.oculus.modules.codegen.AuthModule
    public void linkFbAccountImpl(String str, String str2, final Resolver<AuthModule.AuthFbLinkResult> resolver) {
        ovrAuth().linkFbAccount(str, str2, null, null, new AuthResultCallback<Void, AuthError>() {
            /* class com.oculus.modules.AuthModuleImpl.AnonymousClass3 */

            @Override // com.oculus.authapi.AuthResultCallback
            public void onError(AuthError authError) {
                Log.e(AuthModuleImpl.TAG, "linkFbAccountImpl() failed", authError);
                resolver.resolve(AuthModuleImpl.makeLinkFbAccountResult(authError));
            }

            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
            @Override // com.oculus.authapi.AuthResultCallback
            public /* bridge */ /* synthetic */ void onResult(Void r2) {
                onResult((Void) null);
            }

            public void onResult(Void r3) {
                resolver.resolve(new AuthModule.AuthFbLinkResult());
            }
        });
    }

    @Override // com.oculus.modules.codegen.AuthModule
    public void loginToFbImpl(String str, String str2, final Resolver<AuthModule.AuthFbLoginResult> resolver) {
        ovrAuth().loginToFb(str, str2, new AuthResultCallback<AuthFbLoginResult, AuthFbLoginError>() {
            /* class com.oculus.modules.AuthModuleImpl.AnonymousClass8 */

            public void onError(AuthFbLoginError authFbLoginError) {
                Log.e(AuthModuleImpl.TAG, "loginToFbImpl() failed", authFbLoginError);
                resolver.resolve(AuthModuleImpl.makeFbLoginResult(authFbLoginError));
            }

            public void onResult(AuthFbLoginResult authFbLoginResult) {
                resolver.resolve(AuthModuleImpl.makeFbLoginResult(authFbLoginResult));
            }
        });
    }

    @Override // com.oculus.modules.codegen.AuthModule
    public void loginWithEmailAndPasswordImpl(String str, String str2, final Resolver<AuthModule.AuthLoginResult> resolver) {
        ovrAuth().loginWithEmailAndPassword(str, str2, new AuthResultCallback<Void, AuthLoginError>() {
            /* class com.oculus.modules.AuthModuleImpl.AnonymousClass1 */

            public void onError(AuthLoginError authLoginError) {
                Log.e(AuthModuleImpl.TAG, "loginWithEmailAndPasswordImpl() failed", authLoginError);
                resolver.resolve(AuthModuleImpl.makeLoginResult(authLoginError));
            }

            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
            @Override // com.oculus.authapi.AuthResultCallback
            public /* bridge */ /* synthetic */ void onResult(Void r2) {
                onResult((Void) null);
            }

            public void onResult(Void r3) {
                resolver.resolve(new AuthModule.AuthLoginResult());
            }
        });
    }

    @Override // com.oculus.modules.codegen.AuthModule
    public void loginWithFbAuthImpl(String str, final Resolver<AuthModule.AuthLoginWithFbAuthResult> resolver) {
        ovrAuth().loginWithFbAuth(str, new AuthResultCallback<Void, AuthLoginWithFbAuthError>() {
            /* class com.oculus.modules.AuthModuleImpl.AnonymousClass4 */

            public void onError(AuthLoginWithFbAuthError authLoginWithFbAuthError) {
                Log.e(AuthModuleImpl.TAG, "loginWithFbAuthImpl() failed", authLoginWithFbAuthError);
                resolver.resolve(AuthModuleImpl.makeLoginWithFbAuthResult(authLoginWithFbAuthError));
            }

            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
            @Override // com.oculus.authapi.AuthResultCallback
            public /* bridge */ /* synthetic */ void onResult(Void r2) {
                onResult((Void) null);
            }

            public void onResult(Void r3) {
                resolver.resolve(new AuthModule.AuthLoginWithFbAuthResult());
            }
        });
    }

    @Override // com.oculus.panellib.modules.AbstractBroadcastReceiverModule
    public void processIntent(Intent intent, boolean z) {
        String action = intent.getAction();
        if (action.hashCode() != 698177661 || !action.equals("com.oculus.auth.BROADCAST_LOGIN")) {
            Log.e(TAG, String.format("Unhandled action: %s", action));
        } else {
            emitOnLogin(null);
        }
    }

    @Override // com.oculus.modules.codegen.AuthModule
    public void reloginWithEmailAndPasswordImpl(String str, String str2, final Resolver<AuthModule.AuthLoginResult> resolver) {
        ovrAuth().reloginWithEmailAndPassword(str, str2, new AuthResultCallback<Void, AuthLoginError>() {
            /* class com.oculus.modules.AuthModuleImpl.AnonymousClass2 */

            public void onError(AuthLoginError authLoginError) {
                Log.e(AuthModuleImpl.TAG, "reloginWithEmailAndPasswordImpl() failed", authLoginError);
                resolver.resolve(AuthModuleImpl.makeLoginResult(authLoginError));
            }

            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
            @Override // com.oculus.authapi.AuthResultCallback
            public /* bridge */ /* synthetic */ void onResult(Void r2) {
                onResult((Void) null);
            }

            public void onResult(Void r3) {
                resolver.resolve(new AuthModule.AuthLoginResult());
            }
        });
    }

    @Override // com.oculus.modules.codegen.AuthModule
    public void reloginWithFbAuthImpl(String str, final Resolver<AuthModule.AuthLoginWithFbAuthResult> resolver) {
        ovrAuth().reloginWithFbAuth(str, new AuthResultCallback<Void, AuthLoginWithFbAuthError>() {
            /* class com.oculus.modules.AuthModuleImpl.AnonymousClass5 */

            public void onError(AuthLoginWithFbAuthError authLoginWithFbAuthError) {
                Log.e(AuthModuleImpl.TAG, "reloginWithFbAuthImpl() failed", authLoginWithFbAuthError);
                resolver.resolve(AuthModuleImpl.makeLoginWithFbAuthResult(authLoginWithFbAuthError));
            }

            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
            @Override // com.oculus.authapi.AuthResultCallback
            public /* bridge */ /* synthetic */ void onResult(Void r2) {
                onResult((Void) null);
            }

            public void onResult(Void r3) {
                resolver.resolve(new AuthModule.AuthLoginWithFbAuthResult());
            }
        });
    }

    @Override // com.oculus.modules.codegen.AuthModule
    public void sendTwoFactorCodeImpl(String str, String str2, final Resolver<AuthModule.AuthSendTwoFactorCodeResult> resolver) {
        ovrAuth().sendTwoFactorCode(str, str2, new AuthResultCallback<Void, AuthError>() {
            /* class com.oculus.modules.AuthModuleImpl.AnonymousClass10 */

            @Override // com.oculus.authapi.AuthResultCallback
            public void onError(AuthError authError) {
                Log.e(AuthModuleImpl.TAG, "sendTwoFactorCodeImpl() failed", authError);
                resolver.resolve(AuthModuleImpl.makeSendTwoFactorCodeResult(authError));
            }

            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
            @Override // com.oculus.authapi.AuthResultCallback
            public /* bridge */ /* synthetic */ void onResult(Void r2) {
                onResult((Void) null);
            }

            public void onResult(Void r3) {
                resolver.resolve(new AuthModule.AuthSendTwoFactorCodeResult());
            }
        });
    }

    @Override // com.oculus.modules.codegen.AuthModule
    public void statusImpl(final Resolver<AuthModule.AuthStatusResult> resolver) {
        ovrAuth().status(new ResultReceiver(null) {
            /* class com.oculus.modules.AuthModuleImpl.AnonymousClass16 */

            public void onReceiveResult(int i, Bundle bundle) {
                String str;
                String str2;
                AuthModule.AuthStatusResult authStatusResult = new AuthModule.AuthStatusResult();
                if (i == 1) {
                    AuthModule.AuthError authError = new AuthModule.AuthError();
                    authStatusResult.error = authError;
                    authError.code = (double) bundle.getInt("error_code");
                    str = AuthModuleImpl.TAG;
                    str2 = "statusImpl() failed";
                } else {
                    authStatusResult.userId = bundle.getString("user_id");
                    authStatusResult.accessToken = bundle.getString("access_token");
                    str = AuthModuleImpl.TAG;
                    str2 = "statusImpl() succeeded";
                }
                Log.e(str, str2);
                resolver.resolve(authStatusResult);
            }
        });
    }

    @Override // com.oculus.modules.codegen.AuthModule
    public void verifyLoginForAccountLinkingImpl(@Nullable String str, String str2, @Nullable String str3, @Nullable String str4, final Resolver<AuthModule.AuthVerifyLoginForAccountLinkingResult> resolver) {
        ovrAuth().verifyLoginForAccountLinking(str, str2, str3, str4, false, new AuthResultCallback<AuthVerifyLoginForAccountLinkingResult, AuthLoginError>() {
            /* class com.oculus.modules.AuthModuleImpl.AnonymousClass13 */

            public void onError(AuthLoginError authLoginError) {
                Log.e(AuthModuleImpl.TAG, "verifyLoginForAccountLinkingImpl() failed", authLoginError);
                resolver.resolve(AuthModuleImpl.makeVerifyLoginForAccountLinkingResult(authLoginError));
            }

            public void onResult(AuthVerifyLoginForAccountLinkingResult authVerifyLoginForAccountLinkingResult) {
                resolver.resolve(AuthModuleImpl.makeVerifyLoginForAccountLinkingResult(authVerifyLoginForAccountLinkingResult));
            }
        });
    }

    @Override // com.oculus.modules.codegen.AuthModule
    public void verifyLoginImpl(@Nullable String str, String str2, @Nullable String str3, @Nullable String str4, boolean z, final Resolver<AuthModule.AuthVerifyLoginResult> resolver) {
        ovrAuth().verifyLogin(str, str2, str3, str4, false, new AuthResultCallback<Void, AuthError>() {
            /* class com.oculus.modules.AuthModuleImpl.AnonymousClass11 */

            @Override // com.oculus.authapi.AuthResultCallback
            public void onError(AuthError authError) {
                resolver.resolve(AuthModuleImpl.makeVerifyLoginResult(authError));
            }

            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
            @Override // com.oculus.authapi.AuthResultCallback
            public /* bridge */ /* synthetic */ void onResult(Void r2) {
                onResult((Void) null);
            }

            public void onResult(Void r3) {
                resolver.resolve(new AuthModule.AuthVerifyLoginResult());
            }
        });
    }

    @Override // com.oculus.modules.codegen.AuthModule
    public void verifyReloginImpl(@Nullable String str, String str2, @Nullable String str3, @Nullable String str4, boolean z, final Resolver<AuthModule.AuthVerifyLoginResult> resolver) {
        ovrAuth().verifyRelogin(str, str2, str3, str4, false, new AuthResultCallback<Void, AuthError>() {
            /* class com.oculus.modules.AuthModuleImpl.AnonymousClass12 */

            @Override // com.oculus.authapi.AuthResultCallback
            public void onError(AuthError authError) {
                resolver.resolve(AuthModuleImpl.makeVerifyLoginResult(authError));
            }

            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
            @Override // com.oculus.authapi.AuthResultCallback
            public /* bridge */ /* synthetic */ void onResult(Void r2) {
                onResult((Void) null);
            }

            public void onResult(Void r3) {
                resolver.resolve(new AuthModule.AuthVerifyLoginResult());
            }
        });
    }

    public static AuthModule.AuthAuthWithOculusEmailAndPasswordForAccountLinkingResult makeAuthWithEmailAndPasswordResult(AuthAuthWithOculusEmailAndPasswordForAccountLinkingResult authAuthWithOculusEmailAndPasswordForAccountLinkingResult) {
        AuthModule.AuthAuthWithOculusEmailAndPasswordForAccountLinkingResult authAuthWithOculusEmailAndPasswordForAccountLinkingResult2 = new AuthModule.AuthAuthWithOculusEmailAndPasswordForAccountLinkingResult();
        authAuthWithOculusEmailAndPasswordForAccountLinkingResult2.accessToken = authAuthWithOculusEmailAndPasswordForAccountLinkingResult.mAccessToken;
        authAuthWithOculusEmailAndPasswordForAccountLinkingResult2.userId = authAuthWithOculusEmailAndPasswordForAccountLinkingResult.mUserId;
        return authAuthWithOculusEmailAndPasswordForAccountLinkingResult2;
    }

    public static AuthModule.AuthAuthWithOculusEmailAndPasswordForAccountLinkingResult makeAuthWithEmailAndPasswordResult(AuthLoginError authLoginError) {
        AuthModule.AuthAuthWithOculusEmailAndPasswordForAccountLinkingResult authAuthWithOculusEmailAndPasswordForAccountLinkingResult = new AuthModule.AuthAuthWithOculusEmailAndPasswordForAccountLinkingResult();
        AuthModule.AuthLoginError authLoginError2 = new AuthModule.AuthLoginError();
        authAuthWithOculusEmailAndPasswordForAccountLinkingResult.error = authLoginError2;
        authLoginError2.code = (double) authLoginError.mErrorCode;
        authLoginError2.message = authLoginError.getMessage();
        AuthModule.AuthLoginError authLoginError3 = authAuthWithOculusEmailAndPasswordForAccountLinkingResult.error;
        authLoginError3.title = authLoginError.mErrorTitle;
        authLoginError3.nonce = authLoginError.mNonce;
        ArrayList<AuthTwoFactorMethod> arrayList = authLoginError.mTwoFactorMethods;
        authLoginError3.methodId = (arrayList == null || arrayList.get(0) == null) ? null : arrayList.get(0).id;
        return authAuthWithOculusEmailAndPasswordForAccountLinkingResult;
    }

    public static AuthModule.AuthCheckFbMachineApprovalResult makeCheckFbMachineApprovalResult(AuthError authError) {
        AuthModule.AuthCheckFbMachineApprovalResult authCheckFbMachineApprovalResult = new AuthModule.AuthCheckFbMachineApprovalResult();
        AuthModule.AuthError authError2 = new AuthModule.AuthError();
        authCheckFbMachineApprovalResult.error = authError2;
        authError2.code = (double) authError.mErrorCode;
        authError2.message = authError.getMessage();
        authCheckFbMachineApprovalResult.error.title = authError.mErrorTitle;
        return authCheckFbMachineApprovalResult;
    }

    public static AuthModule.AuthCheckFbMachineApprovalResult makeCheckFbMachineApprovalResult(boolean z) {
        AuthModule.AuthCheckFbMachineApprovalResult authCheckFbMachineApprovalResult = new AuthModule.AuthCheckFbMachineApprovalResult();
        authCheckFbMachineApprovalResult.approved = Boolean.valueOf(z);
        return authCheckFbMachineApprovalResult;
    }

    public static AuthModule.CredentialsResult makeCredentialsResult(AuthCredentials authCredentials) {
        AuthModule.CredentialsResult credentialsResult = new AuthModule.CredentialsResult();
        credentialsResult.accessToken = authCredentials.mAccessToken;
        return credentialsResult;
    }

    public static AuthModule.CredentialsResult makeCredentialsResult(AuthError authError) {
        AuthModule.CredentialsResult credentialsResult = new AuthModule.CredentialsResult();
        AuthModule.AuthError authError2 = new AuthModule.AuthError();
        credentialsResult.error = authError2;
        authError2.code = (double) authError.mErrorCode;
        authError2.message = authError.getMessage();
        credentialsResult.error.title = authError.mErrorTitle;
        return credentialsResult;
    }

    public static AuthModule.AuthFbLoginResult makeFbLoginResult(AuthError authError) {
        AuthModule.AuthFbLoginResult authFbLoginResult = new AuthModule.AuthFbLoginResult();
        AuthModule.AuthFbLoginError authFbLoginError = new AuthModule.AuthFbLoginError();
        authFbLoginResult.error = authFbLoginError;
        authFbLoginError.code = (double) authError.mErrorCode;
        authFbLoginError.message = authError.getMessage();
        authFbLoginResult.error.title = authError.mErrorTitle;
        return authFbLoginResult;
    }

    public static AuthModule.AuthFbLoginResult makeFbLoginResult(AuthFbLoginError authFbLoginError) {
        AuthModule.AuthFbLoginResult authFbLoginResult = new AuthModule.AuthFbLoginResult();
        AuthModule.AuthFbLoginError authFbLoginError2 = new AuthModule.AuthFbLoginError();
        authFbLoginResult.error = authFbLoginError2;
        authFbLoginError2.code = (double) authFbLoginError.mErrorCode;
        authFbLoginError2.message = authFbLoginError.getMessage();
        AuthModule.AuthFbLoginError authFbLoginError3 = authFbLoginResult.error;
        authFbLoginError3.title = authFbLoginError.mErrorTitle;
        authFbLoginError3.fbUserId = authFbLoginError.mUid;
        authFbLoginError3.machineId = authFbLoginError.mMachineId;
        authFbLoginError3.authToken = authFbLoginError.mAuthToken;
        authFbLoginError3.loginFirstFactor = authFbLoginError.mLoginFirstFactor;
        return authFbLoginResult;
    }

    public static AuthModule.AuthFbLoginResult makeFbLoginResult(AuthFbLoginResult authFbLoginResult) {
        AuthModule.AuthFbLoginResult authFbLoginResult2 = new AuthModule.AuthFbLoginResult();
        authFbLoginResult2.fbUserId = authFbLoginResult.mUid;
        authFbLoginResult2.fbAccessToken = authFbLoginResult.mAccessToken;
        return authFbLoginResult2;
    }

    public static AuthModule.AuthFetchFbInfoForAccountLinkingResult makeFetchFbInfoForAccountLinkingResult(AuthFetchFbInfoForAccountLinkingError authFetchFbInfoForAccountLinkingError) {
        AuthModule.AuthFetchFbInfoForAccountLinkingResult authFetchFbInfoForAccountLinkingResult = new AuthModule.AuthFetchFbInfoForAccountLinkingResult();
        AuthModule.AuthFetchFbInfoForAccountLinkingError authFetchFbInfoForAccountLinkingError2 = new AuthModule.AuthFetchFbInfoForAccountLinkingError();
        authFetchFbInfoForAccountLinkingResult.error = authFetchFbInfoForAccountLinkingError2;
        authFetchFbInfoForAccountLinkingError2.code = (double) authFetchFbInfoForAccountLinkingError.mErrorCode;
        authFetchFbInfoForAccountLinkingError2.message = authFetchFbInfoForAccountLinkingError.getMessage();
        AuthModule.AuthFetchFbInfoForAccountLinkingError authFetchFbInfoForAccountLinkingError3 = authFetchFbInfoForAccountLinkingResult.error;
        authFetchFbInfoForAccountLinkingError3.accessToken = authFetchFbInfoForAccountLinkingError.mAccessToken;
        authFetchFbInfoForAccountLinkingError3.userId = authFetchFbInfoForAccountLinkingError.mUserId;
        return authFetchFbInfoForAccountLinkingResult;
    }

    public static AuthModule.AuthFetchFbInfoForAccountLinkingResult makeFetchFbInfoForAccountLinkingResult(AuthFetchFbInfoForAccountLinkingResult authFetchFbInfoForAccountLinkingResult) {
        AuthModule.AuthFetchFbInfoForAccountLinkingResult authFetchFbInfoForAccountLinkingResult2 = new AuthModule.AuthFetchFbInfoForAccountLinkingResult();
        authFetchFbInfoForAccountLinkingResult2.name = authFetchFbInfoForAccountLinkingResult.mName;
        authFetchFbInfoForAccountLinkingResult2.email = authFetchFbInfoForAccountLinkingResult.mEmail;
        authFetchFbInfoForAccountLinkingResult2.profilePicUri = authFetchFbInfoForAccountLinkingResult.mProfilePicUri;
        return authFetchFbInfoForAccountLinkingResult2;
    }

    public static AuthModule.AuthFbLinkResult makeLinkFbAccountResult() {
        return new AuthModule.AuthFbLinkResult();
    }

    public static AuthModule.AuthFbLinkResult makeLinkFbAccountResult(AuthError authError) {
        AuthModule.AuthFbLinkResult authFbLinkResult = new AuthModule.AuthFbLinkResult();
        AuthModule.AuthError authError2 = new AuthModule.AuthError();
        authFbLinkResult.error = authError2;
        authError2.code = (double) authError.mErrorCode;
        authError2.message = authError.getMessage();
        authFbLinkResult.error.title = authError.mErrorTitle;
        return authFbLinkResult;
    }

    public static AuthModule.AuthLoginResult makeLoginResult() {
        return new AuthModule.AuthLoginResult();
    }

    public static AuthModule.AuthLoginResult makeLoginResult(AuthLoginError authLoginError) {
        AuthModule.AuthLoginResult authLoginResult = new AuthModule.AuthLoginResult();
        AuthModule.AuthLoginError authLoginError2 = new AuthModule.AuthLoginError();
        authLoginResult.error = authLoginError2;
        authLoginError2.code = (double) authLoginError.mErrorCode;
        authLoginError2.message = authLoginError.getMessage();
        AuthModule.AuthLoginError authLoginError3 = authLoginResult.error;
        authLoginError3.title = authLoginError.mErrorTitle;
        authLoginError3.nonce = authLoginError.mNonce;
        ArrayList<AuthTwoFactorMethod> arrayList = authLoginError.mTwoFactorMethods;
        authLoginError3.methodId = (arrayList == null || arrayList.get(0) == null) ? null : arrayList.get(0).id;
        return authLoginResult;
    }

    public static AuthModule.AuthLoginWithFbAuthResult makeLoginWithFbAuthResult() {
        return new AuthModule.AuthLoginWithFbAuthResult();
    }

    public static AuthModule.AuthLoginWithFbAuthResult makeLoginWithFbAuthResult(AuthLoginWithFbAuthError authLoginWithFbAuthError) {
        AuthModule.AuthLoginWithFbAuthResult authLoginWithFbAuthResult = new AuthModule.AuthLoginWithFbAuthResult();
        AuthModule.AuthLoginWithFbAuthError authLoginWithFbAuthError2 = new AuthModule.AuthLoginWithFbAuthError();
        authLoginWithFbAuthResult.error = authLoginWithFbAuthError2;
        authLoginWithFbAuthError2.code = (double) authLoginWithFbAuthError.mErrorCode;
        authLoginWithFbAuthError2.message = authLoginWithFbAuthError.getMessage();
        AuthModule.AuthLoginWithFbAuthError authLoginWithFbAuthError3 = authLoginWithFbAuthResult.error;
        authLoginWithFbAuthError3.title = authLoginWithFbAuthError.mErrorTitle;
        authLoginWithFbAuthError3.name = authLoginWithFbAuthError.mName;
        authLoginWithFbAuthError3.fbUserId = authLoginWithFbAuthError.mFbUserId;
        authLoginWithFbAuthError3.fbAccessToken = authLoginWithFbAuthError.mFbAccessToken;
        authLoginWithFbAuthError3.email = authLoginWithFbAuthError.mEmail;
        authLoginWithFbAuthError3.profilePicUri = authLoginWithFbAuthError.mProfilePicUri;
        authLoginWithFbAuthError3.usernameSuggestions = authLoginWithFbAuthError.mUsernameSuggestions;
        authLoginWithFbAuthError3.allEmails = authLoginWithFbAuthError.mAllEmails;
        authLoginWithFbAuthError3.existingEmailConflict = authLoginWithFbAuthError.mExistingEmailConflict;
        return authLoginWithFbAuthResult;
    }

    public static AuthModule.AuthResult makeResult() {
        return new AuthModule.AuthResult();
    }

    public static AuthModule.AuthResult makeResult(AuthError authError) {
        AuthModule.AuthResult authResult = new AuthModule.AuthResult();
        AuthModule.AuthError authError2 = new AuthModule.AuthError();
        authResult.error = authError2;
        authError2.code = (double) authError.mErrorCode;
        authError2.message = authError.getMessage();
        authResult.error.title = authError.mErrorTitle;
        return authResult;
    }

    public static AuthModule.AuthSendTwoFactorCodeResult makeSendTwoFactorCodeResult() {
        return new AuthModule.AuthSendTwoFactorCodeResult();
    }

    public static AuthModule.AuthSendTwoFactorCodeResult makeSendTwoFactorCodeResult(AuthError authError) {
        AuthModule.AuthSendTwoFactorCodeResult authSendTwoFactorCodeResult = new AuthModule.AuthSendTwoFactorCodeResult();
        AuthModule.AuthError authError2 = new AuthModule.AuthError();
        authSendTwoFactorCodeResult.error = authError2;
        authError2.code = (double) authError.mErrorCode;
        authError2.message = authError.getMessage();
        authSendTwoFactorCodeResult.error.title = authError.mErrorTitle;
        return authSendTwoFactorCodeResult;
    }

    public static AuthModule.AuthVerifyLoginForAccountLinkingResult makeVerifyLoginForAccountLinkingResult(AuthLoginError authLoginError) {
        AuthModule.AuthVerifyLoginForAccountLinkingResult authVerifyLoginForAccountLinkingResult = new AuthModule.AuthVerifyLoginForAccountLinkingResult();
        AuthModule.AuthLoginError authLoginError2 = new AuthModule.AuthLoginError();
        authVerifyLoginForAccountLinkingResult.error = authLoginError2;
        authLoginError2.code = (double) authLoginError.mErrorCode;
        authLoginError2.message = authLoginError.getMessage();
        authVerifyLoginForAccountLinkingResult.error.title = authLoginError.mErrorTitle;
        return authVerifyLoginForAccountLinkingResult;
    }

    public static AuthModule.AuthVerifyLoginForAccountLinkingResult makeVerifyLoginForAccountLinkingResult(AuthVerifyLoginForAccountLinkingResult authVerifyLoginForAccountLinkingResult) {
        AuthModule.AuthVerifyLoginForAccountLinkingResult authVerifyLoginForAccountLinkingResult2 = new AuthModule.AuthVerifyLoginForAccountLinkingResult();
        authVerifyLoginForAccountLinkingResult2.accessToken = authVerifyLoginForAccountLinkingResult.mAccessToken;
        authVerifyLoginForAccountLinkingResult2.userId = authVerifyLoginForAccountLinkingResult.mUserId;
        return authVerifyLoginForAccountLinkingResult2;
    }

    public static AuthModule.AuthVerifyLoginResult makeVerifyLoginResult() {
        return new AuthModule.AuthVerifyLoginResult();
    }

    public static AuthModule.AuthVerifyLoginResult makeVerifyLoginResult(AuthError authError) {
        AuthModule.AuthVerifyLoginResult authVerifyLoginResult = new AuthModule.AuthVerifyLoginResult();
        AuthModule.AuthError authError2 = new AuthModule.AuthError();
        authVerifyLoginResult.error = authError2;
        authError2.code = (double) authError.mErrorCode;
        authError2.message = authError.getMessage();
        authVerifyLoginResult.error.title = authError.mErrorTitle;
        return authVerifyLoginResult;
    }
}
