package com.oculus.authapi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.ResultReceiver;
import com.facebook.debug.log.BLog;
import com.oculus.common.build.BuildConstants;

public class OVRAuth {
    private static final String TAG = OVRAuth.class.getSimpleName();
    private final CallerInfoProvider mCallerInfoProvider;
    private final Context mContext;
    private boolean mIsUnderTest;

    public interface CallerInfoProvider {
        Intent attachCallerInfo(Intent intent);
    }

    /* access modifiers changed from: protected */
    public ResultReceiver getReceiverForSending(ResultReceiver actualReceiver) {
        if (this.mIsUnderTest) {
            return new TestReceiverForSending(actualReceiver);
        }
        Parcel parcel = Parcel.obtain();
        actualReceiver.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        ResultReceiver resultReceiver = (ResultReceiver) ResultReceiver.CREATOR.createFromParcel(parcel);
        parcel.recycle();
        return resultReceiver;
    }

    /* access modifiers changed from: package-private */
    public static final class TestReceiverForSending extends ResultReceiver {
        final ResultReceiver mActualReceiver;

        TestReceiverForSending(ResultReceiver actualReceiver) {
            super(null);
            this.mActualReceiver = actualReceiver;
        }

        public void send(int resultCode, Bundle resultData) {
            this.mActualReceiver.send(resultCode, resultData);
        }
    }

    public OVRAuth(Context context, CallerInfoProvider provider) {
        this.mContext = context;
        this.mCallerInfoProvider = provider;
    }

    private void loginWithEmailAndPassword(String email, String password, boolean isRelogin, ResultReceiver receiver) {
        Intent intent = newHorizonIntent("com.oculus.auth.ACTION_LOGIN_API");
        intent.putExtra("email", email);
        intent.putExtra("password", password);
        intent.putExtra("receiver", getReceiverForSending(receiver));
        intent.putExtra("is_relogin", isRelogin);
        startAuthService(intent);
    }

    private Intent attachCallerInfo(Intent intent) {
        if (this.mCallerInfoProvider != null) {
            return this.mCallerInfoProvider.attachCallerInfo(intent);
        }
        return intent;
    }

    public void loginWithEmailAndPassword(String email, String password, AuthResultCallback<Void, AuthLoginError> callback) {
        loginWithEmailAndPassword(email, password, false, new AuthTaskReceiver<Void, AuthLoginError>(callback) {
            /* class com.oculus.authapi.OVRAuth.AnonymousClass1 */

            /* access modifiers changed from: protected */
            @Override // com.oculus.authapi.AuthTaskReceiver
            public Void createResultFromBundle(Bundle resultData) {
                return null;
            }

            /* access modifiers changed from: protected */
            @Override // com.oculus.authapi.AuthTaskReceiver
            public AuthLoginError createErrorFromBundle(Bundle resultData) {
                return new AuthLoginError(resultData);
            }
        });
    }

    public void reloginWithEmailAndPassword(String email, String password, AuthResultCallback<Void, AuthLoginError> callback) {
        loginWithEmailAndPassword(email, password, true, new AuthTaskReceiver<Void, AuthLoginError>(callback) {
            /* class com.oculus.authapi.OVRAuth.AnonymousClass2 */

            /* access modifiers changed from: protected */
            @Override // com.oculus.authapi.AuthTaskReceiver
            public Void createResultFromBundle(Bundle resultData) {
                return null;
            }

            /* access modifiers changed from: protected */
            @Override // com.oculus.authapi.AuthTaskReceiver
            public AuthLoginError createErrorFromBundle(Bundle resultData) {
                return new AuthLoginError(resultData);
            }
        });
    }

    public void authWithOculusEmailAndPasswordForAccountLinking(String email, String password, AuthResultCallback<AuthAuthWithOculusEmailAndPasswordForAccountLinkingResult, AuthLoginError> resultCallback) {
        AuthTaskReceiver<AuthAuthWithOculusEmailAndPasswordForAccountLinkingResult, AuthLoginError> receiver = new AuthTaskReceiver<AuthAuthWithOculusEmailAndPasswordForAccountLinkingResult, AuthLoginError>(resultCallback) {
            /* class com.oculus.authapi.OVRAuth.AnonymousClass4 */

            /* access modifiers changed from: protected */
            @Override // com.oculus.authapi.AuthTaskReceiver
            public AuthAuthWithOculusEmailAndPasswordForAccountLinkingResult createResultFromBundle(Bundle resultData) {
                return new AuthAuthWithOculusEmailAndPasswordForAccountLinkingResult(resultData);
            }

            /* access modifiers changed from: protected */
            @Override // com.oculus.authapi.AuthTaskReceiver
            public AuthLoginError createErrorFromBundle(Bundle resultData) {
                return new AuthLoginError(resultData);
            }
        };
        Intent intent = newHorizonIntent("com.oculus.auth.ACTION_AUTH_WITH_OCULUS_EMAIL_AND_PASSWORD_FOR_ACCOUNT_LINKING");
        intent.putExtra("email", email);
        intent.putExtra("password", password);
        intent.putExtra("receiver", getReceiverForSending(receiver));
        startAuthService(intent);
    }

