package com.oculus.userserver.api;

import android.annotation.SuppressLint;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint({"BadMethodUse-android.util.Log.e"})
public final class ExceptionUtils {
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_TYPE = "type";
    public static final String TAG = "ExceptionUtils";

    public static void A00(Class<? extends Exception> cls, String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(KEY_TYPE, cls.getName());
            jSONObject.put("message", str);
            throw new IllegalStateException(jSONObject.toString());
        } catch (JSONException e) {
            throw new IllegalStateException(e);
        }
    }

    @SuppressLint({"BadDependencyInjection"})
    public ExceptionUtils() {
        throw new AssertionError();
    }
}
