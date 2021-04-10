package com.oculus.authapi;

import X.AnonymousClass0MD;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.ResultReceiver;
import com.oculus.auth.service.contract.ServiceContract;
import com.oculus.horizon.fbconnect.contract.FBConnectContent;
import javax.annotation.Nullable;

public class OVRAuth {
    public static final String EXTRA_RESULT = "result";
    public static final int RESULT_ERROR = 1;
    public static final int RESULT_OK = -1;
    public static final String TAG = "OVRAuth";
    public final CallerInfoProvider mCallerInfoProvider;
    public final Context mContext;
    public boolean mIsUnderTest;

    public interface CallerInfoProvider {
        Intent attachCallerInfo(Intent intent);
    }

    public class ProxyAuthCallback implements AuthResultCallback<Void, AuthLoginError> {
        public final AuthResultCallback<Void, AuthLoginError> mCallback;
        public final String mFbAccessToken;
        public final String mFbUserId;

        public ProxyAuthCallback(String str, String str2, AuthResultCallback<Void, AuthLoginError> authResultCallback) {
            this.mFbUserId = str;
            this.mFbAccessToken = str2;
            this.mCallback = authResultCallback;
        }

        public void onError(AuthLoginError authLoginError) {
            this.mCallback.onError(authLoginError);
        }

        /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
        @Override // com.oculus.authapi.AuthResultCallback
        public /* bridge */ /* synthetic */ void onResult(@Nullable Void r2) {
            onResult((Void) null);
        }

        public void onResult(@Nullable Void r4) {
            OVRAuth.this.insertFbAccessToken(this.mFbUserId, this.mFbAccessToken);
            this.mCallback.onResult(r4);
        }
    }

    public static final class TestReceiverForSending extends ResultReceiver {
        public final ResultReceiver mActualReceiver;

        public TestReceiverForSending(ResultReceiver resultReceiver) {
            super(null);
            this.mActualReceiver = resultReceiver;
        }

        public void send(int i, Bundle bundle) {
            this.mActualReceiver.send(i, bundle);
        }
    }

    @Deprecated
    public void login(String str, ResultReceiver resultReceiver) {
        loginWithCredentials(null, str, resultReceiver);
    }

    public void loginToFbAndLink(String str, String str2, @Nullable String str3, String str4, AuthResultCallback<Void, AuthFbLoginError> authResultCallback) {
        loginToFb(str, str2, str3, str4, ServiceContract.FOLLOW_UP_LINK, new AuthTaskReceiver<Void, AuthFbLoginError>(authResultCallback) {
            /* class com.oculus.authapi.OVRAuth.AnonymousClass17 */

            @Override // com.oculus.authapi.AuthTaskReceiver
            public Void createResultFromBundle(Bundle bundle) {
                return null;
            }

            @Override // com.oculus.authapi.AuthTaskReceiver
            public AuthFbLoginError createErrorFromBundle(Bundle bundle) {
                return new AuthFbLoginError(bundle);
            }
        });
    }

    public void reloginWithFbAuth(String str, AuthResultCallback<Void, AuthLoginWithFbAuthError> authResultCallback) {
        loginWithFbAuth(str, true, authResultCallback);
    }

    public void verifyRelogin(@Nullable String str, String str2, @Nullable String str3, @Nullable String str4, boolean z, AuthResultCallback<Void, AuthError> authResultCallback) {
        verifyLogin(str, str2, str3, str4, z, true, authResultCallback);
    }