    public void loginWithFbAuth(String fbAccessToken, AuthResultCallback<Void, AuthLoginWithFbAuthError> resultCallback) {
        loginWithFbAuth(fbAccessToken, false, resultCallback);
    }

    public void reloginWithFbAuth(String fbAccessToken, AuthResultCallback<Void, AuthLoginWithFbAuthError> resultCallback) {
        loginWithFbAuth(fbAccessToken, true, resultCallback);
    }

    private void loginWithFbAuth(String fbAccessToken, boolean isRelogin, AuthResultCallback<Void, AuthLoginWithFbAuthError> resultCallback) {
        AuthTaskReceiver<Void, AuthLoginWithFbAuthError> receiver = new AuthTaskReceiver<Void, AuthLoginWithFbAuthError>(resultCallback) {
            /* class com.oculus.authapi.OVRAuth.AnonymousClass6 */

            /* access modifiers changed from: protected */
            @Override // com.oculus.authapi.AuthTaskReceiver
            public Void createResultFromBundle(Bundle resultData) {
                return null;
            }

            /* access modifiers changed from: protected */
            @Override // com.oculus.authapi.AuthTaskReceiver
            public AuthLoginWithFbAuthError createErrorFromBundle(Bundle resultData) {
                return new AuthLoginWithFbAuthError(resultData);
            }
        };
        Intent intent = newHorizonIntent("com.oculus.auth.ACTION_LOGIN_WITH_FB_AUTH");
        intent.putExtra("fb_access_token", fbAccessToken);
        intent.putExtra("receiver", getReceiverForSending(receiver));
        intent.putExtra("is_relogin", isRelogin);
        startAuthService(intent);
    }

    public void fetchFbInfoForAccountLinking(String fbAccessToken, AuthResultCallback<AuthFetchFbInfoForAccountLinkingResult, AuthFetchFbInfoForAccountLinkingError> resultCallback) {
        AuthTaskReceiver<AuthFetchFbInfoForAccountLinkingResult, AuthFetchFbInfoForAccountLinkingError> receiver = new AuthTaskReceiver<AuthFetchFbInfoForAccountLinkingResult, AuthFetchFbInfoForAccountLinkingError>(resultCallback) {
            /* class com.oculus.authapi.OVRAuth.AnonymousClass7 */

            /* access modifiers changed from: protected */
            @Override // com.oculus.authapi.AuthTaskReceiver
            public AuthFetchFbInfoForAccountLinkingResult createResultFromBundle(Bundle resultData) {
                return new AuthFetchFbInfoForAccountLinkingResult(resultData);
            }

            /* access modifiers changed from: protected */
            @Override // com.oculus.authapi.AuthTaskReceiver
            public AuthFetchFbInfoForAccountLinkingError createErrorFromBundle(Bundle resultData) {
                return new AuthFetchFbInfoForAccountLinkingError(resultData);
            }
        };
        Intent intent = newHorizonIntent("com.oculus.auth.ACTION_FETCH_FB_INFO_FOR_ACCOUNT_LINKING");
        intent.putExtra("fb_access_token", fbAccessToken);
        intent.putExtra("receiver", getReceiverForSending(receiver));
        startAuthService(intent);
    }

    public void status(final ResultReceiver receiver) {
        Intent intent = newHorizonIntent("com.oculus.auth.ACTION_STATUS");
        intent.putExtra("receiver", getReceiverForSending(new ResultReceiver(null) {
            /* class com.oculus.authapi.OVRAuth.AnonymousClass8 */

            /* access modifiers changed from: protected */
            public void onReceiveResult(int resultCode, Bundle resultData) {
                receiver.send(resultCode, resultData);
            }
        }));
        startAuthService(intent);
    }

