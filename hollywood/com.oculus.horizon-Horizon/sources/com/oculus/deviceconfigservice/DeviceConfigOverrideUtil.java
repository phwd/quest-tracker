package com.oculus.deviceconfigservice;

import X.AnonymousClass0NO;
import android.content.Context;
import com.facebook.infer.annotation.Nullsafe;
import java.util.ArrayList;
import java.util.Iterator;
import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class DeviceConfigOverrideUtil {
    public static final String PREF_BOOLEAN_VALUES = "pref_boolean_values";
    public static final String PREF_DOUBLE_VALUES = "pref_double_values";
    public static final String PREF_LONG_VALUES = "pref_long_values";
    public static final String PREF_STRING_VALUES = "pref_string_values";
    public static final String PREF_VALUE_SOURCES = "pref_value_sources";
    public static final String TAG = "DeviceConfigOverrideUtil";
    public static final ArrayList<DeviceConfigOverriddenValueInfo<Boolean>> mBooleanValueInfos = new ArrayList<>();
    public static final ArrayList<DeviceConfigOverriddenValueInfo<Double>> mDoubleValueInfos = new ArrayList<>();
    public static final ArrayList<DeviceConfigOverriddenValueInfo<Long>> mLongValueInfos = new ArrayList<>();
    public static final ArrayList<DeviceConfigOverriddenValueInfo<String>> mStringValueInfos = new ArrayList<>();

    public static <T> JSONObject A00(ArrayList<DeviceConfigOverriddenValueInfo<T>> arrayList, @Nullable JSONObject jSONObject) {
        if (jSONObject == null) {
            try {
                jSONObject = new JSONObject();
            } catch (JSONException e) {
                AnonymousClass0NO.A0K(TAG, e, "Exception while converting overridden value sources to storage", e);
                return jSONObject;
            }
        }
        Iterator<DeviceConfigOverriddenValueInfo<T>> it = arrayList.iterator();
        while (it.hasNext()) {
            DeviceConfigOverriddenValueInfo<T> next = it.next();
            jSONObject.put(next.mConfigParamName, next.mValueSource.toString());
        }
        return jSONObject;
    }

    public static <T> void A01(Context context, String str, ArrayList<DeviceConfigOverriddenValueInfo<T>> arrayList) {
        String str2;
        DeviceConfigSharedPreferencesNames deviceConfigSharedPreferencesNames = DeviceConfigSharedPreferencesNames.OVERRIDDEN_VALUE;
        try {
            JSONObject jSONObject = new JSONObject();
            Iterator<DeviceConfigOverriddenValueInfo<T>> it = arrayList.iterator();
            while (it.hasNext()) {
                DeviceConfigOverriddenValueInfo<T> next = it.next();
                jSONObject.put(next.mConfigParamName, next.mValue);
            }
            str2 = jSONObject.toString();
        } catch (JSONException e) {
            AnonymousClass0NO.A0H(TAG, e, "Exception while converting overridden values to storage");
            str2 = "{}";
        }
        context.getSharedPreferences(deviceConfigSharedPreferencesNames.toString(), 0).edit().putString(str, str2).commit();
        mStringValueInfos.clear();
    }
}
