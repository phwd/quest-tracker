package com.oculus.modules;

import X.AbstractC057411o;
import X.AnonymousClass006;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.util.Log;
import android.util.Pair;
import com.oculus.auth.service.contract.ServiceContract;
import com.oculus.authapi.AuthError;
import com.oculus.authapi.AuthFbLoginError;
import com.oculus.authapi.AuthResultCallback;
import com.oculus.authapi.OVRAuth;
import com.oculus.panellib.ThreadExecutor;
import com.oculus.panellib.modules.AbstractBroadcastReceiverModule;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import com.oculus.secure.trustedapp.CallerInfoHelper;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

public class AccountLinkingModule extends AbstractBroadcastReceiverModule {
    public static final String ACTION_COMPLETE_ACCOUNT_LINKING = "com.oculus.vrshell.home.COMPLETE_ACCOUNT_LINKING";
    public static final String KEY_SERVICE_PROVIDER = "service_provider";
    public static final Uri LINKED_ACCOUNTS_CONTENT_URI = Uri.parse("content://com.oculus.horizon.linkedaccounts/accounts");
    public static final String MODULE_NAME = "AccountLinkingModule";
    public Context mContext = null;
    public OVRAuth mOVRAuth;

    public static <T> T nonNullElse(@Nullable T t, T t2) {
        return t == null ? t2 : t;
    }

    private void ensureOVRAuth() {
        if (this.mOVRAuth == null) {
            this.mOVRAuth = new OVRAuth(this.mContext, new OVRAuth.CallerInfoProvider() {
                /* class com.oculus.modules.AccountLinkingModule.AnonymousClass7 */

                @Override // com.oculus.authapi.OVRAuth.CallerInfoProvider
                public Intent attachCallerInfo(Intent intent) {
                    try {
                        CallerInfoHelper.attachCallerInfo(intent, AccountLinkingModule.this.mContext, null);
                        return intent;
                    } catch (Exception e) {
                        Log.e(AccountLinkingModule.MODULE_NAME, "Exception while calling ovrauth", e);
                        return intent;
                    }
                }
            });
        }
    }