    public void fetchDeviceScopedCredentials(AuthResultCallback<AuthCredentials, AuthError> callback) {
        AuthTaskReceiver<AuthCredentials, AuthError> receiver = new AuthTaskReceiver<AuthCredentials, AuthError>(callback) {
            /* class com.oculus.authapi.OVRAuth.AnonymousClass9 */

            /* access modifiers changed from: protected */
            @Override // com.oculus.authapi.AuthTaskReceiver
            public AuthCredentials createResultFromBundle(Bundle bundle) {
                return new AuthCredentials(bundle);
            }
        };
        Intent intent = newHorizonIntent("com.oculus.auth.ACTION_FETCH_DEVICE_SCOPED_CREDENTIALS");
        intent.putExtra("receiver", getReceiverForSending(receiver));
        startAuthService(intent);
    }

    /* access modifiers changed from: package-private */
    public void sendTwoFactorCode(String nonce, String methodId, ResultReceiver receiver) {
        Intent intent = newHorizonIntent("com.oculus.auth.ACTION_SEND_TWO_FACTOR_CODE");
        intent.putExtra("nonce", nonce);
        intent.putExtra("two_factor_method_id", methodId);
        intent.putExtra("receiver", getReceiverForSending(receiver));
        startAuthService(intent);
    }

    public void sendTwoFactorCode(String nonce, String methodId, AuthResultCallback<Void, AuthError> resultCallback) {
        sendTwoFactorCode(nonce, methodId, new AuthTaskReceiver<Void, AuthError>(resultCallback) {
            /* class com.oculus.authapi.OVRAuth.AnonymousClass13 */

            /* access modifiers changed from: protected */
            @Override // com.oculus.authapi.AuthTaskReceiver
            public Void createResultFromBundle(Bundle resultData) {
                return null;
            }
        });
    }

    private void verifyLogin(String nonce, String pin, String methodId, String fbAccessToken, boolean isSecuredAction, boolean isRelogin, AuthResultCallback<Void, AuthError> resultCallback) {
        AuthTaskReceiver<Void, AuthError> receiver = new AuthTaskReceiver<Void, AuthError>(resultCallback) {
            /* class com.oculus.authapi.OVRAuth.AnonymousClass14 */

            /* access modifiers changed from: protected */
            @Override // com.oculus.authapi.AuthTaskReceiver
            public Void createResultFromBundle(Bundle resultData) {
                return null;
            }
        };
        Intent intent = newHorizonIntent("com.oculus.auth.ACTION_VERIFY_LOGIN");
        intent.putExtra("nonce", nonce);
        intent.putExtra("pin", pin);
        intent.putExtra("two_factor_method_id", methodId);
        intent.putExtra("fb_access_token", fbAccessToken);
        intent.putExtra("secured_action", isSecuredAction);
        intent.putExtra("is_relogin", isRelogin);
        intent.putExtra("receiver", getReceiverForSending(receiver));
        startAuthService(intent);
    }

    public void verifyLogin(String nonce, String pin, String methodId, String fbAccessToken, boolean isSecuredAction, AuthResultCallback<Void, AuthError> resultCallback) {
        verifyLogin(nonce, pin, methodId, fbAccessToken, isSecuredAction, false, resultCallback);
    }

    public void verifyRelogin(String nonce, String pin, String methodId, String fbAccessToken, boolean isSecuredAction, AuthResultCallback<Void, AuthError> resultCallback) {
        verifyLogin(nonce, pin, methodId, fbAccessToken, isSecuredAction, true, resultCallback);
    }

    public void verifyLoginForAccountLinking(String nonce, String pin, String methodId, String fbAccessToken, boolean isSecuredAction, AuthResultCallback<AuthVerifyLoginForAccountLinkingResult, AuthLoginError> resultCallback) {
        AuthTaskReceiver<AuthVerifyLoginForAccountLinkingResult, AuthLoginError> receiver = new AuthTaskReceiver<AuthVerifyLoginForAccountLinkingResult, AuthLoginError>(resultCallback) {
            /* class com.oculus.authapi.OVRAuth.AnonymousClass15 */

            /* access modifiers changed from: protected */
            @Override // com.oculus.authapi.AuthTaskReceiver
            public AuthVerifyLoginForAccountLinkingResult createResultFromBundle(Bundle resultData) {
                return new AuthVerifyLoginForAccountLinkingResult(resultData);
            }

            /* access modifiers changed from: protected */
            @Override // com.oculus.authapi.AuthTaskReceiver
            public AuthLoginError createErrorFromBundle(Bundle resultData) {
                return new AuthLoginError(resultData);
            }
        };
        Intent intent = newHorizonIntent("com.oculus.auth.ACTION_VERIFY_LOGIN_FOR_ACCOUNT_LINKING");
        intent.putExtra("nonce", nonce);
        intent.putExtra("pin", pin);
        intent.putExtra("two_factor_method_id", methodId);
        intent.putExtra("fb_access_token", fbAccessToken);
        intent.putExtra("secured_action", isSecuredAction);
        intent.putExtra("receiver", getReceiverForSending(receiver));
        startAuthService(intent);
    }

