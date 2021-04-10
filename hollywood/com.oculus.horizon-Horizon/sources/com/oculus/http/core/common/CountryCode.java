package com.oculus.http.core.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.common.base.Objects;
import java.text.CollationKey;
import java.text.Collator;
import java.util.Arrays;

public class CountryCode implements Comparable<CountryCode>, Parcelable {
    public static final Parcelable.Creator<CountryCode> CREATOR = new Parcelable.Creator<CountryCode>() {
        /* class com.oculus.http.core.common.CountryCode.AnonymousClass1 */

        /* Return type fixed from 'java.lang.Object' to match base method */
        @Override // android.os.Parcelable.Creator
        public final CountryCode createFromParcel(Parcel parcel) {
            return new CountryCode(parcel);
        }

        /* Return type fixed from 'java.lang.Object[]' to match base method */
        @Override // android.os.Parcelable.Creator
        public final CountryCode[] newArray(int i) {
            return new CountryCode[i];
        }
    };
    public static final Collator sCollator = Collator.getInstance();
    public final String displayName;
    public final String isoCode;
    public final CollationKey mKey = sCollator.getCollationKey(toString());

    public final int describeContents() {
        return 0;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.mKey});
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // java.lang.Comparable
    public final int compareTo(CountryCode countryCode) {
        return this.mKey.compareTo(countryCode.mKey);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return Objects.equal(this.mKey, ((CountryCode) obj).mKey);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.isoCode);
        parcel.writeString(this.displayName);
    }

    public CountryCode(Parcel parcel) {
        this.isoCode = parcel.readString();
        this.displayName = parcel.readString();
    }

    public final String toString() {
        return this.displayName;
    }
}
