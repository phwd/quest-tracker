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

    public License(long j, String str, long j2, long j3, String str2, String str3, Category category2, List<PackageFilter> list, List<Rule> list2) {
        throw new RuntimeException("Stub!");
    }

    public License(Parcel parcel) {
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

    public boolean isExpired(long j) {
        throw new RuntimeException("Stub!");
    }

    public boolean equals(Object obj) {
        throw new RuntimeException("Stub!");
    }

    public int hashCode() {
        throw new RuntimeException("Stub!");
    }

    public int describeContents() {
        throw new RuntimeException("Stub!");
    }

    public void writeToParcel(Parcel parcel, int i) {
        throw new RuntimeException("Stub!");
    }
}
