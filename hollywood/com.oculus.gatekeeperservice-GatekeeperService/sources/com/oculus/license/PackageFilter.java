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
        public PackageFilter createFromParcel(Parcel in) {
            return new PackageFilter(in);
        }

        @Override // android.os.Parcelable.Creator
        public PackageFilter[] newArray(int size) {
            return new PackageFilter[size];
        }
    };
    public final String identifierPattern;
    public final List<Signer> signers;

    public PackageFilter(String identifierPattern2, List<Signer> signers2) {
        this.identifierPattern = identifierPattern2;
        this.signers = Collections.unmodifiableList(signers2);
    }

    public PackageFilter(Parcel in) {
        this(in.readString(), in.createTypedArrayList(Signer.CREATOR));
    }

    public int size() {
        String str = this.identifierPattern;
        int i = 0;
        int length = (str != null ? str.length() : 0) + 1;
        List<Signer> list = this.signers;
        if (list != null) {
            i = list.stream().mapToInt($$Lambda$O9E31hM3rnoLDM9fLrzBXMvSRNk.INSTANCE).sum();
        }
        return length + i;
    }

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof PackageFilter)) {
            return false;
        }
        PackageFilter o = (PackageFilter) other;
        if (!Objects.equals(this.identifierPattern, o.identifierPattern) || !Objects.equals(this.signers, o.signers)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Objects.hash(this.identifierPattern, this.signers);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(this.identifierPattern);
        out.writeTypedList(this.signers);
    }
}