    private Intent attachCallerInfo(Intent intent) {
        CallerInfoProvider callerInfoProvider = this.mCallerInfoProvider;
        if (callerInfoProvider != null) {
            return callerInfoProvider.attachCallerInfo(intent);
        }
        return intent;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void insertFbAccessToken(String str, String str2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("userid", str);
        contentValues.put("accesstoken", str2);
        this.mContext.getContentResolver().insert(FBConnectContent.Account.CONTENT_URI, contentValues);
    }

    public static void putExtraIfNonnull(Intent intent, String str, @Nullable String str2) {
        if (str2 != null) {
            intent.putExtra(str, str2);
        }
    }

    public void authWithOculusEmailAndPasswordForAccountLinking(String str, String str2, AuthResultCallback<AuthAuthWithOculusEmailAndPasswordForAccountLinkingResult, AuthLoginError> authResultCallback) {
        AnonymousClass4 r1 = new AuthTaskReceiver<AuthAuthWithOculusEmailAndPasswordForAccountLinkingResult, AuthLoginError>(authResultCallback) {
            /* class com.oculus.authapi.OVRAuth.AnonymousClass4 */

            @Override // com.oculus.authapi.AuthTaskReceiver
            public AuthLoginError createErrorFromBundle(Bundle bundle) {
                return new AuthLoginError(bundle);
            }

            @Override // com.oculus.authapi.AuthTaskReceiver
            public AuthAuthWithOculusEmailAndPasswordForAccountLinkingResult createResultFromBundle(Bundle bundle) {
                return new AuthAuthWithOculusEmailAndPasswordForAccountLinkingResult(bundle);
            }
        };
        Intent newHorizonIntent = newHorizonIntent(ServiceContract.ACTION_AUTH_WITH_OCULUS_EMAIL_AND_PASSWORD_FOR_ACCOUNT_LINKING);
        newHorizonIntent.putExtra("email", str);
        newHorizonIntent.putExtra("password", str2);
        newHorizonIntent.putExtra("receiver", getReceiverForSending(r1));
        startAuthService(newHorizonIntent);
    }

    public void checkFbMachineApproval(String str, String str2, AuthResultCallback<Boolean, AuthError> authResultCallback) {
        AnonymousClass18 r1 = new AuthTaskReceiver<Boolean, AuthError>(authResultCallback) {
            /* class com.oculus.authapi.OVRAuth.AnonymousClass18 */

            @Override // com.oculus.authapi.AuthTaskReceiver
            public Boolean createResultFromBundle(Bundle bundle) {
                return Boolean.valueOf(bundle.getBoolean(ServiceContract.EXTRA_MACHINE_APPROVED));
            }
        };
        Intent newHorizonIntent = newHorizonIntent(ServiceContract.ACTION_FB_CHECK_MACHINE_APPROVAL);
        newHorizonIntent.putExtra("uid", str);
        newHorizonIntent.putExtra("machine_id", str2);
        newHorizonIntent.putExtra("receiver", getReceiverForSending(r1));
        startAuthService(newHorizonIntent);
    }

    public void completeFbLoginAfterMachineApprovalAndLink(String str, String str2, String str3, @Nullable String str4, String str5, AuthResultCallback<Void, AuthError> authResultCallback) {
        completeFbLoginAfterMachineApproval(str, str2, str3, str4, str5, ServiceContract.FOLLOW_UP_LINK, new AuthVoidReceiver(authResultCallback));
    }

    public void completeFbLoginWithTwoFactorCodeAndLink(String str, String str2, String str3, String str4, String str5, @Nullable String str6, String str7, AuthResultCallback<Void, AuthError> authResultCallback) {
        completeFbLoginWithTwoFactorCode(str, str2, str3, str4, str5, str6, str7, ServiceContract.FOLLOW_UP_LINK, new AuthVoidReceiver(authResultCallback));
    }

    public void ensureDeviceOwnership(AuthResultCallback<Void, AuthError> authResultCallback) {
        AuthVoidReceiver authVoidReceiver = new AuthVoidReceiver(authResultCallback);
        Intent newHorizonIntent = newHorizonIntent(ServiceContract.ACTION_ENSURE_DEVICE_OWNERSHIP);
        newHorizonIntent.putExtra("receiver", getReceiverForSending(authVoidReceiver));
        startAuthService(newHorizonIntent);
    }

    public void fetchDeviceScopedCredentials(AuthResultCallback<AuthCredentials, AuthError> authResultCallback) {
        AnonymousClass9 r1 = new AuthTaskReceiver<AuthCredentials, AuthError>(authResultCallback) {
            /* class com.oculus.authapi.OVRAuth.AnonymousClass9 */

            @Override // com.oculus.authapi.AuthTaskReceiver
            public AuthCredentials createResultFromBundle(Bundle bundle) {
                return new AuthCredentials(bundle);
            }
        };
        Intent newHorizonIntent = newHorizonIntent(ServiceContract.ACTION_FETCH_DEVICE_SCOPED_CREDENTIALS);
        newHorizonIntent.putExtra("receiver", getReceiverForSending(r1));
        startAuthService(newHorizonIntent);
    }

    public void fetchFbInfoForAccountLinking(String str, AuthResultCallback<AuthFetchFbInfoForAccountLinkingResult, AuthFetchFbInfoForAccountLinkingError> authResultCallback) {
        AnonymousClass7 r1 = new AuthTaskReceiver<AuthFetchFbInfoForAccountLinkingResult, AuthFetchFbInfoForAccountLinkingError>(authResultCallback) {
            /* class com.oculus.authapi.OVRAuth.AnonymousClass7 */

            @Override // com.oculus.authapi.AuthTaskReceiver
            public AuthFetchFbInfoForAccountLinkingError createErrorFromBundle(Bundle bundle) {
                return new AuthFetchFbInfoForAccountLinkingError(bundle);
            }

            @Override // com.oculus.authapi.AuthTaskReceiver
            public AuthFetchFbInfoForAccountLinkingResult createResultFromBundle(Bundle bundle) {
                return new AuthFetchFbInfoForAccountLinkingResult(bundle);
            }
        };
        Intent newHorizonIntent = newHorizonIntent(ServiceContract.ACTION_FETCH_FB_INFO_FOR_ACCOUNT_LINKING);
        newHorizonIntent.putExtra(ServiceContract.EXTRA_FB_ACCESS_TOKEN, str);
        newHorizonIntent.putExtra("receiver", getReceiverForSending(r1));
        startAuthService(newHorizonIntent);
    }

    public void fetchHorizonDeviceScopedCredentials(AuthResultCallback<AuthCredentials, AuthError> authResultCallback) {
        AnonymousClass10 r1 = new AuthTaskReceiver<AuthCredentials, AuthError>(authResultCallback) {
            /* class com.oculus.authapi.OVRAuth.AnonymousClass10 */

            @Override // com.oculus.authapi.AuthTaskReceiver
            public AuthCredentials createResultFromBundle(Bundle bundle) {
                return new AuthCredentials(bundle);
            }
        };
        Intent newHorizonIntent = newHorizonIntent(ServiceContract.ACTION_FETCH_HORIZON_DEVICE_SCOPED_CREDENTIALS);
        newHorizonIntent.putExtra("receiver", getReceiverForSending(r1));
        startAuthService(newHorizonIntent);
    }

    public ResultReceiver getReceiverForSending(ResultReceiver resultReceiver) {
        if (this.mIsUnderTest) {
            return new TestReceiverForSending(resultReceiver);
        }
        Parcel obtain = Parcel.obtain();
        resultReceiver.writeToParcel(obtain, 0);
        obtain.setDataPosition(0);
        ResultReceiver resultReceiver2 = (ResultReceiver) ResultReceiver.CREATOR.createFromParcel(obtain);
        obtain.recycle();
        return resultReceiver2;
    }

    public void linkFbAccount(String str, String str2, @Nullable String str3, String str4, AuthResultCallback<Void, AuthError> authResultCallback) {
        AuthVoidReceiver authVoidReceiver = new AuthVoidReceiver(authResultCallback);
        Intent newHorizonIntent = newHorizonIntent(ServiceContract.ACTION_LINK_FB_ACCOUNT);
        newHorizonIntent.putExtra("uid", str);
        newHorizonIntent.putExtra("access_token", str2);
        newHorizonIntent.putExtra(ServiceContract.EXTRA_FB_LINK_TOS_VERSION, str3);
        newHorizonIntent.putExtra(ServiceContract.EXTRA_FB_LINK_LOGGING_JSON, str4);
        newHorizonIntent.putExtra("receiver", getReceiverForSending(authVoidReceiver));
        startAuthService(newHorizonIntent);
    }

    public Intent newHorizonIntent(String str) {
        Intent intent = new Intent(str);
        intent.setPackage("com.oculus.horizon");
        return attachCallerInfo(intent);
    }

    public void reloginWithEmailAndPassword(String str, String str2, AuthResultCallback<Void, AuthLoginError> authResultCallback) {
        loginWithEmailAndPassword(str, str2, true, new AuthTaskReceiver<Void, AuthLoginError>(authResultCallback) {
            /* class com.oculus.authapi.OVRAuth.AnonymousClass2 */

            @Override // com.oculus.authapi.AuthTaskReceiver
            public Void createResultFromBundle(Bundle bundle) {
                return null;
            }

            @Override // com.oculus.authapi.AuthTaskReceiver
            public AuthLoginError createErrorFromBundle(Bundle bundle) {
                return new AuthLoginError(bundle);
            }
        });
    }

    public void resendFbSms(String str, String str2, AuthResultCallback<Void, AuthError> authResultCallback) {
        AuthVoidReceiver authVoidReceiver = new AuthVoidReceiver(authResultCallback);
        Intent newHorizonIntent = newHorizonIntent(ServiceContract.ACTION_FB_RESEND_SMS);
        newHorizonIntent.putExtra("uid", str);
        newHorizonIntent.putExtra(ServiceContract.EXTRA_FIRST_FACTOR, str2);
        newHorizonIntent.putExtra("receiver", getReceiverForSending(authVoidReceiver));
        startAuthService(newHorizonIntent);
    }

    public void resendLoginApprovalsCode(String str, AuthResultCallback<Void, AuthError> authResultCallback) {
        AnonymousClass12 r1 = new AuthTaskReceiver<Void, AuthError>(authResultCallback) {
            /* class com.oculus.authapi.OVRAuth.AnonymousClass12 */

            @Override // com.oculus.authapi.AuthTaskReceiver
            public Void createResultFromBundle(Bundle bundle) {
                return null;
            }
        };
        Intent newHorizonIntent = newHorizonIntent(ServiceContract.ACTION_RESEND_LOGIN_APPROVAL_CODE);
        newHorizonIntent.putExtra("nonce", str);
        newHorizonIntent.putExtra("receiver", getReceiverForSending(r1));
        startAuthService(newHorizonIntent);
    }

    @SuppressLint({"BadMethodUse-android.content.Context.startService"})
    public void startAuthService(Intent intent) {
        ResultReceiver resultReceiver;
        try {
            if (this.mContext.startService(intent) == null) {
                AnonymousClass0MD.A04(TAG, "Horizon Service not installed...");
                Bundle extras = intent.getExtras();
                if (extras != null && (resultReceiver = (ResultReceiver) extras.getParcelable("receiver")) != null) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("error_code", -7);
                    resultReceiver.send(-7, bundle);
                }
            }
        } catch (Exception e) {
            AnonymousClass0MD.A0C(TAG, e, "Failed to communicate with Horizon service");
        }
    }

