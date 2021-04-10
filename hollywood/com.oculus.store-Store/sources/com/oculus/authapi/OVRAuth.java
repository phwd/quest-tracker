package com.oculus.authapi;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.ResultReceiver;
import com.facebook.debug.log.BLog;
import com.oculus.auth.service.contract.ServiceContract;
import com.oculus.common.build.BuildConstants;
import com.oculus.horizon.fbconnect.contract.FBConnectContent;
import javax.annotation.Nullable;

public class OVRAuth {
    public static final String EXTRA_RESULT = "result";
    public static final int RESULT_ERROR = 1;
    public static final int RESULT_OK = -1;
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

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void insertFbAccessToken(String fbUserId, String fbAccessToken) {
        ContentValues values = new ContentValues();
        values.put(FBConnectContent.Account.USER_ID, fbUserId);
        values.put(FBConnectContent.Account.ACCESS_TOKEN, fbAccessToken);
        this.mContext.getContentResolver().insert(FBConnectContent.Account.CONTENT_URI, values);
    }

    private void loginWithEmailAndPassword(String email, String password, boolean isRelogin, ResultReceiver receiver) {
        Intent intent = newHorizonIntent(ServiceContract.ACTION_LOGIN_API);
        intent.putExtra("email", email);
        intent.putExtra(ServiceContract.EXTRA_PASSWORD, password);
        intent.putExtra(ServiceContract.EXTRA_RECEIVER, getReceiverForSending(receiver));
        intent.putExtra(ServiceContract.EXTRA_IS_RELOGIN, isRelogin);
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

    public void loginWithEmailAndPassword(String email, String password, String fbUserId, String fbAccessToken, AuthResultCallback<Void, AuthLoginError> callback) {
        Intent intent = newHorizonIntent(ServiceContract.ACTION_LOGIN_API);
        intent.putExtra("email", email);
        intent.putExtra(ServiceContract.EXTRA_PASSWORD, password);
        intent.putExtra(ServiceContract.EXTRA_FB_ACCESS_TOKEN, fbAccessToken);
        intent.putExtra(ServiceContract.EXTRA_RECEIVER, getReceiverForSending(new AuthTaskReceiver<Void, AuthLoginError>(new ProxyAuthCallback(fbUserId, fbAccessToken, callback)) {
            /* class com.oculus.authapi.OVRAuth.AnonymousClass3 */

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
        }));
        startAuthService(intent);
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
        Intent intent = newHorizonIntent(ServiceContract.ACTION_AUTH_WITH_OCULUS_EMAIL_AND_PASSWORD_FOR_ACCOUNT_LINKING);
        intent.putExtra("email", email);
        intent.putExtra(ServiceContract.EXTRA_PASSWORD, password);
        intent.putExtra(ServiceContract.EXTRA_RECEIVER, getReceiverForSending(receiver));
        startAuthService(intent);
    }

    @Deprecated
    public void login(String accessToken, ResultReceiver receiver) {
        loginWithCredentials(null, accessToken, receiver);
    }

    public void loginWithCredentials(String userId, String accessToken, ResultReceiver receiver) {
        Intent intent = newHorizonIntent(ServiceContract.ACTION_LOGIN_API);
        intent.putExtra(ServiceContract.EXTRA_USER_ID, userId);
        intent.putExtra("access_token", accessToken);
        intent.putExtra(ServiceContract.EXTRA_RECEIVER, getReceiverForSending(receiver));
        startAuthService(intent);
    }

    public void loginWithCredentials(String userId, String accessToken, String fbUserId, String fbAccessToken, AuthResultCallback<Void, AuthLoginError> callback) {
        Intent intent = newHorizonIntent(ServiceContract.ACTION_LOGIN_API);
        intent.putExtra(ServiceContract.EXTRA_USER_ID, userId);
        intent.putExtra("access_token", accessToken);
        intent.putExtra(ServiceContract.EXTRA_FB_ACCESS_TOKEN, fbAccessToken);
        intent.putExtra(ServiceContract.EXTRA_RECEIVER, getReceiverForSending(new AuthTaskReceiver<Void, AuthLoginError>(new ProxyAuthCallback(fbUserId, fbAccessToken, callback)) {
            /* class com.oculus.authapi.OVRAuth.AnonymousClass5 */

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
        }));
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
        Intent intent = newHorizonIntent(ServiceContract.ACTION_LOGIN_WITH_FB_AUTH);
        intent.putExtra(ServiceContract.EXTRA_FB_ACCESS_TOKEN, fbAccessToken);
        intent.putExtra(ServiceContract.EXTRA_RECEIVER, getReceiverForSending(receiver));
        intent.putExtra(ServiceContract.EXTRA_IS_RELOGIN, isRelogin);
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
        Intent intent = newHorizonIntent(ServiceContract.ACTION_FETCH_FB_INFO_FOR_ACCOUNT_LINKING);
        intent.putExtra(ServiceContract.EXTRA_FB_ACCESS_TOKEN, fbAccessToken);
        intent.putExtra(ServiceContract.EXTRA_RECEIVER, getReceiverForSending(receiver));
        startAuthService(intent);
    }

    public void status(final ResultReceiver receiver) {
        Intent intent = newHorizonIntent(ServiceContract.ACTION_STATUS);
        intent.putExtra(ServiceContract.EXTRA_RECEIVER, getReceiverForSending(new ResultReceiver(null) {
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
        Intent intent = newHorizonIntent(ServiceContract.ACTION_FETCH_DEVICE_SCOPED_CREDENTIALS);
        intent.putExtra(ServiceContract.EXTRA_RECEIVER, getReceiverForSending(receiver));
        startAuthService(intent);
    }

    public void fetchHorizonDeviceScopedCredentials(AuthResultCallback<AuthCredentials, AuthError> callback) {
        AuthTaskReceiver<AuthCredentials, AuthError> receiver = new AuthTaskReceiver<AuthCredentials, AuthError>(callback) {
            /* class com.oculus.authapi.OVRAuth.AnonymousClass10 */

            /* access modifiers changed from: protected */
            @Override // com.oculus.authapi.AuthTaskReceiver
            public AuthCredentials createResultFromBundle(Bundle bundle) {
                return new AuthCredentials(bundle);
            }
        };
        Intent intent = newHorizonIntent(ServiceContract.ACTION_FETCH_HORIZON_DEVICE_SCOPED_CREDENTIALS);
        intent.putExtra(ServiceContract.EXTRA_RECEIVER, getReceiverForSending(receiver));
        startAuthService(intent);
    }

    public void logout(ResultReceiver receiver) {
        logout(receiver, false);
    }

    public void logout(final ResultReceiver receiver, boolean localLogout) {
        Intent intent = newHorizonIntent(ServiceContract.ACTION_LOGOUT);
        intent.putExtra(ServiceContract.EXTRA_RECEIVER, getReceiverForSending(new ResultReceiver(null) {
            /* class com.oculus.authapi.OVRAuth.AnonymousClass11 */

            /* access modifiers changed from: protected */
            public void onReceiveResult(int resultCode, Bundle resultData) {
                receiver.send(resultCode, resultData);
            }
        }));
        intent.putExtra(ServiceContract.EXTRA_LOGOUT_WITHOUT_SERVER, localLogout);
        startAuthService(intent);
    }

    /* access modifiers changed from: package-private */
    public void sendTwoFactorCode(@Nullable String nonce, String methodId, ResultReceiver receiver) {
        Intent intent = newHorizonIntent(ServiceContract.ACTION_SEND_TWO_FACTOR_CODE);
        intent.putExtra(ServiceContract.EXTRA_NONCE, nonce);
        intent.putExtra(ServiceContract.EXTRA_TWO_FACTOR_METHOD_ID, methodId);
        intent.putExtra(ServiceContract.EXTRA_RECEIVER, getReceiverForSending(receiver));
        startAuthService(intent);
    }

    public void resendLoginApprovalsCode(String nonce, AuthResultCallback<Void, AuthError> resultCallback) {
        AuthTaskReceiver<Void, AuthError> receiver = new AuthTaskReceiver<Void, AuthError>(resultCallback) {
            /* class com.oculus.authapi.OVRAuth.AnonymousClass12 */

            /* access modifiers changed from: protected */
            @Override // com.oculus.authapi.AuthTaskReceiver
            public Void createResultFromBundle(Bundle resultData) {
                return null;
            }
        };
        Intent intent = newHorizonIntent(ServiceContract.ACTION_RESEND_LOGIN_APPROVAL_CODE);
        intent.putExtra(ServiceContract.EXTRA_NONCE, nonce);
        intent.putExtra(ServiceContract.EXTRA_RECEIVER, getReceiverForSending(receiver));
        startAuthService(intent);
    }

    public void sendTwoFactorCode(@Nullable String nonce, String methodId, AuthResultCallback<Void, AuthError> resultCallback) {
        sendTwoFactorCode(nonce, methodId, new AuthTaskReceiver<Void, AuthError>(resultCallback) {
            /* class com.oculus.authapi.OVRAuth.AnonymousClass13 */

            /* access modifiers changed from: protected */
            @Override // com.oculus.authapi.AuthTaskReceiver
            public Void createResultFromBundle(Bundle resultData) {
                return null;
            }
        });
    }

    private void verifyLogin(@Nullable String nonce, String pin, @Nullable String methodId, @Nullable String fbAccessToken, boolean isSecuredAction, boolean isRelogin, AuthResultCallback<Void, AuthError> resultCallback) {
        AuthTaskReceiver<Void, AuthError> receiver = new AuthTaskReceiver<Void, AuthError>(resultCallback) {
            /* class com.oculus.authapi.OVRAuth.AnonymousClass14 */

            /* access modifiers changed from: protected */
            @Override // com.oculus.authapi.AuthTaskReceiver
            public Void createResultFromBundle(Bundle resultData) {
                return null;
            }
        };
        Intent intent = newHorizonIntent(ServiceContract.ACTION_VERIFY_LOGIN);
        intent.putExtra(ServiceContract.EXTRA_NONCE, nonce);
        intent.putExtra(ServiceContract.EXTRA_PIN, pin);
        intent.putExtra(ServiceContract.EXTRA_TWO_FACTOR_METHOD_ID, methodId);
        intent.putExtra(ServiceContract.EXTRA_FB_ACCESS_TOKEN, fbAccessToken);
        intent.putExtra(ServiceContract.EXTRA_IS_SECURED_ACTION, isSecuredAction);
        intent.putExtra(ServiceContract.EXTRA_IS_RELOGIN, isRelogin);
        intent.putExtra(ServiceContract.EXTRA_RECEIVER, getReceiverForSending(receiver));
        startAuthService(intent);
    }

    public void verifyLogin(@Nullable String nonce, String pin, @Nullable String methodId, @Nullable String fbAccessToken, boolean isSecuredAction, AuthResultCallback<Void, AuthError> resultCallback) {
        verifyLogin(nonce, pin, methodId, fbAccessToken, isSecuredAction, false, resultCallback);
    }

    public void verifyRelogin(@Nullable String nonce, String pin, @Nullable String methodId, @Nullable String fbAccessToken, boolean isSecuredAction, AuthResultCallback<Void, AuthError> resultCallback) {
        verifyLogin(nonce, pin, methodId, fbAccessToken, isSecuredAction, true, resultCallback);
    }

    public void verifyLoginForAccountLinking(@Nullable String nonce, String pin, @Nullable String methodId, @Nullable String fbAccessToken, boolean isSecuredAction, AuthResultCallback<AuthVerifyLoginForAccountLinkingResult, AuthLoginError> resultCallback) {
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
        Intent intent = newHorizonIntent(ServiceContract.ACTION_VERIFY_LOGIN_FOR_ACCOUNT_LINKING);
        intent.putExtra(ServiceContract.EXTRA_NONCE, nonce);
        intent.putExtra(ServiceContract.EXTRA_PIN, pin);
        intent.putExtra(ServiceContract.EXTRA_TWO_FACTOR_METHOD_ID, methodId);
        intent.putExtra(ServiceContract.EXTRA_FB_ACCESS_TOKEN, fbAccessToken);
        intent.putExtra(ServiceContract.EXTRA_IS_SECURED_ACTION, isSecuredAction);
        intent.putExtra(ServiceContract.EXTRA_RECEIVER, getReceiverForSending(receiver));
        startAuthService(intent);
    }

    public void ensureDeviceOwnership(AuthResultCallback<Void, AuthError> callback) {
        AuthTaskReceiver<Void, AuthError> receiver = new AuthVoidReceiver(callback);
        Intent intent = newHorizonIntent(ServiceContract.ACTION_ENSURE_DEVICE_OWNERSHIP);
        intent.putExtra(ServiceContract.EXTRA_RECEIVER, getReceiverForSending(receiver));
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

    public void loginToFbAndLink(String email, String password, @Nullable String tosVersion, String linkLoggingJson, AuthResultCallback<Void, AuthFbLoginError> resultCallback) {
        loginToFb(email, password, tosVersion, linkLoggingJson, ServiceContract.FOLLOW_UP_LINK, new AuthTaskReceiver<Void, AuthFbLoginError>(resultCallback) {
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

    private void loginToFb(String email, String password, @Nullable String tosVersion, @Nullable String linkLoggingJson, @Nullable String followUp, ResultReceiver receiver) {
        Intent intent = newHorizonIntent(ServiceContract.ACTION_FB_LOGIN);
        intent.putExtra("email", email);
        intent.putExtra(ServiceContract.EXTRA_PASSWORD, password);
        intent.putExtra(ServiceContract.EXTRA_FB_LINK_TOS_VERSION, tosVersion);
        intent.putExtra(ServiceContract.EXTRA_FB_LINK_LOGGING_JSON, linkLoggingJson);
        putExtraIfNonnull(intent, ServiceContract.EXTRA_FOLLOW_UP, followUp);
        intent.putExtra(ServiceContract.EXTRA_RECEIVER, getReceiverForSending(receiver));
        startAuthService(intent);
    }

    public void checkFbMachineApproval(String uid, String machineId, AuthResultCallback<Boolean, AuthError> resultCallback) {
        AuthTaskReceiver<Boolean, AuthError> receiver = new AuthTaskReceiver<Boolean, AuthError>(resultCallback) {
            /* class com.oculus.authapi.OVRAuth.AnonymousClass18 */

            /* access modifiers changed from: protected */
            @Override // com.oculus.authapi.AuthTaskReceiver
            public Boolean createResultFromBundle(Bundle resultData) {
                return Boolean.valueOf(resultData.getBoolean(ServiceContract.EXTRA_MACHINE_APPROVED));
            }
        };
        Intent intent = newHorizonIntent(ServiceContract.ACTION_FB_CHECK_MACHINE_APPROVAL);
        intent.putExtra("uid", uid);
        intent.putExtra(ServiceContract.EXTRA_MACHINE_ID, machineId);
        intent.putExtra(ServiceContract.EXTRA_RECEIVER, getReceiverForSending(receiver));
        startAuthService(intent);
    }

    public void completeFbLoginAfterMachineApproval(String uid, String machineId, String authToken, AuthResultCallback<AuthFbLoginResult, AuthError> resultCallback) {
        completeFbLoginAfterMachineApproval(uid, machineId, authToken, null, null, null, new AuthFbLoginReceiver(resultCallback));
    }

    public void completeFbLoginAfterMachineApprovalAndLink(String uid, String machineId, String authToken, @Nullable String tosVersion, String linkLoggingJson, AuthResultCallback<Void, AuthError> resultCallback) {
        completeFbLoginAfterMachineApproval(uid, machineId, authToken, tosVersion, linkLoggingJson, ServiceContract.FOLLOW_UP_LINK, new AuthVoidReceiver(resultCallback));
    }

    private void completeFbLoginAfterMachineApproval(String uid, String machineId, String authToken, @Nullable String tosVersion, @Nullable String linkLoggingJson, @Nullable String followUp, ResultReceiver receiver) {
        Intent intent = newHorizonIntent(ServiceContract.ACTION_FB_MACHINE_APPROVAL_LOGIN);
        intent.putExtra("uid", uid);
        intent.putExtra(ServiceContract.EXTRA_MACHINE_ID, machineId);
        intent.putExtra(ServiceContract.EXTRA_AUTH_TOKEN, authToken);
        putExtraIfNonnull(intent, ServiceContract.EXTRA_FB_LINK_TOS_VERSION, tosVersion);
        putExtraIfNonnull(intent, ServiceContract.EXTRA_FB_LINK_LOGGING_JSON, linkLoggingJson);
        putExtraIfNonnull(intent, ServiceContract.EXTRA_FOLLOW_UP, followUp);
        intent.putExtra(ServiceContract.EXTRA_RECEIVER, getReceiverForSending(receiver));
        startAuthService(intent);
    }

    public void completeFbLoginWithTwoFactorCode(String email, String uid, String machineId, String firstFactor, String twoFactorCode, AuthResultCallback<AuthFbLoginResult, AuthError> resultCallback) {
        completeFbLoginWithTwoFactorCode(email, uid, machineId, firstFactor, twoFactorCode, null, null, null, new AuthFbLoginReceiver(resultCallback));
    }

    public void completeFbLoginWithTwoFactorCodeAndLink(String email, String uid, String machineId, String firstFactor, String twoFactorCode, @Nullable String tosVersion, String linkLoggingJson, AuthResultCallback<Void, AuthError> resultCallback) {
        completeFbLoginWithTwoFactorCode(email, uid, machineId, firstFactor, twoFactorCode, tosVersion, linkLoggingJson, ServiceContract.FOLLOW_UP_LINK, new AuthVoidReceiver(resultCallback));
    }

    private void completeFbLoginWithTwoFactorCode(String email, String uid, String machineId, String firstFactor, String twoFactorCode, @Nullable String tosVersion, @Nullable String linkLoggingJson, @Nullable String followUp, ResultReceiver receiver) {
        Intent intent = newHorizonIntent(ServiceContract.ACTION_FB_VERIFY_LOGIN_APPROVALS_CODE);
        intent.putExtra("email", email);
        intent.putExtra("uid", uid);
        intent.putExtra(ServiceContract.EXTRA_MACHINE_ID, machineId);
        intent.putExtra(ServiceContract.EXTRA_FIRST_FACTOR, firstFactor);
        intent.putExtra(ServiceContract.EXTRA_PIN, twoFactorCode);
        putExtraIfNonnull(intent, ServiceContract.EXTRA_FB_LINK_TOS_VERSION, tosVersion);
        putExtraIfNonnull(intent, ServiceContract.EXTRA_FB_LINK_LOGGING_JSON, linkLoggingJson);
        putExtraIfNonnull(intent, ServiceContract.EXTRA_FOLLOW_UP, followUp);
        intent.putExtra(ServiceContract.EXTRA_RECEIVER, getReceiverForSending(receiver));
        startAuthService(intent);
    }

    public void resendFbSms(String uid, String firstFactor, AuthResultCallback<Void, AuthError> resultCallback) {
        AuthVoidReceiver receiver = new AuthVoidReceiver(resultCallback);
        Intent intent = newHorizonIntent(ServiceContract.ACTION_FB_RESEND_SMS);
        intent.putExtra("uid", uid);
        intent.putExtra(ServiceContract.EXTRA_FIRST_FACTOR, firstFactor);
        intent.putExtra(ServiceContract.EXTRA_RECEIVER, getReceiverForSending(receiver));
        startAuthService(intent);
    }

    public void linkFbAccount(String uid, String accessToken, @Nullable String tosVersion, String linkLoggingJson, AuthResultCallback<Void, AuthError> resultCallback) {
        AuthVoidReceiver receiver = new AuthVoidReceiver(resultCallback);
        Intent intent = newHorizonIntent(ServiceContract.ACTION_LINK_FB_ACCOUNT);
        intent.putExtra("uid", uid);
        intent.putExtra("access_token", accessToken);
        intent.putExtra(ServiceContract.EXTRA_FB_LINK_TOS_VERSION, tosVersion);
        intent.putExtra(ServiceContract.EXTRA_FB_LINK_LOGGING_JSON, linkLoggingJson);
        intent.putExtra(ServiceContract.EXTRA_RECEIVER, getReceiverForSending(receiver));
        startAuthService(intent);
    }

    @SuppressLint({"BadMethodUse-android.content.Context.startService"})
    public void startAuthService(Intent intent) {
        Bundle extras;
        ResultReceiver receiver;
        if (this.mContext.startService(intent) == null && (extras = intent.getExtras()) != null && (receiver = (ResultReceiver) extras.getParcelable(ServiceContract.EXTRA_RECEIVER)) != null) {
            Bundle serviceErrorBundle = new Bundle();
            serviceErrorBundle.putInt("error_code", -7);
            BLog.e(getClass().toString(), "Horizon Service not installed...");
            receiver.send(-7, serviceErrorBundle);
        }
    }

    /* access modifiers changed from: protected */
    public Intent newHorizonIntent(String action) {
        Intent intent = new Intent(action);
        intent.setPackage(BuildConstants.PACKAGE_NAME_HORIZON);
        return attachCallerInfo(intent);
    }

    private static void putExtraIfNonnull(Intent intent, String key, @Nullable String value) {
        if (value != null) {
            intent.putExtra(key, value);
        }
    }

    private class ProxyAuthCallback implements AuthResultCallback<Void, AuthLoginError> {
        private final AuthResultCallback<Void, AuthLoginError> mCallback;
        private final String mFbAccessToken;
        private final String mFbUserId;

        public ProxyAuthCallback(String fbUserId, String fbAccessToken, AuthResultCallback<Void, AuthLoginError> callback) {
            this.mFbUserId = fbUserId;
            this.mFbAccessToken = fbAccessToken;
            this.mCallback = callback;
        }

        public void onResult(@Nullable Void result) {
            OVRAuth.this.insertFbAccessToken(this.mFbUserId, this.mFbAccessToken);
            this.mCallback.onResult(result);
        }

        public void onError(AuthLoginError error) {
            this.mCallback.onError(error);
        }
    }

    /* access modifiers changed from: package-private */
    public void setIsUnderTest(boolean isUnderTest) {
        this.mIsUnderTest = isUnderTest;
    }
}
