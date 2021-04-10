package com.oculus.common.quickpromotion;

import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public abstract class JSONParcel {
    private static final String TAG = LoggingUtil.tag(JSONParcel.class);

    public abstract void setFromJSONObject(JSONObject jSONObject);

    protected static final <T extends JSONParcel> ArrayList<T> convertJSONArrayToParcelList(JSONArray jSONArray, Class<T> cls) {
        if (jSONArray == null) {
            Log.e(TAG, "Null array cannot be converted to parcel list");
            return null;
        }
        ArrayList<T> arrayList = new ArrayList<>();
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                T newInstance = cls.newInstance();
                newInstance.setFromJSONObject(jSONArray.optJSONObject(i));
                arrayList.add(newInstance);
            } catch (IllegalAccessException | InstantiationException unused) {
            }
        }
        return arrayList;
    }

    protected static final ArrayList<String> convertJSONArrayToStringList(JSONArray jSONArray) {
        if (jSONArray == null) {
            Log.e(TAG, "Null array cannot be converted to string list");
            return null;
        }
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(jSONArray.optString(i));
        }
        return arrayList;
    }
}
