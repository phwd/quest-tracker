package com.oculus.license;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

public final class Rule implements Parcelable {
    public static final Parcelable.Creator<Rule> CREATOR = null;
    public final List<String> capabilities = null;
    public final List<FilterConfig> conditions = null;
    public final Disposition disposition = null;

    public enum Disposition {
        grant_or_skip,
        deny_or_skip,
        grant_or_deny
    }

    public Rule(Disposition disposition2, List<String> list, List<FilterConfig> list2) {
        throw new RuntimeException("Stub!");
    }

    public Rule(Parcel in) {
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
