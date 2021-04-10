package com.oculus.modules;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.util.Log;
import android.util.Pair;
import com.google.common.util.concurrent.FutureCallback;
import com.oculus.authapi.AuthError;
import com.oculus.authapi.AuthFbLoginError;
import com.oculus.authapi.AuthResultCallback;
import com.oculus.authapi.OVRAuth;
import com.oculus.common.build.BuildConfig;
import com.oculus.panellib.ThreadExecutor;
import com.oculus.panellib.modules.AbstractBroadcastReceiverModule;
import com.oculus.secure.trustedapp.CallerInfoHelper;
import java.util.List;
import java.util.concurrent.Callable;
import org.json.JSONException;
import org.json.JSONObject;

public class AccountLinkingModule extends AbstractBroadcastReceiverModule {
    private static final String ACTION_COMPLETE_ACCOUNT_LINKING = "com.oculus.vrshell.home.COMPLETE_ACCOUNT_LINKING";
    private static final String KEY_SERVICE_PROVIDER = "service_provider";
    private static final Uri LINKED_ACCOUNTS_CONTENT_URI = Uri.parse("content://com.oculus.horizon.linkedaccounts/accounts");
    private static final String MODULE_NAME = AccountLinkingModule.class.getSimpleName();
    private Context mContext = null;
    private OVRAuth mOVRAuth;

    public void invokeCallback(int callbackID, String jsonString) {
        nativeInvokeCallback(this.RVRCtxTag, callbackID, "[" + jsonString + "]");
    }

