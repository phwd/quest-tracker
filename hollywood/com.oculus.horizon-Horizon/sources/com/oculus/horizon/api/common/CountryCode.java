package com.oculus.horizon.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.common.base.Objects;
import java.text.CollationKey;
import java.text.Collator;
import java.util.Arrays;

public class CountryCode implements Comparable<CountryCode>, Parcelable {
    public static final Parcelable.Creator<CountryCode> CREATOR = new Parcelable.Creator<CountryCode>() {
        /* class com.oculus.horizon.api.common.CountryCode.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public CountryCode createFromParcel(Parcel parcel) {
            return new CountryCode(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public CountryCode[] newArray(int i) {
            return new CountryCode[i];
        }
    };
    public static final Collator sCollator = Collator.getInstance();
    public final String displayName;
    public final String isoCode;
    public final CollationKey mKey = sCollator.getCollationKey(toString());

    public int describeContents() {
        return 0;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.mKey});
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return Objects.equal(this.mKey, ((CountryCode) obj).mKey);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.isoCode);
        parcel.writeString(this.displayName);
    }

    public String toString() {
        return this.displayName;
    }

    public CountryCode(Parcel parcel) {
        this.isoCode = parcel.readString();
        this.displayName = parcel.readString();
    }

    public CountryCode(String str, String str2) {
        this.isoCode = str;
        this.displayName = str2;
    }

    public int compareTo(CountryCode countryCode) {
        return this.mKey.compareTo(countryCode.mKey);
    }
}
