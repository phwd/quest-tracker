package com.oculus.license;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Date;
import java.util.List;

public final class License implements Parcelable {
    public static final Parcelable.Creator<License> CREATOR = null;
    public final Category category = null;
    public final String comment = null;
    public final long expires = 0;
    public final long id = 0;
    public final long issued = 0;
    public final String issuer = null;
    public final List<PackageFilter> packageFilters = null;
    public final String requester = null;
    public final List<Rule> rules = null;

    public License(long id2, String comment2, long issued2, long expires2, String requester2, String issuer2, Category category2, List<PackageFilter> list, List<Rule> list2) {
        throw new RuntimeException("Stub!");
    }

    public License(Parcel in) {
        throw new RuntimeException("Stub!");
    }

    public Date getIssuedTime() {
        throw new RuntimeException("Stub!");
    }

    public Date getExpiresTime() {
        throw new RuntimeException("Stub!");
    }

    public boolean isExpired() {
        throw new RuntimeException("Stub!");
    }

    public boolean isExpired(long asOf) {
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
