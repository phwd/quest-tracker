package com.facebook.msys.mci;

import X.AnonymousClass006;
import X.AnonymousClass1Nh;
import X.AnonymousClass1PE;
import X.AnonymousClass1PG;
import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.util.Log;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.proguard.annotations.DoNotStrip;
import com.oculus.deviceconfigclient.shared.Constants;
import java.util.Map;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;
import org.json.JSONException;
import org.json.JSONObject;

@DoNotStrip
@SuppressLint({"MissingNativeLoadLibrary"})
@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
public class AuthDataStorage {
    public static volatile boolean sInitialized;
    public static volatile AnonymousClass1PG sObjectSerializer;
    public static volatile SharedPreferences sSharedPreferences;

    @DoNotStrip
    public static native void nativeInitialize();

    @DoNotStrip
    @Nullable
    public static Object getFromRawKey(String str) {
        if (sInitialized) {
            String string = sSharedPreferences.getString(str, null);
            if (string == null) {
                return null;
            }
            try {
                JSONObject jSONObject = new JSONObject(string);
                String string2 = jSONObject.getString("type");
                if (string2.equals("null")) {
                    return null;
                }
                if (string2.equals(Constants.DOUBLE_TYPE_JSON_VALUE)) {
                    String string3 = jSONObject.getString("value");
                    try {
                        return Double.valueOf(Double.parseDouble(string3));
                    } catch (NumberFormatException unused) {
                        throw new JSONException(AnonymousClass006.A07("Could not parse double ", string3));
                    }
                } else if (string2.equals("float")) {
                    String string4 = jSONObject.getString("value");
                    try {
                        return Float.valueOf(Float.parseFloat(string4));
                    } catch (NumberFormatException unused2) {
                        throw new JSONException(AnonymousClass006.A07("Could not parse float ", string4));
                    }
                } else if (string2.equals("int")) {
                    String string5 = jSONObject.getString("value");
                    try {
                        return Integer.valueOf(Integer.parseInt(string5));
                    } catch (NumberFormatException unused3) {
                        throw new JSONException(AnonymousClass006.A07("Could not parse int ", string5));
                    }
                } else if (string2.equals(Constants.LONG_TYPE_JSON_VALUE)) {
                    String string6 = jSONObject.getString("value");
                    try {
                        return Long.valueOf(Long.parseLong(string6));
                    } catch (NumberFormatException unused4) {
                        throw new JSONException(AnonymousClass006.A07("Could not parse long ", string6));
                    }
                } else if (string2.equals(Constants.STRING_TYPE_JSON_VALUE)) {
                    return jSONObject.getString("value");
                } else {
                    throw new JSONException(AnonymousClass006.A07("Unsupported type of object: ", string2));
                }
            } catch (JSONException e) {
                throw new AnonymousClass1PE(e);
            } catch (AnonymousClass1PE e2) {
                Log.e("AuthDataStorage", AnonymousClass006.A07("Error deserializing object for key ", str), e2);
                return null;
            }
        } else {
            throw new RuntimeException("Settings have not been initialized yet. Call initialize() first");
        }
    }

    @DoNotStrip
    public static void resetFromFacebookUserId(String str) {
        if (sInitialized) {
            Map<String, ?> all = sSharedPreferences.getAll();
            SharedPreferences.Editor edit = sSharedPreferences.edit();
            for (Map.Entry<String, ?> entry : all.entrySet()) {
                if (entry.getKey().startsWith(str)) {
                    edit.remove(entry.getKey());
                }
            }
            edit.commit();
            return;
        }
        throw new RuntimeException("Settings have not been initialized yet. Call initialize() first");
    }

    @DoNotStrip
    public static void setWithRawKey(String str, @Nullable Object obj) {
        if (sInitialized) {
            try {
                SharedPreferences.Editor edit = sSharedPreferences.edit();
                JSONObject jSONObject = new JSONObject();
                if (obj == null) {
                    try {
                        jSONObject.put("type", "null");
                    } catch (JSONException e) {
                        throw new AnonymousClass1PE(e);
                    }
                } else if (obj instanceof Double) {
                    jSONObject.put("type", Constants.DOUBLE_TYPE_JSON_VALUE);
                    jSONObject.put("value", obj.toString());
                } else if (obj instanceof Float) {
                    jSONObject.put("type", "float");
                    jSONObject.put("value", obj.toString());
                } else if (obj instanceof Integer) {
                    jSONObject.put("type", "int");
                    jSONObject.put("value", obj.toString());
                } else if (obj instanceof Long) {
                    jSONObject.put("type", Constants.LONG_TYPE_JSON_VALUE);
                    jSONObject.put("value", obj.toString());
                } else if (obj instanceof String) {
                    jSONObject.put("type", Constants.STRING_TYPE_JSON_VALUE);
                    jSONObject.put("value", obj.toString());
                } else {
                    throw new AnonymousClass1PE(AnonymousClass006.A07("Unsupported type of object: ", obj.getClass().getName()));
                }
                edit.putString(str, jSONObject.toString()).commit();
            } catch (AnonymousClass1PE e2) {
                Log.e("AuthDataStorage", AnonymousClass006.A07("Error serializing object for key ", str), e2);
            }
        } else {
            throw new RuntimeException("Settings have not been initialized yet. Call initialize() first");
        }
    }

    static {
        AnonymousClass1Nh.A00();
    }
}