    public void status(final ResultReceiver resultReceiver) {
        Intent newHorizonIntent = newHorizonIntent(ServiceContract.ACTION_STATUS);
        newHorizonIntent.putExtra("receiver", getReceiverForSending(new ResultReceiver(null) {
            /* class com.oculus.authapi.OVRAuth.AnonymousClass8 */

            public void onReceiveResult(int i, Bundle bundle) {
                resultReceiver.send(i, bundle);
            }
        }));
        startAuthService(newHorizonIntent);
    }

    public void verifyLoginForAccountLinking(@Nullable String str, String str2, @Nullable String str3, @Nullable String str4, boolean z, AuthResultCallback<AuthVerifyLoginForAccountLinkingResult, AuthLoginError> authResultCallback) {
        AnonymousClass15 r1 = new AuthTaskReceiver<AuthVerifyLoginForAccountLinkingResult, AuthLoginError>(authResultCallback) {
            /* class com.oculus.authapi.OVRAuth.AnonymousClass15 */

            @Override // com.oculus.authapi.AuthTaskReceiver
            public AuthLoginError createErrorFromBundle(Bundle bundle) {
                return new AuthLoginError(bundle);
            }

            @Override // com.oculus.authapi.AuthTaskReceiver
            public AuthVerifyLoginForAccountLinkingResult createResultFromBundle(Bundle bundle) {
                return new AuthVerifyLoginForAccountLinkingResult(bundle);
            }
        };
        Intent newHorizonIntent = newHorizonIntent(ServiceContract.ACTION_VERIFY_LOGIN_FOR_ACCOUNT_LINKING);
        newHorizonIntent.putExtra("nonce", str);
        newHorizonIntent.putExtra(ServiceContract.EXTRA_PIN, str2);
        newHorizonIntent.putExtra(ServiceContract.EXTRA_TWO_FACTOR_METHOD_ID, str3);
        newHorizonIntent.putExtra(ServiceContract.EXTRA_FB_ACCESS_TOKEN, str4);
        newHorizonIntent.putExtra(ServiceContract.EXTRA_IS_SECURED_ACTION, z);
        newHorizonIntent.putExtra("receiver", getReceiverForSending(r1));
        startAuthService(newHorizonIntent);
    }