    public void ensureDeviceOwnership(AuthResultCallback<Void, AuthError> callback) {
        AuthTaskReceiver<Void, AuthError> receiver = new AuthVoidReceiver(callback);
        Intent intent = newHorizonIntent("com.oculus.auth.ACTION_ENSURE_DEVICE_OWNERSHIP");
        intent.putExtra("receiver", getReceiverForSending(receiver));
        startAuthService(intent);
    }

    public void loginToFb(String email, String password, AuthResultCallback<AuthFbLoginResult, AuthFbLoginError> resultCallback) {
        loginToFb(email, password, null, null, null, new AuthTaskReceiver<AuthFbLoginResult, AuthFbLoginError>(resultCallback) {
            /* class com.oculus.authapi.OVRAuth.AnonymousClass16 */

            /* access modifiers changed from: protected */
            @Override // com.oculus.authapi.AuthTaskReceiver
            public AuthFbLoginResult createResultFromBundle(Bundle resultData) throws AuthFbLoginError {
                try {
                    return new AuthFbLoginResult(resultData);
                } catch (AuthError error) {
                    throw new AuthFbLoginError(error);
                }
            }

            /* access modifiers changed from: protected */
            @Override // com.oculus.authapi.AuthTaskReceiver
            public AuthFbLoginError createErrorFromBundle(Bundle resultData) {
                return new AuthFbLoginError(resultData);
            }
        });
    }

    public void loginToFbAndLink(String email, String password, String tosVersion, String linkLoggingJson, AuthResultCallback<Void, AuthFbLoginError> resultCallback) {
        loginToFb(email, password, tosVersion, linkLoggingJson, "link", new AuthTaskReceiver<Void, AuthFbLoginError>(resultCallback) {
            /* class com.oculus.authapi.OVRAuth.AnonymousClass17 */

            /* access modifiers changed from: protected */
            @Override // com.oculus.authapi.AuthTaskReceiver
            public Void createResultFromBundle(Bundle resultData) {
                return null;
            }

            /* access modifiers changed from: protected */
            @Override // com.oculus.authapi.AuthTaskReceiver
            public AuthFbLoginError createErrorFromBundle(Bundle resultData) {
                return new AuthFbLoginError(resultData);
            }
        });
    }

    private void loginToFb(String email, String password, String tosVersion, String linkLoggingJson, String followUp, ResultReceiver receiver) {
        Intent intent = newHorizonIntent("com.oculus.auth.ACTION_FB_LOGIN");
        intent.putExtra("email", email);
        intent.putExtra("password", password);
        intent.putExtra("fb_link_tos_version", tosVersion);
        intent.putExtra("fb_link_logging_json", linkLoggingJson);
        putExtraIfNonnull(intent, "follow_up", followUp);
        intent.putExtra("receiver", getReceiverForSending(receiver));
        startAuthService(intent);
    }

    public void checkFbMachineApproval(String uid, String machineId, AuthResultCallback<Boolean, AuthError> resultCallback) {
        AuthTaskReceiver<Boolean, AuthError> receiver = new AuthTaskReceiver<Boolean, AuthError>(resultCallback) {
            /* class com.oculus.authapi.OVRAuth.AnonymousClass18 */

            /* access modifiers changed from: protected */
            @Override // com.oculus.authapi.AuthTaskReceiver
            public Boolean createResultFromBundle(Bundle resultData) {
                return Boolean.valueOf(resultData.getBoolean("machine_approved"));
            }
        };
        Intent intent = newHorizonIntent("com.oculus.auth.ACTION_FB_CHECK_MACHINE_APPROVAL");
        intent.putExtra("uid", uid);
        intent.putExtra("machine_id", machineId);
        intent.putExtra("receiver", getReceiverForSending(receiver));
        startAuthService(intent);
    }

    public void completeFbLoginAfterMachineApproval(String uid, String machineId, String authToken, AuthResultCallback<AuthFbLoginResult, AuthError> resultCallback) {
        completeFbLoginAfterMachineApproval(uid, machineId, authToken, null, null, null, new AuthFbLoginReceiver(resultCallback));
    }

    public void completeFbLoginAfterMachineApprovalAndLink(String uid, String machineId, String authToken, String tosVersion, String linkLoggingJson, AuthResultCallback<Void, AuthError> resultCallback) {
        completeFbLoginAfterMachineApproval(uid, machineId, authToken, tosVersion, linkLoggingJson, "link", new AuthVoidReceiver(resultCallback));
    }

