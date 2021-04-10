package com.oculus.license;

import android.os.Parcel;
import android.os.Parcelable;
import org.json.JSONObject;

public final class FilterConfig implements Parcelable {
    public static final Parcelable.Creator<FilterConfig> CREATOR = null;
    public final JSONObject configuration = null;
    public final FilterClass filterClass = null;

    public int describeContents() {
        throw new RuntimeException("Stub!");
    }

    public boolean equals(Object obj) {
        throw new RuntimeException("Stub!");
    }

    public int hashCode() {
        throw new RuntimeException("Stub!");
    }

    public void writeToParcel(Parcel parcel, int i) {
        throw new RuntimeException("Stub!");
    }

    public FilterConfig(Parcel parcel) throws Exception {
        throw new RuntimeException("Stub!");
    }

    public FilterConfig(FilterClass filterClass2, JSONObject jSONObject) {
        throw new RuntimeException("Stub!");
    }
}