    public OVRAuth(Context context, CallerInfoProvider callerInfoProvider) {
        this.mContext = context;
        this.mCallerInfoProvider = callerInfoProvider;
    }

    public void setIsUnderTest(boolean z) {
        this.mIsUnderTest = z;
    }

    private void completeFbLoginAfterMachineApproval(String str, String str2, String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, ResultReceiver resultReceiver) {
        Intent newHorizonIntent = newHorizonIntent(ServiceContract.ACTION_FB_MACHINE_APPROVAL_LOGIN);
        newHorizonIntent.putExtra("uid", str);
        newHorizonIntent.putExtra("machine_id", str2);
        newHorizonIntent.putExtra(ServiceContract.EXTRA_AUTH_TOKEN, str3);
        putExtraIfNonnull(newHorizonIntent, ServiceContract.EXTRA_FB_LINK_TOS_VERSION, str4);
        putExtraIfNonnull(newHorizonIntent, ServiceContract.EXTRA_FB_LINK_LOGGING_JSON, str5);
        putExtraIfNonnull(newHorizonIntent, ServiceContract.EXTRA_FOLLOW_UP, str6);
        newHorizonIntent.putExtra("receiver", getReceiverForSending(resultReceiver));
        startAuthService(newHorizonIntent);
    }

    private void completeFbLoginWithTwoFactorCode(String str, String str2, String str3, String str4, String str5, @Nullable String str6, @Nullable String str7, @Nullable String str8, ResultReceiver resultReceiver) {
        Intent newHorizonIntent = newHorizonIntent(ServiceContract.ACTION_FB_VERIFY_LOGIN_APPROVALS_CODE);
        newHorizonIntent.putExtra("email", str);
        newHorizonIntent.putExtra("uid", str2);
        newHorizonIntent.putExtra("machine_id", str3);
        newHorizonIntent.putExtra(ServiceContract.EXTRA_FIRST_FACTOR, str4);
        newHorizonIntent.putExtra(ServiceContract.EXTRA_PIN, str5);
        putExtraIfNonnull(newHorizonIntent, ServiceContract.EXTRA_FB_LINK_TOS_VERSION, str6);
        putExtraIfNonnull(newHorizonIntent, ServiceContract.EXTRA_FB_LINK_LOGGING_JSON, str7);
        putExtraIfNonnull(newHorizonIntent, ServiceContract.EXTRA_FOLLOW_UP, str8);
        newHorizonIntent.putExtra("receiver", getReceiverForSending(resultReceiver));
        startAuthService(newHorizonIntent);
    }

