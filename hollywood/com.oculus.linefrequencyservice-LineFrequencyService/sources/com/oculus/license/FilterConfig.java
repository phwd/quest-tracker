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
        public FilterConfig createFromParcel(Parcel in) {
            try {
                return new FilterConfig(in);
            } catch (Exception e) {
                throw new IllegalArgumentException(e);
            }
        }

        @Override // android.os.Parcelable.Creator
        public FilterConfig[] newArray(int size) {
            return new FilterConfig[size];
        }
    };
    public final JSONObject configuration;
    private int configurationSize;
    public final FilterClass filterClass;

    public FilterConfig(FilterClass filterClass2, JSONObject configuration2) {
        this.configurationSize = -1;
        this.filterClass = filterClass2;
        this.configuration = configuration2;
    }

    public FilterConfig(Parcel in) throws Exception {
        this((FilterClass) in.readSerializable(), new JSONObject(in.readString()));
    }

    public int size() {
        if (this.configurationSize == -1) {
            try {
                this.configurationSize = this.configuration.toString(0).length();
            } catch (JSONException e) {
                this.configurationSize = 0;
            }
        }
        return this.configurationSize + 1;
    }

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof FilterConfig)) {
            return false;
        }
        FilterConfig o = (FilterConfig) other;
        if (!Objects.equals(this.filterClass, o.filterClass) || !equals(this.configuration, o.configuration)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Objects.hash(this.filterClass, this.configuration);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeSerializable(this.filterClass);
        out.writeString(this.configuration.toString());
    }

    private static boolean equals(JSONObject obj1, JSONObject obj2) {
        try {
            return Objects.equals(toMap(obj1), toMap(obj2));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private static Map<String, Object> toMap(JSONObject obj) throws JSONException {
        Map<String, Object> m = new HashMap<>();
        Iterator<String> keys = obj.keys();
        while (keys.hasNext()) {
            String k = keys.next();
            m.put(k, toValue(obj.get(k)));
        }
        return m;
    }

    private static List<Object> toList(JSONArray arr) throws JSONException {
        List<Object> l = new ArrayList<>();
        for (int i = 0; i < arr.length(); i++) {
            l.add(toValue(arr.get(i)));
        }
        return l;
    }

    private static Object toValue(Object v) throws JSONException {
        if (v instanceof JSONArray) {
            return toList((JSONArray) v);
        }
        if (v instanceof JSONObject) {
            return toMap((JSONObject) v);
        }
        return v;
    }
}
