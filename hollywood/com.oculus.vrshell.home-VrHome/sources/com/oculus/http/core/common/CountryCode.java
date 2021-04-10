package com.oculus.http.core.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.common.base.Objects;
import java.text.CollationKey;
import java.text.Collator;

public class CountryCode implements Parcelable, Comparable<CountryCode> {
    public static final Parcelable.Creator<CountryCode> CREATOR = new Parcelable.Creator<CountryCode>() {
        /* class com.oculus.http.core.common.CountryCode.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public CountryCode createFromParcel(Parcel in) {
            return new CountryCode(in);
        }

        @Override // android.os.Parcelable.Creator
        public CountryCode[] newArray(int size) {
            return new CountryCode[size];
        }
    };
    private static final Collator sCollator = Collator.getInstance();
    public final String displayName;
    public final String isoCode;
    private final CollationKey mKey = sCollator.getCollationKey(toString());

    public CountryCode(String isoCode2, String displayName2) {
        this.isoCode = isoCode2;
        this.displayName = displayName2;
    }

    public CountryCode(Parcel parcel) {
        this.isoCode = parcel.readString();
        this.displayName = parcel.readString();
    }

    public String toString() {
        return this.displayName;
    }

    public int compareTo(CountryCode countryCode) {
        return this.mKey.compareTo(countryCode.mKey);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return Objects.equal(this.mKey, ((CountryCode) o).mKey);
    }

    public int hashCode() {
        return Objects.hashCode(this.mKey);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.isoCode);
        dest.writeString(this.displayName);
    }
}
