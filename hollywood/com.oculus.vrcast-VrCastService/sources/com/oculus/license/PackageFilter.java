package com.oculus.license;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class PackageFilter implements Parcelable {
    public static final Parcelable.Creator<PackageFilter> CREATOR = new Parcelable.Creator<PackageFilter>() {
        /* class com.oculus.license.PackageFilter.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public PackageFilter createFromParcel(Parcel parcel) {
            return new PackageFilter(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public PackageFilter[] newArray(int i) {
            return new PackageFilter[i];
        }
    };
    public final String identifierPattern;
    public final List<Signer> signers;

    public int describeContents() {
        return 0;
    }

    public PackageFilter(String str, List<Signer> list) {
        this.identifierPattern = str;
        this.signers = Collections.unmodifiableList(list);
    }

    public PackageFilter(Parcel parcel) {
        this(parcel.readString(), parcel.createTypedArrayList(Signer.CREATOR));
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PackageFilter)) {
            return false;
        }
        PackageFilter packageFilter = (PackageFilter) obj;
        return Objects.equals(this.identifierPattern, packageFilter.identifierPattern) && Objects.equals(this.signers, packageFilter.signers);
    }

    public int hashCode() {
        return Objects.hash(this.identifierPattern, this.signers);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.identifierPattern);
        parcel.writeTypedList(this.signers);
    }
}
