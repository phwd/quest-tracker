package com.oculus.authapi;

import android.accounts.Account;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

@SuppressLint({"BadMethodUse-android.util.Log.e"})
public class AuthControl {
    public static final String ACCOUNT_TYPE_WORK_PLATFORM = "com.facebook.workplatform";
    public static final String FBPERMISSION_MANAGE_ACCOUNTS_WORK_PLATFORM = "com.oculus.horizon.fbpermission.MANAGE_ACCOUNTS_WORK_PLATFORM";
    public static final String TAG = "AuthControl";
    public final Context mContext;

    public void addAccount(Account account, String str) throws AuthError {
        if (account == null) {
            throw new NullPointerException("account is null");
        } else if (str == null) {
            throw new NullPointerException("accessToken is null");
        } else if (!TextUtils.isEmpty(str)) {
            Bundle bundle = new Bundle();
            bundle.putString("name", account.name);
            bundle.putString("type", account.type);
            bundle.putString("access_token", str);
            Bundle call = this.mContext.getContentResolver().call(AuthPrivateContract.URI_AUTHENTICATOR_CONTROL, AuthPrivateContract.METHOD_ADD_ACCOUNT, (String) null, bundle);
            if (call == null) {
                Log.e(TAG, "Content provider add_account method returned null");
                call = new Bundle();
                call.putInt("error_code", -7);
            }
            if (call.containsKey("error_code")) {
                throw new AuthError(call);
            }
        } else {
            throw new IllegalArgumentException("accessToken is empty");
        }
    }

    public AuthControl(Context context) {
        if (context != null) {
            this.mContext = context.getApplicationContext();
            return;
        }
        throw new NullPointerException("context is null");
    }
}