    public AccountLinkingModule(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override // com.oculus.panellib.modules.AbstractBroadcastReceiverModule
    public String getModuleName() {
        return MODULE_NAME;
    }

    protected static List<Pair<String, String>> describe() {
        List<Pair<String, String>> list = AbstractBroadcastReceiverModule.describe();
        list.add(new Pair<>("unlink", "si"));
        list.add(new Pair<>("facebookLinkWithCredentials", "ssssii"));
        list.add(new Pair<>("facebookLinkCheckMachineApproval", "sssssii"));
        list.add(new Pair<>("facebookLinkWithTwoFacCode", "sssssssii"));
        return list;
    }

    /* access modifiers changed from: protected */
    public final String marshallJSConstants() {
        JSONObject result = new JSONObject();
        try {
            result.put("ERROR_CODE_INVALID_CREDS", -1);
            result.put("ERROR_CODE_RATE_LIMITED", -5);
            result.put("ERROR_CODE_TWO_FACTOR_LOGIN_REQUIRED", -8);
            result.put("ERROR_CODE_LOGIN_APPROVALS_INVALID_CODE", -10);
        } catch (JSONException e) {
        }
        return result.toString();
    }

    @Override // com.oculus.panellib.modules.AbstractBroadcastReceiverModule
    public IntentFilter getIntentFilter() {
        return new IntentFilter(ACTION_COMPLETE_ACCOUNT_LINKING);
    }

    @Override // com.oculus.panellib.modules.AbstractBroadcastReceiverModule
    public void processIntent(Intent intent, boolean forceNativeUpdate) {
        Log.d(MODULE_NAME, "processIntent()");
        String serviceProvider = intent.getStringExtra(KEY_SERVICE_PROVIDER);
        try {
            JSONObject response = new JSONObject();
            response.put("serviceProvider", serviceProvider);
            nativeEmitEventWithJsonData(this.RVRCtxTag, "onAccountLinkingCompleteListener", response.toString());
        } catch (JSONException e) {
            Log.e(MODULE_NAME, "JSON Error", e);
        }
    }

    public void unlink(final String serviceProvider, final int callbackID) {
        Log.d(MODULE_NAME, "unlink " + serviceProvider);
        ThreadExecutor.getInstance().execute(new Callable<Integer>() {
            /* class com.oculus.modules.AccountLinkingModule.AnonymousClass1 */

            @Override // java.util.concurrent.Callable
            public Integer call() {
                return Integer.valueOf(AccountLinkingModule.this.mContext.getContentResolver().delete(AccountLinkingModule.LINKED_ACCOUNTS_CONTENT_URI, null, new String[]{serviceProvider}));
            }
        }, new FutureCallback<Integer>() {
            /* class com.oculus.modules.AccountLinkingModule.AnonymousClass2 */

            public void onSuccess(Integer numOfLinkedAccountsDeleted) {
                String result = BuildConfig.PROVIDER_SUFFIX;
                try {
                    result = new JSONObject().put("result", numOfLinkedAccountsDeleted.intValue() == 1 ? "success" : "error").toString();
                } catch (JSONException e) {
                    Log.e(AccountLinkingModule.MODULE_NAME, "JSON Error", e);
                }
                AccountLinkingModule.this.invokeCallback(callbackID, result);
            }

            @Override // com.google.common.util.concurrent.FutureCallback
            public void onFailure(Throwable throwable) {
                Log.e(AccountLinkingModule.MODULE_NAME, "Exception while unlinking " + serviceProvider, throwable);
                AccountLinkingModule.this.invokeCallback(callbackID, BuildConfig.PROVIDER_SUFFIX);
            }
        });
    }

    public void facebookLinkWithCredentials(String emailOrPhone, String password, String tosVersion, String loggingExtrasJson, final int successCallbackID, final int failureCallbackID) {
        ensureOVRAuth();
        this.mOVRAuth.loginToFbAndLink(emailOrPhone, password, tosVersion, loggingExtrasJson, new AuthResultCallback<Void, AuthFbLoginError>() {
            /* class com.oculus.modules.AccountLinkingModule.AnonymousClass3 */

            public void onResult(Void result) {
                AccountLinkingModule.this.invokeCallback(successCallbackID, BuildConfig.PROVIDER_SUFFIX);
            }

            public void onError(AuthFbLoginError error) {
                AccountLinkingModule.this.invokeCallback(error.getErrorCode() == -8 ? successCallbackID : failureCallbackID, AccountLinkingModule.fbLoginErrorToJson(error).toString());
            }
        });
    }

    public void facebookLinkCheckMachineApproval(final String uid, final String authToken, final String machineID, final String tosVersion, final String loggingExtrasJson, final int successCallbackID, final int failureCallbackID) {
        ensureOVRAuth();
        this.mOVRAuth.checkFbMachineApproval(uid, machineID, new AuthResultCallback<Boolean, AuthError>() {
            /* class com.oculus.modules.AccountLinkingModule.AnonymousClass4 */

            public void onResult(Boolean result) {
                if (result.booleanValue()) {
                    AccountLinkingModule.this.facebookLinkMachineApprovalLogin(uid, authToken, machineID, tosVersion, loggingExtrasJson, successCallbackID, failureCallbackID);
                } else {
                    AccountLinkingModule.this.invokeCallback(failureCallbackID, BuildConfig.PROVIDER_SUFFIX);
                }
            }

            @Override // com.oculus.authapi.AuthResultCallback
            public void onError(AuthError error) {
                AccountLinkingModule.this.invokeCallback(failureCallbackID, AccountLinkingModule.errorToJson(error).toString());
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void facebookLinkMachineApprovalLogin(String uid, String authToken, String machineID, String tosVersion, String loggingExtrasJson, final int successCallbackID, final int failureCallbackID) {
        ensureOVRAuth();
        this.mOVRAuth.completeFbLoginAfterMachineApprovalAndLink(uid, machineID, authToken, tosVersion, loggingExtrasJson, new AuthResultCallback<Void, AuthError>() {
            /* class com.oculus.modules.AccountLinkingModule.AnonymousClass5 */

            public void onResult(Void result) {
                AccountLinkingModule.this.invokeCallback(successCallbackID, BuildConfig.PROVIDER_SUFFIX);
            }

            @Override // com.oculus.authapi.AuthResultCallback
            public void onError(AuthError error) {
                AccountLinkingModule.this.invokeCallback(failureCallbackID, AccountLinkingModule.errorToJson(error).toString());
            }
        });
    }

    public void facebookLinkWithTwoFacCode(String twoFacCode, String emailOrPhone, String firstFactor, String uid, String machineID, String tosVersion, String loggingExtrasJson, final int successCallbackID, final int failureCallbackID) {
        ensureOVRAuth();
        this.mOVRAuth.completeFbLoginWithTwoFactorCodeAndLink(emailOrPhone, uid, machineID, firstFactor, twoFacCode, tosVersion, loggingExtrasJson, new AuthResultCallback<Void, AuthError>() {
            /* class com.oculus.modules.AccountLinkingModule.AnonymousClass6 */

            public void onResult(Void result) {
                AccountLinkingModule.this.invokeCallback(successCallbackID, BuildConfig.PROVIDER_SUFFIX);
            }

            @Override // com.oculus.authapi.AuthResultCallback
            public void onError(AuthError error) {
                AccountLinkingModule.this.invokeCallback(failureCallbackID, AccountLinkingModule.errorToJson(error).toString());
            }
        });
    }

    private void ensureOVRAuth() {
        if (this.mOVRAuth == null) {
            this.mOVRAuth = new OVRAuth(this.mContext, new OVRAuth.CallerInfoProvider() {
                /* class com.oculus.modules.AccountLinkingModule.AnonymousClass7 */

                @Override // com.oculus.authapi.OVRAuth.CallerInfoProvider
                public Intent attachCallerInfo(Intent intent) {
                    try {
                        return CallerInfoHelper.attachCallerInfo(intent, AccountLinkingModule.this.mContext, null);
                    } catch (Exception e) {
                        Log.e(AccountLinkingModule.MODULE_NAME, "Exception while calling ovrauth", e);
                        return intent;
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public static JSONObject errorToJson(AuthError error) {
        try {
            JSONObject resultJson = new JSONObject();
            resultJson.put("error_title", error.getErrorTitle());
            resultJson.put("error_message", error.getMessage());
            resultJson.put("error_code", error.getErrorCode());
            return resultJson;
        } catch (JSONException e) {
            Log.e(MODULE_NAME, "JSON Error", e);
            return new JSONObject();
        }
    }

    /* access modifiers changed from: private */
    public static JSONObject fbLoginErrorToJson(AuthFbLoginError error) {
        try {
            JSONObject resultJson = errorToJson(error);
            resultJson.put("uid", nonNullElse(error.getUid(), BuildConfig.PROVIDER_SUFFIX));
            resultJson.put("machine_id", nonNullElse(error.getMachineId(), BuildConfig.PROVIDER_SUFFIX));
            resultJson.put("auth_token", nonNullElse(error.getAuthToken(), BuildConfig.PROVIDER_SUFFIX));
            resultJson.put("login_first_factor", nonNullElse(error.getLoginFirstFactor(), BuildConfig.PROVIDER_SUFFIX));
            return resultJson;
        } catch (JSONException e) {
            Log.e(MODULE_NAME, "JSON Error", e);
            return new JSONObject();
        }
    }

    private static <T> T nonNullElse(T value, T elseValue) {
        return value != null ? value : elseValue;
    }
}