    public static JSONObject errorToJson(AuthError authError) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(ServiceContract.EXTRA_ERROR_TITLE, authError.mErrorTitle);
            jSONObject.put("error_message", authError.getMessage());
            jSONObject.put("error_code", authError.mErrorCode);
            return jSONObject;
        } catch (JSONException e) {
            Log.e(MODULE_NAME, "JSON Error", e);
            return new JSONObject();
        }
    }

    public static JSONObject fbLoginErrorToJson(AuthFbLoginError authFbLoginError) {
        try {
            JSONObject errorToJson = errorToJson(authFbLoginError);
            String str = authFbLoginError.mUid;
            if (str == null) {
                str = "";
            }
            errorToJson.put("uid", str);
            String str2 = authFbLoginError.mMachineId;
            if (str2 == null) {
                str2 = "";
            }
            errorToJson.put("machine_id", str2);
            String str3 = authFbLoginError.mAuthToken;
            if (str3 == null) {
                str3 = "";
            }
            errorToJson.put(ServiceContract.EXTRA_AUTH_TOKEN, str3);
            String str4 = authFbLoginError.mLoginFirstFactor;
            if (str4 == null) {
                str4 = "";
            }
            errorToJson.put(ServiceContract.EXTRA_LOGIN_FIRST_FACTOR, str4);
            return errorToJson;
        } catch (JSONException e) {
            Log.e(MODULE_NAME, "JSON Error", e);
            return new JSONObject();
        }
    }

    @Override // com.oculus.panellib.modules.AbstractBroadcastReceiverModule
    public IntentFilter getIntentFilter() {
        return new IntentFilter(ACTION_COMPLETE_ACCOUNT_LINKING);
    }

    public void invokeCallback(int i, String str) {
        RCTBaseJavaModule.nativeInvokeCallback(this.RVRCtxTag, i, AnonymousClass006.A09("[", str, "]"));
    }

    public final String marshallJSConstants() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("ERROR_CODE_INVALID_CREDS", -1);
            jSONObject.put("ERROR_CODE_RATE_LIMITED", -5);
            jSONObject.put("ERROR_CODE_TWO_FACTOR_LOGIN_REQUIRED", -8);
            jSONObject.put("ERROR_CODE_LOGIN_APPROVALS_INVALID_CODE", -10);
        } catch (JSONException unused) {
        }
        return jSONObject.toString();
    }

    @Override // com.oculus.panellib.modules.AbstractBroadcastReceiverModule
    public void processIntent(Intent intent, boolean z) {
        String stringExtra = intent.getStringExtra(KEY_SERVICE_PROVIDER);
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("serviceProvider", stringExtra);
            RCTBaseJavaModule.nativeEmitEventWithJsonData(this.RVRCtxTag, "onAccountLinkingCompleteListener", jSONObject.toString());
        } catch (JSONException e) {
            Log.e(MODULE_NAME, "JSON Error", e);
        }
    }

    public AccountLinkingModule(Context context) {
        super(context);
        this.mContext = context;
    }

    public static /* synthetic */ String access$200() {
        return MODULE_NAME;
    }

    public static List<Pair<String, String>> describe() {
        List<Pair<String, String>> describe = AbstractBroadcastReceiverModule.describe();
        describe.add(new Pair<>("unlink", "si"));
        describe.add(new Pair<>("facebookLinkWithCredentials", "ssssii"));
        describe.add(new Pair<>("facebookLinkCheckMachineApproval", "sssssii"));
        describe.add(new Pair<>("facebookLinkWithTwoFacCode", "sssssssii"));
        return describe;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void facebookLinkMachineApprovalLogin(String str, String str2, String str3, String str4, String str5, final int i, final int i2) {
        ensureOVRAuth();
        this.mOVRAuth.completeFbLoginAfterMachineApprovalAndLink(str, str3, str2, str4, str5, new AuthResultCallback<Void, AuthError>() {
            /* class com.oculus.modules.AccountLinkingModule.AnonymousClass5 */

            @Override // com.oculus.authapi.AuthResultCallback
            public void onError(AuthError authError) {
                AccountLinkingModule.this.invokeCallback(i2, AccountLinkingModule.errorToJson(authError).toString());
            }

            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
            @Override // com.oculus.authapi.AuthResultCallback
            public /* bridge */ /* synthetic */ void onResult(Void r2) {
                onResult((Void) null);
            }

            public void onResult(Void r4) {
                AccountLinkingModule.this.invokeCallback(i, "");
            }
        });
    }

    public void facebookLinkCheckMachineApproval(final String str, final String str2, final String str3, final String str4, final String str5, final int i, final int i2) {
        ensureOVRAuth();
        this.mOVRAuth.checkFbMachineApproval(str, str3, new AuthResultCallback<Boolean, AuthError>() {
            /* class com.oculus.modules.AccountLinkingModule.AnonymousClass4 */

            @Override // com.oculus.authapi.AuthResultCallback
            public void onError(AuthError authError) {
                AccountLinkingModule.this.invokeCallback(i2, AccountLinkingModule.errorToJson(authError).toString());
            }

            public void onResult(Boolean bool) {
                if (bool.booleanValue()) {
                    AccountLinkingModule.this.facebookLinkMachineApprovalLogin(str, str2, str3, str4, str5, i, i2);
                } else {
                    AccountLinkingModule.this.invokeCallback(i2, "");
                }
            }
        });
    }

    public void facebookLinkWithCredentials(String str, String str2, String str3, String str4, final int i, final int i2) {
        ensureOVRAuth();
        this.mOVRAuth.loginToFbAndLink(str, str2, str3, str4, new AuthResultCallback<Void, AuthFbLoginError>() {
            /* class com.oculus.modules.AccountLinkingModule.AnonymousClass3 */

            public void onError(AuthFbLoginError authFbLoginError) {
                int i;
                boolean z = false;
                if (authFbLoginError.mErrorCode == -8) {
                    z = true;
                }
                AccountLinkingModule accountLinkingModule = AccountLinkingModule.this;
                if (z) {
                    i = i;
                } else {
                    i = i2;
                }
                accountLinkingModule.invokeCallback(i, AccountLinkingModule.fbLoginErrorToJson(authFbLoginError).toString());
            }

            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
            @Override // com.oculus.authapi.AuthResultCallback
            public /* bridge */ /* synthetic */ void onResult(Void r2) {
                onResult((Void) null);
            }

            public void onResult(Void r4) {
                AccountLinkingModule.this.invokeCallback(i, "");
            }
        });
    }

    public void facebookLinkWithTwoFacCode(String str, String str2, String str3, String str4, String str5, String str6, String str7, final int i, final int i2) {
        ensureOVRAuth();
        this.mOVRAuth.completeFbLoginWithTwoFactorCodeAndLink(str2, str4, str5, str3, str, str6, str7, new AuthResultCallback<Void, AuthError>() {
            /* class com.oculus.modules.AccountLinkingModule.AnonymousClass6 */

            @Override // com.oculus.authapi.AuthResultCallback
            public void onError(AuthError authError) {
                AccountLinkingModule.this.invokeCallback(i2, AccountLinkingModule.errorToJson(authError).toString());
            }

            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
            @Override // com.oculus.authapi.AuthResultCallback
            public /* bridge */ /* synthetic */ void onResult(Void r2) {
                onResult((Void) null);
            }

            public void onResult(Void r4) {
                AccountLinkingModule.this.invokeCallback(i, "");
            }
        });
    }

    @Override // com.oculus.panellib.modules.AbstractBroadcastReceiverModule
    public String getModuleName() {
        return MODULE_NAME;
    }

    public void unlink(final String str, final int i) {
        ThreadExecutor.getInstance().execute(new Callable<Integer>() {
            /* class com.oculus.modules.AccountLinkingModule.AnonymousClass1 */

            @Override // java.util.concurrent.Callable
            public Integer call() {
                return Integer.valueOf(AccountLinkingModule.this.mContext.getContentResolver().delete(AccountLinkingModule.LINKED_ACCOUNTS_CONTENT_URI, null, new String[]{str}));
            }
        }, new AbstractC057411o<Integer>() {
            /* class com.oculus.modules.AccountLinkingModule.AnonymousClass2 */

            @Override // X.AbstractC057411o
            public void onFailure(@Nonnull Throwable th) {
                Log.e(AccountLinkingModule.MODULE_NAME, AnonymousClass006.A07("Exception while unlinking ", str), th);
                AccountLinkingModule.this.invokeCallback(i, "");
            }

            public void onSuccess(Integer num) {
                String str;
                String str2;
                try {
                    JSONObject jSONObject = new JSONObject();
                    if (num.intValue() == 1) {
                        str2 = "success";
                    } else {
                        str2 = "error";
                    }
                    str = jSONObject.put("result", str2).toString();
                } catch (JSONException e) {
                    Log.e(AccountLinkingModule.MODULE_NAME, "JSON Error", e);
                    str = "";
                }
                AccountLinkingModule.this.invokeCallback(i, str);
            }
        });
    }
}