    private void completeFbLoginAfterMachineApproval(String uid, String machineId, String authToken, String tosVersion, String linkLoggingJson, String followUp, ResultReceiver receiver) {
        Intent intent = newHorizonIntent("com.oculus.auth.ACTION_FB_MACHINE_APPROVAL_LOGIN");
        intent.putExtra("uid", uid);
        intent.putExtra("machine_id", machineId);
        intent.putExtra("auth_token", authToken);
        putExtraIfNonnull(intent, "fb_link_tos_version", tosVersion);
        putExtraIfNonnull(intent, "fb_link_logging_json", linkLoggingJson);
        putExtraIfNonnull(intent, "follow_up", followUp);
        intent.putExtra("receiver", getReceiverForSending(receiver));
        startAuthService(intent);
    }

    public void completeFbLoginWithTwoFactorCode(String email, String uid, String machineId, String firstFactor, String twoFactorCode, AuthResultCallback<AuthFbLoginResult, AuthError> resultCallback) {
        completeFbLoginWithTwoFactorCode(email, uid, machineId, firstFactor, twoFactorCode, null, null, null, new AuthFbLoginReceiver(resultCallback));
    }

    public void completeFbLoginWithTwoFactorCodeAndLink(String email, String uid, String machineId, String firstFactor, String twoFactorCode, String tosVersion, String linkLoggingJson, AuthResultCallback<Void, AuthError> resultCallback) {
        completeFbLoginWithTwoFactorCode(email, uid, machineId, firstFactor, twoFactorCode, tosVersion, linkLoggingJson, "link", new AuthVoidReceiver(resultCallback));
    }

    private void completeFbLoginWithTwoFactorCode(String email, String uid, String machineId, String firstFactor, String twoFactorCode, String tosVersion, String linkLoggingJson, String followUp, ResultReceiver receiver) {
        Intent intent = newHorizonIntent("com.oculus.auth.ACTION_FB_VERIFY_LOGIN_APPROVALS_CODE");
        intent.putExtra("email", email);
        intent.putExtra("uid", uid);
        intent.putExtra("machine_id", machineId);
        intent.putExtra("first_factor", firstFactor);
        intent.putExtra("pin", twoFactorCode);
        putExtraIfNonnull(intent, "fb_link_tos_version", tosVersion);
        putExtraIfNonnull(intent, "fb_link_logging_json", linkLoggingJson);
        putExtraIfNonnull(intent, "follow_up", followUp);
        intent.putExtra("receiver", getReceiverForSending(receiver));
        startAuthService(intent);
    }

    public void linkFbAccount(String uid, String accessToken, String tosVersion, String linkLoggingJson, AuthResultCallback<Void, AuthError> resultCallback) {
        AuthVoidReceiver receiver = new AuthVoidReceiver(resultCallback);
        Intent intent = newHorizonIntent("com.oculus.auth.ACTION_LINK_FB_ACCOUNT");
        intent.putExtra("uid", uid);
        intent.putExtra("access_token", accessToken);
        intent.putExtra("fb_link_tos_version", tosVersion);
        intent.putExtra("fb_link_logging_json", linkLoggingJson);
        intent.putExtra("receiver", getReceiverForSending(receiver));
        startAuthService(intent);
    }

    @SuppressLint({"BadMethodUse-android.content.Context.startService"})
    public void startAuthService(Intent intent) {
        Bundle extras;
        ResultReceiver receiver;
        boolean error = false;
        try {
            if (this.mContext.startService(intent) == null) {
                error = true;
                BLog.e(TAG, "Horizon Service not installed...");
            }
        } catch (Exception e) {
            error = true;
            BLog.e(TAG, e, "Failed to communicate with Horizon service");
        }
        if (error && (extras = intent.getExtras()) != null && (receiver = (ResultReceiver) extras.getParcelable("receiver")) != null) {
            Bundle serviceErrorBundle = new Bundle();
            serviceErrorBundle.putInt("error_code", -7);
            receiver.send(-7, serviceErrorBundle);
        }
    }

    /* access modifiers changed from: protected */
    public Intent newHorizonIntent(String action) {
        Intent intent = new Intent(action);
        intent.setPackage(BuildConstants.PACKAGE_NAME_HORIZON);
        return attachCallerInfo(intent);
    }

    private static void putExtraIfNonnull(Intent intent, String key, String value) {
        if (value != null) {
            intent.putExtra(key, value);
        }
    }
}
