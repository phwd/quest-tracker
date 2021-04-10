package com.oculus.userserver.api;

import android.annotation.SuppressLint;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint({"BadMethodUse-android.util.Log.e"})
public final class ExceptionUtils {
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_TYPE = "type";
    public static final String TAG = "ExceptionUtils";

    @SuppressLint({"BadDependencyInjection"})
    public ExceptionUtils() {
        throw new AssertionError();
    }

    public static <E extends Exception> void A00(IllegalStateException illegalStateException, Class<E> cls) throws Exception {
        String message = illegalStateException.getMessage();
        if (message != null) {
            try {
                JSONObject jSONObject = new JSONObject(message);
                if (cls.getName().equals(jSONObject.optString("type"))) {
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
