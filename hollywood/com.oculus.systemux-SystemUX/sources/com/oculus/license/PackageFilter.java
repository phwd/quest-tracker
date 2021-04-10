package com.oculus.license;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

public final class PackageFilter implements Parcelable {
    public static final Parcelable.Creator<PackageFilter> CREATOR = null;
    public final String identifierPattern = null;
    public final List<Signer> signers = null;

    public PackageFilter(String str, List<Signer> list) {
        throw new RuntimeException("Stub!");
    }

    public PackageFilter(Parcel parcel) {
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