    private void loginToFb(String str, String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, ResultReceiver resultReceiver) {
        Intent newHorizonIntent = newHorizonIntent(ServiceContract.ACTION_FB_LOGIN);
        newHorizonIntent.putExtra("email", str);
        newHorizonIntent.putExtra("password", str2);
        newHorizonIntent.putExtra(ServiceContract.EXTRA_FB_LINK_TOS_VERSION, str3);
        newHorizonIntent.putExtra(ServiceContract.EXTRA_FB_LINK_LOGGING_JSON, str4);
        putExtraIfNonnull(newHorizonIntent, ServiceContract.EXTRA_FOLLOW_UP, str5);
        newHorizonIntent.putExtra("receiver", getReceiverForSending(resultReceiver));
        startAuthService(newHorizonIntent);
    }

    private void loginWithEmailAndPassword(String str, String str2, boolean z, ResultReceiver resultReceiver) {
        Intent newHorizonIntent = newHorizonIntent(ServiceContract.ACTION_LOGIN_API);
        newHorizonIntent.putExtra("email", str);
        newHorizonIntent.putExtra("password", str2);
        newHorizonIntent.putExtra("receiver", getReceiverForSending(resultReceiver));
        newHorizonIntent.putExtra(ServiceContract.EXTRA_IS_RELOGIN, z);
        startAuthService(newHorizonIntent);
    }

