package com.oculus.license;

import android.os.Parcel;
import android.os.Parcelable;
import org.json.JSONObject;

public final class FilterConfig implements Parcelable {
    public static final Parcelable.Creator<FilterConfig> CREATOR = null;
    public final JSONObject configuration = null;
    public final FilterClass filterClass = null;

    public FilterConfig(FilterClass filterClass2, JSONObject configuration2) {
        throw new RuntimeException("Stub!");
    }

    public FilterConfig(Parcel in) throws Exception {
        throw new RuntimeException("Stub!");
    }

    public boolean equals(Object other) {
        throw new RuntimeException("Stub!");
    }

    public int hashCode() {
        throw new RuntimeException("Stub!");
    }

    public int describeContents() {
        throw new RuntimeException("Stub!");
    }

    public void writeToParcel(Parcel out, int flags) {
        throw new RuntimeException("Stub!");
    }
}
