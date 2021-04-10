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
    private static final String TAG = ExceptionUtils.class.getSimpleName();

    @SuppressLint({"BadDependencyInjection"})
    private ExceptionUtils() {
        throw new AssertionError();
    }

    public static void wrapAndThrow(Class<? extends Exception> type, String message) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(KEY_TYPE, type.getName());
            jsonObject.put(KEY_MESSAGE, message);
            throw new IllegalStateException(jsonObject.toString());
        } catch (JSONException e) {
            throw new IllegalStateException(e);
        }
    }

    static <E extends Exception> void unwrapAndMaybeThrow(IllegalStateException wrapper, Class<E> type) throws Exception {
        String jsonMessage = wrapper.getMessage();
        if (jsonMessage != null) {
            try {
                JSONObject jsonObject = new JSONObject(jsonMessage);
                if (type.getName().equals(jsonObject.optString(KEY_TYPE))) {
                    String message = jsonObject.optString(KEY_MESSAGE);
                    throw type.getDeclaredConstructor(String.class).newInstance(message);
                }
            } catch (JSONException e) {
            } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e2) {
                Log.e(TAG, "Error unwrapping exception", e2);
            }
        }
    }
}