    private void loginWithFbAuth(String str, boolean z, AuthResultCallback<Void, AuthLoginWithFbAuthError> authResultCallback) {
        AnonymousClass6 r1 = new AuthTaskReceiver<Void, AuthLoginWithFbAuthError>(authResultCallback) {
            /* class com.oculus.authapi.OVRAuth.AnonymousClass6 */

            @Override // com.oculus.authapi.AuthTaskReceiver
            public Void createResultFromBundle(Bundle bundle) {
                return null;
            }

            @Override // com.oculus.authapi.AuthTaskReceiver
            public AuthLoginWithFbAuthError createErrorFromBundle(Bundle bundle) {
                return new AuthLoginWithFbAuthError(bundle);
            }
        };
        Intent newHorizonIntent = newHorizonIntent(ServiceContract.ACTION_LOGIN_WITH_FB_AUTH);
        newHorizonIntent.putExtra(ServiceContract.EXTRA_FB_ACCESS_TOKEN, str);
        newHorizonIntent.putExtra("receiver", getReceiverForSending(r1));
        newHorizonIntent.putExtra(ServiceContract.EXTRA_IS_RELOGIN, z);
        startAuthService(newHorizonIntent);
    }

    private void verifyLogin(@Nullable String str, String str2, @Nullable String str3, @Nullable String str4, boolean z, boolean z2, AuthResultCallback<Void, AuthError> authResultCallback) {
        AnonymousClass14 r1 = new AuthTaskReceiver<Void, AuthError>(authResultCallback) {
            /* class com.oculus.authapi.OVRAuth.AnonymousClass14 */

            @Override // com.oculus.authapi.AuthTaskReceiver
            public Void createResultFromBundle(Bundle bundle) {
                return null;
            }
        };
        Intent newHorizonIntent = newHorizonIntent(ServiceContract.ACTION_VERIFY_LOGIN);
        newHorizonIntent.putExtra("nonce", str);
        newHorizonIntent.putExtra(ServiceContract.EXTRA_PIN, str2);
        newHorizonIntent.putExtra(ServiceContract.EXTRA_TWO_FACTOR_METHOD_ID, str3);
        newHorizonIntent.putExtra(ServiceContract.EXTRA_FB_ACCESS_TOKEN, str4);
        newHorizonIntent.putExtra(ServiceContract.EXTRA_IS_SECURED_ACTION, z);
        newHorizonIntent.putExtra(ServiceContract.EXTRA_IS_RELOGIN, z2);
        newHorizonIntent.putExtra("receiver", getReceiverForSending(r1));
        startAuthService(newHorizonIntent);
    }

