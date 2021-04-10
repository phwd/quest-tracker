package com.oculus.userserver.api;

import android.annotation.SuppressLint;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint({"BadMethodUse-android.util.Log.e"})
public final class ExceptionUtils {
    private static final String KEY_MESSAGE = "message";
    private static final String KEY_TYPE = "type";
    private static final String TAG = "ExceptionUtils";

    @SuppressLint({"BadDependencyInjection"})
    private ExceptionUtils() {
        throw new AssertionError();
    }

    public static void wrapAndThrow(Class<? extends Exception> cls, String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(KEY_TYPE, cls.getName());
            jSONObject.put("message", str);
            throw new IllegalStateException(jSONObject.toString());
        } catch (JSONException e) {
            throw new IllegalStateException(e);
        }
    }

    static <E extends Exception> void unwrapAndMaybeThrow(IllegalStateException illegalStateException, Class<E> cls) throws Exception {
        String message = illegalStateException.getMessage();
        if (message != null) {
            try {
                JSONObject jSONObject = new JSONObject(message);
                if (cls.getName().equals(jSONObject.optString(KEY_TYPE))) {
                    String optString = jSONObject.optString("message");
                    throw cls.getDeclaredConstructor(String.class).newInstance(optString);
                }
            } catch (JSONException unused) {
            } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
                Log.e(TAG, "Error unwrapping exception", e);
            }
        }
    }
}
