package com.oculus.license;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class License implements Parcelable {
    public static final Parcelable.Creator<License> CREATOR = new Parcelable.Creator<License>() {
        /* class com.oculus.license.License.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public License createFromParcel(Parcel parcel) {
            return new License(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public License[] newArray(int i) {
            return new License[i];
        }
    };
    public final Category category;
    public final String comment;
    public final long expires;
    public final long id;
    public final long issued;
    public final String issuer;
    public final List<PackageFilter> packageFilters;
    public final String requester;
    public final List<Rule> rules;

    public int describeContents() {
        return 0;
    }

    public License(long j, String str, long j2, long j3, String str2, String str3, Category category2, List<PackageFilter> list, List<Rule> list2) {
        this.id = j;
        this.comment = str;
        this.issued = j2;
        this.expires = j3;
        this.requester = str2;
        this.issuer = str3;
        this.category = category2;
        this.packageFilters = Collections.unmodifiableList(list);
        this.rules = Collections.unmodifiableList(list2);
    }

    public License(Parcel parcel) {
        this(parcel.readLong(), parcel.readString(), parcel.readLong(), parcel.readLong(), parcel.readString(), parcel.readString(), (Category) parcel.readSerializable(), parcel.createTypedArrayList(PackageFilter.CREATOR), parcel.createTypedArrayList(Rule.CREATOR));
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof License)) {
            return false;
        }
        License license = (License) obj;
        return this.id == license.id && this.issued == license.issued && this.expires == license.expires && this.category == license.category && Objects.equals(this.comment, license.comment) && Objects.equals(this.requester, license.requester) && Objects.equals(this.issuer, license.issuer) && Objects.equals(this.packageFilters, license.packageFilters) && Objects.equals(this.rules, license.rules);
    }

    public int hashCode() {
        return Objects.hash(Long.valueOf(this.id));
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.id);
        parcel.writeString(this.comment);
        parcel.writeLong(this.issued);
        parcel.writeLong(this.expires);
        parcel.writeString(this.requester);
        parcel.writeString(this.issuer);
        parcel.writeSerializable(this.category);
        parcel.writeTypedList(this.packageFilters);
        parcel.writeTypedList(this.rules);
    }
}