    public void completeFbLoginAfterMachineApproval(String str, String str2, String str3, AuthResultCallback<AuthFbLoginResult, AuthError> authResultCallback) {
        completeFbLoginAfterMachineApproval(str, str2, str3, null, null, null, new AuthFbLoginReceiver(authResultCallback));
    }

    public void completeFbLoginWithTwoFactorCode(String str, String str2, String str3, String str4, String str5, AuthResultCallback<AuthFbLoginResult, AuthError> authResultCallback) {
        completeFbLoginWithTwoFactorCode(str, str2, str3, str4, str5, null, null, null, new AuthFbLoginReceiver(authResultCallback));
    }

    public void loginToFb(String str, String str2, AuthResultCallback<AuthFbLoginResult, AuthFbLoginError> authResultCallback) {
        loginToFb(str, str2, null, null, null, new AuthTaskReceiver<AuthFbLoginResult, AuthFbLoginError>(authResultCallback) {
            /* class com.oculus.authapi.OVRAuth.AnonymousClass16 */

            @Override // com.oculus.authapi.AuthTaskReceiver
            public AuthFbLoginError createErrorFromBundle(Bundle bundle) {
                return new AuthFbLoginError(bundle);
            }

            @Override // com.oculus.authapi.AuthTaskReceiver
            public AuthFbLoginResult createResultFromBundle(Bundle bundle) throws AuthFbLoginError {
                try {
                    return new AuthFbLoginResult(bundle);
                } catch (AuthError e) {
                    throw new AuthFbLoginError(e);
                }
            }
        });
    }

    public void loginWithCredentials(String str, String str2, ResultReceiver resultReceiver) {
        Intent newHorizonIntent = newHorizonIntent(ServiceContract.ACTION_LOGIN_API);
        newHorizonIntent.putExtra("user_id", str);
        newHorizonIntent.putExtra("access_token", str2);
        newHorizonIntent.putExtra("receiver", getReceiverForSending(resultReceiver));
        startAuthService(newHorizonIntent);
    }

    public void loginWithCredentials(String str, String str2, String str3, String str4, AuthResultCallback<Void, AuthLoginError> authResultCallback) {
        Intent newHorizonIntent = newHorizonIntent(ServiceContract.ACTION_LOGIN_API);
        newHorizonIntent.putExtra("user_id", str);
        newHorizonIntent.putExtra("access_token", str2);
        newHorizonIntent.putExtra(ServiceContract.EXTRA_FB_ACCESS_TOKEN, str4);
        newHorizonIntent.putExtra("receiver", getReceiverForSending(new AuthTaskReceiver<Void, AuthLoginError>(new ProxyAuthCallback(str3, str4, authResultCallback)) {
            /* class com.oculus.authapi.OVRAuth.AnonymousClass5 */

            @Override // com.oculus.authapi.AuthTaskReceiver
            public Void createResultFromBundle(Bundle bundle) {
                return null;
            }

            @Override // com.oculus.authapi.AuthTaskReceiver
            public AuthLoginError createErrorFromBundle(Bundle bundle) {
                return new AuthLoginError(bundle);
            }
        }));
        startAuthService(newHorizonIntent);
    }

