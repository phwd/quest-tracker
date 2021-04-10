package com.oculus.license;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public final class License implements Parcelable {
    public static final Parcelable.Creator<License> CREATOR = new Parcelable.Creator<License>() {
        /* class com.oculus.license.License.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public License createFromParcel(Parcel in) {
            return new License(in);
        }

        @Override // android.os.Parcelable.Creator
        public License[] newArray(int size) {
            return new License[size];
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

    public License(long id2, String comment2, long issued2, long expires2, String requester2, String issuer2, Category category2, List<PackageFilter> packageFilters2, List<Rule> rules2) {
        this.id = id2;
        this.comment = comment2;
        this.issued = issued2;
        this.expires = expires2;
        this.requester = requester2;
        this.issuer = issuer2;
        this.category = category2;
        this.packageFilters = Collections.unmodifiableList(packageFilters2);
        this.rules = Collections.unmodifiableList(rules2);
    }

    public License(Parcel in) {
        this(in.readLong(), in.readString(), in.readLong(), in.readLong(), in.readString(), in.readString(), (Category) in.readSerializable(), in.createTypedArrayList(PackageFilter.CREATOR), in.createTypedArrayList(Rule.CREATOR));
    }

    public Date getIssuedTime() {
        return new Date(this.issued);
    }

    public Date getExpiresTime() {
        return new Date(this.expires);
    }

    public boolean isExpired() {
        return isExpired(System.currentTimeMillis() / 1000);
    }

    public boolean isExpired(long asOf) {
        long j = this.expires;
        return j != 0 && j < asOf;
    }

    public int size() {
        int i;
        String str = this.comment;
        int i2 = 0;
        int length = (str != null ? str.length() : 0) + 1;
        String str2 = this.requester;
        int length2 = length + (str2 != null ? str2.length() : 0);
        String str3 = this.issuer;
        int length3 = length2 + (str3 != null ? str3.length() : 0);
        List<PackageFilter> list = this.packageFilters;
        if (list != null) {
            i = list.stream().mapToInt($$Lambda$xZzTKCwzw1YIMfpdFixMCKzAbU.INSTANCE).sum();
        } else {
            i = 0;
        }
        int i3 = length3 + i;
        List<Rule> list2 = this.rules;
        if (list2 != null) {
            i2 = list2.stream().mapToInt($$Lambda$Wx55pojjtjGl8w_U8K2xUCbfbY.INSTANCE).sum();
        }
        return i3 + i2;
    }

    public final boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof License)) {
            return false;
        }
        License o = (License) other;
        if (this.id != o.id || this.issued != o.issued || this.expires != o.expires || this.category != o.category || !Objects.equals(this.comment, o.comment) || !Objects.equals(this.requester, o.requester) || !Objects.equals(this.issuer, o.issuer) || !Objects.equals(this.packageFilters, o.packageFilters) || !Objects.equals(this.rules, o.rules)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Objects.hash(Long.valueOf(this.id));
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeLong(this.id);
        out.writeString(this.comment);
        out.writeLong(this.issued);
        out.writeLong(this.expires);
        out.writeString(this.requester);
        out.writeString(this.issuer);
        out.writeSerializable(this.category);
        out.writeTypedList(this.packageFilters);
        out.writeTypedList(this.rules);
    }
}
