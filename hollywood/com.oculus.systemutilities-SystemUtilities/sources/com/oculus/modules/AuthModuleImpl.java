package com.oculus.modules;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;
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

public class AuthModuleImpl extends AuthModule {
    private static final String AUTH_BROADCAST_LOGIN = "com.oculus.auth.BROADCAST_LOGIN";
    private static final String TAG = AuthModuleImpl.class.getSimpleName();
    private final Context mContext;
    private OVRAuth mOVRAuth;

    public AuthModuleImpl(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override // com.oculus.panellib.modules.AbstractBroadcastReceiverModule
    public IntentFilter getIntentFilter() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(AUTH_BROADCAST_LOGIN);
        return filter;
    }

    @Override // com.oculus.panellib.modules.AbstractBroadcastReceiverModule
    public void processIntent(Intent intent, boolean forceNativeUpdate) {
        String action = intent.getAction();
        char c = 65535;
        switch (action.hashCode()) {
            case 698177661:
                if (action.equals(AUTH_BROADCAST_LOGIN)) {
                    c = 0;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                onLogin();
                return;
            default:
                Log.e(TAG, String.format("Unhandled action: %s", action));
                return;
        }
    }

    private void onLogin() {
        Log.d(TAG, "Login status changed.");
        emitOnLogin(null);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.AuthModule
    public void loginWithEmailAndPasswordImpl(String email, String password, final Resolver<AuthModule.AuthLoginResult> resolver) {
        Log.d(TAG, "loginWithEmailAndPasswordImpl()");
        ovrAuth().loginWithEmailAndPassword(email, password, new AuthResultCallback<Void, AuthLoginError>() {
            /* class com.oculus.modules.AuthModuleImpl.AnonymousClass1 */

            public void onResult(Void result) {
                Log.d(AuthModuleImpl.TAG, "loginWithEmailAndPasswordImpl() succeeded");
                resolver.resolve(AuthModuleImpl.makeLoginResult());
            }

            public void onError(AuthLoginError error) {
                Log.e(AuthModuleImpl.TAG, "loginWithEmailAndPasswordImpl() failed", error);
                resolver.resolve(AuthModuleImpl.makeLoginResult(error));
            }
        });
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.AuthModule
    public void reloginWithEmailAndPasswordImpl(String email, String password, final Resolver<AuthModule.AuthLoginResult> resolver) {
        Log.d(TAG, "reloginWithEmailAndPasswordImpl()");
        ovrAuth().reloginWithEmailAndPassword(email, password, new AuthResultCallback<Void, AuthLoginError>() {
            /* class com.oculus.modules.AuthModuleImpl.AnonymousClass2 */

            public void onResult(Void result) {
                Log.d(AuthModuleImpl.TAG, "reloginWithEmailAndPasswordImpl() succeeded");
                resolver.resolve(AuthModuleImpl.makeLoginResult());
            }

            public void onError(AuthLoginError error) {
                Log.e(AuthModuleImpl.TAG, "reloginWithEmailAndPasswordImpl() failed", error);
                resolver.resolve(AuthModuleImpl.makeLoginResult(error));
            }
        });
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.AuthModule
    public void linkFbAccountImpl(String fbUserId, String fbAccessToken, final Resolver<AuthModule.AuthFbLinkResult> resolver) {
        Log.d(TAG, "linkFbAccountImpl()");
        ovrAuth().linkFbAccount(fbUserId, fbAccessToken, null, null, new AuthResultCallback<Void, AuthError>() {
            /* class com.oculus.modules.AuthModuleImpl.AnonymousClass3 */

            public void onResult(Void result) {
                Log.d(AuthModuleImpl.TAG, "linkFbAccountImpl() succeeded");
                resolver.resolve(AuthModuleImpl.makeLinkFbAccountResult());
            }

            @Override // com.oculus.authapi.AuthResultCallback
            public void onError(AuthError error) {
                Log.e(AuthModuleImpl.TAG, "linkFbAccountImpl() failed", error);
                resolver.resolve(AuthModuleImpl.makeLinkFbAccountResult(error));
            }
        });
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.AuthModule
    public void loginWithFbAuthImpl(String fbAccessToken, final Resolver<AuthModule.AuthLoginWithFbAuthResult> resolver) {
        Log.d(TAG, "loginWithFbAuthImpl()");
        ovrAuth().loginWithFbAuth(fbAccessToken, new AuthResultCallback<Void, AuthLoginWithFbAuthError>() {
            /* class com.oculus.modules.AuthModuleImpl.AnonymousClass4 */

            public void onResult(Void result) {
                Log.d(AuthModuleImpl.TAG, "loginWithFbAuthImpl() succeeded");
                resolver.resolve(AuthModuleImpl.makeLoginWithFbAuthResult());
            }

            public void onError(AuthLoginWithFbAuthError error) {
                Log.e(AuthModuleImpl.TAG, "loginWithFbAuthImpl() failed", error);
                resolver.resolve(AuthModuleImpl.makeLoginWithFbAuthResult(error));
            }
        });
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.AuthModule
    public void reloginWithFbAuthImpl(String fbAccessToken, final Resolver<AuthModule.AuthLoginWithFbAuthResult> resolver) {
        Log.d(TAG, "reloginWithFbAuthImpl()");
        ovrAuth().reloginWithFbAuth(fbAccessToken, new AuthResultCallback<Void, AuthLoginWithFbAuthError>() {
            /* class com.oculus.modules.AuthModuleImpl.AnonymousClass5 */

            public void onResult(Void result) {
                Log.d(AuthModuleImpl.TAG, "reloginWithFbAuthImpl() succeeded");
                resolver.resolve(AuthModuleImpl.makeLoginWithFbAuthResult());
            }

            public void onError(AuthLoginWithFbAuthError error) {
                Log.e(AuthModuleImpl.TAG, "reloginWithFbAuthImpl() failed", error);
                resolver.resolve(AuthModuleImpl.makeLoginWithFbAuthResult(error));
            }
        });
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.AuthModule
    public void fetchFbInfoForAccountLinkingImpl(String fbAccessToken, final Resolver<AuthModule.AuthFetchFbInfoForAccountLinkingResult> resolver) {
        Log.d(TAG, "fetchFbInfoForAccountLinkingImpl()");
        ovrAuth().fetchFbInfoForAccountLinking(fbAccessToken, new AuthResultCallback<AuthFetchFbInfoForAccountLinkingResult, AuthFetchFbInfoForAccountLinkingError>() {
            /* class com.oculus.modules.AuthModuleImpl.AnonymousClass6 */

            public void onResult(AuthFetchFbInfoForAccountLinkingResult result) {
                Log.d(AuthModuleImpl.TAG, "fetchFbInfoForAccountLinkingImpl() succeeded");
                resolver.resolve(AuthModuleImpl.makeFetchFbInfoForAccountLinkingResult(result));
            }

            public void onError(AuthFetchFbInfoForAccountLinkingError error) {
                Log.e(AuthModuleImpl.TAG, "fetchFbInfoForAccountLinkingImpl() failed", error);
                resolver.resolve(AuthModuleImpl.makeFetchFbInfoForAccountLinkingResult(error));
            }
        });
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.AuthModule
    public void authWithOculusEmailAndPasswordForAccountLinkingImpl(String email, String password, final Resolver<AuthModule.AuthAuthWithOculusEmailAndPasswordForAccountLinkingResult> resolver) {
        Log.d(TAG, "authWithOculusEmailAndPasswordForAccountLinkingImpl()");
        ovrAuth().authWithOculusEmailAndPasswordForAccountLinking(email, password, new AuthResultCallback<AuthAuthWithOculusEmailAndPasswordForAccountLinkingResult, AuthLoginError>() {
            /* class com.oculus.modules.AuthModuleImpl.AnonymousClass7 */

            public void onResult(AuthAuthWithOculusEmailAndPasswordForAccountLinkingResult result) {
                Log.d(AuthModuleImpl.TAG, "authWithOculusEmailAndPasswordForAccountLinkingImpl() succeeded");
                resolver.resolve(AuthModuleImpl.makeAuthWithEmailAndPasswordResult(result));
            }

            public void onError(AuthLoginError error) {
                Log.e(AuthModuleImpl.TAG, "authWithOculusEmailAndPasswordForAccountLinkingImpl() failed", error);
                resolver.resolve(AuthModuleImpl.makeAuthWithEmailAndPasswordResult(error));
            }
        });
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.AuthModule
    public void loginToFbImpl(String emailOrPhone, String password, final Resolver<AuthModule.AuthFbLoginResult> resolver) {
        Log.d(TAG, "loginToFbImpl()");
        ovrAuth().loginToFb(emailOrPhone, password, new AuthResultCallback<AuthFbLoginResult, AuthFbLoginError>() {
            /* class com.oculus.modules.AuthModuleImpl.AnonymousClass8 */

            public void onResult(AuthFbLoginResult result) {
                Log.d(AuthModuleImpl.TAG, "loginToFbImpl() succeeded");
                resolver.resolve(AuthModuleImpl.makeFbLoginResult(result));
            }

            public void onError(AuthFbLoginError error) {
                Log.e(AuthModuleImpl.TAG, "loginToFbImpl() failed", error);
                resolver.resolve(AuthModuleImpl.makeFbLoginResult(error));
            }
        });
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.AuthModule
    public void checkFbMachineApprovalImpl(String fbUserId, String machineId, final Resolver<AuthModule.AuthCheckFbMachineApprovalResult> resolver) {
        Log.d(TAG, "checkFbMachineApprovalImpl()");
        ovrAuth().checkFbMachineApproval(fbUserId, machineId, new AuthResultCallback<Boolean, AuthError>() {
            /* class com.oculus.modules.AuthModuleImpl.AnonymousClass9 */

            public void onResult(Boolean result) {
                Log.d(AuthModuleImpl.TAG, "checkFbMachineApprovalImpl() succeeded");
                resolver.resolve(AuthModuleImpl.makeCheckFbMachineApprovalResult(result.booleanValue()));
            }

            @Override // com.oculus.authapi.AuthResultCallback
            public void onError(AuthError error) {
                Log.e(AuthModuleImpl.TAG, "checkFbMachineApprovalImpl() failed", error);
                resolver.resolve(AuthModuleImpl.makeCheckFbMachineApprovalResult(error));
            }
        });
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.AuthModule
    public void sendTwoFactorCodeImpl(String nonce, String methodId, final Resolver<AuthModule.AuthSendTwoFactorCodeResult> resolver) {
        Log.d(TAG, "sendTwoFactorCodeImpl()");
        ovrAuth().sendTwoFactorCode(nonce, methodId, new AuthResultCallback<Void, AuthError>() {
            /* class com.oculus.modules.AuthModuleImpl.AnonymousClass10 */

            public void onResult(Void result) {
                Log.d(AuthModuleImpl.TAG, "sendTwoFactorCodeImpl() succeeded");
                resolver.resolve(AuthModuleImpl.makeSendTwoFactorCodeResult());
            }

            @Override // com.oculus.authapi.AuthResultCallback
            public void onError(AuthError error) {
                Log.e(AuthModuleImpl.TAG, "sendTwoFactorCodeImpl() failed", error);
                resolver.resolve(AuthModuleImpl.makeSendTwoFactorCodeResult(error));
            }
        });
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.AuthModule
    public void verifyLoginImpl(String nonce, String pin, String methodId, String fbAccessToken, boolean isSecuredAction, final Resolver<AuthModule.AuthVerifyLoginResult> resolver) {
        Log.d(TAG, "verifyLoginImpl()");
        ovrAuth().verifyLogin(nonce, pin, methodId, fbAccessToken, false, new AuthResultCallback<Void, AuthError>() {
            /* class com.oculus.modules.AuthModuleImpl.AnonymousClass11 */

            public void onResult(Void result) {
                Log.d(AuthModuleImpl.TAG, "verifyLoginImpl() succeeded");
                resolver.resolve(AuthModuleImpl.makeVerifyLoginResult());
            }

            @Override // com.oculus.authapi.AuthResultCallback
            public void onError(AuthError error) {
                Log.d(AuthModuleImpl.TAG, "verifyLoginImpl() failed", error);
                resolver.resolve(AuthModuleImpl.makeVerifyLoginResult(error));
            }
        });
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.AuthModule
    public void verifyReloginImpl(String nonce, String pin, String methodId, String fbAccessToken, boolean isSecuredAction, final Resolver<AuthModule.AuthVerifyLoginResult> resolver) {
        Log.d(TAG, "verifyReloginImpl()");
        ovrAuth().verifyRelogin(nonce, pin, methodId, fbAccessToken, false, new AuthResultCallback<Void, AuthError>() {
            /* class com.oculus.modules.AuthModuleImpl.AnonymousClass12 */

            public void onResult(Void result) {
                Log.d(AuthModuleImpl.TAG, "verifyReloginImpl() succeeded");
                resolver.resolve(AuthModuleImpl.makeVerifyLoginResult());
            }

            @Override // com.oculus.authapi.AuthResultCallback
            public void onError(AuthError error) {
                Log.d(AuthModuleImpl.TAG, "verifyReloginImpl() failed", error);
                resolver.resolve(AuthModuleImpl.makeVerifyLoginResult(error));
            }
        });
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.AuthModule
    public void verifyLoginForAccountLinkingImpl(String nonce, String pin, String methodId, String fbAccessToken, final Resolver<AuthModule.AuthVerifyLoginForAccountLinkingResult> resolver) {
        Log.d(TAG, "verifyLoginForAccountLinkingImpl()");
        ovrAuth().verifyLoginForAccountLinking(nonce, pin, methodId, fbAccessToken, false, new AuthResultCallback<AuthVerifyLoginForAccountLinkingResult, AuthLoginError>() {
            /* class com.oculus.modules.AuthModuleImpl.AnonymousClass13 */

            public void onResult(AuthVerifyLoginForAccountLinkingResult result) {
                Log.d(AuthModuleImpl.TAG, "verifyLoginForAccountLinkingImpl() succeeded");
                resolver.resolve(AuthModuleImpl.makeVerifyLoginForAccountLinkingResult(result));
            }

            public void onError(AuthLoginError error) {
                Log.e(AuthModuleImpl.TAG, "verifyLoginForAccountLinkingImpl() failed", error);
                resolver.resolve(AuthModuleImpl.makeVerifyLoginForAccountLinkingResult(error));
            }
        });
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.AuthModule
    public void completeFbLoginAfterMachineApprovalImpl(String fbUserId, String machineId, String authToken, final Resolver<AuthModule.AuthFbLoginResult> resolver) {
        Log.d(TAG, "completeFbLoginAfterMachineApproval()");
        ovrAuth().completeFbLoginAfterMachineApproval(fbUserId, machineId, authToken, new AuthResultCallback<AuthFbLoginResult, AuthError>() {
            /* class com.oculus.modules.AuthModuleImpl.AnonymousClass14 */

            public void onResult(AuthFbLoginResult result) {
                Log.d(AuthModuleImpl.TAG, "completeFbLoginAfterMachineApproval() succeeded");
                resolver.resolve(AuthModuleImpl.makeFbLoginResult(result));
            }

            @Override // com.oculus.authapi.AuthResultCallback
            public void onError(AuthError error) {
                Log.e(AuthModuleImpl.TAG, "completeFbLoginAfterMachineApproval() failed", error);
                resolver.resolve(AuthModuleImpl.makeFbLoginResult(error));
            }
        });
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.AuthModule
    public void completeFbLoginWithTwoFactorCodeImpl(String email, String fbUserId, String machineId, String firstFactor, String twoFactorCode, final Resolver<AuthModule.AuthFbLoginResult> resolver) {
        Log.d(TAG, "completeFbLoginWithTwoFactorCodeImpl()");
        ovrAuth().completeFbLoginWithTwoFactorCode(email, fbUserId, machineId, firstFactor, twoFactorCode, new AuthResultCallback<AuthFbLoginResult, AuthError>() {
            /* class com.oculus.modules.AuthModuleImpl.AnonymousClass15 */

            public void onResult(AuthFbLoginResult result) {
                Log.d(AuthModuleImpl.TAG, "completeFbLoginWithTwoFactorCodeImpl() succeeded");
                resolver.resolve(AuthModuleImpl.makeFbLoginResult(result));
            }

            @Override // com.oculus.authapi.AuthResultCallback
            public void onError(AuthError error) {
                Log.e(AuthModuleImpl.TAG, "completeFbLoginWithTwoFactorCodeImpl() failed", error);
                resolver.resolve(AuthModuleImpl.makeFbLoginResult(error));
            }
        });
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.AuthModule
    public void statusImpl(final Resolver<AuthModule.AuthStatusResult> resolver) {
        Log.d(TAG, "statusImpl()");
        ovrAuth().status(new ResultReceiver(null) {
            /* class com.oculus.modules.AuthModuleImpl.AnonymousClass16 */

            /* access modifiers changed from: protected */
            public void onReceiveResult(int resultCode, Bundle resultData) {
                AuthModule.AuthStatusResult result = new AuthModule.AuthStatusResult();
                if (resultCode == 1) {
                    result.error = new AuthModule.AuthError();
                    result.error.code = (double) resultData.getInt("error_code");
                    Log.e(AuthModuleImpl.TAG, "statusImpl() failed");
                } else {
                    result.userId = resultData.getString("user_id");
                    result.accessToken = resultData.getString("access_token");
                    Log.e(AuthModuleImpl.TAG, "statusImpl() succeeded");
                }
                resolver.resolve(result);
            }
        });
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.AuthModule
    public void ensureDeviceOwnershipImpl(final Resolver<AuthModule.AuthResult> resolver) {
        Log.d(TAG, "ensureDeviceOwnershipImpl()");
        ovrAuth().ensureDeviceOwnership(new AuthResultCallback<Void, AuthError>() {
            /* class com.oculus.modules.AuthModuleImpl.AnonymousClass17 */

            public void onResult(Void result) {
                Log.d(AuthModuleImpl.TAG, "ensureDeviceOwnershipImpl() succeeded");
                resolver.resolve(AuthModuleImpl.makeResult());
            }

            @Override // com.oculus.authapi.AuthResultCallback
            public void onError(AuthError error) {
                Log.e(AuthModuleImpl.TAG, "ensureDeviceOwnershipImpl() failed", error);
                resolver.resolve(AuthModuleImpl.makeResult(error));
            }
        });
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.AuthModule
    public void fetchDeviceScopedCredentialsImpl(final Resolver<AuthModule.CredentialsResult> resolver) {
        Log.d(TAG, "fetchDeviceScopedCredentialsImpl()");
        try {
            ovrAuth().fetchDeviceScopedCredentials(new AuthResultCallback<AuthCredentials, AuthError>() {
                /* class com.oculus.modules.AuthModuleImpl.AnonymousClass18 */

                public void onResult(AuthCredentials result) {
                    Log.d(AuthModuleImpl.TAG, "fetchDeviceScopedCredentialsImpl() succeeded");
                    resolver.resolve(AuthModuleImpl.makeCredentialsResult(result));
                }

                @Override // com.oculus.authapi.AuthResultCallback
                public void onError(AuthError error) {
                    Log.e(AuthModuleImpl.TAG, "fetchDeviceScopedCredentialsImpl() failed", error);
                    resolver.resolve(AuthModuleImpl.makeCredentialsResult(error));
                }
            });
        } catch (Exception e) {
            Log.d(TAG, e.getMessage());
            resolver.reject(e);
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.AuthModule
    public double marshallJSConstantErrorCodeDeviceAlreadyClaimed() {
        return -14.0d;
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.AuthModule
    public double marshallJSConstantErrorCodeInvalidCreds() {
        return -1.0d;
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.AuthModule
    public double marshallJSConstantErrorCodeLoginApprovalsInvalidCode() {
        return -10.0d;
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.AuthModule
    public double marshallJSConstantErrorCodeNetworkError() {
        return -4.0d;
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.AuthModule
    public double marshallJSConstantErrorCodeHttpError() {
        return -6.0d;
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.AuthModule
    public double marshallJSConstantErrorCodeNoDeviceIdentity() {
        return -16.0d;
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.AuthModule
    public double marshallJSConstantErrorCodeNoLinkedAccount() {
        return -13.0d;
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.AuthModule
    public double marshallJSConstantErrorCodeRateLimited() {
        return -5.0d;
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.AuthModule
    public double marshallJSConstantErrorCodeTwoFactorLoginRequired() {
        return -8.0d;
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.AuthModule
    public double marshallJSConstantErrorCodeAnomalousLogin() {
        return -2.0d;
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.AuthModule
    public double marshallJSConstantErrorCodeUserAlreadyOnDevice() {
        return -17.0d;
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.AuthModule
    public double marshallJSConstantErrorCodeAccountConflict() {
        return -18.0d;
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.AuthModule
    public double marshallJSConstantErrorCodeCannotMergeAccountsBecauseAlreadyLinked() {
        return -19.0d;
    }

    private synchronized OVRAuth ovrAuth() {
        if (this.mOVRAuth == null) {
            this.mOVRAuth = new OVRAuth(this.mContext, new OVRAuth.CallerInfoProvider() {
                /* class com.oculus.modules.AuthModuleImpl.AnonymousClass19 */

                @Override // com.oculus.authapi.OVRAuth.CallerInfoProvider
                public Intent attachCallerInfo(Intent intent) {
                    try {
                        return CallerInfoHelper.attachCallerInfo(intent, AuthModuleImpl.this.mContext, null);
                    } catch (Exception e) {
                        Log.e(AuthModuleImpl.TAG, "Error attaching caller info", e);
                        return intent;
                    }
                }
            });
        }
        return this.mOVRAuth;
    }

    /* access modifiers changed from: private */
    public static AuthModule.AuthResult makeResult() {
        return new AuthModule.AuthResult();
    }

    /* access modifiers changed from: private */
    public static AuthModule.AuthResult makeResult(AuthError from) {
        AuthModule.AuthResult result = new AuthModule.AuthResult();
        result.error = new AuthModule.AuthError();
        result.error.code = (double) from.getErrorCode();
        result.error.message = from.getMessage();
        result.error.title = from.getErrorTitle();
        return result;
    }

    /* access modifiers changed from: private */
    public static AuthModule.AuthLoginResult makeLoginResult() {
        return new AuthModule.AuthLoginResult();
    }

    /* access modifiers changed from: private */
    public static AuthModule.AuthLoginResult makeLoginResult(AuthLoginError from) {
        AuthModule.AuthLoginResult result = new AuthModule.AuthLoginResult();
        result.error = new AuthModule.AuthLoginError();
        result.error.code = (double) from.getErrorCode();
        result.error.message = from.getMessage();
        result.error.title = from.getErrorTitle();
        result.error.nonce = from.getNonce();
        result.error.methodId = (from.getTwoFactorMethods() == null || from.getTwoFactorMethods().get(0) == null) ? null : from.getTwoFactorMethods().get(0).id;
        return result;
    }

    /* access modifiers changed from: private */
    public static AuthModule.AuthFbLinkResult makeLinkFbAccountResult() {
        return new AuthModule.AuthFbLinkResult();
    }

    /* access modifiers changed from: private */
    public static AuthModule.AuthFbLinkResult makeLinkFbAccountResult(AuthError from) {
        AuthModule.AuthFbLinkResult result = new AuthModule.AuthFbLinkResult();
        result.error = new AuthModule.AuthError();
        result.error.code = (double) from.getErrorCode();
        result.error.message = from.getMessage();
        result.error.title = from.getErrorTitle();
        return result;
    }

    /* access modifiers changed from: private */
    public static AuthModule.AuthLoginWithFbAuthResult makeLoginWithFbAuthResult() {
        return new AuthModule.AuthLoginWithFbAuthResult();
    }

    /* access modifiers changed from: private */
    public static AuthModule.AuthLoginWithFbAuthResult makeLoginWithFbAuthResult(AuthLoginWithFbAuthError from) {
        AuthModule.AuthLoginWithFbAuthResult result = new AuthModule.AuthLoginWithFbAuthResult();
        result.error = new AuthModule.AuthLoginWithFbAuthError();
        result.error.code = (double) from.getErrorCode();
        result.error.message = from.getMessage();
        result.error.title = from.getErrorTitle();
        result.error.name = from.getName();
        result.error.fbUserId = from.getFbUserId();
        result.error.fbAccessToken = from.getFbAccessToken();
        result.error.email = from.getEmail();
        result.error.profilePicUri = from.getProfilePicUri();
        result.error.usernameSuggestions = from.getUsernameSuggestions();
        result.error.allEmails = from.getAllEmails();
        result.error.existingEmailConflict = from.getExistingEmailConflict();
        return result;
    }

    /* access modifiers changed from: private */
    public static AuthModule.AuthFetchFbInfoForAccountLinkingResult makeFetchFbInfoForAccountLinkingResult(AuthFetchFbInfoForAccountLinkingResult from) {
        AuthModule.AuthFetchFbInfoForAccountLinkingResult result = new AuthModule.AuthFetchFbInfoForAccountLinkingResult();
        result.name = from.getName();
        result.email = from.getEmail();
        result.profilePicUri = from.getProfilePicUri();
        return result;
    }

    /* access modifiers changed from: private */
    public static AuthModule.AuthFetchFbInfoForAccountLinkingResult makeFetchFbInfoForAccountLinkingResult(AuthFetchFbInfoForAccountLinkingError from) {
        AuthModule.AuthFetchFbInfoForAccountLinkingResult result = new AuthModule.AuthFetchFbInfoForAccountLinkingResult();
        result.error = new AuthModule.AuthFetchFbInfoForAccountLinkingError();
        result.error.code = (double) from.getErrorCode();
        result.error.message = from.getMessage();
        result.error.accessToken = from.getAccessToken();
        result.error.userId = from.getUserId();
        return result;
    }

    /* access modifiers changed from: private */
    public static AuthModule.AuthAuthWithOculusEmailAndPasswordForAccountLinkingResult makeAuthWithEmailAndPasswordResult(AuthAuthWithOculusEmailAndPasswordForAccountLinkingResult from) {
        AuthModule.AuthAuthWithOculusEmailAndPasswordForAccountLinkingResult result = new AuthModule.AuthAuthWithOculusEmailAndPasswordForAccountLinkingResult();
        result.accessToken = from.getAccessToken();
        result.userId = from.getUserId();
        return result;
    }

    /* access modifiers changed from: private */
    public static AuthModule.AuthAuthWithOculusEmailAndPasswordForAccountLinkingResult makeAuthWithEmailAndPasswordResult(AuthLoginError from) {
        AuthModule.AuthAuthWithOculusEmailAndPasswordForAccountLinkingResult result = new AuthModule.AuthAuthWithOculusEmailAndPasswordForAccountLinkingResult();
        result.error = new AuthModule.AuthLoginError();
        result.error.code = (double) from.getErrorCode();
        result.error.message = from.getMessage();
        result.error.title = from.getErrorTitle();
        result.error.nonce = from.getNonce();
        result.error.methodId = (from.getTwoFactorMethods() == null || from.getTwoFactorMethods().get(0) == null) ? null : from.getTwoFactorMethods().get(0).id;
        return result;
    }

    /* access modifiers changed from: private */
    public static AuthModule.AuthFbLoginResult makeFbLoginResult(AuthFbLoginResult from) {
        AuthModule.AuthFbLoginResult result = new AuthModule.AuthFbLoginResult();
        result.fbUserId = from.getUid();
        result.fbAccessToken = from.getAccessToken();
        return result;
    }

    /* access modifiers changed from: private */
    public static AuthModule.AuthFbLoginResult makeFbLoginResult(AuthError from) {
        AuthModule.AuthFbLoginResult result = new AuthModule.AuthFbLoginResult();
        result.error = new AuthModule.AuthFbLoginError();
        result.error.code = (double) from.getErrorCode();
        result.error.message = from.getMessage();
        result.error.title = from.getErrorTitle();
        return result;
    }

    /* access modifiers changed from: private */
    public static AuthModule.AuthFbLoginResult makeFbLoginResult(AuthFbLoginError from) {
        AuthModule.AuthFbLoginResult result = new AuthModule.AuthFbLoginResult();
        result.error = new AuthModule.AuthFbLoginError();
        result.error.code = (double) from.getErrorCode();
        result.error.message = from.getMessage();
        result.error.title = from.getErrorTitle();
        result.error.fbUserId = from.getUid();
        result.error.machineId = from.getMachineId();
        result.error.authToken = from.getAuthToken();
        result.error.loginFirstFactor = from.getLoginFirstFactor();
        return result;
    }

    /* access modifiers changed from: private */
    public static AuthModule.CredentialsResult makeCredentialsResult(AuthCredentials from) {
        AuthModule.CredentialsResult result = new AuthModule.CredentialsResult();
        result.accessToken = from.getAccessToken();
        return result;
    }

    /* access modifiers changed from: private */
    public static AuthModule.CredentialsResult makeCredentialsResult(AuthError from) {
        AuthModule.CredentialsResult result = new AuthModule.CredentialsResult();
        result.error = new AuthModule.AuthError();
        result.error.code = (double) from.getErrorCode();
        result.error.message = from.getMessage();
        result.error.title = from.getErrorTitle();
        return result;
    }

    /* access modifiers changed from: private */
    public static AuthModule.AuthCheckFbMachineApprovalResult makeCheckFbMachineApprovalResult(boolean approved) {
        AuthModule.AuthCheckFbMachineApprovalResult result = new AuthModule.AuthCheckFbMachineApprovalResult();
        result.approved = Boolean.valueOf(approved);
        return result;
    }

    /* access modifiers changed from: private */
    public static AuthModule.AuthCheckFbMachineApprovalResult makeCheckFbMachineApprovalResult(AuthError from) {
        AuthModule.AuthCheckFbMachineApprovalResult result = new AuthModule.AuthCheckFbMachineApprovalResult();
        result.error = new AuthModule.AuthError();
        result.error.code = (double) from.getErrorCode();
        result.error.message = from.getMessage();
        result.error.title = from.getErrorTitle();
        return result;
    }

    /* access modifiers changed from: private */
    public static AuthModule.AuthSendTwoFactorCodeResult makeSendTwoFactorCodeResult() {
        return new AuthModule.AuthSendTwoFactorCodeResult();
    }

    /* access modifiers changed from: private */
    public static AuthModule.AuthSendTwoFactorCodeResult makeSendTwoFactorCodeResult(AuthError from) {
        AuthModule.AuthSendTwoFactorCodeResult result = new AuthModule.AuthSendTwoFactorCodeResult();
        result.error = new AuthModule.AuthError();
        result.error.code = (double) from.getErrorCode();
        result.error.message = from.getMessage();
        result.error.title = from.getErrorTitle();
        return result;
    }

    /* access modifiers changed from: private */
    public static AuthModule.AuthVerifyLoginResult makeVerifyLoginResult() {
        return new AuthModule.AuthVerifyLoginResult();
    }

    /* access modifiers changed from: private */
    public static AuthModule.AuthVerifyLoginResult makeVerifyLoginResult(AuthError from) {
        AuthModule.AuthVerifyLoginResult result = new AuthModule.AuthVerifyLoginResult();
        result.error = new AuthModule.AuthError();
        result.error.code = (double) from.getErrorCode();
        result.error.message = from.getMessage();
        result.error.title = from.getErrorTitle();
        return result;
    }

    /* access modifiers changed from: private */
    public static AuthModule.AuthVerifyLoginForAccountLinkingResult makeVerifyLoginForAccountLinkingResult(AuthVerifyLoginForAccountLinkingResult from) {
        AuthModule.AuthVerifyLoginForAccountLinkingResult result = new AuthModule.AuthVerifyLoginForAccountLinkingResult();
        result.accessToken = from.getAccessToken();
        result.userId = from.getUserId();
        return result;
    }

    /* access modifiers changed from: private */
    public static AuthModule.AuthVerifyLoginForAccountLinkingResult makeVerifyLoginForAccountLinkingResult(AuthLoginError from) {
        AuthModule.AuthVerifyLoginForAccountLinkingResult result = new AuthModule.AuthVerifyLoginForAccountLinkingResult();
        result.error = new AuthModule.AuthLoginError();
        result.error.code = (double) from.getErrorCode();
        result.error.message = from.getMessage();
        result.error.title = from.getErrorTitle();
        return result;
    }
}