    public void loginWithEmailAndPassword(String str, String str2, AuthResultCallback<Void, AuthLoginError> authResultCallback) {
        loginWithEmailAndPassword(str, str2, false, new AuthTaskReceiver<Void, AuthLoginError>(authResultCallback) {
            /* class com.oculus.authapi.OVRAuth.AnonymousClass1 */

            @Override // com.oculus.authapi.AuthTaskReceiver
            public Void createResultFromBundle(Bundle bundle) {
                return null;
            }

            @Override // com.oculus.authapi.AuthTaskReceiver
            public AuthLoginError createErrorFromBundle(Bundle bundle) {
                return new AuthLoginError(bundle);
            }
        });
    }

    public void loginWithEmailAndPassword(String str, String str2, String str3, String str4, AuthResultCallback<Void, AuthLoginError> authResultCallback) {
        Intent newHorizonIntent = newHorizonIntent(ServiceContract.ACTION_LOGIN_API);
        newHorizonIntent.putExtra("email", str);
        newHorizonIntent.putExtra("password", str2);
        newHorizonIntent.putExtra(ServiceContract.EXTRA_FB_ACCESS_TOKEN, str4);
        newHorizonIntent.putExtra("receiver", getReceiverForSending(new AuthTaskReceiver<Void, AuthLoginError>(new ProxyAuthCallback(str3, str4, authResultCallback)) {
            /* class com.oculus.authapi.OVRAuth.AnonymousClass3 */

            @Override // com.oculus.authapi.AuthTaskReceiver
            public Void createResultFromBundle(Bundle bundle) {
                return null;
            }

            @Override // com.oculus.authapi.AuthTaskReceiver
            public AuthLoginError createErrorFromBundle(Bundle bundle) {
                return new AuthLoginError(bundle);
            }
        }));
        startAuthService(newHorizonIntent);
    }

    public void loginWithFbAuth(String str, AuthResultCallback<Void, AuthLoginWithFbAuthError> authResultCallback) {
        loginWithFbAuth(str, false, authResultCallback);
    }

    public void logout(ResultReceiver resultReceiver) {
        logout(resultReceiver, false);
    }

    public void logout(final ResultReceiver resultReceiver, boolean z) {
        Intent newHorizonIntent = newHorizonIntent(ServiceContract.ACTION_LOGOUT);
        newHorizonIntent.putExtra("receiver", getReceiverForSending(new ResultReceiver(null) {
            /* class com.oculus.authapi.OVRAuth.AnonymousClass11 */

            public void onReceiveResult(int i, Bundle bundle) {
                resultReceiver.send(i, bundle);
            }
        }));
        newHorizonIntent.putExtra(ServiceContract.EXTRA_LOGOUT_WITHOUT_SERVER, z);
        startAuthService(newHorizonIntent);
    }

    public void sendTwoFactorCode(@Nullable String str, String str2, ResultReceiver resultReceiver) {
        Intent newHorizonIntent = newHorizonIntent(ServiceContract.ACTION_SEND_TWO_FACTOR_CODE);
        newHorizonIntent.putExtra("nonce", str);
        newHorizonIntent.putExtra(ServiceContract.EXTRA_TWO_FACTOR_METHOD_ID, str2);
        newHorizonIntent.putExtra("receiver", getReceiverForSending(resultReceiver));
        startAuthService(newHorizonIntent);
    }

    public void sendTwoFactorCode(@Nullable String str, String str2, AuthResultCallback<Void, AuthError> authResultCallback) {
        sendTwoFactorCode(str, str2, new AuthTaskReceiver<Void, AuthError>(authResultCallback) {
            /* class com.oculus.authapi.OVRAuth.AnonymousClass13 */

            @Override // com.oculus.authapi.AuthTaskReceiver
            public Void createResultFromBundle(Bundle bundle) {
                return null;
            }
        });
    }

    public void verifyLogin(@Nullable String str, String str2, @Nullable String str3, @Nullable String str4, boolean z, AuthResultCallback<Void, AuthError> authResultCallback) {
        verifyLogin(str, str2, str3, str4, z, false, authResultCallback);
    }
}
