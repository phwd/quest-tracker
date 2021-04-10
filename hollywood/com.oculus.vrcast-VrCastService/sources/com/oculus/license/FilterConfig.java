package com.oculus.license;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class FilterConfig implements Parcelable {
    public static final Parcelable.Creator<FilterConfig> CREATOR = new Parcelable.Creator<FilterConfig>() {
        /* class com.oculus.license.FilterConfig.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public FilterConfig createFromParcel(Parcel parcel) {
            try {
                return new FilterConfig(parcel);
            } catch (Exception e) {
                throw new IllegalArgumentException(e);
            }
        }

        @Override // android.os.Parcelable.Creator
        public FilterConfig[] newArray(int i) {
            return new FilterConfig[i];
        }
    };
    public final JSONObject configuration;
    private int configurationSize;
    public final FilterClass filterClass;

    public int describeContents() {
        return 0;
    }

    public FilterConfig(FilterClass filterClass2, JSONObject jSONObject) {
        this.configurationSize = -1;
        this.filterClass = filterClass2;
        this.configuration = jSONObject;
    }

    public FilterConfig(Parcel parcel) throws Exception {
        this((FilterClass) parcel.readSerializable(), new JSONObject(parcel.readString()));
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FilterConfig)) {
            return false;
        }
        FilterConfig filterConfig = (FilterConfig) obj;
        return Objects.equals(this.filterClass, filterConfig.filterClass) && equals(this.configuration, filterConfig.configuration);
    }

    public int hashCode() {
        return Objects.hash(this.filterClass, this.configuration);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeSerializable(this.filterClass);
        parcel.writeString(this.configuration.toString());
    }

    private static boolean equals(JSONObject jSONObject, JSONObject jSONObject2) {
        try {
            return Objects.equals(toMap(jSONObject), toMap(jSONObject2));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private static Map<String, Object> toMap(JSONObject jSONObject) throws JSONException {
        HashMap hashMap = new HashMap();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            hashMap.put(next, toValue(jSONObject.get(next)));
        }
        return hashMap;
    }

    private static List<Object> toList(JSONArray jSONArray) throws JSONException {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(toValue(jSONArray.get(i)));
        }
        return arrayList;
    }

    private static Object toValue(Object obj) throws JSONException {
        if (obj instanceof JSONArray) {
            return toList((JSONArray) obj);
        }
        return obj instanceof JSONObject ? toMap((JSONObject) obj) : obj;
    }
}
