package com.oculus.horizon.push;

import android.content.Context;

public class PushTokenSharedPreferencesHelper {
    public static final String PUSH_TOKEN_ID_KEY_NAME = "push_token_id";
    public static final String PUSH_TOKEN_KEY_NAME = "push_token_key";
    public static final String SHARED_PREFS_NAME = "push_token_shared_prefs";

    public static long A00(Context context) {
        return context.getSharedPreferences(SHARED_PREFS_NAME, 0).getLong("push_token_id", 0);
    }
}
